package ACM.PacificNW.y2008;
import java.util.*;

/* ACM Pacific Northwest 2008
 * Problem B: "Robot Roll Call"
 * Type: Brute Force
 * Solution: Just brute force. (Coded by Celestine).
 */

public class B
{

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        for (int tests = 1; tests <= t; tests++)
        {
            int n = sc.nextInt();
            sc.nextLine();
            String[] names = new String[n];
            for (int i = 0; i < n; i++)
            {
                names[i] = sc.nextLine();
            }
            int d = sc.nextInt();
            sc.nextLine();
            
            HashSet<String> hs = new HashSet<String>();
            for (int i = 0; i < d; i++)
            {
                String s = sc.nextLine();
                StringTokenizer st = new StringTokenizer(s);
                while (st.hasMoreTokens())
                {
                    String tok = st.nextToken();
                    hs.add(tok);
                }

            }
            
            System.out.println("Test set " + tests + ":");
            for (int i = 0; i < n; i++)
            {
                if (hs.contains(names[i]))
                {
                    System.out.println(names[i] + " is present");
                }
                else
                {
                    System.out.println(names[i] + " is absent");
                }
            }
            System.out.println();

        }



    }




}
