package TopCoder.Easy;
import java.util.*;
import static java.lang.Math.*;
/* TopCoder SRM 449
 * Easy Problem 250 Points: MaxTriangle
 * Type: Brute Force
 * Solution: Find all pythagorean triples that match, then try all pairs of triples.
 */
public class MaxTriangle {

	public double calculateArea(int A, int B) {
		ax = new ArrayList<Integer>();
		bx = new ArrayList<Integer>();
		ay = new ArrayList<Integer>();
		by = new ArrayList<Integer>();
		
		for(int x = 0;x*x <= A;x++)
		{
			double sum = sqrt(A-x*x);
			if(round(sum) == sum)
			{
				ax.add((int)sum);
				ay.add(x);
			}			
		}
		for(int x = 0;x*x <= B;x++)
		{
			double sum = sqrt(B-x*x);
			if(round(sum) == sum)
			{
				bx.add((int)sum);
				by.add(x);
			}			
		}
		double ans = -1.0;
		for(int i = 0; i < ax.size();i++)
			for(int j = 0; j < bx.size();j++)
			{
				ans = max(ans,area(ax.get(i),ay.get(i),bx.get(j),by.get(j)));
				ans = max(ans,area(ay.get(i),ax.get(i),bx.get(j),by.get(j)));
				ans = max(ans,area(ax.get(i),ay.get(i),by.get(j),bx.get(j)));
				ans = max(ans,area(ay.get(i),ax.get(i),by.get(j),bx.get(j)));
			}
		
		return ans;
	}
	ArrayList<Integer> ax,bx,ay,by;
	public double area(int x1, int y1, int x2, int y2)
	{
		double best = 0.0;
		best = max(best,area1(x1,y1,x2,y2));
		best = max(best,area1(x1,y1,x2,-y2));
		best = max(best,area1(x1,-y1,x2,y2));
		best = max(best,area1(x1,-y1,x2,-y2));
		best = max(best,area1(-x1,y1,x2,y2));
		best = max(best,area1(-x1,y1,x2,-y2));
		best = max(best,area1(-x1,-y1,x2,y2));
		best = max(best,area1(-x1,-y1,x2,-y2));
		best = max(best,area1(x1,y1,-x2,y2));
		best = max(best,area1(x1,y1,-x2,-y2));
		best = max(best,area1(x1,-y1,-x2,y2));
		best = max(best,area1(x1,-y1,-x2,-y2));
		best = max(best,area1(-x1,y1,-x2,y2));
		best = max(best,area1(-x1,y1,-x2,-y2));
		best = max(best,area1(-x1,-y1,-x2,y2));
		best = max(best,area1(-x1,-y1,-x2,-y2));
		return best;
	}
	public double area1(int x1, int y1, int x2, int y2)
	{
		return abs(x1*y2-y1*x2)/2.0;
	}
}
