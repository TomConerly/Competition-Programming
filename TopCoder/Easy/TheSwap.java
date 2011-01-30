package TopCoder.Easy;

/* TopCoder SRM 437
 * Easy Problem 250 Points: TheSwap
 * Type: Search
 * Solution: Just dfs over all changes you can make.
 */


public class TheSwap {

	int M = 0;
	public int findMax(int n, int k) {
		reach = new boolean[1000001][k+1];		
		int temp = n;
		while(temp > 0)
		{
			temp /= 10;
			M++;
		}
		dfs(n,k);
		int max = -1;
		for(int i = 0; i <= 1000000;i++)
		{
			if(reach[i][0]) max = i;
		}
		return max;
	}
	boolean[][] reach;
	public void dfs(int at, int numLeft)
	{
		if(reach[at][numLeft]) return;
		reach[at][numLeft] = true;
		if(numLeft == 0) return;
		for(int i = 0; i < M;i++)
			for(int j = 0; j < i;j++)
			{
				int v = flip(at,i,j);
				if(v != -1)
					dfs(v,numLeft-1);
			}
		
	}
	public int flip(int at, int i, int j)
	{
		int[] dig = new int[M];
		int k = 0;
		while(at > 0)
		{
			dig[k++] = at%10;
			at /= 10;
		}
		int t = dig[i];
		dig[i] = dig[j];
		dig[j] = t;
		if(dig[M-1] == 0) return -1;
		int ans = 0;
		for(k = M-1;k>=0;k--)
		{
			ans = ans*10+dig[k];
		}
		return ans;
	}
	public void p(Object o){System.out.println(o);}
}
