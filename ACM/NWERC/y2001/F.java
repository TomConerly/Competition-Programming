package ACM.NWERC.y2001;
import java.util.*;
import static java.lang.Math.*;

/* ACM NWERC 2001
 * Problem F: Gridland
 * Type: Graph Theory
 * Solution: If either width or height is even you can do it using only length 1 edges, otw. you need one sqrt(2) edge.
 */

public class F
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int i = 0; i < T;i++)
		{
			int n = sc.nextInt();
			int m = sc.nextInt();
			double ans;
			if(n%2 == 0 || m %2 == 0)
			{
				ans = n*m;
			}
			else
			{
				ans = n*m-1+sqrt(2);
			}
			System.out.println("Scenario #"+(i+1)+":");
			System.out.format("%.02f\n\n",ans);
		}
	}
}
