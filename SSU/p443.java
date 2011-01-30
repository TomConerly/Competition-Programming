package SSU;
import java.util.*;

/* Saratov State University Online Judge
 * Problem 443: Everlasting...?
 * Type: Math
 * Solution: Just do the math.
 */

public class p443 {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		TreeSet<Integer> fa = new TreeSet<Integer>();
		for(int i = 2; i <= A;i++)
		{
			while(A%i == 0)
			{
				fa.add(i);
				A /= i;
			}
		}
		TreeSet<Integer> fb = new TreeSet<Integer>();
		for(int i = 2; i <= B;i++)
		{
			while(B%i == 0)
			{
				fb.add(i);
				B /= i;
			}
		}
		int sa = fa.last()*2;
		for(int a:fa)
			sa -= a;
		int sb = fb.last()*2;
		for(int b:fb)
			sb -= b;
		if(sa > sb)
			System.out.println("a");
		else
			System.out.println("b");
	}
}
