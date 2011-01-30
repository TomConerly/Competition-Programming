package ACM.NCPC.y2008;
import java.util.*;

/* ACM NCPC 2008
 * Problem B: Best Compression Ever
 * Type: Simple
 * Solution: With B bits you can make 2^{b+1}-1 words (2^i at every length i n between 1 and b inclusive).
 */

public class B
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextLong())
		{
			long N = sc.nextLong();
			long B = sc.nextLong();
			long num = pow(2,B+1)-1; 

			if(N > num)
			{
				System.out.println("no");
			}else{
				System.out.println("yes");
			}
		}
	}
	public static long pow(long a, long b)
	{
		long ans = 1;
		for(int i = 0; i < b;i++)
			ans*=a;
		return ans;
	}
}
