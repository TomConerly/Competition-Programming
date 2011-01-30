package TopCoder.Easy;
import static java.lang.Math.*;
/* TopCoder SRM 392
 * Easy Problem 250 Points: TwoStringMasks
 * Type: Simulation
 * Solution: Remove equal strings off the start and end such that it is of the form
 * (*A) and (B*) then find the largest overlap of A and B and you are done.
 */
public class TwoStringMasks {
	String im = "impossible";
	public String shortestCommon(String s1, String s2) {
		String a1 = s1.substring(0,s1.indexOf("*"));
		String b1 = s2.substring(0,s2.indexOf("*"));
		String a2 = s1.substring(s1.indexOf("*")+1);
		String b2 = s2.substring(s2.indexOf("*")+1);
		
		String ans = "";
		for(int i = 0; i < min(a1.length(),b1.length());i++)
		{
			if(a1.charAt(i) != b1.charAt(i)) return im;
			ans += a1.charAt(i);
		}
		String ansBack = "";
		String a2t = reverse(a2);
		String b2t = reverse(b2);
		for(int i = 0; i < min(a2t.length(),b2t.length());i++)
		{
			if(a2t.charAt(i) != b2t.charAt(i)) return im;
			ansBack =  a2t.charAt(i)+ansBack;
		}
		
		int lena = min(a1.length(),b1.length());
		a1 = a1.substring(lena);
		b1 = b1.substring(lena);
		lena = min(a2t.length(),b2t.length());
		a2t = a2t.substring(lena);
		b2t = b2t.substring(lena);
		if(a1.length() == 0 && a2t.length() == 0) return ans+b1+reverse(b2t)+ansBack;
		if(b1.length() == 0 && b2t.length() == 0) return ans+a1+reverse(a2t)+ansBack;
		
		if(a2t.length() == 0&& b1.length() == 0)
		{
			b2 = reverse(b2t);
			String a=ans+a1+b2+ansBack;
			for(int i = 0; i < min(a1.length(),b2.length());i++)
			{
				if(a1.substring(a1.length()-i-1).equals(b2.substring(0,i+1)))
				{
					a = ans+a1+b2.substring(i+1)+ansBack;
				}
			}
			return a;
		}else{
			a2 = reverse(a2t);
			String a= ans+b1+a2+ansBack;
			for(int i = 0; i < min(a2.length(),b1.length());i++)
			{
				if(b1.substring(b1.length()-i-1).equals(a2.substring(0,i+1)))
				{
					a = ans+b1+a2.substring(i+1)+ansBack;
				}
			}
			return a;
		}
	}
	
	private String reverse(String b2) {
		String n = "";
		for(int i = 0; i < b2.length();i++)
		{
			n = b2.charAt(i)+n;
		}
		return n;
	}


}
