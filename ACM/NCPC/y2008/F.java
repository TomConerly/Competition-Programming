package ACM.NCPC.y2008;
import java.util.*;

/* ACM NCPC 2008
 * Problem F: Fixing the Bugs
 * Type: DP
 * Solution: It is greedy in that you always pick the next move that maximizes your expectation for that move.
 * Assume every hour doesn't fix a bug you get a ordering of the bugs. You will follow this order unless you fix
 * a bug, then you will remove all other entries of the bug from the list. Now dp over which bugs solved, how many hours
 * left, and where in the list you are. Where in the list you are is equivalent to how many hours have you spent
 * working on any bug which you have yet to solve.
 */

public class F
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextInt())
		{
			B = sc.nextInt();
			T = sc.nextInt();
			F = sc.nextDouble();
			P = new double[B];
			S = new int[B];
			for(int i = 0; i < B;i++)
			{
				P[i] = sc.nextDouble();
				S[i] = sc.nextInt();
			}
			dp = new double[1<<B][T+1][T+1];
			for(int i = 0; i < dp.length;i++)
				for(int j = 0; j < dp[0].length;j++)
					Arrays.fill(dp[i][j],-1);
			
			idx = new int[1<<B][T+1];

			int[] times = new int[B];
			double[] prob = new double[B];
			for(int i = 0; i < B;i++)
				prob[i] = P[i];
			order = new int[B*100];
			porder = new double[B*100];
			nt = new int[B*100];
			for(int z = 0; z < B*100;z++)
			{
				double best = 0.0;
				int bestAt = -1;
				double bestp = 0.0;
				for(int i = 0; i < B;i++)
				{
					if(times[i] == 100)
						continue;
					double ev = prob[i]*S[i];
					if(bestAt == -1 || ev > best)
					{
						best = ev;
						bestAt = i;
						bestp = prob[i];
					}
				}
				order[z] = bestAt;
				nt[z] = times[bestAt];
				times[bestAt]++;
				prob[bestAt] *= F;
				porder[z] =bestp;
				
			
			}
			
			System.out.format("%.07f\n",recur(0,0,0,0));
		}
	}
	public static double recur(int left, int at, int uh, int index)
	{
		if(left == (1<<B)-1)
			return 0.0;
		if(at == T)
			return 0.0;		
		
		if(dp[left][at][uh] != -1)
		{
			return dp[left][at][uh];
		}	
		
		while(((1<<order[index]) & left) != 0)
			index++;
		
		double ans = 0.0;
		
		ans += porder[index]* (recur(left+(1<<order[index]),at+1,uh-nt[index],index+1)
				+S[order[index]]);
		
		ans += (1-porder[index])*recur(left,at+1,uh+1,index+1);
		
		dp[left][at][uh] = ans;
		return ans;
	}
	static int[] nt;
	static int[][] idx;
	static int[] order;
	static double[] porder;
	static double[][][] dp;
	static int B,T;
	static double F;
	static double[] P;
	static int[] S;
}
