package ACM.NCPC.y2008;
import java.util.*;

/* ACM NCPC 2008
 * Problem E: Event Planning
 * Type: Simple
 * Solution: Just try all.
 */

public class E
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextInt())
		{
			int N = sc.nextInt();
			int B = sc.nextInt();
			int H = sc.nextInt();
			int W = sc.nextInt();
			int best = -1;
			for(int i = 0; i < H;i++)
			{
				int P = sc.nextInt();
				for(int j = 0; j < W;j++)
				{
					int num = sc.nextInt();
					if(num < N)
						continue;
					int cost = N*P;
					if(cost > B)
						continue;
					if(best == -1 || cost < best)
						best = cost;
				}
			}
			if(best == -1)
				System.out.println("stay home");
			else
				System.out.println(best);
		}
	}
}
