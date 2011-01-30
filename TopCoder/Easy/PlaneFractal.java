package TopCoder.Easy;

/* TopCoder 2009 TCO Round 2
 * Easy Problem 250 Points: PlaneFractal
 * Type: Simulation
 * Solution: Just need to make a function which asks if a point is black or white. That function
 * is not terribly hard to write see cov.
 */
public class PlaneFractal {

	int N,K,S;
	public String[] getPattern(int s, int n, int k, int r1, int r2, int c1, int c2) {
		N = n;
		K = k;
		S = s;
		String[] ans = new String[r2-r1+1];
		for(int r = r1;r<=r2;r++)
		{
			ans[r-r1] = "";
			for(int c = c1;c<=c2;c++)
				ans[r-r1] = ans[r-r1]+(cov(r,c)?"1":"0");
		}
		return ans;
	}
	public boolean cov(int r, int c)
	{
		long len = (long)Math.pow(N,S);
		for(int i = 0; i < S;i++)
		{
			long temp = len / N;
			long dr = (r / temp) - (N-K)/2;
			long dc = (c / temp) - (N-K)/2;
			if(0<= dr && dr < K && 0 <= dc &&  dc < K) return true;
			
			len /= N;
			r %= len;
			c %= len;
		}
		return false;
	}
}
