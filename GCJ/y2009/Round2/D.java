package GCJ.y2009.Round2;
import java.util.*;
import static java.lang.Math.*;

/* Google Code Jam 2009 Round 2
 * Problem D: Watering Plants
 * Type: Computational Geometry
 * Solution: Binary search on the radius. For a given circle you can draw a circle s.t. if a
 * point is inside the circle then it is covering the circle. Draws all such circles. You know
 * if a sprinkler is in the intersection of some circles you could move it to an intersection point,
 * or it is inside one circle and can be put in the center. Try all such points and check for a solution
 */


public class D 
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int zz = 1; zz <= T;zz++)
		{
			System.out.print("Case #"+zz+": ");
			N = sc.nextInt();
			xp = new int[N];
			yp = new int[N];
			rp = new int[N];
			
			for(int i = 0; i < N;i++)
			{
				xp[i] = sc.nextInt();
				yp[i] = sc.nextInt();
				rp[i] =sc.nextInt();
			}
			
			double low = 0.0;
			double high = 100000;
			for(int i = 0; i < 200;i++)
			{
				double mid = (high+low)/2;
				if(pos(mid))
				{
					high = mid;
				}else{
					low = mid;
				}
			}
			System.err.println(zz);
			System.out.format("%.015f\n",low);
		}
	}
	private static boolean pos(double mid) 
	{
		x = new int[N];
		y = new int[N];
		r = new double[N];
		pX = new ArrayList<Double>();
		pY = new ArrayList<Double>();
		ha = new ArrayList<Integer>();
		hb = new ArrayList<Integer>();
		for(int i = 0; i < N;i++)
		{
			pX.add((double)xp[i]);
			pY.add((double)yp[i]);
			if(mid >= rp[i])
			{
				ha.add(i);
				hb.add(i);
			}else{
				ha.add(-1);
				hb.add(-1);
			}
			x[i] = xp[i];
			y[i] = yp[i];
			r[i] = mid-rp[i];
		}
		for(int i = 0; i < N;i++)
			for(int j = i+1;j < N;j++)
				addInt(i,j);
		
		boolean[][] cover = new boolean[pX.size()][N];
		for(int i = 0; i < pX.size();i++)
		{
			for(int k = 0; k < N;k++)
			{
				double a = sqrt(pow(pX.get(i)-x[k],2)+pow(pY.get(i)-y[k],2));
				if(a > r[k] )
				{
					if(ha.get(i) == k || hb.get(i) == k)
					{
						cover[i][k] = true;
					}else
					cover[i][k] = false;
				}else{
					cover[i][k] = true;
				}
			}
		}
		for(int i = 0; i < pX.size();i++)
			for(int j = i;j<pX.size();j++)
			{				
				boolean found = true;
				for(int k = 0; k < N;k++)
				{
					if(!cover[i][k] && !cover[j][k])
					{
						found = false;
						break;
					}
				}
				if(found)
				{
					return true;					
				}
			}

		return false;
	}
	public static void addInt(int a, int b)
	{
		double x0 = x[a];
		double x1 = x[b];
		double x2;
		double y0 = y[a];
		double y1 = y[b];
		double y2;
		double r0 = r[a];
		double r1 = r[b];
		
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
	static ArrayList<Integer> ha,hb;
	static ArrayList<Double> pX,pY;
	static int N;
	static int[] xp,yp,rp;
	static int[] x,y;
	static double[] r;
}
