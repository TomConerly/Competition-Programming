package SSU;
import java.util.*;

/* Saratov State University Online Judge
 * Problem 463: Walking around Berhattan
 * Type: Simulation
 * Solution: Simple simulation.
 */

public class p463 {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[][] B = new int[N][M];
		for(int i = 0; i < N;i++)
		{
			String s = sc.next();
			for(int j = 0; j < M;j++)
			{
				B[i][j] = s.charAt(j)-'0';
			}
		}
		boolean[][] V = new boolean[N][M];
		int dir = 0;
		int x = 0;
		int y = 0;
		String s = sc.next();
		int ans = 0;
		for(int i = 0; i < s.length();i++)
		{
			if(s.charAt(i) == 'R')
				dir = (dir+1)%4;
			if(s.charAt(i) == 'L')
				dir = (dir-1+4)%4;
			if(s.charAt(i) == 'M')
			{
				int nx = x+dx[dir];
				int ny = y+dy[dir];
				if(dir == 0)
				{
					ans += visit(B,V,x,y);
					ans += visit(B,V,x,y-1);										
				}else if(dir == 1)
				{
					ans += visit(B,V,x,y);
					ans += visit(B,V,x-1,y);	
				}else if(dir == 2)
				{
					ans += visit(B,V,x-1,y);
					ans += visit(B,V,x-1,y-1);	
				}else if(dir == 3)
				{
					ans += visit(B,V,x,y-1);
					ans += visit(B,V,x-1,y-1);	
				}
				x = nx;
				y = ny;
			}
		}
		System.out.println(ans);
	}
	private static int visit(int[][] B, boolean[][] V, int x, int y) {
		if(y < 0 || y >= B.length || x < 0 || x >= B[0].length)
			return 0;
		int ans = V[y][x] ? B[y][x]/2 :B[y][x];
		V[y][x] = true;
		return ans;
	}
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
}
