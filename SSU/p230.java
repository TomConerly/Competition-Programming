package SSU;
import java.util.*;
import java.io.*;

/* Saratov State University Online Judge
 * Problem 230: Weighings
 * Type: Graph Theory
 * Solution: Topological sort
 */

public class p230 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		BufferedReader R = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(R.readLine());
		N = Integer.valueOf(st.nextToken());
		M = Integer.valueOf(st.nextToken());
		adj = new ArrayList[N];
		for(int i = 0; i < N;i++)
			adj[i] = new ArrayList<Integer>();
		for(int i = 0; i < M;i++){
			st = new StringTokenizer(R.readLine());
			int a = Integer.valueOf(st.nextToken())-1;
			int b = Integer.valueOf(st.nextToken())-1;
			adj[b].add(a);
		}
		ans = new ArrayList<Integer>();
		V = new boolean[N];
		O = new int[N];
		for(int i = 0; i < N;i++)
			dfs(i);
		boolean legal = true;
		for(int i = 0; i < N;i++)
			for(int e:adj[i]){
				if(O[i] <= O[e])
					legal = false;			
				if(i==e)
					legal = false;
			}
		
		if(!legal){
			System.out.println("No solution");
		}else{
			for(int i = 0; i < ans.size();i++)
				System.out.print(O[i]+(i != ans.size()-1 ? " ":""));
			System.out.println();
		}
	}
	static int[] O;
	static boolean[] V;
	private static void dfs(int at) {
		if(V[at])
			return;
		V[at] = true;
		for(int e:adj[at])
			dfs(e);
		ans.add(at);
		O[at] = ans.size();
	}
	static int N,M;
	static ArrayList<Integer>[] adj;
	static ArrayList<Integer> ans;
}
