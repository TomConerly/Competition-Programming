package TopCoder.Easy;
import static java.lang.Math.*;
/* TopCoder SRM 389
 * Easy Problem 250 Points: ApproximateDivision
 * Type: Simulation
 * Solution: Just do it.
 */
public class ApproximateDivision {

	public double quotient(int a, int b, int terms) {
		int t = 1;
		while(t*2 < b)
			t*=2;
		if(b > 1)
			t*=2;
		double ans = 0.0;
		for(int i = 0; i < terms;i++)
		{
			ans += pow(t-b,i)/pow(t,i+1);
		}
		return ans*a;
	}
	
	void print(int[] a)
	{
		for(int b:a)
			System.out.print(b+" ");
		System.out.println();
	}

}
