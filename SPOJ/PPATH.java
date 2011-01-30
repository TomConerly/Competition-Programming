package SPOJ;
import java.util.*;
import static java.lang.Math.*;

/* SPOJ Problem 1841: PPATH
 * Type: BFS
 * Solution: Generate the prime table, then just
 * BFS.
 */

public class PPATH {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int zz = sc.nextInt();
		boolean[] prime = new boolean[10000];
		for(int i = 2;i < prime.length;i++)
		{
			if(!prime[i])
			{
				for(int j = i*2;j< prime.length;j+=i)
					prime[j] = true;
			}
		}
		for(int z = 0; z < zz;z++)
		{
			int st = sc.nextInt();
			int en = sc.nextInt();
			Queue<Integer> q = new LinkedList<Integer>();
			q.add(st);
			int ans = -1;
			boolean[] seen = new boolean[10000];
			while(q.size() > 0)
			{
				int n = q.poll();
				int at = n & 0xFFFF;
				int cost = n >>> 16;
				
				seen[at] = true;
				if(at == en)
				{
					ans = cost;
					break;
				}
				
				for(int i = 0; i < 4;i++)
				{
					for(int j = 0; j < 10;j++)
					{
						int pow = (int)(pow(10,i));
						int num = at%pow + j*pow + (at/(pow*10))*pow*10;
						if(num < 1000) continue;
						if(num >= 10000) continue;
						if(seen[num]) continue;
						if(prime[num]) continue;
						seen[num] = true;
						q.offer((cost+1)<<16 | (num));
					}
				}
			}
			if(ans == -1)
				System.out.println("Impossible");
			else System.out.println(ans);
		}
	}

}
