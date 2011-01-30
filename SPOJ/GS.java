package SPOJ;
import java.util.*;

/* SPOJ Problem 5468: Going to school
 * Type: DP
 * Solution: DP[s][t] = expected # of moves to get from s to t. I'll show this for uniform probabilities just cause its easier:
 *  Let e be the vertex s.t. moving from s to e gets you closer to t:
 *  Let m be the number of edges out of s
 *  DP[s][t] = 1 + (1/m)T(e,s)+ + sum e' (e' is an outgoing edge not to e) (1/m)(DP[e'][s]+DP[s][t])
 *  This can be seen as you either get closer to s. Or you move one further, then you compute the cost to move back to s, then its
 *  the same cost from s to t.
 *  Simplifies too:
 *  DP[s][t] = m(1+(1/m)DP[e][t] + sum e' (e' is an outgoing edge not to e) (1/m)DP[e'][s])
 */

public class GS {
	public static void p(Object...o)
	{
		System.out.println(Arrays.deepToString(o));
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int zz = sc.nextInt();
		for(int z = 0; z < zz;z++)
		{
			N = sc.nextInt();
			int st = sc.nextInt()-1;
			int en = sc.nextInt()-1;
			P = new double[N][N];
			adj = new ArrayList[N];
			for(int i = 0; i < N;i++)
				adj[i] = new ArrayList<Integer>();
			
			for(int i = 0; i < N-1;i++)
			{
				int a = sc.nextInt()-1;
				int b = sc.nextInt()-1;
				int c = sc.nextInt();
				P[a][b] = c;
				P[b][a] = c;
				adj[a].add(b);
				adj[b].add(a);
			}
			for(int i = 0; i < N;i++)
			{
				double t = 0;
				for(int j = 0; j < N;j++)
					t += P[i][j];
				for(int j = 0; j < N;j++)
					P[i][j] /= t;
			}
			DP = new double[N][N];
			for(double[] d:DP)
				Arrays.fill(d,-1);
			D = new int[N][N];
			for(int[] i:D)
				Arrays.fill(i,-1);
			for(int i = 0; i < N;i++)
				update(i,i,0);
			double ans = recur(st,en);
			System.out.format("%.05f\n",ans);
		}
	}
	private static void update(int at, int up, int dist) {
		if(D[at][up] != -1)
			return;
		D[at][up] = dist;
		for(int e:adj[at])
			update(e,up,dist+1);		
	}
	private static double recur(int at, int to) {
		if(at == to)
			return 0.;
		if(DP[at][to] != -1)
			return DP[at][to];
		
		int towards = 0;
		for(int e:adj[at])
			if(D[e][to] < D[at][to])
				towards = e;
		double ans = (1+P[at][towards]*recur(towards,to));
		for(int e:adj[at])
		{
			if(e == towards)
				continue;
			ans += P[at][e]*recur(e,at);
		}		
		ans *= 1/P[at][towards];
		DP[at][to] = ans;
		return ans;
	}
	static int[][] D;
	static double[][] DP;
	static int N;
	static ArrayList<Integer>[] adj;
	static double[][] P;
}
