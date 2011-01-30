package GCJ.y2009.Round2;
import java.util.*;

/* Google Code Jam 2009 Round 2
 * Problem C: Stock Charts
 * Type: Max Flow
 * Solution: Make the partial sorting of them. This creates a DAG, find the smallest number
 * of paths to cover the DAG using max flow. (see contest analysis for better description).
 */


public class C 
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int zz = 1; zz <= T;zz++)
		{
			System.out.print("Case #"+zz+": ");
			int N = sc.nextInt();
			int K = sc.nextInt();
			int[][] board = new int[N][K];
			for(int i = 0; i < N;i++)
			{
				for(int j = 0; j < K;j++)
				{
					board[i][j] = sc.nextInt();
				}
			}
			int[][] adj = new int[2*N+2][2*N+2];
			int st = 2*N;
			int en = 2*N+1;
			for(int i = 0; i < N;i++)
			{
				for(int j = 0; j < N;j++)
				{
					if(above(board[i],board[j]))
					{
						//System.out.println(i+" "+j);
						adj[i][j+N] = 1;
					}
				}
			}
			for(int i = 0; i < N;i++)
			{
				adj[st][i] = 1;
				adj[i+N][en] = 1;
			}
			//for(int i = 0; i < adj.length;i++)
			//	System.out.println(Arrays.toString(adj[i]));
			int maxf = maxf(st,en,adj);
			System.out.println(N-maxf);
		}
	}

	private static boolean above(int[] a, int[] b) {
		for(int i = 0; i < a.length;i++)
		{
			if(b[i] >= a[i])
				return false;
		}
		return true;
	}

	
	static int start;
	static int end;
	static int[][] adj;
	public static int maxf(int start,int end, int[][] adj)
	{
		C.start = start;
		C.end = end;
		C.adj = adj;
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
