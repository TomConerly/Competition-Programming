package ACM.WorldFinals.y2007;

import java.util.*;
import static java.lang.Math.*;
import java.io.*;

/* 2007 World Finals
 * Problem J: Tunnels
 * Type: Dijkstra/Max Flow Min Cut
 * Difficulty Coding: 3
 * Algorithmic Difficulty: 5
 * Solution: Given the path of the spy we want to at some point
 * cut a bunch of edges and disconnect the vertex the spy is at 
 * from the end. So for each vertex compute the number of edges
 * we have to cut to disconnect it from the end (use max flow min cut).
 * Now the cost of a path is the min of this value over all verticies.
 * Now simply use Dijkstra so that the spy can find the path with the 
 * highest min value over all verticies.
 */ 


public class J {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File("J.in"));
		for(int z = 1;;z++)
		{
			int R = sc.nextInt();
			int T = sc.nextInt();
			if(R==0 && T==0) break;
			int[][] flow = new int[R+3][R+3];
			int s = flow.length-2;
			int e = flow.length-1;
			for(int i = 0; i < T;i++)
			{
				int a = sc.nextInt();
				int b = sc.nextInt();
				flow[a][b]++;
				flow[b][a]++;
			}
			flow[0][e] = 100000000;
			int[] cost = new int[R+1];
			for(int i = 0; i < cost.length;i++)
			{
				if(i==0) cost[0] = Integer.MAX_VALUE;
				else{
					int[][] fl = new int[R+3][R+3];
					for(int j = 0; j < fl.length;j++)
						for(int k = 0; k < fl.length;k++)
							fl[j][k]  = flow[j][k];
					fl[s][i] = 100000000;
					cost[i] = maxflow(s,e,fl);
				}
			}
			//System.out.println(Arrays.toString(cost));
			int f = dijkstra(1,0,cost,flow);
			System.out.println("Case "+z+": "+f+"\n");
		}
	}
	private static int dijkstra(int s, int e, int[] cost,int[][]flow) {
		int[] best = new int[cost.length];
		Arrays.fill(best,-1);
		best[s] = cost[s];
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(new Node(cost[s],s));
		while(pq.size() > 0)
		{
			Node n = pq.poll();
			
			if(n.at==e) return n.cost;
			if(n.cost < best[n.at]) continue;
			for(int i = 0; i < cost.length;i++)
			{
				if(flow[n.at][i]>0)
				{
					int c = min(cost[i],n.cost);
					if(c > best[i])
					{
						best[i] = c;
						pq.add(new Node(c,i));
					}
				}
			}
		}
		return 0;
	}
	public static class Node implements Comparable<Node>{
		int cost,at;
		Node(int c, int a)
		{
			cost = c;
			at = a;
		}
		public int compareTo(Node n) {
			return -Integer.valueOf(cost).compareTo(n.cost);
		}
	}
	public static int maxflow(int s, int e, int[][] f)
	{
		int flow = 0;
		while(true)
		{
			int[] p = bfs(s,e,f);
			if(p ==null) break;
			update(s,e,p,f);
			flow++;
		}
		return flow;
	}
	private static void update(int s, int e, int[] p,int[][] f) {
		if(s==e) return;
		
		f[p[e]][e]--;
		f[e][p[e]]++;
		update(s,p[e],p,f);
		
	}
	public static int[] bfs(int s, int e, int[][] f)
	{
		int[] prev = new int[f.length];
		Arrays.fill(prev,-1);
		LinkedList<Integer> q = new LinkedList<Integer>();
		q.add(s);
		
		while(q.size() > 0)
		{
			int at = q.poll();
			for(int i = 0; i < f.length;i++)
			{
				if(prev[i] != -1 || i==at) continue;
				if(f[at][i] > 0)
				{
					prev[i] = at;
					if(i == e) return prev;
					q.addLast(i);
				}
			}
		}
		return null;
	}
}
