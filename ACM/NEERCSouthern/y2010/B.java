package ACM.NEERCSouthern.y2010;

import java.util.*;
import static java.lang.Math.*;

public class B {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[][] B = new int[N][M];
		int ans = 0;
		for(int i = 0; i < N;i++){
			String S = sc.next();
			for(int j = 0; j < M;j++){
				B[i][j] = S.charAt(j)-'0';
				if(B[i][j] > 0){
					ans += 2 + B[i][j]*4;
				}
			}
		}
		for(int i = 0; i < N;i++){
			for(int j = 0; j < M;j++){
				for(int d = 0; d < 2; d++){
					int ni = i+dx[d];
					int nj = j+dy[d];
					if(ni >= N || nj >= M)
						continue;
					ans -= 2*min(B[i][j], B[ni][nj]);
				}
			}
		}
		System.out.println(ans);
	}
	static int[] dx = {1,0};
	static int[] dy = {0,1};
}
