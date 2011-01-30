package USACO.Chapter2;
/*
ID: theycal2
LANG: JAVA
TASK: concom
 */
/* USACO Training
 * Controlling Companies
 * Type: Brute Force
 * Solution: Brute force it. 
 */
import java.io.*;
import java.util.*;

public class concom 
{
	public static void main(String[] args) throws IOException 
	{
		long start = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("concom.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		con = new int[Z+1][Z+1];
		ans = new ArrayList<Long>();
		
		for(int i = 0; i < N;i++)
		{
			st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			con[a][b] = Integer.parseInt(st.nextToken());
		}
		for(int i = 1; i <= Z;i++)
		{
			calc(i);
		}
		Collections.sort(ans);
		for(Long s: ans)
		{
			out.println((s>>>32) +" "+(s&0xFFFFFFFFL));
		}
		out.close();
		System.out.println(System.currentTimeMillis()-start);
	}
	static int[][] con;
	static int N;
	static ArrayList<Long> ans;
	static int Z = 100;
	
	public static void calc(int comp)
	{
		int[] control = new int[Z+1];
		boolean[] c = new boolean[Z+1];
		for(int i = 1; i <= Z;i++)
		{
			if(i==comp) continue;
			control[i] = con[comp][i];
			
		}
		while(true)
		{
			boolean done = true;
			for(int i = 1; i <= Z;i++)
			{
				if(i==comp) continue;
				if(!c[i] && control[i] > 50)
				{
					done = false;
					ans.add((((long)comp) << 32) | i);
					for(int j = 1; j <= Z;j++)
					{
						if(j==comp) continue;
						control[j] += con[i][j];
					}
					c[i] = true;
				}
			}
			if(done) break;
		}
	}
}

