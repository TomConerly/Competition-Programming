package TopCoder.Easy;
import java.util.*;
import static java.lang.Math.*;

/* TopCoder TCC07 Round 3
 * Easy Problem 250 Points: LazyCat
 * Type: Greedy
 * Solution: For each one compute the last time you can hit it.
 * You always want to hit the thing that is leaving first with 
 * your first shot.s
 */

public class LazyCat {

	public int maxMiceCount(int[] P, int[] S, int d, int[] R) 
	{
		int N = P.length;
		int M = R.length;
		int[] L = new int[N];
		for(int i = 0; i < N;i++)
		{
			L[i] = (int)floor((d-P[i])/(S[i]+0.0));
		}
		Arrays.sort(L);
		Arrays.sort(R);
		int count = 0;
		int time = 0;
		int j = 0;
		for(int i = 0; i < N && j < M;i++)
		{
			if(time > L[i]) continue;
			time += R[j++];
			count++;
		}
		return count;
	}
	public void p(Object o){System.out.println(o);}
}
