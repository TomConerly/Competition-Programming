package ACM.WorldFinals.y2007;

import java.util.*;
import java.io.*;

/* 2007 World Finals
 * Problem A: Consanguine Calculations
 * Type: Simulation
 * Difficulty Coding: 2
 * Algorithmic Difficulty: 1
 * Solution: Just try all.
 */ 


public class A {
	
	public static void main(String[] args)  throws IOException {
		
		Scanner sc = new Scanner(new File("A.in"));
		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		ArrayList<String> A = new ArrayList<String>();
		A.add("AA");
		A.add("AO");
		ArrayList<String> AB = new ArrayList<String>();
		AB.add("AB");
		ArrayList<String> B = new ArrayList<String>();
		B.add("BB");
		B.add("BO");
		ArrayList<String> O = new ArrayList<String>();
		O.add("OO");
		map.put("A" , A);
		map.put("AB", AB);
		map.put("B", B);
		map.put("O", O);
		
		HashMap<String, String> revmap = new HashMap<String, String>();
		revmap.put("AA", "A");
		revmap.put("AO", "A");
		revmap.put("OA", "A");
		revmap.put("OB", "B");
		revmap.put("BO", "B");
		revmap.put("BB", "B");
		revmap.put("OO", "O");
		revmap.put("AB", "AB");
		revmap.put("BA", "AB");
		
		HashMap<Character, ArrayList<String>> map3 = new HashMap<Character, ArrayList<String>>();
		ArrayList<String> A3 = new ArrayList<String>();
		A3.add("A");
		A3.add("AB");
		ArrayList<String> B3 = new ArrayList<String>();
		B3.add("B");
		B3.add("AB");
		ArrayList<String> O3 = new ArrayList<String>();
		O3.add("O");
		O3.add("A");
		O3.add("B");
		map3.put('A', A3);
		map3.put('B', B3);
		map3.put('O', O3);
		
		
		int cases = 0;
		while (true) {
			cases++;
			String parent1 = sc.next();
			String parent2 = sc.next();
			String child = sc.next();
			
			if (parent1.equals("E") && parent2.equals("N") && child.equals("D")) break;
			
			if (child.equals("?")){
				
				String bt1 = parent1.substring(0, parent1.length() - 1);
				String bt2 = parent2.substring(0, parent2.length() - 1);
				boolean pp1 = parent1.charAt(parent1.length() - 1) == '+';
				boolean pp2 = parent2.charAt(parent2.length() - 1) == '+';
				
				
				
				HashSet<String> poss = new HashSet<String>();
				for (String blood1 : map.get(bt1)) {
					for (String blood2 : map.get(bt2)) {
						poss.add(blood1.charAt(0) + "" + blood2.charAt(0));
						poss.add(blood1.charAt(0) + "" + blood2.charAt(1));
						poss.add(blood1.charAt(1) + "" + blood2.charAt(0));
						poss.add(blood1.charAt(1) + "" + blood2.charAt(1));
						
					}
				}
				
				
				System.out.print("Case " + cases + ": " + parent1 + " " + parent2 + " ");
				HashSet<String> ans = new HashSet<String>();
				for (String blood : poss) {
					//System.out.println(blood);
					if (!pp1 & !pp2) {
						ans.add(revmap.get(blood) + "-");
						
					} else {
						ans.add(revmap.get(blood) + "+");
						ans.add(revmap.get(blood) + "-");
					}
					
				}
				System.out.println(format(ans));
				
			} else {
				
				boolean p2known = parent1.equals("?");
				
				if (p2known) {
					String bt2 = parent2.substring(0, parent2.length() - 1);
					String btc = child.substring(0, child.length() - 1);
					boolean pp2 = parent2.charAt(parent2.length() - 1) == '+';
					boolean ppc = child.charAt(child.length() - 1) == '+';
					
					ArrayList<String> possc = map.get(btc);
					ArrayList<String> poss2 = map.get(bt2);
					
					if (bt2.equals("AB") && btc.equals("O")) {
						System.out.println("Case " + cases + ": IMPOSSIBLE " + parent2 + " " + child);
						continue;
					}
					
					HashSet<String> poss = new HashSet<String>();
					
					for (int i = 0; i < poss2.size(); i++) {
						
						for (int j = 0; j < possc.size(); j++) {
							String al1 = poss2.get(i);
							String al2 = possc.get(j);
							for (int k = 0;  k < 2; k++) {
								for (int l = 0; l < 2; l++) {
									if (al1.charAt(k) == al2.charAt(l)) {
										char q = al2.charAt(1-l);
										poss.addAll(map3.get(q));
									}
								}
							}
							
							
						}
						
					}
					
					System.out.print("Case " + cases + ": ");
					ArrayList<String> ans = new ArrayList<String>();
					for (String blood : poss) {
						
						if (!pp2 & ppc) {
							ans.add(blood + "+");
							
						} else {
							ans.add(blood + "+");
							ans.add(blood + "-");
						}
						
					}
					System.out.println(format(ans) + " " + parent2 + " " + child);
					
					
					
					
				}
				
				else {
					
					String bt2 = parent1.substring(0, parent1.length() - 1);
					String btc = child.substring(0, child.length() - 1);
					boolean pp2 = parent1.charAt(parent1.length() - 1) == '+';
					boolean ppc = child.charAt(child.length() - 1) == '+';
					
					ArrayList<String> possc = map.get(btc);
					ArrayList<String> poss2 = map.get(bt2);
					
					if (bt2.equals("AB") && btc.equals("O")) {
						System.out.println("Case " + cases + ": " +  parent1 + " IMPOSSIBLE " + child);
						continue;
					}
					
					HashSet<String> poss = new HashSet<String>();
					
					for (int i = 0; i < poss2.size(); i++) {
						
						for (int j = 0; j < possc.size(); j++) {
							String al1 = poss2.get(i);
							String al2 = possc.get(j);
							
							for (int k = 0;  k < 2; k++) {
								for (int l = 0; l < 2; l++) {
									if (al1.charAt(k) == al2.charAt(l)) {
										char q = al2.charAt(1-l);
										poss.addAll(map3.get(q));
									}
								}
							}
							
							
						}
						
					}
					System.out.print("Case " + cases + ": " + parent1 + " ");
					ArrayList<String> ans = new ArrayList<String>();
					for (String blood : poss) {
						
						if (!pp2 & ppc) {
							ans.add(blood + "+");
							
						} else {
							ans.add(blood + "+");
							ans.add(blood + "-");
						}
						
					}
					
					System.out.println(format(ans) + " " + child);
					
					
					
				}
				
				
				
			}
			
			
			
			
		}
		
		
		
		
	}
	public static String format(Collection<String> c) {
		String s = "{";
		boolean first = true;
		
		for (String s1 : c) {
			if (first) {
				first = !first;
				s += s1;
			}
			else s += ", " + s1;
			
		}
		
		s += "}";
		return s;
	}
	
	
	
	
	
}
