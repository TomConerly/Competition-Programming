package ACM.CTUOpen.y2009;
import java.util.*;
import static java.lang.Math.*;

/* CTU Open 2009
 * Problem S: Suspicous Stocks
 * Type: Greedy
 * Solution: Go backwards, keep track of the highest value we have seen.
 * Try buying now and then selling at that highest value.
 */

public  class S
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            int D = sc.nextInt();
            if(D == 0)
                break;
            long M = sc.nextInt();
            long[] P = new long[D];
            for(int i = 0; i < D;i++)
                P[i] =sc.nextInt();

            long max = 0;
            long ans = 0;
            for(int i = D-1;i>=0;i--)
            {
                long in = M/P[i];
                long left = M%P[i];
                long out = in*max;
                ans = max(ans,out-M+left);
                max = max(max,P[i]);
            }
            System.out.println(ans);
        }
    }
}
