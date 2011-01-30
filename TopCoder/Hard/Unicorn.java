package TopCoder.Hard;
import static java.lang.Math.*;

/* TopCoder 2009 TCO Round 1
 * Hard Problem 1000 Points: Unicorn
 * Type: DP
 * Solution: The naive DP will time out. It is O(R*C*|w|*R*C) which is way to long. We stick with the same recurence
 * dp[at][r][c] is the number of ways to match the first at characters of w ending on r,c. So given we have computed
 * all smaller we want to find the sum of the values with the prev character than can jump to r,c. Compute a dp table
 * where dp2[r][c] is the sum of all characters above and to the left of r,c. Notice the pattern of pieces than
 * can jump to r,c: where C is the center. Using the DP table we can quickly compute the sum of everything but this.
 *          XXX
 *          XXX
 *         XXXXX
 *    XXXXXXXXXXXXXXX
 *    XXXXXXXCXXXXXXX
 *    XXXXXXXXXXXXXXX
 *         XXXXX
 *          XXX
 *          XXX
 *       
 * 
 */


public class Unicorn {

	int R,C,L;
	String W;
	char[][] B;
	long M = 1000000007L;
	long[][] dp;
	long[][] dp2;
	public int countWays(int a, int b, int l, int seed, String w) {
		R = a;
		C = b;
		L = l;
		W = w;
		B = gen(seed);
		dp = new long[R][C];
		for(int r = 0; r < R;r++)
			for(int c = 0; c < C;c++)
				if(B[r][c] == w.charAt(0))
					dp[r][c] = 1;

		for(int at = 1; at < w.length();at++)
		{
			dp2 = new long[R][C];
			dp2[0][0] = dp[0][0];
			for(int r = 0; r < R;r++)
				for(int c = 0; c < C;c++)
					dp2[r][c] = l(dp2,r-1,c)+l(dp2,r,c-1)-l(dp2,r-1,c-1)+l(dp,r,c);

			long[][] ndp = new long[R][C];

			for(int r = 0; r < R;r++)
				for(int c = 0; c < C;c++)
					if(B[r][c] == w.charAt(at))
					{
						//ul
						ndp[r][c] = l(dp2,r-2,c-2)-l(dp,r-2,c-2);
						//ur
						ndp[r][c] += l(dp2,r-2,dp2[0].length-1)-l(dp2,r-2,min(c+1,dp2[0].length-1))-l(dp,r-2,c+2);
						//ll
						ndp[r][c] += l(dp2,dp2.length-1,c-2) -l(dp2,min(r+1,dp2.length-1),c-2) - l(dp,r+2,c-2);
						//lr
						ndp[r][c] += l(dp2,dp2.length-1,dp2[0].length-1) - l(dp2,dp2.length-1,min(c+1,dp2[0].length-1))
						-l(dp2,min(r+1,dp2.length-1),dp2[0].length-1) + l(dp2,min(r+1,dp2.length-1),min(c+1,dp2[0].length-1)) 
						- l(dp,r+2,c+2);
						ndp[r][c] %= M;
					}
			dp = ndp;
		}
		long ans = 0;
		for(int r = 0; r < R;r++)
		{
			for(int c = 0; c < C;c++)
			{
				ans += dp[r][c];
			}
		}

		return (int)(ans % M);
	}
	public long l(long[][] t, int r, int c)
	{
		if( r < 0 || r >= t.length || c < 0 || c >= t[0].length) return 0;
		return t[r][c];
	}
	public char[][] gen(int seed)
	{
		char[][] chess = new char[R][C];
		int x = seed;
		int d = (65535 / L)+1;
		for (int r=0; r<R; r++)
		{
			for (int c=0; c<C; c++) {
				x = (x * 25173 + 13849) % 65536;
				chess[r][c] = (char)((65+(x / d)));
			}
		}
		return chess;
	}

}
