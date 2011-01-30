package USACO.Chapter4;

/*
ID: theycal2
LANG: JAVA
TASK: fence8
 */
/* USACO Training
 * Fence Rails
 * Type: Pruning
 * Solution: Do an iterative deepening search. Keep track of waste wood
 * and how much wood you need so you can cut off. Don't use two boards of the
 * same length, only use the first one. If there is a board of your exact length
 * taking it is optimal so don't try anything else. Always use the smallest
 * k rails if you are trying to solve it for k, but match the rails
 * largest first.
 */
import java.io.*;
import java.util.Arrays;

import static java.lang.Math.*;

public class fence8 {

	public static void main (String [] args) throws IOException {
		start = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("fence8.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence8.out")));

		N = Integer.valueOf(f.readLine());
		boards = new int[N];
		int boardSum = 0;
		maxBoard = 0;
		for(int i = 0; i < N;i++)
		{
			boards[i] = Integer.valueOf(f.readLine());
			boardSum += boards[i];
			maxBoard = max(maxBoard,boards[i]);
		}
		Arrays.sort(boards);
		for(int i = 0; i < N/2;i++)
		{
			int temp = boards[i];
			boards[i] = boards[N-i-1];
			boards[N-i-1] = temp;
		}
		maxBoard++;
		
		R = Integer.valueOf(f.readLine());
		rails = new int[R];
		for(int i = 0; i < R;i++)
			rails[i] = Integer.valueOf(f.readLine());


		Arrays.sort(rails);
		int[] railS = new int[R];
		for(int i = 0; i < R;i++)
		{
			railS[i] = rails[i] + (i>0 ? railS[i-1]:0);
		}
		
		int ans = 0;
		int railSum = 0;
		for(int i = 1; i <= R;i++)
		{
			railSum = railS[i-1];
			
			maxDepth = i;
			solve = 0;
			maxWaste = boardSum - railSum;
			if(railSum <= boardSum)
				recur(0,boards,0);
			
			if(solve == 0)
			{
				break;
			}
			else
			{
				i = solve;
				ans = i;
			}
			
		}
		out.println(ans);
		out.close();      
		System.out.println("$:"+(System.currentTimeMillis()-start));
		System.exit(0);                             
	}
	static int[] boards,rails;
	static int N,R;
	static int maxDepth;
	static int solve;
	static long start;
	static int maxWaste;
	static int maxBoard;
	
	static void recur(int at, int[] b,int waste)
	{
		if(waste > maxWaste) return;
		
		if(at == rails.length){
			solve = at;
			return;
		}
		if(at >= maxDepth)
		{
			solve = at;
			for(int i = 0; i < b.length;i++)
			{
				if(b[i] >= rails[at])
				{
					b[i] -= rails[at];
					recur(at+1,b,waste);
					b[i] += rails[at];
					
					return;
				}
			}
			return;
		}
		for(int i = 0; i < b.length;i++)
		{
			if(b[i] == rails[maxDepth-at-1])
			{
				b[i] = 0;
				recur(at+1,b,waste);
				b[i] += rails[maxDepth-at-1];
				
				return;
			}
		}

		boolean used[] = new boolean[maxBoard];
		for(int i = 0; i < b.length;i++)
		{
			if(used[b[i]]) continue;
			used[b[i]] = true;
			
			if(b[i] > rails[maxDepth-at-1])
			{
				b[i] -= rails[maxDepth-at-1];
				recur(at+1,b,b[i] < rails[0]?waste+b[i]:waste);
				b[i] += rails[maxDepth-at-1];
				
				if(solve != 0) break;
			}
		}
	}
}
