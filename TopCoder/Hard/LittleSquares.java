package TopCoder.Hard;
import java.util.*;

/* TopCoder SRM 389
 * Hard Problem 1000 Points: LittleSquares
 * Type: Nim
 * Solution: We can split it into the top 2 rows, next 2 rows, on down and play them seperately,
 * calculate the nimber for each (using dp, expore the whole tree) then xor together.
 */

public class LittleSquares {

	int N;
	public String winner(String[] state) {
		N = state[0].length();
		int nimsum = 0;
		dp = new int[1<<(20)];
		Arrays.fill(dp,-1);
		for(int i = 0; i < state.length;i+=2)
		{
			int t = 0;
			int b = 0;
			for(int j = 0; j <N;j++)
			{
				t = (t<<1)|(state[i].charAt(j) =='#'?1:0);
				b = (b<<1)|(state[i+1].charAt(j) =='#'?1:0);
			}
			int n = nim(t,b);
			nimsum ^= n;
		}
		if(nimsum != 0) return "first";
		else return "second";
	}
	int[] dp;
	int nim(int t, int b)
	{
		int h = (t<<10|b);
		if(dp[h] != -1) return dp[h];
		boolean[] have = new boolean[10];
		for(int i = 0; i < N;i++)
		{
			if((t&(1<<i)) == 0)			
				have[nim(t|(1<<i),b)]=true;;
			if((b&(1<<i)) == 0)			
				have[nim(t,b|(1<<i))]=true;
			if(i +1 < N)
			{
				if((t&(1<<i)) == 0 && (t&((1)<<(i+1))) == 0 && (b&(1<<i)) == 0 && (b&(1<<(i+1))) == 0 )
				{
					have[nim(t|(1<<i)|(1<<(i+1)),b|(1<<i)|(1<<(i+1)))]=true;
				}
			}			
		}

		int ans = 0;
		for(int i = 0; i < have.length;i++)
			if(!have[i])
			{
				ans = i;
				break;
			}
		dp[h] = ans;
		return ans;
	}
}
