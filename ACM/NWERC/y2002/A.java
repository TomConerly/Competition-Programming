package ACM.NWERC.y2002;
import java.util.*;
import static java.lang.Math.*;

/* ACM NWERC 2002
 * Problem A: Euro Efficiency
 * Type: BFS
 * Solution: Just BFS and assume that you can't go negative, or larger than 100*100.
 */

public class A
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int zz = 1; zz <= T;zz++)
        {
            coins = new int[6];
            for(int i = 0; i < 6;i++)
                coins[i] = sc.nextInt();

            int num = 0;
            int max = 0;
            int[] cost = new int[20000];
            Arrays.fill(cost,-1);
            LinkedList<Integer> q = new LinkedList<Integer>();
            q.add(0);
            cost[0] = 0;
            while(q.size() > 0)
            {
                int at = q.poll();
                for(int c : coins)
                {
                    for(int i = 0; i < 2;i++)
                    {
                        int na = at + (i==0?c:-c);
                        if(na < 0 || na >= cost.length)
                            continue;
                        if(cost[na] == -1)
                        {
                            cost[na] = cost[at]+1;
                            q.add(na);
                        }
                    }
                }
            }
            
            for(int i = 1; i <= 100;i++)
            {
                int n = cost[i];
                num += n;
                max = max(n,max);
            }
            System.out.format("%.02f %d\n",num/100.0,max);
        }
    }
    static int[] coins;
}
