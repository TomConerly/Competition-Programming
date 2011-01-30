package CMUInvitional.y2009;
import java.util.*;

/* CMU Spring Invitational 2009
 * Problem F: Optimal Cheating
 * Type: BFS
 * Solution: Run a BFS for shortest path because edge weights are 1.
 */

public class F {
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			L = sc.nextInt();
			int N = sc.nextInt();
			if(L == 0 && N == 0) break;
			
			board = new int[L];
			for(int i = 0; i < N;i++)
			{
				int x = sc.nextInt();
				int d = sc.nextInt();
				board[x-1] = d;
			}
			dp = new long[L];
			Arrays.fill(dp,Integer.MAX_VALUE);
			Queue<Long> pq = new LinkedList<Long>();
			pq.add(0L);
			while(pq.size() > 0)
			{       
				long l = pq.poll();
				int at = (int)(l &0xFFFFFFFFL);
				int dist = (int)(l >>> 32);

				if(dp[at] < dist) continue;

				for(int i = 3;i<=18;i++)
				{       
					int to = at+i+board(at+i);
					if(to >=L || to < 0) continue;
					if(dp[to] > dist+1) 
					{       
						dp[to] = dist+1; 
						pq.add((((long)dist+1L) << 32) | (to));
					}       
				}       
			}       
			if(dp[L-1] >= Integer.MAX_VALUE) System.out.println("IMPOSSIBLE");
			else System.out.println(dp[L-1]);
		}       
	}
	private static int board(int i)
	{
		if( i < 0 || i >= dp.length) return 0;
		return board[i];
	}
	static int[] board;
	static int L;
	static long[] dp;

}
