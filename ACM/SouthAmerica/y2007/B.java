package ACM.SouthAmerica.y2007;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

/* ACM ICPC 2007 South American Regional 
 * Problem B: Ballroom Lights
 * Type: Computational Geometry
 * Solution: For each light make a list of ranges that are covered by ORing together the ranges
 * blocked by each column. Then AND the ranges from all lights to get the dark regions.
 */

public class B {
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		while(true)
		{
			int L = s.nextInt();
			int C = s.nextInt();
			int X = s.nextInt();
			int Y = s.nextInt();
			if(L == 0 && C == 0 && X == 0 && Y == 0) break; 
			Point[] lights = new Point[L];
			for(int i = 0; i < L;i++)
			{
				lights[i] = new Point(s.nextInt(),s.nextInt());
			}
			int[] radius = new int[C];
			Point[] cols = new Point[C];
			for(int i = 0; i < C;i++)
			{
				cols[i] = new Point(s.nextInt(),s.nextInt());
				radius[i] = s.nextInt();
			}
			
			//indexed by, wall, light, list of covered
			ArrayList<ArrayList<ArrayList<Range>>> ranges = new ArrayList<ArrayList<ArrayList<Range>>>();
			int[][][] outside = {{{0,0,X,0},{0,1,0,1},{X,1},{1,0}},
								{{0,Y,X,Y},{0,-1,0,-1},{X,-1},{1,0}},
								{{0,0,0,Y},{1,0,1,0},{Y,-1},{0,1}},
								{{X,0,X,Y},{-1,0,-1,0},{Y,1},{0,1}}};
			for(int i = 0; i < 4;i++)
			{
				ranges.add(new ArrayList<ArrayList<Range>>());
			}
			for(int i = 0; i < L;i++)
			{
				for(int j = 0; j < 4;j++)
				{
					ranges.get(j).add(new ArrayList<Range>());
				}
				for(int j = 0; j < C;j++)
				{
					double dist = lights[i].dist(cols[j]);
					double theta = Math.asin(radius[j]/dist);
					double phi = Math.atan2(cols[j].y-lights[i].y,cols[j].x-lights[i].x);
					
					double distToGo = Math.sqrt(dist*dist-radius[j]*radius[j]);
									
					Point a = lights[i].add(distToGo*Math.cos(phi-theta), distToGo*Math.sin(phi-theta));
					Point b = lights[i].add(distToGo*Math.cos(phi+theta), distToGo*Math.sin(phi+theta));
					
					Line one = new Line(lights[i],a);
					Line two = new Line(lights[i],b);
					
					for(int k = 0; k < 4;k++)
					{
						int[] t = outside[k][0];
						Line bot = new Line(new Point(t[0],t[1]),new Point(t[2],t[3]));
						double[] int1 = one.intersect(bot);
						double[] int2 = two.intersect(bot);
						
						t = outside[k][1];
						boolean dir1 = t[0]*lights[i].x+t[1]*lights[i].y > t[2]*a.x+t[3]*a.y;
						boolean dir2 = t[0]*lights[i].x+t[1]*lights[i].y > t[2]*b.x+t[3]*b.y;
						
						
						
						int max = outside[k][2][0];
						t = outside[k][3];
						if(dir1 && dir2)
						{
							double x1 = Math.min(max,Math.max(0,t[0]*int1[0]+t[1]*int1[1]));
							double x2 = Math.min(max,Math.max(0,t[0]*int2[0]+t[1]*int2[1]));
							ranges.get(k).get(i).add(new Range(Math.min(x1,x2),Math.max(x1,x2)));
						}else if(dir1 || dir2)
						{
							Point vec = dir1 ? a:b;
							double[] inter = dir1?int1:int2;
							
							double ints = Math.min(max,Math.max(0,t[0]*inter[0]+t[1]*inter[1]));
							double cross = crossProduct(vec.x-lights[i].x,vec.y-lights[i].y,cols[j].x-lights[i].x,cols[j].y-lights[i].y);
																				
							if(outside[k][2][1]*cross > 0) ranges.get(k).get(i).add(new Range(ints,max));
							if(outside[k][2][1]*cross < 0) ranges.get(k).get(i).add(new Range(0,ints));
						}
					}
				}
			}
			double total = 0;
			for(int i = 0; i < 4;i++)
			{
				for(int j = 0; j < L;j++)
				{
					or(ranges.get(i).get(j));
				}
				ArrayList<Range> a = and(ranges.get(i));
				double max = (i<2)?X:Y;
				for(Range r: a)
				{
					max -= r.b-r.a;
				}
				total += max;				
			}
			System.out.println(String.format("%.4f",total));
		}
	}
	public static void or(ArrayList<Range> r)
	{
		Collections.sort(r);
		Range next=null,last;
		for(Iterator<Range> it = r.iterator();it.hasNext();)
		{
			last = next;
			next = it.next();
			if(last == null) continue;
			
			if(next.a <= last.b)
			{
				last.b = Math.max(last.b,next.b);
				next = last;
				it.remove();
			}
		}
	}
	
	public static ArrayList<Range> and(ArrayList<ArrayList<Range>> range)
	{
		for(ArrayList<Range> r: range) Collections.sort(r);
		ArrayList<Range> f = range.get(0);
		for(int i = 1; i < range.size();i++)
		{
			int fp = 0;
			int sp = 0;
			ArrayList<Range> newRange = new ArrayList<Range>();
			while(true)
			{
				if(fp >= f.size() || sp >= range.get(i).size()) break;
				Range fr = f.get(fp);
				Range sr = range.get(i).get(sp);
				if(fr.a < sr.a){
					//intersect
					if(sr.a < fr.b)
					{
						newRange.add(new Range(sr.a,Math.min(sr.b,fr.b)));
						if(sr.b < fr.b)
						{
							fr.a = sr.b;
							sp++;
						}else{
							sr.a = fr.b;
							fp++;
						}
					}
					//no intersect
					else
					{
						fp++;
					}
				}
				else
				{
					//intersect
					if(fr.a < sr.b)
					{
						newRange.add(new Range(fr.a,Math.min(fr.b,sr.b)));
						if(fr.b < sr.b)
						{
							sr.a = fr.b;
							fp++;
						}else{
							fr.a = sr.b;
							sp++;
						}
					}
					//no intersect
					else
					{
						sp++;
					}
				}
			}
			f = newRange;
		}
		return f;		
	}
	public static double crossProduct(double x1, double y1, double x2, double y2)
	{
		return x1*y2 - y1*x2;
	}
	private static class Range implements Comparable<Range>
	{
		double a,b;
		public Range(double a1,double b1)
		{
			a = a1;
			b = b1;
		}
		public String toString()
		{
			return "("+a+","+b+")";
		}
		public int compareTo(Range r)
		{
			return a < r.a? -1:1;
		}
	}
	private static class Line
	{
		double A,B,C;
		public Line(Point p1, Point p2)
		{
			A = p2.y-p1.y;
			B = p1.x-p2.x;
			C = A*p1.x+B*p1.y;
		}
		public double[] intersect(Line l)
		{
			double det = A*l.B - l.A*B;
			if(det == 0) return null;
			else
			{
				double x = (l.B*C-l.C*B)/det;
				double y = (A*l.C - l.A*C)/det;
				return new double[] {x,y};
			}
		}
	}
	private static class Point
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
		public double dist(Point p)
		{
			return Math.sqrt((p.x-x)*(p.x-x) + (p.y-y)*(p.y-y));
		}
		public Point add(double dx, double dy)
		{
			return new Point(x+dx,y+dy);
		}
		public String toString()
		{
			return "("+x+","+y+")";
		}
		@SuppressWarnings("unused")
		public double cross(Point p)
		{
			return x*p.y-y*p.x;
		}
	}
}
