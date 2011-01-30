package TopCoder.Medium;
/* TopCoder SRM 459
 * Medium Problem 500 Points: NumberPyramids
 * Type: DP
 * Solution: We only care about the bottom row. We know that if something is in position i on the bottom row it adds baselength choose i to the top element.
 * DP is DP[base position][numLeft]. This by itself would time out. Think about computing the next baseposition given the previous one. DP[baseposition][numleft] 
 * can be created by DP[baseposition+1][k] if (numleft - k) (and k < numLeft) is divisible by the amount that adding 1 to this position increases the top. Thus we keep 
 * track for each value 0...the amount increased by adding 1 so we can compute this faster.
 */

import java.util.*;

public class NumberPyramids {
	public void p(Object s){System.out.println(s);}
	long M = 1000000009L;
	public int count(int baseLength, int top) {
		if(baseLength > 20)
			return 0;
		T = top - (1 << (baseLength-1));
		if(T < 0)
			return 0;
		len = baseLength;
		int[] D = new int[baseLength];
		C = new int[baseLength];
		for(int i = 0; i < D.length;i++)
			D[i] = choose(baseLength-1,i);
		Arrays.sort(D);
		for(int i = 0; i < D.length;i++)
			C[i] = D[baseLength-i-1];

		long[] DP = new long[T+1];
		long[] DP2 = new long[T+1];
		DP[0] = 1;
		for(int i = 0; i < C.length;i++)
		{
			boolean d = false;
			if(i+1 < C.length && C[i] == C[i+1])
				d = true;
			if(C[i] == 1)
			{
				for(int j = 0; j <=T;j++)
				{
					if(DP[j] != 0)
					{
						DP2[T] += DP[j]*(d ?(T-j+1):1);
						DP2[T] %= M;
					}
				}
			}else if(d)//else if(C[i] < 1000)
			{
				long[] m = new long[C[i]];
				long[] s = new long[C[i]];
				for(int j = 0; j <= T;j++)
				{
					s[j%C[i]] += DP[j];
					s[j%C[i]] %= M;
					m[j%C[i]] += s[j%C[i]];
					m[j%C[i]] %= M;
					DP2[j] += m[j%C[i]];
					DP2[j] %= M;
				}
			}
			else
			{
				long[] s = new long[C[i]];
				for(int j = 0; j <= T;j++)
				{
					s[j%C[i]] += DP[j];
					s[j%C[i]] %= M;
					DP2[j] += s[j%C[i]];
					DP2[j] %= M;
				}
			}
			DP = DP2;
			for(int j = 0; j <= T;j++)
				DP[j] %= M;
			DP2 = new long[T+1];
			if(d)
				i++;
		}
		return (int) DP[T];
	}
	public int choose(int n, int k)
	{
		long ans = 1;
		for(int i = n-k+1;i<=n;i++)
			ans *= i;
		for(int i =2;i<=k;i++)
			ans /=i;
		return (int) ans;
	}
	int T,len;
	int[] C;

}
