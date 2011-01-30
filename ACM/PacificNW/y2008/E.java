package ACM.PacificNW.y2008;
import java.util.*;

/* ACM Pacific Northwest 2008
 * Problem E: Pencils from the 19th Century
 * Type: Brute Force
 * Solution: Just try all combinations.
 */

public class E
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		for(int zz = 1; ;zz++)
		{
			int N = sc.nextInt();
			if(N == 0)
				break;
			System.out.println("Case "+zz+":");
			System.out.println(N+" pencils for "+N+" cents");
			int tc = N*4;
			boolean found = false;
			for(int a = 1; a <= N;a++)
			{
				for(int b = 1; b <= N;b++)
				{
					for(int c = 1; c <= N;c++)
					{
						if(a+b+c != N)
							continue;
						int cost = a*16+b*2+c;
						if(cost == tc)
						{
							System.out.println(a+" at four cents each");
							System.out.println(b+" at two for a penny");
							System.out.println(c+" at four for a penny");
							System.out.println();
							found = true;
						}
					}
				}
			}
			if(!found)
			{
				System.out.println("No solution found.");
				System.out.println();
			}
		}
	}
}
