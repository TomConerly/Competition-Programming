package SSU;
import java.util.*;

/* Saratov State University Online Judge
 * Problem 239: Minesweeper
 * Type: DP
 * Solution: DP[n][a][b] = the spots 1... n-1 have been filled succesfully and the previous two are a and b
 */

public class p239 {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		B = new int[N];
		for(int i = 0; i < N;i++)
			B[i] = sc.nextInt();
		
		if(N == 1){
			if(B[0] == 1 || B[0] == 0)
				System.out.println(1);
			else
				System.out.println(0);
			return;
		}
		
		long[][][] DP = new long[N+1][2][2];
		if(B[N-1] == 0)
			DP[N][0][0] = 1;
		if(B[N-1] == 1){
			DP[N][1][0] = 1;
			DP[N][0][1] = 1;
		}
		if(B[N-1] == 2)
			DP[N][1][1] = 1;

		for(int n = N-1; n >= 2;n--)
			for(int a = 0; a < 2;a++)
				for(int b = 0; b < 2;b++)
					for(int c = 0; c < 2;c++)
						if(B[n-1] == a+b+c)
							DP[n][a][b] += DP[n+1][b][c];			
		
		long ans = 0;
		if(B[0] == 0)
			ans += DP[2][0][0];
		if(B[0] == 1){		
			ans += DP[2][1][0];
			ans += DP[2][0][1];
		}
		if(B[0] == 2)
			ans += DP[2][1][1];
		System.out.println(ans);
	}
	static int N;
	static int[] B;
}
