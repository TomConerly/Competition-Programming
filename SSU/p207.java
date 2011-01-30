package SSU;
import java.util.*;
import static java.lang.Math.*;

/* Saratov State University Online Judge
 * Problem 207:Robbers
 * Type: Greedy
 * Solution: Round everything down. Distribute first extra coin to person who gets the most out of it, then repeat.
 */

public class p207 {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int Y = sc.nextInt();
		int[] X = new int[N];
		int[] K = new int[N];
		Left[] L = new Left[N];
		int left = M;
		for(int i = 0; i < N;i++)
		{
			
			X[i] = sc.nextInt();
			K[i] = (int)floor(X[i]/(double)Y*M);
			L[i] = new Left(unfair(X[i],Y,K[i],M)-unfair(X[i],Y,K[i]+1,M),i);
			
			left -= K[i];
		}
		Arrays.sort(L);
		for(int i = L.length-1;i>= 0 && left > 0;i--)
		{
			K[L[i].idx]++;
			left--;
		}
		for(int i = 0; i < N;i++)
			System.out.print(K[i]+" ");
		System.out.println();
		
	}
	public static double unfair(double x, double y, double k, double m)
	{
		return abs(x/y-k/m);
	}
	private static class Left implements Comparable<Left>
	{
		double p;
		int idx;
		public Left(double p, int idx)
		{
			this.p = p;
			this.idx = idx;
		}
		@Override
		public int compareTo(Left l) {
			return Double.valueOf(p).compareTo(l.p);
		}
		public String toString()
		{
			return idx+" "+p;
		}
	}
}
