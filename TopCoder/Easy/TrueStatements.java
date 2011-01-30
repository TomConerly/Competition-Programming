package TopCoder.Easy;

/* TopCoder SRM 378
 * Easy Problem 250 Points: TrueStatements
 * Type: Simulation
 * Solution: Look for n statements that say exactly n statements are true.
 */

public class TrueStatements {

	public int numberTrue(int[] s) {
		int[] count = new int[51];
		for(int a:s)
			count[a]++;
		
		int best = 0;
		for(int i = 1;i<=50;i++)
		{
			if(i == count[i]) best = i;
		}
		if(best == 0 && count[0] > 0) return -1;
		return best;
	}
	public void p(Object o){System.out.println(o);}
}
