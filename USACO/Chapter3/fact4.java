package USACO.Chapter3;
/*
ID: theycal2
LANG: JAVA
TASK: fact4
 */
/* USACO Training
 * Factorial
 * Type: Number Theory
 * Solution: We only care about the last few digits. More than
 * just the last digit because there could be a bunch of stored up 2s
 * that we need to keep track of because 5s will come and make zeroes.
 */
import java.io.*;
import java.util.*;

public class fact4 
{
	public static void main(String[] args) throws IOException 
	{
		long start = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("fact4.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fact4.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.valueOf(st.nextToken());
		
		long at = 1;
		for(int i = 2; i <= N;i++)
		{
			at *= i;
			while(at %10 == 0) at /= 10;
			at = at %100000000000L;
		}
		out.println(at%10);
		
		out.close();
		System.out.println("$:"+(System.currentTimeMillis()-start));
		System.exit(0);
	}
}

