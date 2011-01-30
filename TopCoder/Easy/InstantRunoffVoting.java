package TopCoder.Easy;
import java.util.*;

/* TopCoder SRM 393
 * Easy Problem 250 Points: InstantRunoffVoting
 * Type: Simulation
 * Solution: Do it.
 */

public class InstantRunoffVoting {

	public int winner(String[] voters) {
		int N = voters[0].length();
		boolean[] out = new boolean[N];
		while(true)
		{
			int[] count = new int[N];
			for(String s: voters)
				count[vote(s,out)]++;
			int worst = Integer.MAX_VALUE;
			ArrayList<Integer> list = new ArrayList<Integer>();
			int best = -1;
			int bestAt = -1;
			for(int i = 0; i < N;i++)
			{
				if(out[i]) continue;
				if(count[i] > best)
				{
					best = count[i];
					bestAt = i;
				}
				if(worst > count[i])
				{
					list = new ArrayList<Integer>();
					list.add(i);
					worst = count[i];
				}else if(worst == count[i])
				{
					list.add(i);
				}
			}
			//System.out.println("best: "+best);
			if(best*2 > voters.length) return bestAt;
			boolean changed = false;
			for(int a:list)
			{
				changed = true;
				out[a] = true;
			}
				
			
			boolean allgone = true;
			for(boolean b: out)
				if(!b) allgone = false;
			if(allgone) return -1;
			if(!changed) return -1;
		}
	}
	public int vote(String pref, boolean[] out)
	{
		for(int i = 0; i < pref.length();i++)
		{
			if(out[pref.charAt(i)-'0']) continue;
			return pref.charAt(i)-'0';
		}
		return -1;
	}
	void print(int[] a)
	{
		for(int b:a)
			System.out.print(b+" ");
		System.out.println();
	}

}
