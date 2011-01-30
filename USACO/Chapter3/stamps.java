package USACO.Chapter3;
/*
ID: theycal2
LANG: JAVA
TASK: stamps
 */
/* USACO Training
 * Stamps
 * Type: DP
 * Solution: Standard DP.
 */
import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class stamps 
{
	public static void main(String[] args) throws IOException 
	{
		long start = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("stamps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stamps.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int K = Integer.valueOf(st.nextToken());
		int N = Integer.valueOf(st.nextToken());
		
		st = new StringTokenizer(f.readLine());
		int[] v = new int[N];
		for(int i = 0; i < N;i++)
		{
			if(!st.hasMoreTokens())
				st = new StringTokenizer(f.readLine());
			v[i] = Integer.valueOf(st.nextToken());
		}
		int[] m = new int[K*10000+2];
		Arrays.fill(m,1000000);
		m[0] = 0;
		int ans = 0;
		for(int j = 0; j < m.length;j++)
		{
			for(int i = 0; i<N;i++)
			{
				if(j-v[i] >= 0)
					m[j] = min(m[j],m[j-v[i]]+1);
			}
			if(m[j] >K)
			{
				ans = j;
				break;
			}
		}
		out.println(ans-1);
		out.close();
		System.out.println("$:"+(System.currentTimeMillis()-start));
		System.exit(0);
	}
}

