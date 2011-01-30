package TopCoder.Easy;
import static java.lang.Math.*;
/* TopCoder SRM 396
 * Easy Problem 250 Points: DNAString
 * Type: Simulation
 * Solution: Straight forward.
 */
public class DNAString {

	public int minChanges(int maxPeriod, String[] dna) {
		String s = "";
		for(String a:dna)
			s += a;
		
		int best = Integer.MAX_VALUE;
		for(int period = 1; period <= maxPeriod;period++)
		{
			int t = change(s,period);
			//System.out.println(period+" "+t);
			best = min(best,t);
		}
		return best;
	}

	private int change(String s, int period) {
		int ops = 0;
		for(int i = 0; i < period;i++)
		{
			int a=0,c=0,g=0,t=0;
			for(int j = i; j < s.length();j+= period)
			{
				if(s.charAt(j) == 'A') a++;
				if(s.charAt(j) == 'C') c++;
				if(s.charAt(j) == 'G') g++;
				if(s.charAt(j) == 'T') t++;
			}
			//System.out.println("\t"+period+" "+i+" "+a+" "+c+" "+g+" "+t);
			ops += a+c+g+t - max(max(a,c),max(g,t));
		}
		return ops;
	}

}
