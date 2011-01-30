package TopCoder.Medium;
import java.util.*;
import static java.lang.Math.*;

/* TopCoder SRM 383
 * Medium Problem 500 Points: FloorBoards
 * Type: DP
 * Solution: Sweep from top to bottom. At any point you keep track
 * of where you are, and which x values you have a board coming down from
 * and return the min # of boards to cover.
 */

public class FloorBoards {

	boolean[][] b;
	int[] bo;
	int N,M;
	public int layout(String[] r) {
		N = r.length;
		M = r[0].length();
		b = new boolean[N][M];
		bo = new int[N];
		for(int i = 0; i < N;i++)
		{
			bo[i] = 0;
			for(int j = 0; j < M;j++)
			{
				b[i][j] = r[i].charAt(j) == '#';
				if(!b[i][j]) bo[i] |= 1<<j;
			}
		}
		dp = new int[N][1<<M];
		for(int i = 0; i < N;i++)
			Arrays.fill(dp[i],-1);
		
		
		return recur(0,0);
	}
	private int recur(int at, int down) {
		if(at == N) return 0;
		down &= bo[at];
		if(dp[at][down] != -1) return dp[at][down];
		
		int best = 10000;
		for(int i = bo[at];i>=0; i = (i-1)&bo[at])
		{
			int ans = Integer.bitCount(i)-Integer.bitCount(i&down);
			int cov = i;
			boolean free = false;
			for(int j = 0; j < M;j++)
			{
				if(b[at][j] || ((1<<j)&cov) != 0){
					free = false;
					continue;
				}
				if(((1<<j) & cov) == 0)
				{
					if(!free)
					{
						ans++;
						free = true;
					}					
				}
			}
			best = min(best,ans+recur(at+1,i));
			if(i==0) break;
		}
		
		dp[at][down] = best;
		return best;
	}
	int[][] dp;
	public void p(Object o){System.out.println(o);}
}
