package ACM.NCPC.y2008;
import java.util.*;

/* ACM NCPC 2008
 * Problem A: Aspen Avenue
 * Type: Greedy
 * Solution: Sort the trees, asign them in order. (Coded by Celestine).
 */

public class A
{

    public static void main(String[] args)
    {
    	
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext())
        {
        int N = sc.nextInt();
            int L = sc.nextInt();
            int W = sc.nextInt();

            double total = 0;
            int[] trees = new int[N];
            for (int i = 0; i < N; i++)
            {
                trees[i] = sc.nextInt();
            }
            Arrays.sort(trees);
            double interval = L / (N / 2.0 - 1); 
            for (int i = 0; i < N; i+=2)
            {
                double xpos = interval * (i/2);
                double dist1 = Math.abs(trees[i] - xpos) + dist(trees[i+1]-xpos, W);
                double dist2 = Math.abs(trees[i+1] - xpos) + dist(trees[i]-xpos, W);

                if (dist1 < dist2)
                {
                    total += dist1;
                }
                else
                {
                    total += dist2;
                }

            }

            System.out.println(total);
        }

    }

    public static double dist (double x, double y)
    {
        return Math.sqrt(x * x + y * y);
    }






}
