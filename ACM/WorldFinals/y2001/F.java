package ACM.WorldFinals.y2001;

import java.util.*;
import java.io.*;

/* 2001 World Finals
 * Problem F: A Major Problem
 * Type: Simulation
 * Difficulty Coding: 2 
 * Algorithmic Difficulty: 1
 * Solution: Learn the sacles then just make a lookup table.
 */ 

public class F {

	public static void main(String[] args) throws IOException{
		
		Scanner sc = new Scanner(new File("major.in"));
		
		String[][] scales = new String[][] {
				{"C","D","E","F","G","A","B"},
				{"G","A","B","C","D","E","F#"},
				{"D","E","F#","G","A","B","C#"},
				{"A","B","C#","D","E","F#","G#"},
				{"E","F#","G#","A","B","C#","D#"},
				{"B","C#","D#","E","F#","G#","A#"},
				{"F#","G#","A#","B","C#","D#","E#"},
				{"C#","D#","E#","F#","G#","A#","B#"},
				{"F","G","A","Bb","C","D","E"},
				{"Bb","C","D","Eb","F","G","A"},
				{"Eb","F","G","Ab","Bb","C","D"},
				{"Ab","Bb","C","Db","Eb","F","G"},
				{"Db","Eb","F","Gb","Ab","Bb","C"},
				{"Gb","Ab","Bb","Cb","Db","Eb","F"},
				{"Cb","Db","Eb","Fb","Gb","Ab","Bb"}
		};
		
		boolean frun = true;
		
		while (true) {
			
			String first = sc.next();
			if (first.equals("*")) break;
			String second = sc.next();
			int valid1 = -1;
			int valid2 = -1;
			for (int i = 0; i < scales.length; i++){
				if (scales[i][0].equals(first)) valid1 = i;
				if (scales[i][0].equals(second)) valid2 = i;
			}
			if (!frun) {
				System.out.println("");
				
			}else frun = false;
			if (valid1 == -1){
				System.out.println("Key of " + first + " is not a valid major key");
				sc.nextLine();
				continue;
			}
			if (valid2 == -1){
				System.out.println("Key of " + second + " is not a valid major key");
				sc.nextLine();
				continue;
			}
			System.out.println("Transposing from " + first + " to " + second);
			while (true) {
				
				String note = sc.next();
				if (note.equals("*")) break;
				
				int pos = -1;
				for (int i = 0; i < scales[valid1].length; i++){
					if (scales[valid1][i].equals(note)) {
						pos = i; break;
					}
				}
				
				if (pos == -1) {
					System.out.println("  " + note + " is not a valid note in the " + first + " major scale");
				}
				else {
					System.out.println("  " + note + " transposes to " + scales[valid2][pos]);
				}
			}
			
			
		}
		
	}
	
}
