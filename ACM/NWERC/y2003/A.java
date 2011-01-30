package ACM.NWERC.y2003;
import java.util.*;
import static java.lang.Math.*;

/* ACM NWERC 2003
 * Problem A: Bridging signals
 * Type: DP
 * Solution: You can translate the problem to longest increasing subsequence which can be solved with the standard DP.
 */

public class A
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int zz = 1; zz <= T;zz++)
        {
            P = sc.nextInt();
            M = new int[P];
            for(int i = 0; i < P;i++)
                M[i] = sc.nextInt();

            dp = new int[P+1];
            Arrays.fill(dp,Integer.MAX_VALUE);
            dp[0] = 0;
            int ans = 0;
            for(int i = 0; i < P;i++)
            {
                int j = binarySearch(i);
                if(dp[j+1] == Integer.MAX_VALUE || M[dp[j+1]] > M[i])
                {
                    dp[j+1] = i;
                    ans = max(ans,j+1);
                }
            }
            System.out.println(ans);
        }
    }
    static int binarySearch(int i)
    {
        int low = 0;
        int high = P;
        int best = 0;
        while(low <= high)
        {
            int mid = (low+high)/2;
            if(mid != 0 && (dp[mid] == Integer.MAX_VALUE || M[dp[mid]] >= M[i]))
            {
                high = mid-1;
            }
            else
            {
                best = max(best,mid);
                low = mid+1;
            }
        }
        return best;
    }
    static int P;
    static int[] M,dp;
}
