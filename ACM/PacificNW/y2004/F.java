package ACM.PacificNW.y2004;
/* ACM ICPC 2004 Pacific NW Region
 * Problem F: Specialized Four-Digit Numbers
 * Type: Brute Force
 * Solution: Brute force.
 */

public class F
{
    public static void main(String[] args)
    {
        for(int i = 1000; i < 10000;i++)
        {
            String s = i+"";
            int a = sum(Integer.toString(i,10));
            int b = sum(Integer.toString(i,12));
            int c = sum(Integer.toString(i,16));
            if(a == b && b == c)
                System.out.println(s);
        }
    }
    public static int sum(String s)
    {
       
        int sum = 0;
        for(int i = 0; i < s.length();i++)
        {
            if(Character.isLetter(s.charAt(i)))
            {
                sum += s.charAt(i)-'a'+10;
            }else
            sum += s.charAt(i)-'0';
        }
        return sum;
    }
}
