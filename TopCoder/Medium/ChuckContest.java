package TopCoder.Medium;
import java.util.*;
import static java.lang.Math.*;

/* TopCoder TCO09 Round 4
 * Medium Problem 500 Points: ChuckContest
 * Type: DP
 * Solution: DP is for where you are at, and how many problems you have solved, and a value % 20, the range of values that you can 
 * legally have. Ex: At time 2, you can have 3 problem solved and if your time penalty is 5 mod 20 it must be between 25 and 85.
 * This is not that many states. To update you take the previous state and try solving some number of problems in this state, using
 * DP to figure out what ranges of time penalties you can get from solving that number of problems. Then ensure that you follow the rules,
 */

public class ChuckContest {


	public String chuckRules(int numProblems, String[] lowerBounds, String[] upperBounds, int[] partTimes) {
		NP = numProblems;
		int N = partTimes.length;
		T = partTimes;
		lP = new int[N];
		lT = new int[N];
		hP = new int[N];
		hT = new int[N];
		for(int i = 0; i < N;i++)
		{
			StringTokenizer st = new StringTokenizer(lowerBounds[i]);
			lP[i] = Integer.parseInt(st.nextToken());
			lT[i] = Integer.parseInt(st.nextToken())-1;			
			st = new StringTokenizer(upperBounds[i]);
			hP[i] = Integer.parseInt(st.nextToken());
			hT[i] = Integer.parseInt(st.nextToken())+1;
		}
		dpMin = new int[N+1][NP+1][];	
		dpMin[0][0] = new int[20];
		Arrays.fill(dpMin[0][0],Integer.MAX_VALUE);
		dpMin[0][0][0] = 0;
		for(int i = 1; i < dpMin[0].length;i++)
		{
			dpMin[0][i] = new int[20];
			Arrays.fill(dpMin[0][i],Integer.MAX_VALUE);
		}
		
		dpMax = new int[N+1][NP+1][];	
		dpMax[0][0] = new int[20];
		Arrays.fill(dpMax[0][0],Integer.MAX_VALUE);
		dpMax[0][0][0] = 0;
		for(int i = 1; i < dpMin[0].length;i++)
		{
			dpMax[0][i] = new int[20];
			Arrays.fill(dpMax[0][i],0);
		}
		dp = new int[N][NP+1][];
		int aP=-1,aT=0;
		for(int i = 0; i <= NP;i++)	
		{
			int[] all = rMin(N,i);
			for(int j = 0;j < 20;j++)
			{
				int t = all[j];
				if(t != Integer.MAX_VALUE)
				{
					if(i > aP || (i== aP && t < aT))
					{
						aP = i;
						aT = t;
					}
				}	
			}
		}
		if(aP != -1) return aP+" "+aT;
		else return "";
	}
	int[][][] dp;
	private int[] timePenalty(int at, int numSolved) {
		if(dp[at][numSolved] != null) return dp[at][numSolved];
		int timeStart = (at == 0)?1:(T[at-1]+1);
		int timeEnd = T[at];
		int[] ans = new int[20];
		Arrays.fill(ans,Integer.MAX_VALUE);
		done = new int[numSolved+1][20];
		for(int i = 0; i < done.length;i++)
			Arrays.fill(done[i],Integer.MAX_VALUE);		
		recur(ans,timeStart,timeEnd,0,numSolved,0);
		dp[at][numSolved] = ans;
		return ans;
	}
	int[][] done;
	private void recur(int[] ans, int timeStart, int timeEnd, int at,int numSolved, int sum) {
		if(done[at][sum%20] <= sum) return;
		done[at][sum%20] = sum;
		if(at == numSolved)
		{
			ans[sum%20] = min(ans[sum%20],sum);
		}else{
			for(int i = timeStart; i<= min(timeEnd,timeStart+19);i++)
				recur(ans,timeStart,timeEnd,at+1,numSolved,sum+i);
		}
	}
	//using timeZones 0...at-1
	//solving solved problems
	//what is min time penalty if time penalty tP % 20 = t
	int[] rMin(int at, int solved)
	{
		if(dpMin[at][solved] != null) return dpMin[at][solved];
		dpMin[at][solved] = new int[20];
		dpMax[at][solved] = new int[20];
		Arrays.fill(dpMin[at][solved],Integer.MAX_VALUE);
		int timePeriod = at-1;
		for(int s = 0; s <= solved;s++)
		{
			if(solved < lP[timePeriod] || solved > hP[timePeriod]) continue;
			int[] lowTime = rMin(at-1,solved-s);
			int[] highTime = dpMax[at-1][solved-s];
			int[] timePenalty = timePenalty(timePeriod,s);			
			for(int i = 0; i < 20;i++)
			{
				if(lowTime[i] == Integer.MAX_VALUE) continue;
				for(int j = 0; j < 20;j++)
				{
					if(timePenalty[j] == Integer.MAX_VALUE) continue;
					int time = lowTime[i]+timePenalty[j];					
					if(solved == hP[timePeriod])
					{
						if(time < hT[timePeriod])
						{
							if(s != 0)
							{
								int diff = (int)ceil((hT[timePeriod]-time)/20.0);
								time += diff*20;
							}else{
								int diff = 20*((int)ceil((hT[timePeriod]-time)/20.0));
								if(lowTime[i]+diff < highTime[i])
									time += diff;
								else continue;
							}
						}
					}
					if(solved == lP[timePeriod])
					{
						if(time > lT[timePeriod]) continue;
					}
					//p(at+" "+solved+" "+time);
					dpMin[at][solved][time%20] = min(dpMin[at][solved][time%20],time);
					if(solved == lP[timePeriod])
					{
						int diff = (int)floor((lT[timePeriod]-time)/20.0);
						time += diff*20;
						dpMax[at][solved][time%20] = max(dpMax[at][solved][time%20],time);
					}else{
						dpMax[at][solved][time%20] = Integer.MAX_VALUE;
					}
				}
			}
		}
		return dpMin[at][solved];		
	}	

	int NP;
	int[] lP,lT,hP,hT,T;
	int N;
	int[][][] dpMin,dpMax;
	public void p(Object o){System.out.println(o);}
}
