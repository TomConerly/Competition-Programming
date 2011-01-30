package GCJ.y2009.qual;
import java.util.*;

/* Google Code Jam 2009 Qual
 * Problem B: Watersheds
 * Type: Graph Theory
 * Solution: Look at it as a graph and just do it.
 */

public class B {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int zz = 1; zz <= T;zz++)
		{
			H = sc.nextInt();
			W = sc.nextInt();
			h = new int[H][W];
			for(int i = 0; i < H;i++)
				for(int j = 0; j < W;j++)
					h[i][j] = sc.nextInt();

			ans = new char[H][W];
			char at = 'a';
			for(int i = 0; i < H;i++)
				for(int j = 0; j < W;j++)
				{
					if(basin(i,j))
					{
						ans[i][j] = at;
						at++;
					}
				}
			for(int i = 0; i < H;i++)
				for(int j = 0; j < W;j++)
				{
					if(ans[i][j] == 0)
						solve(i,j);
				}
			System.out.println("Case #"+zz+": ");
			at = 'a';
			char[] map = new char[26];
			for(int i = 0; i < H;i++)
			{
				for(int j = 0; j < W;j++)
				{
					if(j != 0)
						System.out.print(" ");
					if(map[ans[i][j]-'a'] == 0)
					{
						map[ans[i][j]-'a'] = at;
						at++;
					}
					System.out.print(map[ans[i][j]-'a']);
				}
				System.out.println();
			}
		}
	}
	private static char solve(int i, int j) {
		if(ans[i][j] != 0) return ans[i][j];
		int bestH = Integer.MAX_VALUE;
		int best = -1;
		for(int k = 0; k < 4;k++)
		{
			int c = v(i+x[k],j+y[k]);
			if(c < bestH && c < v(i,j))
			{
				bestH = c;
				best = k;
			}
		}
		ans[i][j] = solve(i+x[best],j+y[best]);
		return ans[i][j];

	}
	private static boolean basin(int i, int j) 
	{
		for(int k = 0; k < 4;k++)
		{
			if(v(i,j) > v(i+x[k],j+y[k])) return false;
		}
		return true;
	}
	private static int v(int i, int j)
	{
		if( i < 0 || i >= H || j < 0 || j >= W) return Integer.MAX_VALUE;
		else return h[i][j];
	}
	static int[] x = {-1,0,0,1};
	static int[] y = {0,-1,1,0};
	static int H,W;
	static int[][] h;
	static char[][] ans;
}
