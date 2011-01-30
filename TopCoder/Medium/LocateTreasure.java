package TopCoder.Medium;
/* TopCoder SRM 427
 * Med Problem 600 Points: LocateTresure
 * Type: Simulation
 * Solution: Once we find a cycle round in the digits
 * then round it up so it a cycle of length len%4=0
 * then we can just apply the cycle a larger number of times
 * by x = dx*num cycles, y = dy*num cycles. Then at the end
 * apply the move a few times to get to the end.
 */
import java.util.Arrays;
import static java.lang.Math.*;

public class LocateTreasure {
	int[][] d = {{0,1},{1,0},{0,-1},{-1,0}};
	public String location(int K, int multi) {
		long g = 1;
		int[] visited = new int[10];
		Arrays.fill(visited,-1);
		int t = 0;

		while(true)
		{
			if(visited[dig(g)] != -1) break;
			visited[dig(g)] = t;
			t++;
			g = dig(g)*multi;
		}
		int x = 0;
		int y = 0;
		int tempG = 1;
		int start = visited[dig(g)];
		for(int i = 0; i < min(start,K);i++)
		{
			x += d[i%4][0]*dig(tempG);
			y += d[i%4][1]*dig(tempG);
			tempG = dig(tempG)*multi;
		}
		if(K <= start) return x+" "+y;
		int cycleLen = t-start;
		if(cycleLen%4 != 0) cycleLen *= 2;
		if(cycleLen%4 != 0) cycleLen *= 2;

		//System.out.println(cycleLen+" "+start);
		
		int tx=0, ty=0;
		for(int i =start; i < start+cycleLen;i++)
		{
			//System.out.println("A:"+dig(tempG));
			tx += d[i%4][0]*dig(tempG);
			ty += d[i%4][1]*dig(tempG);
			tempG = dig(tempG)*multi;
		}
		//System.out.println("T:"+tx+" "+ty);
		
		x += (max(0,(K-start))/cycleLen)*tx;
		y += (max(0,(K-start))/cycleLen)*ty;

	//	System.out.println((K-start)+" "+x+" "+y);
		
		for(int i = ((K-start)/cycleLen)*cycleLen+start; i < K;i++)
		{
			x += d[i%4][0]*dig(tempG);
			y += d[i%4][1]*dig(tempG);
			tempG = dig(tempG)*multi;
		}


		return x+" "+y;
	}
	public int dig(long a)
	{
		int sum = 0;
		while(a != 0)
		{
			sum += a%10;
			a/=10;
		}
		if(sum < 10) return sum;
		else return dig(sum);
	}

}
