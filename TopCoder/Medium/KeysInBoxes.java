package TopCoder.Medium;
/* TopCoder SRM 391
 * Medium Problem 500 Points: KeysInBoxes
 * Type: Brute Force
 * Solution: We can setup a forumla for the probability that we have to blow up the
 * next box or not, then at each step there are only 2 options so the tree
 * is 2^20 large. Have a fraction class for properly formatting the answer.
 * Be careful for long overflow in fraction class. 
 */
public class KeysInBoxes {

	int N,M;
	public String getAllKeys(int n, int m) {
		N = n;
		M = m;
		frac p = (recur(0,m,false,N-1));
		
		
		return p.num+"/"+p.dem;
	}
	frac recur(int at,int bomb,boolean key, int goodKeys)
	{
		if(at == N) return new frac(1,1);
		
		if(key)
		{
			frac p = new frac(goodKeys,N-at);
			frac p1 = new frac(N-at-goodKeys,N-at);
			return p.multiply(recur(at+1,bomb,true,goodKeys-1)).add(p1.multiply(recur(at+1,bomb,false,goodKeys-1)));
		}else{
			if(bomb == 0) return new frac(0,1);
			frac p = new frac(goodKeys,N-at);
			frac p1 = new frac(N-at-goodKeys,N-at);
			return p.multiply(recur(at+1,bomb-1,true,goodKeys-1)).add(p1.multiply(recur(at+1,bomb-1,false,goodKeys-1)));
		}
	}
	class frac{
		long num,dem;
		frac(long a,long b)
		{
			num = a;
			dem = b;
			//long g = gcd(a,b);
			//num /= g;
			//dem /= g;
		}
		frac add(frac x)
		{
			long e = gcd(dem,x.dem);			
			long c = (dem)*(x.dem/e);
			long b = num*(c/dem)+x.num*(c/x.dem);
			long g = gcd(b,c);
			frac f =  new frac(b/g,c/g);
			return f;
		}
		public String toString()
		{
			return num+"/"+dem;
		}
		frac multiply(frac a)
		{
			long n = a.num*num;
			long d = a.dem*dem;
			long g = gcd(n,d);
			frac f = new frac(n/g,d/g);
			return f;
		}
		private long gcd(long a, long b) {
			if(b == 0) return a;
			return gcd(b,a%b);
		}
	}
	void print(int[] a)
	{
		for(int b:a)
			System.out.print(b+" ");
		System.out.println();
	}

}
