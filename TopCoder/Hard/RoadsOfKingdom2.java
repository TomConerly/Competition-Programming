package TopCoder.Hard;
import java.util.Arrays;

/* TopCoder SRM 425
 * Medium Problem 1000 Points: RoadsOfKingdom
 * Type: Dynamic Programming
 * More details at http://forums.topcoder.com/?module=Thread&threadID=627599&start=0
 * Solution: This is the  O(3^n*n+2^n*n^2) solution their is an O(n^3) solution under RoadsOfKingdom.java
 * 
 * Keep track of the probability that a given subset is a tree. To find the probability of a subset first
 * pick two vertices arbirtraily that are in the subset, s, and f.
 * where sp[f][m] is the probability that f is connected to m through exactly one edge.
 * f[mask]=sum (for all subset m of mask, m must contain s; f[m]*f[mask-m]*sp[f][m]*(probabilty m is not connected with (mask^m^f))).
 * 
 * Run the DP and it runs barely in time.
 * The easier O(3^n*n^2) solution does not run time.
 */

public class RoadsOfKingdom2 {

	public double getProbability(String[] roads) {
		N = roads.length;
		p = new double[N][N];

		for(int i = 0; i < N;i++)
		{
			for(int j = 0; j < N;j++)
			{
				p[i][j] = (roads[i].charAt(j)-'0')/8.00;
			}
		}
		dpC = new double[1<<N][N];
		dpD = new double[1<<N][N];
		calc();
		
		dp = new double[1<<N];
		Arrays.fill(dp,Double.NaN);

		for(int subset = 1; subset < dp.length;subset++)
		{
			if(Integer.bitCount(subset) <= 1)
			{
				dp[subset] = 1.0;
			}
			else
			{
				double prob = 0;

				int s = -1;
				int f = -1;
				for(int j = 0; j < N;j++)
				{
					if((subset & (1<<j)) != 0)
					{
						if(f == -1) f = j;
						else if(s == -1) s = j;
					}
				}
				int subsetF = subset ^ (1<<f);
				
				for(int i = (subsetF);i > 0;i = (i-1)&subsetF)
				{
					if((i &(1<<s)) == 0) continue;
					double temp = 1.0;
					
					for(int j = 0; j < N;j++)
					{
						if(((subsetF - i) & (1<<j)) != 0)
							temp *= dpD[i][j];
						
					}
					
					prob += dp[i]*dp[subset - i] * temp * dpC[i][f]; 
				}
				dp[subset] = prob;
			}
		}
		
		return dp[(1<<N)-1];
	}
	public void calc() {
		for(int subset = 1; subset < (1<<N);subset++)
		{
			for(int i = 0; i < N;i++)
			{
				if((subset & (1 << i)) != 0) continue;

				double prC = 0.0;
				double prD = 1.0;
				int z=-1;
				for(int j = 0; j < N;j++)
				{
					if((subset & (1 << j)) != 0)
					{
						if(1-p[i][j] != 0)
						{
							prD *= (1-p[i][j]);
							
						}
						else
						{
							if(z == -1)
							{
								z = j;
							}
							else
							{
								z = -2;
								break;
							}
						}

					}
				}
				if(z != -1)
					dpD[subset][i] = 0;
				else
					dpD[subset][i] = prD;

				if(z == -2)
					dpC[subset][i] = 0;
				else if(z != -1)
				{
					dpC[subset][i] = prD;
				}
				else
				{
					for(int j = 0; j < N;j++)
					{
						if((subset & (1 << j)) != 0)
						{
							prC += prD*p[i][j]/(1-p[i][j]);
						}
					}
					dpC[subset][i] = prC;
				}

			}
		}
	}
	int N;
	double[] dp;
	double[][] p;
	double[][] dpC;
	double[][] dpD;
}
