package ACM.NWERC.y2003;
import java.util.*;

/* ACM NWERC 2003
 * Problem E: Subway Tree System
 * Type: Graph Theory
 * Solution: You can recreate the tree using the data given, then the problem is simply tree ismorphism.
 * Given two roots find all subtrees of each, and find some matching between them such that matched
 * subtrees are equivalent.
 */

public class E
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        for(int zz = 1; zz <= N;zz++)
        {
            String a = sc.next();
            String b = sc.next();
            boolean ans = match(a,b);
            if(ans)
                System.out.println("same");
            else
                System.out.println("different");
        }
    }
    public static boolean match(String a, String b)
    {
        String[] p1 = split(a);
        String[] p2 = split(b);
        if(a.length() != b.length())
             return false;
        if(p1.length != p2.length)
            return false;
        if(p1.length == 1 && p2.length == 1)
        {
            if(a.length() <= 2)
                return a.equals(b);
            return match(a.substring(1,a.length()-1),b.substring(1,b.length()-1));
        }
        boolean[] used = new boolean[p2.length];
        for(int i = 0; i < p1.length;i++)
        {
            boolean found = false;
            for(int j = 0; j < p2.length;j++)
            {
                if(used[j])
                    continue;
                if(match(p1[i],p2[j]))
                {
                    found = true;
                    used[j] = true;
                    break;
                }
            }
            if(!found)
            {
                return false;
            }
        }
        return true;
    }
    public static String[] split(String a)
    {
        ArrayList<String> p = new ArrayList<String>();
        int prev = 0;
        int count = 0;
        for(int i = 0; i < a.length();i++)
        {
            if(a.charAt(i) == '0')
                count++;
            else
                count--;
            if(count == 0)
            {
                p.add(a.substring(prev,i+1));
                prev=i+1;
            }
        }
        String[] pp = new String[p.size()];
        for(int i = 0; i < p.size();i++)
            pp[i] = p.get(i);
        return pp;
    }
}
