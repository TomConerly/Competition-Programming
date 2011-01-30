package USACO.Chapter3;
/*
ID: theycal2
LANG: JAVA
TASK: rockers
 */
/* USACO Training
 * Raucous Rockers
 * Type: Brute Force
 * Solution: Each song is either used or not
 * 2^n is easily brute forceable.
 */
import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class rockers 
{
	public static void main(String[] args) throws IOException 
	{
		long start = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("rockers.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rockers.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());

		N = Integer.valueOf(st.nextToken());
		T = Integer.valueOf(st.nextToken());
		M = Integer.valueOf(st.nextToken());

		st = new StringTokenizer(f.readLine());
		len = new int[N];
		for(int i = 0; i < N;i++)
		{
			len[i] = Integer.valueOf(st.nextToken());
		}
		best = 0;
		recur(0,0,T,0);
		out.println(best);
		out.close();
		System.out.println("$:"+(System.currentTimeMillis()-start));
		System.exit(0);
	}
	static int best;
	static int[] len;
	static int N,T,M;
	
	private static void recur(int at, int disk, int timeLeft,int used) {
		//System.out.println(at+" "+disk+" "+timeLeft+" "+used);
		if(at == N)
		{
			best = max(best,used);
			return;
		}
		recur(at+1,disk,timeLeft,used);
		
		if(timeLeft >= len[at])
		{
			recur(at+1,disk,timeLeft-len[at],used+1);
		}
		else
		{
			if(disk < M-1 && T >= len[at])
				recur(at+1,disk+1,T-len[at],used+1);
		}
	}
	
}

