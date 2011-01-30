package TopCoder.Easy;
/* TopCoder SRM 452
 * Easy Problem 250 Points: NotTwo
 * Type: Greedy
 * Solution: Guess that the optimal is of the form:
 *           **--**--**--
 *           **--**--**--
 *           __**__**__**
 *           __**__**__**
 */

import static java.lang.Math.*;

public class NotTwo {
	public void p(Object s){System.out.println(s);}
	public int maxStones(int width, int height) {
		int ans = 0;
		for(int i = 0; i < height;i++)
		{
			if(i%4 == 0 || i%4 == 1)
			{				
				int k = width/4;
				ans += k*2+min(width%4,2);
			}else 
			{
				int w = max(0,width-2);
				int k = w/4;
				ans += k*2+min(w%4,2);
			}
		}
		System.out.println(ans);
		return ans;
	}
	
}
