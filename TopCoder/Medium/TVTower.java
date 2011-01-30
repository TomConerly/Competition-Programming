package TopCoder.Medium;
import static java.lang.Math.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/* TopCoder SRM 183 Division 1
 * Medium Problem 675 Points: TVTower
 * Type: Computational Geometry
 * Solution: The central tower is either at the midpoint of 2 towers, or the center of the circle defined by 3 towers
 * try all. Make sure to only feed the find center function 3 valid points (distinct and not colinear)
 */

public class TVTower {

	public double minRadius(int[] x, int[] y) {
		double ans = Double.MAX_VALUE;
		for(int i = 0; i < x.length;i++)
		{
			for(int j = i+1; j < x.length;j++)
			{
				for(int k = j+1; k < x.length;k++)
				{
					if((x[i] == x[j] && y[i]==y[j]) || (x[j] == x[k] && y[j]==y[k]) ||(x[i] == x[k] && y[i]==y[k])) continue;
					if(Line2D.ptLineDist(x[i],y[i],x[j],y[j],x[k],y[k]) < 1e-9) continue;
					double[] mid = circleCenter(x[i],y[i],x[j],y[j],x[k],y[k]);
					
					ans = min(ans,test(x,y,mid[0],mid[1]));
				}
				ans = min(ans,test(x,y,(x[i]+x[j])/2.0,(y[i]+y[j])/2.0));
			}
		}
		if(x.length == 1) ans = 0.0;
		return ans;
	}
	double test(int[] x, int[] y, double cx, double cy)
	{
		double ans = 0;
		for(int i = 0; i < x.length;i++)
		{
			ans = max(ans,Point2D.distance(x[i], y[i], cx, cy));
		}
		return ans;
	}
	
	double[] circleCenter(double x0, double y0, double x1, double y1, double x2, double y2) {
		// return the center of the circle that goes through the given 3 points.
		double[] dist = new double[3];
		dist[0] = -(x0*x0 + y0*y0);
		dist[1] = -(x1*x1 + y1*y1);
		dist[2] = -(x2*x2 + y2*y2);
		double D = det(new double[][] {{x0, y0, 1.0}, {x1, y1, 1.0}, {x2, y2, 1.0}});
		double A = det(new double[][] {{dist[0], y0, 1.0}, {dist[1], y1, 1.0}, {dist[2], y2, 1.0}})/D;
		double B = det(new double[][] {{x0, dist[0], 1.0}, {x1, dist[1], 1.0}, {x2, dist[2], 1.0}})/D;
		return (new double[] {A/(-2.0), B/(-2.0)});
	}
	private double det(double[][] m) {
		int n = m.length;
		double res = 1.0;
		for (int i = 0; i < n; i++) {
			int j = i;
			for (int k = i; k < n; k++) if (Math.abs(m[k][i]) > Math.abs(m[j][i])) j=k;
			if (Math.abs(m[j][i]) < 1e-10) return 0.0;
			if (j != i) res = -res;
			double[] t = m[j]; m[j] = m[i]; m[i] = t;
			res *= m[i][i];
			for (int l = i+1; l < n; l++) {
				double z = m[l][i]/m[i][i];
				for (int k=i; k<n; k++) m[l][k] -= z*m[i][k];
			}
		}
		return res;
	}
	public void p(Object s){System.out.println(s);}
}
