package ACM.CTUOpen.y2009;
import java.util.*;

/* CTU Open 2009
 * Problem P: Peculiar Primes
 * Type: Brute Force
 * Solution: Generate all possible values sparsely. Basically you are doing n for loops
 * where each for loop multiplies the value by the given prime. If the value is ever larger
 * than the max then you break out of that for loop. Do this using recursion.
 */

public class P
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            int N = sc.nextInt();
            if(N==0)
                break;
            P = new int[N];
            ans = new TreeSet<Long>();
            for(int i = 0; i < N;i++)
                P[i] = sc.nextInt();
            L = sc.nextLong();
            H = sc.nextLong();
            recur(0,1);
            if(ans.size() == 0)
                System.out.println("none");
            else
            {
                boolean first = true;
                for(long i:ans)
                {
                    if(first)
                        System.out.print(i);
                    else
                        System.out.print(","+i);
                    first = false;
                }
                System.out.println();
            }
        }
    }
    static long L,H;
    public static void recur(int at, long p)
    {
        if(L <= p && p <= H)
            ans.add(p);
        if(at == P.length)
            return;
        long t = 1;
        for(int i = 0; ;i++)
        {
            if(p*t > H)
                break;
            recur(at+1,p*t);
            t*= P[at];
        }
    }
    static int[] P;
    static TreeSet<Long> ans;
}
