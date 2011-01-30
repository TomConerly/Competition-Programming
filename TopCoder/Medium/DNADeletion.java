package TopCoder.Medium;
import java.util.*;
import static java.lang.Math.*;

/* TopCoder SRM 435
 * Medium Problem 250 Points: DNADeletion
 * Type: DP
 * Solution: We can first DP over how many different ways to 
 * do the substring i... to the end. To compute this we need to 
 * find the first occurance in i...end of each option. To do this
 * we use match (which is memoized). Match can quickly compute this 
 * becuase for each position and each character we store the location
 * of the first instance of that character after the given position.
 */

public class DNADeletion {

	long M = 1000000007L;
	String d;
	ArrayList<ArrayList<Integer>> toCheck;
	String[] c;
	String[] a;
	int N;
	public int differentProteins(String[] DNA, String[] ct) {
		for(String s: ct)
		{
			System.out.print(s+",");
		}
		toCheck = new ArrayList<ArrayList<Integer>>();				
		a = new String[ct.length];
		c = new String[ct.length];
		for(int i = 0; i < a.length;i++)
		{
			String[] s = ct[i].split(" ");
			c[i] = s[0];
			boolean found = false;
			for(int j = 0; j < i;j++)
			{
				if(s[1].equals(a[j]))
				{
					found = true;
					toCheck.get(j).add(i);
				}
			}
			if(!found){
				toCheck.add(new ArrayList<Integer>());
				toCheck.get(toCheck.size()-1).add(i);
				a[toCheck.size()-1] = s[1];
			}
		}
		N = toCheck.size();
		d = "";
		for(String s: DNA)d+=s;
		dp = new long[d.length()+1];
		dp2 = new int[c.length][d.length()+1];

		next = new int[d.length()+1][4];
		for(int j = 0; j < 4;j++)
		{
			int prev = 0;
			for(int i = 0; i < d.length();i++)
			{
				if(d.charAt(i) == toC(j))
				{
					for(int k = prev;k<=i;k++)
					{
						next[k][j] = i+1;
					}
					prev = i+1;
				}
			}	
			for(int k = prev;k<next.length;k++)
			{
				next[k][j] = -1;
			}
		}

		for(int i =0; i < c.length;i++)
			Arrays.fill(dp2[i],-1);
		Arrays.fill(dp,-1);
		return (int)recur(0);
	}
	int[][] next;
	int to(char c)
	{
		if(c == 'A') return 0;
		if(c == 'C') return 1;
		if(c == 'G') return 2;
		if(c == 'T') return 3;
		return -1;
	}
	char toC(int c)
	{
		if(c == 0) return 'A';
		if(c == 1) return 'C';
		if(c == 2) return 'G';
		if(c == 3) return 'T';
		return ' ';
	}
	long[] dp;
	private long recur(int at) {
		if(dp[at] != -1) return dp[at];
		long ans = at != 0 ? 1:0;
		for(int i = 0; i < N;i++)
		{
			int min = Integer.MAX_VALUE;
			for(int a: toCheck.get(i))
				min = min(min,match(a,at));
			if(min != Integer.MAX_VALUE)
				ans = (ans + (recur(min)))%M;
		}
		dp[at] = ans%M;
		return ans%M;
	}
	int[][] dp2;
	int match(int t, int start)
	{
		if(dp2[t][start] != -1) return dp2[t][start];
		String a = c[t];
		int at = start;	
		
		for(int i = 0; i < a.length();i++)
		{
			at = next[at][to(a.charAt(i))];
			if(at == -1) 
			{
				at = Integer.MAX_VALUE;
				break;
			}
		}
		dp2[t][start] = at;		
		return at;
	}
}
