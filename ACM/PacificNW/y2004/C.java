package ACM.PacificNW.y2004;
import java.util.*;

/* ACM Pacific Northwest 2004
 * Problem C: Lenny's Lucky Lotto Lists
 * Type: DP
 * Solution: Simple DP
 */

public class C
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int R = sc.nextInt();
        for(int zz = 1; zz <= R;zz++)
        {
            N = sc.nextInt();
            M = sc.nextInt();
            dp = new long[N+1][M+2];
            for(int i = 0; i < dp.length;i++)
                Arrays.fill(dp[i],-1);
            long ans = recur(0,0);
            System.out.println("Case "+zz+": n = "+N+", m = "+M+", # lists = "+ans);
        }
    }
    static int N,M;
    static long[][] dp;
    public static long recur(int at, int last)
    {
        if(at == N)
            return 1;
        if(dp[at][last] != -1)
            return dp[at][last];
        long ans = 0;
        for(int i = Math.max(1,last*2);i<=M;i++)
        {
            ans += recur(at+1,i);
        }
        dp[at][last] = ans;
        return ans;
    }
}
