package TopCoder.Hard;
import java.util.*;
import static java.lang.Math.*;

/* TopCoder SRM 437 Div 2
 * Hard Problem 500 Points: ThePower
 * Type: Search
 * Solution: Start with the ending number, you know you know for each power p
 * you will either go to k^p below you or directly above you, so try both.
 */

public class ThePower {

	public int count(long n) {
	
		p("START");
		return calc(n);
	}
	HashMap<Long,Integer> hm = new HashMap<Long,Integer>();
	public int calc(long n)
	{
		if(n == 1) return 0;
		if(hm.containsKey(n)) return hm.get(n);
		
		int ans = Integer.MAX_VALUE;
		ans = (int)min(ans,abs(1-n));
		for(int i = 2;;i++)
		{
			double d = pow(n,1.0/i);
			long a = (long)floor(d);
			long b = (long)ceil(d);
			
			if(a != 0 && a != n)
				ans = (int)min(ans,calc(a)+abs(n-po(a,i))+1);
			if(b != a && b != n)
				ans = (int)min(ans,calc(b)+abs(n-po(b,i))+1);
			if(a == 1) break;
		}
		p(n+" "+ans);
		hm.put(n,ans);
		return ans;		
	}
	private long po(long a, int i) {
		long ans = 1;
		for(int j = 0; j < i;j++)ans*=a;
		return ans;
	}
	public void p(Object o){System.out.println(o);}
}
