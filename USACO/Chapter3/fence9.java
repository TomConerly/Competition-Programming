package USACO.Chapter3;
/*
ID: theycal2
LANG: JAVA
TASK: fence9
 */
/* USACO Training
 * Electric Fence
 * Type: Computational Geometry
 * Solution: Picks theorem makes
 * it trivial.
 */
import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class fence9 
{
	public static void main(String[] args) throws IOException 
	{
		long start = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("fence9.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence9.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		int N = Integer.valueOf(st.nextToken());
		int M = Integer.valueOf(st.nextToken());
		int P = Integer.valueOf(st.nextToken());
		
		double area = P*M/2.0;
		int latice = 0;
		latice += P+1; // xaxis
		latice += gcd(N,M);//origin to point not on xaxis
		latice += gcd(abs(P-N),M)-1;//don't count either end point
		double ans = area + 1 - latice/2.0;
		System.out.println(area+" "+latice+" "+ans);
		out.println((int)ans);		

		
		out.close();
		System.out.println("$:"+(System.currentTimeMillis()-start));
		System.exit(0);
	}
	public static int gcd(int a, int b) 
	{ 
	   return ( b == 0 ? a : gcd(b, a % b) ); 
	}

}

