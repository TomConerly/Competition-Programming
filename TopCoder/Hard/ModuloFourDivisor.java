package TopCoder.Hard;
/* TopCoder SRM 458
 * Hard Problem 900 Points: ModuloFourDivisor
 * Type: Math
 * Solution: B > 0 because 1 divides everything and is 1 mod B. If c == 0 then a == 0 because anything 0 mod 4 is divisible by 2^2 so there exists something
 * divisible by 2. a %c == 0 (if c != 0) because take anything divisible by 2 (the cs) and multiply by 2^i and you get something in a. c = b+d or 0 because if there
 * is a 2 in the prime factorization of n, then 2* anything in b or d is in c. 
 * 
 *  This gets us to the gcd part. two numbers mod 1 multiplied are still mod 1, two numbers mod 3 multiplied are mod 1, and a mod 1 and mod 3 become mod 3. 
 *  I forget how exactly but when you take the gcd, you should have the same mod 1 as mod 3, or one more mod1.
 */
public class ModuloFourDivisor {
	public void p(Object s){System.out.println(s);}
	public int countQuadruplets(long[] A, long[] B, long[] C, long[] D) {
		int ans = 0;
		for(long a:A)
			for(long b:B)
				for(long c:C)
					for(long d:D)
					{
						if(b == 0)
							continue;
						if((c == 0 && a == 0) || (c == b+d && a%c == 0))
						{
							long g = gcd(b,d);
							if(b/g == d/g || b/g == d/g+1)
								ans++;
						}
					}
		return ans;
	}
	private long gcd(long x, long y) {
		if(x == 0)
			return y;
		if( y == 0)
			return x;
		if(x%y == 0)
			return y;
		else
			return gcd(y,x%y);
	}

}
