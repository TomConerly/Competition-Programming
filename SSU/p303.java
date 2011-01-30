package SSU;
import java.util.*;
import static java.lang.Math.*;

/* Saratov State University Online Judge
 * Problem 303: Great Berland Wall
 * Type: Graph Theory/Geometry
 * Solution: Similar to 198. Use Dijkstra on an augmented graph. Search for a cycle that goes around one, but not the other. Keep track of angle relative to both
 * and find min cost to reach the same vertex twice but with different angle on one but not the other. (Off by 2PI).
 */

public class p303 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		adj = new ArrayList[N];
		hm = new HashMap<Point,Integer>();
		P = new Point[N];
		for(int i = 0; i < N;i++)
		{
			Point a = new Point(sc.nextInt(),sc.nextInt());
			Point b = new Point(sc.nextInt(),sc.nextInt());
			int c = sc.nextInt();
			int aa = add(a);
			int bb = add(b);
			adj[aa].add(new Edge(bb,c, i+1));
			adj[bb].add(new Edge(aa,c,i+1));
		}
		st = new Point(sc.nextInt(),sc.nextInt());
		en = new Point(sc.nextInt(),sc.nextInt());

		int ans = Integer.MAX_VALUE;
		int[] ansA = null;
		for(int i = 0; i < hm.size();i++)
		{
			PriorityQueue<State> pq = new PriorityQueue<State>();
			pq.add(new State(i,0,ang(st,P[i]),ang(en,P[i])));

			St[][][] prev = new St[hm.size()][3][3];
			int[][][] C = new int[hm.size()][3][3];
			for(int k = 0; k < C.length;k++)
				for(int j = 0; j < C[0].length;j++)
					Arrays.fill(C[k][j],Integer.MAX_VALUE);
			double[][] A = new double[hm.size()][2];
			A[i] = new double[] {ang(st,P[i]),ang(en,P[i])};
			C[i][1][1] = 0;

			while(pq.size() > 0)
			{
				State s = pq.poll();
				int a = (int)round((s.ang1-A[s.at][0])/(2*PI))+1;
				int b = (int)round((s.ang2-A[s.at][1])/(2*PI))+1;
				if(C[s.at][a][b] < s.c)
					continue;

				if(s.at == i &&((a==1 && b != 1) || (a!= 1 && b == 1)))
				{
					if(s.c < ans)
					{
						ans = s.c;
						ArrayList<Integer> an = new ArrayList<Integer>();
						int at = s.at;
						while(at != i || a != 1 || b != 1)
						{
							St temp = prev[at][a][b];
							an.add(temp.num);
							at = temp.pat;
							a = temp.pa;
							b = temp.pb;
						}
						ansA = new int[an.size()];
						for(int k =0; k < an.size();k++)
						{
							ansA[k] = an.get(k);
						}
					}
					break;
				}

				for(Edge e:adj[s.at])
				{
					State to = new State(e.to,s.c+e.c,s.ang1+angDelta(s.at,e.to,st),s.ang2+angDelta(s.at,e.to,en));
				
					if(C[to.at][1][1] == Integer.MAX_VALUE)
					{
						A[to.at][0] = to.ang1;
						A[to.at][1] = to.ang2;
					}
					int na = (int)round((to.ang1-A[to.at][0])/(2*PI))+1;
					int nb = (int)round((to.ang2-A[to.at][1])/(2*PI))+1;
					if(na < 0 || na > 2 || nb < 0 || nb > 2)
						continue;
					if(C[e.to][na][nb] > to.c)
					{
						C[e.to][na][nb] = to.c;
						prev[e.to][na][nb] = new St(e.num,s.at,a,b);
						pq.add(to);						
					}
				}
			}
		}
		if(ans == Integer.MAX_VALUE)
		{
			while(true);
		}
		System.out.println(ans);
		System.out.println(ansA.length);
		for(int a:ansA)
			System.out.print(a+" ");
		System.out.println();
	}
	static double eps = 1e-9;

	static Point en,st;
	static Point[] P;
	static int N;
	static HashMap<Point,Integer> hm;
	static ArrayList<Edge>[] adj;
	
	private static double angDelta(int at, int to, Point p) {
		int x1 = P[at].x - p.x;
		int y1 = P[at].y - p.y;
		int x2 = P[to].x - p.x;
		int y2 = P[to].y - p.y;

		double theta = abs(atan2(y1,x1)-atan2(y2,x2));
		if(theta > PI)
		{
			theta = 2*PI-theta;
		}
		double d1 = sqrt(pow(x1,2)+pow(y1,2));
		double d2 = sqrt(pow(x2,2)+pow(y2,2));
		double x = x1/d1*cos(theta)-y1/d1*sin(theta);
		double y = x1/d1*sin(theta)+y1/d1*cos(theta);
		double ans = 0;
		if(abs(x-x2/d2) < 1e-5 && abs(y-y2/d2) < 1e-5)
		{
			ans = theta;
		}else{
			ans = -theta;
		}
		return ans;
	}
	private static double ang(Point fix, Point p) {
		int x = p.x-fix.x;
		int y = p.y-fix.y;
		return atan2(y, x);
	}
	private static class St
	{
		int num, pat,pa,pb;
		St(int a, int b, int c, int d)
		{
			num = a;
			pat = b;
			pa = c;
			pb = d;
		}
	}
	private static class State implements Comparable<State>
	{
		int at, c;
		double ang1,ang2;
		public State(int a, int b, double cc,double dd)
		{
			at = a;
			c = b;
			ang1 = cc;
			ang2 = dd;
		}
		public int compareTo(State s) {
			return Integer.valueOf(c).compareTo(s.c);
		}
	}
	private static int add(Point p) {
		if(hm.containsKey(p))
			return hm.get(p);
		else{
			int ret = hm.size();
			hm.put(p,ret);
			adj[ret] = new ArrayList<Edge>();
			P[ret] = p;
			return ret;
		}
	}

	private static class Edge
	{
		int to, c, num;
		public Edge(int a, int b, int n)
		{
			to = a;
			c = b;
			num = n;
		}
	}
	private static class Point
	{
		int x,y;
		Point(int a,int b)
		{
			x = a;
			y = b;
		}
		public boolean equals(Object o)
		{
			Point p = (Point)o;
			return x == p.x && y == p.y;
		}
		public int hashCode()
		{
			return x ^ (y <<8);
		}
	}
}