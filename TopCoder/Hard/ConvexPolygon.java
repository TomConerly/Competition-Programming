package TopCoder.Hard;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import static java.lang.Math.*;

/* TopCoder SRM 166 Div 2
 * Hard Problem 900 Points: ConvexPolygon
 * Type: Computational Geometry
 * Solution: Use the polygon class...
 */

public class ConvexPolygon {

	public double findArea(int[] x, int[] y) {
		Poly p = new Poly();
		for(int i = 0; i < x.length;i++)
			p.addPoint(x[i],y[i]);
		return p.area();
	}
	public void p(Object o){System.out.println(o);}
	@SuppressWarnings("serial")
	public class Poly extends Polygon{	
		public Poly(){
			super();
		}
		public Poly(int[] x,int[] y, int n){
			super(x,y,n);
		}
		public double area(){
			double area = 0;
			for(int i = 0; i < npoints;i++){
				area += xpoints[i]*ypoints[(i+1)%npoints];
			}
			for(int i = 0; i < npoints;i++){
				area -= xpoints[(i+1)%npoints]*ypoints[i];
			}		
			return abs(area)/2.0;
		}
		public String toString()
		{
			return super.toString();
		}
		/* 
		 * tested (SRM 187 div 2 hard), returns 0 if on edge on polygon, 1 if inside, -1 if outside
		 * The java polygon is correct if the points is out/in, but if it is on the line
		 * it is undefined. Runtime is O(N)
		 */
		public int cont(double x, double y)
		{
			for(int i = 0; i < xpoints.length;i++)
			{
				if(Line2D.ptSegDist(xpoints[i], ypoints[i], xpoints[(i+1)%npoints], ypoints[(i+1)%npoints], x, y) < 1e-9)
					return 0; 
			}
			if(contains(x,y)) return 1;
			else return -1;
		}
	}
}
