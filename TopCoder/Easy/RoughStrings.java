package TopCoder.Easy;
import java.util.*;
import static java.lang.Math.*;

/* TopCoder SRM 394
 * Easy Problem 250 Points: RoughStrings
 * Type: Simulation
 * Solution: Just do it.
  */

public class RoughStrings {

	public int minRoughness(String s, int n) {
		int[] counts = new int[26];
		for(int i = 0; i < s.length();i++)
		{
			counts[s.charAt(i)-'a']++;
		}
		int z = 0;
		for(int i = 0; i < 26;i++)
		{
			if(counts[i] == 0) z++;
		}
		int[] next = new int[26-z];
		int at = 0;
		for(int i = 0; i < 26;i++)
		{
			if(counts[i] != 0)
			{
				next[at++]= counts[i];
			}
		}
		counts = next;
		Arrays.sort(counts);
	//	print(counts);
		int[] c = new int[counts.length];
		for(int i = 0; i < c.length;i++)
		{
			c[i] = counts[i];
		}
		int[] min = new int[n+1];
		min[0] = c[0];
		for(int i = 0; i < n;i++)
		{
			decMin(c);
			Arrays.sort(c);
			min[i+1] = small(c);
		}
		
		for(int i = 0; i < c.length;i++)
		{
			c[i] = counts[i];
		}
		int[] max = new int[n+1];
		max[0] = c[c.length-1];
		for(int i = 0; i < n;i++)
		{
			c[c.length-1]--;
			Arrays.sort(c);
			max[i+1] = c[c.length-1];
		}
		//print(min);
		//print(max);
		int best = Integer.MAX_VALUE;
		for(int i = 0; i <= n;i++)
		{
			best = min(best, max(0,max[n-i]-min[i]));
		}
		return best;
		
	}

	private int small(int[] c) {
		for(int i = 0; i < c.length;i++)
		{
			if(c[i] == 0) continue;
			return c[i];
		}
		return -1;
	}

	private void decMin(int[] c) {
		for(int i = 0; i < c.length;i++)
		{
			if(c[i] == 0) continue;
			c[i]--;
			return;
		}
		
	}

}
