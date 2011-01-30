package ACM.Yokohama.y2006;
import java.util.*;

/* ACM Asia Regional Yokohama 2006
 * Problem D: Sum of Different Primes
 * Type: DP
 * Solution: Straight forward DP.
 */

public class D
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		primes = new ArrayList<Integer>();
		boolean[] isp = new boolean[1500];
		isp[0] = false;
		isp[1] = false;
		Arrays.fill(isp,true);
		for(int i = 2; i < isp.length;i++)
		{
			if(!isp[i])
				continue;
			primes.add(i);
			for(int j = 2; i*j < isp.length;j++)
			{
				isp[i*j] = false;
			}
		}
		while(true)
		{
			int n = sc.nextInt();
			int k = sc.nextInt();
			if(n == 0 && k == 0)
				break;
			dp = new int[n+1][k+1][primes.size()+1];
			for(int i = 0; i < dp.length;i++)
				for(int j = 0; j < dp[0].length;j++)
					Arrays.fill(dp[i][j],-1);
			System.out.println(dp(n,k,0));
		}
	}
	static int[][][] dp;
	static public int dp(int n, int k, int lastp)
	{
		if(n == 0 && k == 0)
			return 1;
		if(n < 0 || k < 0)
			return 0;
		if(dp[n][k][lastp] != -1)
			return dp[n][k][lastp];
		int ans = 0;
		for(int i = lastp; i < primes.size();i++)
		{
			if(primes.get(i) > n)
				break;
			ans += dp(n-primes.get(i),k-1,i+1);
		}
		dp[n][k][lastp] = ans;
		return ans;
	}
	static ArrayList<Integer> primes;
}
