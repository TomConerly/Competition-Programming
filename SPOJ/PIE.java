package SPOJ;
import java.util.*;
import static java.lang.Math.*;

/* SPOJ Problem 1837: PIE
 * Type: Binary Search
 * Solution: Binary search of the volume.
 */

public class PIE {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int zz = sc.nextInt();
		for(int z = 0; z < zz;z++)
		{
			int N = sc.nextInt();
			int F = sc.nextInt();
			double[] v = new double[N];
			for(int i = 0; i < N;i++)
			{
				int r = sc.nextInt();
				v[i] = PI*r*r;
			}
			double low = 0;
			double high = 1e20;
			for(int i = 0; i < 150;i++)
			{
				double mid = (low+high)/2;
				int num = 0;
				for(int j =0; j < N;j++)
					num += (int)(floor(v[j]/mid));
				//System.out.println(num+" "+mid);
				if(num >= F+1)
				{
					low = mid;
				}else{
					high = mid;
				}
			}
			System.out.println(low);
		}
	}
}
