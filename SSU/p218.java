package SSU;
import java.io.*;
import java.util.*;

import static java.lang.Math.*;

/* Saratov State University Online Judge
 * Problem 218: Unstable Systems
 * Type: Max flow
 * Solution: Binary search on the answer. Use max flow to bipartite match between computers and jobs only add edges that are <= your guess.
 */

public class p218 {
	public static void main(String[] args) throws NumberFormatException, IOException
	{
//		long stt = System.currentTimeMillis();
		BufferedReader R = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(R.readLine().trim());
//		Random rand = new Random(23424L);
		adj = new int[N][N];
		int low = Integer.MAX_VALUE;
		int high = Integer.MIN_VALUE;
		for(int i = 0; i < N;i++)
		{
			StringTokenizer st = new StringTokenizer(R.readLine().trim());
			for(int j = 0; j < N;j++)
			{
				//rand.nextInt(1000000);//
				adj[i][j] = Integer.parseInt(st.nextToken());
				low = min(low,adj[i][j]);
				high = max(high,adj[i][j]);
			}
		}

		int best = 1000000;
		ArrayList<String> bestAns = null;
		while(low <= high)
		{
			int mid = (low+high)/2;
			ArrayList<String> ans = pos(mid);
			if(ans != null)
			{
				best = mid;
				high = mid-1;
				bestAns = ans;
			}else{
				low = mid+1;
			}
		}
		StringBuilder out = new StringBuilder("");
		out.append(best+"\n");
		for(String s:bestAns)
			out.append(s+"\n");
		System.out.print(out);
//		long en = System.currentTimeMillis();
//		System.out.println(en-stt);
	}
	private static ArrayList<String> pos(int max) {
		A = new int[N*2+2][N*2+2];
		int st = A.length-2;
		int en = A.length-1;
		for(int i = 0; i < N;i++)
		{
			A[st][i] = 1;
			A[i+N][en] = 1;
			for(int j = 0; j < N;j++)
			{
				if(adj[i][j] <= max)
				{
					A[i][j+N] = 1;
				}
			}
		}
		int maxf = 0;
		for(int i = 0; i < N;i++)
		{
			for(int j = N; j < 2*N;j++)
			{
				if(A[i][j] > 0 && A[j][en] > 0)
				{
					maxf++;
					A[i][j] = 0;
					A[j][i] = 1;
					A[st][i] = 0;
					A[i][st] = 1;
					A[j][en] = 0;
					A[en][j] = 1;
					break;
				}
			}
		}
		
		while(maxf < N)
		{
			V = new boolean[A.length];
			int flow = dfs(st,en,1);
			if(flow == 0)
				break;
			maxf += flow;
		}
		if(maxf != N)
			return null;

		ArrayList<String> ans = new ArrayList<String>();
		for(int i = 0; i < N;i++)
		{
			for(int j = 0; j < N;j++)
			{
				if(A[j+N][i] == 1)
				{
					ans.add((i+1)+" "+(j+1));
				}
			}
		}

		return ans;
	}
	static boolean[] V;
	static int[][] A;
	private static int dfs(int at, int en, int flow) {
		if(at == en)
			return flow;
		if(V[at])
			return 0;
		V[at] = true;
		if(at < N)
		{
			for(int i = N; i < A.length;i++)
			{
				if(A[at][i] > 0)
				{
					int f = dfs(i,en,min(flow,A[at][i]));
					if(f > 0)
					{
						A[at][i] -= f;
						A[i][at] += f;					
						return f;
					}
				}
			}
		}else
		{
			for(int i = 0; i < A.length;i++)
			{
				if(A[at][i] > 0)
				{
					int f = dfs(i,en,min(flow,A[at][i]));
					if(f > 0)
					{
						A[at][i] -= f;
						A[i][at] += f;					
						return f;
					}
				}
			}
		}
		return 0;
	}
	static int N;
	static int[][] adj;
}
