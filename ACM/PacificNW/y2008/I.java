package ACM.PacificNW.y2008;
import java.util.*;
import static java.lang.Math.*;

/* ACM Pacific Northwest 2008
 * Problem I: Crazy Circuits
 * Type: Max Flow
 * Solution: Constrained flow problem. First turn the graph given into a circulation (connect end to start).
 * Now make a new sink and source. For each edge with a min flow (the component edges) add an edge from the 
 * sink to the end of the edge, and from the start of the edge to the source both of capacity l (the lower bound).
 * Now if the max flow equals l then the constraint was satisfied. Do this for all edges with constraints and then
 * check if the total max flow is the sum.
 * 
 * Now to minimize the flow. Along the edge from the old end to old start we want to minimize the flow. We could do this
 * with min cost, but that is uglier to code. Instead binary search on the min value of this edge that allows the max flow.
 * Any value greater than the min must allow a flow, and all less must not so we have the property needed for binary search.
 */

public class I {
	public static void main(String[] args)
	{
		long stt = System.currentTimeMillis();
		Scanner sc = new Scanner(System.in);
		int zz = 0;
		while(true)
		{
			J = sc.nextInt();
			M = sc.nextInt();
			if(J == 0 && M == 0)
				break;
			
			P = new int[M];
			N = new int[M];
			C = new int[M];
			int plus = J;
			int neg = J+1;
			for(int i = 0; i < M;i++)
			{
				String s = sc.next();
				if(s.equals("+"))
					P[i] = plus;
				else
					P[i] = Integer.valueOf(s)-1;
				
				s = sc.next();
				if(s.equals("-"))
					N[i] = neg;
				else
					N[i] = Integer.valueOf(s)-1;
				
				C[i] = sc.nextInt();
			}
			
			//one for each junction, 2 for each component, source,sink
			int[][] adj = new int[(J+2)+2*M+2][(J+2)+2*M+2];
			int st = adj.length-2;
			int en = adj.length-1;
			for(int i = 0; i < M;i++)
			{
				adj[P[i]][i+J+2] = BIG;
				adj[i+M+J+2][N[i]] = BIG;
				adj[i+J+2][i+M+J+2] = BIG;
			}
			int should = 0;
			for(int i = 0; i < M;i++)
			{
				should+=C[i];
				adj[st][i+M+J+2] = C[i];
				adj[i+J+2][en] = C[i];
			}
			int ans = Integer.MAX_VALUE;
			int low = 0;
			int high = M*100;
			while(true)
			{
				int i = (low+high)/2;
				adj[neg][plus] = i;
				int[][] temp = new int[adj.length][adj.length];
				for(int j = 0; j < adj.length;j++)
					for(int k = 0; k < adj.length;k++)
						temp[j][k] = adj[j][k];
				int maxf = maxf(st,en,temp);
				if(should == maxf)
				{
					ans = min(ans,i);					
					high = i-1;
				}else{
					low = i+1;
				}
				if(low > high)
					break;
			}
			if(ans == Integer.MAX_VALUE)
				System.out.println("impossible");
			else
				System.out.println(ans);
			
			zz++;
			System.err.println(zz);
		}
		long ent = System.currentTimeMillis();
		System.err.println(ent-stt);
	}
	static int BIG = 1000000;
	static int[] P,N,C;
	static int J,M;
	
	
	static int start;
	static int end;
	static int[][] adj;
	public static int maxf(int start,int end, int[][] adj)
	{
		I.start = start;
		I.end = end;
		I.adj = adj;
		int maxflow = 0;
		while(true)
		{
			int[] p = dfs();
			if(p==null) break;
			maxflow += update(Integer.MAX_VALUE,end,p);
		}
		return maxflow;
	}
	public static int update(int f, int at,int[] p)
	{
		if(at == start) return f;
		f = Math.min(f,adj[p[at]][at]);
		f = update(f,p[at],p);
		adj[p[at]][at] -= f;
		adj[at][p[at]] += f;
		return f;
	}
	public static int[] dfs()
	{
		LinkedList<Integer> paths = new LinkedList<Integer>();
		int[] p = new int[adj.length];
		Arrays.fill(p,-1);
		paths.add(start);
		
		while(paths.size() > 0)
		{
			int at = paths.removeFirst();
			for(int i = 0; i < p.length;i++)
			{
				if(p[i] == -1 && adj[at][i] > 0)
				{
					p[i] = at;
					if(i == end)
						return p;
					paths.addFirst(i);
				}
			}
		}
		return null;
	}
}
