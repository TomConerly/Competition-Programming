package SSU;
import java.util.*;

/* Saratov State University Online Judge
 * Problem 461: Wiki Lists
 * Type: Recursion
 * Solution: The thing is recursively defined just to the obvious.
 */

public class p461 {
	public static void main(String[] args)
	{
		ArrayList<String> input = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextLine())
			input.add(sc.nextLine());
		S = new String[input.size()];
		input.toArray(S);
		print(0,S.length-1,0);
	}
	private static void print(int st, int en,int dep) {
		int at = st;
		while(at <= en)
		{
			int size = getsize(at,st,en,dep);
			if(dep > 0)
			{
				System.out.println("<li>");
			}
			if(size == 1)
			{
				System.out.println(S[at].substring(dep));
			}
			else
			{
				if(S[at].charAt(dep) == '#')
				{
					System.out.println("<ol>");
					print(at,at+size-1,dep+1);
					System.out.println("</ol>");
				}else{					
					System.out.println("<ul>");
					print(at,at+size-1,dep+1);
					System.out.println("</ul>");					
				}
			}
			if(dep > 0)
			{
				System.out.println("</li>");
			}
			at += size;
		}
	}
	private static int getsize(int at, int st, int en, int dep) {
		int size = 1;
		for(int i = at+1;i <= en;i++)
		{
			if(S[at].charAt(dep) == S[i].charAt(dep) && (S[at].charAt(dep) == '*' || S[at].charAt(dep) == '#'))
				size++;
			else break;
		}
		return size;
	}
	static String[] S;
}
