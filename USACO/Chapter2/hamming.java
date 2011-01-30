package USACO.Chapter2;
/*
ID: theycal2
LANG: JAVA
TASK: hamming
 */
/* USACO Training
 * Hamming Codes
 * Type: Greedy
 * Solution: Always pick the minimal new code
 * that works with all of the other ones.
 */
import java.io.*;
import java.util.*;

public class hamming 
{
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(new File("hamming.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
		N = sc.nextInt();
		B = sc.nextInt();
		D = sc.nextInt();

		ArrayList<Integer> set = new ArrayList<Integer>();
		set.add(0);
		for(int i = 1; i < N;i++)
		{
			for(int j = 0; j < (1 << B);j++)
			{
				boolean good = true;
				for(int k = 0; k < set.size();k++)
				{
					if(hd(set.get(k),j) < D)
					{
						good = false;
						break;
					}
				}
				if(good)
				{
					set.add(j);break;
				}
			}
		}
		for(int i = 0; i < set.size();i++)
		{
			if(i % 10 == 9 || i == set.size()-1)
			{
				out.println(set.get(i));
			}
			else out.print(set.get(i)+" ");
		}
		out.close();
	}
	static int N,B,D;
	static int hd(int a, int b)
	{
		int count = 0;
		for(int i = 0; i < B;i++)
		{
			if((a&1) != (b&1)) count++;
			a>>=1;
		b>>=1;
		}
		return count;
	}
}

