package SSU;
import java.util.*;

/* Saratov State University Online Judge
 * Problem 123: The Sum
 * Type: Math
 * Solution: Just do it.
 */

public class p123 {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();
		long ans = 0;
		long a = 0;
		long b = 1;
		
		for(int i = 0; i < K;i++)
		{
			ans += b;
			long temp = a+b;
			a = b;
			b = temp;
		}
		System.out.println(ans);
	}
}
