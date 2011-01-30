package USACO.Chapter2;
/*
ID: theycal2
LANG: JAVA
TASK: maze1
 */
/* USACO Training
 * Overfencing
 * Type: Graph Search
 * Solution: Just Djikstra out from each exit.
 * This code is ugly as all hell.
 */
import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class maze1 
{
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("maze1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		board = new int[H][W];
		String[] b = new String[2*H+1];
		for(int i = 0; i < b.length;i++)		
			b[i] = f.readLine();

		int e11=0,e12=0,e21=0,e22=0;
		boolean foundFirst = false;

		for(int i = 0; i < H;i++)
		{
			for(int j = 0; j < W;j++)
			{
				int height = 2*i+1;
				int width  = 2*j+1;
				if(b[height-1].charAt(width) == '-')
				{
					board[i][j] += 1;

				}
				else
				{
					if(i == 0)
					{
						if(!foundFirst)
						{
							e11=i;
							e12=j;
						}
						else
						{
							e21=i;
							e22=j;
						}
						foundFirst = true;
					}
				}
				if(b[height+1].charAt(width) == '-')
				{
					board[i][j] += 2;

				}
				else
				{
					if(i == H-1)
					{
						if(!foundFirst)
						{
							e11=i;
							e12=j;
						}
						else
						{
							e21=i;
							e22=j;
						}
						foundFirst = true;
					}
				}
				if(b[height].charAt(width+1) == '|')
				{
					board[i][j] += 4;					
				}
				else{
					if(j == W-1)
					{
						if(!foundFirst)
						{
							e11=i;
							e12=j;
						}
						else
						{
							e21=i;
							e22=j;
						}
						foundFirst = true;
					}
				}
				if(b[height].charAt(width-1) == '|')
				{
					board[i][j] += 8;

				}
				else
				{
					if(j == 0)
					{
						if(!foundFirst)
						{
							e11=i;
							e12=j;
						}
						else
						{
							e21=i;
							e22=j;
						}
						foundFirst = true;
					}

				}
			}
		}
		int[][] best1 = new int[H][W];
		int[][] best2 = new int[H][W];
		for(int k = 0; k < H;k++)
		{
			Arrays.fill(best1[k],Integer.MAX_VALUE);
			Arrays.fill(best2[k],Integer.MAX_VALUE);
		}
		PriorityQueue<Long> pq = new PriorityQueue<Long>();

		pq.add((1L<<32)|(e11<<16)|e12);
		while(pq.size() > 0)
		{
			long p = pq.poll();
			int r = (int)((p >>> 16)&0xFFFF);
			int c = (int)(p&0xFFFF);
			int cost = (int)(p>>>32);

			if(best1[r][c] <= cost) continue;
			best1[r][c] = cost;

			if(canN(r,c))
			{
				if(cost+1 < best1[r-1][c])
					pq.add((((long)cost+1) << 32)|((r-1)<<16)|(c));
			}
			if(canS(r,c))
			{
				if(cost+1 < best1[r+1][c])
				{
					pq.add((((long)cost+1) << 32)|((r+1)<<16)|(c));
				}
			}
			if(canE(r,c))
			{
				if(cost+1 < best1[r][c+1])
					pq.add((((long)cost+1) << 32)|(r<<16)|(c+1));
			}
			if(canW(r,c))
			{
				if(cost+1 < best1[r][c-1])
					pq.add((((long)cost+1) << 32)|(r<<16)|(c-1));
			}
		}
		pq = new PriorityQueue<Long>();

		pq.add((1L<<32)|(e21<<16)|e22);
		while(pq.size() > 0)
		{
			long p = pq.poll();
			int r = (int)((p >>> 16)&0xFFFF);
			int c = (int)(p&0xFFFF);
			int cost = (int)(p>>>32);

			if(best2[r][c] <= cost) continue;
			best2[r][c] = cost;

			if(canN(r,c))
			{
				if(cost+1 < best2[r-1][c])
					pq.add((((long)cost+1) << 32)|((r-1)<<16)|(c));
			}
			if(canS(r,c))
			{
				if(cost+1 < best2[r+1][c])
				{
					pq.add((((long)cost+1) << 32)|((r+1)<<16)|(c));
				}
			}
			if(canE(r,c))
			{
				if(cost+1 < best2[r][c+1])
					pq.add((((long)cost+1) << 32)|(r<<16)|(c+1));
			}
			if(canW(r,c))
			{
				if(cost+1 < best2[r][c-1])
					pq.add((((long)cost+1) << 32)|(r<<16)|(c-1));
			}
		}
		int best = 0;
		for(int i = 0; i < H;i++)
		{
			for(int j = 0; j < W;j++)
			{
				best = max(best,min(best1[i][j],best2[i][j]));
			}
		}
		out.println(best);

		out.close();
	}
	static int[][] board;
	static int H,W;
	static boolean canN(int r, int c)
	{
		if(r-1 < 0) return false;
		if((board[r][c] & 1) == 0) return true;
		return false;
	}
	static boolean canS(int r, int c)
	{
		if(r+1 >= H) return false;
		if((board[r][c] & 2) == 0) return true;
		return false;
	}
	static boolean canE(int r, int c)
	{
		if(c+1 >= W) return false;
		if((board[r][c] & 4) == 0) return true;
		return false;
	}
	static boolean canW(int r, int c)
	{
		if(c-1 < 0) return false;
		if((board[r][c] & 8) == 0) return true;
		return false;
	}
}

