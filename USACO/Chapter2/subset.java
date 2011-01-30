package USACO.Chapter2;
/*
ID: theycal2
LANG: JAVA
TASK: subset
 */
/* USACO Training
 * Subset Sums
 * Type: DP
 * Solution: Straightforward.
 */
import java.io.*;
import java.util.*;

public class subset 
{
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(new File("subset.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));

		int N = sc.nextInt();
		int sum = 0;
		for(int i = 1; i <= N;i++) sum += i;
		num = new long[N][sum+1];
		goal = sum/2;
		for(int i = 0; i < N;i++)
		{
			Arrays.fill(num[i],-1);
		}
		if(sum %2 == 1) out.println("0");
		else{
			//recur2(0,0);
			//out.println(total/2);
			out.println(recur(0,0)/2);
		}
		out.close();
	}
	static int goal;
	static long[][] num;
	public static long recur(int at, int sum)
	{
		if(sum == goal) return 1;
		if(at == num.length) return 0;
		
		if(num[at][sum] != -1) return num[at][sum];
		
		long ans = recur(at+1,sum+at+1)+recur(at+1,sum);
		
		num[at][sum] = ans;
		return ans;
	}
	/*static long total = 0;
	public static void recur2(int at, int sum)
	{
		if(at == num.length)
		{			
			if(sum == goal) total++;
			return;
		}
		recur2(at+1,sum+at+1);
		recur2(at+1,sum);
		
	}*/

}

