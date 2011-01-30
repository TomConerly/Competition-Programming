package CMUInvitional.y2009;
import java.util.*;
import static java.lang.Math.*;

/* CMU Spring Invitational 2009
 * Problem D: Tunnels
 * Type: Dijkstra
 * Solution: Use Dijkstra to search for widest tunnel, alternatively
 * you can binary search for smallest, or use union find.
 */

public class D {
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		for(int z = 0; z < N;z++)
		{
			hm = new HashMap<Integer,Integer>();
			S = add(sc.nextInt());
			E = add(sc.nextInt());
			L = sc.nextInt();
			int w = sc.nextInt();
			
			graph = new ArrayList<ArrayList<Integer>>();
			cost = new ArrayList<ArrayList<Integer>>();
			
			for(int i = 0; i < L;i++)
			{
				int x1 = add(sc.nextInt());
				int x2 = add(sc.nextInt());
				int c = sc.nextInt();
				while(graph.size() <= x1)
				{
					graph.add(new ArrayList<Integer>());
					cost.add(new ArrayList<Integer>());
				}
				while(graph.size() <= x2)
				{
					graph.add(new ArrayList<Integer>());
					cost.add(new ArrayList<Integer>());
				}
				graph.get(x1).add(x2);
				graph.get(x2).add(x1);
				cost.get(x1).add(c);
				cost.get(x2).add(c);
			}
			
			PriorityQueue<Node> pq = new PriorityQueue<Node>();
			int[] dist = new int[graph.size()];
			Arrays.fill(dist,-1);
			pq.add(new Node(S,Integer.MAX_VALUE));
			dist[S] = Integer.MAX_VALUE;
			int ans = -1;
			while(pq.size() > 0)
			{
				Node at = pq.poll();
				
				if(dist[at.at] > at.cost) continue;
				
				if(at.at == E)
				{
					ans = at.cost;
					break;
				}
				for(int i = 0; i < graph.get(at.at).size();i++)
				{
					int c = min(cost.get(at.at).get(i),at.cost);
					int to = graph.get(at.at).get(i);
					if(c > dist[to])
					{
						dist[to] = c;
						pq.add(new Node(to,c));
					}
				}
			}
			if(ans < w) System.out.println("NOT POSSIBLE");
			else System.out.println(ans);
			
		}
	}
	static ArrayList<ArrayList<Integer>> cost;
	static ArrayList<ArrayList<Integer>> graph;
	static HashMap<Integer,Integer> hm;
	static int add(int x)
	{
		if(hm.containsKey(x)) return hm.get(x);
		int v = hm.size();
		hm.put(x, v);
		return v;
	}
	static int S,E,L;
	private static class Node implements Comparable<Node>
	{
		int at, cost;
		Node(int a, int c)
		{
			at = a;
			cost = c;
		}
		public int compareTo(Node o) {
			return -Integer.valueOf(cost).compareTo(o.cost);
		}
	}
}
