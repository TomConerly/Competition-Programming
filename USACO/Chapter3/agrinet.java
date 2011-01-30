package USACO.Chapter3;
/*
ID: theycal2
LANG: JAVA
TASK: agrinet
 */
/* USACO Training
 * Agri-Net
 * Type: MST
 * Solution: Run MST
 */
import java.io.*;
import java.util.*;


public class agrinet 
{
	public static void main(String[] args) throws IOException 
	{
		long start = System.currentTimeMillis();
		f = new BufferedReader(new FileReader("agrinet.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("agrinet.out")));
		st = new StringTokenizer(f.readLine());
		int N = Integer.valueOf(st.nextToken());
		
		int[][] adj = new int[N][N];
		for(int i = 0; i < N;i++)
		{
			for(int j = 0; j < N;j++)
			{
				adj[i][j] = nextInt();
			}
		}
		
		out.println(Prim(adj));
		
		
		out.close();
		System.out.println("$:"+(System.currentTimeMillis()-start));
		System.exit(0);
	}	
	public static int Prim(int[][] adj)
	{
		boolean[] covered = new boolean[adj.length];
		covered[0]=true;
		int cost = 0;
		for(int i = 0; i < adj.length-1;i++)
		{
			int min = Integer.MAX_VALUE;
			long minAt = 0;
			for(int j = 0; j < adj.length;j++){
				for(int k = 0; k < adj.length;k++)
				{
					if(covered[j] && !covered[k] && adj[j][k] < min)
					{
						min = adj[j][k];
						minAt = ( ((long)j) << 32) | ((long)k);
					}
				}
			}
			covered[(int)(minAt & 0xFFFFFFFFL)] = true;
			cost += min;
		}
		return cost;
	}
	
	static BufferedReader f;
	static StringTokenizer st;
	public static int nextInt() throws IOException
	{
		if(st.hasMoreTokens())
		{
			return Integer.valueOf(st.nextToken());
		}
		else
		{
			st = new StringTokenizer(f.readLine());
			return nextInt();
		}
	}
}

