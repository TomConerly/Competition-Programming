package SSU;
import java.util.*;

/* Saratov State University Online Judge
 * Problem 444: Headstrong Student
 * Type: Math
 * Solution: Do standard long division. If you visit the same thing twice then you are done.
 */


public class p444 {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int y = sc.nextInt();
		x %= y;
		if(x == 0)
		{
			System.out.println("0 0");
			return;
		}
		int[] V = new int[y];
		Arrays.fill(V,-1);
		int rem = x;
		int at = 0;
		int len = 0;
		while(rem != 0)
		{
			if(V[rem] != -1)
			{
				len = at - V[rem];
				at = V[rem];
				break;
			}
			V[rem] = at;
			at++;
			rem *= 10;
			rem %= y;
		}
		System.out.println(at+" "+len);
	}
}
