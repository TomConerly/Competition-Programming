package SSU;
import java.util.*;

/* Saratov State University Online Judge
 * Problem 105: Div 3
 * Type: simple
 * Solution: Simple formula. Notice that the value mod 3 of this number is 1,0,0,1,0,0,1,0,0,...
 */


public class p105 {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();		
		System.out.println(((N)/3)*2+(N%3==2?1:0));
	}
}
