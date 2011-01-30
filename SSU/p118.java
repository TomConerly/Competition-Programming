package SSU;
import java.util.*;

/* Saratov State University Online Judge
 * Problem 118: Digital Root
 * Type: Math
 * Solution: You can apply the digital root operator whenever you want.
 */

public class p118 {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int zz = 1; zz <= T;zz++)
		{
			int N = sc.nextInt();
			int res = 1;
			int ans = 0;
			for(int i = 0; i < N;i++)
			{
				res = reduce(res*reduce(sc.nextInt()));
				ans = reduce(ans+res);
			}
			ans = reduce(ans);
			System.out.println(ans);
		}
	}

	private static int reduce(int res) {
		int ans = 0;
		while(res != 0)
		{
			ans += res%10;
			res/=10;
		}
		if(ans > 9)
			return reduce(ans);
		return ans;
	}
}
