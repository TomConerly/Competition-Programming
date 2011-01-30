package ACM.WorldFinals.y2001;

import java.io.File;
import java.io.IOException;
import java.util.*;

/* 2001 World Finals
 * Problem A: Airport Configuration
 * Type: Simluation
 * Difficulty Coding: 2 
 * Algorithmic Difficulty: 1
 * Solution: Try every method of configuring the airport.
 */ 

public class A {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(new File("airport.in"));
		
		while(true) {
			
			int cities = sc.nextInt();
			if(cities == 0) break;
			ArrayList<Traffic>[] traffics = new ArrayList [cities];
			for (int i = 0; i < cities; i++){
				int city = sc.nextInt() - 1;
				int k = sc.nextInt();
				traffics[city] = new ArrayList<Traffic>();
				for (int j = 0; j < k; j++){
					traffics[city].add(new Traffic(sc.nextInt() - 1, sc.nextInt()));
				}
			
			}
			
			ArrayList<Traffic> configs = new ArrayList<Traffic>();
			while (true) {
				int configno = sc.nextInt();
				if (configno == 0) break;
				int[] arrival = new int[cities];
				int[] departure = new int[cities];
				for (int i = 0; i < cities; i++){
					arrival[sc.nextInt() - 1] = i;
				}
				for (int i = 0; i < cities; i++){
					departure[sc.nextInt() - 1] = i;
				}
				
				int load = evaluate(traffics, cities, arrival, departure);
				configs.add(new Traffic(configno, load));
				
			}
			Comparator<Traffic> tcomp = new Comparator<Traffic>() {
				public int compare(Traffic a, Traffic b){
					if (a.traffic == b.traffic) return a.dest - b.dest;
					return a.traffic - b.traffic;
				}
			};
			Collections.sort(configs, tcomp);
			System.out.println("Configuration  Load");
			for (Traffic t : configs){
				System.out.printf("    " + t.dest + "          " + t.traffic + "\n");
			}
		}
		
		
	}
	
	public static int evaluate(ArrayList<Traffic>[] traffics, int cities, int[] arrival, int[] departure){
		int total = 0;
		for (int i = 0; i < cities; i++){
			int apos = arrival[i];
			for (Traffic t : traffics[i]){
				int bpos = departure[t.dest];
				total += t.traffic * (Math.abs(apos - bpos) + 1);
			}
		}
		
		
		return total;
	}
	
	private static class Traffic {
		int dest, traffic;
		Traffic(int dest, int traffic){
			this.dest = dest;
			this.traffic = traffic;
		}
	}
}