package ACM.NCPC.y2008;
import java.util.*;

/* ACM NCPC 2008
 * Problem D: Dinner
 * Type: Graph Theory
 * Solution: Switch into decision problem by just trying all years. The problem is now given a graph partition it into a clique and an anticlique such that neither one is too large.
 * This can be solved using brute force. Try putting the first vertex into either set, then branch and try the second and so on. This does not time out because the ith vertex
 * can only branch if all of its neighbors preceding it are in the clique, and all of its not neighbors are in the anticlique. So when assigning the ith person there is exactly
 * one case when you branch. Thus the call stack looks like a binary tree with only O(n) nodes of degree 2.
 */

public class D
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextInt())
		{
			N = sc.nextInt();
			C = sc.nextInt();
			adj = new int[N][N];
			for(int i = 0; i < N;i++)
				Arrays.fill(adj[i],2008);
			for(int i = 0; i < C;i++)
			{
				int a = sc.nextInt()-1;
				int b = sc.nextInt()-1;
				int y = sc.nextInt();
				adj[a][b] = y;
			}
			int ans = -1;
			for(int y = 1948; y<= 2008;y++)
			{
				if(pos(y))
				{
					ans = y;
					break;
				}
			}
			if(ans != -1)
				System.out.println(ans);
			else
				System.out.println("Impossible");
		}
	}
	static int N,C;
	static int[][] adj;

	static ArrayList<Integer>[] ee,ne;

	@SuppressWarnings("unchecked")
	static boolean pos(int Y)
	{

		ee = new ArrayList[N];
		ne = new ArrayList[N];
		for(int i = 0; i < N;i++)
		{
			ee[i] = new ArrayList<Integer>();
			ne[i] = new ArrayList<Integer>();
			for(int j = i+1; j < N;j++)
			{
				if(adj[i][j] < Y)
				{
					ee[i].add(j);
				}else{
					ne[i].add(j);
				}
			}
		}
		ans = false;
		recur(0, new int[N],new int[N],new int[N]);
		return ans;
	}
	static boolean ans = false;
	static void recur(int at, int[] ass, int[] L, int[] R)
	{
		if(ans)
			return;
		if(at == N)
		{
			int s = 0;
			int ns = 0;
			for(int i:ass)
				if(i == 0)
					s++;
				else
					ns++;
			if(s <= 2*N/3.0 && ns <= 2*N/3.0)
				ans = true;
			return;
		}
		if(L[at] == 0)
		{
			for(int i:ne[at])
				L[i]++;
			ass[at] = 0;
			recur(at+1,ass,L,R);
			for(int i:ne[at])
				L[i]--;
		}
		if(R[at] == 0)
		{
			for(int i:ee[at])
				R[i]++;
			ass[at] = 1;
			recur(at+1,ass,L,R);
			for(int i:ee[at])
				R[i]--;
		}
	}
}
