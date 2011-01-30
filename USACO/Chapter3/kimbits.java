package USACO.Chapter3;

/*
ID: theycal2
LANG: JAVA
TASK: kimbits
 */
/* USACO Training
 * Stringsobits
 * Type: Number Theory
 * Solution: Start with the brute force. If we have n 1s and the 
 * last few digits are all zeroes we can skip all of those because they can't be good.
 * We can do the inverse to skip when we know things are counted.
 */
import java.io.*;
import java.util.*;

public class kimbits 
{
	public static void main(String[] args) throws IOException 
	{
		long start = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("kimbits.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("kimbits.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.valueOf(st.nextToken());
		int L = Integer.valueOf(st.nextToken());
		long I = Long.valueOf(st.nextToken());

		long count = 0;
		long ans=0;
		long max = 1L<<N;
		for(long i = 0; i <= max;i++)
		{
			if(Long.bitCount(i) > L) {
				int c = 0;
				long t = i;
				while((t&1) == 0 && t != 0)
				{
					c++;
					t >>= 1;
				}
				i+= (1<<c)-1;
				continue;
			}
			count++;
			if(count == I)
			{
				System.out.println("found");
				ans = i;
				break;
			}
			int c = 0;
			long t = i;
			while((t&1) == 1)
			{
				c++;
				t >>= 1;
			}
			if(I-count > (1L<<c)-1)
			{
				i+= (1L<<c)-1;
				count += (1L<<c)-1;
			}
		}
		String str = Long.toBinaryString(ans);
		while(str.length() < N)
		{
			str = "0"+str;
		}
		out.println(str);
		out.close();
		System.out.println("$:"+(System.currentTimeMillis()-start));
		System.exit(0);
	}
}

