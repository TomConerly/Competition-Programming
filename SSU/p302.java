package SSU;
import java.util.*;

/* Saratov State University Online Judge
 * Problem 302: BHTML 1.0
 * Type: Simulation
 * Solution: Simulate it.
 */

public class p302 {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		Stack<Integer> state = new Stack<Integer>();
		String ans = "";
		while(s.length() > 0)
		{
			if(s.length() >= 4 && s.substring(0,4).equals("<UP>"))
			{
				state.push(1);
				s = s.substring(4);
				continue;
			}
			if(s.length() >= 6 && s.substring(0,6).equals("<DOWN>"))
			{
				state.push(2);
				s = s.substring(6);
				continue;
			}
			if(s.length() >= 5 && s.substring(0,5).equals("</UP>"))
			{
				state.pop();
				s = s.substring(5);
				continue;
			}
			if(s.length() >= 7 && s.substring(0,7).equals("</DOWN>"))
			{
				state.pop();
				s = s.substring(7);
				continue;
			}
			int st = state.size() > 0? state.peek():0;
			String add = s.substring(0,1);
			s = s.substring(1);
			if(st == 1)
			{
				add = add.toUpperCase();
			}else if(st == 2)
			{
				add = add.toLowerCase();
			}
			ans = ans+add;
		}
		System.out.println(ans);
	}
}
