package SSU;
import java.util.*;
import java.math.*;

/* Saratov State University Online Judge
 * Problem 112: a^b-b^a
 * Type: Big Ints
 * Solution: Use biginteger.
 */


public class p112 {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int a =sc.nextInt();
		int b = sc.nextInt();
		
		BigInteger l = BigInteger.valueOf(a).pow(b);
		BigInteger r = BigInteger.valueOf(b).pow(a);
		
		System.out.println(l.subtract(r));
	}
}
