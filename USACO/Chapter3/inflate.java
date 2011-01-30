package USACO.Chapter3;
/*
ID: theycal2
LANG: JAVA
TASK: inflate
 */
/* USACO Training
 * Score Inflation
 * Type: DP
 * Solution: Standard Knapsack to get it to run in time
 * take out the problems that are dominated.
 */
import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class inflate 
{
	public static void main(String[] args) throws IOException 
	{
		long start = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("inflate.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int M = Integer.valueOf(st.nextToken());
		int N = Integer.valueOf(st.nextToken());

		int[][] dat = new int[N][2];
		for(int i = 0; i < N;i++)
		{
			st = new StringTokenizer(f.readLine());
			dat[i][0] = Integer.valueOf(st.nextToken());
			dat[i][1] = Integer.valueOf(st.nextToken());
		}
		System.out.println("$:"+(System.currentTimeMillis()-start));
		boolean[] rem = new boolean[N];
		int count = 0;
		for(int i = 0; i < N;i++)
		{
			if(rem[i]) continue;
			for(int j = 0; j < N;j++)
			{
				if(dat[j][1] > dat[i][1] && dat[j][0] < dat[i][0])
				{
					if(rem[j] == false)
					{
						rem[j] = true;
						count++;
					}
				}
			}
		}

		int[][] newDat = new int[N-count][2];
		int at = 0;		
		for(int i = 0; i < N;i++)
		{			
			if(rem[i]) continue;
			newDat[at] = dat[i];
		
			at++;
		}
		dat = newDat;
		
		System.out.println("$:"+(System.currentTimeMillis()-start));
		int[] best = new int[M+1];
		int ans = 0;
		for(int i = 0; i <= M;i++)
		{
			for(int j = 0; j < dat.length;j++)
			{
				if(i-dat[j][1] >= 0)
					best[i] = max(best[i],best[i-dat[j][1]]+dat[j][0]);
			}
			ans = max(ans,best[i]);
		}
		out.println(ans);

		out.close();
		System.out.println("$:"+(System.currentTimeMillis()-start));
		System.exit(0);
	}

}

