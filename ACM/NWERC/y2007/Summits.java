package ACM.NWERC.y2007;
import java.util.*;
import static java.lang.Math.*;

/* ACM NWERC 2007
 * Problem G: Summits
 * Type: Graph Theory
 * Solution: Sort points by height. Consider the highest it only depends
 * on points height-d. Use union find to keep the disjoint sets of connected
 * points and the max height of each. See if this point is the highest in its
 * set if it is it is a summit.
 */

public class Summits 
{
	static int[] rank,parent,max;
	public static void union(int i, int j)
	{
		int x = find(i);
		int y = find(j);
		if(rank[x] > rank[y])
		{
			parent[y] = x;
			max[x] = max(max[y],max[x]);
		}else if(rank[x] < rank[y])
		{
			parent[x] = y;
			max[y] = max(max[y],max[x]);
		}else{
			parent[y] = x;
			rank[x]++;
			max[x] = max(max[y],max[x]);
		}
	}
	private static int find(int i) 
	{
		if(parent[i] == i)
			return i;
		else
		{
			parent[i] = find(parent[i]);
			return parent[i];
		}
	}
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int zz = 0; zz < T;zz++)
		{
			H = sc.nextInt();
			W = sc.nextInt();
			D = sc.nextInt();
			board = new int[H][W];	
			rank = new int[H*W];
			parent = new int[H*W];
			max = new int [H*W];
			ArrayList<Point> points = new ArrayList<Point>();
			for(int i = 0; i < H;i++)
				for(int j = 0; j < W;j++)
				{
					board[i][j] = sc.nextInt();
					points.add(new Point(board[i][j],i,j));
					max[i*W+j] = (int)board[i][j];
					parent[i*W+j] = i*W+j;
				}
			
			Collections.sort(points);
			int at = 0;
			int ans = 0;
			for(Point p: points)
			{
				while(at < points.size() && points.get(at).h > p.h-D)
				{
					Point q = points.get(at);
					for(int j = 0; j < 4;j++)
					{
						int nx = q.x + dx[j];
						int ny = q.y + dy[j];
						if(nx < 0 || nx >= H || ny < 0 || ny >= W)
							continue;						
						if((board[nx][ny]) > p.h-D)
						{							
							union(nx*W+ny,q.x*W+q.y);
						}						
					}
					at++;
				}
				if(max[find(p.x*W+p.y)] <= p.h)
				{
					ans++;
				}
			}	
			System.out.println(ans);
		}
	}
	private static class Point implements Comparable<Point>
	{
		int h,x,y;
		Point(int a, int b, int c)
		{
			h = a;
			x = b;
			y = c;
		}
		public int compareTo(Point p) 
		{
			return -Integer.valueOf(h).compareTo(p.h);
		}
	}
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int H,W,D;
	static int[][] board;
}
