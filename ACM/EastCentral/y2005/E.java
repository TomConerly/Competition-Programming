package ACM.EastCentral.y2005;
import java.util.*;

/* ACM ICPC 2005 East Central Regional
 * Problem E: Reliable Nets
 * Type: Graph Theory
 */
public class E
{
	static int n,m;
	static ArrayList<ArrayList<Integer>> adjList;
	static int[] eCost;
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		for(int z=1;;z++)
		{
			n = s.nextInt();
			m = s.nextInt();
			if(n==0&&m==0) break;
			adjList = new ArrayList<ArrayList<Integer>>();
			eCost = new int[m];
			gCost = new int[1<<m];
			gConnect = new boolean[1<<m];
			for(int i = 0; i < n;i++)adjList.add(new ArrayList<Integer>());
			for(int i = 0;i<m;i++)
			{
				int a = s.nextInt()-1;
				int b = s.nextInt()-1;
				int c = s.nextInt();
				eCost[i]=c;
				adjList.get(a).add((i<<16)|b);
				adjList.get(b).add((i<<16)|a);
			}
			allGraph(0,0,0);
			int best = Integer.MAX_VALUE;
			for(int i = 0; i < (1<<m);i++)
			{
				int cost = gCost[i];
				if(cost >= best) continue;
				boolean rel = true;
				for(int j = 0; j < m;j++)
				{
					rel &= gConnect[i&(~(1<<j))];	
				}
				if(rel) best = cost;
			}
			if(best == Integer.MAX_VALUE)
				System.out.println("There is no reliable net possible for test case "+z+".");
			else
				System.out.println("The minimal cost for test case "+z+" is "+best+".");
		}
	}
	static int[] gCost;
	static boolean[] gConnect;
	public static void allGraph(int at, int cost, int graph)
	{
		if(at==m)
		{
			edges = graph;
			visit = 0;visited=0;

			recur(0);
			gConnect[graph] = (visit==n);
			gCost[graph]=cost;
		}
		else
		{
			allGraph(at+1,cost,graph);
			allGraph(at+1,cost+eCost[at],graph|(1<<at));
		}
	}
	static int visit,visited,edges;
	public static void recur(int at)
	{
		if((visited & (1<<at)) == 0)
		{
			visited |= 1<<at;
			visit++;
			for(int e: adjList.get(at))
			{
				if(((1<<(e>>>16)) & edges) == 0) continue;
				recur(e & 0xFFFF);
			}
		}
	}
}
