package USACO.Chapter3;
/*
ID: theycal2
LANG: JAVA
TASK: ratios
 */
/* USACO Training
 * Feed Ratios
 * Type: Simulation
 * Solution: Simulation
 */
import java.io.*;
import java.util.*;

public class ratios 
{
	public static void main(String[] args) throws IOException 
	{
		long start = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("ratios.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ratios.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int[] g = new int[3];
		for(int i = 0; i < 3;i++)
			g[i] = Integer.valueOf(st.nextToken());
		
		int[][] p = new int[3][3];
		for(int i = 0; i < 3;i++)
		{
			st = new StringTokenizer(f.readLine());
			for(int j = 0; j < 3;j++)
			{
				p[i][j] = Integer.valueOf(st.nextToken());
			}
		}
		int bestS = -1;
		int besti=0,bestj=0,bestk=0;
		int bestR=0;
		for(int i = 0; i < 100;i++)
		{
			for(int j = 0; j < 100;j++)
			{
				for(int k = 0; k < 100;k++)
				{
					int[] t = new int[3];
									
					t[0] = p[0][0]*i+p[1][0]*j+p[2][0]*k;
					t[1] = p[0][1]*i+p[1][1]*j+p[2][1]*k;
					t[2] = p[0][2]*i+p[1][2]*j+p[2][2]*k;
					if((t[0] == 0 && g[0] != 0) || (t[1] == 0 && g[1] != 0) || (t[2] == 0 && g[2] != 0)) continue;
					if(t[0] < g[0] || t[1] < g[1] || t[2] < g[2]) continue;
					if(t[0]*g[1] == t[1]*g[0] && t[1]*g[2] == t[2]*g[1] && t[0]*g[2] == t[2]*g[0])
					{
						if(bestS == -1 || i+j+k < bestS)
						{
							bestS = i+j+k;
							besti = i;
							bestj = j;
							bestk = k;
							bestR = t[0]/g[0];
						}
					}
				}
			}
		}
		if(bestS == -1)
			out.println("NONE");
		else 
			out.println(besti+" "+bestj+" "+bestk+" "+bestR);		
		
		out.close();
		System.out.println("$:"+(System.currentTimeMillis()-start));
		System.exit(0);
	}
}

