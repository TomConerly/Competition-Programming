package SPOJ;
import java.util.Scanner;

/* SPOJ Problem 5: PALIN
 * Type: Math
 * Solution: Make the palindrome that matches the first half to itself
 * then tweak it to make it larger. Watch out for timeouts using strings
 * so use StringBuilder for those operations.
 */
public class PALIN {
	static void p(Object o){ System.out.println(o);}
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int zz = sc.nextInt();
		for(int z = 0; z < zz;z++)
		{
			String s = sc.next();

			String ans = s.substring(0,s.length()/2);
			if(s.length()%2 == 1)
			{
				ans = ans + s.charAt((s.length()/2))+rev(ans);
			}else
				ans += rev(ans);
			if(larger(ans,s))
			{
				System.out.println(ans);
			}else{
				boolean done = false;
				for(int i = (s.length()-1)/2;i>=0;i--)
				{
					if(ans.charAt(i) != '9')
					{
						done = true;
						if(i == s.length()/2 && s.length()%2 == 1)
						{
							ans = ans.substring(0,s.length()/2)+((char)(ans.charAt(i)+1))+ans.substring(s.length()/2+1);
						}else{
							if(s.length()%2 == 1)
							{
								ans = ans.substring(0,i)+((char)(ans.charAt(i)+1))+zeros(s.length()/2 - i-1);
								ans = ans+"0"+rev(ans);
							}else{
								ans = ans.substring(0,i)+((char)(ans.charAt(i)+1))+zeros(s.length()/2 - i-1);
								ans = ans+rev(ans);
							}
						}
						break;
					}
				}
				if(done) System.out.println(ans);
				else
				{
					ans = "1";
					while(ans.length() < s.length())
						ans +="0";
					ans += "1";
					System.out.println(ans);
				}
			}
		}
	}

	private static String zeros(int i) {
		StringBuilder z = new StringBuilder();
		for(int j = 0; j < i;j++)
			z.append("0");
		return z.toString();
	}
	private static boolean larger(String ans, String s) {
		for(int i = 0; i < ans.length();i++)
		{
			char a = ans.charAt(i);
			char b = s.charAt(i);
			if(a < b) return false;
			if(a > b) return true;
		}
		return false;
	}

	private static String rev(String ans) {
		StringBuilder st = new StringBuilder(ans);
		return st.reverse().toString();
	}
}
