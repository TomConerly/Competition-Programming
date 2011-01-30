package TopCoder.Hard;
import java.util.*;
import static java.lang.Math.*;

/* TopCoder SRM 404 Div 2
 * Hard Problem 1000 Points: GetToTheTop
 * Type: Computational Geometry/DP
 * Solution: DP over which square you just jumped to. Precompute from each square how much you
 * can collect on that level, and which ones above you you can jump to from anything you can
 * reach at this level. 
 */

public class GetToTheTop {

	int[] X,Y,S,L;
	int[] dp;
	int N,K;
	int[] AS;
	public int collectSweets(int k, int[] sweets, int[] x, int[] y, int[] stairLength) {
		K = k;
		X = x;
		Y = y;
		S = sweets;
		L = stairLength;
		N = S.length;
		dp = new int[N];
		Arrays.fill(dp,-1);
		
		AS = new int[N];
		graph = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < N;i++)
		{
			graph.add(new ArrayList<Integer>());
			ArrayList<Integer> at = atLevel(i);
			for(int atLevel:at)
				AS[i] += S[atLevel];
			for(int j = 0; j < N;j++)
			{
				if(Y[j] <= Y[i]) continue;
				for(int atLevel:at)
				{
					if(reach(atLevel,j))
					{
						graph.get(i).add(j);
						break;
					}
				}
			}
		}
		
		int best = 0;
		for(int i = 0; i < N;i++)
		{
			if(y[i] <= K)
				best = max(best,recur(i));
		}
		return best;
	}
	private boolean reach(int a,int b) {
		if(X[a]+L[a] >= X[b] && X[b]+L[b] >= X[a]) return Y[b]-Y[a] <= K;
		double dist = sqrt(pow(min(X[a]+L[a]-X[b],X[b]+L[b] - X[a]),2) + pow(Y[b]-Y[a],2));
		return dist <= K;
	}
	
	ArrayList<ArrayList<Integer>> graph;
	private int recur(int i) {
		if(dp[i] != -1) return dp[i];
		int score =AS[i];
		int best = 0;
		for(int e:graph.get(i))
		{
			best = max(best,recur(e));
		}
		dp[i] = score+best;
		return dp[i];		
	}
	private ArrayList<Integer> atLevel(int i) 
	{		
		ArrayList<Integer> ans = new ArrayList<Integer>();
		boolean[] reach = new boolean[N];
		reach[i] = true;		
		ans.add(i);		
		while(true)
		{
			boolean update = false;
			for(int j = 0; j < N;j++)
			{
				if(!reach[j] || Y[j] != Y[i]) continue;
				for(int k = 0; k < N;k++)
				{
					if(reach[k]  || Y[k] != Y[i]) continue;					
					int dist = min(abs(X[j]+L[j]-X[k]),abs(X[k]+L[k]-X[j]));
					if(dist <= K)
					{
						reach[k] = true;
						update = true;
						ans.add(k);
					}
				}
			}
			if(!update) break;
		}		
		
		return ans;
	}	
}
