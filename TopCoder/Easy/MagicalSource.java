package TopCoder.Easy;
/* TopCoder SRM 451
 * Easy Problem 250 Points: MagicalSource
 * Type: Math
 * Solution: All the magical sources of a number are of the form n/1, n/11,n/111,n/1111... So try them all and take the min.
 * I did some crazy binary search during the match.
 */
import static java.lang.Math.*;

public class MagicalSource {
	public void p(Object s){System.out.println(s);}
	public long calculate(long x) {
		long ans = x;
		for(int i = 1; i <= (x+"").length()+1;i++)
		{
			long low = 1;
			long high = x;
			while(low <= high)
			{
				long mid = (low+high)/2;
				int c = calc(mid,i,x);
				if(c == 0)
				{
					ans = min(ans,mid);
					break;
				}
				if(c < 0)
				{
					low = mid+1;
				}
				else
				{
					high = mid-1;
				}
			}
		}
		return ans;
	}
	private int calc(long mid, int iter, long x) {
		long ans = 0;
		long temp = 1;
		for(int i = 0; i < iter;i++)
		{
			ans += mid*temp;
			if(ans > x)
				return 1;
			temp *= 10;
		}
		if(ans < x)
			return -1;
		return 0;
	}

}
