package ACM.SouthAmerica.y2006;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/* ACM ICPC 2006 South American Regional 
 * Problem E: Onion Layers
 * Type: Computational Geometry
 * Solution: Just do convex hull a few times.
 */
public class E {
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		while(true)
		{
			int n = s.nextInt();
			if(n==0) break;
			
			ArrayList<Point> points = new ArrayList<Point>();
			for(int i = 0; i < n;i++)
			{
				points.add(new Point(s.nextInt(),s.nextInt()));
			}
			int shells = 0;
			Collections.sort(points);
			
			while(true)
			{
				ArrayList<Point> ch = convexHull(points);
				if(ch.size() < 3) break;
				shells++;
				for(Point p: ch)
					points.remove(p);
			}
			if(shells%2 == 0) System.out.println("Do not take this onion to the lab!");
			else System.out.println("Take this onion to the lab!");
		}
	}
	public static ArrayList<Point> convexHull(ArrayList<Point> points)
	{
		ArrayList<Point> ch = new ArrayList<Point>();
		for(int i = 0; i < points.size();i++)
		{
			ch.add(points.get(i));
			
			int size = ch.size();
			while(size >= 3 && ch.get(size-2).cross(ch.get(size-1),ch.get(size-3)) >= 0)
			{
				ch.remove(ch.size()-2);
				size--;
			}
		}
		ArrayList<Point> ch2 = new ArrayList<Point>();
		for(int i = points.size()-1; i >=0;i--)
		{
			ch2.add(points.get(i));
			
			int size = ch2.size();
			while(size >= 3 && ch2.get(size-2).cross(ch2.get(size-1),ch2.get(size-3)) >= 0)
			{
				ch2.remove(ch2.size()-2);
				size--;
			}
		}
		if(ch2.size() > 0)
			ch2.remove(0);
		if(ch2.size() > 0)
			ch2.remove(ch2.size()-1);
		
		ch.addAll(ch2);
		return ch;
	}
	private static class Point implements Comparable<Point>
	{
		double x,y;
		public Point(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		public Point(double x, double y)
		{
			this.x = x;
			this.y = y;
		}
		public Point sub(Point p)
		{
			return new Point(x-p.x,y-p.y);
		}
		@SuppressWarnings("unused")
		public double dist(Point p)
		{
			return Math.sqrt((p.x-x)*(p.x-x) + (p.y-y)*(p.y-y));
		}
		@SuppressWarnings("unused")
		public Point add(double dx, double dy)
		{
			return new Point(x+dx,y+dy);
		}
		public String toString()
		{
			return "("+x+","+y+")";
		}
		public double cross(Point a, Point b)
		{
			return this.sub(a).cross(this.sub(b));
		}
		public double cross(Point p)
		{
			return x*p.y-y*p.x;
		}
		public int compareTo(Point p)
		{
			if(p.x < x) return 1;
			if(p.x > x) return -1;
			if(p.y < y) return 1;
			if(p.y > y) return -1;
			return 0;
		}
	}
}
