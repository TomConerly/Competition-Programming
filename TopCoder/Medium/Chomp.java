package TopCoder.Medium;
import java.util.*;
import static java.lang.Math.*;

/* TopCoder TCO08 Round 1
 * Medium Problem 500 Points: Chomp
 * Type: DP
 * Solution: The DP[a][b][c] means the num of moves and how wins when 
 * the top row has a things left, the middle row b things, and the bottom
 * row c things.
 */

public class Chomp {

	public int moves(int N) {
		dp = new int[N+1][N+1][N+1];
		for(int i = 0; i < dp.length;i++)
			for(int j = 0; j < dp[0].length;j++)
				Arrays.fill(dp[i][j],Integer.MAX_VALUE);
		return solve(N,N,N);
	}
	int[][][] dp;
	public int solve(int a, int b, int c)
	{
		if(a == 0 && b == 0 && c == 0)
		{
			return 0;
		}
		if(dp[a][b][c] != Integer.MAX_VALUE) return dp[a][b][c];
		
		int moves = -1;
		for(int i = 0; i < a;i++)
		{
			int m = -solve(i,b,c);
			if(m <= 0) m--;
			else m++;
			moves = update(moves,m);
		}
		for(int i = 0; i < b;i++)
		{
			int m = -solve(min(i,a),i,c);
			if(m <= 0) m--;
			else m++;
			moves = update(moves,m);
		}
		for(int i = 0; i < c;i++)
		{
			int m = -solve(min(i,a),min(i,b),i);
			if(m <= 0) m--;
			else m++;
			moves = update(moves,m);
		}
		dp[a][b][c] = moves;
		//p(a+" "+b+" "+c+" "+moves);
		return moves;
	}
	private int update(int moves, int m) {
		if(moves > 0 && m > 0)
		{
			return min(m,moves);
		}
		if(moves < 0 && m < 0)
		{
			return min(moves,m);
		}
		if(moves > 0 && m < 0) return moves;
		if(moves < 0 && m > 0) return m;
		return 0;
	}
	public void p(Object o){System.out.println(o);}
}
