package ACM.CTUOpen.y2009;
import java.util.*;

/* CTU Open 2009
 * Problem O: Odd Opportunities
 * Type: Graph Theory
 * Solution: Seperate into connected components. If odd # of odd verticies we failed. Now arbitrarily pair them, and find a path between all pairs.
 * Assuming no overlap this would be a solution. If there is an edge used an even # of times, simply remove it because there are odd verticies
 * on both end points that can be mapped to each other instead of using the edge. If it is used an odd # of times all but one use will be removed.
 * 
 * This solution uses slow IO so it times out, but that is because of Scanner not the algorithm. Also the problem needs a program to check you can't diff
 * output.
 */

public class O {
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		nextCase:
			while(true)
			{
				V = sc.nextInt();
				E = sc.nextInt();
				if(V==0 && E == 0)
					break;
				adj = new ArrayList[V];
				for(int i = 0; i < V;i++)
					adj[i] = new ArrayList<Edge>();
				to = new int[E];
				from = new int[E];
				for(int i = 0; i < E;i++)
				{
					int a = sc.nextInt()-1;
					int b = sc.nextInt()-1;
					to[i] = a;
					from[i] = b;
					adj[a].add(new Edge(a,b,i));
					adj[b].add(new Edge(b,a,i));
				}
				odd = new boolean[V];
				String s = sc.next();
				int c = 0;
				for(int i = 0; i < V;i++)
				{
					odd[i] = s.charAt(i)=='o';
					if(odd[i])
						c++;
				}
				if(c%2==1)
				{
					System.out.println("impossible");
					continue;
				}
				count = new int[E];
				U = new boolean[V];
				for(int i = 0; i < V;i++)
				{
					if(odd[i] && !U[i])
					{
						//System.out.println(i+" "+Arrays.toString(U));
						U[i] = true;
						found = false;
						vis = new boolean[V];
						dfs(i,new int[V],0);
						if(!found)
						{
							
							System.out.println("impossible");
							continue nextCase;
						}
					}
				}
				int ans = 0;
				for(int i = 0; i < E;i++)
				{
					if(count[i] %2 == 1)
						ans++;
				}
				System.out.println(ans);
				for(int i = 0; i < E;i++)
				{
					if(count[i] %2 == 1)
						System.out.println((to[i]+1)+" "+(from[i]+1));
				}
				for(int i = 0; i < V;i++)
				{
					int cc = 0;
					for(Edge e: adj[i])
					{
						if(count[e.id]%2==1)
							cc++;
					}
					if((cc %2 == 0 && odd[i]) || (cc%2 == 1 && !odd[i]))
					{
						System.out.println("ERROR!");
						System.exit(0);
					}
				}
			}
	}
	static int[] to,from;
	static boolean found;
	private static void dfs(int at, int[] e, int eid) {
		if(found)
			return;
		if(vis[at])
			return;
		vis[at] = true;
		if(odd[at] && U[at] == false)
		{
			U[at] = true;
			for(int i = 0; i < eid;i++)
				count[e[i]]++;
			found = true;
			return;
		}
		for(Edge ed: adj[at])
		{
			e[eid] = ed.id;
			dfs(ed.to,e,eid+1);
		}

	}
	static boolean[] vis;
	static boolean[] U;
	static int[] count;
	static boolean[] odd;
	static ArrayList<Edge>[] adj;
	static int V,E;

	private static class Edge
	{
		int to,id;
		Edge(int a, int b, int c)
		{
			to = b;
			id = c;
		}
	}
}
