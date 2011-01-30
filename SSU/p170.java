package SSU;
import java.util.*;
import static java.lang.Math.*;

/* Saratov State University Online Judge
 * Problem 170: Particles
 * Type: Greedy
 * Solution: You know you want to pair up the the first + on the first string, to the first + on the second string. I claim you can always do it in the abs
 * of the distance between.
 */

public class p170 {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		String A = sc.next();
		String B = sc.next();
		ArrayList<Integer> ai = new ArrayList<Integer>();
		ArrayList<Integer> bi = new ArrayList<Integer>();
		for(int i = 0; i < A.length();i++)
		{
			if(A.charAt(i) == '+')
				ai.add(i);
			if(B.charAt(i) == '+')
				bi.add(i);
		}
		if(ai.size() != bi.size())
		{
			System.out.println("-1");
			return;
		}
		int ans = 0;
		for(int i = 0; i < ai.size();i++)
		{
			ans += abs(ai.get(i)-bi.get(i));
		}
		System.out.println(ans);
		
	}
}
