package USACO.Chapter2;
/*
ID: theycal2
LANG: JAVA
TASK: cowtour
 */
/* USACO Training
 * Cow Tours
 * Type: Graph Theory
 * Solution: Find all pairs shortest path. Try all edges that
 * connect two seperate pastures then find the diameter.
 * Make sure to calculate the diameter as either the old shortest
 * path or the path to the new edge, through the edge, to the other person.
 */
import java.io.*;
import java.util.*;

public class cowtour 
{
	public static void main(String[] args) throws IOException 
	{
		long start = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("cowtour.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowtour.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		
		loc = new int[N][2];
		for(int i = 0; i < N;i++)
		{
			st = new StringTokenizer(f.readLine());
			loc[i][0] = Integer.parseInt(st.nextToken());
			loc[i][1] = Integer.parseInt(st.nextToken());
		}
		
		adj = new int[N][N];
		for(int i = 0; i < N;i++)
		{
			String str = f.readLine();
			for(int j = 0; j < N;j++)
			{
				adj[i][j] = str.charAt(j)-'0';
			}
		}
		
		visited = new int[N];
		Arrays.fill(visited,-1);
		for(int i = 0; i < N;i++)
		{
			dfs(i,i);
		}
		
		double[][] adjMatrix = new double[N][N];
		for(int i = 0; i < N;i++)
		{
			for(int j = 0; j < N;j++)
			{
				if(i==j) adjMatrix[i][j] = 0;
				else if(adj[i][j] == 1)
				{
					adjMatrix[i][j] = Math.hypot(loc[i][0]-loc[j][0], loc[i][1]-loc[j][1]);
				}
				else
				{
					adjMatrix[i][j] = Z;
				}
				
			}
		}
		adjMatrix = FW(adjMatrix);
		
		System.out.println(System.currentTimeMillis()-start);
		double best = Z;
		for(int i = 0; i < N;i++)
		{
			for(int j = i+1; j < N;j++)
			{
				if(visited[i] != visited[j])
				{
					double e = Math.hypot(loc[i][0]-loc[j][0], loc[i][1]-loc[j][1]);
					double max = 0;
					
					next:						
					for(int k = 0; k < N;k++)
					{
						if(visited[k] != visited[i] && visited[k] != visited[j]) continue;
						for(int m = k+1; m < N;m++)
						{							
							if(visited[m] != visited[i] && visited[m] != visited[j]) continue;													
							if(adjMatrix[k][m] < max) continue;
							
							if(visited[i] == visited[k])
							{								
								max = Math.max(max,Math.min(adjMatrix[k][m],e+adjMatrix[k][i]+adjMatrix[j][m]));		
							}
							else
							{
								max = Math.max(max,Math.min(adjMatrix[k][m],e+adjMatrix[k][j]+adjMatrix[i][m]));
							}
							if(max > best) break next;											
						}
					}					
					best = Math.min(best,max);
				}
			}
		}
		String ans = String.format("%.6f", best);
		
		while(ans.length()- ans.indexOf(".") < 6)
		{
			ans = ans+"0";
		}
		
		out.println(ans);
		
		out.close();
		System.out.println(System.currentTimeMillis()-start);
		System.exit(0);
	}
	
	static int[][] adj;
	static int[][] loc;
	static int[] visited;
	static int N;
	static double Z = 1e30;
	
	private static void dfs(int at,int num) {
		if(visited[at] != -1) return;
		visited[at] = num;
		for(int i = 0; i < N;i++)
		{
			if(adj[at][i] == 1) dfs(i,num);
		}
	}
	public static double[][] FW(double[][] adj)
	{
		for(int i = 0; i < adj.length;i++){
			for(int j = 0; j < adj.length;j++){
				for(int k = 0; k < adj.length;k++){
					adj[j][k] = Math.min(adj[j][k],adj[j][i]+adj[i][k]);
				}
			}
		}
		return adj;
	}
}

