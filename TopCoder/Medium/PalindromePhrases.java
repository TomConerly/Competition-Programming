package TopCoder.Medium;
import java.util.*;
import static java.lang.Math.*;

/* TopCoder SRM 439
 * Medium Problem 500 Points: PalindromePhrases
 * Type: DP
 * Solution: DP over, which subset of words you have left, which word is unmatched
 * how much of its end is unmatched, and which side it is unmatched on. Constant factor
 * matters so be a little careful.
 */

public class PalindromePhrases {

	int N;
	String[] W;
	public long getAmount(String[] words) {
		W = words;
		N = W.length;
		dp = new long[1<<N][N][14][2];
		for(int i = 0; i < dp.length;i++)
			for(int j = 0; j < dp[0].length;j++)
				for(int k = 0; k < dp[0][0].length;k++)
					Arrays.fill(dp[i][j][k],-1);
		L = new int[N];
		for(int i= 0; i < N;i++)
			L[i] =W[i].length();
		sub1 = new String[N][14];
		sub2 = new String[N][14];
		rsub1 = new String[N][14];
		rsub2 = new String[N][14];
		for(int i = 0; i < N;i++)
		{
			for(int j= 0; j <= W[i].length();j++)
			{
				sub1[i][j] = W[i].substring(0,j);
				sub2[i][j] = W[i].substring(j);
				rsub1[i][j] = rev(W[i].substring(0,j));
				rsub2[i][j] = rev(W[i].substring(j));
			}
		}	
		
		return recur((1<<(N))-1,0,W[0].length(),0);
	}
	int[] L;
	long[][][][] dp;
	String[][] sub1,sub2;
	String[][] rsub1,rsub2;
	public long recur(int subset, int word, int idx, int side)
	{
		if(dp[subset][word][idx][side] != -1)
			return dp[subset][word][idx][side];
		
		long ans = 0;
		if(((side == 0 && idx == L[word]) || (side == 1 && idx == 0)))
		{			
			if(subset != (1<<(N))-1) 
				ans++;
			for(int i = 0; i < N;i++)
			{
				if((subset & (1<<i)) != 0)
				{
					ans += recur(subset & (~(1<<i)), i,0,0);
				}
			}
			dp[subset][word][idx][side] = ans;
			return ans;
		}
		if(side == 0)
		{
			String s = sub2[word][idx];
			for(int i = 0; i < N;i++)
			{
				if((subset & (1<<i)) != 0)
				{
					int len = min(L[i], s.length());
					String t = rsub2[i][L[i]-len];
					
					if(t.equals(s.substring(0,len)))
					{
						if(W[i].length() > s.length())
						{
							ans += recur(subset & (~(1<<i)), i,L[i]-len,1);
						}else{
							ans += recur(subset & (~(1<<i)), word,idx+len,0);
						}
					}
				}
			}
			if(s.equals(rsub2[word][idx]) && !s.equals("")) ans++;
			dp[subset][word][idx][side] = ans;
			return ans;
		}
		else
		{
			String s = rsub1[word][idx];
			int slen = s.length();
			for(int i = 0; i < N;i++)
			{
				if((subset & (1<<i)) != 0)
				{
					int len = min(L[i], slen);
					String t = sub1[i][len];
					if(t.equals(s.substring(0,len)))
					{
						if(W[i].length() > slen)
						{
							ans += recur(subset & (~(1<<i)), i,len,0);
						}else{
							ans += recur(subset & (~(1<<i)), word,idx-len,1);
						}						
					}
				}
			}
			if(s.equals(sub1[word][idx]) && !s.equals("")) ans++;
			dp[subset][word][idx][side] = ans;
			return ans;
		}
	}
	private String rev(String s) {
		String ans = "";
		for(int i = 0; i < s.length();i++)
		{
			ans = s.charAt(i)+ans;
		}
		return ans;
	}
	public void p(Object o){System.out.println(o);}
}
