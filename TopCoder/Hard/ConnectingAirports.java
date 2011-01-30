package TopCoder.Hard;
import java.util.*;

/* TopCoder TCO Round 2
 * Hard Problem 900 Points: ConnectingAirports
 * Type: MaxFlow/Decision
 * Solution: Can be solved with max flow. I solved by solving the decision problem
 * using greedy algorithm. Then doing the simple decision problem to actual solver.
 * 
 * To solve it greedily try sort both lists, try assigning the largest one in one
 * to the largest possible in the other. Then resort lists and continue.
 */
public class ConnectingAirports {

	public String[] getSchedule(int[] a, int[] b) {
		String[] ans = new String[a.length];
		boolean[][] ill = new boolean[a.length][b.length];
		if(!d(a,b,ill)) return new String[0];
		
		for(int i = 0; i < a.length;i++)
		{
			ans[i] = "";
			for(int j = 0; j < b.length;j++)
			{
				ill[i][j] = true;
				if(!d(a,b,ill))
				{
					ans[i] += "1";
					a[i]--;
					b[j]--;
				}else ans[i] += "0";
			}
		}
		return ans;
	}
	public boolean d(int[] A, int[]B,boolean[][] illegal)
	{	
		Entry[] a = new Entry[A.length];
		for(int i = 0; i < a.length;i++)
			a[i] = new Entry(A[i],i);
		Arrays.sort(a);
		
		Entry[] b = new Entry[B.length];
		for(int i = 0; i < b.length;i++)
			b[i] = new Entry(B[i],i);
		
		for(int i = a.length-1;i>=0;i--)
		{
			Arrays.sort(b);
			int count = 0;
			for(int j = b.length-1; count < a[i].num;j--)
			{
				if(j < 0) return false;
				if(illegal[a[i].idx][b[j].idx] || b[j].num == 0) continue;
				b[j].num--;
				count++;
			}
		}
		for(int i = 0; i < b.length;i++)
			if(b[i].num != 0) return false;
		return true;
	}
	public class Entry implements Comparable<Entry>{
		int num,idx;
		Entry(int n, int i)
		{
			num = n;
			idx = i;
		}
		public int compareTo(Entry o) {
			int a =  Integer.valueOf(num).compareTo(o.num);
			if(a != 0) return a;
			return -Integer.valueOf(idx).compareTo(o.idx);
		}
	}
}