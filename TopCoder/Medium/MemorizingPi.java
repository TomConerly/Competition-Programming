package TopCoder.Medium;
import java.util.*;
import static java.lang.Math.*;

/* TopCoder TCO07 Qual 3
 * Medium Problem 500 Points: MemorizingPi
 * Type: DP
 * Solution: Given that you are n characters in how much can you 
 * get for the rest of the string is the DP.
 */

public class MemorizingPi {

	public String[] segmentation(String[] digits) {
		S = "";
		for(String s:digits) S += s;
		N = S.length();
		dp = new long[N];
		Arrays.fill(dp,-1);
		choice = new boolean[N];
		
		recur(0);
		
		int at = 0;
		ArrayList<String> ans = new ArrayList<String>();
		String cur = "";
		while(at != S.length())
		{
			if(choice[at] == false)
			{
				if(cur.length() <= 96)
					cur += (cur.length() == 0?"":" ")+S.substring(at,at+3);
				else{
					ans.add(cur);
					cur = S.substring(at,at+3);
				}
				at += 3;
			}else{
				if(cur.length() <= 95)
					cur += (cur.length() == 0?"":" ")+S.substring(at,at+4);
				else{
					ans.add(cur);
					cur = S.substring(at,at+4);
				}
				at+=4;
			}
		}
		if(cur != "") ans.add(cur);
		String[] a = new String[ans.size()];
		for(int i = 0; i < ans.size();i++)
		{
			a[i] =ans.get(i);
		}
		return a;
	}
	int N;
	String S;
	long[] dp;
	boolean[] choice;
	long recur(int n)
	{
		if(n == N) return 0;
		if(n > N) return Integer.MAX_VALUE;
		if(dp[n] != -1) return dp[n];
		if(n+3>N) return Integer.MAX_VALUE;
		
		long a = recur(n+3)+score(S.substring(n,n+3));
		if(n+3 == N)
		{
			choice[n] = false;
			dp[n] = a;
			return a;
		}
		long b = recur(n+4)+score(S.substring(n,n+4));
		if(a <= b)
		{
			choice[n] = false;
		}else choice[n] = true;
		dp[n] = min(a,b);
		return dp[n];
	}
	private long score(String s) {
		if(s.length() < 3) return Integer.MAX_VALUE;
		boolean same = true;
		for(int i = 0; i < s.length()-1;i++)
			if(s.charAt(i) != s.charAt(i+1)) same = false;
		if(same) return 1;
		
		if(s.charAt(0) != '0' && Integer.bitCount(Integer.valueOf(s)) == 1) return 2;
		
		boolean good = true;
		for(int i = 0; i < s.length()-1;i++)
			if(s.charAt(i)+1 != s.charAt(i+1)) good = false;
		if(good) return 4;
		
		good = true;
		for(int i = 0; i < s.length()-1;i++)
			if(s.charAt(i)-1 != s.charAt(i+1)) good = false;
		if(good) return 5;
		
		if(s.charAt(0) == s.charAt(s.length()-1)) return 7;
		
		for(int i = 0; i < s.length();i++)
			for(int j = i+1;j<s.length();j++)
				if(s.charAt(i) == s.charAt(j)) return 8;
		return 10;
		
	}
	public void p(Object o){System.out.println(o);}
}
