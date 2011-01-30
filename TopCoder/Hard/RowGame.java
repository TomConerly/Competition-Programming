package TopCoder.Hard;
import static java.lang.Math.*;

/* TopCoder SRM 450
 * Hard Problem 1000 Points: RowGame
 * Type: Greedy
 * Solution: For each location find the value of the best move from that location that goes
 * in either direction. You can either stay at this location for the rest of time, or 
 * move to another location with a better best move. (Note that if you have a good best move you can go
 * back and forth an arbitrary number of times using that best move). 
 * 
 * A location with a better best move will always be to your right, because you start on the far left.
 * If a better location was on your left, then you passed it so you could have stayed there.
 * 
 * At any time step find the better location to your right that you can get to in the minimum number of
 * time steps. Then go there.
 */


public class RowGame {
	public void p(Object s){System.out.println(s);}
	public long score(int[] board, int T) {
		B = board;
		N = board.length;
		long[] S = new long[board.length];
		int[] P = new int[board.length];
		long[][] C = new long[board.length][board.length];
		for(int i = 0; i < N;i++)
		{
			S[i] = Long.MIN_VALUE;
			for(int j = 0; j < N;j++)
			{
				long sum = 0;
				for(int k = min(i,j); k <= max(i,j);k++)
				{
					sum += B[k];
				}
				if(S[i] < sum)
					P[i] = j;
				S[i] = max(S[i],sum);
				C[i][j] = sum;
			}
		}
		
		long best = 0;
		int at = 0;
		int time = 0;
		long score = 0;
		while(true)
		{
			best = max(best,score + S[at]*(T-time));
			long dt = -1;
			int b = -1;
			for(int i = at+1;i < N;i++)
			{
				if(S[i] > S[at])
				{
					long c1 = -C[at][i];
					long c2 = -C[P[at]][i];
					
					long t1;
					if(score +C[at][i] >= 0)
						t1 = 0;
					else if(S[at] <= 0 && c1-score > 0)
						t1 = -1;
					else 
						t1 = ru(c1-score,S[at]);				
					
					long t2;
					if(score + S[at] >= 0 && score +C[P[at]][i]+S[at] >= 0)
						t2 = 1;
					else if(S[at] <= 0 && c2-score > 0)
						t2 = -1;
					else
						t2 = ru(c2-score,S[at]);					
					
					if(t1 >= 0 && (t1 %2 == 1))
						t1++;
					if(t2 >= 0 && (t2 %2 == 0))
						t2++;
					
					if(t1 >= 0 && (time+t1)%2 == 1)
						t1++;
					if(t2 >= 0 && (time+t2)%2 == 1)
						t2++;
					
					if(t1 < 0 && t2>=0)
						t1 = Long.MAX_VALUE;
					if(t2 < 0 && t1 >=0)
						t2 = Long.MAX_VALUE;
					if(min(t1,t2) >= 0 && (dt == -1 || min(t1,t2) < dt || (min(t1,t2) == dt && S[i] >= S[b])))
					{
						dt = min(t1,t2);
						b = i;
					}
				}
			}
			if(b == -1)
				break;
			if(time + dt+1 > T)
				break;
			score += S[at]*dt + ((dt) %2 == 0?C[at][b]:C[P[at]][b]);

			at = b;
			time += dt+1;
		}
		System.out.println(best);
		return best;
	}
	private long ru(long l, long m) {
		return (l+m-1)/m;
	}
	int N;
	int[] B;

}
