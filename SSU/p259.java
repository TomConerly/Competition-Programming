package SSU;
import java.util.*;
import static java.lang.Math.*;

/* Saratov State University Online Judge
 * Problem 259: Printed PR
 * Type: Greedy
 * Solution: Sort by delivery time (largest to smallest) then add everything as early as possible.
 */

public class p259 {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		I = new Item[N];
		for(int i = 0; i < N;i++)
		{
			I[i] = new Item();
			I[i].p = sc.nextInt();
		}
		for(int i = 0; i < N;i++)
			I[i].d = sc.nextInt();
		Arrays.sort(I);
		int ans = 0;
		int extra = 0;
		for(Item i:I)
		{
			extra += i.p;
			ans = max(ans,extra+i.d);			
		}
		System.out.println(ans);
	}
	
	static int N;
	static Item[] I;
	static class Item implements Comparable<Item>
	{
		int p,d;
		public int compareTo(Item i) {
			return -Integer.valueOf(d).compareTo(i.d);
		}
	}
}
