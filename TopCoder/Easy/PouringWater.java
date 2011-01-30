package TopCoder.Easy;
/* TopCoder SRM 439
 * Easy Problem 250 Points: PouringWater
 * Type: Simulation
 * Solution: Return of -1 never happens. The number of bottles you can reduce something
 * to is the number of bits that are on. The worst case isn't that bad O(10^7) so
 * just try until you find one.
 */

public class PouringWater {
	public int getMinBottles(int N, int K) {
		for(int i = N;;i++)		
			if(Integer.bitCount(i) <= K) 
				return i-N;		
	}
}
