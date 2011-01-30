package SSU;
import java.util.*;
import static java.lang.Math.*;

/* Saratov State University Online Judge
 * Problem 198: Get Out!
 * Type: Computational Geometry
 * Solution: Attempt to find a cycle around the ship s.t. each pair of islands are close enough together. Keep track of the angle
 * and see if you visit the same place twice with a different angle (it would be off by 2PI). You do this because going one way you always add to your angle
 * and the other way you always subtract. 
 */

public class p198 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		X = new double[N];
		Y = new double[N];
		R = new double[N];
		for(int i = 0; i < N;i++)
		{
			X[i] = sc.nextDouble();
			Y[i] = sc.nextDouble();
			R[i] = sc.nextDouble();
		}
		X0 = sc.nextDouble();
		Y0 = sc.nextDouble();
		R0 = sc.nextDouble();
		adj = new ArrayList[N];
		for(int i = 0; i < N;i++)
		{
			X[i] -= X0;
			Y[i] -= Y0;
		}
		X0 = 0;
		Y0 = 0;
		for(int i = 0; i < N;i++)
		{
			adj[i] = new ArrayList<Integer>();
			for(int j = 0; j < N;j++)
			{
				if(i==j)
					continue;
				if(sqrt(pow(X[i]-X[j],2)+pow(Y[i]-Y[j],2)) - R[i]-R[j] +1e-7< 2*R0)
				{
					//System.out.println(i+" "+j);
					adj[i].add(j);
				}
			}
		}
		V = new boolean[N];
		A = new double[N];
		for(int i = 0; i < N;i++)
		{
			if(!V[i])
				dfs(i,0.0);
		}
		if(found)
			System.out.println("NO");
		else
			System.out.println("YES");
		
	}
	static boolean found = false;
	static boolean[] V;
	static double[] A;
	static void dfs(int at, double ang)
	{
		if(V[at])
		{
			if(abs(A[at]-ang) > PI/2)
				found = true;
			return;
		}
		V[at] = true;
		A[at] = ang;
		for(int e:adj[at])
		{		
			double theta = abs(atan2(Y[at],X[at])-atan2(Y[e],X[e]));
			if(theta > PI)
			{
				theta = 2*PI-theta;
			}
			double d1 = sqrt(pow(X[at],2)+pow(Y[at],2));
			double d2 = sqrt(pow(X[e],2)+pow(Y[e],2));
			double x = X[at]/d1*cos(theta)-Y[at]/d1*sin(theta);
			double y = X[at]/d1*sin(theta)+Y[at]/d1*cos(theta);
			double nang;
			if(abs(x-X[e]/d2) < 1e-5 && abs(y-Y[e]/d2) < 1e-5)
			{
				nang = ang+theta;
			}else{
				nang = ang-theta;
			}
			dfs(e,nang);
		}
	}
	static int N;
	static double X0,Y0,R0;
	static double[] X,Y,R;
	static ArrayList<Integer>[] adj;
}
