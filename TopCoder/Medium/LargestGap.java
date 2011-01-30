package TopCoder.Medium;
import java.util.*;
import static java.lang.Math.*;

/* TopCoder SRM 402
 * Med Problem 500 Points: LargestGap
 * Type: BruteForce
 * Solution: Simply try any gap.
 */
public class LargestGap {

	public int getLargest(String[] board) {
		String b = "";
		for(String a: board)
			b = b + a;
		int[] t = new int[b.length()];
		for(int i = 0; i < b.length();i++)
		{
			t[i] = b.charAt(i) == 'X'?1:0;
		}
		
		N = b.length();
		ArrayList<Integer> best = new ArrayList<Integer>();
		int bestAt = Integer.MAX_VALUE;
		System.out.println(N);
		for(int i = 0; i < N;i++)
		{
			if(t[i] == 1 && t[prev(i)] == 0)
			{
				int min = i;
				boolean firstBreak = false;
				ArrayList<Integer> seq = new ArrayList<Integer>();			
				int count = 0;
				for(int j = next(i);j != i;j=next(j))
				{
					if(t[j] == 1 && count != 0)
					{
						seq.add(count);
					}
					if(t[j] == 0){
						count++;
						firstBreak=true;
					}
					if(t[j] == 1) count = 0;
					if(t[j] == 1 && !firstBreak) min = min(min,j);
				}
				if(count != 0) seq.set(0,seq.get(0)+count);
				Collections.sort(seq,Collections.reverseOrder());
				if(better(seq,min,best,bestAt))
				{
					best = seq;
					bestAt = min;
				}
			}
		}
		//System.out.println("-------------");
		return bestAt;
	}
	public boolean better(ArrayList<Integer> a,int la, ArrayList<Integer> b,int lb)
	{
		for(int i = 0; i < min(a.size(),b.size());i++)
		{
			if(a.get(i) > b.get(i)) return true;
			if(b.get(i) > a.get(i)) return false;
		}
		if(a.size() > b.size()) return true;
		if(b.size() > a.size()) return false;
		if(la < lb) return true;
		if(lb < la) return false;
		return false;
	}
	int N;
	public int next(int a)
	{
		return (a+1)%N;
	}
	public int prev(int a)
	{
		return (a-1+N)%N;
	}
}
