package ACM.PacificNW.y2008;
import java.util.*;

/* ACM Pacific Northwest 2008
 * Problem K: Ancient Calculator
 * Type: Brute Force
 * Solution: Just try all combinations. (Coded by Celestine).
 */


public class K
{

    static int[] segs = {6, 2, 5, 5, 4, 5, 6, 3, 7, 6};

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer,
        ArrayList<Integer>>();

        for (int i = 0; i <=22; i++)
        {
            map.put(i, new ArrayList<Integer>());
        }

        for (int i = 1; i <=999; i++)
        {
            int s = calcSegs(i);
            map.get(s).add(i);
            map.get(s+1).add(-i);
        }
        
        map.get(calcSegs(0)).add(0);

        while (true)
        {
            int x = sc.nextInt();
            if (x == 0) break;
            int y = sc.nextInt();
            int z = sc.nextInt();

            int count = 0;
            
            ArrayList<Integer> xlist = map.get(x/5);
            ArrayList<Integer> ylist = map.get(y/5);
            HashSet<Integer> zset = new HashSet<Integer>();
            zset.addAll(map.get(z/5));
            for (int i = 0; i < xlist.size(); i++)
            {
                for (int j = 0; j < ylist.size(); j++)
                {
                    int plus = xlist.get(i) + ylist.get(j);
                    int minus = xlist.get(i) - ylist.get(j);
                    int mult = xlist.get(i) * ylist.get(j);
                    
                    if (zset.contains(plus)) count++;
                    if (zset.contains(minus)) count++;
                    if (zset.contains(mult)) count++;
                    if (ylist.get(j) == 0) continue;
                    int div = xlist.get(i) / ylist.get(j);
                    if (zset.contains(div)) count++;
                }
            }

            if (count != 1)
            {
                System.out.println(count + " solutions for " + x + " " + y + " "
                + z);
            }
            else
            {
                System.out.println("1 solution for " + x + " " + y + " " + z);
            }



        }


    }

    public static int calcSegs(int n)
    {
        if (n == 0) return segs[0];
        if (n < 10)
        {
            return segs[n];
        }
        if (n < 100)
        {
            return segs[n % 10] + segs[n/10];
        }
        return segs[n % 10] + segs[ (n/10) % 10] + segs[n / 100];
    }


}
