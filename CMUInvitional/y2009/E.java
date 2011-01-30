package CMUInvitional.y2009;
import java.util.*;
import static java.lang.Math.*;

/* CMU Spring Invitational 2009
 * Problem E: Hyperspace
 * Type: Computational Geometry
 * Solution: Use some trig.
 */

public class E {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			int diam = sc.nextInt();
			int len = sc.nextInt();
			if(diam == -1 && len == -1) break;
			double radius = diam/2.0;
			double theta = (len/(diam*PI))*(2*PI);
			double ans = 1.1*2.0*radius*sin(theta/2.0);
			
			if(len < ans) System.out.println("use normal space");
			else System.out.println("use 4D space");
		}
	}
}
