package TopCoder.Easy;
import java.util.*;

/* TopCoder SRM 458
 * Easy Problem 250 Points: BouncingBalls
 * Type: Simulation
 * Solution: The trick is to notice that if too balls collide we can treat it as if they went through each other. Because the balls just switch but
 * their speed and path is identical to the ball they colided with before the collision. Thus just look at the balls pairwise to check if they collide.
 */

public class BouncingBalls {
	public void p(Object s){System.out.println(s);}
	public double expectedBounces(int[] x, int T) {
		Arrays.sort(x);
		double ans = 0;
		for(int k = 0; k < (1<<x.length);k++)
		{
			int dir = k;
			for(int i = 0; i < x.length;i++)
			{
				for(int j = i+1; j < x.length;j++)
				{
					if((dir >> i)%2 == 1 && (dir>>j)%2 == 0)
					{
						if(x[j]-x[i] <= T*2)
							ans++;						
					}
				}
			}
			
		}
		return ans/(1<<x.length);
	}

}
