package USACO.Chapter3;
/*
ID: theycal2
LANG: JAVA
TASK: humble
 */
/* USACO Training
 * Humble Numbers
 * Type: Number Theory
 * Solution: First attempt (solve) was to put numbers into a
 * tree set, take the min humble number out and add that humble 
 * number multiplied by each of the primes, that was too slow.
 * Second method (solve2) keeps a list of the largest humble number
 * that a prime can be multiplied by to create a larger humble number.
 * Takes the min of those and then updates, much faster. 
 */
import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class humble 
{
	public static void main(String[] args) throws IOException 
	{
		long start = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("humble.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("humble.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		int[] nums = new int[K];
		st = new StringTokenizer(f.readLine());
		for(int i = 0; i < K;i++)
		{
			nums[i] = Integer.parseInt(st.nextToken()); 
		}
		Arrays.sort(nums);			

	//	System.out.println(solve(nums,N,K));
		
		//System.out.println("$:"+(System.currentTimeMillis()-start));
		start = System.currentTimeMillis();
		

		out.println(solve2(nums,N,K));
		out.close();
		System.out.println("$:"+(System.currentTimeMillis()-start));
		System.exit(0);
	}
	static int solve2(int[] nums, int N ,int K)
	{
		int[] hum = new int[N+1];
		int[] next = new int[K];
		
		hum[0] = 1;
		for(int i = 1; i <= N;i++)
		{
			int best = Integer.MAX_VALUE;
			for(int j = 0; j < K;j++)
			{
				while(next[j] < i && nums[j]*hum[next[j]] <= hum[i-1])
				{
					next[j]++;
				}
				best = min(best,nums[j]*hum[next[j]]);
			}
			hum[i] = best;
		}		
		
		return hum[N];
	}
	static int solve(int[] nums, int N, int K)
	{
		TreeSet<Integer> ts = new TreeSet<Integer>();
		ts.add(1);
		int high = K;
		for(int i = 0; i < N;i++)
		{
			int a = ts.first();
			ts.remove(a);

			for(int j = 0; j < high;j++)
			{
				long temp = ((long)a)*nums[j];
				if(temp > Integer.MAX_VALUE) 
				{
					high = j;
					break;
				}

				int toAdd = (int)temp;

				if(ts.size() == N-i)
				{						
					if(ts.last() > toAdd)
					{
						if(ts.add(toAdd))
							ts.remove(ts.last());
					}else{
						high = j;
						break;
					}
				}else{
					ts.add(toAdd);
				}	
			}
		}	
		return ts.first();
	}
}

