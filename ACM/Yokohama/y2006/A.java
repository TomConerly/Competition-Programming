package ACM.Yokohama.y2006;
import java.util.*;

/* ACM Asia Regional Yokohama 2006
 * Problem A: How I Wonder What You Are!
 * Type: Geometry
 * Solution: Just work out the geometry (solved by Celestine).
 */

public class A
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		while (true)
		{
			int ns = sc.nextInt();
			if (ns == 0) break;
			double[][] stars = new double[ns][3];
			for (int i = 0; i < ns; i++)
			{
				stars[i][0] = sc.nextDouble();
				stars[i][1] = sc.nextDouble();
				stars[i][2] = sc.nextDouble();
			}

			int m = sc.nextInt();
			double[][] teles = new double[m][3];
			double[] telangle = new double[m];
			for (int i = 0; i < m; i++)
			{
				for (int j = 0; j < 3; j++)
				{
					teles[i][j] = sc.nextDouble();
				}
				telangle[i] = sc.nextDouble();
			}

			boolean[] seen = new boolean[ns];
			Arrays.fill(seen, false);
			for (int i = 0; i < ns; i++)
			{
				for (int j = 0; j < m; j++)
				{
					double dotproduct = dot(stars[i], teles[j]);
					double slen = length(stars[i]);
					double tlen = length(teles[j]);
					double theta = Math.acos(dotproduct / slen / tlen);
					if (theta < telangle[j])
					{
						seen[i] = true;
						break;
					}
				}
			}

			int count = 0;
			for (int i = 0; i < ns; i++)
			{
				if (seen[i]) count++;
			}

			System.out.println(count);

		}
	}
	public static double dot (double[] s, double[] t)
	{
		return s[0] * t[0] + s[1] * t[1] + s[2] * t[2];
	}
	public static double length(double[] s)
	{
		return Math.sqrt(s[0] * s[0] + s[1] * s[1] + s[2] * s[2]);
	}

}
