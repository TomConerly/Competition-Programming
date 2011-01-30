package Denison.y2008;
import java.util.Scanner;

/* 2008 Denison Spring Programming Competition
 * Problem E: Ski Slope
 * Type: Graph Theory
 */

public class E {
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		for(int z=1;;z++)
		{
			int n = s.nextInt();
			int l = s.nextInt();
			if(n==0 && l==0)break;
			int[][] adj = new int[n+2][n+2];
			mem = new int[n+2];
			for(int i = 0; i < l;i++)
			{
				adj[s.nextInt()][s.nextInt()]++;
			}
			
			for(int i = 0; i < n+2;i++) 
				mem[i]=-1;
			mem[0]=1;
			int paths = dfs(adj,1);
			System.out.println("Slope "+z+" has "+paths+" runs.");
		}
	}
	public static int[] mem;
	public static int dfs(int[][] adj, int at)
	{
		if(at == 0) return 1;
		if(mem[at] != -1) return mem[at];
		
		int score = 0;
		for(int i = 0; i < adj[at].length;i++)
		{
			if(adj[at][i]>0)
			{
				score += adj[at][i]*dfs(adj,i);
			}
		}
		mem[at]=score;
		return score;
	}
}
