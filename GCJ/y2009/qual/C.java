package GCJ.y2009.qual;
import java.util.*;

/* Google Code Jam 2009 Round Qual
 * Problem C: Welcome to Code Jam
 * Type: DP
 * Solution: Standard dp.
 */

public class C 
{
	static String t = "welcome to code jam";
	static String s;
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.nextLine();
		for(int zz = 1; zz <= N;zz++)
		{
			
			s = sc.nextLine();
			dp = new int[s.length()][t.length()+1];
			for(int i = 0; i < dp.length;i++)
				Arrays.fill(dp[i],-1);
			System.out.format("Case #%d: %04d\n",zz,recur(0,0));
		}
	}
	private static int recur(int at, int match) {
		//System.out.println(at+" "+match);
		if(at == s.length())
		{
			if(match == t.length()) return 1;
			else return 0;
		}
		if(dp[at][match] != -1) return dp[at][match];
		
		int ans = recur(at+1,match);
		if(match < t.length() && s.charAt(at) == t.charAt(match))
			ans += recur(at+1,match+1);
		ans %= 10000;
		dp[at][match] = ans;
		return ans;
	}
	static int[][] dp;
	
}
