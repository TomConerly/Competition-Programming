package USACO.Contest;
/*
ID: theycal2
LANG: JAVA
TASK: buyhay
 */
/* USACO Contest November 2008 Silver
 * Buying Hay
 * Type: DP
 * Solution: Just straight DP. Keep track
 * of cheapest to make h hay.
 */
import java.io.*;
import java.util.*;
import static java.lang.Math.*;

class buyhay {
	public static void main (String [] args) throws IOException {
		long start = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("buyhay.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("buyhay.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());   
		int H = Integer.parseInt(st.nextToken());   

		int[][] data = new int[N][2];
		for(int i = 0; i < N;i++)
		{
			st = new StringTokenizer(f.readLine());
			data[i][0] = Integer.parseInt(st.nextToken());
			data[i][1] = Integer.parseInt(st.nextToken());
		}
		int[] best = new int[H+1];
		Arrays.fill(best,Integer.MAX_VALUE);
		best[0] = 0;
		for(int i = 0; i < H;i++)
		{
			if(best[i] == Integer.MAX_VALUE) continue;
			for(int j = 0; j < N;j++)
			{
				if(i+data[j][0] > H) 
					best[H] = min(best[H],best[i]+data[j][1]);
				else
					best[i+data[j][0]] = min(best[i+data[j][0]],best[i]+data[j][1]);
			}
		}
		out.println(best[H]);   
		

		out.close();        
		System.out.println(System.currentTimeMillis()-start);
		System.exit(0);                     
	}
}
