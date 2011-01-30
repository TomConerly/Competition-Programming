package SSU;
import java.util.*;
import java.io.*;

/* Saratov State University Online Judge
 * Problem 172: eXam
 * Type: Graph Theory
 * Solution: See if you can 2 color the graph where each subject is a vertex, and they are connected if a student takes both.
 */

public class p172 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException
	{
		BufferedReader R = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(R.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[N];
		for(int i = 0; i < N;i++)
			adj[i] = new ArrayList<Integer>();
		
		for(int i = 0; i < M;i++)
		{
			st = new StringTokenizer(R.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			adj[a].add(b);
			adj[b].add(a);
		}
		V = new int[N];
		legal = true;
		for(int i = 0; i < N;i++)
		{
			if(V[i] == 0)
			{
				dfs(i,1);
			}
		}
		if(legal)
		{
			System.out.println("yes");
			ArrayList<Integer> ans = new ArrayList<Integer>();
			for(int i = 0; i < N;i++)
				if(V[i] == 1)
					ans.add(i+1);
			System.out.println(ans.size());
			for(int e:ans)
				System.out.print(e+" ");
			System.out.println();
		}else{
			System.out.println("no");
		}
	}
	private static void dfs(int at, int v) {
		if(V[at] != 0)
		{
			if(V[at] != v)
				legal = false;
			return;
		}
		V[at] = v;
		for(int e:adj[at])
		{
			dfs(e,v == 1 ? 2:1);
		}
		
	}
	static boolean legal;
	static int N,M;
	static ArrayList<Integer>[] adj;
	static int[] V;
}
