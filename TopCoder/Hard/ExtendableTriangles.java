package TopCoder.Hard;
import static java.lang.Math.*;
import java.awt.geom.Point2D;
import java.util.*;

/* TopCoder 2009 TCO Round 2
 * Medium Problem 500 Points: ExtendableTriangles
 * Type: Convex Hull
 * Solution: Compute convex hull for each, you know that any triple of R,G,B with one inside the convex hull(not on
 * the edge) automatically counts. We can easily count all of those. Then just try all triangles with 3 points
 * on the convex hull. For each pair of points keep track of the largest area, so we can compute which 
 * ones are extendable in O(n^3) where n is the number of points on the convex hull which is 200.
 * 
 * You can make an approximation of the convex hull by taking the highest and lowest of each in every
 * row/col.
 */

public class ExtendableTriangles {
	public int getCount(String[] g) 
	{
		ArrayList<Point2D> rp = new ArrayList<Point2D>();
		ArrayList<Point2D> gp = new ArrayList<Point2D>();
		ArrayList<Point2D> bp = new ArrayList<Point2D>();

		for(int i = 0; i < g.length;i++)
			for(int j = 0; j < g[0].length();j++)
			{
				if(g[i].charAt(j)=='R') rp.add(new Point2D.Double(i,j));
				if(g[i].charAt(j)=='G') gp.add(new Point2D.Double(i,j));
				if(g[i].charAt(j)=='B') bp.add(new Point2D.Double(i,j));
			}
		ArrayList<Point2D> rh = convexHull2(rp,false);
		ArrayList<Point2D> gh = convexHull2(gp,false);
		ArrayList<Point2D> bh = convexHull2(bp,false);
		update(rh,rp);
		update(gh,gp);
		update(bh,bp);

		int ans = 0;
		//iside of blue
		ans += rp.size()*gp.size()*(bp.size()-bh.size());
		//outside of blue, inside of green
		ans += rp.size()*(gp.size()-gh.size())*(bh.size());
		//inside of red, outside of green/blue
		ans += (rp.size()-rh.size())*(gh.size())*(bh.size());

		double[][] mrg = new double[rh.size()][gh.size()];
		double[][] mrb = new double[rh.size()][bh.size()];
		double[][] mgb = new double[gh.size()][bh.size()];
		int[][] r1 = new int[rh.size()][2];
		int[][] g1 = new int[gh.size()][2];
		int[][] b1 = new int[bh.size()][2];
		for(int i = 0; i < rh.size();i++)
			r1[i] = new int[]{(int)rh.get(i).getX(),(int)rh.get(i).getY()};
		for(int i = 0; i < gh.size();i++)
			g1[i] = new int[]{(int)gh.get(i).getX(),(int)gh.get(i).getY()};
		for(int i = 0; i < bh.size();i++)
			b1[i] = new int[]{(int)bh.get(i).getX(),(int)bh.get(i).getY()};

		for(int i = 0; i < rh.size();i++)
			for(int j = 0; j < gh.size();j++)
				for(int k = 0; k < bh.size();k++)
				{
					double a = area(new int[][]{r1[i],g1[j],b1[k]});
					mrg[i][j] = max(a,mrg[i][j]);
					mrb[i][k] = max(a,mrb[i][k]);
					mgb[j][k] = max(a,mgb[j][k]);
				}
		for(int i = 0; i < rh.size();i++)
			for(int j = 0; j < gh.size();j++)
				for(int k = 0; k < bh.size();k++)
				{
					double a = area(new int[][]{r1[i],g1[j],b1[k]});
					if(a<mrg[i][j] || a<mrb[i][k] || a<mgb[j][k]) ans++;
				}
		return ans;
	}
	private void update(ArrayList<Point2D> h, ArrayList<Point2D> pp) {
		ArrayList<Point2D> toAdd = new ArrayList<Point2D>();
		for(Point2D p : pp)
		{
			if(h.contains(p)) continue;
			for(int i = 0; i < h.size();i++)
				if(cross(p.getX()-h.get(i).getX(),p.getY()-h.get(i).getY(),
						h.get((i+1)%h.size()).getX()-p.getX(),h.get((i+1)%h.size()).getY()-p.getY()) == 0)
				{
					toAdd.add(p);
					break;
				}
		}
		h.addAll(toAdd);
	}
	public double area(int[][] c)
	{
		return abs(c[0][0]*c[1][1]+c[1][0]*c[2][1]+c[2][0]*c[0][1]-c[0][1]*c[1][0]-c[1][1]*c[2][0]-c[2][1]*c[0][0])/2.0;
	}
	public ArrayList<Point2D> convexHull2(ArrayList<Point2D> points, boolean onEdge){
		if(points.size() <= 1) return points;
		int N = points.size();
		int p = 0;
		boolean[] used = new boolean[N];
		for(int i = 1; i<N; i++){
			if(points.get(i).getX() < points.get(p).getX())
				p = i;
		}
		ArrayList<Point2D> ans = new ArrayList<Point2D>();	
		int start = p;
		do{
			ans.add(points.get(p));
			int n = -1;
			double dist = 0;
			for(int i = 0; i<N; i++){
				if(i==p)continue;

				if(used[i])continue;

				if(n == -1) n = i;
				double cross = cross(points.get(i).getX()-points.get(p).getX(),points.get(i).getY()-points.get(p).getY()
						,points.get(n).getX()-points.get(p).getX(),points.get(n).getY()-points.get(p).getY());

				double d = sqrt(pow(points.get(i).getX()-points.get(p).getX(),2)+pow(points.get(i).getY()-points.get(p).getY(),2));

				if(cross < 0){
					n = i;
					dist = d;
				}else if(cross == 0 && d > dist){
					dist = d;
					n = i;
				}
			}
			p = n;
			used[p] = true;
		}while(start!=p);
		return ans;
	}
	public static double cross(double x1, double y1, double x2, double y2)
	{
		return x1*y2-x2*y1;
	}
}
