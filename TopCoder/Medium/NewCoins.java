package TopCoder.Medium;
/* TopCoder SRM 458
 * Medium Problem 500 Points: NewCoins
 * Type: DP
 * Solution: DP[K] = the minimum cost to create change, given that the value of the coin you are using is K and all of the prices have been decreased to be divisible by K.
 * This is because the optimal way to make change is to make as many of the big coin, then as many of the next biggest coin, etc. 
 * 
 * To compute DP[k] we take the best option out of: using just coin k
 * Using some coin m s.t. m = k*i for some i. If we use coin m we have to pay for the cost to decrease all of the prices to something divisible by m then the cost of
 * solving with m which is just DP[m]
 */
import java.util.*;
import static java.lang.Math.*;

public class NewCoins {
	public void p(Object s){System.out.println(s);}
	public int minCoins(int[] price)
	{
		int[] DP = new int[111111];
		Arrays.fill(DP,10000000);
		for(int i = 100000;i>=1;i--)
		{
			DP[i] = 0;
			for(int k = 0; k < price.length;k++)
				DP[i] += price[k]/i;
			for(int j = 2;j*i <= 100000;j++)
			{
				int res = DP[j*i];
				for(int k = 0; k < price.length;k++)
				{
					res += (price[k]/i)%j;
				}
				DP[i] = min(res,DP[i]);
			}
		}
		return DP[1];
	}	
}
