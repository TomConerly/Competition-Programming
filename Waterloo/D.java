package Waterloo;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/* Waterloo Sept 23 2007 Local Contest
 * Problem D: Carpool
 * Type: DP/Dijkstra
 * Solution: Simply try all subsets of 5 people. DP so we know
 * the cost of each subset of 5.
 */

import static java.lang.Math.*;
public class D 
{
	static int N,M;
	static HashMap<Integer,Integer> hm;
	static int[][] trueCost;
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File("D.in"));
		N = sc.nextInt();
		M = sc.nextInt();
		int[][] cost = new int[N+2][N+2];
		for(int i = 0; i < cost.length;i++)
			Arrays.fill(cost[i],Integer.MAX_VALUE);
		for(int i = 0; i < M;i++)
		{
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			cost[a][b] = min(cost[a][b],c);
			cost[b][a] = min(cost[b][a],c);
		}
		trueCost = new int[N+2][N+2];
		for(int i = 0; i < N+2;i++)
		{
			int[] dist = dijk(i,cost);
			for(int j = 0; j < N+2;j++)
				trueCost[i][j] = dist[j];
		}		
		hm = new HashMap<Integer,Integer>();
		for(int i = 0; i < (1<<N);i++)
		{
			int num = Integer.bitCount(i);
			if(num <= 5 && num >=1)
			{
				hm.put(i,cost(i,new boolean[N],0,0));
			}
		}
		best = Integer.MAX_VALUE;
		int[] numLeft = new int[(N+4)/5];
		for(int i = 0;i<numLeft.length;i++) 
			numLeft[i] = 5;
		
		recur(0,new int[numLeft.length],numLeft);
		System.out.println(best);
		
	}
	private static void recur(int at, int[] groups, int[] numLeft) {
		if(at == N)
		{
			int cost = 0;
			for(int g: groups)
				cost = max(cost,hm.get(g));
			best = min(cost,best);
			return;
		}
		for(int i = 0; i < groups.length;i++)
		{
			if(numLeft[i] == 0) continue;
			numLeft[i]--;
			groups[i] |= (1<<at);
			recur(at+1,groups,numLeft);
			groups[i] &= ~(1<<at);
			numLeft[i]++;
		}
		
	}
	static int best;
	private static int cost(int people, boolean[] used, int last,int at) {
		if(at == Integer.bitCount(people))
			return trueCost[last][trueCost.length-1];
		
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < N;i++)
		{
			if(((people & (1<<i)) != 0) && !used[i])
			{
				used[i] = true;
				min = min(min,5+trueCost[last][i+1]+cost(people,used,i+1,at+1));
				used[i] = false;
			}
		}
		return min;
	}
	private static class Node implements Comparable<Node>{
		@SuppressWarnings("unused")
		int a,b,c;
		public Node(int i, int j, int k) {
			a = i;
			b = j;
			c = k;
		}		
		public int compareTo(Node n) {
			if(c < n.c) return -1;
			if(c > n.c) return 1;
			return 0;
		}				
	}
	public static int[] dijk(int start, int[][] cost){
		int N = cost.length;
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		boolean[] visited = new boolean[N];
		int[] dist = new int[N];
		
		Arrays.fill(dist,Integer.MAX_VALUE);
		dist[start] = 0;
		pq.add(new Node(0,start,0));
		
		while(pq.size() > 0)
		{
			Node no = pq.poll();
			int at = no.b;
			int c = no.c;
			
			if(visited[at]) continue;
			visited[at] = true;
			
			for(int i = 0; i < N;i++)
			{
				if(cost[at][i] == Integer.MAX_VALUE)continue;
				if(dist[i] > c + cost[at][i])
				{
					dist[i] = c + cost[at][i];
					pq.add(new Node(at,i,dist[i]));
				}
			}
		}
		return dist;
	}
}
