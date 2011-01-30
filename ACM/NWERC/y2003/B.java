package ACM.NWERC.y2003;
import java.util.*;
import static java.lang.Math.*;

/* ACM NWERC 2003
 * Problem B: Vase Collection
 * Type: Brute Force
 * Solution: You can only have 100 vases so the search space is reasonablly small. Given that you satisfy all of the vases for two types try adding
 * another. Special case when the answer is 100 so you don't time out.
 */

public class B
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int zz = 1; zz <= T;zz++)
        {
            int N = sc.nextInt();
            have = new boolean[36][36];
            ans = 0;
            for(int i = 0; i < N;i++)
            {
                have[sc.nextInt()-1][sc.nextInt()-1] = true;
            }

            boolean[] nl = new boolean[36];
            boolean[] nr = new boolean[36];
            for(int i = 0; i < 36;i++)
                for(int j = 0; j < 36;j++)
                    if(have[i][j])
                    {
                        nl[i] = true;
                        nr[j] = true;
                    }
            int cl = 0;
            for(int i = 0; i < 36;i++)
                if(nl[i])
                    cl++;
            int cr = 0;
            for(int i = 0; i < 36;i++)
                if(nr[i])
                    cr++;
            if(cl == 10 && cr == 10 && N==100)
            {
                System.out.println(10);
            }else{
                v = new HashSet<State>();
                recur(new State(0,0));
                System.out.println(ans);
            }
        }
    }
    private static class State
    {
        long l,r;
        State(long a, long b)
        {
            l = a;
            r = b;
        }
        public boolean equals(Object o)
        {
            State s = (State)o;
            return l == s.l && r == s.r;
        }
        public int hashCode()
        {
            int a = (int)l;
            int b = (int)(l>>>32);
            int c = (int)r;
            int d = (int)(r>>>32);
            return a^b^c^d;
        }
    }
    public static void recur(State s)
    {
        if(v.contains(s))
            return;
        v.add(s);
        if(Long.bitCount(s.l) == Long.bitCount(s.r))
        {
            ans = max(ans,Long.bitCount(s.l));
            for(int i = 0; i < 36;i++)
            {
                if(((1L<<i) & s.l) != 0)
                    continue;
                boolean legal = true;
                for(int k = 0; k < 36;k++)
                {
                    if(((1L<<k) & s.r) != 0)
                    {
                        legal &= have[i][k];
                        if(!legal)
                            break;
                    }
                }
                if(legal)
                    recur(new State(s.l|(1L<<i),s.r));
            }
        }else{
            for(int i = 0; i < 36;i++)
            {
                if(((1L<<i) & s.r) != 0)
                    continue;
                boolean legal = true;
                for(int k = 0; k < 36;k++)
                {
                    if(((1L<<k) & s.l) != 0)
                    {
                        legal &= have[k][i];
                        if(!legal)
                            break;
                    }
                }
                if(legal)
                    recur(new State(s.l,s.r|(1L<<i)));
            }
        }
    }
    static HashSet<State> v;
    static int ans;
    static boolean[][] have;
}
