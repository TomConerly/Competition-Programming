package TopCoder.Medium;
import java.util.*;
import static java.lang.Math.*;

/* TopCoder TCCC07 ROund 1A 
 * Medium Problem 500 Points: LongestSubanagramChain
 * Type: Greedy
 * Solution: Sort the characters in each word. We need to pick k from each
 * and order so that each col is increasing. We want the smallest k from the first one,
 * then from the next we want the smallest k that work on top of the previous k.
 */

public class LongestSubanagramChain {

	public int getLength(String[] B) {
		int N = Integer.MAX_VALUE;
		for(String s:B) N = min(N,s.length());
		int[][] c = new int[B.length][];
		for(int i = 0; i < B.length;i++)
		{
			c[i] = new int[B[i].length()];
			for(int j = 0; j < B[i].length();j++)
			{
				c[i][j] = B[i].charAt(j)-'a';
			}
			Arrays.sort(c[i]);
		}
		
		int best = 0;
		for(int i = 0; i <= N;i++)
		{
			boolean good = true;
			boolean[] used = new boolean[50];
			for(int j = 0; j < i;j++)
				used[j] = true;
			boolean[] used2 = new boolean[50];
			next:
			for(int j = 0; j < B.length - 1;j++)
			{
				int at1 = 0;
				int at2 = 0;
				while(true)
				{
					if(at1 >= c[j].length)
						break;
					if(!used[at1])
					{
						at1++;
						continue;
					}
					if(at2 >= c[j+1].length)
					{
						good = false;
						break next;
					}
					
					if(c[j][at1] > c[j+1][at2])
					{
						at2++;
						continue;
					}else{
						used2[at2++] = true;
						at1++;
					}
				}
				used = used2;
				used2 = new boolean[50];
			}
			if(good) best = i;
		}
		return best;
	}
	public void p(Object o){System.out.println(o);}
}
