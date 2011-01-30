package ACM.WorldFinals.y2001;

import java.io.File;
import java.io.IOException;

import java.util.*;
import static java.lang.Math.*;

/* 2001 World Finals
 * Problem B: Say Cheese
 * Type: Dijkstra
 * Difficulty Coding: 2 
 * Algorithmic Difficulty: 2
 * Solution: Look at the air pockets as a graph, we can 
 * make the cost the between two the amount of cheese you have to go through
 * run Dijkstra for min cost path.
 */ 

public class B {
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(new File("cheese.in"));
		for(int zz = 1;;zz++)
		{
			int N = sc.nextInt();
			if(N == -1) break;
			
			dat = new int[N+2][4];
			for(int i = 0; i < N;i++)
			{
				for(int j = 0; j < 4;j++)
				{
					dat[i][j] = sc.nextInt();
				}
			}
			for(int i = N;i<dat.length;i++)
			{
				for(int j = 0; j < 3;j++)
				{
					dat[i][j] = sc.nextInt();
				}
			}
			
			PriorityQueue<Node> pq = new PriorityQueue<Node>();
			double[] min = new double[N+2];
			Arrays.fill(min, Double.MAX_VALUE);
			int goal = N+1;
			double best = 0;
			pq.add(new Node(N,0));
			
			while(pq.size() > 0)
			{
				Node n = pq.poll();
				if(min[n.at] <= n.cost) continue;
				min[n.at] = n.cost;
				
				if(n.at == goal)
				{
					best = n.cost;
					break;
				}
				for(int i = 0; i < N+2;i++)
				{
					double dist = d(n.at,i)*10;
					if(min[i] > n.cost+dist)
					{
						pq.add(new Node(i,n.cost+dist));
					}
				}
			}
			System.out.println("Cheese "+zz+": Travel time = "+((int)(best+0.5)) +" sec");
		}
	}
	static int[][] dat;
	public static double d(int a, int b)
	{
		double d = sqrt(pow(dat[a][0]-dat[b][0],2)+pow(dat[a][1]-dat[b][1],2)+pow(dat[a][2]-dat[b][2],2));
		d -= dat[a][3];
		d -= dat[b][3];
		d = max(d,0);
		return d;
	}
	private static class Node implements Comparable<Node>
	{
		double cost;
		int at;
		public Node(int a, double c)
		{
			at = a;
			cost = c;
		}
		public int compareTo(Node a) {
			if(cost < a.cost) return -1;
			if(cost > a.cost) return 1;
			return 0;
		}
		
	}
}
