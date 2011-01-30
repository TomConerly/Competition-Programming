package TopCoder.Medium;
import java.util.*;
import static java.lang.Math.*;
/* TopCoder TCO 2008 Round 4
 * Medium Problem 500 Points: OlympicGames
 * Type: DP
 * Solution: Can't dp on [at][numSilver][numBronze] so dp on [at][numBetter][numSilver] = min # of bronze medals needed
 * to make numBetter of the countries at,at+1,... better than team 1 using only numSilver. Also use the trick to only keep 
 * track of one level of the table at a time.
 */
public class OlympicGames {
	int[][] me;
	int N;
	boolean[] pos;
	int[][] need;
	int L;
	public int worstPlace(String[] medals, int left) {
		L = left;
		me = new int[medals.length][3];
		N = medals.length;
		for(int i = 0; i < N;i++)
		{
			StringTokenizer st = new StringTokenizer(medals[i]);
			me[i][0] = Integer.parseInt(st.nextToken());
			me[i][1] = Integer.parseInt(st.nextToken());
			me[i][2] = Integer.parseInt(st.nextToken());
		}
		int[] willHave = new int[]{me[0][0]+left,me[0][1],me[0][2]};
		need = new int[N-1][3];
		pos = new boolean[N-1];
		int better = 0;
		Arrays.fill(pos,true);
		for(int i = 1; i < N;i++)
		{
			if(willHave[0] > me[i][0]) pos[i-1] = false;
			else if(me[i][0] > willHave[0]){
				better++;
				pos[i-1] = false;
			}else{
				need[i-1][1] = me[0][1]-me[i][1];
				need[i-1][2] = me[0][2]-me[i][2];
				if(need[i-1][1] < 0 || (need[i-1][1] == 0 && need[i-1][2] < 0)){
					better++;
					pos[i-1] = false;
				}
				if(need[i-1][2] < 0) need[i-1][2] = -1;
			}
		}
		int[][] dp = new int[N+1][left+1];
		for(int j = 1;j< dp.length;j++)
			Arrays.fill(dp[j],-2);
		
		for(int at = N-2;at>=0;at--)
		{
			
			if(!pos[at]) continue;
			
			int[][] dp2 = new int[N+1][left+1];
			for(int numBetter = 0; numBetter < dp.length;numBetter++)
			{
				for(int silver = 0; silver< dp[0].length;silver++)
				{
					int best = -2;
					best = r(best,dp[numBetter][silver]);
					if(numBetter > 0){
						int extra = need[at][2] == -1 ?0:1;

						if(silver >= need[at][1]+extra && need[at][1]+extra <= L)
						{
							best = r(best,dp[numBetter-1][silver-(need[at][1]+extra)]);
						}
						if(silver >= need[at][1] && extra == 1&& need[at][1]+need[at][2]+1 <= L)
						{
							int v = dp[numBetter-1][silver-(need[at][1])];
							if(v != -2)
								best = r(best,(v+need[at][2]+1));
							
						}
					}
					dp2[numBetter][silver] = best;
				}
			}
			dp = dp2;
		}
		
		int ans = 0;
		for(int i = 0; i < N;i++)
		{
			int v = dp[i][left];
			if(v == -2 || v > left) continue;
			ans = i;
		}
		
		//p(better+" "+ans);
		return better+ans+1;
	}
	public int r(int best, int pos)
	{
		if(pos == -2) return best;
		if(best == -2) return pos;
		return min(best,pos);
	}
	public void p(Object o){System.out.println(o);}
}
