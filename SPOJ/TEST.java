package SPOJ;
import java.util.Scanner;
/* SPOJ Problem 1: Test
 * Type: Easy
 * Solution: Do you have a pulse?
 */

public class TEST {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			int i = sc.nextInt();
			if(i==42) break;
			System.out.println(i);
		}
	}
}
