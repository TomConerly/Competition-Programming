package TopCoder.Hard;
import java.util.*;

/* TopCoder SRM 442
 * Hard Problem 950 Points: NowhereLand
 * Type: Max Flow
 * Solution: You can seperate each agency and solve seperately. You want to find the min cut between cities with a gaurd already
 * and the cities w/o an agency. Use maxflow mincut.
 */

public class NowhereLand {
	public void p(Object s){System.out.println(s);}
	@SuppressWarnings("unchecked")
	public int placeGuards(String[] cities, int k, String[] guards, String[] agencies) {
		N = cities.length;
		K = k;
		G = new TreeSet[N];
		A = new TreeSet[N];
		for(int i = 0; i < N;i++)
		{
			G[i] = new TreeSet<Integer>();
			for(String s: guards[i].split(" "))
			{
				if(s.equals(""))
					continue;
				G[i].add(Integer.valueOf(s));
			}
			A[i] = new TreeSet<Integer>();
			for(String s: agencies[i].split(" "))
			{
				if(s.equals(""))
					continue;
				A[i].add(Integer.valueOf(s));
			}
		}
		int ans = 0;

		for(int i = 0; i < K;i++)
		{
			int[][] gr = new int[N+2][N+2];
			int st = N;
			int en = N+1;
			for(int j = 0; j < N;j++)
			{
				if(G[j].contains(i))
					gr[st][j] = M;
				if(!A[j].contains(i))
					gr[j][en] = M;
			}
			for(int j = 0; j < N;j++)
			{
				for(int m = j+1;m<N;m++)
				{
					if(cities[j].charAt(m) == '1')
					{
						gr[j][m] = 1;
						gr[m][j] = 1;
					}
				}
			}
			ans += maxf(st,en,gr);
		}

		return ans;
	}

	int M = 1000000;
	int N,K;
	TreeSet<Integer>[] G,A;


	public int maxf(int start, int end, int[][] adj){
		int flow = 0;
		while(true){
			int newFlow = dfs(start,end,Integer.MAX_VALUE,adj,new boolean[adj.length]);
			flow += newFlow;
			if(newFlow == 0) break;
		}
		return flow;		
	}

	public int dfs(int start, int end,int by, int[][] adj,boolean[] used){
		if(start == end) return by;
		used[start] = true;

		for(int i = 0; i < adj.length;i++){

			if(adj[start][i] > 0 && !used[i]){

				int newFlow = dfs(i,end,Math.min(by,adj[start][i]),adj,used);
				if(newFlow > 0){
					adj[start][i] -= newFlow;
					adj[i][start] += newFlow;
					return newFlow;
				}
			}
		}
		return 0;
	}
}
