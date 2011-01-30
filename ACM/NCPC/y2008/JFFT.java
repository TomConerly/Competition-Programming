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
 * Now to compute X*X we can use karatsuba for FFT. This uses FFT and runs in 30s.
 */

public class JFFT
{
	public static void main(String[] args)
	{
		long st = System.currentTimeMillis();
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextInt()){

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

		}

		long en = System.currentTimeMillis();
		System.out.println("time: "+(en-st));
	}

	private static long[] mult(long[] X, long[] Y) 
	{
		int xlen = Integer.highestOneBit(X.length)<<2;
		int ylen = Integer.highestOneBit(Y.length)<<2;
		Complex[] a = new Complex[xlen];
		Complex[] b = new Complex[ylen];
		for(int i = 0; i < X.length;i++)		
			a[i] = new Complex(X[i],0);		
		for(int i = X.length;i < xlen;i++)
			a[i] = new Complex(0,0);

		for(int i = 0; i < Y.length;i++)		
			b[i] = new Complex(Y[i],0);		
		for(int i = Y.length;i < ylen;i++)
			b[i] = new Complex(0,0);

		Complex[] ans = cconvolve(a, b);
		long[] an = new long[ans.length];
		for(int i = 0; i < an.length;i++)
		{
			an[i] = (long)(ans[i].r+0.5);
		}
		return an;
	}













	//START FFT CODE
	public static Complex[] fft(Complex[] x) {
		int N = x.length;
		if (N == 1) return new Complex[] {x[0]};
		Complex[] even = new Complex[N/2];
		for (int k = 0; k < N/2; k++)
			even[k] = x[2*k];
		Complex[] q = fft(even);
		Complex[] odd  = even;  
		for (int k = 0; k < N/2; k++)
			odd[k] = x[2*k + 1];		
		Complex[] r = fft(odd);
		Complex[] y = new Complex[N];
		double wtemp = sin(PI/N);
		double wpr = -2.0*wtemp*wtemp;
		double wpi = -sin(2*PI/N);
		double wr = 1.0;
		double wi = 0.0;
		for (int k = 0; k < N/2; k++) {
			Complex wk = new Complex(wr,wi);
			y[k]       = q[k].plus(wk.times(r[k]));
			y[k + N/2] = q[k].minus(wk.times(r[k]));
			wtemp = wr;
			wr += wr*wpr-wi*wpi;
			wi += wi*wpr+wtemp*wpi;
		}
		return y;
	}
	public static Complex[] ifft(Complex[] x) {
		int N = x.length;
		Complex[] y = new Complex[N];
		for (int i = 0; i < N; i++) 
			y[i] = x[i].conjugate();
		y = fft(y);
		for (int i = 0; i < N; i++) 
			y[i] = y[i].conjugate();
		for (int i = 0; i < N; i++) 
			y[i] = y[i].times(1.0 / N);		
		return y;
	}
	public static Complex[] cconvolve(Complex[] x, Complex[] y) {
		Complex[] a = fft(x);
		Complex[] b = fft(y);
		Complex[] c = new Complex[x.length];
		for (int i = 0; i < x.length; i++) 
			c[i] = a[i].times(b[i]);		
		return ifft(c);
	}    
	private static class Complex {
		public final double r;
		public final double i;
		public Complex(double real, double imag) {r = real;i = imag;}
		public Complex plus(Complex b) {return new Complex( r + b.r, i + b.i);}
		public Complex minus(Complex b) {return new Complex( r - b.r, i - b.i);}
		public Complex times(Complex b) {return new Complex( r * b.r - i * b.i, r * b.i +i * b.r);}
		public Complex times(double alpha) {return new Complex(alpha * r, alpha * i); }
		public Complex conjugate() {  return new Complex(r, -i); }
	}
}