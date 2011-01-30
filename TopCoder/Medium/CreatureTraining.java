package TopCoder.Medium;import java.util.*;import static java.lang.Math.*;/* TopCoder 2008 TCO Round 2 * Medium Problem 500 Points: CreatureTraining * Type: DP * Solution: DP is over which creature we are at, how many extra of that creature we have * and how many moves we have left.  */public class CreatureTraining {

	public long maximumPower(int[] count, int[] power, int d) {
		c = count;		p = power;		D = d;		dp = new long[c.length][D+1][D+1];		for(int i = 0; i < dp.length;i++)		{			for(int j = 0; j < dp[0].length;j++)				Arrays.fill(dp[i][j],-1);		}		return recur(0,0,D);
	}	int[] c,p;	int D;	long dp[][][];
	public long recur(int at, int extra, int movesLeft)	{		if(at == c.length) return 0;		if(dp[at][extra][movesLeft] != -1) return dp[at][extra][movesLeft];		long best = 0;		for(int i = 0; i <= movesLeft;i++)		{			if(c[at]+extra < i) continue;			if(i > 0 && at == c.length-1) continue;			best = max(best,recur(at+1,i,movesLeft-i)+p[at]*((long)c[at]+extra-i));		}		dp[at][extra][movesLeft] = best;		return best;			}
}
