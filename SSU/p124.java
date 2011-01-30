package SSU;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.util.*;

/* Saratov State University Online Judge
 * Problem 124: Broken Line
 * Type: Computational Geometry
 * Solution: Recreate the polygon by walking along the outside. Then just test if the point is inside or not using java polygon.
 */

public class p124 {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		HashMap<Point,Point> hm1 = new HashMap<Point,Point>();
		HashMap<Point,Point> hm2 = new HashMap<Point,Point>();

		Point[] P = new Point[N];
		for(int i = 0; i < N;i++)
		{			
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();

			int x2 = sc.nextInt();
			int y2 = sc.nextInt();
			Point p = new Point(x1,y1);
			if(i==0)
				P[0] = p;
			Point q = new Point(x2,y2);
			
			if(hm1.containsKey(p))
			{
				hm2.put(p,q);
			}
			else
				hm1.put(p,q);
			
			if(hm1.containsKey(q))
			{		
				hm2.put(q,p);
			}
			else
				hm1.put(q,p);
		}
		//bad input just guess
		if(hm1.size() != N || hm2.size() != N)
		{
			System.out.println("OUTSIDE");
			return;
		}
		for(int i = 1; i < N;i++)
		{
			if(i==1)
			{
				P[i] = hm1.get(P[i-1]);
			}
			else
			{
				Point p = hm1.get(P[i-1]);
				Point q = hm2.get(P[i-1]);
				if(p.equals(P[i-2]))
				{
					P[i] = q;
				}else{
					P[i] = p;
				}
			}
		}
		hm1.clear();
		hm2.clear();
		int[] xp = new int[N];
		int[] yp = new int[N];
		for(int i = 0; i < N;i++)
		{
			xp[i] = (int)Math.round(P[i].x);
			yp[i] = (int)Math.round(P[i].y);
		}
		Polygon poly = new Polygon(xp,yp,N);
		
		int x0 = sc.nextInt();
		int y0 = sc.nextInt();
		for(int i = 0; i < N;i++)
		{
			if(Line2D.ptSegDist(xp[i], yp[i], xp[(i+1)%N], yp[(i+1)%N], x0,y0) < 1e-6)
			{
				System.out.println("BORDER");
				return;
			}
		}
		if(poly.contains(x0,y0))
		{
			System.out.println("INSIDE");
		}else{
			System.out.println("OUTSIDE");
		}
	}
	private static class Point
	{
		int x,y;
		Point(int a, int b)
		{
			x = a;
			y = b;
		}
		public int hashCode()
		{
			return (x<<8)^y;
		}
		public boolean equals(Object o)
		{
			Point p = (Point)o;
			return x == p.x && y == p.y;
		}
	}
}
