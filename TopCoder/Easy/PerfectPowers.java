package TopCoder.Easy;
import java.math.BigInteger;
import java.util.*;
import static java.lang.Math.*;

/* TopCoder TCO'09 Semifinal
 * Easy Problem 250 Points: PerfectPowers
 * Type: Math
 * Solution: Node that there are only 10^6 numbers m^3 < 10^18, and ~30k m^4, and decreasing. So find all of those, you are missing the ones of the form
 * m^2. For each of the others compare it to another of that kind and compute the closest power of 2. Watch out for special cases cause powers of 4 are also
 * powers of 2.
 */

public class PerfectPowers {
	public void p(Object s){System.out.println(s);}
	public long nearestCouple(long A, long B) {
		long[] N = new long[1010000];
		int at = 0;
		for(int k = 3;k<=63 ;k+=2)
		{
			if(BigInteger.valueOf(k).isProbablePrime(100) == false)
				continue;
			long low = (long)ceil(pow(A,1./k));
			long high = (long)floor(pow(B,1./k));
			for(long i = max(1,low-5); i<= high+5;i++)
			{
				long l = pw(i,k);
				if(l < A || l > B)
					continue;
				if(pw((long)round(sqrt(i)),2) == i)
					continue;
				N[at++] = l;
			}
		}
		System.out.println(A+" "+B+" "+at);
		long best = Long.MAX_VALUE;
		long low = (long)ceil(pow(A,1./2));
		while(pw(low,2) < A)
			low++;
		if(A <= pw(low,2) && pw(low +1,2) <= B)
			best = min(best,pw(low+1,2)-pw(low,2));
		
		long prev = -1;
		Arrays.sort(N);
		
		for(Long l:N)
		{
			if(l == 0)
				continue;
			//System.out.println(l);
			if(prev != -1 && l != prev)
				best = min(best,l-prev);
			
			long ll = (long)floor(pow(l,1./2));
			if(A <= pw(ll,2) && pw(ll,2) <= B)
				best = min(best,abs(pw(ll,2)-l));
			ll++;
			if(A <= pw(ll,2) && pw(ll,2) <= B)
				best = min(best,abs(pw(ll,2)-l));
			
			prev = l;
		}
		return best == Long.MAX_VALUE ?-1:best;
	}
	private Long pw(long s, int p) {
		long ans = 1;
		for(int i= 0; i < p;i++)
		{
			ans *= s;
		}
		return ans;
	}
	
}
