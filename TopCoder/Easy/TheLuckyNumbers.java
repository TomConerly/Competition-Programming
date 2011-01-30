package TopCoder.Easy;
/* TopCoder SRM 403
 * East Problem 250 Points: TheLuckyNumbers
 * Type: Number Theory
 * Solution: Note that there are only ~1000
 * lucky numbers so testing if each one is
 * in between a and b is the way to go.
 */
public class TheLuckyNumbers {

	public int count(int aa,int bb) {
		A = aa;
		B = bb;
		count = 0;
		recur(0,0);
		return count;
	}
	int A,B;
	int count;
	public void recur(int d, long at)
	{
		System.out.println(at);
		if(at > B) return;
		if( at >= A && at <=B) count++;
		recur(d+1,at+pow10(d)*4);
		recur(d+1,at+pow10(d)*7);
	}
	public long pow10(int n)
	{
		long a = 1;
		for(int i = 0; i < n;i++)a*=10;
		return a;
	}

}
