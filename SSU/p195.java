package SSU;
import java.util.*;
import static java.lang.Math.*;
import java.io.*;

/* Saratov State University Online Judge
 * Problem 195: New Year Bonus Grant
 * Type: DP
 * Solution: DP[at][can give self bonus] is computed by taking best of: giving yourself a bonus, or one of your kids, or none of your kids.
 */

public class p195 {
	public static void main(String[] args) throws Exception, IOException
	{
		BufferedReader R = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(R.readLine().trim());
		int[] P = new int[N];
		StringTokenizer st = new StringTokenizer(R.readLine().trim());
		for(int i = 1; i < N;i++)
			P[i] = Integer.valueOf(st.nextToken())-1;
		long[] sum = new long[N];
		long[] best = new long[N];
		for(int i = N-1; i > 0;i--)
		{
			sum[P[i]] += sum[i]+best[i];
			best[P[i]] = max(best[P[i]],(1+sum[i])-(sum[i]+best[i]));
		}
		long ans =sum[0]+best[0];
		int[] G = new int[N];	

		ArrayList<Integer> list = new ArrayList<Integer>();

		for(int i = 0; i < N;i++)
		{
			if(G[P[i]] == 1)
			{
				G[i] = 0;
			}else{
				if((1+sum[i])-(sum[i]+best[i]) == best[P[i]])
				{
					list.add((i+1));
					G[P[i]] = 1;
					G[i] = 1;
				}
			}
		}
		System.out.println(ans*1000);
		for(int e:list)
			System.out.print(e+" ");
		System.out.println();
	}
}
