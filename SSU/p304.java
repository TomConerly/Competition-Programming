package SSU;
import java.util.*;
import static java.lang.Math.*;

/* Saratov State University Online Judge
 * Problem 304: Mars Stomatology
 * Type: DP
 * Solution: DP[teeth left][toothat][paid for current gum] (sort teeth by which gum they are on) = the minimum cost to clean teethleft teeth
 * given that we can only use teeth after tooth at, and whether or not we have paid for the current gum. 
 */

public class p304 {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		P = sc.nextInt();
		T = new Tooth[N];
		G = new int[K];
		for(int i = 0; i < K;i++)
			G[i] = sc.nextInt();
		for(int i = 0; i < N;i++)
		{
			T[i] = new Tooth(i+1,sc.nextInt(),sc.nextInt()-1);
		}
		Arrays.sort(T);
		dp = new int[N+1][N+1][2];
		for(int i = 0; i < dp.length;i++)
			for(int j = 0; j < dp[0].length;j++)
				Arrays.fill(dp[i][j],-1);
		
		use = new boolean[N+1][N+1][2];
		int ans = 0;
		ArrayList<Integer> ansA = new ArrayList<Integer>();
		for(int i = 0; i <= N;i++)
		{
			if(recur(i,0,0) <= P)
			{
				ans = i;
				int nl = i;
				int at = 0;
				int d = 0;
			//	System.out.println("found: "+i);

				ansA = new ArrayList<Integer>();
				while(at < N)
				{
				//	System.out.println(nl+" "+at+" "+d);
					if(use[nl][at][d])
					{
						ansA.add(T[at].idx);
						d = 1;
						nl--;
					}
					at++;
					if(at < N)
					{
						if(T[at].gum != T[at-1].gum)
							d = 0;
					}
				}
			}
		}
		System.out.println(ans);
		for(int i:ansA)
			System.out.print(i+" ");
		System.out.println();
	}
	static int M = 10000000;
	private static int recur(int nl, int at, int paid) {
		if(at >= N)
		{
			if(nl == 0)
				return 0;
			else
				return M;
		}
		if(dp[nl][at][paid] != -1)
			return dp[nl][at][paid];
		
		int o1 = recur(nl,at+1,(at+1 < N && paid == 1 && T[at].gum == T[at+1].gum) ? 1:0);
		int o2 = Integer.MAX_VALUE;
		//System.out.println(at+" "+N);
		if(nl > 0)
			o2 = T[at].c + (paid == 1? 0:G[T[at].gum]) + recur(nl-1,at+1,(at+1 < N && T[at].gum == T[at+1].gum) ? 1:0);
		
		if(o1 < o2)
			use[nl][at][paid] = false;
		else
			use[nl][at][paid] = true;
		dp[nl][at][paid] = min(o1,o2);
		return min(o1,o2);
	}
	static boolean[][][] use;
	static int[][][] dp;
	static Tooth[] T;
	static int N,K,P;
	static int[] G;
	
	private static class Tooth implements Comparable<Tooth>
	{
		int idx, gum,c;
		Tooth(int id, int cost, int g)
		{
			c = cost;
			idx = id;
			gum = g;
		}
		
		public int compareTo(Tooth t) {
			return Integer.valueOf(gum).compareTo(t.gum);
		}
	}
}
