package TopCoder.Medium;
import java.util.*;
import static java.lang.Math.*;

/* TopCoder SRM 378 
 * Medium Problem 500 Points: SolvePolynomial
 * Type: Math
 * Solution: I forget... read the editorial.
 */

public class SolvePolynomial {
	int[] a;
	public int[] integerRoots(int[] X, int[] Y, int n) {
		int lx = X.length;
		int ly = Y.length;
		a = new int[n+1];
		for(int i = 0; i <= n;i++)
		{
			int p = i %lx;
			int q = (i+Y[i%ly])% lx;
			a[i] = X[p];
			X[p] = X[q];
			X[q] = a[i];
		}
		ArrayList<Integer> ans = new ArrayList<Integer>();
		while(a[0] == 0 && a.length > 0)
		{
			if(a.length == n+1) ans.add(0);
			int[] na = new int[a.length-1];
			for(int i = 1; i < a.length;i++)
				na[i-1] = a[i];
			a = na;
		}
		if(a.length == 0) return new int[]{0};
		int c = 0;
		for(int i = 2;i*i <= abs(a[0]);i++)
		{
			if(a[0]%i == 0)
			{
				p(c);
				if(test(i)) ans.add(i);
				if(test(-i)) ans.add(-i);				
				if(i*i != abs(a[0])){
					if(test(a[0]/i)) ans.add(a[0]/i);
					if(test(-a[0]/i)) ans.add(-a[0]/i);
				}
			}
		}
		if(test(-1)) ans.add(-1);
		if(test(1)) ans.add(1);
		if(1 != abs(a[0])){
			if(test(-a[0])) ans.add(-a[0]);
			if(test(a[0])) ans.add(a[0]);
		}
		
		int[] an = new int[ans.size()];
		for(int i = 0; i < an.length;i++)
			an[i] = ans.get(i);
		Arrays.sort(an);
		return an;
	}
	private boolean test(int n) 
	{		
		long t = 0;
		for(int i = 0;i<a.length;i++)
		{
			t += a[i];
			if(t%n != 0) return false;
			t/= n;
		}
		if(t != 0) return false;
		return true;
		
	}
	
	public void p(Object o){System.out.println(o);}
}
