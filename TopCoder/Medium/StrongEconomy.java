package TopCoder.Medium;
import java.math.BigInteger;
import static java.lang.Math.*;

/* TopCoder SRM 450
 * Medium Problem 500 Points: StrongEconomy
 * Type: Greedy
 * Solution: At any given point you will either not build any more and save for the end, or you
 * will build another tower and then recurse. The order of building towers is defined because
 * you always want to increase the smaller of the two types. 
 * 
 * At most you will build sqrt(10^12) towers because once you have 10^6 of each you can get
 * all the money you need in one step. Thus the runtime is O(10^6).
 * 
 * Watch out for overflow.
 */

public class StrongEconomy {
	public void p(Object s){System.out.println(s);}
	public long earn(long n, long k, long price, long target) {
		long f = n;
		long s = k;
		long g = 0;
		long t = 0;
		long best = Long.MAX_VALUE;
		
		if(BigInteger.valueOf(n).multiply(BigInteger.valueOf(k)).compareTo(BigInteger.valueOf(target)) >= 0)
		{
			return 1;
		}
		
		while(true)
		{
			best = min(best,t+ru(target-g,f*s));
			if(f*s > target)
				break;
			long dt = max(0,ru(price-g,f*s));
			
			g += dt*f*s;
			t += dt;
			g -= price;
			
			if(f < s)
				f++;
			else
				s++;
			
		}		
		
		return best;
	}
	public long ru(long a, long b)
	{
		return (a+b-1)/b;
	}
	
}
