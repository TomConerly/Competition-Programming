package TopCoder.Medium;
import java.util.*;
import static java.lang.Math.*;

/* TopCoder Member Pilot 2
 * Medium Problem 450 Points: TransportationNetwork
 * Type: MST
 * Solution: Use Kruskals, at any point in type you could stop and use airports to connect
 * the rest, take the min over all times.
 */

public class TransportationNetwork {
	public void p(Object s){System.out.println(s);}
	
	int[] parent;
	int[] rank;
	int find(int x)
	{
		if(parent[x] == x)
			return x;
		else
		{
			parent[x] = find(parent[x]);
			return parent[x];
		}
	}
	void union(int x, int y)
	{
		int xroot = find(x);
		int yroot = find(y);
		if(rank[xroot] > rank[yroot])
		{
			parent[yroot] = xroot;
		}
		else if(rank[xroot] < rank[yroot])
		{
			parent[xroot] = yroot;
		}else{
			parent[yroot] = xroot;
			rank[xroot]++;
		}
	}
	public double minCost(int[] cityX, int[] cityY, double roadCost, double airportCost) {
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for(int i = 0; i < cityX.length;i++)
		{
			for(int j = i+1; j < cityX.length;j++)
			{
				edges.add(new Edge(i,j,roadCost*sqrt(pow(cityX[i]-cityX[j],2)+pow(cityY[i]-cityY[j],2))));
			}
		}
		Collections.sort(edges);
		parent = new int[cityX.length];
		rank = new int[cityX.length];
		for(int i = 0; i < parent.length;i++)
			parent[i] = i;
		
		double best = cityX.length*airportCost;
		int used = 0;
		double cost = 0;
		for(int i = 0; i < edges.size();i++)
		{
			Edge e = edges.get(i);
			if(find(e.x) == find(e.y))
				continue;
			cost += e.c;
			union(e.x,e.y);
			used++;
			best = min(best,cost+ (cityX.length-used)*airportCost);
		}
		best = min(best,cost);
		return best;
	}
	private class Edge implements Comparable<Edge>
	{
		int x, y;double c;
		Edge(int a, int b, double d)
		{
			x = a;
			y = b;
			c = d;
		}
		public int compareTo(Edge arg0) {
			return Double.valueOf(c).compareTo(arg0.c);
		}
	}
}
