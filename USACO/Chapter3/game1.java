package USACO.Chapter3;
/*
ID: theycal2
LANG: JAVA
TASK: game1
 */
/* USACO Training
 * A Game
 * Type: DP
 * Solution: State is how many off of left and 
 * right we have taken.
 */
import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class game1 
{
	public static void main(String[] args) throws IOException 
	{
		long start = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("game1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("game1.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());

		N = Integer.valueOf(st.nextToken());
		board = new int[N];
		for(int i = 0; i < N;i++)
		{
			if(!st.hasMoreTokens()) st = new StringTokenizer(f.readLine());
			board[i] = Integer.valueOf(st.nextToken());
		}
		dp = new int[N][N];
		for(int i = 0; i < N;i++)		
			Arrays.fill(dp[i],Integer.MAX_VALUE);

		int total = 0;
		for(int i = 0; i < N;i++)
			total += board[i];

		int p1 = recur(0,0);
		int t = (total-p1)/2;
		if( p1 > 0)
			out.println(max(t,total-t)+" "+min(t,total-t));
		else
			out.println(min(t,total-t)+" "+max(t,total-t));

		out.close();
		System.out.println("$:"+(System.currentTimeMillis()-start));
		System.exit(0);
	}
	public static int recur(int l, int r) {
		if(l+r == N) return 0;
		if(dp[l][r] != Integer.MAX_VALUE) return dp[l][r];

		int score = 0;
		//p1 to go
		if((l+r) %2 == 0)
		{
			score = max(board[l]+recur(l+1,r),board[N-r-1]+recur(l,r+1));
		}
		else
		{
			score = min(recur(l+1,r)-board[l],recur(l,r+1)-board[N-r-1]);
		}
		dp[l][r] = score;
		return score;
	}
	static int[][] dp;
	static int N;
	static int[] board;
}

