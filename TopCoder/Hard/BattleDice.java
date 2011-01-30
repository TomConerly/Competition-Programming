package TopCoder.Hard;
import static java.lang.Math.*;
/* TopCoder 2008 TCO Round 3
 * Medium Problem 500 Points: BattleDice
 * Type: BruteForce
 * Solution: There are actually not that many possible dice when you consider order doesn't
 * matter so just try them all.
 */
public class BattleDice {

	int R;
	int N;
	int[] best;
	int[] die1,die2;
	double bestS;
	int[] n1,n2;
	public int[] die3(int[] a, int[] b, int range) {
		die1 = a;
		die2 = b;
		R = range;
		N = die1.length;
		best = null;
		n1 = new int[R];
		n2 = new int[R];
		for(int c:die1)		
			n1[c-1]++;		
		for(int c:die2)
			n2[c-1]++;
		ab = prob(n1,n2);
		bestS = -1;
		count(0,1, new int[R]);
		return best;
	}
	double ab;
	public void count(int at, int num,int[] set)
	{
		if(at == N)
		{
			double ac = prob(n1,set);
			double bc = prob(n2,set);
			double score = min(max(1-ab,1-ac),min(max(ab,1-bc),max(ac,bc)));
			if(abs(bestS-score) < 1e-8)
			{
				int[] ans = new int[N];
				int a = 0;
				for(int i = 0; i < R;i++)
				{
					for(int j = 0; j < set[i];j++)
						ans[a++] = i+1;
				}
				for(int i = 0; i < N;i++)
				{
					if(ans[i] < best[i])
					{
						best = new int[N];
						for(int j = 0; j < N;j++)
							best[j] = ans[j];
						bestS = score;
						return;
					}else if(ans[i] > best[i]) return;
				}
				return;
			}else{
				if(best == null || score > bestS)
				{
					int[] ans = new int[N];
					int a = 0;
					for(int i = 0; i < R;i++)
					{
						for(int j = 0; j < set[i];j++)
							ans[a++] = i+1;
					}
					best = new int[N];
					for(int i = 0; i < N;i++)
						best[i] = ans[i];
					bestS = score;
				}
			}
			return;
		}else{
			for(int i = num;i<=R;i++)
			{
				set[i-1]++;
				count(at+1,i,set);
				set[i-1]--;
			}
		}
	}
	public double prob(int[] n1, int[] n2)
	{
		
		double tie = 0.0;
		for(int i = 0; i < n1.length;i++)
			tie += n1[i]*n2[i];

		double win = 0.0;
		int less = 0;
		for(int i = 0; i < n1.length;i++)
		{
			win += n1[i]*less;
			less += n2[i];
		}

		tie /= N*N;
		win/= N*N;
		double lose = 1-win-tie;
		return win + tie*(win/(win+lose));
	}
	public void p(Object o){System.out.println(o);}
}
