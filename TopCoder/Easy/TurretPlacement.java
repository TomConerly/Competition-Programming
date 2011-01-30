package TopCoder.Easy;
import static java.lang.Math.*;
import static java.util.Arrays.*;

/* TopCoder SRM 465
 * Easy Problem 250 Points: TurretPlacement
 * Type: Geometry
 * Solution: Go over all pairs of spots. Compute the distance and based on that you can compute how many different pairs of sizes work from those spots.
 */

public class TurretPlacement {
	public void p(Object... s){System.out.println(deepToString(s));}
	public long count(int[] x, int[] y) {
		long ans = 0;
		for(int i = 0; i < x.length;i++)
			for(int j = i+1; j < x.length;j++)
			{				
				long d = (long) floor(2*sqrt(pow(x[i]-x[j],2)+pow(y[i]-y[j],2)))-2;
				ans += (d+2)*(d+1)/2;
			}
		return ans;
	}
	
}
