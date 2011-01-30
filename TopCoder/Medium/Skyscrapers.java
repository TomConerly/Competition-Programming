package TopCoder.Medium;
import java.util.*;
/* TopCoder SRM 395
 * Medium Problem 395 Points: Skyscrapers
 * Type: DP
 * Solution: Fairly standard dp, look at recur function for 
 * recusive definition. 
 */

public class Skyscrapers {

	long mod = 1000000007;
	public int buildingCount(int N, int leftSide, int rightSide) {
		dp = new long[N+1][N+1][N+1];
		for(int i = 0; i < dp.length;i++)
		{
			for(int j = 0; j < dp[0].length;j++)
			{
				Arrays.fill(dp[i][j],-1);
			}
		}
		
		return (int)m(recur(N,leftSide,rightSide));
	}
	private long m(long l) {
		return l % mod;
	}
	long[][][] dp;
	public long recur(int at, int left, int right)
	{
		if(left < 0 || right < 0) return 0;
		if(dp[at][left][right] != -1) return dp[at][left][right];
		if(at == 1 && left == 1 && right == 1) return 1;
		if(at == 1) return 0;
		long ans = 0;
		ans = m(recur(at-1,left,right)*(at-2)+ans);
		ans = m(recur(at-1,left-1,right)+ans);
		ans = m(recur(at-1,left,right-1)+ans);
		
		dp[at][left][right] = ans;
		return ans;
	}

}
