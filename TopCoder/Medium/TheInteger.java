package TopCoder.Medium;
import java.util.*;
import static java.lang.Math.*;

/* TopCoder SRM 437
 * Medium Problem 500 Points: TheInteger
 * Type: DP
 * Solution: DP[digits used][new digits left][greater][digit at]
 * Keep track of the digits you have used, # of digits left to use,
 * whether you are greater than the given number, and which digit you are at
 * return the smallest integer larger than than the given one or -1 if it doesn't exist.
 */

public class TheInteger {

	int M;
	int[][][][] dp;
	public long find(long n, int k) {
		long t = n;
		while(t > 0){t/=10;M++;}
		t = n;
		int at = M-1;
		num = new int[M];
		while(t > 0)
		{
			
			num[at--] = (int)(t%10);
			t/=10;
		}
		
		dp = new int[1<<10][k+1][2][M];
		for(int i = 0; i < dp.length;i++)
			for(int j = 0; j < dp[0].length;j++)
				for(int m = 0; m < dp[0][0].length;m++)
					Arrays.fill(dp[i][j][m],-1);
		
		long ans = small(0,k,0,0);
		if(ans != -1) return ans;
		
		M = max(M+1,k);
		ans = 0;
		if(k == 1){
			for(int i = 0; i < M;i++)
				ans = ans*10+1;
			return ans;
		}		
		ans = 1;
		for(int i = 0; i < M;i++)
		{			
			if(k-2 == M-i-1) break;
			ans = ans*10+0;
			
		}
		for(int j = 2;j<k;j++)
		{
			ans = ans*10+j;
		}
		
		return ans;
	}
	int[] num;
	long small(int sub, int newLeft, int greater, int at)
	{
		if(at == M)
		{
			return 0;
		}	
		
		for(int i = 0; i <= 9;i++)
		{
			int ne = ((sub & (1<<i)) == 0) ? 1:0;
			if(i < num[at] && greater == 0) continue;
			
			int g = greater;
			if(i > num[at]) g = 1;
			if(newLeft-ne < 0) continue;
			if(recur(sub | (1<<i), newLeft-ne, g,at+1) == 1)
			{
				long n = small(sub | (1<<i), newLeft-ne, g,at+1);
				return n + (long)Math.pow(10,M-at-1)*i;
			}
		}
		return -1;
	}
	int recur(int sub, int newLeft, int greater, int at)
	{
		if(at == M)
		{
			if(newLeft == 0) return 1;
			else return 0;
		}
		if(dp[sub][newLeft][greater][at] != -1) return dp[sub][newLeft][greater][at];
		
		int ans = 0;
		for(int i = 0; i <= 9;i++)
		{
			int ne = ((sub & (1<<i)) == 0) ? 1:0;
			if(i < num[at] && greater == 0) continue;
			
			int g = greater;
			if(i > num[at]) g = 1;
			if(newLeft-ne < 0) continue;
			if(recur(sub | (1<<i), newLeft-ne, g,at+1) == 1)
				ans = 1;
		}
		dp[sub][newLeft][greater][at] = ans;
		return ans;		
	}
	public void p(Object o){System.out.println(o);}
}
