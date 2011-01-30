package TopCoder.Easy;
/* TopCoder SRM 442
 * Easy Problem 250 Points: Underprimes
 * Type: Simluation
 * Solution: Just factor all of the numbers.
 */
import java.util.*;

public class Underprimes {
	public void p(Object s){System.out.println(s);}
	public int howMany(int A, int B) {
		ArrayList<Integer> primes = new ArrayList<Integer>();
		boolean[] P = new boolean[100009];
		P[0] = true;
		P[1] = true;
		for(int i = 2; i < P.length;i++)
		{
			if(P[i] == false)
			{
				primes.add(i);

				for(int j = 2; j*i < P.length;j++)
				{
					P[i*j] = true;
				}
			}
		}
		int ans = 0;
		for(int i = A; i <= B;i++)
		{
			if(!P[i])
				continue;
			int T = i;
			int a = 0;
			for(int p:primes)
			{
				if(T == 1)
					break;
				while(T%p == 0)
				{
					T/=p;
					a++;
				}
			}
			if(P[a] == false)
			{
				ans++;
			}
		}
		return ans;
	}

}
