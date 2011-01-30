package SPOJ;
import java.util.*;

/* SPOJ Problem 5467: FP
 * Type: DP
 * Solution: Sort strings in the decreasing lexicographic order. Note that a number is divisible by 9 its digits are 0 mod 9. 
 * DP[string at][num strings left][value mod 9] = largest string to finish.
 * 
 * Sorting in decreasing lexicographic order gets ugly with things like "12", "121", and "1212" or "21", "212", and "2121".
 * If you are comparing a and b instead compare aaaaaaa with bbbbbbb (for a high enough # of copies).
 */

public class FP {
	private static String[][][] DP;
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int z = sc.nextInt();
		for(int zz = 0; zz < z;zz++)
		{
			N = sc.nextInt();
			K = sc.nextInt();
			E = new Entry[N];
			for(int i = 0; i < N;i++)
			{
				int n = sc.nextInt();
				String s = n+"";
				int v = 0;
				while(n > 0)
				{
					v += n%10;
					n/=10;
				}
				E[i] = new Entry(s,v);
			}
			Arrays.sort(E);
			DP = new String[N][K+1][10];
			for(String[][] s:DP)
				for(String[] ss:s)
					Arrays.fill(ss,"-");
			String ans = recur(0,K,0);
			if(ans == null)
				System.out.println("-1");
			else
				System.out.println(ans);
		}
	}
	private static String recur(int at, int left,int mod) {
		if(at == N)
			return mod == 0 &&left==0? "":null;
		if(!"-".equals(DP[at][left][mod]))
			return DP[at][left][mod];
		String a1 = recur(at+1,left,mod);
		String a2 = left > 0 ? recur(at+1,left-1,(mod+E[at].v)%9):null;
		String ans = null;
		if(a1 != null)
			ans = a1;
		if(a2 != null)
		{
			a2 = E[at].s+a2;
			if(ans == null || (ans.length() < a2.length()) || (ans.length() == a2.length() && a2.compareTo(ans) > 0))
				ans = a2;
		}
		DP[at][left][mod] = ans;
		return ans;
	}
	static Entry[] E;
	static int N,K;
	static class Entry implements Comparable<Entry>
	{
		String s;
		int v;
		Entry(String s, int v)
		{
			this.s = s;
			this.v = v;
		}
		@Override
		public int compareTo(Entry e) {
			int a = 0;			
			while(a < 100)
			{
				char c1 = s.charAt((a)%s.length());
				char c2 = e.s.charAt((a)%e.s.length());
				if(c1 < c2)
					return 1;
				if(c2 < c1)
					return -1;
				a++;
			}
			return 0;
		}
	}
}