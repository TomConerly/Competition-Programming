package ACM.NWERC.y2002;
import java.util.*;

/* ACM NWERC 2002
 * Problem F: Pearls
 * Type: DP
 * Solution: It is a dp[at] where all pearls of quality higher than at are satisified.
 * We then choose which pearls we will purchase at the price of at.
 */

public class F
{

    static int[] pearls;
    static int[] prices;
    static int[][] cum_pearls;

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int cases = 1; cases <= T; cases++)
        {
            int c = sc.nextInt();
            pearls = new int[c];
            prices = new int[c];
            for (int i = 0; i < c; i++)
            {
                pearls[i] = sc.nextInt();
                prices[i] = sc.nextInt();
            }

            cum_pearls = new int[c][c];
            for (int i = 0; i < c; i++)
            {
                Arrays.fill(cum_pearls[i], 0);
                cum_pearls[i][i] = pearls[i];
                for (int j = i + 1; j < c; j++)
                {
                    cum_pearls[i][j] = cum_pearls[i][j-1] + pearls[j]; 
                }
            }

            int[] dp = new int[c];
            Arrays.fill(dp, -1);

            int ans = solve(dp, 0);
            System.out.println(ans);

        }



    }

    public static int solve(int[] dp, int done)
    {
        if (done == dp.length)
        {
            return 0;
        }

        if (dp[done] != -1) 
        {
            return dp[done];
        }

        if (done == dp.length - 1)
        {
            dp[done] = (pearls[done] + 10) * prices[done];
            return dp[done];
        }

        for (int i = done + 1; i <= dp.length; i++)
        {
            int ans = solve(dp, i);
            int cost = (cum_pearls[done][i-1] + 10) * prices[i-1];

            //System.out.println("done: " + done + " i: " + i + " ans: " + ans + " cost: " + cost);
            if (dp[done] == -1 || ans + cost < dp[done])
            {
                dp[done] = ans + cost;
            }
        }

        return dp[done];
    }


}
