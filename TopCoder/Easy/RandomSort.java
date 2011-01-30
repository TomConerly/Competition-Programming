package TopCoder.Easy;
import java.util.*;

/* TopCoder SRM 402
 * Easy Problem 250 Points: RandomSort
 * Type: Simulation
 * Solution: Bounds are small so just simulate it.
 */

public class RandomSort {

	HashMap<Long,Double> mem;
	public double getExpected(int[] p) {
		mem = new HashMap<Long,Double>();
		N = p.length;
		return recur(pack(p));
	}
	int N;
	private double recur(long a) {
		if(mem.containsKey(a)){
			return mem.get(a);
		}
		int[] p = unpack(a);
		
		ArrayList<Integer> swaps = new ArrayList<Integer>();
		for(int i = 0; i < N;i++)
		{
			for(int j = i+1; j < N;j++)
			{
				if(p[i] > p[j]) swaps.add((i<<16)|j);
			}
		}
		double ans = 0.0;
		for(Integer s:swaps)
		{
			int t = p[s&0xFFFF];
			p[s&0xFFFF] = p[s>>>16];
			p[s>>>16] = t;
			ans += (1.0/swaps.size())*(recur(pack(p))+1);
			t = p[s&0xFFFF];
			p[s&0xFFFF] = p[s>>>16];
			p[s>>>16] = t;
		}
		mem.put(a,ans);
		return ans;
		             
	}
	public long pack(int[] p)
	{
		long a = 0;
		for(int i = 0; i < p.length;i++)
		{
			a |= ((long)p[i]) <<(i*8);
		}
		return a;
	}
	public int[] unpack(long a)
	{
		int[] p = new int[N];
		for(int i = 0; i < N;i++)
		{
			p[i] = (int)((a >>> (8*i)) &0xFF);
		}
		return p;
	}
}
