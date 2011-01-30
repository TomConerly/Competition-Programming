package SSU;
import java.util.*;

/* Saratov State University Online Judge
 * Problem 276: Andrew's Trouble
 * Type: Trivial
 * Solution: ...
 */

public class p276 {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int S = sc.nextInt();
		int P = sc.nextInt();
		if(P <= S)
			System.out.println(0);
		else if(P-S < 5*60)
			System.out.println(1);
		else if(P-S < 15*60)
			System.out.println(2);
		else if(P-S < 30*60)
			System.out.println(3);
		else
			System.out.println(4);
	}
}
