package TopCoder.Hard;
import java.util.*;
import static java.lang.Math.*;

/* TopCoder SRM 393
 * Hard Problem 1000 Points: AirlineInternet
 * Type: Computational Geometry
 * Solution: Binary search over possible values for R. Given a value for R find when
 * a flight is exactly distance R between another airport or another flight, mark
 * this time + a little and - a little in a list. For each time point in the list
 * see if the system is legal, if it isn't then its bad, otherwise its good.
 */
public class AirlineInternet {

	double[][] airport;
	int[][] flights;
	public double minimumRange(String[] airportLocations, String[] flight) {
		airport = new double[airportLocations.length][2];
		for(int i = 0; i < airportLocations.length;i++)
		{
			String[] s = airportLocations[i].split(" ");
			airport[i][0] = Integer.valueOf(s[0]);
			airport[i][1] = Integer.valueOf(s[1]);
		}
		flights = new int[flight.length][4];
		for(int i = 0; i < flight.length;i++)
		{
			String[] s = flight[i].split(" ");
			flights[i][0] = Integer.valueOf(s[0]);
			flights[i][1] = Integer.valueOf(s[1]);
			flights[i][2] = Integer.valueOf(s[2]);
			flights[i][3] = Integer.valueOf(s[3]);
		}

		double min = 0.0,max=1e12;
		int iter = 0;
		while(iter < 100)
		{
			double guess = (min+max)/2;
			if(work(guess))
			{	
				max = guess;
			}else{
				min = guess;
			}
			iter++;
		}
		return (min+max)/2;
	}
	double eps = 1e-12;
	private boolean work(double guess) {
		ArrayList<Double> cp = new ArrayList<Double>();

		for(int i = 0; i < flights.length;i++)
		{
			
			for(int j = 0; j < airport.length;j++)
			{
				double x0 = airport[flights[i][0]][0];
				double y0 = airport[flights[i][0]][1];
				double x1 = airport[flights[i][1]][0];
				double y1 = airport[flights[i][1]][1];
				double t0 = flights[i][2];
				double t1 = flights[i][3];
				double a = (y1-y0)/(t1-t0);
				double b = (x1-x0)/(t1-t0);
				
				double c = airport[j][0];
				double d = airport[j][1];

				double m = a*a+b*b;
				double n = 2*a*(y0-d)+2*b*(x0-c);
				double o = pow(y0-d,2)+pow(x0-c,2)-pow(guess,2);
				cp.addAll(solve(m,n,o,t0,t1));				
			}
			for(int j = i+1;j< flights.length;j++)
			{	

				double x0 = airport[flights[i][0]][0];
				double y0 = airport[flights[i][0]][1];
				double x1 = airport[flights[i][1]][0];
				double y1 = airport[flights[i][1]][1];
				double t0 = flights[i][2];
				double t1 = flights[i][3];
				double a = (y1-y0)/(t1-t0);
				double b = (x1-x0)/(t1-t0);
				
				double ox0 = airport[flights[j][0]][0];
				double oy0 = airport[flights[j][0]][1];
				double ox1 = airport[flights[j][1]][0];
				double oy1 = airport[flights[j][1]][1];
				double ot0 = flights[j][2];
				double ot1 = flights[j][3];
				double oa = (oy1-oy0)/(ot1-ot0);
				double ob = (ox1-ox0)/(ot1-ot0);
				
				double ts = max(t0,ot0);
				double te = min(t1,ot1);
				if(ts >= te) continue;
				
				
				double c = ox0+ob*(ts-ot0);
				double d = oy0+oa*(ts-ot0);
				x0 += b*(ts-t0);
				y0 += a*(ts-t0);
				a -= oa;
				b -= ob;
				
				double m = a*a+b*b;
				double n = 2*a*(y0-d)+2*b*(x0-c);
				double o = pow(y0-d,2)+pow(x0-c,2)-pow(guess,2);
				Collection<Double> temp = solve(m,n,o,ts,te);
				cp.addAll(temp);
			}
			
		}
		
		for(double a: cp)
		{
			if(legal(guess,a) == false) return false;
		}
		return true;
	}
	private Collection<Double> solve(double m, double n, double o,double t0,double t1) {
		double d = t0;
		ArrayList<Double> cp = new ArrayList<Double>();
		double f = (-n + sqrt(n*n-4.0*m*o))/(2.0*m);
		double g = (-n - sqrt(n*n-4.0*m*o))/(2.0*m);
		if(Double.isNaN(f) == false && f+d >= t0 && f+d <= t1)
		{
			cp.add(f-eps+d);
			cp.add(f+eps+d);
		}
		if(Double.isNaN(g) == false && g+d >= t0 && g+d <= t1)
		{
			cp.add(g-eps+d);
			cp.add(g+eps+d);
		}
		return cp;
	}
	int[] have;
	double[][] loc;
	int at;
	double guess;
	private boolean legal(double g, double time)
	{
		guess = g;
		loc = new double[flights.length+airport.length][2];
		have = new int[flights.length+airport.length];
		at = 0;
		for(int i = 0; i < flights.length;i++)
		{
			if(time < flights[i][2] || time > flights[i][3]) continue;
			double p = (time-flights[i][2])/(flights[i][3]-flights[i][2]);
			loc[at][0] = (1-p)*airport[flights[i][0]][0]+p*airport[flights[i][1]][0];
			loc[at][1] = (1-p)*airport[flights[i][0]][1]+p*airport[flights[i][1]][1];
			have[at] = 0;
			at++;
		}
		for(int i = 0; i < airport.length;i++)
		{
			loc[at][0] = airport[i][0];
			loc[at][1] = airport[i][1];
			have[at] = 1;
			at++;
		}
		for(int i = 0; i < at;i++)
		{
			if(have[i] == 1)
				dfs(i);
		}
		for(int i = 0; i < at;i++)
		{
			if(have[i] == 0) return false;
		}
		return true;
	}

	private void dfs(int t) {
		if(have[t] == 2) return;
		have[t] = 2;
		for(int i = 0; i < at;i++)
		{
			if(have[i] == 0 && pow(loc[i][0]-loc[t][0],2)+pow(loc[i][1]-loc[t][1],2) <= guess*guess)
			{
				dfs(i);
			}
		}
	}

	void print(double[][] a)
	{
		for(double[] c: a)
		{
			for(double b:c)
				System.out.print(b+" ");
			System.out.println();
		}
		System.out.println();
	}

}
