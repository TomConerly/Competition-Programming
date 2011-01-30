package TopCoder.Medium;
import java.util.*;
import static java.lang.Math.*;

/* TopCoder TCO09 Round 3
 * Medium Problem 500 Points: CampaignTrail
 * Type: DP
 * Solution: DP is [num votes so far][states decided][visits left].
 */
public class CampaignTrail {

	int[] w1,w2,e;
	int need;
	public double probWin(int[] electors, int[] winCurrent, int[] winIfVisited, int visits) {
		w1 = winCurrent;
		w2 = winIfVisited;
		e = electors;
		int total = 0;
		for(int a:e) total += a;
		need = (int)ceil((total+1)/2.0);
		dp = new double[electors.length*50+1][electors.length][visits+1];
		for(int i = 0; i < dp.length;i++)
		{
			for(int j = 0; j < dp[0].length;j++)
				Arrays.fill(dp[i][j],-1);
		}
		return recur(0,0,visits);
	}
	private double recur(int votes, int at, int visits) {
		if(votes >= need) return 1.0;
		if(at == e.length){			
			return 0.0;
		}
		if(dp[votes][at][visits] != -1) return dp[votes][at][visits];

		double ans = ((w1[at]/100.0)*recur(votes+e[at],at+1,visits))+((1-(w1[at]/100.0))*recur(votes,at+1,visits));
	
		if(visits > 0)
		{
			ans = max(ans,((w2[at]/100.0)*recur(votes+e[at],at+1,visits-1))+((1-(w2[at]/100.0))*recur(votes,at+1,visits-1)));				
			
		}
		dp[votes][at][visits] = ans;
		return ans;
	}
	double[][][] dp;
	public void p(Object o){System.out.println(o);}
}
