package UVaC;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import static java.lang.Math.*;

/* UVa Volume C: 10000 - Longest Paths
 * Type: Graph Search
 * Solution: DFS from any node and look for the 
 * longest path to the start node. Do it this way
 * so that you can memoize really easily. Not sure
 * if memoization is needed for the bounds but this 
 * solution takes 1/3 of the allotted time.
 */

public class LongestPaths {
	public static void main(String[] args)throws Exception
	{
		System.setIn(new FileInputStream("main.in"));

		Scanner sc = new Scanner(System.in);
		
		for(int z = 1;;z++)
		{
			int N = sc.nextInt();
			if(N == 0) break;
			s = sc.nextInt()-1;
			adjList = new ArrayList<ArrayList<Integer>>();
			for(int i = 0; i < N;i++)
			{
				adjList.add(new ArrayList<Integer>());
			}
			while(true)
			{
				int p = sc.nextInt();
				int q = sc.nextInt();
				if(p == 0 || q == 0) break;
				
				adjList.get(q-1).add(p-1);
			}
			mem = new int[N];
			Arrays.fill(mem, -1);
			int bestAt = -1;
			for(int i = 0; i < N;i++)
			{
				dfs(i);
				if(bestAt == -1 || mem[bestAt] < mem[i])
				{
					bestAt = i;
				}
			}
			System.out.println("Case "+z+": The longest path from "+(s+1)+" has length "+mem[bestAt]+", finishing at "+(bestAt+1)+".\n");
		}
	}
	static ArrayList<ArrayList<Integer>> adjList;
	static int[] mem;
	static int s;
	public static int dfs(int at)
	{
		if(at == s) return 0;
		
		if(mem[at] != -1) return mem[at];
		int best = Integer.MIN_VALUE;
		for(int e:adjList.get(at))
		{
			best = max(best,dfs(e)+1);
		}
		mem[at] = best;
		return best;
	}
}
