package ACM.PacificNW.y2004;
import java.util.*;

/* ACM ICPC 2004 Pacific NW Region
 * Problem H: Rate of Return
 * Type: Binary Search
 * Solution: Binary search for the right interest rate.
 */

public class H
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int c = 1;
        while(true)
        {
            int N = sc.nextInt();
            if(N == -1)
                break;
            if(c != 1)
                System.out.println();
            m = new int[N];
            v = new double[N];
            for(int i = 0; i < N;i++)
            {
                m[i] = sc.nextInt();
                v[i] = sc.nextDouble();
            }
            em = sc.nextInt();
            end = sc.nextDouble();

            double low = 0.0;
            double high = 1.0;
            for(int i = 0; i < 100;i++)
            {
                double mid = (low+high)/2.0;
                double v = comp(mid);
                if(v < end)
                {
                    low = mid;
                }else{
                    high = mid;
                }
            }
            long ans = (long)((low+0.000005)*100000);
            low = ans/100000.0; 
            System.out.format("Case %d: %.05f\n",c,low);
            c++;
        }
    }
    public static double comp(double rate)
    {
        double ans = 0.0;
        for(int i = 0; i < m.length;i++)
        {
            ans += v[i]*Math.pow(1+rate,em-m[i]+1);
        }
        return ans;
    }
    static int em;
    static double end;
    static int[] m;
    static double[] v;
}
