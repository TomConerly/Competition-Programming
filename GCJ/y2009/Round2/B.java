package GCJ.y2009.Round2;
import java.util.*;

/* Google Code Jam 2009 Round 2
 * Problem B: A Digging Problem
 * Type: DP
 * Solution: Keep track of x,y,numleft dug out, num right dugout. Then just iterate over all.
 * Lots of cases. 
 */

public class B
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int zz = 1; zz <= T;zz++)
		{
			System.err.println(zz);
			System.out.print("Case #"+zz+": ");
			R  = sc.nextInt();
			C = sc.nextInt();
			Z = C+9;
			F = sc.nextInt();
			grid = new char[C][R];
			for(int i = 0; i < R;i++)
			{
				String s = sc.next();
				for(int j = 0; j < C;j++)
				{
					grid[j][i] = s.charAt(j);
				}
			}
			int ans = bfs();
			if(ans == -1)
				System.out.println("No");
			else
				System.out.println("Yes "+ans);
		}
	}
	static int Z;
	private static int bfs() 
	{
		
		int[][][][] best = new int[C][R][C+10][C+10];
		for(int i = 0; i < best.length;i++)
			for(int j = 0; j < best[0].length;j++)
				for(int k = 0; k < best[0][0].length;k++)
						Arrays.fill(best[i][j][k],-1);
		
		PriorityQueue<State> q = new PriorityQueue<State>();
		q.add(new State(0,0,0,Z,Z));
	
		while(q.size() > 0)
		{
			State s = q.poll();
			if(best[s.x][s.y][s.left][s.right] != -1 && best[s.x][s.y][s.left][s.right] <= s.c)
				continue;
			best[s.x][s.y][s.left][s.right] = s.c;
			if(s.y == R-1)
			{
				return s.c;
			}
			
			if(s.x-1 >= 0)
			{
				if(grid[s.x-1][s.y] == '.')
				{
					int ny = fall(s.x-1,s.y,0);
					if(ny != -1)
					{
						if(ny == s.y)
							q.add(new State(s.x-1,ny,s.c,s.left,s.right));
						else
							q.add(new State(s.x-1,ny,s.c,Z,Z));
					}
				}
				if(s.left <= s.x-1 && s.x-1 <= s.right)
				{
					int ny = fall(s.x-1,s.y,0);
					if(ny == s.y)
						q.add(new State(s.x-1,ny,s.c,s.left,s.right));
					else if(ny != -1)					
						q.add(new State(s.x-1,ny,s.c,Z,Z));
					
				}
				for(int i = 0;i< C-s.x;i++)
				{
					if((grid[s.x+i][s.y] == '.' ||( s.left <= s.x+i && s.x+i <= s.right) ) &&(fall(s.x+i,s.y,0) == s.y) && (grid[s.x-1+i][s.y] == '.' || (s.left <= s.x-1+i && s.x-1+i <= s.right)) && grid[s.x-1+i][s.y+1] == '#')
					{
						int ny = fall(s.x-1+i,s.y+1,1);
						if(ny == s.y+1)
							q.add(new State(s.x-1+i,ny,s.c+i+1,s.x-1,s.x+i-1));
						else if(ny != -1)
							q.add(new State(s.x-1+i,ny,s.c+i+1,Z,Z));
					}else{
						break;
					}
				}
			}
			if(s.x+1 <C)
			{
				if(grid[s.x+1][s.y] == '.')
				{
					int ny = fall(s.x+1,s.y,0);
					if(ny != -1)
					{
						if(ny == s.y)
							q.add(new State(s.x+1,ny,s.c,s.left,s.right));
						else
							q.add(new State(s.x+1,ny,s.c,Z,Z));
					}
				}
				if(s.left <= s.x+1 && s.x+1 <= s.right)
				{
					int ny = fall(s.x+1,s.y,0);
					if(ny == s.y)
						q.add(new State(s.x+1,ny,s.c,s.left,s.right));
					else if(ny != -1)					
						q.add(new State(s.x+1,ny,s.c,Z,Z));
					
				}
				for(int i = 0; i < s.x+1 ;i++)
				{
					if((grid[s.x-i][s.y] == '.' ||( s.left <= s.x-i && s.x-i <= s.right) ) &&(fall(s.x-i,s.y,0) == s.y) && (grid[s.x+1-i][s.y] == '.' || (s.left <= s.x+1-i && s.x+1-i <= s.right)) && grid[s.x+1-i][s.y+1] == '#')
					{					
						int ny = fall(s.x+1-i,s.y+1,1);
						if(ny == s.y+1)
							q.add(new State(s.x+1-i,ny,s.c+i+1,s.x+1-i,s.x+1));
						else if(ny != -1)
							q.add(new State(s.x+1-i,ny,s.c+i+1,Z,Z));
					}else{
						break;
					}
				}
			}
		}
		return -1;
	}
	static int fall(int x, int y, int d)
	{
		int at = y;
		int dist = d;
		while(at+1 < R && grid[x][at+1] == '.')
		{
			dist++;
			at++;
			if(dist > F)
				return -1;
		}
		return at;
	}
	static char[][] grid;
	static int R,C,F;
	private static class State implements Comparable<State>
	{
		int x,y,c;
		int left;
		int right;
		public State(int a, int b, int d, int l,int r)
		{
			x = a;
			y = b;
			c = d;
			left = l;
			right = r;
		}
		@Override
		public int compareTo(State arg0) {
			return Integer.valueOf(c).compareTo(arg0.c);
		}
		
	}
}
