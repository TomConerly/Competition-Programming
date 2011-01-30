package TopCoder.Hard;
import static java.lang.Math.*;

/* TopCoder SRM 395
 * Hard Problem 950 Points: PubTrivia
 * Type: DP
 * Solution: A fairely standard DP.
 */
public class PubTrivia {

	int N,T;
	long[] P,B;
	public long maximumScore(int n, int tokensNeeded, int[] p, int[] c) {
		N = n;
		T = tokensNeeded;
		P = create(p,1001);
		B = create(c,10001);
		dp = new long[N][2];		
		long sum = 0;
		S = new long[N];
		for(int i = 0; i < N;i++)
		{
			sum += P[i];
			S[i] = sum;
		}
		dp[N-1][0] = P[N-1]+(tokensNeeded == 1?B[N-1]:0);
		dp[N-1][1] = P[N-1];

		for(int at = N-2; at >= 0;at--)
		{
			for(int r = 0; r < 2;r++)
			{
				long a,b,d;
				if(r == 0)
				{
					a = P[at]+dp[at+1][1];
					if(T+at-1 < N)
					{
						b = S[T+at-1];
						if(at > 0) b -= S[at-1];
						b += B[T+at-1];
						if(at + T < N)
							b += dp[at+T][0];					
					}else b = 0;
					d = dp[at+1][0]-P[at];
					
					b = max(b,d);
				}else{
					a = P[at]+dp[at+1][1];
					b = dp[at+1][0]-P[at];
				}
				dp[at][r] = max(a,b);
			}
		}
		return dp[0][0];
	}
	long[][] dp;
	long[] S;
	public long[] create(int[] X, int G)
	{
		int k = 0;
		int M = X.length;
		int s;
		long[] P = new long[N];
		for(int i = 0; i < N;i++)
		{
			P[i] = X[k];
			s = (k+1)%M;
			X[k] = ((X[k]^X[s])+13)%G;
			k = s;
		}
		return P;
	}
}
