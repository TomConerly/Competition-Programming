package TopCoder.Medium;
import java.math.BigInteger;
import java.util.*;

/* TopCoder SRM 434
 * Medium Problem 500 Points: HexatridecimalSum
 * Type: BigInteger
 * Solution: Use BigInteger... please.
 */

public class HexatridecimalSum {

	public String maximizeSum(String[] numbers, int k) {
		BigInteger[] imp = new BigInteger[36];
		BigInteger ans = BigInteger.ZERO;
		for(int i = 0; i < 36;i++)
			imp[i] = BigInteger.ZERO;
		
		for(int i = 0; i < numbers.length;i++)
		{
			System.out.println(new BigInteger(numbers[i],36));
			ans = ans.add(new BigInteger(numbers[i],36));
			for(int j = 0; j < numbers[i].length();j++)
			{
				int a = Integer.valueOf(numbers[i].charAt(j)+"",36);
				BigInteger pow = BigInteger.valueOf(36);
				pow = pow.pow(numbers[i].length()-j-1);
				imp[a]=imp[a].add(BigInteger.valueOf(35-a).multiply(pow));
			}
		}
		Arrays.sort(imp);
		for(int i = imp.length-1; i >= imp.length-k;i--)
		{
			ans = ans.add(imp[i]);
		}
		String a = ans.toString(36);
		return a.toUpperCase();
	}
	

}
