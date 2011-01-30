package TopCoder.Hard;
import java.util.*;
/* TopCoder SRM 396
 * Hard Problem 1000 Points: MoreNim
 * Type: Linear Algebra
 * Solution: We want to make it so that the stacks left there is no subset
 * where the xors are zero. If we look at them as bit vectors we want them to 
 * be a linearly independent set. So simply find the rank of the matrix made up
 * of all vectors, try removing each onne if the rank drops leave it otherwise
 * take it out. Try them in order from smallest to largest.
 */

public class MoreNim {
	long[] heaps;
	public long bestSet(String[] h) {
		heaps = new long[h.length];
		for(int i = 0; i < h.length;i++)		
			heaps[i] = Long.valueOf(h[i]);
		
		Arrays.sort(heaps);
		int[][] matrix = new int[h.length][60];
		for(int i = 0; i < heaps.length;i++)		
			for(int j = 0; j < 60;j++)			
				matrix[i][j] = (heaps[i] & (1L<<j)) != 0?1:0;
			
		
		long ans = 0;
		int num = num(matrix);
		for(int i = 0; i < matrix.length;i++)
		{
			int[] temp = new int[matrix[0].length];
			for(int j = 0; j < temp.length;j++)
			{
				temp[j] = matrix[i][j];
				matrix[i][j] = 0;
			}
			int n = num(matrix);
			if(n == num)
			{
				ans += heaps[i];
			}else{
				for(int j = 0; j < temp.length;j++)				
					matrix[i][j] = temp[j];				
			}			
		}
		return ans;
	}
	private int num(int[][] matrix) {
		int[][] copy = new int[matrix.length][matrix[0].length];
		for(int i = 0; i < copy.length;i++)		
			for(int j = 0; j < copy[0].length;j++)			
				copy[i][j] = matrix[i][j];			
		
		return numWays(copy);
	}
	public int numWays(int[][] matrix)
	{		
		int ans = 0;
		for(int i = 0; i < matrix[0].length;i++)
		{
			int at = -1;
			
			for(int j = ans; j < matrix.length;j++)			
				if(matrix[j][i] == 1)		
					at = j;
			
			if(at==-1) 	continue;		
			
			swap(at,ans,matrix);
			
			for(int j = ans+1;j<matrix.length;j++)			
				if(matrix[j][i] == 1)
					sub(j,ans,matrix);			
			ans++;
		}
		return ans;
	}
	private void sub(int j, int i, int[][] m) {
		for(int k = 0; k < m[0].length;k++)		
			m[j][k] = (m[j][k] + m[i][k])%2;
		
	}
	private void swap(int j, int i,int[][]m) {
		for(int k = 0; k < m[0].length;k++)
		{
			int t = m[i][k];
			m[i][k] = m[j][k];
			m[j][k] = t;
		}		
	}
}
