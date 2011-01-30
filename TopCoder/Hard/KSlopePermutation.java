package TopCoder.Hard;
import java.util.*;

/* TopCoder TCCC07 ROund 1A 
 * Hard Problem 1000 Points: KSlopePermutation
 * Type: DP
 * Solution: Do DP over inverted permutations. We know that the fth element has
 * to be 0. dp[i][j][k] means with the number of ways to fill the last i things
 * given that the first i had k slope, and the last element placed has j items
 * that can be placed with lower value.
 */

public class KSlopePermutation {

	public long getCount(int n, int k, int f) {
		N = n;
		K = k;
		F = f;

		dp = new long[N+10][N+10][N+10];
		for(int i= 0; i < dp.length;i++)
			for(int j= 0; j < dp[i].length;j++)
				Arrays.fill(dp[i][j],-1);
		
		return recur(0,1,0);
	}
	long[][][] dp;
	int N,K,F;
	long recur(int at, int below, int k)
	{
		if(at == N) return k==K?1:0;
		
		if(dp[at][below][k] != -1) return dp[at][below][k];

		if(at == F)
		{
			dp[at][below][k] = recur(at+1,0,k+(at == 0?0:1));
			return dp[at][below][k];
		}
		long ans = 0;
		for(int i = (at > F?0:1); i < below;i++)
		{
			ans += recur(at+1,i,k+1);
		}
		for(int i = below; i < N-at;i++)
		{
			ans += recur(at+1,i,k);
		}
		dp[at][below][k] = ans;
		//System.out.println(at+" "+below+" "+k+" "+ans);
		return ans;
	}
}
