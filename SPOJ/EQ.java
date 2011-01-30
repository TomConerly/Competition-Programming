package SPOJ;
import static java.lang.Math.*;
import java.util.*;

/* SPOJ Problem 5466: EQ
 * Type: Simulation
 * Solution: Just simulate it.
 */

public class EQ {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int zz = sc.nextInt();
		for(int z = 0; z < zz;z++)
		{
			int N = sc.nextInt();
			int P = sc.nextInt();
			long[] C = new long[N];
			long ans = 0;
			for(int i = 0; i < P;i++)
			{
				int a = sc.nextInt();
				int t = sc.nextInt();
				int ma = 0;
				for(int j = 0; j < N;j++)
					if(max(C[j],a) < max(C[ma],a))
						ma = j;
				long start = max(C[ma],a);
				C[ma] = start + 5+t;
				ans += 5+t + start-a;
			}
			System.out.println(ans);
			
		}
	}
}
