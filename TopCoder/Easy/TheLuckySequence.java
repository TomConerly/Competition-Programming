package TopCoder.Easy;
import java.util.*;

/* TopCoder SRM 403
 * Easy Problem 500 Points: TheLuckySequence
 * Type: Counting
 * Solution: The numbers we have we only care about the lucky ones.
 * Also we only care about how many go start 4 end 4, start 4 end 7,
 * start 7 end 4, start 7 end 7. If we have the number that end in 4 and 7
 * from a given step we can use that to figure out how many in the next step.
 * Also note that we can combine two steps to get one step which will move us length 2.
 * I compute the steps that will move us each power of 2 futher. Then go through the 
 * binary representation of length to apply only log n moves to get the answer.
 */

public class TheLuckySequence {
	
	public static void main(String[] args)
	{
		long ans = 1;
		long mod = 1234567891;
		for(int i = 0; i <=1000000;i++)
		{
			ans <<=1;
			ans %= mod;
		}
		System.out.println(ans);
	}
	public int count(int[] numbers, int length) {
		long[][] stoe = new long[2][2];
		ArrayList<Integer> in = new ArrayList<Integer>();
		
		for(int i: numbers)
		{
			if(!lucky(i)) continue;
			if(in.contains(i)) continue;
			in.add(i);
			String s = ""+i;
			if(s.charAt(0) == '4' && s.charAt(s.length()-1) == '4')			
				stoe[0][0]++;
			if(s.charAt(0) == '4' && s.charAt(s.length()-1) == '7')			
				stoe[0][1]++;
			if(s.charAt(0) == '7' && s.charAt(s.length()-1) == '4')			
				stoe[1][0]++;
			if(s.charAt(0) == '7' && s.charAt(s.length()-1) == '7')			
				stoe[1][1]++;
		}
		long[][][] m = new long[40][2][2];
		m[0] = stoe;
		long mod = 1234567891;
		for(int i = 1; i < m.length;i++){
			m[i][0][0] = ((m[i-1][0][0]*m[i-1][0][0])%mod+(m[i-1][0][1]*m[i-1][1][0])%mod)%mod;
			
			m[i][0][1] = ((m[i-1][0][0]*m[i-1][0][1])%mod+(m[i-1][0][1]*m[i-1][1][1])%mod)%mod;
			
			m[i][1][0] = ((m[i-1][1][0]*m[i-1][0][0])%mod+(m[i-1][1][1]*m[i-1][1][0])%mod)%mod;
			
			m[i][1][1] = ((m[i-1][1][1]*m[i-1][1][1])%mod+(m[i-1][1][0]*m[i-1][0][1])%mod)%mod;
		}
		long[] cur ={1,1};
		int at = 0;
		while(length != 0)
		{
			if((length &1) == 1)
			{
				long[] next = new long[2];
				next[0] = ((cur[0]*m[at][0][0])%mod+(cur[1]*m[at][1][0])%mod)%mod;
				next[1] = ((cur[0]*m[at][0][1])%mod+(cur[1]*m[at][1][1])%mod)%mod;
				cur = next;
			}
			at++;
			length >>=1;
		}
		return (int)((cur[0]+cur[1])%mod);
	}

	public boolean lucky(int a)
	{
		while(a != 0)
		{
			if(a%10 != 7 && a%10 != 4) return false;
			a/=10;
		}
		return true;
	}
}
