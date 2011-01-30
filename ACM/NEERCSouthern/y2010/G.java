package ACM.NEERCSouthern.y2010;

import java.util.*;
import static java.lang.Math.*;
import java.io.*;

public class G {
	public void p(Object...o){System.out.println(Arrays.deepToString(o));}
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] V = new int[N];
		for(int i = 0; i < N;i++)
			V[i] = sc.nextInt();
		double best = Long.MAX_VALUE;
		double[] A = new double[N];
		double[] B = new double[N];
		for(int i = 0; i < N;i++){
			for(int j = i+1; j < N;j++){
				double a1 = V[i];
				double a2 = V[j];
				double len = (a2-a1)/(j-i);
				double first = a1-len*i;
				double ans = 0;
				double at = first;
				for(int k = 0; k < N;k++){
					ans += abs(V[k]-at);
					B[k] = at;
					at += len;
				}
				if(ans < best){
					best = ans;
					for(int k = 0; k < N;k++)
						A[k] = B[k];
				}
			}
		}
		System.out.format("%.09f\n", best);
		for(int i = 0; i < N;i++){
			if(i > 0)
				System.out.format(" ");
			System.out.format("%.09f", A[i]);
		}
		System.out.format("\n");
	}
}
