package USACO.Chapter3;
/*
ID: theycal2
LANG: JAVA
TASK: shopping
 */
/* USACO Training
 * Shopping
 * Type: DP
 * Solution: Standard DP, pain to code.
 */
import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class shopping 
{
	public static void main(String[] args) throws IOException 
	{
		long start = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("shopping.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shopping.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		S = Integer.valueOf(st.nextToken());
		deal = new int[S][12];
		for(int i = 0; i < S;i++)
		{
			st = new StringTokenizer(f.readLine());
			int n = Integer.valueOf(st.nextToken());
			deal[i][0] = n;
			for(int j = 1; j < 2*n+2;j++)
			{
				deal[i][j] = Integer.valueOf(st.nextToken());
			}
		}
		st = new StringTokenizer(f.readLine());
		B = Integer.valueOf(st.nextToken());

		int[] s = new int[5];
		items = new int[B][3];
		for(int i = 0; i < B;i++)
		{
			st = new StringTokenizer(f.readLine());
			for(int j = 0; j < 3;j++)
			{
				items[i][j] = Integer.valueOf(st.nextToken());
			}
			s[i] = items[i][1];
		}
		dp = new int[6][6][6][6][6];
		for(int i = 0; i < 6;i++)		
			for(int j = 0; j < 6;j++)			
				for(int k = 0; k < 6;k++)				
					for(int m = 0; m < 6;m++)
						Arrays.fill(dp[i][j][k][m],-1);


		int ans = recur(s);
		out.println(ans);
		out.close();
		System.out.println("$:"+(System.currentTimeMillis()-start));
		System.exit(0);
	}
	static int S;
	static int B;
	static int[][] items;
	static int[][] deal;
	static int[][][][][] dp;
	public static int recur(int[] t)
	{
		boolean b = (t[0] == 1 && t[1] == 2);
		if(t[0] == 0 && t[1] == 0 && t[2] == 0 && t[3] == 0 && t[4] == 0) return 0;
		if(dp[t[0]][t[1]][t[2]][t[3]][t[4]] != -1) return dp[t[0]][t[1]][t[2]][t[3]][t[4]];

		int min = Integer.MAX_VALUE;
		if(b)
			System.out.print("");
		for(int i = 0; i < S;i++)
		{
			int[] c = new int[5];
			for(int j = 0; j < 5;j++)
				c[j] = t[j];

			boolean legal1 = true;
			for(int j = 1; j < deal[i][0]*2;j+=2)
			{
				boolean legal = false;
				for(int k = 0; k < B;k++)
				{
					if(items[k][0] == deal[i][j])
					{
						if(c[k] >= deal[i][j+1])
						{
							c[k] -= deal[i][j+1];
							legal = true;
						}
						else
							break;
					}
				}
				if(!legal) 
				{
					legal1=false;
					break;
				}
			}
			if(legal1)
			{
				min = min(recur(c)+deal[i][deal[i][0]*2+1],min);
			}
		}
		for(int i = 0; i < B;i++)
		{
			int[] c = new int[5];
			for(int j = 0; j < 5;j++)
				c[j] = t[j];

			if(c[i] > 0)
			{
				c[i]--;
				min = min(recur(c)+items[i][2],min);
			}
		}
		/*for(int i = 0; i < 5;i++)
			System.out.print(t[i]+" ");
		System.out.print("\t cost: "+min);
		System.out.println();*/
		dp[t[0]][t[1]][t[2]][t[3]][t[4]] = min;
		return min;
	}
}

