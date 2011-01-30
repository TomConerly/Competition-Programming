package SSU;
import java.util.*;
import java.io.*;
import java.math.BigInteger;

/* Saratov State University Online Judge
 * Problem 275: To xor or not to xor
 * Type: Math
 * Solution: First determine if we can make the first bit set. Do this by setting up a system of linear equations where the variables
 * are 0 or 1 for whether or not you use a number. The answer needs to be [1]. If we can set the first bit we want to. To solve the for
 * the next one set up a similar system but solve for the first bit being whatever you got from the first part, then the second bit being one.
 * Continue doing this into you have the whole number.
 */

public class p275 {
	static int SIZE = 100;
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = new int[N][SIZE];
		B = new BigInteger[N];
		for(int i = 0; i < N;i++){
			BigInteger b = new BigInteger(sc.next());
			B[i] = b;
			String s = b.toString(2);
			while(s.length() < SIZE)
				s = "0"+s;
			for(int j = 0; j < SIZE;j++)
				M[i][j] = s.charAt(j) == '1'?1:0;
		}
		int[] A = new int[SIZE];
		String an = "";
		for(int i = 1; i <= SIZE;i++){
			int[][] T = new int[N][i];
			for(int j = 0; j < N;j++)
				for(int k = 0; k < i;k++)
					T[j][k] = M[j][k];
			int[] AA = new int[i];
			for(int j = 0; j < i-1;j++)
				AA[j] = A[j];
			AA[i-1] = 1;
			A[i-1] = solve(T,AA) ? 1:0;
			an += A[i-1] == 1?"1":"0";
		}		
		System.out.println(new BigInteger(an,2));
	}
	static int N;
	static int[][] M;
	static BigInteger[] B = new BigInteger[N];
	static boolean solve(int[][] T,int[] A){
		int len = T[0].length;
		int top = 0;
		for(int i = 0; i < len;i++){
			int nonzero = -1;
			for(int j = top; j < N;j++){
				if(T[j][i] == 1){
					nonzero = j;
					break;
				}
			}
			if(nonzero == -1)
				continue;
			swap(top++,nonzero,T);
			for(int j = top; j < N;j++){
				if(T[j][i] == 1)
					add(top-1,j,T);
			}			
		}
		top = 0;
		int[] ans = new int[len];
		for(int i = 0; i < len && top < T.length;i++){
			if((ans[i]^A[i]) == 1 && T[top][i] == 0)
				return false;
			if(T[top][i] == 1){				
				if((ans[i]^A[i]) == 1){
					for(int j = 0; j < len;j++)
						ans[j] ^= T[top][j];
				}
				top++;
			}
		}
		for(int i = 0; i < len;i++)
			if(A[i] != ans[i])
				return false;
		return true;
	}
	static void swap(int a, int b, int[][] T){
		if(a == b)
			return;
		int[] temp = T[a];
		T[a] = T[b];
		T[b] = temp;
	}
	static void add(int a, int b, int[][] T){
		for(int i = 0; i < T[0].length;i++)
			T[b][i] ^= T[a][i];
	}
}
