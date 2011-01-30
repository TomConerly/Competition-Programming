package SSU;
import java.util.*;
import static java.lang.Math.*;

/* Saratov State University Online Judge
 * Problem 104: Little shop of flowers
 * Type: DP
 * Solution: Standard dp.
 */

public class p104 {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		F = sc.nextInt();
		V = sc.nextInt();
		P = new int[F][V];
		for(int i = 0; i < F;i++)
			for(int j = 0; j < V;j++)
				P[i][j] = sc.nextInt();
		
		dp = new long[F][V];
		ch = new long[F][V];
		for(int i = 0; i < F;i++)
			Arrays.fill(dp[i],-1);
		
		long ans = dp(0,0);
		int va = 0,fa=0;
		System.out.println(ans);
		while(va < V && fa < F)
		{
			if(ch[fa][va] == 0)
			{
				va++;
			}
			else
			{
				fa++;
				va++;
				System.out.print(va+" ");
			}
		}
		System.out.println();
	}
	private static long dp(int va, int fa) {
		if(fa == F)
			return 0;
		if(va == V)
		{
			return -Integer.MAX_VALUE;
		}
		if(dp[fa][va] != -1)
			return dp[fa][va];
		
		long a = dp(va+1,fa);
		long b = P[fa][va] + dp(va+1,fa+1);
		long ans = max(a,b);
		ch[fa][va] = a > b ? 0:1;
		dp[fa][va] = ans;
		return ans;
	}
	static long[][] dp, ch;
	static int F,V;
	static int[][] P;
}
