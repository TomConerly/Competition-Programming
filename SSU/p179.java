package SSU;
import java.util.*;

/* Saratov State University Online Judge
 * Problem 179: Brackets light
 * Type: Greedy
 * Solution: Search from right to left for the first time you see a ( which you can replace with ) then all left followed by all right.
 * Take the first.
 */

public class p179 {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		String S = sc.next();
		int N = S.length();
		int[] V = new int[N];
		for(int i = 1; i < S.length();i++)
		{
			V[i] = V[i-1] + (S.charAt(i-1) == '('? 1:-1);
		}
		int j;
		boolean found = false;

		for( j=S.length()-1;j>=0;j--)
		{
			if(S.charAt(j) == '(')
			{
				int left = S.length()-j-1;
				int need = V[j]-1;
				if(need >= 0 && left >= need && (left-need)%2 == 0)
				{
					found = true;
					break;
				}
			}		
		}
		if(found)
		{
			String ans = S.substring(0,j);
			ans += ')';
			int left = S.length()-j-1;
			int need = V[j]-1;

			int open = (left-need)/2;
			int close = (left-need)/2 + need;
			for(int i = 0; i < open;i++)
				ans += '(';
			for(int i = 0; i < close;i++)
				ans += ')';
			System.out.println(ans);
		}else{
			System.out.println("No solution");
		}
	}
}
