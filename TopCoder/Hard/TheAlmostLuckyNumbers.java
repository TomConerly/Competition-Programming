package TopCoder.Hard;
import java.util.*;

/* TopCoder SRM 453.5
 * Hard Problem 1000 Points: TheAlmostLuckyNumbers
 * Type: Math/Simulation
 * Solution: Do inclusion exclusion. Notice that when doing inclusion exclusion the LCM gets very high quicklky
 * so you don't time out.
 */

public class TheAlmostLuckyNumbers {
	public void p(Object s){System.out.println(s);}
	
	public long count(long a, long b) {
		A = a;
		B = b;
		TreeSet<Long> pos = new TreeSet<Long>();
		for(int i = 1; i <= 10;i++)
		{
			
			for(int j = 0; j < 1<<i;j++)
			{
				long num = 0;
				for(int k = 0; k < i;k++)
				{
					num *= 10;
					if(((j>>k) & 1) == 1)
					{
						num += 7;
					}else{
						num += 4;
					}
				}
				boolean legal = true;
				for(long n:pos)
				{
					if(num%n==0)
						legal = false;
				}
				if(legal)
					pos.add(num);
			}
		}
		N = new long[pos.size()];
		int at = pos.size()-1;
		for(long l:pos)
			N[at--] = l;
	
		
		recur(1,0,-1);
		return ans;
	}
	long ans = 0;
	long[] N;
	long M = 10000000000L;
	long A,B;
	void recur(long lcm, int at,int m)
	{
		if(lcm != 1)
			ans += m*num(lcm);
		for(int i = at; i < N.length;i++)
		{
			long lc = lcm(lcm,N[i]);
			if(lc > M)
				continue;
			recur(lc,i+1,m*-1);
		}
	}
	private long num(long n) {
		//if(n < 2*B)
		//	System.out.println(A+" "+B+" "+n+" "+((A-1)/n + (B/n)));
		return (B/n)-(A-1)/n;
	}
	long lcm(long a, long b)
	{
		long z = a/gcd(a,b);
		if(M/z < b)
			return M+1;
		else
			return b*z;
	}
	long gcd(long a, long b)
	{
		if(b == 0)
			return a;
		else
			return gcd(b,a%b);
	}
}
