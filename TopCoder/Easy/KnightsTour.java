package TopCoder.Easy;
/* TopCoder SRM 447
 * Easy Problem 250 Points: KnightsTour
 * Type: Simulation
 * Solution: Simulate it, yawn.
 */
public class KnightsTour {

	int[] x = {-2,-2,2,2,-1,-1,1,1};
	int[] y = {1,-1,1,-1,2,-2,2,-2};
	public int visitedPositions(String[] board) {
		int r=0,c=0;
		for(int i = 0; i < 8;i++)
			for(int j = 0; j < 8;j++)
				if(board[i].charAt(j) == 'K')
				{
					r = i;
					c = j;
				}
		board[r] = board[r].substring(0,c) + "*"+board[r].substring(c+1);
		int ans = 0;
		while(true)
		{
			int best = Integer.MAX_VALUE;
			int bestr=0,bestc=0;
			for(int i = 0; i < 8;i++)
			{
				if(!l(r+x[i],c+y[i])) continue;
				if(board[r+x[i]].charAt(c+y[i]) == '*') continue;
				int acc = acc(board,r+x[i],c+y[i]);
				if(acc < best || (acc == best && r+x[i] < bestr) ||(acc == best && r+x[i] == bestr && c+y[i] < bestc))
				{
					best = acc;
					bestr = r+x[i];
					bestc = c+y[i];
				}
			}
			if(best == Integer.MAX_VALUE) break;
			board[bestr] = board[bestr].substring(0,bestc)+"*"+board[bestr].substring(bestc+1);
			r = bestr;
			c = bestc;
			//System.out.println(r+" "+c);
			//System.out.println(Arrays.toString(board));
			ans++;
		}
		return ans+1;
	}
	private int acc(String[] board, int r, int c) {
		int ans = 0;
		for(int i = 0; i < 8;i++)
		{
			if(l(r+x[i],c+y[i]))
				if(board[r+x[i]].charAt(c+y[i]) == '.') ans++;
		}
		return ans;
	}
	private boolean l(int i, int j) {
		
		return i >= 0 && i <= 7 && j >= 0 && j <= 7;
	}

}
