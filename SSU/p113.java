package SSU;
import java.math.BigInteger;
import java.util.*;

/* Saratov State University Online Judge
 * Problem 113: Nearly Prime Numbers
 * Type: math
 * Solution: Use sieve to find primes <= sqrt(N). Then try of all of those primes, see if they divide N if so check if N/k is a prime.
 */

public class p113 {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		boolean[] prime = new boolean[100000];
		Arrays.fill(prime,true);
		prime[0]=false;prime[1]=false;
		for(int i = 2; i < prime.length;i++)
		{
			if(prime[i])
			{
				for(int j = i+i;j<prime.length;j+=i)
					prime[j] = false;
			}
		}
		for(int i = 0; i < N;i++)
		{
			int K = sc.nextInt();
			boolean found = false;
			for(int j = 0; j < prime.length;j++)
			{
				if(prime[j] && K%j == 0)
				{
					if(BigInteger.valueOf(K/j).isProbablePrime(100))
					{
						found = true;
						break;
					}
				}
			}
			if(found)
				System.out.println("Yes");
			else
				System.out.println("No");
		}
	}
}
