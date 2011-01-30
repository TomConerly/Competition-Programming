package USACO.Chapter1;
/*
ID: theycal2
LANG: JAVA
TASK: milk2
 */
/* USACO Training
 * Milking Cows
 * Type: Greedy
 * Solution: Combine overlapping time periods.
 */
import java.io.*;
import java.util.*;

public class milk2 
{
	public static void main(String[] args) throws IOException 
	{
		long start = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("milk2.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.valueOf(st.nextToken());

		int[][] data = new int[N][2];
		
		for(int i = 0; i < N;i++)
		{
			st = new StringTokenizer(f.readLine());
			data[i][0] = Integer.valueOf(st.nextToken());
			data[i][1] = Integer.valueOf(st.nextToken());
			
		}
		long[] d = new long[N];
		for(int i = 0; i < N;i++)
			d[i] = (((long)data[i][0]) << 32) | data[i][1];
		System.out.println("$:"+(System.currentTimeMillis()-start));
		
		Arrays.sort(d);
		boolean[] r = new boolean[N];
		int prev = -1;
		for(int at = 0;at < N;at++)
		{
			int e1 = (int)(d[at] &0xFFFFFFFF);
			int s1 = (int)(d[at] >>> 32);
			if(prev == -1)
			{
				prev = at;
				continue;
			}else{				
				int e2 = (int)(d[prev] &0xFFFFFFFF);
				int s2 = (int)(d[prev] >>> 32);
				
				if(s2 <= s1 && s1 <= e2)
				{
					r[at] = true;
					e2 = Math.max(e2,e1);
					d[prev] = (((long)s2 << 32) |e2);
				}else prev = at;
			}		
		}
		int maxDead = 0;
		int maxWork = 0;
		int prevEnd = -1;
		for(int at = 0; at < N;at++)
		{
			if(!r[at])
			{
				int e1 = (int)(d[at] &0xFFFFFFFF);
				int s1 = (int)(d[at] >>> 32);
				
				if(prevEnd != -1)
				{
					maxDead = Math.max(maxDead,s1-prevEnd);
				}
				maxWork = Math.max(maxWork,e1-s1);
				prevEnd = e1;
			}
		}

		out.println(maxWork+" "+maxDead);
		out.close();
		
		System.out.println("$:"+(System.currentTimeMillis()-start));
		System.exit(0);
	}
}

