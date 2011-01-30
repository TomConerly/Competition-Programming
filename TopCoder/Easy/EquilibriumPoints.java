package TopCoder.Easy;
/* TopCoder SRM 421
 * Easy Problem 250 Points: EquilibriumPoints
 * Type: Binary Search
 */
public class EquilibriumPoints {

	public double[] getPoints(int[] x, int[] m) {
		double ans[] = new double[x.length-1];
		for(int i = 1; i < x.length;i++)
		{
			double bottom = x[i-1];
			double top = x[i];
			ans[i-1] = bs(bottom,top,x,m);

		}
		return ans;
	}
	public double bs(double low, double high,int[] x, int[] m)
	{
		int count = 0;
		while(true)
		{
			double mid;
			mid = (low+high)/2;
			if(count == 1000) return mid;
			int force = force(mid,x,m);
			if(force > 0)
			{
				high = mid;
			}else if(force < 0)
			{
				low=mid;
			}
			count++;
		}
	}
	public int force(double at, int[] x, int[] m)
	{
		double f = 0;
		for(int i = 0; i < x.length;i++)
		{
			if(x[i] < at)
			{
				f -= m[i]/Math.pow(x[i]-at,2);
			}
			else if( x[i] > at)
			{
				f += m[i]/Math.pow(x[i]-at,2);
			}
		}
		if( f > 0) return 1;
		else return -1;
	}

}
