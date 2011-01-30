package USACO.Contest;
/*
ID: theycal2
LANG: JAVA
TASK: mtime
 */
/* USACO Contest November 2008 Silver
 * Time Management
 * Type: Greedy
 * Solution: There is a simple greedy algorithm.
 * Work backwards from the end and you know at each point
 * when a job has to end then you can work forwards.
 */
import java.io.*;
import java.util.*;
import static java.lang.Math.*;

class mtime {
	public static void main (String [] args) throws IOException {
		long start = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("mtime.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mtime.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());   

		long[] data = new long[N];
		for(int i = 0; i < N;i++)
		{
			st = new StringTokenizer(f.readLine());
			int t = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			data[i] = (((long)s)<< 32) | t;
		}
		Arrays.sort(data);
		long time = Long.MAX_VALUE;
		for(int i = N-1;i>=0;i--)
		{
			int s = (int)(data[i] >>> 32);
			int t = (int)(data[i] &0xFFFFFFFFL);
			time = min(time,s);
			time -= t;
			if(time < 0) break;
		}
		if(time >=0) out.println(time);
		else out.println(-1);
		

		out.close();        
		System.out.println(System.currentTimeMillis()-start);
		System.exit(0);                     
	}
}
