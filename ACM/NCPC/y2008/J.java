package ACM.NCPC.y2008;
import java.util.*;
import static java.lang.Math.*;

/* ACM NCPC 2008
 * Problem J: Just A Few More Triangles!
 * Type: Math
 * Solution: Make a vector X such that x[i] equals the number of 1<= j <=n such that j*j mod n = i. Consider X as a number where each element is a digit.
 * (X*X)[i] is the number of ways to have (j*j mod n) + (k*k mod n) = i. Now take X and for all entries greater than n mod them down to where they should be.
 * Now take the dot product the fixed up X^2 with X and you have your answer. Note that this method doesn't have the restriction that a<=b for a^2+b^2=c^2 so we
 * double count solutions when a!=b. Count the solutions when a=b (takes linear time) remove those from the answer, then half the answer.
 * 
 * Now to compute X*X we can use karatsuba for FFT. This uses karatsuba and runs in 90s.
 */

public class J 
{
	public static void main(String[] args)
	{
		
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextInt()){
			long st = System.currentTimeMillis();
			int N = sc.nextInt();
			long[] X = new long[N];
			long[] Y = new long[N];
			long[] C = new long[N];

			for(long i = 1; i < N;i++)
			{
				X[(int)((i*i)%N)]++;
				Y[(int)((i*i)%N)]++;
				C[(int)((i*i)%N)]++;
			}
			long sing = 0;
			for(int i = 0; i < N;i++)
			{
				sing += X[i]*X[(2*i)%N];
			}
			long[] prod = mult(X,Y);

			long[] P = new long[N];
			for(int i = 0; i < prod.length;i++)
			{
				P[i%N] += prod[i];
			}
			long ans = 0;
			for(int i = 0; i < N;i++)
			{
				ans += C[i]*P[i];
			}
			ans = (ans-sing)/2 + sing;

				
			System.out.println(ans);
			long en = System.currentTimeMillis();
			System.out.println("time: "+(en-st));
		}
		

	}

	private static long[] mult(long[] X, long[] Y) {
		if(X.length + Y.length < 100)
			return slow_mul(X,Y);
		
		int m = max(X.length,Y.length)/2;

		long[] x0 = new long[m];
		long[] y0 = new long[m];
		for(int i = 0; i < m;i++)
		{
			x0[i] = X[i];
			y0[i] = Y[i];
		}
		long[] x1 = new long[X.length-m];
		for(int i = m;i<X.length;i++)
			x1[i-m] = X[i];

		long[] y1 = new long[Y.length-m];
		for(int i = m;i<Y.length;i++)
			y1[i-m] = Y[i];

		long[] z2 = mult(x1,y1);
		long[] z0 = mult(x0,y0);

		long[] z1 = sub(mult(add(x0,x1),add(y0,y1)),add(z0,z2));

		long[] ans = new long[max(max(z2.length+2*m,z1.length+m),z0.length)];
		for(int i = 0; i < z0.length;i++)
			ans[i] += z0[i];
		for(int i = 0;i < z1.length;i++)
			ans[i+m] += z1[i];
		for(int i = 0; i < z2.length;i++)
			ans[i+2*m] += z2[i];
		return ans;
	}
	private static long[] slow_mul(long[] X, long[] Y)
	{
		long[] ans = new long[X.length+Y.length];
		for(int i = 0; i < X.length;i++)
			for(int j = 0; j < Y.length;j++)
				ans[i+j] += X[i]*Y[j];
		return ans;
	}

	private static long[] sub(long[] X, long[] Y) {
		long[] Z = new long[max(X.length,Y.length)];
		for(int i = 0; i < Z.length;i++)
		{
			if(i < X.length)
				Z[i] += X[i];
			if(i < Y.length)
				Z[i] -= Y[i];
		}
		return Z;
	}

	private static long[] add(long[] X, long[] Y) {
		long[] Z = new long[max(X.length,Y.length)];
		for(int i = 0; i < Z.length;i++)
		{
			if(i < X.length)
				Z[i] += X[i];
			if(i < Y.length)
				Z[i] += Y[i];
		}
		return Z;
		
	}
}
