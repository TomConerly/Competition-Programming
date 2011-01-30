package TopCoder.Hard;
import java.util.*;
import static java.lang.Math.*;

/* TopCoder Member Pilot 2
 * Hard Problem 900 Points: PrefixFreeCode
 * Type: DP
 * Solution: Imagine making it as a tree. dp[at][numLeft][good]. At is which child you are on (index into cost)
 * numLeft is the number of nodes to make. And good is whether or not you have used any nodes so far. FOr the current
 * child you can put some elements there, recurse on that subtree and the rest of this tree. Use good to enforce that you cannot
 * put them all into the same tree otherwise you get cycles.
 */

public class PrefixFreeCode {
	public void p(Object s){System.out.println(s);}
	public int minCost(int a, int[] c) {
		N = a;
		cost = c;
		dp = new long[c.length][N+1][2];
		for(int i = 0; i < dp.length;i++)
			for(int j = 0; j < dp[0].length;j++)
				Arrays.fill(dp[i][j],-1);
		return (int)recur(0,a,0);
	}
	int N;
	int[] cost;
	long[][][] dp;
	public long recur(int at, int numLeft, int good)
	{
		if(at == 0 && numLeft == 1)
			return 0;
		if(numLeft == 0)
		{
			return 0;
		}
		if(at == cost.length)
		{
			if(numLeft == 0) {
				return 0;
			}
			else return Integer.MAX_VALUE;
		}
		if(dp[at][numLeft][good] != -1)
			return dp[at][numLeft][good];
		long best = Integer.MAX_VALUE;
		for(int i = 0; i <= numLeft;i++)
		{
			if(i == numLeft && good == 0)
				continue;
			
			best = min(best,cost[at]*i+recur(0,i,0)+recur(at+1,numLeft-i,(i > 0 || good > 0? 1:0)));
		}
		dp[at][numLeft][good] = best;
		return best;
	}

}
