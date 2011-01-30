package SSU;
import java.util.*;

/* Saratov State University Online Judge
 * Problem 231: Prime Sum
 * Type: Simulation
 * Solution: Just calculate it.
 */

public class p231 {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] F = new int[N+1];
		ArrayList<Integer> P = new ArrayList<Integer>();
		for(int i = 2; i<= N;i++)
		{
			if(F[i] == 0)
			{
				P.add(i);
				for(int j = 2; j *i <= N;j++)
					F[i*j] = 1;
			}
		}
		int count = 0;
		ArrayList<String> out = new ArrayList<String>();
		for(int i = 0; i < P.size();i++)
		{
			if(2+P.get(i) <= N && F[2+P.get(i)] == 0)
			{
				count++;
				out.add(new String(2+" "+P.get(i)));
			}
		}
		System.out.println(count);
		for(String s:out)
			System.out.println(s);
	}
}
