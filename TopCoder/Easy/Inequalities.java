package TopCoder.Easy;
/* TopCoder SRM 459
 * Easy Problem 250 Points: Inequalities
 * Type: Simulation
 * Solution: Notice that the possible values for an answer are either an integer given, or 0.5 above or below it. Try all of those.
 */

import java.util.*;
import static java.lang.Math.*;

public class Inequalities {
	public void p(Object s){System.out.println(s);}
	public int maximumSubset(String[] S) {
		TreeSet<Double> ts = new TreeSet<Double>();
		String[] op = new String[S.length];
		int[] V = new int[S.length];
		for(int i = 0; i < S.length;i++)
		{
			String s = S[i];
			StringTokenizer st = new StringTokenizer(s);
			st.nextToken();
			op[i] = st.nextToken();
			V[i] = Integer.valueOf(st.nextToken());
			ts.add(V[i]+0.);
			ts.add(V[i]+0.5);
			ts.add(V[i]-0.5);
		}
		int best = 0;
		for(double v:ts)
		{
			int count = 0;
			for(int i = 0; i < S.length;i++)
			{
				if(op[i].equals("<") && v < V[i])
					count++;
				if(op[i].equals("<=") && v <= V[i])
					count++;
				if(op[i].equals("=") && v == V[i])
					count++;
				if(op[i].equals(">") && v > V[i])
					count++;
				if(op[i].equals(">=") && v >= V[i])
					count++;
			}
			best = max(best,count);
		}
		return best;
	}
	
}
