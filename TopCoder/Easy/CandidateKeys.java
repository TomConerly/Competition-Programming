package TopCoder.Easy;
import java.util.*;
import static java.lang.Math.*;

/* TopCoder SRM 386
 * Easy Problem 250 Points: CandidateKeys
 * Type: Simulation
 * Solution: Just do it.
 */

public class CandidateKeys {

	public int[] getKeys(String[] t) {
		int N = t.length;
		int M = t[0].length();
		ArrayList<Integer> ans = new ArrayList<Integer>();
		next:
			for(int i = 0; i < (1<<M);i++)
			{
				for(int j = 0; j < N;j++)
				{
					for(int k = 0; k < j;k++)
					{
						boolean diff = false;
						for(int m = 0; m < M;m++)
						{
							if((i&(1<<m)) == 0) continue;
							if(t[j].charAt(m) != t[k].charAt(m))
							{
								diff = true;
								break;
							}
						}
						if(!diff) continue next;
					}
				}
				ans.add(i);
			}
		if(ans.size() == 0) return new int[0];
		int low = Integer.MAX_VALUE;
		int high = 0;
		//p(ans);
		for(int i = 0; i < ans.size();i++)
		{
			boolean good = true;
			for(int j = 0; j < ans.size();j++)
			{
				if(i==j) continue;
				//p(ans.get(i)+" "+ans.get(j)+" "+(ans.get(i)&ans.get(j)));
				if((ans.get(i)&ans.get(j)) == ans.get(j) && Integer.bitCount(ans.get(i)) > Integer.bitCount(ans.get(j)))
				{
				//	p("bad");
					good = false;
					break;
				}
			}
			if(good)
			{
				//p(i);
				low = min(low,Integer.bitCount(ans.get(i)));
				high = max(high,Integer.bitCount(ans.get(i)));
			}
		}
		//p("ans: "+low+" "+high);
		return new int[]{low,high};
	}
	public void p(Object o){System.out.println(o);}
}
