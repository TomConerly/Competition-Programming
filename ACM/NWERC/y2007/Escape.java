package ACM.NWERC.y2007;
import java.util.*;

/* ACM NWERC 2007
 * Problem E: Escape
 * Type: Graph Theory
 * Solution: Binary search on the dist. BFS from all bases to find all illegal squares.
 * Then see if there is a path on the graph.
 */

public class Escape 
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int zz = 0; zz < T;zz++)
		{
			N = sc.nextInt();
			H = sc.nextInt();
			W = sc.nextInt();
			sx = sc.nextInt();
			sy = sc.nextInt();
			ex = sc.nextInt();
			ey = sc.nextInt();
			base = new int[N][2];
			for(int i = 0; i < N;i++)
				for(int j = 0; j < 2;j++)
					base[i][j] = sc.nextInt();
			
			int low = 0;
			int high = 2*(H+W);
			int ans = 0;
			int best = 0;
			while(true)
			{
				int mid = (low+high)/2;
				int d = pos(mid);
				if(d != -1)
				{
					if(mid > ans)
					{
						ans = mid;
						best = d;
					}
					low = mid+1;
				}else{
					high = mid-1;
				}
				if(low > high)
					break;
			}
			System.out.println(ans+" "+best);
		}
	}
	private static int pos(int dist) 
	{
		if(dist == 0)
			return 0;
		boolean[][] l = new boolean[H][W];
		for(int i = 0; i < H;i++)
			Arrays.fill(l[i],true);
		LinkedList<Point> q = new LinkedList<Point>();
		for(int i = 0; i < N;i++)
		{
			q.add(new Point(base[i][0],base[i][1],0));
			l[base[i][0]][base[i][1]] = false;
		}
		while(q.size() > 0)
		{
			Point p = q.poll();
			if(p.dist == dist-1)
				continue;
			for(int i = 0; i < 4;i++)
			{
				Point np = new Point(p.x+dx[i],p.y+dy[i],p.dist+1);
				if(np.x < 0 || np.x >= H || np.y < 0 || np.y >= W)
					continue;
				if(!l[np.x][np.y])
					continue;
				else
				{
					l[np.x][np.y] = false;
					q.add(np);
				}
			}
		}
		q.add(new Point(sx,sy,0));
		boolean[][] v = new boolean[H][W];
		
		v[sx][sy] = true;
		while(q.size() > 0)
		{			
			Point p = q.poll();
			if(!l[p.x][p.y])
				continue;
			if(p.x == ex && p.y == ey)
				return p.dist;
			for(int i = 0; i < 4;i++)
			{
				Point np = new Point(p.x+dx[i],p.y+dy[i],p.dist+1);
				if(np.x < 0 || np.x >= H || np.y < 0 || np.y >= W)
					continue;
				if(!v[np.x][np.y])
				{
					v[np.x][np.y] = true;
					q.add(np);
				}
			}
		}
		return -1;
	}
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	private static class Point
	{
		int x,y,dist;
		Point(int a, int b,int c)
		{
			x = a;
			y = b;
			dist = c;
		}
	}
	static int sx,sy,ex,ey;
	static int N,H,W;
	static int[][] base;
}
