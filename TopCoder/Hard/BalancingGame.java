package TopCoder.Hard;
import java.util.*;

/* TopCoder TCO07 Qual 3
 * Hard Problem 1000 Points: BalancingGame
 * Type: DP
 * Solution: Explore the game tree using dp.
 */

public class BalancingGame {

	public int[] winningMoves(int[] x, int[] y, int[] w, int threshold) {
		N = x.length;
		long T = ((long)threshold)*threshold;
		stable = new boolean[1<<x.length];
		winner = new int[1<<x.length];
		for(int i = 0; i < 1 << x.length;i++)
		{
			long tx=0,ty=0;
			for(int j = 0; j < x.length;j++)
			{
				if((i&(1<<j)) != 0)
				{
					tx += -y[j]*w[j];
					ty += x[j]*w[j];
				}
			}
			if(tx*tx+ty*ty > T) stable[i] = false;
			else stable[i] = true;
		}
		
		if(!stable[(1<<x.length)-1]) return new int[]{-1};
		fWin((1<<x.length-1));
		ArrayList<Integer> ans = new ArrayList<Integer>();

		for(int i = 0; i < x.length;i++)
		{
			if(fWin(((1<<x.length)-1) & (~(1<<i))))
				ans.add(i);
		}
		int[] a = new int[ans.size()];
		for(int i = 0; i < ans.size();i++)
			a[i] = ans.get(i);
		return a;
	}
	boolean fWin(int board)
	{
		if(winner[board] != 0)
		{
			return winner[board] == 1;
		}
		if(board == 0)
		{
			winner[board] = -1;
			return false;
		}
		if(!stable[board])
		{
			if((N-Integer.bitCount(board))%2 == 1){
				winner[board] = -1;
				return false;
			}
			else{
				winner[board] = 1;
				return true;
			}
		}
		boolean fTurn = (N-Integer.bitCount(board))%2 == 0;
		if(fTurn)
		{
			for(int i = 0; i < N;i++)			
				if((board & (1<<i)) != 0)
					if(fWin(board & (~(1<<i)))) {
						winner[board] = 1;
						return true;			
					}
			winner[board] = -1;
			return false;
		}else{
			for(int i = 0; i < N;i++)	
				if((board & (1<<i)) != 0)
					if(!fWin(board & (~(1<<i)))){
						winner[board] = -1;
						return false;		
					}
			winner[board] = 1;
			return true;
		}
	}
	int N;
	boolean[] stable;
	int[] winner;
}
