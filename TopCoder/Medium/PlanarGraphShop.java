package TopCoder.Medium;
import java.util.*;

/* TopCoder SRM 453.5
 * Medium Problem 450 Points: PlanarGraphShop
 * Type: Graph Theory
 * Solution: A planar graph can have at most 3n-6 edges.
 */

public class PlanarGraphShop {
	public void p(Object s){System.out.println(s);}
	public int bestCount(int N) {
		TreeSet<Integer> pos = new TreeSet<Integer>();
		pos.add(1);
		pos.add(8);
		pos.add(9);
		for(int i = 3; i*i*i <=N;i++)
		{
			for(int j = 0; j <= 3*i-6;j++)
			{
				pos.add(i*i*i+j*j);
			}
		}
		int[] minDist = new int[N+1];
		Arrays.fill(minDist,-1);
		minDist[0] = 0;
		for(int i = 0; i < N;i++)
		{
			if(minDist[i] == -1)
				continue;
			if(minDist[N] != -1 && minDist[N] <= minDist[i])
				continue;
			for(int j:pos)
			{
				if(i+j > N)
					break;
				if(minDist[i+j] == -1 || minDist[i+j] > minDist[i]+1)
				{
					minDist[i+j] = minDist[i]+1;
				}
			}
		}
		return minDist[N];		
	}	
}
