package ACM.WorldFinals.y2006;

import java.util.*;
import java.io.*;


/* 2006 World Finals
 * Problem G: Pilgrimage
 * Type: Brute Force
 * Difficulty Coding: 3
 * Algorithmic Difficulty:3
 * Solution: We know the group size has to be less than the largest
 * pay statement (2000), so try all group sizes and see what works.
 */ 


public class G {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new File("G.in"));
	
		while (true) {
			
			int N = sc.nextInt();
			if (N == 0) break;
			
			Pair[] inst = new Pair[N];
			for (int i = 0; i < N; i++) {
				String word = sc.next();
				int k = sc.nextInt();
				if (word.equals("IN")) inst[i] = new Pair(0, k);
				if (word.equals("OUT")) inst[i] = new Pair(1, k);
				if (word.equals("COLLECT")) inst[i] = new Pair(2, k);
				if (word.equals("PAY")) inst[i] = new Pair(3, k);
				
			}
			
			ArrayList<Pair> relations = new ArrayList<Pair>();
			int curInst = 0;
			int curN = 0;
			int curP = 0;
			boolean first = true;
			int min = 0;
			
			for (curInst = 0; curInst < N; curInst++) {
				Pair p = inst[curInst];
				if (p.a == 2) continue;
				if (p.a == 3 && first) continue;
				
				if (curN < min) min = curN;
				
				if (first) {
					first = false;
					if (p.a == 0) curN = p.b;
					else curN = -p.b;
					continue;
				}
				
				if (p.a == 3) {
					curP += p.b;
				}
				else {
					if (curP != 0) {
						relations.add(new Pair(curP, curN));
						//System.out.println("Rel: " + curP + " " + curN + " " + min);
						curP = 0;	
					}
					
					if (p.a == 0) curN += p.b;
					else curN -= p.b;
					
				}
			}
			
			if (curN < min) min = curN;
			
			if (relations.size() == 0) {
				System.out.println("SIZE >= " + (-min + 1));
				continue;
			}
			
			HashSet<Integer> accepted = new HashSet<Integer>();
			
			fill(accepted, relations.get(0), (-min + 1));
			
			for (int i = 1; i < relations.size(); i++) {
				HashSet<Integer> next = new HashSet<Integer>();
				Pair p = relations.get(i);
				for (Integer num : accepted) {
					if (p.a % (num + p.b) == 0) next.add(num);
				}
				accepted = next;
			}
			
			if (accepted.size() == 0) {
				System.out.println("IMPOSSIBLE");
			}
			else {
				for (Integer num: accepted) {
					System.out.print(num + " ");
				}
				System.out.println();
			}
			
		}
		
		
		
		
	
	}
	
	public static void fill(HashSet<Integer> accepted, Pair p, int min) {
		
		for (int i = 1; i <= p.a; i++) {
			
			if (p.a % i == 0) {
				int n = i - p.b;
				if (n >= min) accepted.add(n);
			}
			
		}
	}
	
	
	private static class Pair {
		int a, b;
		public Pair (int a, int b){
			this.a = a;
			this.b = b;
		}
	}
	
}
