package TopCoder.Easy;
import java.util.*;
import static java.lang.Math.*;
/* TopCoder TCO09 Round 3
 * Easy Problem 250 Points: SaveTheTrees
 * Type: Brute Force
 * Solution: Try every rectangle that has points on its edges (only O(n^2) rectangles)
 * count the number outside and look at their height, then take more out of the ones
 * inside the rectangle if necessary.
 */
public class SaveTheTrees {

	public int minimumCut(int[] x, int[] y, int[] h) {
		
		int[] xv = x.clone();
		int[] yv = y.clone();
		Arrays.sort(xv);
		Arrays.sort(yv);
		
		int best = Integer.MAX_VALUE;
		for(int lx = 0; lx < xv.length;lx++){
			for(int hx = lx;hx< xv.length;hx++){
				for(int ly = 0; ly < yv.length;ly++){
					for(int hy = ly;hy< yv.length;hy++){
						int[] he = h.clone();
						int extra = 0;
						int ans = 0;
						
						for(int i = 0; i < x.length;i++){
							if(xv[lx] <= x[i] && x[i] <= xv[hx]&&yv[ly] <= y[i] && y[i] <= yv[hy])
							{
							}else{
								ans++;
								extra += he[i];
								he[i] = 0;
							}
						}
						Arrays.sort(he);
						int need = 2*(xv[hx]-xv[lx])+2*(yv[hy]-yv[ly]);
						int at = h.length-1;
						while(extra < need && at >= 0)
						{
							extra += he[at];
							at--;
							ans++;
						}
						if(extra >= need)
							best = min(best,ans);
					}
				}
			}
		}
		return best;
	}
	public void p(Object o){System.out.println(o);}
}
