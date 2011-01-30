package ACM.WorldFinals.y2006;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


/* 2006 World Finals
 * Problem D: Bipartite Numbers
 * Type: Math/DP
 * Difficulty Coding: 3
 * Algorithmic Difficulty:4
 * Solution: We know a bipartite number can always be represented as either
 * the subtraction or addition of two monopartite numbers. So we want to find two 
 * monopartite numbers whose addition/subtraction mod N is 0. By the pigeon hole principle
 * it will only take N+1 monopartite numbers before we find a pair.
 * 
 * DP[i][d] = min length of the monopartite number made up of d that is i mod N.
 * Fill this table with length 1 numbers, length 2 numbers, on up until you find a match
 * so that you find the smallest match.
 */ 


public class D {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File("D.in"));
		while(true)
		{
			int N = sc.nextInt();
			if(N==0) break;
			int[][] table = new int[N][10];
			for(int i = 0; i < N;i++)
				Arrays.fill(table[i],-1);
			long[] pmod = new long[10];
			int n = -1,m=-1,s=-1,t=-1;
			for(int i =1;;i++)
			{				
				for(int d = 0; d< 10;d++)
				{
					int mod = (int)(((pmod[d])*10+d)%N);
					pmod[d] = mod;
					
					if(d != 0)
					{
						for(int k = 1; k <= d;k++)
						{
							if(table[mod][k] != -1){
								
								int m1 = i-table[mod][k];
								int s1 = d;
								int n1 = table[mod][k];
								int t1 = d-k;

								if(m == -1 ||((s1 < s) || (s1 == s && ((m < m1 && t > m) || (m == m1 && t1 < t) || (m > m1 && t1 < m)))))
								{
									m = m1;
									s = s1;
									n = n1;
									t = t1;
								}
							}
						}
						for(int k = 1; k+d < 10;k++)
						{
							if(table[N-mod][k] != -1)
							{
								int m1 = i-table[N-mod][k];
								int s1 = d;
								int n1 = table[N-mod][k];
								int t1 = d+k;

								if(m==-1||((s1 < s) || (s1 == s && ((m < m1 && t > m) || (m == m1 && t1 < t) || (m > m1 && t1 < m)))))
								{
									m = m1;
									s = s1;
									n = n1;
									t = t1;
								}
							}
						}
					}
					
					if(table[mod][d] == -1) {
						table[mod][d] = i;
					}
					
				}
				//System.out.println(i);
				if(m != -1) {					
					System.out.println(N+": "+m+" "+s+" "+n+" "+t);
					break;
				}
			}

		}
	}
}
