package SSU;
import java.util.*;

/* Saratov State University Online Judge
 * Problem 108: Self-numbers 2
 * Type: Smart brute force
 * Solution: For all numbers 1..N mark d(i) as not a self-number. Notice that d(i)-i < 100. So we can keep
 * a table of only i and the next 100 entries, this is circular so no memory is allocated after the start.
 * This times out because computing d(i) takes too long. Instead precompute d(i) for all i < 1000 then d(i)
 *  = d(i%10000)+d(i/10000).
 */

public class p108 {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		long[] idx = new long[K];
		for(int i = 0; i < K;i++)
		{
			idx[i] = (sc.nextLong() << 32) | i;
		}
		long st = System.currentTimeMillis();
		int[] lu = new int[10000];
		for(int i = 0; i < lu.length;i++)
		{
			lu[i] = next(i)-i;
		}
		
		Arrays.sort(idx);
		final int W = 1024;
		boolean[] window = new boolean[W];
		int at = 0;
		int ansat = 0;
		int[] ans = new int[K];
		int total = 0;		
		
		for(int i = 1; i <= N;i++)
		{
			if(!window[at])
			{
				total++;
				
				while(ansat < K && (idx[ansat]>>>32) == total)
				{
					ans[ansat++] = i;
				}				
			}
			window[(at+lu[i%10000]+lu[i/10000])%W] = true;
			window[at] = false;
			at = (at+1)%W;			
		}
		long[] rans = new long[K];
		for(int i = 0; i < K;i++)
		{
			rans[i] = ((idx[i]) << 32) | (long)ans[i];
		}
		Arrays.sort(rans);
		System.out.println(total);
		for(long l:rans)
			System.out.print((l&0xFFFFFFFFL)+" ");
		long en = System.currentTimeMillis();
		System.out.println("\n"+(en-st));
	}
	public static int next(int n)
	{
		int temp = n;
		while(temp != 0)
		{
			n += temp %10;
			temp/=10;
		}
		return n;
	}
}
