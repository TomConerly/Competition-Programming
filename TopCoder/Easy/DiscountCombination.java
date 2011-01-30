package TopCoder.Easy;
import java.util.*;
import static java.lang.Math.*;

/* TopCoder TCO08 Round 1
 * Easy Problem 250 Points: DiscountCombination
 * Type: Simulation
 * Solution: You can sort each type of discount by how good it is. Then you can do O(n^3)
 * by picking how many of each discount you will use, then keep the best.
 */

public class DiscountCombination {

	public double minimumPrice(String[] discounts, int price) {
		int[] n1= new int[50];
		int[] n2 = new int[50];
		int[] n3 = new int[50];
		Arrays.fill(n1,Integer.MAX_VALUE);
		Arrays.fill(n2,Integer.MAX_VALUE);
		Arrays.fill(n3,Integer.MAX_VALUE);
		int a1=0,a2=0,a3=0;
		for(String s: discounts)
		{
			StringTokenizer st = new StringTokenizer(s);
			int c = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			if(p==1)
			{
				n1[a1++] = c;
			}else if(p == 2)
			{
				n2[a2++] = c;
			}else
				n3[a3++] = c;
		}
		Arrays.sort(n1);
		Arrays.sort(n2);
		Arrays.sort(n3);
		double bestPrice = price;
		for(int i = 0; i <= a1;i++)
			for(int j = 0; j <= a2;j++)
				for(int k = 0; k <= a3;k++)
				{
					int extra = 0;
					for(int m = 0; m < i;m++)
						extra += n1[m];
					for(int m = 0; m < j;m++)
						extra += n2[m];
					for(int m = 0; m < k;m++)
						extra += n3[m];
					double p = price;
					for(int m = 0; m < i;m++)
						p*=.99;
					for(int m = 0; m < j;m++)
						p*=.98;
					for(int m = 0; m < k;m++)
						p*=.97;
					double pr = p+extra;
					bestPrice = min(bestPrice,pr);
					//p(p+" "+extra+" "+pr);
				}
		
		
		return bestPrice;
	}
	
	public void p(Object o){System.out.println(o);}
}
