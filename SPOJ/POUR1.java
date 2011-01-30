package SPOJ;
import java.util.*;


/* SPOJ Problem 25: POUR1
 * Type: Search
 * Solution: BFS, but note that each state has either
 * a = 0 or a = A or b = 0 or b = B so very few states.
 */

public class POUR1 {
	public static void p(Object o){System.out.println(o);}
	static long A,B;
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int zz = sc.nextInt();
		long[] q = new long[200000];
		for(int z = 0; z < zz;z++)
		{
			A = sc.nextInt();
			B = sc.nextInt();
			long C = sc.nextInt();
			
			seenAe = new long[(int)(B+1)];
			seenAf = new long[(int)(B+1)];
			seenBe = new long[(int)(A+1)];
			seenBf = new long[(int)(A+1)];
			Arrays.fill(seenAe,Integer.MAX_VALUE);
			Arrays.fill(seenAf,Integer.MAX_VALUE);
			Arrays.fill(seenBe,Integer.MAX_VALUE);
			Arrays.fill(seenBf,Integer.MAX_VALUE);
			int ans = -1;
			
			int qs = 0;
			int qe = 0;
			q[qe++] = 0;
			seenAe[0] = 0;
			seenBe[0] = 0;
			while(qe-qs > 0)
			{
				long l = q[qs++];
				long cost = (l>>>32);
				long at = (l&(0xFFFFFFFFL));
				long a = at & (0xFFFF);
				long b = at >>> 16;
				if(lookup((int)a,(int)b) < cost) continue;				
				
				if(a == C || b == C)
				{
					ans = (int)cost;
					break;
				}				
				long to1 = b << 16;
				long to2 = a;
				long to3 = (b<<16)+A;
				long to4 = (B<<16)+a;
				long to5;
				if(A-a > b)
				{
					to5 = a+b;
				}else{
					to5 = A+((b-(A-a))<<16);
				}
				long to6;
				if(B-b > a)
				{
					to6 = (a+b)<<16;
				}else{
					to6 = (B<<16)+(a-(B-b));
				}
				cost++;
				if(lookup((int)(to1 & (0xFFFF)),(int)(to1 >>> 16)) > cost)
				{
					set((int)(to1 & (0xFFFF)),(int)(to1 >>> 16),cost);
					q[qe++] = ((cost << 32)|to1);
				}
				if(lookup((int)(to2 & (0xFFFF)),(int)(to2 >>> 16)) > cost)
				{
					set((int)(to2 & (0xFFFF)),(int)(to2 >>> 16),cost);
					q[qe++] = ((cost << 32)|to2);
				}
				if(lookup((int)(to3 & (0xFFFF)),(int)(to3 >>> 16)) > cost)
				{
					set((int)(to3 & (0xFFFF)),(int)(to3 >>> 16),cost);
					q[qe++] = ((cost << 32)|to3);
				}
				if(lookup((int)(to4 & (0xFFFF)),(int)(to4 >>> 16)) > cost)
				{
					set((int)(to4 & (0xFFFF)),(int)(to4 >>> 16),cost);
					q[qe++] = ((cost << 32)|to4);
				}
				if(lookup((int)(to5 & (0xFFFF)),(int)(to5 >>> 16)) > cost)
				{
					set((int)(to5 & (0xFFFF)),(int)(to5 >>> 16),cost);
					q[qe++] = ((cost << 32)|to5);
				}
				if(lookup((int)(to6 & (0xFFFF)),(int)(to6 >>> 16)) > cost)
				{
					set((int)(to6 & (0xFFFF)),(int)(to6 >>> 16),cost);
					q[qe++] = ((cost << 32)|to6);
				}
			}
			p(ans);
		}
	}
	private static void set(int a, int b, long cost) {
		if(a == 0) seenAe[b] = cost;
		if(a == A) seenAf[b] = cost;
		if(b == 0) seenBe[a] = cost;
		if(b == B) seenBf[a] = cost;;
		
	}
	static long[] seenAe,seenAf,seenBe,seenBf;;
	static long lookup(int a,int b)
	{
		if(a == 0) return seenAe[b];
		if(a == A) return seenAf[b];
		if(b == 0) return seenBe[a];
		if(b == B) return seenBf[a];
		return Integer.MAX_VALUE;		
	}
}
