package USACO.Chapter1;
/*
ID: theycal2
LANG: JAVA
TASK: ariprog
 */
/* USACO Training
 * Arithmetic Progressions
 * Type: Brute Force
 * Solution: Brute force it. The time is an issue so use arrays not collections.
 */
import java.io.*;
import java.util.*;

public class ariprog 
{
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(new File("ariprog.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
		N = sc.nextInt();
		M = sc.nextInt();

		ArrayList<Integer> all = new ArrayList<Integer>();
		boolean[] h = new boolean[M*M*2+10];
		for(int i = 0; i <= M;i++)
		{
			for(int j = 0; j <=M;j++)
			{
				if(h[i*i+j*j]) continue;
				h[i*i+j*j] = true;
				all.add(i*i+j*j);
			}
		}

		Collections.sort(all);

		ArrayList<Long> ans = new ArrayList<Long>();
		
		for(int i = 0; i < h.length;i++)
		{
			if(h[i] == false) continue;
			for(int diff = 1; diff < 5000;diff++)
			{
				int at = i;
				int count = 0;
				for(count = 0; count < N && at < h.length && h[at]; count++)
				{
					at += diff;
				}
				if(count >= N)
					ans.add(((long)diff << 32) | i);				
			}
		}

		Collections.sort(ans);
		for(long a:ans)
		{
			out.println((a&0xFFFFFFFFL) +" "+(a >>>32));
		}
		if(ans.size()== 0) out.println("NONE");

		out.close();
	}
	static int N,M;
}

