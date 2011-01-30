package SSU;
import java.util.*;

/* Saratov State University Online Judge
 * Problem 117: Counting
 * Type: Math
 * Solution: Use fast exponentiation.
 */

public class p117 {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int K = sc.nextInt();
		
		int ans = 0;
		for(int i = 0; i < N;i++)
		{
			int t = sc.nextInt();
			if(powmod(t,M,K) == 0)
				ans++;
		}
		System.out.println(ans);
	}

	private static int powmod(int base, int exp, int mod) {
		int temp = exp;
		int ans = 1;
		int big = base%mod;
		
		while(temp != 0)
		{
			if(temp%2 == 1)
				ans  = (ans*big)%mod;
			temp /=2;
			big = (big*big)%mod;
		}		
		return ans%mod;
	}
}
