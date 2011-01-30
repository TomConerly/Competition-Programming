package USACO.Chapter2;

/*
ID: theycal2
LANG: JAVA
TASK: holstein
 */
/* USACO Training
 * Healthy Holsteins
 * Type: Brute Force
 * Solution: Brute force it. 
 */
import java.io.*;
import java.util.*;

public class holstein 
{
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(new File("holstein.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
		V = sc.nextInt();
		min = new int[V];
		for(int i = 0; i < V;i++)
		{
			min[i] = sc.nextInt();
		}
		G = sc.nextInt();
		scoop = new int[G][V];
		for(int i = 0; i < G;i++)
		{
			for(int j = 0; j < V;j++)
			{
				scoop[i][j]= sc.nextInt();
			}
		}
		best = null;
		bestC = Integer.MAX_VALUE;
		
		recur(0,new boolean[G],0,new int[V]);
		
		ArrayList<Integer> ans = new ArrayList<Integer>();
		for(int i = 0; i < G;i++)
		{
			if(best[i]) ans.add(i+1);
		}
		Collections.sort(ans);
		out.print(ans.size());
		for(int a: ans)
			out.print(" "+a);
		out.println();
		
		out.close();
	}
	static int[] min;
	static int[][] scoop;
	static int V,G;
	
	static boolean[] best;
	static int bestC;
	
	public static void recur(int at, boolean[] used,int count,int[] vit)
	{
		if(at == used.length)
		{
			for(int i = 0; i < V;i++)
			{
				if(vit[i] < min[i]) return;
			}
			if(better(count,used))
			{
				bestC = count;
				best = used.clone();
			}
			return;
		}
		recur(at+1,used,count,vit);
		
		for(int i = 0; i < V;i++){
			vit[i] += scoop[at][i];
		}
		used[at] = true;
		recur(at+1,used,count+1,vit);
		for(int i = 0; i < V;i++){
			vit[i] -= scoop[at][i];
		}
		used[at] = false;
	}
	public static boolean better(int count, boolean[] used)
	{
		if(count < bestC) return true;
		if(count > bestC) return false;
		
		for(int i = 0; i < G;i++)
		{
			if(used[i] && !best[i]) return true;
			if(!used[i] && best[i]) return false;
		}
		return false;
	}
}

