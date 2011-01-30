package TopCoder.Easy;
import static java.lang.Math.*;

/* TopCoder SRM 434
 * Easy Problem 250 Points: FindingSquareInTable
 * Type: Brute Force
 * Solution: Watch out for special cases.
 */

public class FindingSquareInTable {

	public int findMaximalSquare(String[] table) {
		int[][] t = new int[table.length][table[0].length()];
		int N = table.length;
		int M = table[0].length();
		for(int i= 0; i < t.length;i++)
		{
			for(int j = 0; j < t[0].length;j++)
			{
				t[i][j] = table[i].charAt(j)-'0';
			}
		}
		long best = -1;
		for(int ix = 0; ix < N;ix++)
		{
			for(int iy = 0; iy < M;iy++)
			{
				for(int jx = 0; jx < N;jx++)
				{
					for(int jy = 0;jy < M;jy++)
					{
						int dx = jx-ix;
						int dy = jy-iy;
						long num = 0;
						int x = ix;int y = iy;
						
						while(x < N && x >= 0&& y < M && y >=0)
						{							
							num = num*10+t[x][y];
							if(num > best&& psquare(num))
								best = num;
							if(dx == 0 && dy == 0) break;
							x += dx;
							y += dy;
						}
					}
				}
			}
		}
		return (int) best;
	}
	boolean psquare(long n)
	{
		long d = (long) floor(sqrt(n));
		return d*d == n;
	}
	void print(int[] a)
	{
		for(int b:a)
			System.out.print(b+" ");
		System.out.println();
	}

}
