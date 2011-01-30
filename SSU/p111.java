package SSU;
import java.math.BigInteger;
import java.util.Scanner;

/* Saratov State University Online Judge
 * Problem 111: Very simple problem
 * Type: Binary search
 * Solution: Binary search with big ints.
 */


public class p111 {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		BigInteger n = sc.nextBigInteger();

		BigInteger low = BigInteger.ONE;
		BigInteger high = n;
		
		BigInteger best = BigInteger.ZERO;
		while(low.compareTo(high) <= 0)
		{
			BigInteger mid = (low.add(high)).divide(BigInteger.valueOf(2));
			
			if((mid.multiply(mid).compareTo(n)) <= 0)
			{
				low = mid.add(BigInteger.ONE);
				best = best.max(mid);
			}
			else
			{
				high = mid.subtract(BigInteger.ONE);
			}
		}
		System.out.println(best);
	}
}
