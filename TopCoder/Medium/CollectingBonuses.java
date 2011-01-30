package TopCoder.Medium;
/* TopCoder SRM 400
 * Hard Problem 1000 Points: CollectingBonuses
 * Type: Floating point error
 * Solution: THIS ONE DOESN'T WORK. This problem devolves into finding the difference
 * of two elements in the harmonic series. The elements can be very
 * large so we have to approximate them. The approximation I use is good
 * but we have to do more to get the error even lower. The floating point
 * subtraction on line 22 is one of the bad things. To find out what to do see:
 * http://www.topcoder.com/tc?module=Static&d1=match_editorials&d2=srm400 
 */
public class CollectingBonuses {

	public double expectedBuy(String n1, String k1) {
		double a = 0;
		long n = Long.parseLong(n1);
		long k = Long.parseLong(k1);
		for(int i = 0; i < k;i++)
		{
			a += 1/((double)(n-i));
		}
		double hn = harmonic(n);
		double hnk = harmonic(n-k);
		System.out.println((n*(hn-hnk)));
		return n*(hn-hnk);
		
	}
	
	public static double harmonic(long n)
	{
		if(n < 1<<25)
		{
			double sum = 0;
			for(int i = 1; i <= n;i++)
			{
				sum += 1.0/i; 
			}
			return sum;
		}else{
			double nt = n;
			return (.5/n)-(1/12.0)*(1/(nt*nt))+(1/120.0)*(1/(nt*nt*nt*nt))+Math.log(n)+0.57721566490153286060651209008240243104215933593992;
		}
	}
}
