package ACM.WorldFinals.y2001;

import java.io.*;
import java.util.*;
import java.awt.*;

/* 2001 World Finals
 * Problem C: Crossword Puzzle
 * Type: Brute Force
 * Difficulty Coding: 3 
 * Algorithmic Difficulty: 1
 * Solution: The board is small so try putting words in by DFS until we find a match.
 */ 

public class C {

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(new File("crossword.in"));
		int trials = 0;
		while (true){
			if (trials > 0) System.out.println("");
			trials++;
			int totalslots = sc.nextInt();
			if (totalslots == 0) break;
			
			Slot[] slots = new Slot[totalslots];
			for (int i = 0; i < totalslots; i++){
				slots[i] = new Slot(sc.nextInt()-1, sc.nextInt()-1, sc.next().charAt(0));
			}
			
			for (int i = 0; i < totalslots; i++) {
				for (int j = i + 1; j < totalslots; j++){
					if (slots[i].dir == slots[j].dir){
						if (slots[i].dir == 0){
							if (slots[i].r != slots[j].r) continue;
							int conf = 1010 - Math.abs(slots[i].c - slots[j].c);
							slots[i].conflicts += conf;
							slots[j].conflicts += conf;
						}
						else {
							if (slots[i].c != slots[j].c) continue;
							int conf = 1010 - Math.abs(slots[i].r - slots[j].r);
							slots[i].conflicts += conf;
							slots[j].conflicts += conf;
						}
					}
					else {
						if (slots[i].dir == 0) {
							if (slots[i].c > slots[j].c || slots[i].r < slots[j].r) continue;
							int conf = 1020 - (slots[i].r - slots[j].r) - (slots[j].c - slots[i].c);
							slots[i].conflicts += conf;
							slots[j].conflicts += conf;
						}
						else {
							if (slots[i].r > slots[j].r || slots[i].c < slots[j].c) continue;
							int conf = 1020 - (slots[i].c - slots[j].c) - (slots[j].r - slots[i].r);
							slots[i].conflicts += conf;
							slots[j].conflicts += conf;
						}
					}
				}
			}
			Arrays.sort(slots);
			ArrayList<String> words = new ArrayList<String>();
			for (int i = 0; i < totalslots + 1; i++){
				words.add(sc.next());
			}
			char[][] board = new char[10][10];
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++)
					board[i][j] = ' ';
			}
			globalset = new HashSet<String>();
			dfs(slots, 0, words, board);
			if (globalset.size() == 0){
				System.out.println("Trial " + trials + ": Impossible");
			}
			else {
				System.out.print("Trial " + trials + ":");
				for (String w : globalset){
					System.out.print(" " + w);
				}
				System.out.println("");
			}
			
		} // end while
	}
	
	public static HashSet<String> globalset;
	
	@SuppressWarnings("unchecked")
	public static void dfs(Slot[] slots, int depth, ArrayList<String> words, char[][] board){
		
		if (depth == slots.length){
			globalset.add(words.get(0));
			
			return;
		}
		Slot curr = slots[depth];
		for (int i = 0; i < words.size(); i++){
			String word = words.get(i);
			if (match(curr.r, curr.c, board, word, curr.dir)){
				ArrayList<Point> changed = fillBoard(curr.r, curr.c, board, word, curr.dir);
				
				ArrayList<String> newwords = (ArrayList<String>) words.clone();
				newwords.remove(word);
				dfs(slots, depth+1, newwords, board);
				for (Point p: changed){
					board[p.x][p.y] = ' ';
				}
			}
		}
		
	}
	
	public static void printBoard(char[][] board){
		for (int k = 0 ; k < 10; k++){
			for (int j =0 ; j < 10; j++)
				System.out.print(board[k][j]);
			System.out.println();
		}
		System.out.println("-----------");
	}
	
	public static boolean match (int r, int c, char[][] board, String word, int dir){
		if (dir == 0){
			// across
			if (c + word.length() > board.length) return false;
			for (int i = c; i < c + word.length(); i++){
				if (board[r][i] != ' ' && board[r][i] != word.charAt(i-c))
					return false;
			}
			return true;
		}
		else {
			// down
			if (r + word.length() > board.length) return false;
			for (int i = r; i < r + word.length(); i++){
				if (board[i][c] != ' ' && board[i][c] != word.charAt(i-r))
					return false;
			}
			return true;
		}
		
	}
	
	public static ArrayList<Point> fillBoard(int r, int c, char[][] board, String word, int dir){
		ArrayList<Point> changed = new ArrayList<Point>();
		if (dir == 0){
			for (int i = c; i < c + word.length(); i++){
				if (board[r][i] == ' '){
					changed.add(new Point(r, i));
					board[r][i] = word.charAt(i-c);
				}
			}
		}
		else {
			for (int i = r; i < r + word.length(); i++){
				if (board[i][c] == ' '){
					changed.add(new Point(i, c));
					board[i][c] = word.charAt(i-r);
				}
			}
		}
		return changed;
	}
	
	private static class Slot implements Comparable<Slot>{
	
		int r, c, dir;
		int conflicts = 0;
		
		public Slot(int r, int c, char q) {
			this.r = r;
			this.c = c;
			if (q == 'D') dir = 1; else dir = 0;
		}
		
		public int compareTo(Slot s){
			return s.conflicts - conflicts;
		}
	}
}
