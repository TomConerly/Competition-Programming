package TopCoder.Easy;
/* TopCoder 2008 TCO Round 3
 * Easy Problem 250 Points: ZenoDivision
 * Type: Math
 * Solution: Try all lengths, for a given length you need the proportion to be correct. Let
 * x be the proportion sent to you, then x/(x+y) needs to equal the fraction. We know what x+y
 * equals so we can determine x, and see if it can be represented using the numbers we have 
 * access to given this length.
 */
public class ZenoDivision {

	public String cycle(String a1, String b1) {
		long a = Long.parseLong(a1);
		long b = Long.parseLong(b1);
		
		for(long len = 1L;len <= 60;len++)
		{
			long total = (1L << (len))-1L;
			if(total % b != 0) continue;
			
			long top = a*(total/b);
			if(top == (top << (64-len))>>>(64-len))
			{
				String ans = "";
				for(long i = len-1L;i>=0;i--)
				{
					if((top&(1L<<i)) != 0) ans += "*";
					else ans += "-";
				}
				return ans;
			}
		}
		
		return "impossible";
	}
	public void p(Object o){System.out.println(o);}
}
