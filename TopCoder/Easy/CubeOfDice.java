package TopCoder.Easy;
import static java.lang.Math.*;

/* TopCoder TCO09 Round 4 
 * Easy Problem 250 Points: CubeOfDice
 * Type: Simulation
 * Solution: You know for corner/edge/face dice what the min value is. Calculate
 * the value of each then count how many (note that when n = 1 is a special case).
 */

public class CubeOfDice {

	public long minimumSum(int n, int[] values) {
		long N = n;
		if(N == 1)
		{
			int max = 0;
			int sum = 0;
			for(int a: values)
			{
				max = max(a,max);
				sum += a;
			}
			return sum-max;
		}
		long nC = 4;
		long nE = 4*(N-1)+4*(N-2);
		long nI = 4*(N-1)*(N-2)+(N-2)*(N-2);
		long check = nC*3+nE*2+nI;
		if(check != 5*N*N)
			System.out.println("BAD COUNT");
		
		int min = Integer.MAX_VALUE;
		for(int a: values)
			min = min(a,min);
		
		int minEdge = Integer.MAX_VALUE;
		for(int i = 0; i < 6;i++)
		{
			for(int j = i+1;j<6;j++)
			{
				if(i == 0 && j == 5) continue;
				if(i == 1 && j == 4) continue;
				if(i == 2 && j == 3) continue;
				minEdge = min(minEdge,values[i]+values[j]);
			}
		}
		int minCorner = values[0]+values[1]+values[2];
		minCorner = min(minCorner,values[0]+values[2]+values[4]);
		minCorner = min(minCorner,values[0]+values[3]+values[4]);
		minCorner = min(minCorner,values[0]+values[3]+values[1]);
		
		minCorner = min(minCorner,values[5]+values[1]+values[2]);
		minCorner = min(minCorner,values[5]+values[2]+values[4]);
		minCorner = min(minCorner,values[5]+values[4]+values[3]);
		minCorner = min(minCorner,values[5]+values[3]+values[1]);
		return nC*minCorner + nE*minEdge+nI*min;
	}
	public void p(Object o){System.out.println(o);}
}
