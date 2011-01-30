package TopCoder.Hard;
import static java.lang.Math.*;
import java.awt.Polygon;
import java.awt.geom.Line2D;

/* TopCoder SRM 187 Division 2
 * Hard Problem 1000 Points: PointsInPolygon
 * Type: Computational Geometry
 * Solution: Use the poly class from the notebook, note that the 
 * java polygon class doesn't detect the boundary case correctly.
 */

public class PointInPolygon {

	public String testPoint(String[] v, int x, int y) {
		int[] px = new int[v.length];
		int[] py = new int[v.length];
		for(int i = 0; i < v.length;i++)
		{
			int x1 = Integer.parseInt(v[i].split(" ")[0]);
			int y1 = Integer.parseInt(v[i].split(" ")[1]);
			px[i] = x1;
			py[i] = y1;
		}
		Poly p = new Poly(px,py,v.length);
		int c = p.cont(x, y);
		if(c == 0) return "BOUNDARY";
		else if(c == 1) return "INTERIOR";
		else return "EXTERIOR";
	}
	public void p(Object s){System.out.println(s);}

	@SuppressWarnings("serial")
	private class Poly extends Polygon{	
		@SuppressWarnings("unused")
		public Poly(){
			super();
		}
		public Poly(int[] x,int[] y, int n){
			super(x,y,n);
		}
		@SuppressWarnings("unused")
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
