package ACM.CTUOpen.y2009;
import java.util.*;

/* CTU Open 2009
 * Problem I: Intriguing Identifiers
 * Type: Simulation
 * Solution: Follow all of the rules exactly.
 */

public class I
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            String s = sc.next();
            if(s.equals("end"))
                break;
            int ans = ans(s);
            if(ans == -1)
            {
                System.out.println("invalid");
            }else if(ans == 0)
            {
                System.out.println("girl");
            }else if(ans == 1)
            {
                System.out.println("boy");
            }
        }
    }
    static int[] days = {31,28,31,30,31,30,31,31,30,31,30,31};
    public static int ans(String s)
    {
            int count = 0;
            for(int i = 0; i < s.length();i++)
                if(s.charAt(i)=='/')
                    count++;
            if(count != 1)
                return -1;
            if(s.length() != 10 && s.length() != 11)
                return -1;
            String[] p = s.split("\\/");
            if(p[0].length() != 6)
            {
                return -1;
            }
            int y = Integer.valueOf(p[0].substring(0,2));
            int m = Integer.valueOf(p[0].substring(2,4));
            int d = Integer.valueOf(p[0].substring(4,6));
            if(y < 10)
                y+= 2000;
            else
                y+= 1900;
            boolean male;
            if(m >50)
            {
                male = false;
                m -= 50;
            }else{
                male = true;
            }
            if(m < 1 || m > 12)
                return -1;
            if(d > days[m-1] || d < 1)
            {
                if(d != 29 || m != 2 || y%4 != 0)
                {
                    return -1;
                }
            }
            if(y < 1920 || y > 2009)
                return -1;
            if(y<= 1953 && p[1].length() != 3)
                return -1;
            if(y >= 1954 && p[1].length() != 4)
                return -1;
            if(p[1].length() == 4)
            {
                long mm = Long.valueOf(p[0]+p[1]);
                if(mm%11 != 0)
                    return -1;
            }
            return male? 1:0;
    }
}
