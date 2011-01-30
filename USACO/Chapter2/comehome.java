package USACO.Chapter2;
/*
ID: theycal2
LANG: JAVA
TASK: comehome
 */
/* USACO Training
 * Bessie Come Home
 * Type: Graph Theory
 * Solution: Run Dijkstra.
 */
import java.io.*;
import java.util.*;

public class comehome 
{
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("comehome.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("comehome.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int P = Integer.parseInt(st.nextToken());
		int N = 26*2;
		int[][] adj = new int[N][N];

		for(int i = 0; i < N;i++)
			Arrays.fill(adj[i],-1);

		for(int i = 0; i < P;i++)
		{
			st = new StringTokenizer(f.readLine());

			char s1 = st.nextToken().charAt(0);
			int s = Character.isUpperCase(s1)?s1-'A'+26:s1-'a';

			char e1 = st.nextToken().charAt(0);
			int e = Character.isUpperCase(e1)?e1-'A'+26:e1-'a';

			int l = Integer.parseInt(st.nextToken());
			if(adj[s][e] == -1 || l < adj[s][e])
			{
				adj[s][e] = l;
				adj[e][s] = l;
			}
		}

		int[] dist = new int[N];
		Arrays.fill(dist,Integer.MAX_VALUE);
		PriorityQueue<Long> pq = new PriorityQueue<Long>();
		pq.add((0L<<32)|(51));

		while(pq.size() > 0)
		{
			long p = pq.poll();
			int at = (int)(p&0xFFFFFFFFL);
			int cost = (int)(p>>>32);	


			if(cost >= dist[at]) continue;
			dist[at] = cost;

			for(int i = 0; i < N;i++)
			{
				if(i==at) continue;
				if(adj[at][i] == -1) continue;

				if(cost + adj[at][i] < dist[i])
				{
					pq.add((((long)cost + adj[at][i])<< 32)|i);
				}
			}
		}
		int maxAt = 26;
		for(int i = 26; i < N-1;i++)
		{
			if(dist[i] == Integer.MAX_VALUE) continue;
			if(dist[maxAt] > dist[i])
			{
				maxAt = i;
			}
		}
		out.println(((char)((maxAt-26)+'A'))+" "+dist[maxAt]);

		out.close();
		System.exit(0);
	}
}

