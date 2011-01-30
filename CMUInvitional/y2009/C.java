package CMUInvitional.y2009;
import java.util.*;

/* CMU Spring Invitational 2009
 * Problem C: Matching Bookends
 * Type: Search
 * Solution: Just test it.
 */

public class C {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			String s = sc.next();
			if(s.equals("0")) break;
			int sum = 0;
			boolean one = false;
			boolean good = true;
			for(int i = 0; i < s.length();i++)
			{
				char c = s.charAt(i);
				if(c == '(')
				{
					one = true;
					sum++;
				}else if(c == ')')
				{
					one = true;
					sum--;
				}
				if(sum < 0) 
				{
					good = false;
				}
			}
			if(sum == 0 && one == true && good == true)
				System.out.println("Compliant");
			else System.out.println("Not Compliant");
		}
	}
}
