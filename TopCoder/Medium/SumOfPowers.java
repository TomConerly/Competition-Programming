package TopCoder.Medium;
import java.math.BigInteger;
/* TopCoder SRM 397
 * Medium Problem 500 Points: SumOfPowers
 * Type: Math
 * Solution: Google for a closed form and solve it.
 */

public class SumOfPowers {

	long m = 1000000007L;
	public int value(int n, int p) {
		long ans = 0;
		for(long i = 1; i <= p;i++)
		{
			for(long j = 0; j < i;j++)
			{
				long a = pow(-1,j);
				long b = pow(i-j,p);
				long c = choose(n+p-i+1,n-i);
				long d = choose(p+1,j);
				ans += m((m(a*b))*(m(c*d)));
			}
		}
		return (int)m(ans);
	}
	
	public long m(long t)
	{
		long ans = t%m;
		while(ans < 0) ans += m;
		return ans;
	}
	private long pow(long l, long p) {
		long ans = 1;
		for(int i = 0; i < p;i++)
			ans = m(ans*l);
		return m(ans);
	}
	long choose(long n, long k)
	{
		BigInteger ans = BigInteger.ONE;
		for(long i = n; i > k;i--)
		{
			ans = ans.multiply(BigInteger.valueOf(i));

		}
		for(int j = 1; j <= n-k ;j++)
		{
			ans = ans.divide(BigInteger.valueOf(j));
		}
		return m(ans.mod(BigInteger.valueOf(m)).longValue());
	}
}
