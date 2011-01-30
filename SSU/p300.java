package SSU;
import java.util.*;
import static java.lang.Math.*;

/* Saratov State University Online Judge
 * Problem 300: Train
 * Type: Computational Geometry
 * Solution: Find all intersections and the distance between the two along the tracks
 * min all of those and that is the answer.
 */

public class p300 {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		x = new int[N];
		y = new int[N];
		s = new int[N];
		int ans = 0;
		for(int i = 0; i < N;i++)
		{
			x[i] = sc.nextInt();
			y[i] = sc.nextInt();
			
			if(i > 0)
			{
				ans += abs(x[i]-x[i-1])+abs(y[i]-y[i-1]);
			}
			s[i] = ans;
		}
		for(int i = 1; i < N;i++)
		{
			for(int j = i+2;j<N;j++)
			{
				int[] p = cross(i,j);
				if(p == null)
				{
					continue;
				}
				int ta = s[i-1]+abs(x[i-1]-p[0])+abs(y[i-1]-p[1]);
				int tb = s[j-1]+abs(x[j-1]-p[0])+abs(y[j-1]-p[1]);
				ans = min(ans,tb-ta);
			}
		}
		
		System.out.println(ans);
		
	}
	private static int[] cross(int i, int j) {
		if(x[i]==x[i-1])
		{
			if(x[j] == x[j-1])
				return null;
			if(min(y[i],y[i-1]) <= y[j] &&  y[j] <= max(y[i],y[i-1]) &&
					min(x[j],x[j-1]) <= x[i] &&  x[i] <= max(x[j],x[j-1]))
			{
				return new int[]{x[i],y[j]};
			}
			return null;
		}
		else
		{
			if(x[j] == x[j-1])
				return cross(j,i);
			else
				return null;
		}
	}
	static int[]x ,y,s;
}
