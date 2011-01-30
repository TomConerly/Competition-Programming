package ACM.WorldFinals.y2005;

import static java.lang.Math.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/* 2005 World Finals
 * Problem H: The Great Wall Game
 * Type: Assigment Problem
 * Difficulty Coding: 3
 * Algorithmic Difficulty: 4
 * Solution: Difficulty is low because assignment problem algorithm is in notebook.
 * Try every ending configuration (only 32 at most). For each piece compute the cost
 * of assigning it to a given end position (the manhatan distance). Then run an assignment 
 * problem algorithm (min cost max flow here) to find the minimal cost assignment.
 * 
 * It doesn't matter that pieces might be blocked by others in getting to their assigned square.
 * The reason for this is that if a piece x is blocked by piece y we could alternatively
 * send y to x's desitnation and have x go to y's position and then y's destination. This
 * makes the problem much easier.
 */ 

public class H {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File("H.in"));
		for(int CASE = 1;;CASE++)
		{
			int N = sc.nextInt();
			if(N==0) break;
			int[] x = new int[N];
			int[] y = new int[N];
			for(int i = 0; i < N;i++)
			{
				x[i] = sc.nextInt()-1;
				y[i] = sc.nextInt()-1;
			}
			int best = Integer.MAX_VALUE;
			for(int i = 0; i < N;i++)
			{
				int[][] c = new int[N][N];
				int[][] d = new int[N][N];
				for(int j = 0; j < N;j++)
				{
					for(int k = 0; k < N;k++)
					{
						c[j][k] = abs(x[j]-i)+abs(y[j]-k);
						d[j][k] = abs(x[j]-k)+abs(y[j]-i);
					}
				}
				int cc = assign(c);
				int cd = assign(d);
				//System.out.println(i+" "+cc+" "+cd);
				best = min(best,min(cc,cd));
			}
			int[][] c = new int[N][N];
			int[][] d = new int[N][N];
			for(int j = 0; j < N;j++)
			{
				for(int k = 0; k < N;k++)
				{
					c[j][k] = abs(x[j]-k)+abs(y[j]-k);
					d[j][k] = abs(x[j]-k)+abs(y[j]-N-k-1);
				}
			}
			int cc = assign(c);
			int cd = assign(d);
		//	System.out.println("diag "+cc+" "+cd);
			best = min(best,min(cc,cd));
			System.out.println("Board "+CASE+": "+best+" moves required.\n");
		}
		
	}
	static int start,end;
	static int[][] flow,cost;
	private static int assign(int[][] matr) {
		start = matr.length*2;
		end = matr.length*2+1;
		flow = new int[end+1][end+1];
		cost = new int[end+1][end+1];
		for(int i = 0; i < matr.length;i++)
		{
			for(int j = 0; j < matr.length;j++)
			{
				flow[i][j+matr.length]=1;
			}
			flow[start][i] = 1;
			flow[i+matr.length][end]=1;
		}
		for(int i = 0; i < matr.length;i++)
		{
			for(int j = 0; j < matr.length;j++)
			{
				cost[i][j+matr.length] = matr[i][j];
			}
		}
		int[] p = new int[cost.length];
		
		int c = 0;
		for(int i = 0; i < matr.length;i++)
		{
			int[][] ans = Dijk(start,p,flow,cost);
			c += ans[0][end]-p[start]+p[end];
			for(int j = 0; j < p.length;j++)
			{
				p[j] += ans[0][j];
			}
			for(int at = end; at != start; at = ans[1][at])
			{
				flow[ans[1][at]][at] -= 1;
				flow[at][ans[1][at]] += 1;
				int temp = cost[ans[1][at]][at];
				cost[ans[1][at]][at] = -cost[at][ans[1][at]];
				cost[at][ans[1][at]] = -temp;
			}
		}
		return c;
	}
	public static int[][] Dijk(int start, int[] p, int[][] flow, int[][] cost)
	{
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		boolean[] visited = new boolean[flow.length];
		int[] dist = new int[flow.length];
		int[] pred = new int[flow.length];
		
		Arrays.fill(dist,Integer.MAX_VALUE);
		dist[start] = 0;
		Arrays.fill(pred,-1);
		
		pq.add(start);
		
		while(pq.size() > 0)
		{
			int next = pq.poll();
			int node = next & 0xFFFF;
			int c = next >>> 16;
			
			if(visited[node]) continue;
			visited[node] = true;
			
			for(int i = 0; i < flow.length;i++)
			{
				if(flow[node][i] <= 0) continue;
				int newCost = c+cost[node][i]+p[node]-p[i];
				if(dist[i] > newCost)
				{
					dist[i] = newCost;
					pq.add((dist[i]<<16)|i);
					pred[i] = node;
				}
			}
		}
		return new int[][]{dist,pred};
	}
}
