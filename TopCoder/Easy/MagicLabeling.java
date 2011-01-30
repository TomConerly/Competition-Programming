package TopCoder.Easy;
import java.util.*;

/* TopCoder TCO 2008 Round 4
 * Easy Problem 250 Points: MagicLabeling
 * Type: Graph Theory
 * Solution: Split into components. If all are single vertex then special case. Otherwise
 * try each sum, set one vertex in the component as a number (try all) then dfs to see
 * if you can fill it.
 */
public class MagicLabeling {

	int N,M;
	String[] g;
	int MOD = 1000003;
	public int count(String[] graph, int m) {
		M = m;
		N = graph.length;
		g = graph;
		group = new int[N];
		Arrays.fill(group,-1);
		int at = 0;
		int[] first = new int[N];
		for(int i = 0; i < N;i++)
		{
			if(group[i] == -1)
			{
				first[at] = i;
				dfs(i,at++);
			}
		}
		boolean[] alone = new boolean[N];
		for(int i = 0; i < at;i++)
		{
			int num = 0;
			for(int j = 0; j < N;j++)			
				if(group[j] == i)				
					num++;			
			if(num == 1)
				alone[first[i]] = true;
		}
		
		boolean all = true;
		for(int i =0; i < N;i++)
		{
			all &= alone[i];
		}
		if(all)
		{
			int ans = 1;
			for(int i =0;i<N;i++)
			{
				ans = (ans*M)%MOD;
			}
			return ans;
		}
		int ans = 0;
		for(int sum = 2; sum <= 2*M;sum++)
		{
			SUM = sum;
			int num = 1;
			for(int i = 0; i < at;i++)
			{
				val = new int[N];
				int ways = 0;
				for(int j = 1; j<= M;j++)
				{
					Arrays.fill(val,-1);
					if(fill(first[i],j)){
						ways++;
					}
				}
				num = (num*ways)%MOD;
			}
			ans = (ans+num)%MOD;			
		}
		return (ans)%MOD;
	}
	boolean print=false;
	int SUM;
	private boolean fill(int i, int j) {
		if(val[i] != -1) return val[i] == j;
		if(j < 1 || j > M) return false;
		val[i] = j;
		boolean good = true;
		for(int k = 0; k < N;k++)
		{
			if(g[i].charAt(k) == 'Y') 
			{
				good &= fill(k,SUM-j);
			}
		}
		return good;
	}
	int[] val;
	private void dfs(int i, int j) {
		if(group[i] != -1) return;
		group[i] = j;
		for(int k = 0; k < N;k++)
		{
			if(g[i].charAt(k) == 'Y') dfs(k,j);
		}		
	}
	int[] group;
	public void p(Object o){System.out.println(o);}
}
