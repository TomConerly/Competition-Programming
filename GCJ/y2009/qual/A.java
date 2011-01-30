package GCJ.y2009.qual;
import java.util.*;

/* Google Code Jam 2009 Qual
 * Problem A: Alien Language
 * Type: Brute Force
 * Solution: Just try all
 */

public class A {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		@SuppressWarnings("unused")
		int L = sc.nextInt();
		int D = sc.nextInt();
		int N = sc.nextInt();
		String[] dict = new String[D];
		for(int i = 0; i < D;i++)
			dict[i] = sc.next();
		for(int zz = 1; zz <= N;zz++)
		{
			String s = sc.next();
			int ans = 0;
			for(int i = 0; i < D;i++)
				if(match(dict[i],s))
					ans++;
			System.out.println("Case #"+zz+": "+ans);
		}
	}

	private static boolean match(String d, String s) {
		int at = 0;
		for(int i = 0; i < d.length();i++)
		{
			if(s.charAt(at) == '(')
			{
				boolean good = false;
				while(s.charAt(at) != ')')
				{
					if(s.charAt(at) == d.charAt(i))
						good = true;
					at++;
				}
				if(!good) return false;
				else at++;
			}
			else
			{
				if(d.charAt(i) != s.charAt(at)) return false;
				else at++;
			}
		}
		return true;
	}
}
