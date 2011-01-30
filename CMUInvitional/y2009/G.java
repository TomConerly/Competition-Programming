package CMUInvitional.y2009;
import java.util.*;

/* CMU Spring Invitational 2009
 * Problem G: Wireless Coverage
 * Type: Greedy
 * Solution: Sort by end coordinate, it is always optimal to put
 * a access point at the end of the first range.
 */

public class G {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			int N = sc.nextInt();
			if(N == 0) break;
			Range[] ranges = new Range[N];
			for(int i = 0; i < N;i++)
			{
				int loc = sc.nextInt();
				int dist = sc.nextInt();
				ranges[i] = new Range(loc-dist,loc+dist);
			}
			int lastMark = Integer.MIN_VALUE;
			Arrays.sort(ranges);
			int ans = 0;
			for(int i = 0; i < N;i++)
			{
				Range r = ranges[i];
				if(r.low <= lastMark && lastMark <= r.high) continue;
				ans++;
				lastMark = r.high;
			}
			System.out.println(ans);
		}
	}
	private static class Range implements Comparable<Range>
	{
		int low,high;
		public Range(int l, int h)
		{
			low = l;
			high = h;
		}
		public int compareTo(Range r) {
			return Integer.valueOf(high).compareTo(r.high);
		}
	}
}
