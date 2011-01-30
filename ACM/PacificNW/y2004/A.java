package ACM.PacificNW.y2004;
import java.util.*;
import java.math.*;

/* ACM Pacific Northwest 2004
 * Problem A: Mersenne Composite Numbers
 * Type: Brute Force
 * Solution: Just brute force, if you needed to you could precompute.
 */

public class A
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        for(int i = 2; i <= k;i++)
        {
            if(!BigInteger.valueOf(i).isProbablePrime(100))
            continue;
           long num = (1L<<i)-1;
            ArrayList<Long> factors = new ArrayList<Long>();
            if(BigInteger.valueOf(num).isProbablePrime(100))
                continue;
            
            for(long j = 3;j <= 1L <<32;j+=2)
            {
                if(j*j > num)
                    break;
                while(num % j == 0)
                {
                    factors.add(j);
                    num /= j;
                    if(num == 1)
                        break;
                }
            }
            if(num != 1)
                factors.add(num);
            for(int j = 0; j < factors.size();j++)
            {
                if(j != 0)
                    System.out.print(" * ");
                System.out.print(factors.get(j));
                
            }
            System.out.print(" = "+((1L<<i)-1) +" = ( 2 ^ "+i+" ) - 1\n");
        }
    }
}
