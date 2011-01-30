package SSU;
import java.io.*;
import java.util.*;

/* Saratov State University Online Judge
 * Problem 301: Boring. Hot. Summer...
 * Type: Graph Theory
 * Solution: Use Dijkstra to find distance of all points to start and end. Any point where distance start + distance end equals the distance
 * from the actual start to the actual end it is on a shortest path. Go through all such points in order of distance from the actual start
 * keeping track of when it is possible to branch.
 */
public class p301 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		String[] p = f.readLine().split(" ");
		int N = Integer.parseInt(p[0]);
		int X = Integer.parseInt(p[1])-1;
		int Y = Integer.parseInt(p[2])-1;
		 p = f.readLine().split(" ");
		int M = Integer.parseInt(p[0]);
		adj = new ArrayList[N];
		for(int i = 0; i< N;i++)
			adj[i] = new ArrayList<Edge>();
		int[] I = new int[M*3];
		int at = 0;
		while(at != M*3)
		{
			p = f.readLine().split(" ");
			for(String s:p)
			{
				I[at++] = Integer.valueOf(s);
			}
		}
		for(int i = 0; i < M;i++)
		{			 
			int x = I[i*3]-1;
			int y = I[i*3+1]-1;
			int l = I[i*3+2];
			adj[x].add(new Edge(y,l));
			adj[y].add(new Edge(x,l));
		}
		
		PriorityQueue<State> pq = new PriorityQueue<State>();
		int[] D1 = new int[N];
		Arrays.fill(D1,Integer.MAX_VALUE);
		D1[X] = 0;
		pq.add(new State(X,0));
		while(pq.size() > 0)
		{
			State s = pq.poll();
			if(D1[s.at] < s.dist)
				continue;
			for(Edge e:adj[s.at])
			{
				if(D1[e.to] > s.dist + e.dist)
				{
					D1[e.to] = s.dist + e.dist;
					pq.add(new State(e.to,D1[e.to]));
				}
			}
		}
		pq.clear();
		int[] D2 = new int[N];
		Arrays.fill(D2,Integer.MAX_VALUE);
		D2[Y] = 0;
		pq.add(new State(Y,0));
		while(pq.size() > 0)
		{
			State s = pq.poll();
			if(D2[s.at] < s.dist)
				continue;
			for(Edge e:adj[s.at])
			{
				if(D2[e.to] > s.dist + e.dist)
				{
					D2[e.to] = s.dist + e.dist;
					pq.add(new State(e.to,D2[e.to]));
				}
			}
		}
		pq.clear();
		boolean[] G = new boolean[N];
		for(int i = 0; i < N;i++)
		{
			if(D1[i] + D2[i] == D1[Y])
			{
				G[i] = true;
			}
		}
		for(int i = 0; i < N;i++)
		{
			if(G[i])
				pq.add(new State(i,D1[i]));
		}
		int points = 1;
		int[] C = new int[N];
		C[X] = 1;
		int[] A = new int[N];
		while(pq.size() > 0)
		{
			State s = pq.poll();
			points -= C[s.at];
			
			LinkedList<State> L = new LinkedList<State>();
			L.add(s);
			
			while(pq.peek() != null && pq.peek().dist == s.dist)
			{
				s = pq.poll();
				points -= C[s.at];
				L.add(s);
			}
			for(State ss:L)
			{
				A[ss.at] = L.size()+points;
			}

			for(State ss:L)
			{
				for(Edge e:adj[ss.at])
				{
					if(!G[e.to])
						continue;
					if(D1[e.to] != D1[ss.at]+e.dist)
						continue;
					points++;
					C[e.to]++;
				}
			}
		}
		StringBuilder ans = new StringBuilder();
		for(int i = 0; i < N;i++)
		{
			ans.append(A[i]+" ");
		}
		System.out.println(ans);
	}
	static ArrayList<Edge>[] adj;
	private static class Edge{
		int to ,dist;
		public Edge(int a, int c)
		{
			to  = a;
			dist = c;
		}
	}
	private static class State implements Comparable<State>
	{
		int at, dist;
		State(int a, int b)
		{
			at = a;
			dist = b;
		}
		@Override
		public int compareTo(State arg0) {
			return Integer.valueOf(dist).compareTo(arg0.dist);
		}
	}
}
