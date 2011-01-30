package ACM.WorldFinals.y2005;

import java.util.*;
import java.io.*;

/* 2005 World Finals
 * Problem D: cNteSahruPfefrlefe
 * Type: Brute Force
 * Difficulty Coding: 4
 * Algorithmic Difficulty: 3
 * Solution: Look at the number of errors a given position has compared to the perfect shuffle at this
 * step (we can find which step we are at because only one # of shuffles has the correct number of errors).
 * If the number of error is larger than the number of shuffles left we can't solve it, otherwise
 * we try all errors. The search space is large but we can prune when the number of errors is high.
 */ 

public class D {
	
	public static void main(String[] args) throws IOException
	{
		int[][] a = new int[11][52];
		for(int i = 0; i < 52;i++)
			a[0][i] = i;
		for(int j = 1; j < 11;j++)
		{
			a[j] = shuffle(a[j-1]);
		}
		
		Scanner sc = new Scanner(new File("D2.in"));
		
		int N = sc.nextInt();
		for (int cases = 1; cases <= N; cases++) {
			
			int[] deck = new int[52];
			for (int i = 0; i < 52; i++) {
				deck[i] = sc.nextInt();
			}
			
			int shuffles = matching(deck, a);
			Arrays.fill(errors, -1);
			dfs (deck, a, shuffles);
			
			System.out.println("Case " + cases);
			System.out.println("Number of shuffles = " + shuffles);
			for (int i = 1; i <= 10; i++) {
				if (errors[i] == -1) continue;
				
				System.out.println("Error in shuffle " + i + " at location " + errors[i]);
			}
			System.out.println();
			
		}
		
		
		
	}
	
	static int[] errors = new int[11]; 
	
	private static boolean dfs (int[] deck, int[][] a, int shuffles) {
		ArrayList<Integer> errPos = new ArrayList<Integer>();
		int numErrors = findErrors (deck, a[shuffles], errPos);
		if (numErrors == 0) return true;
		if (numErrors > 2 * shuffles) return false;

		int[] rev = reverse(deck);
		
		if (dfs (rev, a, shuffles - 1)) {
			return true;
		}
		
		boolean[] tried = new boolean[52];
		Arrays.fill(tried, false);
		
		for (int i = 0; i < errPos.size(); i++) {
			int pos = errPos.get(i);
			if (pos == 0) {
				tried[0] = true;
				rev[0] = deck[0];
				rev[26] = deck[1];
				
				if (dfs (rev, a, shuffles - 1)) {
					errors[shuffles] = 0;
					return true;
				}
				rev[0] = deck[1];
				rev[26] = deck[0];
				
			}
			else {
				if (!tried[pos - 1]){
					tried[pos-1] = true;
					rev[findRevPos(pos-1)] = deck[pos];
					rev[findRevPos(pos)] = deck[pos - 1];
					if (dfs (rev, a, shuffles - 1)) {
						errors[shuffles] = pos - 1;
						return true;
					}
					rev[findRevPos(pos)] = deck[pos];
					rev[findRevPos(pos - 1)] = deck[pos - 1];
				}
				if (pos != 51) {
					tried[pos] = true;
					rev[findRevPos(pos+1)] = deck[pos];
					rev[findRevPos(pos)] = deck[pos + 1];
					if (dfs (rev, a, shuffles - 1)) {
						errors[shuffles] = pos;
						return true;
					}
					rev[findRevPos(pos)] = deck[pos];
					rev[findRevPos(pos + 1)] = deck[pos + 1];
				}
			}
			
		}
		return false;
		
	}
	
	private static int findRevPos(int n) {
		if ((n & 1) == 1) {
			return (n - 1) / 2;
		}
		else {
			return (n + 52) / 2;
		}
	}
	
	private static int findErrors (int[] a, int[] b, ArrayList<Integer> errPos) {
		int sum = 0;
		for (int i = 0; i < 52; i++) {
			if (a[i] != b[i]) {
				sum++;
				errPos.add(i);
			}
		}
		return sum;
	}
	
	private static int matching (int[] deck, int[][] a) {
		
		for (int i = 1; i < 11; i++) {
			int sum = 0;
			for (int j = 0; j < 52; j++) {
				if (deck[j] == a[i][j]) sum++;
			}
			if (sum >= 32) return i;
		}
		return 0;
	}

	private static int[] shuffle(int[] a) {
		int[] b = new int[52];
		for(int i = 0; i < 52;i++)
		{
			if(i < 26)
			{
				b[2*i+1] = a[i];
			}else{
				b[(2*i)%52] = a[i];
			}
		}
		return b;
	}
	
	private static int[] reverse(int[] a) {
		int[] b = new int[52];
		for (int i = 0; i < 52; i++) {
			if ((i & 1) == 0) {
				b[(i+52)/2] = a[i];
			}
			else {
				b[(i-1)/2] = a[i];
			}
		}
		return b;
	}
	
}
