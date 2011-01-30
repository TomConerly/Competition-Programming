package TopCoder.Easy;
/* TopCoder SRM 390
 * Easy Problem 250 Points: ConcatenateNumber
 * Type: BruteForce
 * Solution: Try concatinations up to k then you can stop.
 */
public class ConcatenateNumber {

	public int getSmallest(int n, int k) {
		k = k/gcd(n,k);
		if(k == 1) return 1;
		
		int l = (n+"").length();
		long m =(int)(pow10(l)%k);
		long d = m;
		long temp = (m+1)%k;
		int count = 2;
		while(temp != 0)
		{
			temp = (temp+d*m)%k;
			d = (d*m)%k;			
			count++;
			if(count > 1000000)return -1;
		}
		
		return count;
	}
	private long pow10(int l) {
		long a = 1;
		for(int i = 0; i < l;i++)
			a*=10;
		return a;
	}
	int gcd(int a, int b)
	{
		if(b == 0) return a;
		return gcd(b,a%b);
	}
	void print(int[] a)
	{
		for(int b:a)
			System.out.print(b+" ");
		System.out.println();
	}

}
