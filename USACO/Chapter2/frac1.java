package USACO.Chapter2;
/*
ID: theycal2
LANG: JAVA
TASK: frac1
 */
/* USACO Training
 * The Ordered Fractions
 * Type: Simulation
 * Solution: Brute force it.
 */
import java.io.*;
import java.util.*;

public class frac1 
{
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(new File("frac1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
		N = sc.nextInt();
		
		ArrayList<frac> ans = new ArrayList<frac>();
		ans.add(new frac(0,1));
		ans.add(new frac(1,1));
		for(int den = 1; den <= N;den++)
		{
			for(int num = 1; num < den;num++)
			{
				if(gcd(den,num) == 1) 
					ans.add(new frac(num,den));
			}
		}
		Collections.sort(ans);
		for(frac f: ans)
		{
			out.println(f.num+"/"+f.den);
		}
		
		out.close();
	}
	static int gcd(int x, int y)
	{
		if(y==0) return x;
		else return gcd(y,x%y);
	}
	static int N,M;
	private static class frac implements Comparable<frac>
	{
		int num,den;
		public frac(int n,int d)
		{
			num = n; den = d;
		}
		public int compareTo(frac f)
		{
			if(((double)num)/den > ((double)f.num)/f.den) return 1; 
			if(((double)num)/den < ((double)f.num)/f.den) return -1; 
			else return 0;
		}
	}
}

