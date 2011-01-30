package ACM.Yokohama.y2006;
import java.util.*;
import java.awt.geom.*;
import static java.lang.Math.*;

/* ACM Asia Regional Yokohama 2006
 * Problem B: How I Mathematician Wonder What You Are
 * Type: Geometry
 * Solution: Extend every line segment of the polygon into a line, you know if there exists
 * a center it is on the correct side of every line (left side of the line). This can be 
 * done with half plane intersection which is messy to code. Realize that if there exists
 * a center it must lie on the intersection of two of these lines, so test all intersection points.
 */

public class B
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			int N = sc.nextInt();
			if(N == 0) break;
			int[][] p = new int[N][2];
			for(int i = 0; i < N;i++)
			{
				p[i][0] = sc.nextInt();
				p[i][1] = sc.nextInt();
			}
			l = new Line2D[N];
			for(int i = 0; i < N;i++)
			{
				l[i] = new Line2D.Double(p[i][0],p[i][1],p[(i+1)%N][0],p[(i+1)%N][1]);
			}
			boolean found = false;
			for(int i = 0; i < N;i++)
			{
				for(int j = i+1; j < N;j++)
				{
					Point2D pp = intersect(i,j);
					if(pp != null)
					{
					//	System.out.println(pp.getX() +" "+pp.getY());
						if(side(pp))
						{
							found = true;
							break;
						}
					}
				}
			}
			if(found)
				System.out.println(1);
			else
				System.out.println(0);
		}
	}
	static boolean side(Point2D p)
	{
		for(int i = 0; i < l.length;i++)
		{
			if(l[i].ptLineDist(p) < 1e-9)
				continue;
			double det = (l[i].getX2()-l[i].getX1())*(p.getY()-l[i].getY1()) -   (p.getX()-l[i].getX1())*(l[i].getY2()-l[i].getY1());
			if(det < 0)
				return false;
		}
		
		return true;
	}
	static Point2D intersect(int i, int j)
	{
		Line2D a = l[i];
		Line2D b = l[j];
		double A1 = a.getY2() - a.getY1();
		double B1 = a.getX1() - a.getX2();
		double C1 = A1*a.getX1() + B1*a.getY1();

		double A2 = b.getY2() - b.getY1();
		double B2 = b.getX1() - b.getX2();
		double C2 = A2*b.getX1() + B2*b.getY1();
		
		double det = A1*B2 - A2*B1;

		if(abs(det) < 1e-9)
			return null;
		else
		{
			Point2D.Double ans = new Point2D.Double((B2*C1-B1*C2)/det,(A1*C2-A2*C1)/det);
			return ans;
		}
	}
	static Line2D[] l;
}
