package TopCoder.Hard;

/* TopCoder SRM 434
 * Hard Problem 1000 Points: IncreasingLists
 * Type: Brute Forceish
 * Solution: In the string with 50 ?s there are only
 * ~50,000 to legally place the commas (observing the rule
 * that commas have to become more spaced out for increasing
 * sequence). So place on the commas then minimize and take the best.
 */

public class IncreasingLists {

	String best = null;
	int N;
	public String makeIncreasingList(String mask) {		
		N = mask.length();
		if(mask.charAt(0) == ',' || mask.charAt(mask.length()-1) == ',') return "impossible";
		recur(0,1,mask);
		return best != null?best:"impossible";
	}
	void recur(int at, int d, String s)
	{
		if(at == N)
		{
			fill(s);
			return;
		}
		if(at > 0 && at == N-1) return;
		if(s.charAt(at) == '?' && at != 0)
			s = s.substring(0,at)+","+s.substring(at+1);
		if(at != 0 && d == 1) d = 2;
		
		for(int i = d; i+at<=N;i++)
		{
			if(i+at < N && s.charAt(at+i) != '?' && s.charAt(at+i) != ',') continue;	
			for(int j = at+1;j<i+at;j++)			
				if(s.charAt(j) == ',') continue;			
			recur(at+i,i,s);
		}
	}
	
	void fill(String s )
	{
		String last = "0";
		String test = "";
		int prev = 0;
		for(int j = 1; j <= s.length();j++)
		{
			if(j == s.length() || s.charAt(j) == ',')
			{
				String p = s.substring(prev,j);
				p = minLarger(p,last);
				if(p == null) return;
				last = p;
				test = test+ p+(j != s.length() ?",":"");
				if(best != null && test.compareTo(best.substring(0,test.length())) > 0) return;
				prev = j+1;
			}
		}
		if(best == null || test.compareTo(best) < 0)		
			best = test;
		
	}
	private String minLarger(String s, String last) {
		boolean[] q = new boolean[s.length()];
		for(int i = 0; i < s.length();i++)
		{
			if(s.charAt(i) == '?') q[i] = true;
		}
		if(s.length() < last.length()) return null;
		if(s.length() > last.length())
		{
			s = s.replace('?', '0');
			if(s.charAt(0) == '0' && q[0]) s = "1"+s.substring(1);
			if(s.charAt(0) == '0') return null;
			return s;
		}
		else
		{
			for(int i = 0; i < s.length();i++)
			{
				if(s.charAt(i) == '?')
				{
					s = s.substring(0,i)+last.charAt(i)+s.substring(i+1);
				}else{
					if(s.charAt(i) > last.charAt(i))
					{
						return s.replace('?', '0');
					}else if(s.charAt(i) < last.charAt(i))
					{
						for(int j = i-1; j>=0;j--)
						{
							if(!q[j]) continue;
							if(s.charAt(j) != '9')
								return s.substring(0,j)+(char)(s.charAt(j)+1)+s.substring(j+1).replace('?','0');
							else
								s = s.substring(0,j)+"0"+s.substring(j+1);
						}
						return null;
					}
				}
			}
			for(int j = s.length()-1; j>=0;j--)
			{
				if(!q[j]) continue;
				if(s.charAt(j) != '9')
					return s.substring(0,j)+(char)(s.charAt(j)+1)+s.substring(j+1);
				else
					s = s.substring(0,j)+"0"+s.substring(j+1);
			}
			return null;
		}
	}


}
