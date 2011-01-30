package USACO.Chapter2;
/*
ID: theycal2
LANG: JAVA
TASK: runround
 */
/* USACO Training
 * Runaround Numbers
 * Type: Simulation
 * Solution: Make sure to just create all # with unique digits and check those
 * not all numbers.
 */
import java.io.*;
import java.util.*;

public class runround 
{
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(new File("runround.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));

		long start = sc.nextLong();
		out.println(next(start));
		
		out.close();
	}
	static long min;
	static long best;
	
	public static long next(long start)
	{
		int numDigit = 0;
		long temp = start;
		while(temp != 0)
		{
			numDigit++;
			temp/=10;
		}
		min = start;
		best = Long.MAX_VALUE;
		for(int i = numDigit; i <= 9;i++)
		{
			if(best != Long.MAX_VALUE) break;
			int[] set = new int[10];
			Arrays.fill(set,-1);
			recur(0,new boolean[10],set,i);
		}
		
		return best;
	}
	public static long pow10(int i)
	{
		long a = 1;
		for(int j = 0; j < i;j++) a*=10;
		return a;
	}
	public static void recur(int at, boolean[] used, int[] set, int max)
	{
		if(at == max)
		{
			long num = 0;
			for(int i = 0; i < set.length;i++)
			{
				if(set[i] == -1) continue;
				num += pow10(set[i])*(i+1);
			}
			if(num > min && num < best && round(num))
			{
				best = num;
			}
			return;
		}
		for(int i = 0; i < 9;i++)
		{
			if(used[i]) continue;
			used[i] = true;
			set[i] = at;
			recur(at+1,used,set,max);
			used[i] = false;
			set[i] = -1;
		}
	}
	public static boolean round(long num)
	{		
		int[] digits = new int[20];
		int at = 0;
		while(num != 0)
		{
			digits[at] = (int)(num%10);
			num/=10;
			at++;
		}
		int[] r = new int[at];
		
		boolean[] in = new boolean[10];
		for(int i = 0; i < at;i++)
		{
			r[i] = digits[i];		
			if(in[r[i]]) return false;
			in[r[i]] = true;
		}
		boolean[] visited = new boolean[10];
		
		at = at-1;
		for(int i = r.length-1;;i--)
		{
			if(visited[r[at]]) return false;
			visited[r[at]] = true;			
			
			at = (at - r[at]+r.length*10)%r.length;			
			if(at == r.length-1) break;
		}
		for(int i = 0; i < 10;i++)
		{
			if(visited[i] != in[i]) return false;
		}
		return true;
	}
}

