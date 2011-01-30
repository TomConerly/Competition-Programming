package TopCoder.Hard;
import static java.lang.Math.*;

/* TopCoder SRM 392
 * Hard Problem 1000 Points: EquiDigitNumbers
 * Type: Math/Greedy
 * Solution: We know the answer has to have same number of digits (it could be all 9s).
 * So we try every p and q where we fix the first p digits and set the p+1th digit to q
 * then from there we can greedily fill in the number to be the smallest equidigit number.
 */

public class EquiDigitNumbers {

	String act;
	public long findNext(String N) {
		act = N;
		StringBuilder n = new StringBuilder(N);
		String ans = null;
		for(int i = 0; i < n.length();i++)
		{
			for(int j = 0; j < 10;j++)
			{
				if(i == 0 && j == 0) continue;
				if(j < act.charAt(i)-'0') continue;
				StringBuilder temp = new StringBuilder(N);
				temp.setCharAt(i, (char)(j+'0'));
				String best = solve(temp,i);
				if(best == null) continue;
				if(ans == null || ans.compareTo(best) > 0)
				{
					ans = best;
				}
			}
		}

		return Long.valueOf(ans);
	}


	private String solve(StringBuilder t, int at) 
	{

		String tt = t.toString();
		String best =  null;
		next:
		for(int h = 1; h <= t.length();h++)
		{
			if(t.length() %h != 0) continue;
			int n = t.length()/h;
			t = new StringBuilder(tt);
			for(int j = at+1;j < t.length();j++)
			{
				boolean good = false;
				for(int p = 0; p < 10;p++)
				{				
					StringBuilder temp = new StringBuilder(t.toString());
					temp.setCharAt(j, (char)(p+'0'));
					if(act.compareTo(temp.toString()) > 0) continue;
					if(h(temp,j)*n(temp,j) > t.length() || h(temp,j) > h||n(temp,j)>n) continue;
					else{
						t = temp;
						good = true;
						break;
					}
				}
				if(!good){
					continue next;
				}
			}
			if(best == null || best.compareTo(t.toString()) > 0)
			{
				best = t.toString();
			}
		}
		if(best == null) return null;
		t = new StringBuilder(best);
		if(h(t,t.length()-1)*n(t,t.length()-1) > t.length()) return null;
		return best;

	}

	private int n(StringBuilder t, int j) {
		boolean[] have = new boolean[10];
		int ans = 0;
		for(int i = 0;i<=j;i++)
		{
			if(have[t.charAt(i)-'0'] == false)
			{
				ans++;
				have[t.charAt(i)-'0'] = true;
			}
		}
		return ans;
	}

	private int h(StringBuilder t, int j) {
		int[] have = new int[10];
		int ans = 0;
		for(int i = 0;i<=j;i++)
		{
			have[t.charAt(i)-'0']++;			
			ans = max(ans,have[t.charAt(i)-'0']);

		}
		return ans;
	}


	void print(int[] a)
	{
		for(int b:a)
			System.out.print(b+" ");
		System.out.println();
	}

}
