import java.util.*;
import static java.lang.Math.*;
import java.io.*;

public class A {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		C = new int[N][N];
		for(int i = 0; i < N;i++)
			for(int j = 0; j < N;j++)
				C[i][j] = sc.nextInt();
		K = sc.nextInt();
		L = new int[K];
		for(int i = 0; i < K;i++)
			L[i] = sc.nextInt();
		DP = new boolean[N+1][K+1];
		recur(0, 0);
		System.out.println(ans.size());
		boolean first = true;
		for(int a:ans){
			if(!first)
				System.out.print(" ");
			first = false;
			System.out.print(a+1);
		}
		System.out.println();
	}
	static void recur(int at, int edge){
		if(DP[at][edge])
			return;
		DP[at][edge] = true;;
		if(K == edge){
			ans.add(at);
			return;
		}
		for(int i = 0; i < N;i++){
			if(C[at][i] == 0 || C[at][i] != L[edge])
				continue;
			recur(i, edge+1);
		}
	}
	static TreeSet<Integer> ans = new TreeSet<Integer>();
	static boolean[][] DP;
	static int[] L;
	static int N, K;
	static int[][] C;
}
