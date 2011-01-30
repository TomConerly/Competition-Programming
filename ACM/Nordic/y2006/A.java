package ACM.Nordic.y2006;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import static java.lang.Math.*;

public class A {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File("A.in"));
		int cc = sc.nextInt();
		for(int zz = 0; zz < cc;zz++)
		{
			//System.out.println(zz+2);
			int n = sc.nextInt();
			N = n;
			p = new double[n];
			for(int i = 0; i < n;i++)
				p[i] = sc.nextInt()/100.0;	
			dp = new double[n][n][1<<n];
			for(int i = 0; i < n;i++)
				dp[i][i][1<<i] = 1.0;
			target = new int[n][1<<n];


			for(int size = 2;size <= n;size++)
			{
				for(int at = 0; at < n;at++)
				{
					for(int sub = 0; sub < (1<<n);sub++)
					{
						if(Integer.bitCount(sub) != size) continue;
						if((sub & (1<<at)) == 0) continue;

						int bestAt = 0;
						double best = 0.0;
						for(int t = 0; t < n;t++)
						{
							if((sub & (1<<t)) == 0 || at == t) continue;
							if(s(at,sub,t) > best)
							{
								best = s(at,sub,t);
								bestAt = (1<<t);
							}else if(abs(s(at,sub,t) - best) < 1e-9)
							{
								bestAt |= (1<<t);
							}
						}
						target[at][sub] = bestAt;
					}
				}
				for(int at = 0; at < n;at++)
				{
					for(int sub = 0; sub < (1<<n);sub++)
					{
						if(Integer.bitCount(sub) != size) continue;
						if((sub & (1<<at)) == 0) continue;
						double[] prob = new double[n];
						double left = 1.0;
						int i = at;
						do
						{
							if((sub & (1<<i)) == 0){
								i = (i+1)%N;
								continue;
							}
							prob[i] += p[i]*left;
							left *= (1-p[i]);
							i = (i+1)%N;
						}while(i != at);
						
						double v = 0.0;
						for(int j = 0; j < n;j++)
						{
							if((sub & (1<<j)) == 0) continue;
							
							int t = target[j][sub];
							double dv = 0.0;
							for(int k = 0; k < n;k++)
							{
								if((t & (1<<k)) == 0) continue;
								dv += (dp[at][n(sub,j,k)][sub &(~(1<<(k)))]);
							}
							v += (dv*prob[j])/Integer.bitCount(t);
						}
						dp[at][at][sub] = v/(1-left);
					}
					for(int realAt = (at-1+N)%N; realAt != at;realAt = (realAt-1+N)%N)
					{
						for(int sub = 0; sub < (1<<n);sub++)
						{
							if(Integer.bitCount(sub) != size) continue;
							if((sub & (1<<at)) == 0 || (sub & (1<<realAt)) == 0) continue;
							int t = target[realAt][sub];
							dp[at][realAt][sub] = 0.0;
							for(int k = 0; k < n;k++)
							{
								if((t & (1<<k)) == 0) continue;
								dp[at][realAt][sub] += p[realAt]*dp[at][n(sub,realAt,k)][sub &(~(1<<k))]
								                          +(1-p[realAt])*dp[at][n(sub,realAt,30)][sub];
							}
							dp[at][realAt][sub] /= Integer.bitCount(t);
						}
					}
				}

			}
			for(int i = 0; i < n;i++)
			{
				if(i == 0) System.out.format("%.2f",dp[i][0][(1<<n)-1]*100);
				else System.out.format(" %.2f",dp[i][0][(1<<n)-1]*100);
			}
			System.out.println();
		}
	}
	static int N;
	private static double s(int at, int sub, int t) {
		return dp[at][n(sub,at,t)][sub &(~(1<<t))];
	}
	private static int n(int sub, int at, int t) {
		for(int i = (at+1)%N;;i = (i+1)%N)
		{
			if(i != t && (sub & (1<<i)) != 0) return i;
		}
	}
	static double[] p;
	static double[][][] dp;
	static int[][] target;
}
