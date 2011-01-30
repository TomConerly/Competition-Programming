package TopCoder.Easy;

/* TopCoder SRM 450
 * Easy Problem 250 Points: OrderedNim
 * Type: DP
 * Solution: Solve the game, if there are n pieces you should either take them all or all but one. If you take all but some number larger than 1
 * the other player can either take the whole pile or force you too, which is worse than you deciding which way.
 * 
 * There is an easier greedy solution I didn't come up with.
 */

public class OrderedNim {
	public void p(Object s){System.out.println(s);}
	public String winner(int[] layout) {
		L = layout;
		dp = new int[L.length][3][3];
		int ans = recur(0,L[0],1);
		return ans == 1 ? "Alice" :"Bob";
	}
	int[] L;
	int[][][] dp;
	public int recur(int at, int left, int turn)
	{
		if(at == L.length-1)
			return turn;
		if(left > 2)left=2;
		if(dp[at][left][turn+1] != 0)
			return dp[at][left][turn+1];
		
		int ans = -turn;
		int r1 = recur(at+1,L[at+1],-turn);
		if(r1 == turn)
			ans = r1;
		if(left > 1)
		{
			int r2 = recur(at,1,-turn);
			if(r2 == turn)
				ans = r2;
		}
		dp[at][left][turn+1] = ans;
		return ans;
	}

}
