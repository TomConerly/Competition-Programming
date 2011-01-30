package SSU;
import java.util.*;

/* Saratov State University Online Judge
 * Problem 125: Shtirlits
 * Type: Pruning
 * Solution: Brute force with pruning.
 */

public class p125 {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		B = new int[N][N];
		for(int i = 0; i < N;i++)
			for(int j = 0; j < N;j++)
				B[i][j] = sc.nextInt();
		recur(0,new int[N][N]);
		if(!done)
			System.out.println("NO SOLUTION");
	}
	static int N;
	static int[][] B;
	static boolean done = false;
	static void recur(int at, int[][] A){
		if(done)
			return;
		if(at == N*N){
			done = true;
			for(int i = 0; i < N;i++){
				for(int j = 0; j < N;j++)
					System.out.print(A[i][j]+" ");
				System.out.println();
			}
			return;
		}
		int x = at/N;
		int y = at%N;
		for(int i = 1; i<= 9;i++){
			A[x][y] = i;
			boolean legal = true;
			if(x > 0 && !legal(A,x-1,y))
				legal = false;
			if(x == N-1 && y > 0 && !legal(A,x,y-1))
				legal = false;
			if(x == N-1 && y == N-1 && !legal(A,x,y))
				legal = false;
			if(legal)
				recur(at+1,A);
		}
	}
	private static boolean legal(int[][] A, int x, int y) {
		int count = 0;
		for(int i = 0; i < 4;i++){
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx < 0 || nx >= N || ny < 0 || ny >= N)
				continue;
			if(A[nx][ny] > A[x][y])
				count++;
		}
		return count == B[x][y];
	}
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
}
