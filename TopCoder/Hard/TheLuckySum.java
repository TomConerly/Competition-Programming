package TopCoder.Hard;
import java.util.*;

/* TopCoder SRM 404
 * Hard Problem 1000 Points: TheLuckySum
 * Type: Number Theory
 * Solution: The key here is that if we have a set of lucky numbers
 * that sum to n then we can switch a digit in position i with a digit
 * in position i of a different term and get the same sum. So just calculate
 * how many 4s/7s in a given position and try all the ones that work,
 *  which isn't very many.
 */
public class TheLuckySum {

	public int[] sum(int a) {		
		int temp = a;
		int count = 0;
		while(temp != 0)
		{
			temp/=10;
			count++;
		}
		digits = new int[count];
		temp = a;
		int at = 0;
		while(temp != 0)
		{
			digits[at] = temp%10;
			at++;
			temp/=10;			
		}
		solution = null;
		for(int i = 1; i < 15;i++)
		{
			d4 = new int[digits.length];
			d7 = new int[digits.length];
			mem = new int[10][digits.length][i];
			test = i;
			recur(0,0,i);
			if(solution != null) break;
		}
		if(solution == null) return new int[]{};
		
		int[] ans = new int[solution.size()];
		for(int i = 0; i<ans.length;i++)
			ans[i] = solution.get(i);
		return ans;
	}
	int test;
	int[] digits;
	int[][][] mem;
	int[] d4;
	int[] d7;
	int[] num;
	ArrayList<Integer> solution;
	public int pow10(int n)
	{
		int ans = 1;
		for(int i = 0; i < n;i++)ans*=10;
		return ans;
	}
	public void recur(int carry, int digit, int left) {
		
		if(digit == digits.length)
		{
			if(carry != 0) return;
			
			int[] n4 = new int[d4.length];
			for(int i = 0; i < n4.length;i++)n4[i]=d4[i];
			int[] n7 = new int[d7.length];
			for(int i = 0; i < n7.length;i++)n7[i]=d7[i];
			ArrayList<Integer> ans = new ArrayList<Integer>();
			for(int i = 0; i < test;i++)
			{
				int soFar = 0;
				for(int j = 0; j < digits.length;j++)
				{
					if(n4[j]+n7[j] < test-i)
					{
						ans.add(soFar);
						soFar = -1;
						break;
					}
					if(n4[j] >= 1)
					{
						soFar = soFar+4*pow10(j);
						n4[j]--;
					}else{
						soFar = soFar+7*pow10(j);
						n7[j]--;
					}
				}
				if(soFar != -1) ans.add(soFar);
			}
			boolean better = false;
			if(solution == null) solution = ans;
			for(int i = 0; i < solution.size();i++)
			{
				if(ans.get(i) < solution.get(i)) 
				{
					better = true;
					break;
				}
				if(ans.get(i) > solution.get(i)) 
				{
					break;
				}
			}
			if(better) solution = ans;
			return;
		}
		for(int num4 = 0; num4 <= left;num4++)
		{
			for(int num7 = 0;num7+num4 <= left; num7++)
			{
				if((num4*4+num7*7 +carry)%10 != digits[digit]) continue;
				d4[digit]=num4;
				d7[digit]=num7;
				recur((num4*4+num7*7+carry)/10,digit+1,num4+num7);
			}			
		}
		
	}
	
}
