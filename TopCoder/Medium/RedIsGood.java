package TopCoder.Medium;
/* TopCoder SRM 420
 * Medium Problem 500 Points: RedIsGood
 * Type: DP
 */
public class RedIsGood {

	public double getProfit(int R, int B) {
		double[] r1 = new double[2*(R+B)];
		double[] r2 = new double[2*(R+B)];
		
		if(R ==0 && B == 0) return 0;
		r1[0] = 0;
		for(int at = 1; at <= R+B;at++)
		{
			for(int i = 0; i <= at;i++)
			{
				int red = at-i;
				int bla = i;
				double ans = 0;

				double prRed = ((double)red)/(red+bla);
				double prBl = ((double)bla)/(red+bla);
				ans = prRed*(1+r1[bla]) + prBl*((bla-1 >= 0? r1[bla-1]-1:0));
				if(ans < 0) ans = 0;
				if(red == R && bla == B) return ans;
				r2[bla] = ans;
			}
			r1 = r2;
			r2 = new double[2*(R+B)];
		}
		return -1;
	}
}
