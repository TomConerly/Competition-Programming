package TopCoder.Hard;
import java.util.*;
import static java.lang.Math.*;

/* TopCoder SRM 460
 * Hard Problem 950 Points: FencingGarden
 * Type: Meet in the middle
 * Solution: If you have a subset of the fences you can always match them to the same length with one cut. So if you take away some subset for long side
 * you can always make two short sides. Also if you take away a subset for a short side you can always use a cut to match the short side and make a long
 * side with the remaining. The optimal size is long side has half, short sides have a quarter. So do subset sum to find closest to 1/4 and 1/2 you can
 * get. 
 * 
 * Subset sum algo: Seperate in half. For each half find sums of all subsets and sort them. Now we start with first half
 * and remove one subset from it and add a subset from the other side. Sort sizes of subsets go through both in increasing
 * order. If your "guess" is to low then go to a larger second subset, otherwise increase first subset.
 * 
 * 
 */

public class FencingGarden {
	public void p(Object...s){System.out.println(Arrays.deepToString(s));}
	public long computeWidth(int[] S) {
		for(int i = 0; i < S.length;i++)
			S[i] *= 2;
		
		int mid = (S.length-1)/2;
		long[] s1 = get(S,0,mid);
		long[] s2 = get(S,mid+1,S.length-1);
		Arrays.sort(s1);
		Arrays.sort(s2);
		long sum1 = 0;
		
		for(int i = 0; i <= mid;i++)
			sum1 += S[i];
		long sum2 = 0;		
		for(int j = mid+1;j < S.length;j++)
			sum2 += S[j];
		
		long total = sum2+sum1;
		long goal = total/2;
		long best = 0;
		int s1A = 0;
		int s2A = 0;
		while(true)
		{
			long can = sum1-s1[s1A]+s2[s2A];
			long score = abs(goal-can);
			if(score < abs(best-goal) || (score == abs(best-goal) && can > best))			
				best = can;
			
			if(can > goal){
				s1A++;
				if(s1A == s1.length)
					break;
			}else{
				s2A++;
				if(s2A == s2.length)
					break;
			}
		}
		s1A = 0;
		s2A = 0;
		while(true)
		{
			long can = total - (sum1-s1[s1A]+s2[s2A])*2;
			long score = abs(goal-can);
			if(score < abs(best-goal) || (score == abs(best-goal) && can > best))			
				best = can;
			
			if(can < goal){
				s1A++;
				if(s1A == s1.length)
					break;
			}else{
				s2A++;
				if(s2A == s2.length)
					break;
			}
		}
		return best/2;
	}
	private long[] get(int[] s, int st, int en) {
		long[] pos = new long[(1<<(en-st+1))];
		for(int i = 1; i < (1<<(en-st+1));i++)
		{
			int j = Integer.numberOfTrailingZeros(i);
			pos[i] = pos[i ^ (1<<j)] + s[j+st];
		}
		return pos;
	}
}