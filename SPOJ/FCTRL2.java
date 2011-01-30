package SPOJ;
import java.math.BigInteger;
import java.util.*;


/* SPOJ Problem 24: FCTRL2
 * Type: Math
 * Solution: Use bigints.
 */

public class FCTRL2 {
	public static void p(Object o){System.out.println(o);}
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int z = sc.nextInt();
		for(int zz = 0; zz < z;zz++)
		{
			int N = sc.nextInt();
			BigInteger ans = BigInteger.ONE;
			for(int i = 2; i <=N;i++)
				ans = ans.multiply(BigInteger.valueOf(i));
			p(ans);
		}
	}
}
