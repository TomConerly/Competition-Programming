package ACM.Yokohama.y2006;
import java.util.*;
import static java.lang.Math.*;

/* ACM Asia Regional Yokohama 2006
 * Problem G: Polygons on the Grid
 * Type: DP/Geometry
 * Solution: Given a subset of the 6 left, and the x,y coordinate you are at compute the best you can do to finish.
 * You can calculate area because each new piece adds a triangle worth of area. Do a little pruning and you get in time.
 */

public class G 
{
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{
		long st = System.currentTimeMillis();
		Scanner sc = new Scanner(System.in);
		dp = new int[1<<6][300*3][300*3*2];
		while(true)
		{
			int N = sc.nextInt();
			if(N == 0)
				break;
			int[] len = new int[N];
			for(int i = 0; i < N;i++)
				len[i] = sc.nextInt();
			dx = new ArrayList[N];
			dy = new ArrayList[N];
			for(int i = 0; i < N;i++)
			{
				dx[i] = new ArrayList<Integer>();
				dy[i] = new ArrayList<Integer>();
				for(int x = 0;;x++)
				{
					for(int y=x;;y++)
					{
						if(x*x+y*y == len[i]*len[i])
						{
							dx[i].add(x);
							dy[i].add(y);
						}else if(x*x+y*y > len[i]*len[i])
						{
							break;
						}
					}
					if(x*x > len[i]*len[i])
						break;
				}
			}
			
			for(int i = 0; i < dp.length;i++)
				for(int j = 0; j < dp[0].length;j++)
					Arrays.fill(dp[i][j],-1);
			int area = recur((1<<N)-1,0,0);
			if(area <= 0)
				System.out.println(-1);
			else
				System.out.println(area/2);
			
		}
		long en = System.currentTimeMillis();
		System.out.println(en-st);
	}
	static int recur(int left, int x, int y)
	{
		if(x*x + y*y > 300*300*Integer.bitCount(left)*Integer.bitCount(left))
			return -10000000;
		if(y <= -900 || y >= 900 || x < 0|| x >= 900)
			return -10000000;
		if(left == 0 && x == 0 && y == 0)				
			return 0;		
		if(left == 0)
			return -10000000;
		if(dp[left][x][y+300*3] != -1)
			return dp[left][x][y+300*3];

		int best = -10000000;
		for(int i = 0; i < dx.length;i++)
		{
			if(Integer.bitCount(left) == dx.length && i > 0)
				break;
			if((left & (1<<i)) != 0)
			{
				for(int j = 0; j < dx[i].size();j++)
				{
					for(int k = 0; k < 4;k++)
					{
						best = max(best,area(x,y,x+a[k]*dx[i].get(j),y+b[k]*dy[i].get(j)) + recur(left-(1<<i),x+a[k]*dx[i].get(j),y+b[k]*dy[i].get(j)));
						best = max(best,area(x,y,x+a[k]*dy[i].get(j),y+b[k]*dx[i].get(j)) + recur(left-(1<<i),x+a[k]*dy[i].get(j),y+b[k]*dx[i].get(j)));
					}
				}
			}
		}
		dp[left][x][y+300*3] = best;
		return best;
	}
	private static int area(int x, int y, int i, int j) 
	{		
		return abs(x*j-y*i);
	}
	static int[] a = {-1,-1,1,1};
	static int[] b = {-1,1,-1,1};
	static int[][][] dp;
	static ArrayList<Integer>[] dx;
	static ArrayList<Integer>[] dy;
}
