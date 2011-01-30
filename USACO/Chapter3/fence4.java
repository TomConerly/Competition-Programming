package USACO.Chapter3;
/*
ID: theycal2
LANG: JAVA
TASK: fence4
 */
/* USACO Training
 * Closed Fences
 * Type: Computational Geometry
 * Solution: We only need to consider rays from the obs point to
 * end points and mid points. If something's end points are obscured
 * then we can see it just past someone else's end points. When we are 
 * checking these rays and an end point intersects the ray note which side
 * the intersection point goes. 
 * 
 * ---^------^----->
 *   / \    / \
 * In this poor asci drawing the ray goes through even though it is touched twice
 * because it was touched from below both times.
 * 
 *            \ /
 * ----^------>@
 *    / \
 * In this drawing the ray stops because it is bounded by two points.
 */

import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class fence4 
{
	public static void main(String[] args) throws IOException 
	{
		long start = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("fence4.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence4.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());

		N = Integer.valueOf(st.nextToken());
		st = new StringTokenizer(f.readLine());
		vx = Integer.valueOf(st.nextToken());
		vy = Integer.valueOf(st.nextToken());
		ob = new Point(vx,vy);

		points = new Point[N];
		for(int i = 0; i < N;i++)
		{
			st = new StringTokenizer(f.readLine());
			int x = Integer.valueOf(st.nextToken());
			int y = Integer.valueOf(st.nextToken());
			points[i] = new Point(x,y);
		}
		lines = new Line[N];
		for(int i = 0; i < N;i++)
		{
			lines[i] = new Line(points[i],points[(i+1)%N]);
		}
		boolean legal = true;
		for(int i = 0; i < N;i++)
		{
			for(int j = i+1;j < N;j++)
			{
				Point a = lines[i].intersectSeg(lines[j]);				
				if(a != null)
				{
					legal = false;
				}
			}
		}
		if(!legal)
		{
			out.println("NOFENCE");
		}
		else
		{
			visited = new boolean[N];
			for(int i = 0; i < N;i++)
			{
				Point p1 = new Point(points[i].x,points[i].y);
				check(p1);
				
				Point p2 = points[i].midPoint(points[(i+1)%N]);
				check(p2);
			}
			
			int num = 0;
			ArrayList<Integer> pt = new ArrayList<Integer>();
			for(int i = 0; i < N;i++)
			{
				if(visited[i])
				{
					num++;
					int fr = min(i,(i+1)%N);
					int sc = max(i,(i+1)%N);
					pt.add((sc<<16)|fr);
				}

			}
			Collections.sort(pt);
			out.println(num);
			for(int i = 0; i < pt.size();i++)
			{
				int poll = pt.get(i);
				int sc = poll >>> 16;
					int fr = poll & 0xFFFF;
					out.println(((int)points[fr].x)+" "+((int)points[fr].y)+" "+((int)points[sc].x)+" "+((int)points[sc].y));

			}
		}
		out.close();
		System.out.println("$:"+(System.currentTimeMillis()-start));
		System.exit(0);
	}
	private static int sign(double d) {
		if(d < 0) return -1;
		if(d > 0) return 1;
		return 0;
	}
	public static void check(Point to)
	{
		Line li = new Line(ob,to);
		int min = -1;
		Point minP = null;
		double end = Double.MAX_VALUE;
		int endOther = -1;
		//closest point to intersect from left and from right
		double sideL = Double.MAX_VALUE;
		double sideR = Double.MAX_VALUE;
		
		for(int k = 0; k < N;k++)
		{
			Point p = lines[k].intersectSegment(li);

			if(p != null && (minP == null || p.dist(ob) <= minP.dist(ob)))
			{
				//make sure it is on the correct side of the ray
				if(sign(to.x-ob.x) != sign(p.x-ob.x) || sign(to.y-ob.y) != sign(p.y-ob.y)) continue;							

				if(p != null && (p.equals(points[k]) || p.equals(points[(k+1)%N])))
				{
					endOther = p.equals(points[k])? (k+1)%N:k;
					if(li.side(points[endOther]) > 0)
					{
						if(sideR != -1)						
							end = min(end,max(sideR,p.dist(ob)));					
						
						sideL = min(sideL,p.dist(ob));						
					}
					else
					{
						if(sideL != -1)
						{							
							end = min(end,max(sideL,p.dist(ob)));
						}						
						sideR = min(sideR,p.dist(ob));						
					}
				}
				else
				{							
					minP = p;
					min = k;
				}
			}
		}
		if(min != -1)
		{				
			if(minP.dist(ob) < end)
				visited[min] = true;
		}
	}
	static Line[] lines;
	static Point[] points;
	static Point ob;
	static int vx,vy,N;
	static boolean[] visited;


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
		//which side of the line this point is on
		public int side(Point c) {
			Point a = new Point(x1,y1);
			Point b = new Point(x2,y2);

			double l = b.sub(a).cross(c.sub(a));

			return sign(l);
		}

		public String toString()
		{
			return "("+x1+","+y1+")->("+x2+","+y2+")";
		}
		//intersect treating both as lines
		@SuppressWarnings("unused")
		public Point intersect(Line l)
		{
			double det = A*l.B - l.A*B;
			if(det == 0) 
			{
				//parrallel lines, check if they are the same then return any point
				if(abs(C/B -l.C/l.B) < 1e-9)
				{
					return new Point(x1,y1);
				}
				else return null;
			}
			else
			{
				double x = (l.B*C-l.C*B)/det;
				double y = (A*l.C - l.A*C)/det;
				return new Point(x,y);
			}
		}
		//intersect treating this as line segment, l as line
		public Point intersectSegment(Line l)
		{
			double det = A*l.B - l.A*B;
			if(det == 0) 
			{
				//parrallel lines, check if they are the same then return any point
				if(abs(C/B -l.C/l.B) < 1e-9)
				{
					return new Point(x1,y1);
				}
				else return null;
			}
			else
			{
				double x = (l.B*C-l.C*B)/det;
				double y = (A*l.C - l.A*C)/det;
				if(x >= x1 && x <= x2 && y>= min(y1,y2) && y <= max(y1,y2))
					return new Point(x,y);
				else return null;
			}
		}
		//intersect treating both as line segments
		public Point intersectSeg(Line l) {
			double det = A*l.B - l.A*B;
			if(det == 0) 
			{
				//parrallel lines, check if they are the same then return 
				//any point on both segments
				if(abs(C/B -l.C/l.B) < 1e-9)
				{
					if(l.x1 <= x1 && x1 <= l.x2)
						return new Point(x1,y1);
					else if(l.x1 <= x2 && x2 <= l.x2)
						return new Point(x2,y2);
					else return null;
				}
				else return null;
			}
			else
			{
				double x = (l.B*C-l.C*B)/det;
				double y = (A*l.C - l.A*C)/det;
				if((x > x1 && x < x2 && y> min(y1,y2) && y < max(y1,y2))
						&& (x > l.x1 && x < l.x2 && y> min(l.y1,l.y2) && y < max(l.y1,l.y2)))
					return new Point(x,y);
				else return null;
			}
		}
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
		public int compareTo(Point p)
		{
			if(p.x < x) return 1;
			if(p.x > x) return -1;
			if(p.y < y) return 1;
			if(p.y > y) return -1;
			return 0;
		}
		public boolean equals(Point p)
		{
			return (abs(p.x-x) < 1e-12 && abs(p.y-y) < 1e-12);
		}
		public Point midPoint(Point a)
		{
			return new Point((a.x+x)/2.0,(a.y+y)/2.0);
		}
	}
}

