package USACO.Chapter2;
/*
ID: theycal2
LANG: JAVA
TASK: nocows
 */
/* USACO Training
 * Arithmetic Progressions
 * Type: DP
 * Solution: DP table for # of ways to do height/num nodes.
 * Split it up for whether we strictly need to get height or not.
 */
import java.io.*;
import java.util.*;

public class nocows 
{
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(new File("nocows.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));

		int N = sc.nextInt();
		int K = sc.nextInt();
		int[][] dp = new int[K+1][N+1];
		int[][] dp2 = new int[K+1][N+1];

		for(int height = 1; height <= K;height++)
		{
			for(int num = 1;num <=N;num++)
			{
				if(num == 1 && height == 1)dp[height][num]= 1; 
				else if(num == 1) dp[height][num]= 0; 
				else if(height == 1)dp[height][num]= 0; 
				else{
					long total = 0;
					for(int i = 0;i <= num-1;i++)
					{		
						total += dp[height-1][num-1-i]*dp2[height-1][i];
						total += dp2[height-1][num-1-i]*dp[height-1][i];
						total += dp[height-1][num-1-i]*dp[height-1][i];	
					}
					dp[height][num]= (int)(total%9901);
				}


				if(height == 1) dp2[height][num] = 0;
				else if(num == 1) dp2[height][num] = 1;
				else{
					long total = 0;
					for(int i = 0;i <= num-1;i++)
					{		
						total += dp2[height-1][num-1-i]*dp2[height-1][i];
					}
					dp2[height][num]= (int)(total%9901);
				}
			}
		}

		out.println(dp[K][N]);
		out.close();
	}
}

