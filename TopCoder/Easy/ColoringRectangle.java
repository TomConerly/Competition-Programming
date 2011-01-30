package TopCoder.Easy;
import java.util.*;
import static java.lang.Math.*;

/* TopCoder SRM 460
 * Easy Problem 300 Points: ColoringRectangle
 * Type: Greedy
 * Solution: You must alternate Red-Blue-Red (either one first). Use biggest first. Assuming you alternate you never have any problems so seperate
 * things as much as possible.
 */

public class ColoringRectangle {
	public void p(Object s){System.out.println(s);}
	public int chooseDisks(int W, int H, int[] R, int[] B) {
		Arrays.sort(R);
		Arrays.sort(B);
		
		int a1 = solve(W,H,R,B);
		int a2 = solve(W,H,B,R);
		if(a1 == -1)
			return a2;
		if(a2 == -1)
			return a1;
		return min(a1,a2);
	}
	private int solve(int W, int H, int[] B, int[] R) {
		double widthLeft = W;
		int bat = B.length-1;
		int rat = R.length-1;
		int used = 0;
		while(true)
		{
			if(bat < 0)
				break;	
			if(B[bat] < H)
				break;
			double cover = 2*sqrt(pow(B[bat]/2.,2)-pow(H/2.,2));			
			widthLeft -= cover;
			bat--;
			used++;
			if(widthLeft <= 0)
				return used;
			
			if(rat < 0)
				break;
			if(R[rat] < H)
				break;
			cover = 2*sqrt(pow(R[rat]/2.,2)-pow(H/2.,2));			
			widthLeft -= cover;
			rat--;
			used++;
			if(widthLeft <= 0)
				return used;
		}
		return -1;
	}
	
}
