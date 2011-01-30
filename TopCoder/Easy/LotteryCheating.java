package TopCoder.Easy;
import static java.lang.Math.*;
import static java.util.Arrays.*;

/* TopCoder SRM 466
 * Easy Problem 250 Points: LotteryCheating
 * Type: Math
 * Solution: Notice that the perfect squares are the only numbers with an easy number of divisors. There aren't that many perfect squares so try em all.
 */

public class LotteryCheating {
	public void p(Object... s){System.out.println(deepToString(s));}
	public int minimalChange(String s) {
		int ans = s.length();
		for(long i = 0;;i++){
			String S = (i*i)+"";
			if(S.length() > s.length())
				break;
			while(S.length() < s.length())
				S = "0"+S;
			int diff = 0;
			for(int j = 0; j < S.length();j++)
				if(S.charAt(j) != s.charAt(j))
					diff++;
			ans = min(ans,diff);
		}
		return ans;
	}
	
}
