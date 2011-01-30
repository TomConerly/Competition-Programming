package ACM.WorldFinals.y2003;

import java.util.*;
import java.io.*;

/* 2003 World Finals
 * Problem F: Combining Images
 * Type: Trees
 * Difficulty Coding: 3 
 * Algorithmic Difficulty: 2
 * Solution: Do what they ask, but never fully expand the quad tree
 * because it could be really really large.
 */ 

public class F {

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(new File("images.in"));
		int image = 1;
		
		while (true){
			String line1 = sc.nextLine();
			String line2 = sc.nextLine();
			if (line1.equals("0") && line2.equals("0")) break;
			String s1 = truncate(htobin(line1));
			String s2 = truncate(htobin(line2));
			
			String ans = AND(s1, s2);
		
			System.out.println("Image " + image + ":");
			System.out.println(bintoh(ans));
			System.out.println();
			image++;
		}
		
		
		
	}
	
	public static String AND (String s1, String s2){
		
		if (s1.equals(s2)) return s1;
		String c1 = s1.substring(0, 2);
		String c2 = s2.substring(0, 2);
		if (c1.equals("10") || c2.equals("10")) return "10";
		if (c1.equals("11")) return s2;
		if (c2.equals("11")) return s1;
		if (c1.charAt(0) != '0' || c2.charAt(0) != '0') System.out.println("oh no");
		String topleft1 = findNextQuad(s1.substring(1));
		String topleft2 = findNextQuad(s2.substring(1));
		String topright1 = findNextQuad(s1.substring(1 + topleft1.length()));
		String topright2 = findNextQuad(s2.substring(1 + topleft2.length()));
		String botleft1 = findNextQuad(s1.substring(1 + topleft1.length() + topright1.length()));
		String botleft2 = findNextQuad(s2.substring(1 + topleft2.length() + topright2.length()));
		String botright1 = findNextQuad(s1.substring(1 + topleft1.length() + topright1.length() + botleft1.length()));
		String botright2 = findNextQuad(s2.substring(1 + topleft2.length() + topright2.length() + botleft2.length()));
		
		String and1 = AND(topleft1, topleft2);
		String and2 = AND(topright1, topright2);
		String and3 = AND(botleft1, botleft2);
		String and4 = AND(botright1, botright2);
		
		if (and1.equals("10") && and2.equals("10") && and3.equals("10") && and4.equals("10")) return "10";
		else return "0" + and1 + and2 + and3 + and4;
		
	}
	
	public static String findNextQuad(String s){
		if (s.charAt(0) == '1') return s.substring(0, 2);
		
		int len = 1;
		for (int i = 0; i < 4; i++){
			String k = findNextQuad(s.substring(len));
			len += k.length();
		}
		return s.substring(0, len);
	}
	
	static String[] BIN = new String []{"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111",
		"1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"
	};
	
	public static String htobin (String s){
		
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < s.length(); i++){
			char q = s.charAt(i);
			int d = 0;
			if (q >= '0' && q <= '9') d = q - '0';
			else d = q - 'A' + 10;
			sb.append(BIN[d]);
		}
		return sb.toString();
		
	}
	
	public static String bintoh(String s){
		s = "1" + s;
		int diff = 4 - (s.length() % 4);
		for (int i = 0; i < diff; i++) s = "0" + s;
				
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length() / 4; i++){
			String p = s.substring(i*4, i*4 + 4);
			int k = 0;
			if (p.charAt(0) == '1') k += 8;
			if (p.charAt(1) == '1') k += 4;
			if (p.charAt(2) == '1') k += 2;
			if (p.charAt(3) == '1') k ++;
			if (k < 9) sb.append((char) ('0' + k));
			else sb.append((char)('A' + k - 10));
		}
		return sb.toString();
	}
	
	public static String truncate(String s){
		int index = 0;
		while (s.charAt(index) == '0') index++;
		return s.substring(index+1);
	}
	
	
}
