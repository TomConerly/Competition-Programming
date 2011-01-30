package SSU;
import java.util.*;

/* Saratov State University Online Judge
 * Problem 460: Plural Form of Nouns
 * Type: Simulation
 * Solution: Boring.
 */

public class p460 {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		for(int i = 0; i < N;i++)
		{
			String s = sc.next();
			if(s.endsWith("ch") || s.endsWith("x") || s.endsWith("s") || s.endsWith("o"))
				s += "es";
			else if(s.endsWith("f"))
				s = s.substring(0,s.length()-1)+"ves";
			else if(s.endsWith("fe"))
				s = s.substring(0,s.length()-2)+"ves";
			else if(s.endsWith("y"))
				s = s.substring(0,s.length()-1)+"ies";
			else
				s += "s";
			System.out.println(s);

		}
	}
}
