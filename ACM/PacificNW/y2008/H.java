package ACM.PacificNW.y2008;
import java.util.*;
import static java.lang.Math.*;

/* ACM Pacific Northwest 2008
 * Problem H: Repair Depots
 * Type: Computational Geometry/DP
 * Solution: Binary search on the radius. For a given radius draw a circle for each point s.t. if a 
 * repair depot is in the circle it covers the point within distance r. W.L.O.G. the repair depots
 * are on the intersection of two such circles or at the center. Try all of these points for centers,
 * for each one you have a subset of the points covered by it. Now do dp[subset][movesLeft] to attempt
 * to cover the subset using only C moves. 
 */

public class H 
{
	public static void main(String[] args)
	{
		long st = System.currentTimeMillis();
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int zz = 0; zz < T;zz++)
		{
			N = sc.nextInt();
			C = sc.nextInt();
			x = new double[N];
			y = new double[N];
			for(int i = 0; i < N;i++)
			{
				x[i] = sc.nextDouble();
				y[i] = sc.nextDouble();
			}
			double low = 0;
			double high = 100.0;
			for(int i = 0; i < 100;i++)
			{
				double mid = (low+high)/2;
				if(pos(mid))
				{
					high = mid;
				}
				else
				{
					low = mid;
				}
			}
			System.out.format("%.06f\n",low);
		}
		long en = System.currentTimeMillis();
		System.err.println("time: "+(en-st));
	}
	private static boolean pos(double r) 
	{
		pX = new ArrayList<Double>();
		pY = new ArrayList<Double>();
		ha = new ArrayList<Integer>();
		hb = new ArrayList<Integer>();
		for(int i = 0; i < N;i++)
		{
			for(int j = i+1; j < N;j++)
			{
				addInt(i,j,r);
			}
			pX.add(x[i]);
			pY.add(y[i]);
			ha.add(i);
			hb.add(i);
		}
		dp = new int[1<<N][C+1];
		for(int i = 0; i < dp.length;i++)
			Arrays.fill(dp[i],-1);
		
		moves = new int[pX.size()];
		for(int i = 0; i < pX.size();i++)
		{
			int m = 0;
			for(int j = 0; j < N;j++)
			{
				if(ha.get(i) == j || hb.get(i) == j)
					m |= (1<<j);
				double dist = sqrt(pow(pX.get(i)-x[j],2)+pow(pY.get(i)-y[j],2));
				if(dist <= r)
					m |= (1<<j);
			}
			moves[i] = m;
		}
		return recur(0,C);
	}
	private static boolean recur(int sub, int numLeft) 
	{
		if(sub == (1<<N)-1)
		{
			return true;
		}
		if(dp[sub][numLeft] != -1)
			return dp[sub][numLeft] == 1;
		if(numLeft == 0)
			return false;
		boolean ret = false;
		for(int i = 0; i < moves.length;i++)
		{
			int temp = sub | moves[i];
			if(temp != sub)
			{
				ret = recur(temp,numLeft-1);
				if(ret)
					break;
			}
		}
		dp[sub][numLeft] = ret ? 1:0;
		return ret;
	}
	static int[] moves;
	static int[][] dp;
	static double[] x,y;
	static int N,C;
	static ArrayList<Double> pX,pY;
	static ArrayList<Integer> ha,hb;
	
	public static void addInt(int a, int b, double r)
	{
		double x0 = x[a];
		double x1 = x[b];
		double x2;
		double y0 = y[a];
		double y1 = y[b];
		double y2;
		double r0 = r;
		double r1 = r;
		
		double d = sqrt(pow(x0-x1,2)+pow(y0-y1,2));	
		if(d > r0+r1)
			return;
		if(d < abs(r0-r1))
			return;
		
		
		double a0 = (r0*r0-r1*r1+d*d)/(2*d);
		double h = sqrt(r0*r0-a0*a0);
		x2 = x0 + a0*(x1-x0)/d;
		y2 = y0 + a0*(y1-y0)/d;
		double x3 = x2-h*(y1-y0)/d;
		double y3 = y2+h*(x1-x0)/d;		
		double x4 = x2+h*(y1-y0)/d;
		double y4 = y2-h*(x1-x0)/d;
		
		pX.add(x3);
		pX.add(x4);
		pY.add(y3);
		pY.add(y4);
		ha.add(a);ha.add(a);
		hb.add(b);hb.add(b);
		
		double d0 = sqrt(pow(x0-x3,2)+pow(y0-y3,2));
		if(abs(d0-r0) > 1e-9)
			System.out.println("ERROR1 "+d0+" "+r0);
		
		d0 = sqrt(pow(x1-x3,2)+pow(y1-y3,2));
		if(abs(d0-r1) > 1e-9)
			System.out.println("ERROR2 "+d0+" "+r1);
		
		double d1 = sqrt(pow(x0-x4,2)+pow(y0-y4,2));
		if(abs(d1-r0) > 1e-9)
			System.out.println("ERROR3 "+d1+" "+r0);
		
		d1 = sqrt(pow(x1-x4,2)+pow(y1-y4,2));
		if(abs(d1-r1) > 1e-9)
			System.out.println("ERROR4"+" "+d1+" "+r1);
	}
}
