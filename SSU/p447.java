package SSU;
import java.util.*;
import static java.lang.Math.*;
import java.io.*;

/* Saratov State University Online Judge
 * Problem 447: Optimal Rest
 * Type: DP
 * Solution: DP[length left][previous piece] = min to fill in. First try starting any note. Then try continuing on previous. This gives you minimum length.
 * Now go back through table looking for lexicographically first. A dot beats everything so try that first. Otherwise to the one which gives min length
 * that comes first which breaks everything but R1 and R16 because R1 is a prefix of R16. 6 comes before an R but after a . thus if the next thing is a dot
 * R1. is better than R16. but if the next thing is an R then R16R is better than R1R.
 */

public class p447 {
	public static void main(String[] args) throws IOException
	{		
		BufferedReader R = new BufferedReader(new InputStreamReader(System.in));
		String S = R.readLine();
		N = 0;
		int prev = 0;
		for(int i = 0; i < S.length();)
		{
			if(S.charAt(i) == 'R')
			{
				String a = S.substring(i+1,min(S.length(),i+3));
				if(a.length() == 2 && !Character.isDigit(a.charAt(1)))
					a = a.substring(0,1);
				int v = Integer.valueOf(a);
				i += 1+a.length();
				if(v == 1)
					v = 64;
				else if(v == 2)
					v = 32;
				else if(v == 4)
					v = 16;
				else if(v == 8)
					v = 8;
				else if(v == 16)
					v = 4;
				else if(v == 32)
					v = 2;
				else if(v == 64)
					v = 1;
				N += v;
				prev = v;
				
			}else{
				N += prev/2;
				prev /= 2;
				i++;
			}
		}
		int[][] DP = new int[N+1][7];
		Arrays.fill(DP[N],0);
		boolean[][] dot = new boolean[N+1][7];
		for(int i = N-1;i>= 0;i--)
		{
			for(int j = 0; j < 7;j++)
			{
				DP[i][j] = Integer.MAX_VALUE;
				for(int k = 0; k < 7;k++)
				{
					if(i+len[k] > N)
						continue;
					DP[i][j] = min(DP[i][j],cost[k]+DP[i+len[k]][k]);
				}
				if(i != 0 && j != 6 && i+len[j+1] <= N)
				{
					int cost = 1+DP[i+len[j+1]][j+1];
					if(cost <= DP[i][j])
					{
						dot[i][j] = true;
					}
					DP[i][j] = min(DP[i][j],cost);
				}
			}
		}
		StringBuilder ans = new StringBuilder();
		int at = 0;
		prev = 0;
		while(at != N)
		{
			if(dot[at][prev])
			{
				ans.append(".");
				at += len[prev+1];
				prev++;
				continue;
			}
			int i = at;
			int best = Integer.MAX_VALUE;
			int bestAt = 0;
			for(int k = 0; k < 7;k++)
			{
				if(i+len[k] > N)
					continue;
				int c = cost[k]+DP[i+len[k]][k];
				if(c < best || (c == best && order[k] < order[bestAt]) || (c == best && order[k] == order[bestAt] && dot[i+len[0]][0] == false))
				{
					best = c;
					bestAt = k;
				}
			}
			at +=len[bestAt];
			prev = bestAt;
			ans.append(str[bestAt]);
		}	
		System.out.println(ans);
	}
	static String[] str = {"R1","R2","R4","R8","R16","R32","R64"};
	static int[] order = {0,1,3,5,0,2,4};
	static int[] len = {64,32,16,8,4,2,1};
	static int[] cost = {2,2,2,2,3,3,3};
	static int N;
}
