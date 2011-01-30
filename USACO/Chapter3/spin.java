package USACO.Chapter3;
/*
ID: theycal2
LANG: JAVA
TASK: spin
 */
/* USACO Training
 * Spinning Wheels
 * Type: Simulation
 * Solution: Key insight is that the either line up
 * in the first 359 seconds or never will. Because at 360
 * seconds they all line up at 0 again so it cycles.
 */
import java.io.*;
import java.util.*;

public class spin 
{
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException 
	{
		long start = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("spin.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("spin.out")));
		StringTokenizer st;

		int[] speed = new int[5];
		ArrayList<Integer>[] cut = (ArrayList<Integer>[])new ArrayList[5];
		for(int i = 0; i < 5;i++)
		{
			st = new StringTokenizer(f.readLine());
			speed[i] = Integer.valueOf(st.nextToken());
			int num = Integer.valueOf(st.nextToken());
			cut[i] = new ArrayList<Integer>();
			for(int j = 0;j < num*2;j++)
			{
				cut[i].add(Integer.valueOf(st.nextToken()));
			}
		}
		boolean[][] empty = new boolean[5][360];
		for(int i = 0; i < 5;i++)
		{
			for(int j = 0; j < cut[i].size();j+=2) 
			{
				for(int k = 0;k <= cut[i].get(j+1);k++)
				{
					empty[i][(cut[i].get(j)+k)%360] = true;
				}
			}
		}
		int ans = -1;
		done:
		for(int t = 0; t < 360;t++)
		{
			boolean[][] e = new boolean[5][360];
			for(int i = 0; i < 5;i++)
			{
				int at = (t*speed[i])%360;
				for(int j = 0; j < 360;j++)
				{
					e[i][(j+at)%360] = empty[i][j];
				}
			}
			for(int j = 0; j < 360;j++)
			{
				boolean good = true;
				for(int i = 0; i < 5;i++)
				{
					good &= e[i][j];
				}
				if(good)
				{
					ans = t;
					break done;
				}
			}
		}
		if(ans == -1)
			out.println("none");
		else
			out.println(ans);

		out.close();
		System.out.println("$:"+(System.currentTimeMillis()-start));
		System.exit(0);
	}
}

