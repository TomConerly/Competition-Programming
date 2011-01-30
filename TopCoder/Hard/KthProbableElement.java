package TopCoder.Hard;

import java.util.*;

/* TopCoder 2009 TCO Round 1
 * Medium Problem 500 Points: KthProbableElement
 * Type: DP
 * Solution: DP over how many we have picked, how many less than the number
 * and how many equal to the number.
 */

public class KthProbableElement {

	int M,U,L,N,K;
	public double probability(int m, int lowerBound, int upperBound, int n, int k) {
		M = m;
		dp = new double[M][M+10][M+10];
		U = upperBound;
		L = lowerBound;
		N = n;
		K = k;
		for(int i = 0; i < dp.length;i++)
			for(int j = 0; j < dp[0].length;j++)
				Arrays.fill(dp[i][j],Double.MAX_VALUE);
		return recur(0,0,0);
	}
	double dp[][][];
	public double recur(int at, int numLess,int have)
	{
		if(at == M)
		{
			if(K - numLess <= 0) return 0.0;
			int d = K-numLess;
			if(d <= have && have > 0){
			
				return 1.0;
			}
			return 0.0;
		}
		
		if(dp[at][numLess][have] != Double.MAX_VALUE) return dp[at][numLess][have];
		double low = (N - L)/(U-L+1.0);
		double high = (U-N)/(U-L+1.0);
		double mid = 1.0 / (U-L+1.0);
		
		double ans = 0;
		ans += low * recur(at+1,numLess+1,have);
		ans += mid * recur(at+1,numLess,have+1);
		ans += high * recur(at+1,numLess,have);
		//System.out.println(low+" "+high+" "+mid);
		dp[at][numLess][have] = ans;
		return ans;
	}
	public void p(String s){System.out.println(s);}

}
