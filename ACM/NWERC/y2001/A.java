package ACM.NWERC.y2001;
import java.util.*;

/* ACM NWERC 2001
 * Problem A: Area
 * Type: Computational Geometry
 * Solution: Use Pick's theorem. (Coded by Celestine)
 */

public class A
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int cases = 1; cases <= T; cases++)
		{
			int m = sc.nextInt();
			int[] xp = new int[m];
			int[] yp = new int[m];
			int curx = 0, cury = 0;
			xp[0] = 0; yp[0] = 0;
			int borderp = 0;
			for (int i = 1; i < m; i++)
			{
				int dx = sc.nextInt();
				int dy = sc.nextInt();
				curx += dx;
				cury += dy;
				xp[i] = curx;
				yp[i] = cury;
				borderp += gcd(Math.abs(dx), Math.abs(dy));
			}
			int dx = sc.nextInt();
			int dy = sc.nextInt();
			borderp += gcd(Math.abs(dx), Math.abs(dy));
			// twice of area
			int area = findArea(xp, yp, m);
			
			int interior = (area + 2 - borderp) / 2;
			System.out.println("Scenario #" + cases + ":");
			System.out.printf("%d %d %.1f\n", interior, borderp, area / 2.0f);
			System.out.println();


		}



	}

	public static int gcd (int x, int y)
	{
		if (x > y) return gcd(y, x);
		if (x == 0) return y;
		return gcd(y % x, x);
	}

	public static int findArea(int[] xp, int[] yp, int m)
	{
		int total = 0;
		for (int i = 0; i < m; i++)
		{
			int next = (i == m-1) ? 0 : i+1;
			total += xp[i] * yp[next] - xp[next] * yp[i];
		}
		return total;
	}
}
