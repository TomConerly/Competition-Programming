package GCJ.y2009.Round2;
import java.util.*;

/* Google Code Jam 2009 Round 2
 * Problem A: Crazy Rows
 * Type: Greedy
 * Solution: For each thing out of place find the closest thing that works their and swap them.
 */

public class A 
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int zz = 1; zz <= T;zz++)
		{			
			System.out.print("Case #"+zz+": ");
			int N = sc.nextInt();
			int[] rv = new int[N];
			for(int i = 0; i < N;i++)
			{
				String s = sc.next();
				for(int j = 0; j < s.length();j++)
				{
					if(s.charAt(j) == '1')
						rv[i] = j;
				}
			}
			int ans = 0;
			boolean done = false;
			while(!done)
			{
				done = true;
				for(int i = 0; i < N;i++)
				{
					if(rv[i] > i)
					{
						done = false;
						int swap = 0;
						for(int j = i+1;j<N;j++)
						{
							if(rv[j] <= i)
							{
								swap = j;
								break;
							}
						}
						while(swap != i)
						{
							ans++;
							int temp = rv[swap];
							rv[swap] = rv[swap-1];
							rv[swap-1] = temp;
							swap--;
						}
						break;
					}
				}				
			}
			System.out.println(ans);
		}
	}
}
