package SSU;
import java.util.*;

/* Saratov State University Online Judge
 * Problem 139: Help Needed!
 * Type: Math
 * Solution: Googled for a closed form.
 */

public class p139 {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int[][] B = new int[4][4];
		for(int i = 0; i < 4;i++)
			for(int j = 0; j < 4;j++)
				B[i][j] = sc.nextInt();
		int parity = 0;
		for(int i = 0; i < 16;i++)
		{
			for(int j = i+1; j < 16;j++)
			{
				int a = B[i/4][i%4];
				if(a == 0)
					a = 16;
				int b = B[j/4][j%4];
				if(b == 0)
					b = 16;
				
				if(a > b)
				{
					parity++;
				}
			}
		}
		for(int i = 0; i < 4;i++)
			for(int j = 0; j < 4;j++)
				if(B[i][j] == 0)
					parity += (3-i)+(3-j);
		
		if(parity %2 ==0 )
			System.out.println("YES");
		else
			System.out.println("NO");
	}

}
