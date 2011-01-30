package ACM.WorldFinals.y2005;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import static java.lang.Math.*;

/* 2005 World Finals
 * Problem F: Crossing Streets
 * Type: Dijkstra/Computational Geometry
 * Difficulty Coding: 3
 * Algorithmic Difficulty: 3
 * Solution: First slab up the grid. Now we want to form a graph out of it.
 * Walk along each line and mark that there is an edge between the faces
 * on either side of it. Then run Dijkstra on this graph.
 */ 

public class F {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File("F.in"));
		for(int CASE = 1;;CASE++)
		{
			int N = sc.nextInt();
			if(N == 0) break;
			int[][] lines = new int[N][4];
			for(int i = 0; i < N;i++)
			{
				for(int j = 0; j < 4;j++)
				{
					lines[i][j] = sc.nextInt();
				}
			}
			int sx = sc.nextInt();
			int sy = sc.nextInt();
			int ex = sc.nextInt();
			int ey = sc.nextInt();
			TreeSet<Integer> x = new TreeSet<Integer>();
			TreeSet<Integer> y = new TreeSet<Integer>();
			for(int i = 0; i < N;i++)
			{
				x.add(lines[i][0]);
				x.add(lines[i][2]);
				y.add(lines[i][1]);
				y.add(lines[i][3]);
			}
			int[] xv = new int[x.size()];
			int[] yv = new int[y.size()];
			int at = 0;
			for(int e: x)
				xv[at++] = e;
			at = 0;
			for(int e: y)
				yv[at++] = e;

			int[][][] d = new int[xv.length+1][yv.length+1][4];
			for(int i = 0; i < N;i++)
			{
				//horizontal
				if(lines[i][1] == lines[i][3])
				{
					int yy = Arrays.binarySearch(yv, lines[i][1]);
					int s = Arrays.binarySearch(xv, min(lines[i][0],lines[i][2]));
					int e = Arrays.binarySearch(xv, max(lines[i][0],lines[i][2]));
					for(int j = s+1; j <= e;j++)
					{
						d[j][yy][0] = 1;
						d[j][yy+1][2] = 1;
					}
				}else{
					int xx = Arrays.binarySearch(xv, lines[i][0]);
					int s = Arrays.binarySearch(yv, min(lines[i][1],lines[i][3]));
					int e = Arrays.binarySearch(yv, max(lines[i][1],lines[i][3]));
					for(int j = s+1; j <= e;j++)
					{
						d[xx][j][1] = 1;
						d[xx+1][j][3] = 1;
					}
				}
			}
			int ssx=0,ssy=0,eex=0,eey=0;
			for(int i = 0; i <=xv.length;i++)
			{
				if(i==0)
				{
					if(sx <= xv[0]) ssx = i;
					if(ex <= xv[0]) eex = i;
				}else if(i == xv.length)
				{
					if(sx > xv[i-1]) ssx = i;
					if(ex > xv[i-1]) eex = i;
				}else{
					if(xv[i-1] < sx && sx <= xv[i]) ssx = i;
					if(xv[i-1] < ex && ex <= xv[i]) eex = i;
				}
			}
			for(int i = 0; i <=yv.length;i++)
			{
				if(i==0)
				{
					if(sy <= yv[0]) ssy = i;
					if(ey <= yv[0]) eey = i;
				}else if(i == yv.length)
				{
					if(sy > yv[i-1]) ssy = i;
					if(ey > yv[i-1]) eey = i;
				}else{
					if(yv[i-1] < sy && sy <= yv[i]) ssy = i;
					if(yv[i-1] < ey && ey <= yv[i]) eey = i;
				}
			}
			
			int min = dijk(ssx,ssy,eex,eey,d);
			System.out.println("City "+CASE);
			System.out.println("Peter has to cross "+min+" streets");
		}
	}
	private static class Node implements Comparable<Node>{
		int x,y,c;
		public Node(int xx, int yy, int cc)
		{
			x = xx;
			y = yy;
			c = cc;
		}
		public int compareTo(Node n) {
			if(c < n.c) return -1;
			if(c > n.c) return 1;
			return 0;
		}
	}
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	private static int dijk(int sx, int sy, int ex, int ey, int[][][] d) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		boolean[][] visited = new boolean[d.length][d[0].length];
		int[][] dist = new int[d.length][d[0].length];
		for(int i = 0; i < dist.length;i++)Arrays.fill(dist[i],Integer.MAX_VALUE);
		dist[sx][sy] = 0;

		pq.add(new Node(sx,sy,0));
		while(pq.size() > 0)
		{
			Node n = pq.poll();
			if(visited[n.x][n.y]) continue;
			visited[n.x][n.y] = true;

			if(n.x == ex && n.y == ey) return n.c;
			for(int i = 0; i < 4;i++)
			{
				int x = n.x + dx[i];
				int y = n.y + dy[i];
				if(x < 0 || x >= d.length || y < 0 || y >= d[0].length) continue;
				int c = d[x][y][i]+n.c;
				if(c < dist[x][y])
				{
					dist[x][y] = c;
					pq.add(new Node(x,y,c));
				}
			}
		}
		return -1;
	}
}
