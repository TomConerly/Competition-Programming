package SPOJ;
import java.util.*;

/* SPOJ Problem 2: PRIME1
 * Type: Math
 * Solution: Use a sieve to find all primes <= sqrt(10^9)
 * then use those as a sieve to find primes in the correct
 * range.
 */
public class PRIME1 {
	public static void main(String[] args)
	{

		Scanner sc = new Scanner(System.in);
		int z =sc.nextInt();
		boolean[] prime = new boolean[100000];
		ArrayList<Integer> primes = new ArrayList<Integer>();
		for(int i = 2; i < prime.length;i++)
		{
			if(!prime[i])
			{
				primes.add(i);
				for(int j = i;j < prime.length;j+=i)
					prime[j] = true;
			}
		}
		for(int zz = 0; zz < z;zz++)
		{
			int m = sc.nextInt();
			int n = sc.nextInt();
			StringBuilder ans = new StringBuilder();
			boolean[] test = new boolean[n-m+1];
			for(int p: primes)
			{
				for(int i = (m/p)*p;i<= n;i+=p)
				{
					if(i==p) continue;
					if(i-m < 0) continue;
					test[i-m] = true;
				}
			}

			for(int i = m;i<=n;i++)
			{
				if(i==1) continue;
				if(test[i-m] == false)
				{
					ans.append(i+"\n");
				}
			}
			System.out.println(ans);
		}
	}
}
