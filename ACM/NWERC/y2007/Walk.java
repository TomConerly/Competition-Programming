package ACM.NWERC.y2007;
import java.util.*;
import java.awt.*;
import static java.lang.Math.*;

/* ACM NWERC 2007
 * Problem J: Walk
 * Type: Geometry/Ad Hoc
 * Solution: If a curve contains neither the start or end, or both the start or end we don't care about it. We
 * know Alice has to go out of the polygons containing just her, then into the ones containing just Bob. The order
 * for this is determined by the nesting of the polygons (which is based on polygon area). So Alice goes out of
 * the polygons contains just her in increasing order of area, and into the polygons containing just Bob in decreasing
 * order of area.
 */

public class Walk 
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int Z = sc.nextInt();
		for(int zz = 0; zz < Z;zz++)
		{
			int N = sc.nextInt();
			Poly[] polys = new Poly[N];
			int[] H = new int[N];
			for(int i = 0; i < N;i++)
			{
				H[i] = sc.nextInt();
				int P = sc.nextInt();
				int[] x = new int[P];
				int[] y = new int[P];
				for(int j = 0; j < P;j++)
				{
					x[j] = sc.nextInt();
					y[j] = sc.nextInt();
				}
				Polygon pp = new Polygon(x,y,P);
				polys[i] = new Poly(pp,H[i],area(pp));	
				
			}
			Arrays.sort(polys);
			int sx = 0,sy=0,ex=100000,ey=0;
			ArrayList<Integer> heights = new ArrayList<Integer>();
			for(int i = 0; i < N;i++)
			{
				if(polys[i].p.contains(sx,sy) &&( !polys[i].p.contains(ex,ey)))
					heights.add(polys[i].height);
			}
			for(int i = N-1; i >= 0;i--)
			{
				if((!polys[i].p.contains(sx,sy)) && polys[i].p.contains(ex,ey))
					heights.add(polys[i].height);
			}
			int up = 0;
			int down = 0;
			for(int i = 0; i < heights.size()-1;i++)
			{
				if(heights.get(i) > heights.get(i+1))
				{
					down += heights.get(i)-heights.get(i+1);
				}else if(heights.get(i+1) > heights.get(i)){
					up += heights.get(i+1)-heights.get(i);
				}
			}
			System.out.println(up+" "+down);
		}
	}
	private static class Poly implements Comparable<Poly>
	{
		Polygon p;
		int height;
		long area;
		Poly(Polygon a, int b, long c)
		{
			p = a;
			height  = b;
			area = c;
		}
		public int compareTo(Poly a) 
		{
			return Long.valueOf(area).compareTo(a.area);
		}
	}
	public static long area(Polygon p)
	{
		long a = 0;
		for(int i = 0; i < p.npoints;i++)
		{
			a += ((long)p.xpoints[i])*p.ypoints[(i+1)%p.npoints];
			a -= ((long)p.ypoints[i])*p.xpoints[(i+1)%p.npoints];
		}
		return abs(a);
	}
	
}
