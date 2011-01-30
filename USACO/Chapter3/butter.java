package USACO.Chapter3;
/*
ID: theycal2
LANG: JAVA
TASK: butter
 */
/* USACO Training
 * Sweet Butter
 * Type: Graph Theory
 * Solution: Run all pairs shortest path
 * for Java we need some extra speedups.
 */
import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class butter 
{
	public static void main(String[] args) throws IOException 
	{
		long start = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("butter.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("butter.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int N = Integer.valueOf(st.nextToken());
		int P = Integer.valueOf(st.nextToken());
		int C = Integer.valueOf(st.nextToken());
		int[] cows = new int[N];
		for(int i = 0; i < N;i++)
		{
			st = new StringTokenizer(f.readLine());
			cows[i] = Integer.valueOf(st.nextToken());
		}
		int[][] edge = new int[C][3];
		for(int i = 0; i < C;i++)
		{
			st = new StringTokenizer(f.readLine());
			for(int j = 0; j < 3;j++)
			{
				edge[i][j]= Integer.valueOf(st.nextToken());
			}
		}
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < P;i++)adj.add(new ArrayList<Integer>());
		for(int i = 0; i < C;i++)
		{
			adj.get(edge[i][0]-1).add(edge[i][1]-1);
			adj.get(edge[i][0]-1).add(edge[i][2]);	
			
			adj.get(edge[i][1]-1).add(edge[i][0]-1);
			adj.get(edge[i][1]-1).add(edge[i][2]);
		}
		a = new int[P][];
		for(int i = 0; i < P;i++)
		{
			a[i] = new int[adj.get(i).size()];
			for(int j = 0; j < a[i].length;j++)
			{
				a[i][j] = adj.get(i).get(j);
			}
		}
		System.out.println("$:"+(System.currentTimeMillis()-start));
		cowCount = new int[P];
		for(int i = 0; i < N;i++)
		{
			cowCount[cows[i]-1]++;
		}
		min = Integer.MAX_VALUE;
		totalCows = N;
		for(int i = 0; i < P;i++)
		{
			dijk(i);
		}

		out.println(min);
		out.close();
		System.out.println("$:"+(System.currentTimeMillis()-start));
		System.exit(0);
	}
	static int[][] a;
	static int[] cowCount;
	static int totalCows;
	static int min;
	private static void dijk(int st) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();		
		
		int[] dist = new int[a.length];
		Arrays.fill(dist,Integer.MAX_VALUE);
		pq.add(st);
		
		int left = totalCows;
		int score = 0;
		
		while(pq.size() > 0)
		{
			int p = pq.poll();
			int at = p &0x3FF;
			int cost = p>>>10;
			
			if(dist[at] <= cost) continue;
			dist[at] = cost;
			
			score += cowCount[at]*cost;	
			left -= cowCount[at];
			if(score + left*cost >= min)
				 return;						
			
			if(left == 0)
				break;
			
			int[] l = a[at];
			for(int i = 0; i < l.length;i+=2)
			{
				int to = l[i];
				int c = l[i+1];
				if(dist[to] > cost+c)
				{
					pq.add( ((c+cost)<< 10) | to);
				}
			}
		}
		min = min(min,score);
	}
}

