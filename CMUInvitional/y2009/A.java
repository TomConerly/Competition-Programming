package CMUInvitional.y2009;
import java.util.Scanner;

/* CMU Spring Invitational 2009
 * Problem A: Magic Scrolls
 * Type: Brute Force
 * Solution: Just do it.
 */


public class A {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int z = sc.nextInt();
		for(int t = 0; t < z; t++)
		{
			String s = sc.next();
			boolean good = true;
			for(int i = 0; i < s.length();i++)
			{
				for(int j = i+1; j < s.length();j++)
				{
					if(Character.isUpperCase(s.charAt(i)) && Character.isLowerCase(s.charAt(j)))
					{
						char c = Character.toLowerCase(s.charAt(i));
						if(s.charAt(j) < c){
							good = false;
							break;
						}
					}
				}
			}
			if(good) System.out.println("Magical");
			else System.out.println("Not Magical");
		}
	}
}
