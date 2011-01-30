package USACO.Chapter3;
/*
ID: theycal2
LANG: JAVA
TASK: range
 */
/* USACO Training
 * Home On the Range
 * Type: DP
 * Solution: A square of size ixi is only a square
 * if the four sqaures of size (i-1)x(i-1) are squares.
 */
import java.io.*;
import java.util.*;

public class range 
{
	public static void main(String[] args) throws IOException 
	{
		long start = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("range.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("range.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int N = Integer.valueOf(st.nextToken());
		boolean[][] s = new boolean[N][N];
		for(int i = 0; i < N;i++)
		{
			String str = f.readLine();
			for(int j = 0; j < N;j++)
			{
				s[i][j] = str.charAt(j) == '1';
			}
		}
		int[] count = new int[N+1];
		for(int i = 2; i <= N;i++)
		{
			boolean[][] next = new boolean[s.length-1][s.length-1];
			for(int x = 0; x < N-i+1;x++)
			{
				for(int y = 0; y < N-i+1;y++)
				{
					
					if(s[x][y] && s[x+1][y] && s[x][y+1] && s[x+1][y+1])
					{
						count[i]++;
						next[x][y] = true;
					}else next[x][y] = false;
				}
			}
			s = next;
		}
		for(int i = 2; i <= N;i++)
		{
			if(count[i] != 0)
				out.println(i+" "+count[i]);
		}
		
		
		out.close();
		System.out.println("$:"+(System.currentTimeMillis()-start));
		System.exit(0);
	}
}

