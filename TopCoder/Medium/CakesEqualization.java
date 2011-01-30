package TopCoder.Medium;

/* TopCoder SRM 421
 * Medium Problem 500 Points: CakesEqualization
 * Type: Greedy
 */
public class CakesEqualization {

	public double fairDivision(int[] weights, int maxCuts) {
		double[] w = new double[weights.length];
		int[] cuts = new int[weights.length];
		for(int i = 0; i < w.length;i++)
		{
			w[i]=weights[i];
		}
		double bestScore = Double.MAX_VALUE;
		for(int k = 0; k <= maxCuts;k++)
		{
			double max = 0;
			int at = 0;
			for(int i = 0; i < w.length;i++)
			{
				if(w[i] > max) {
					max = w[i];
					at = i;
				}
			}
			double min = Double.MAX_VALUE;
			for(double d: w) min = Math.min(min,d);
			if(max-min < bestScore) bestScore = max-min;
			
			w[at] = weights[at]/(cuts[at]+2.0);
			cuts[at]++;
		}
		return bestScore;
	}

}
