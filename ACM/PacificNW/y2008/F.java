package ACM.PacificNW.y2008;
import java.util.*;

/* ACM Pacific Northwest 2008
 * Problem F: Optimal Strategy for the ICPC
 * Type: Brute Force
 * Solution: Just brute force, keep a running total so you can prune.
 */

public class F {
	public static void main(String[] args)
	{
		long st = System.currentTimeMillis();
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		for(int zz = 1; zz <= N;zz++)
		{
			K = sc.nextInt();
			S = new int[K];
			for(int i = 0; i < K; i++)
				S[i] = (sc.nextInt()<<16) | i;
			Arrays.sort(S);
			bestS = 0;
			bestT = 0;
			recur(0,new int[K],0,0,0,0,0);
			System.out.println("Data set "+zz+": "+best);
		}
		long en = System.currentTimeMillis();
		System.out.println((en-st)+"ms");
	}
	private static void recur(int at, int[] assign, int ta, int tb, int tc, int ns, int t) 
	{
		if(ns > 0 &&( ns > bestS || (ns==bestS && t < bestT) || (ns == bestS && t == bestT && ans(assign,at).compareTo(best) < 0)))
		{
			best = ans(assign,at);
			bestS = ns;
			bestT = t;
		}
		if(at == K)
			return;

		int nt = (S[at]>>>16);
		if(ta + nt <= 300)
		{
			assign[at] = 0;
			recur(at+1,assign,ta+nt,tb,tc,ns+1,t+ta+nt);
		}
		if(tb + nt <= 300)
		{
			assign[at] = 1;
			recur(at+1,assign,ta,tb+nt,tc,ns+1,t+tb+nt);
		}
		if(tc + nt <= 300)
		{
			assign[at] = 2;
			recur(at+1,assign,ta,tb,tc+nt,ns+1,t+tc+nt);
		}

	}
	private static String ans(int[] assign, int at) 
	{
		int[] t = new int[3];
		int[] ans = new int[at];
		int tp=0;
		for(int i = 0; i < at;i++)
		{
			t[assign[i]] += (S[i]>>>16);
			tp += t[assign[i]];
			ans[i] = (t[assign[i]] << 16) | (S[i] & 0xFFFF);
		}
		Arrays.sort(ans);
		String s = "";
		for(int i = 0; i < at;i++)
		{
			s += ((char)((ans[i] & 0xFFFF)+'A'))+" ";
		}
		s += at+" "+tp;
		return s;
	}
	static String best;
	static int bestS,bestT;
	static int K;
	static int[] S;
}
