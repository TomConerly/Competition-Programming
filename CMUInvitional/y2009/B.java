package CMUInvitional.y2009;
import java.util.*;
import static java.lang.Math.*;

/* CMU Spring Invitational 2009
 * Problem B: Marbles
 * Type: Math
 * Solution: Just do it.
 */

public class B {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int z = sc.nextInt();
		for(int t = 0; t < z;t++)
		{
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();
			
			double slope = ((double)(y1-y2))/(x1-x2);
			if(abs(slope) < 1e-9) System.out.println("Neither");
			else if (slope > 0) System.out.println("Left");
			else System.out.println("Right");
		}
	}
}
