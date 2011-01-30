package SPOJ;
import java.util.*;
import static java.lang.Math.*;

/* SPOJ Problem 11: FCTRL
 * Type: Math
 * Solution: Find the number of times 2 and 5 appear
 * as prime factors and take the min.
 */

public class FCTRL {
	public static void p(Object o){System.out.println(o);}
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int zz = sc.nextInt();
		for(int z = 0; z < zz;z++)
		{
			int N = sc.nextInt();
			int num2 = 0;
			int pow = 2;
			while(pow <= N)
			{
				num2 += N/pow;
				pow *= 2;
			}
			int num5 = 0;
			pow = 5;
			while(pow <= N)
			{
				num5 += N/pow;
				pow *= 5;
			}
			System.out.println(min(num2,num5));
			
		}
	}
}
