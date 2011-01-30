package ACM.NWERC.y2003;
import java.util.*;
import static java.lang.Math.*;

/* ACM NWERC 2003
 * Problem F: Prison rearrangement
 * Type: Graph Theory
 * Solution: Put an edge between people who must be seperated. Now seperate into components. You will either
 * leave a component, or switch it. Now use DP to find the closest you can get to the answer.
 */

public class F
{
    @SuppressWarnings("unchecked")
	public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int zz = 1; zz <=T;zz++)
        {
            N = sc.nextInt();
            M = sc.nextInt();
            adj = new ArrayList[N*2];
            for(int i = 0; i < N*2;i++)
                adj[i] = new ArrayList<Integer>();
            for(int i = 0; i < M;i++)
            {
                int a = sc.nextInt()-1;
                int b = sc.nextInt()-1+N;

                adj[a].add(b);
                adj[b].add(a);
            }
            comp = new int[N*2];
            Arrays.fill(comp,-1);
            int next = 0;
            for(int i = 0 ;i < N*2;i++)
            {
                if(comp[i] == -1)
                    dfs(i,next++);
            }
            moves = new int[next][2];
            for(int i = 0; i < N*2;i++)
            {
                if(i < N)
                    moves[comp[i]][0]++;
                else
                    moves[comp[i]][1]++;
            }
           /* for(int i = 0; i < next;i++)
            {
                System.out.println(moves[i][0]+" "+moves[i][1]);
            }
            System.out.println("done");*/
            dp = new int[next][N/2+1][N/2+1];
            for(int i = 0; i < dp.length;i++)
                for(int j = 0; j < dp[i].length;j++)
                    Arrays.fill(dp[i][j],-1);
            int ans = recur(0,0,0);
            System.out.println(ans);
        }
    }
    public static void dfs(int at, int c)
    {
        if(comp[at] != -1)
            return;
        comp[at] = c;
        for(int i:adj[at])
            dfs(i,c);
    }
    static int[][] moves;
    public static int recur(int at, int l, int r)
    {
        if(at == dp.length)
        {
            if(l==r)
                return l;
            else
                return 0;
        }
        if(dp[at][l][r] != -1)
            return dp[at][l][r];
        int ans = 0;
        if(l==r)
            ans = l;
        ans = max(ans,recur(at+1,l,r));
        int nl = l+moves[at][0];
        int nr = r+moves[at][1];
        if(nl <= (N/2) && nr <= (N/2))
        {
            ans = max(ans,recur(at+1,nl,nr));
        }
        dp[at][l][r] = ans;
        return ans;
    }
    static int[][][] dp;
    static int[] comp;
    static int N,M;
    static ArrayList<Integer>[] adj;
}
