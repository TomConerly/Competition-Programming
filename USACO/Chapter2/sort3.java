package USACO.Chapter2;
/*
ID: theycal2
LANG: JAVA
TASK: sort3
 */
/* USACO Training
 * The Sorting a Three-Valued Sequence
 * Type: Sorting
 * Solution: Count the number of 1s that are in a 2s spot, 1s in a 3s spot
 * 2s in 1, 2s in 3, 3s in 1, 3s in 2. If there is a 1 in 2 and a 2 in a 1
 * we want to switch them. So first do all of the moves which corrects 2.
 * The number is min(1in2,2in1)+min(1in3,3in1)+min(2in3,3in2). Note that we
 * have to have exactly k misplaced 1s, k misplaced 2s, and k misplaced 3s
 * (try it out, this has to be true). So this is basically solving something like
 * 312 but 3...31...12...2 which takes 2*k moves.
 */

import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class sort3
{
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(new File("sort3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
		int N = sc.nextInt();
		
		int[] board = new int[N];
		
		for(int i = 0; i < N;i++)
		{
			board[i] = sc.nextInt();
			
		}	
		out.println(run(board));
		out.close();
	}
	public static int run(int[] board)
	{
		int N = board.length;
		int[] cor = new int[N];
		int onec = 0;
		int twoc = 0;
		for(int i = 0; i < N;i++)
		{
			if(board[i] == 1) onec++;
			if(board[i] == 2) twoc++;
		}
		for(int i = 0; i < N;i++)
		{
			if(i < onec) cor[i] = 1;
			else if(i < onec+twoc) cor[i] = 2;
			else cor[i] = 3;
		}
		int i12 = 0;
		int i21 = 0;
		int i13 = 0;
		int i31 = 0;
		int i23 = 0;
		int i32 = 0;
		for(int i = 0; i < N;i++)
		{
			if(board[i] == 1&& cor[i] == 2) i12++;
			if(board[i] == 2&& cor[i] == 1) i21++;
			
			if(board[i] == 1&& cor[i] == 3) i13++;
			if(board[i] == 3&& cor[i] == 1) i31++;
			
			if(board[i] == 2&& cor[i] == 3) i23++;
			if(board[i] == 3&& cor[i] == 2) i32++;
		}
		int moves = min(i12,i21)+min(i13,i31)+min(i23,i32)+2*(max(i12,i21)-min(i12,i21));
		
		return moves;
	}
}

