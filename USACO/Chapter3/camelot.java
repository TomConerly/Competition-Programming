package USACO.Chapter3;
/*
ID: theycal2
LANG: JAVA
TASK: camelot
 */
/* USACO Training
 * Camelot
 * Type: Graph Theory
 * Solution: Just brute force all possible
 * solutions then add some heuristics.
 */
import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class camelot 
{
	public static void main(String[] args) throws IOException 
	{
		long start = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("camelot.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("camelot.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());

		R = Integer.valueOf(st.nextToken());
		C = Integer.valueOf(st.nextToken());

		st = new StringTokenizer(f.readLine());
		kingY = st.nextToken().charAt(0)-'A';
		kingX = Integer.valueOf(st.nextToken())-1;

		while(true)
		{
			if(st.hasMoreTokens())
			{
				ky.add(st.nextToken().charAt(0)-'A');
				kx.add(Integer.valueOf(st.nextToken())-1);
			}
			else
			{
				if(f.ready())
				{
					st = new StringTokenizer(f.readLine());
				}
				else break;
			}
		}
		N = kx.size();
		adj = new int[R][C][R][C];
		for(int x = 0; x < R;x++)
		{
			for(int y = 0; y < C;y++)
			{
				dijk(x,y);
			}
		}
		System.out.println("$:"+(System.currentTimeMillis()-start));
		int min = Integer.MAX_VALUE;
		int ontop = -1;
		for(int i = 0; i < N;i++)
		{
			if(kingX == kx.get(i) && kingY == ky.get(i))
			{
				ontop = i;
				break;
			}
		}

		if(N > 0)
		{
			int ax = 0;
			int ay = 0;
			for(int i = 0; i < N;i++)
			{
				ax += kx.get(i);
				ay += ky.get(i);
			}
			ax /= N;
			ay /= N;

			min = min(min,score(ax,ay,-1,min,false));
			if(ontop==-1)
			{
				for(int i = 0; i < N;i++)
				{
					min = min(min,score(ax,ay,i,min,false));
				}
			}
			else
			{
				min = min(min,score(ax,ay,ontop,min,true));
			}
		}

		for(int x = 0; x < R;x++)
		{
			for(int y = 0; y < C;y++)
			{
				int s = score(x,y,-1,min,false);
				if(s == Integer.MAX_VALUE) continue;
				min = min(min,s);
				if(ontop==-1)
				{
					for(int i = 0; i < N;i++)
					{
						min = min(min,score(x,y,i,min,false));
					}
				}
				else
				{
					min = min(min,score(x,y,ontop,min,true));
				}
			}

		}
		out.println(min);
		out.close();
		System.out.println("$:"+(System.currentTimeMillis()-start));
		System.exit(0);
	}
	private static int score(int x, int y, int k,int m,boolean ontop) {
		int score = 0;

		for(int i = 0; i < N;i++)
		{
			if(i==k) continue;
			score += adj[kx.get(i)][ky.get(i)][x][y];
		}
		if(score > m) return Integer.MAX_VALUE;

		if(k == -1)
		{
			if(!ontop)
			{
				int dx = abs(x-kingX);
				int dy = abs(y-kingY);
				score += min(dx,dy)+abs(dx-dy);
			}
		}
		else
		{
			if(ontop)
			{
				score += adj[kx.get(k)][ky.get(k)][x][y];
			}
			else
			{
				int min = Integer.MAX_VALUE;
				for(int r = 0; r < R;r++)
				{
					for(int c = 0; c < C;c++)
					{
						min = min(min,abs(r-kingX)+abs(c-kingY)+adj[kx.get(k)][ky.get(k)][r][c]+adj[r][c][x][y]);
					}
				}
				score += min;
			}			
		}
		//System.out.println(x+" "+y+" "+k+" "+score);
		return score;		
	}
	public static void dijk(int xs,int ys) {
		for(int i = 0; i < R;i++)
			Arrays.fill(adj[xs][ys][i],1000000);

		PriorityQueue<Long> pq = new PriorityQueue<Long>();
		pq.add((long)(xs<<16) | ys);

		while(pq.size() > 0)
		{
			long p = pq.poll();
			int x = (int)((p>>>16) &0xFFFF);
			int y = (int)(p &0xFFFF);
			int c = (int) (p>>>32);

			if(adj[xs][ys][x][y] < c) continue;
			adj[xs][ys][x][y] = c;

			for(int i = 0; i < moves.length;i++)
			{
				int nx = x+moves[i][0];
				int ny = y+moves[i][1];
				if(legal(nx,ny) && adj[xs][ys][nx][ny] > c+1)
				{
					adj[xs][ys][nx][ny] = c+1;
					pq.add((((long)(c+1))<<32)|(nx<<16)|(ny));
				}
			}
		}
		return;
	}
	public static boolean legal(int x, int y) {		
		return x>= 0 && x < R && y >= 0 && y < C;
	}
	static int[][] moves = new int[][] {{-2,1},{-2,-1},{2,1},{2,-1},{1,-2},{-1,-2},{1,2},{-1,2},};
	static int[][][][] adj;
	static ArrayList<Integer> kx = new ArrayList<Integer>();
	static ArrayList<Integer> ky = new ArrayList<Integer>();
	static int kingX,kingY;
	static int R,C,N;
}

