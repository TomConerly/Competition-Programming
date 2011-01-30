package TopCoder.Medium;
/* TopCoder SRM 451
 * Medium Problem 500 Points: BaronsAndCoins
 * Type: DP
 * Solution: DP[coin at][previous k_i] notice that the previous k_i can't be larger than 10k so the DP is 10k*50 states. From a coin we go to the 
 * next coin and want to end with the lowest k_i when we get there. (lower k_i dominates higher because you have less restrictions). Compute that minimum
 * using a simple greedy.
 */
import java.util.*;
import static java.lang.Math.*;

public class BaronsAndCoins {
	public void p(Object s){System.out.println(s);}
	public int getMaximum(int[] xx, int[] yy) {
		x = new int[xx.length+1];
		for(int i = 0; i < xx.length;i++)
			x[i+1] = xx[i];
		y = new int[yy.length+1];
		for(int i = 0; i < yy.length;i++)
			y[i+1] =yy[i];
		
		dp = new int[x.length][10001];
		for(int i = 0; i < dp.length;i++)
			Arrays.fill(dp[i],-1);
		return recur(0,0);
	}
	int[][] dp;
	int[] x,y;
	public int recur(int at, int prev)
	{
		if(dp[at][prev] != -1)
			return dp[at][prev];
		int ans = 0;
		for(int i = 0; i  < x.length;i++)
		{
			if(y[i] <= y[at] || x[i] <= x[at])
				continue;
			int next = minnext(x[i]-x[at],y[i]-y[at],prev);
			if(next == Integer.MAX_VALUE)
				continue;
			ans = max(ans,1+recur(i,next));
		}
		dp[at][prev] = ans;
		return ans;
	}
	public int minnext(int dx, int dy, int prev)
	{
		int low = 0;
		for(int i = 0; i < dy;i++)
			low += prev+i+1;
		if(low > dx)
			return Integer.MAX_VALUE;

		low = prev+dy;
		int high = 10000;
		int ans = Integer.MAX_VALUE;
		int min = 0;
		for(int i = 0; i < dy-1;i++)
			min += prev + i + 1;
		while(low <= high)
		{
			int mid = (low+high)/2;

			int max = 0;
			for(int i = 0; i < dy-1;i++)
			{
				max += mid-i-1;
			}
			int need = dx-mid;
			if(min <= need && need <= max)
			{
				ans = min(ans,mid);
				high = mid-1;
			}
			else if( need < min)
			{
				high = mid-1;
			}else
			{
				low = mid+1;
			}

		}
		return ans;
	}
}
