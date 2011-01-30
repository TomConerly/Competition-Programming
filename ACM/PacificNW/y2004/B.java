package ACM.PacificNW.y2004;
import java.util.*;

/* ACM Pacific Northwest 2004
 * Problem B: Zipper
 * Type: Brute Force
 * Solution: Just brute force. (Coded by Celestine)
 */

public class B {

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        for (int cases = 1; cases <= N; cases++)
        {

            String s1 = sc.next();
            String s2 = sc.next();
            String s3 = sc.next();
            int[][] dp = new int[s1.length() + 1][s2.length() + 1];
            for (int i = 0; i < dp.length; i++)
            {
                Arrays.fill(dp[i], 0);
            }
            boolean ans = possible(s1, s2, s3, 0, 0, dp);
            if (ans)
            {
                System.out.println("Data set " + cases + ": yes");
            }
            else
            {
                System.out.println("Data set " + cases + ": no");
            }

        }



    }

    static boolean possible (String s1, String s2, String s3, int u1, int u2, int[][]
    dp)
    {
        if (u1 + u2 == s3.length()) return true;
        if (dp[u1][u2] != 0) return dp[u1][u2] == 1;

        char next = s3.charAt(u1 + u2);
        boolean ans1 = false;
        boolean ans2 = false;
        if (u1 < s1.length() && s1.charAt(u1) == next)
        {
            ans1 = possible(s1, s2, s3, u1+1, u2, dp);
            if (ans1)
            {
                dp[u1][u2] = 1;
                return true;
            }
        }

        if (u2 < s2.length() && s2.charAt(u2) == next)
        {
            ans2 = possible(s1, s2, s3, u1, u2+1, dp);
            if (ans2)
            {
                dp[u1][u2] = 1;
                return true;
            }
        }

        dp[u1][u2] = -1;
        return false;
    }


}
