package USACO.Chapter4;
/*
ID: theycal2
LANG: JAVA
TASK: nuggets
 */
/* USACO Training
 * Beef McNuggets
 * Type: Number Theory/DP
 * Solution: Check all pairs GCD if none of them
 * are 1 then we cannot generate all numbers.
 * Otherwise just DP over first 100,000 numbers.
 * If we can get all above some N, then N must be 
 * less than ~60,000 the product of the two largest
 * primes less than 256.
 */
import java.io.*;

public class nuggets 
{
	public static void main(String[] args) throws IOException 
	{
		long start = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("nuggets.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nuggets.out")));

		int N = Integer.valueOf(f.readLine());
		int[] nums = new int[N];
		for(int i = 0; i < N;i++)
			nums[i] = Integer.valueOf(f.readLine());

		boolean possible = false;
		for(int i = 0; i < N;i++)
			for(int j = i+1;j<N;j++)
				if(gcd(nums[i],nums[j]) == 1)
					possible = true;
		
		if(!possible)
		{
			out.println("0");
		}
		else
		{
			boolean[] pos = new boolean[100000];
			pos[0] = true;
			int ans = 0;
			for(int i = 0; i < pos.length;i++)
			{
				if(!pos[i])
				{
					ans = i;
				}else{
					for(int j = 0; j < N;j++)
						if(i+nums[j] < pos.length)
							pos[i+nums[j]] = true;
				}
			}
			out.println(ans);
		}		
		
		out.close();
		System.out.println("$:"+(System.currentTimeMillis()-start));
		System.exit(0);
	}
	public static int gcd(int a, int b)
	{
		if (b == 0) return a;
		else return gcd(b, a % b);
	}
}

