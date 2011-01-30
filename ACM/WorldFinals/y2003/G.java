package ACM.WorldFinals.y2003;

import java.io.*;
import java.util.*;

/* 2003 World Finals
 * Problem G: A Linking Loader
 * Type: Simulation
 * Difficulty Coding: 5
 * Algorithmic Difficulty: 2
 * Solution: Really bad to implement, I don't believe that this code actually works.
 */ 
public class G {

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(new File("linker.in"));
		
		int cases = 1;
		while (true) {
			
			ArrayList<String> program = new ArrayList<String>();
			HashMap<String, Mapping> defs = new HashMap<String, Mapping>();
			boolean firstLine = true;
			int start = 0x100;
			while (true) {
				String line = sc.nextLine();
				if (line.equals("$")) {
					if (firstLine) return;
					break;
				}
				
				firstLine = false;
				program.add(line);
				if (line.charAt(0) == 'D') {
					Scanner st = new Scanner(line);
					st.next();
					String symbol = st.next();
					int offset = htoi(st.next());
					if (defs.containsKey(symbol)) {
						defs.get(symbol).multi = true;
					}
					else defs.put(symbol, new Mapping(offset + start));
					
				}
				else if (line.charAt(0) == 'C'){
					Scanner st = new Scanner(line);
					st.next();
					int bytes  = htoi(st.next());
					start += bytes;
				}	
			}
			
			int checksum = 0;
			Iterator <String> itr = program.iterator();
			while (itr.hasNext()) {
			
				HashMap<Integer, String> refs = new HashMap<Integer, String>();
				int index = 0;
				while(true) {
					String line = itr.next();
					if (line.charAt(0) == 'E') {
						Scanner st = new Scanner(line);
						st.next();
						String symbol = st.next();
						refs.put(index++, symbol);
						
					}
					else if (line.charAt(0) == 'C'){
						Scanner st = new Scanner(line);
						st.next();
						int bytes = htoi(st.next());
						String[] b = new String[bytes];
						for (int i = 0; i < bytes; i++){
							b[i] = st.next();
						}
						
						for (int i = 0; i < bytes; i++){
						//	System.out.print (b[i] + " ");
							if (b[i].equals("$")){
								String key = refs.get(htoi(b[i+1]));
								if (key != null) {
									Mapping m = defs.get(key);
									if (m == null) {
										m = new Mapping(0);
										m.present = false;
										defs.put(key, m);
									}
									int value = 0;
									if (m.present){
										value = m.address;
									}
								//	System.out.println(value);
									checksum = rotate(checksum) + ((value >> 8) & 0xff);
									checksum = checksum & 0xffff;
									checksum = rotate(checksum) + (value & 0xff);
									checksum = checksum & 0xffff;
								}
								else {
									checksum = rotate(rotate(checksum));
									checksum = checksum & 0xffff;
								}
								
								
							} else {
								checksum = rotate(checksum) + (htoi(b[i]) & 0xff);
								checksum = checksum & 0xffff;
							}
						//	System.out.println(checksum);
						}
						
						
						
					}
					else if (line.charAt(0) == 'Z') break;
					
					
				}// end 2nd while
				
			
			}
			
			System.out.println("Case " + cases + ": checksum = " + itoh(checksum));
			System.out.println(" SYMBOL   ADDR");
			System.out.println("--------  ----");
			ArrayList<String> list = new ArrayList<String>();
			list.addAll(defs.keySet());
			Collections.sort(list);
			for (int i = 0; i < list.size(); i++){
				String key = list.get(i);
				System.out.print(key);
				for (int j = 0; j < (10-key.length()); j++) System.out.print(" ");
				Mapping m = defs.get(key);
				if (!m.present) System.out.println("????");
				else if (m.multi) System.out.println(itoh(m.address) + " M");
				else System.out.println(itoh(m.address));
			}
			
		}
	}
	
	public static int rotate(int s){
		return ((s << 1) | ((s >> 15) & 1));
		
	}
	
	public static int htoi (String s){
		Scanner sc = new Scanner(s);
		return sc.nextInt(16);
	}
	
	static String[] HEXDIGIT = new String [] {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
	
	public static String itoh (int x){
		
		String ans = "";
		while (x > 0){
			int f = x % 16;
			x /= 16;
			ans = HEXDIGIT[f] + ans;
		}
		return ans;
		
	}
	
	private static class Mapping {
		
		int address;
		boolean multi = false;
		boolean present = true;
		
		public Mapping(int address){
			this.address = address;
		}
		
		
		
	}
	
}
