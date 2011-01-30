package SSU;
import java.util.*;

/* Saratov State University Online Judge
 * Problem 102: Domino
 * Type: Brute Force
 * Solution: Brute force it.
 */

public class p102 {

	public static void main(String[] args)
	{
		Scanner sc= new Scanner(System.in);
		int N = sc.nextInt();
		int ans = 0;
		for(int i = 1; i <= N;i++)
		{
			if(gcd(i,N) == 1)
			{
				ans++;
			}
		}
		System.out.println(ans);

	}

	private static int gcd(int a, int b) {
		if(b==0)
			return a;
		else
			return gcd(b,a%b);
	}
}
