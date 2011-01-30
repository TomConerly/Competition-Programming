package SSU;
import java.util.*;

/* Saratov State University Online Judge
 * Problem 353: Billing
 * Type: Simulation
 * Solution: Simulate it, watch for weird cases.
 */

public class p353 {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int k1 = sc.nextInt();
		int k2 = sc.nextInt();
		int p1 = sc.nextInt();
		int p2 = sc.nextInt();
		int p3 = sc.nextInt();

		System.out.println(q(N,k1,k2,p1,p2,p3));
		
	}
	static int q(int N, int k1, int k2, int p1, int p2, int p3)
	{
		int ans = 0;
		if(N < p1)
			return ans;
		N -= p1;
		ans += k1;
				
		int n = 0;
		while( n < k2 && N > 0)
		{
			N -= p2;
			n++;
			ans++;
		}
		if( N <= 0)
			return ans;
		
		while( N > 0)
		{
			N-= p3;
			ans++;
		}		
		
		return ans;
	}
}
