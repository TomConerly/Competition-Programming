package SSU;
import java.util.*;

/* Saratov State University Online Judge
 * Problem 103: Traffic Lights
 * Type: Dijkstra
 * Solution: Standard shortest path. Only hard part is a check for the next time when two lights match.
 */

public class p103 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		st = sc.nextInt()-1;
		en = sc.nextInt()-1;
		N = sc.nextInt();
		M = sc.nextInt();
		
		C = new int[N];
		R = new int[N];
		T = new int[N][2];
		
		edges = new ArrayList[N];
		len = new ArrayList[N];
		for(int i = 0; i < N;i++)
		{
			edges[i] = new ArrayList<Integer>();
			len[i] = new ArrayList<Integer>();
			String s = sc.next();
			if(s.equals("B"))
			{
				C[i] = 0;
			}else{
				C[i] =1;
			}
			R[i] = sc.nextInt();
			T[i][0] = sc.nextInt();
			T[i][1] = sc.nextInt();			
		}
		
		
		for(int i = 0; i < M;i++)
		{
			int a = sc.nextInt()-1;
			int b = sc.nextInt()-1;
			int l = sc.nextInt();
			edges[a].add(b);
			len[a].add(l);
			
			edges[b].add(a);
			len[b].add(l);
		}
		PriorityQueue<State> pq = new PriorityQueue<State>();
		pq.add(new State(st,0));
		int[] v = new int[N];
		int[] pred = new int[N];
		Arrays.fill(v,Integer.MAX_VALUE);
		int ans = -1;
		while(pq.size() > 0)
		{
			State s = pq.poll();
			if(v[s.at] < s.c)
				continue;
			if(s.at == en)
			{
				ans = s.c;
				break;
			}
			for(int i = 0; i < edges[s.at].size();i++)
			{
				int to = edges[s.at].get(i);
				int t = time(s.at,to,s.c);
				if(t == -1)
					continue;
				
				t += len[s.at].get(i);
				
				if(t < v[to])
				{
					pred[to] = s.at;
					v[to] = t; 
					pq.add(new State(to,t));
				}
			}
		}
		if(ans == -1)
		{
			System.out.println(0);
		}else{
			System.out.println(ans);
			int at = en;
			String an = "";
			while(true)
			{
				an = (at+1)+" "+an;
				if(at == st)
					break;
				at =  pred[at];
			}
			System.out.println(an);
		}
	}
	private static int time(int a, int b, int t) 
	{
		int ca = color(a,t);
		int la = left(a,t);
		
		int cb = color(b,t);
		int lb = left(b,t);
		
		
		for(int i = 0; i <= 200;i++)
		{
			if(la == 0)
			{
				ca = 1-ca;
				la = T[a][ca];
			}
			if(lb == 0)
			{
				cb = 1-cb;
				lb = T[b][cb];
			}
			if(ca == cb)
			{
				return t+i;
			}
			la--;
			lb--;
		}
		
		return -1;
	}
	private static int color(int a, int t) {
		t %= T[a][0]+T[a][1];
		if(t < R[a])
		{
			return C[a];
		}
		if(t-R[a] < T[a][1-C[a]])
		{
			return 1-C[a];
		}
		return C[a];
	}
	private static int left(int a, int t) 
	{
		t %= T[a][0]+T[a][1];
		if(t < R[a])
		{
			return R[a]-t;
		}
		if(t-R[a] < T[a][1-C[a]])
		{
			return T[a][1-C[a]] - (t-R[a]);
		}
		return T[a][C[a]] - (t-R[a]-T[a][1-C[a]]);
	}
	static ArrayList<Integer>[] len;
	static ArrayList<Integer>[] edges;
	static int[] C,R;
	static int[][] T;
	static int st,en,N,M;
	private static class State implements Comparable<State>
	{
		int at, c;
		State(int a, int b)
		{
			at = a;
			c = b;
		}
		
		public int compareTo(State s) {
			return Integer.valueOf(c).compareTo(s.c);
		}
	}
	
}
