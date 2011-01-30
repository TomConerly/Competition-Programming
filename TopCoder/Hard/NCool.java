package TopCoder.Hard;
import java.util.*;
import static java.lang.Math.*;


/* TopCoder SRM 401
 * Hard Problem 950 Points: NCool
 * Type: Computational Geometry
 * Solution: Generate all the points using convex hull
 * followed by a line sweep. Then two points are on the end points
 * of an ncool line if x1%(n-1) = x2%(n-1) and y1%(n-1)=y2%(n-1).
 * Save the values for x%(n-1) and y%(n-1) into a list and sort it
 * then its trivial to find if a point is ncool or not.
 */

public class NCool {

	public int nCoolPoints(int[] x, int[] y, int n) {
		n--;
		ArrayList<Point> points = new ArrayList<Point>();
		for(int i = 0; i < x.length;i++)
		{
			points.add(new Point(x[i],y[i]));
		}
		ArrayList<Point> hull = convexHull(points,false);
		
		ArrayList<Line> lines = new ArrayList<Line>();
		for(int i = 0; i < hull.size()-1;i++)
		{
			Point a = hull.get(i);
			Point b = hull.get(i+1);
			lines.add(new Line(a,b));
		}
		int ymax = Integer.MIN_VALUE;
		int ymin = Integer.MAX_VALUE;
		for(int i = 0; i < y.length;i++)
		{
			ymax = max(ymax,y[i]);
			ymin = min(ymin,y[i]);
		}
		ArrayList<Integer> p = new ArrayList<Integer>();
		for(int i = ymax; i >= ymin;i--)
		{
			Point first = null;
			Point second = null;
			Line test = new Line(new Point(0,i),new Point(1,i));
			for(Line l:lines)
			{
				Point inter = l.intersectSegment(test);
				if(inter != null)
				{				
					if(inter.equals(first) || inter.equals(second)) continue;
					
					if(first == null) first = inter;
					else second = inter;
				}
			}
		//	System.out.println(i+" "+first+" "+second);
			if(second == null)
			{
				int x1 = (int)(first.x);
				p.add(((x1%n)<<16) | (i%n));
				//System.out.println(x1+" "+i);
				
			}else{
				int x1 = (int)(Math.ceil(min(first.x,second.x)));
				int x2 = (int)(Math.floor(max(first.x,second.x)));
				for(int j =x1; j<= x2;j++)
				{
					p.add(((j%n)<<16)|(i%n));
					//System.out.println(j+" "+i);
				}
			}
		}
		Collections.sort(p);
		
		boolean[] matched = new boolean[p.size()];
		for(int at = 0; at < p.size()-1;at++)
		{
			if(p.get(at).equals(p.get(at+1))){
				matched[at] = true;
				matched[at+1] = true;
			}
		}
		int count = 0;
		for(boolean b: matched)
			if(b) count++;
		return count;
	}
	private static class Line
	{
		double A,B,C;
		double x1,y1,x2,y2;
		public Line(Point p1, Point p2)
		{
			A = p2.y-p1.y;
			B = p1.x-p2.x;
			C = A*p1.x+B*p1.y;
			if(p1.x < p2.x)
			{
				x1 = p1.x;
				y1 = p1.y;
				x2 = p2.x;
				y2 = p2.y;
			}else{
				x1 = p2.x;
				y1 = p2.y;
				x2 = p1.x;
				y2 = p1.y;
			}
		}
		public String toString()
		{
			return "("+x1+","+y1+")->("+x2+","+y2+")";
		}
		@SuppressWarnings("unused")
		public Point intersect(Line l)
		{
			double det = A*l.B - l.A*B;
			if(det == 0) return null;
			else
			{
				double x = (l.B*C-l.C*B)/det;
				double y = (A*l.C - l.A*C)/det;
				return new Point(x,y);
			}
		}
		public Point intersectSegment(Line l)
		{
			double det = A*l.B - l.A*B;
			if(det == 0) return null;
			else
			{
				double x = (l.B*C-l.C*B)/det;
				double y = (A*l.C - l.A*C)/det;
				if(x >= x1 && x <= x2 && y>= min(y1,y2) && y <= max(y1,y2))
					return new Point(x,y);
				else return null;
			}
		}
	}
	//jarvis march 
	//option for adding or not points that are colinear
	public static ArrayList<Point> convexHull(ArrayList<Point> points,boolean edges)
	{
		if(points.size() == 0) return new ArrayList<Point>();
		int n = points.size();
		int leftMost = 0;
		for(int i = 1; i < n;i++)
		{
			Point lm = points.get(leftMost);
			Point x = points.get(i);
			if(lm.x > x.x || (lm.x == x.x && lm.y < x.y))
			{
				leftMost = i;
			}
		}
		int start = leftMost;
		int at = start;
		ArrayList<Point> ans = new ArrayList<Point>();
		ans.add(points.get(at));
		boolean[] used = new boolean[n];
		do{
			int next = -1;
			double dist = edges?Double.MAX_VALUE:0;
			for(int i = 0; i < n;i++)
			{
				if(i==at) continue;
				if(used[i]) continue;
				
				if(next == -1) next = i;				
				double cross = points.get(i).sub(points.get(at)).cross(points.get(next).sub(points.get(at)));
				
				double d = points.get(i).sub(points.get(at)).dist(points.get(next).sub(points.get(at)));
				if(cross < 0)
				{
					next = i;
					dist = d;
				}else if(cross == 0)
				{
					if(edges && d < dist)
					{
						dist = d;
						n = i;
					}else if(!edges && d > dist)
					{
						dist = d;
						n = i;
					}
				}
			}
			if(next == -1) return ans;
			at = next;
			ans.add(points.get(at));
			used[at] = true;
		}while(start != at);
		return ans;
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
		public double dist(Point p)
		{
			return Math.sqrt((p.x-x)*(p.x-x) + (p.y-y)*(p.y-y));
		}
		@SuppressWarnings("unused")
		public Point add(double dx, double dy)
		{
			return new Point(x+dx,y+dy);
		}
		@SuppressWarnings("unused")
		public double cross(Point a, Point b)
		{
			return this.sub(a).cross(this.sub(b));
		}
		public String toString()
		{
			return "("+x+","+y+")";
		}
		public double cross(Point p)
		{
			return x*p.y-y*p.x;
		}
		public boolean equals(Point p)
		{
			if(p == null) return false;
			if(Math.abs(x-p.x) < 1e-9 && Math.abs(y-p.y) < 1e-9) return true;
			return false;
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
