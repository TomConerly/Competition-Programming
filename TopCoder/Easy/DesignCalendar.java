package TopCoder.Easy;
/* TopCoder SRM 427
 * Easy Problem 250 Points: DesignCalendar
 * Type: Number Theory
 * Solution: Find the difference between year length
 * and the smaller real year length we will use.
 * We want this difference to be some number of days.
 * We can uses the gcd to compute this number.
 * (There are many other ways to solve this)
 */
public class DesignCalendar {

	public int shortestPeriod(int dayLength, int yearLength) {
		int low = yearLength/dayLength;
		
		int t = yearLength-low*dayLength;
		
		int g = gcd(t,dayLength);
		
		return dayLength/g;
	}
	int gcd(int a, int b) 
	{ 
	   return ( b == 0 ? a : gcd(b, a % b) ); 
	}


}
