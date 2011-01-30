package SSU;
import java.util.*;

/* Saratov State University Online Judge
 * Problem 109: Magic of David Copperfield II
 * Type: Ad hoc
 * Solution: Use the even and odd parity. If the person is on row i column j after an even number of moves
 * their new row and column will sum to a number with the same parity as the old sum. I first remove everything
 * in the lower right that is unreachable after N moves, then I remove the diaganols in order.
 */


public class p109 {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int[][] board = new int[N][N];
		for(int i = 0; i < N;i++)		
			for(int j  = 0; j < N;j++)
				board[i][j] = (i)*N+(j+1);
		
		System.out.print(N);
		for(int i = 0; i < N;i++)		
			for(int j  = 0; j < N;j++)
				if(i+j > N)
					System.out.print(" "+board[i][j]);
		System.out.println();
		int at = N+1;
		if(at%2 == 0)
			at++;
		for(int d = N; d >= 1;d--)
		{			
			System.out.print(at);
			for(int i = 0; i < N;i++)		
				for(int j  = 0; j < N;j++)
					if(i+j == d)
					{
						System.out.print(" "+board[i][j]);
					}
			System.out.println();
			at+=2;
			
		}

	}
}
