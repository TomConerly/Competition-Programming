package TopCoder.Hard;
import java.util.*;
import static java.lang.Math.*;

/* TopCoder SRM 432
 * Hard Problem 1000 Points: BuildersCountry
 * Type: MST
 * Solution: If we have two cities a and b and they don't have an edge ignore it for now, 
 * if they have an edge we can determine which one it is better to build first. This is simple
 * and doesn't change the order for any other edge. We can use this to determine the cost of this edge
 * it is the cost of building plus the additional house consturction cost incurred by it. Aftert that
 * we have a MST problem.
 */

public class BuildersCountry {

	long[] a,b,h;
	int[] group;
	long[] c;
	ArrayList<ArrayList<Integer>> graph;
	int N;
	long R;
	public long minCost(int[] before, int[] after, int[] houseCost, String[] g, int roadCost) {
		N = before.length;
		a = new long[N];
		b = new long[N];
		h = new long[N];
		for(int i = 0; i < N;i++)
		{
			a[i] = after[i];
			b[i] = before[i];
			h[i] = houseCost[i];
		}
		R = roadCost;
		c = new long[N];
		for(int i = 0; i < N;i++)
			c[i] = h[i]*(a[i]-b[i]);
		graph = new ArrayList<ArrayList<Integer>>();
		long ans = 0;
		int edge = 0;
		for(int i = 0; i < N;i++)
		{
			graph.add(new ArrayList<Integer>());
			for(int j = 0; j < N;j++)
			{
				if(g[i].charAt(j) == 'Y')
				{
					graph.get(i).add(j);
					if(i < j)
					{
						edge++;
						ans += min(c[i]*b[j]+c[j]*a[i],c[i]*a[j]+c[j]*b[i]);
					}
				}
			}
		}		
		for(int i = 0; i < N;i++)
		{
			ans += (c[i]*(b[i]+a[i]-1))/2;
		}
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		for(int i = 0; i < N;i++)
		{
			for(int j = i+1;j < N;j++)
			{
				if(g[i].charAt(j) == 'N')
				{
					long cost = (b[i]+b[j])*R+min(c[i]*b[j]+c[j]*a[i],c[i]*a[j]+c[j]*b[i]);
					pq.add(new Edge(i,j,cost));
				}			
			}
		}
		group = new int[N];
		int at = 0;
		Arrays.fill(group,-1);
		for(int i = 0; i < N;i++)
		{
			if(group[i] == -1) dfs(i,at++);
		}
		
		while(pq.size() > 0)
		{
			Edge e = pq.poll();			
			if(group[e.a] != group[e.b])
			{
				ans += e.c;
				dfs(e.a,group[e.a],group[e.b]);
				graph.get(e.a).add(e.b);
				graph.get(e.b).add(e.a);
			}
		}
		return ans;
	}
	private void dfs(int i, int g1, int g2) {
		if(group[i] != g1) return;
		group[i] = g2;
		for(int e: graph.get(i))
			dfs(e,g1,g2);
		
	}
	private void dfs(int i, int g) {
		if(group[i] != -1) return;
		group[i] = g;
		for(int e: graph.get(i))
			dfs(e,g);		
	}
	private class Edge implements Comparable<Edge>
	{
		int a,b;
		long c;
		public Edge(int aa,int bb, long cc)
		{
			a = aa;b=bb;c=cc;
		}
		public int compareTo(Edge e) {
			if(c < e.c) return -1;
			if(c > e.c) return 1;
			return 0;
		}
	}
	void print(int[] a)
	{
		for(int b:a)
			System.out.print(b+" ");
		System.out.println();
	}

}
