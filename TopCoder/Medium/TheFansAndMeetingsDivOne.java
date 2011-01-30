package TopCoder.Medium;
/* TopCoder SRM 460
 * Medium Problem 500 Points: TheFansAndMeetingsDivOne
 * Type: DP
 * Solution: Use DP to find the probability that each person gets exactly k fans. DP is DP[city at][cities left to use][fans left to get exactly k].
 */
import java.math.BigInteger;

public class TheFansAndMeetingsDivOne {
	public void p(Object s){System.out.println(s);}
	public double find(int[] minJ, int[] maxJ, int[] minB, int[] maxB, int k) {
		DP1 = new double[40+1][40*40+1][40+1];
		DP2 = new double[40+1][40*40+1][40+1];
		DP1[minJ.length][0][0] = 1.0;
		DP2[minJ.length][0][0] = 1.0;
		
		for(int at = minJ.length-1;at >= 0;at--)
		{
			for(int left = 0; left <= 40*40;left++)
			{
				for(int need = 0; need <= 40;need++)
				{
					double ans = 0;
					for(int i = minJ[at];i<=maxJ[at];i++)
						if(left-i >= 0 && need-1 >= 0)
							ans += DP1[at+1][left-i][need-1];
					ans /= maxJ[at]-minJ[at]+1;
					ans += DP1[at+1][left][need];
					DP1[at][left][need] = ans;
				}
			}
		}
		for(int at = minB.length-1;at >= 0;at--)
		{
			for(int left = 0; left <= 40*40;left++)
			{
				for(int need = 0; need <= 40;need++)
				{
					double ans = 0;
					for(int i = minB[at];i<=maxB[at];i++)
						if(left-i >= 0 && need-1 >= 0)
							ans += DP2[at+1][left-i][need-1];
					ans /= maxB[at]-minB[at]+1;
					ans += DP2[at+1][left][need];
					DP2[at][left][need] = ans;
				}
			}
		}
		
		double ans = 0;
		BigInteger choose = BigInteger.ONE;
		for(int i = 1; i <= minJ.length;i++)
			choose = choose.multiply(BigInteger.valueOf(i));
		for(int i = 1; i <= k;i++)
			choose = choose.divide(BigInteger.valueOf(i));
		for(int i = 1; i <= minJ.length-k;i++)
			choose = choose.divide(BigInteger.valueOf(i));
		long c = choose.longValue();
		for(int i = 0; i <= 40*40;i++)
		{
			ans += DP1[0][i][k]/c*DP2[0][i][k]/c;
		}
		return ans;
	}
	double DP1[][][];
	double DP2[][][];
	
}
