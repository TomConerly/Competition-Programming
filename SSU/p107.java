package SSU;
import java.util.*;

/* Saratov State University Online Judge
 * Problem 107: 987654321 problem
 * Type: math
 * Solution: Only the last 9 digits matter, only 8 nine digit numbers have the correct square (solved by brute force).
 * So the first digit can be any of 9 values, every other digit but the last 9 can be any of 10. The last 9 collectively
 * can take on 8 different values. So the answer is of the form 7200000 with N-10 zeroes. Used fast exponentiation
 * like algorithm to make that many zeroes.
 */

public class p107 {
	static int goal = 987654321;
	static int mod = 1000000000;
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		if(N < 9)
			System.out.println(0);
		else if(N == 9)
			System.out.println(8);
		else{
			System.out.print("72");
			StringBuilder s = new StringBuilder("");
			StringBuilder z = new StringBuilder("0");
			int temp = N-10;
			
			while(temp != 0)
			{
				if(temp % 2 == 1)
				{
					s.append(z);
				}
				temp /=2;
				z.append(z);
			}
				
			System.out.println(s);
		}
		System.out.println("done");
	}
}
