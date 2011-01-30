package CMUInvitional.y2009;
import java.util.*;

/* CMU Spring Invitational 2009
 * Problem I: Revenge of the Parenthesis
 * Type: DP
 * Solution: DP[i][j] is the number of ways to make a balanced string using
 * the substring of S starting at i of length j. DP is clear from there.
 */

public class I {
	static String S;
	static int N;
	static long[][] dp;
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			S = sc.next();
			if(S.equals("0")) break;
			N = S.length();
			dp = new long[N][N+1];
			for(int i = 0; i < N;i++)
				Arrays.fill(dp[i],-1);
			long ans = recur(0,N);
			System.out.println(ans);
		}
	}
	private static long recur(int start, int len) 
	{
		if(dp[start][len] != -1) return dp[start][len];
		if(len < 2) return 0;
		if(len == 2)
		{
			if(S.charAt(start) == ')' || S.charAt(start+1)=='(') return 0;
			else return 1;
		}
		if(S.charAt(start) == ')' || S.charAt(start+len-1) == '(') return 0;
		long ans = 0;
		for(int firstLen = 0;firstLen <= len-2;firstLen++)
			ans += recur(start+1,firstLen)*recur(start+firstLen+1,len-firstLen-2);
		
		dp[start][len] = ans;
		return ans;
	}
}