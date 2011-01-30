package SPOJ;
import java.util.*;
import static java.lang.Math.*;

/* SPOJ Problem 1027: FPOLICE
 * Type: DP
 * Solution: DP over where you are at, and how much time you have left.
 */

public class FPOLICE {
	public static void p(Object o){System.out.println(o);}
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int zz = sc.nextInt();
		for(int z = 0; z < zz;z++)
		{
			N = sc.nextInt();
			T = sc.nextInt();
			time = new int[N][N];
			for(int i = 0; i < N;i++)
				for(int j = 0; j < N;j++)
					time[i][j] = sc.nextInt();
			risk = new int[N][N];
			for(int i = 0; i < N;i++)
				for(int j = 0; j < N;j++)
					risk[i][j] = sc.nextInt();
			
			dp = new int[N][T+1];
			for(int[] a:dp)
				Arrays.fill(a,-1);
			
			int cost = recur(0,T);
			if(cost == Integer.MAX_VALUE)
			{
				System.out.println("-1");
				continue;
			}
			int ansT = T;
			while(true)
			{
				if(recur(0,ansT-1) == cost) ansT--;
				else break;
				if(ansT == 0) break;
			}
			p(cost+" "+ansT);
		}
	}
	static int N,T;
	static int[][] time,risk,dp;
	public static int recur(int at, int t)
	{
		if(t < 0) return Integer.MAX_VALUE;
		if(at == N-1) return 0;
		if(dp[at][t] != -1) return dp[at][t];
		int bestc = Integer.MAX_VALUE;
		for(int i = 0; i < N;i++)
		{
			if(i==at) continue;
			int c = recur(i,t-time[at][i]);
			if(c == Integer.MAX_VALUE) continue;
			bestc = min(bestc,c+risk[at][i]);
		}
		dp[at][t] = bestc;
		return bestc;
	}
}
