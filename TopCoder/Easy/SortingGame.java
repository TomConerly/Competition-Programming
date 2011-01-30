package TopCoder.Easy;
import java.util.*;

/* TopCoder SRM 397
 * Easy Problem 250 Points: SortingGame
 * Type: Dijkstra
 * Solution: Just search the space.
 */

public class SortingGame {

	int LEN;
	public int fewestMoves(int[] temp, int k) {
		LEN = temp.length;
		Queue<Long> q = new LinkedList<Long>();
		q.offer(to(temp));
		HashSet<Long> visited = new HashSet<Long>();
		while(q.size() > 0)
		{
			long l = q.poll();
			int move = (int)(l >>> 48);
			int[] board = board(l&0xFFFFFFFFFFFFL);
			
			boolean good = true;
			for(int i = 0; i < LEN;i++)
			{
				if(board[i] != i+1) good = false;
			}
			if(good) return move;
			
			if(visited.contains(to(board))) continue;
			visited.add(to(board));
			
			
			for(int i = 0; i < board.length-k+1;i++)
			{
				for(int j = 0; j < (k/2);j++)
				{
					int t = board[j+i];
					board[j+i] = board[i+k-j-1];
					board[i+k-j-1] = t;
				}
				
				q.add( (((long)move+1) << 48) | to(board));
				
				for(int j = 0; j < (k/2);j++)
				{
					int t = board[j+i];
					board[j+i] = board[i+k-j-1];
					board[i+k-j-1] = t;
				}
			}
		}
		return -1;
	}
	private int[] board(long l) {
		int[] board = new int[LEN];
		for(int i = LEN-1; i >= 0;i--)
		{
			board[i] = (int)(l & 0xF);
			l >>>= 4;
		}
		return board;
	}
	HashMap<Long,Integer> seen;
	public long to(int[] board)
	{
		long l = 0;
		for(int i = 0; i < board.length;i++)
		{
			l = (l << 4) | board[i];
		}
		return l;
	}

}
