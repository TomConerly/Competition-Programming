package ACM.WorldFinals.y2006;

import java.util.*;
import java.io.*;

/* 2006 World Finals
 * Problem A: Low Cost Air Travel
 * Type: Dijkstra
 * Difficulty Coding: 3
 * Algorithmic Difficulty: 2
 * Solution: You can make a graph out of the problem then run Dijkstra on it.
 */ 

public class A {

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(new File("a.in"));
		int cases = 0;
		while (true) {
			cases++;
			int NT = sc.nextInt();
			if (NT == 0) break;
			
			ArrayList<Edge> edges = new ArrayList<Edge>();
			for (int i = 0; i < NT; i++) {
				int cost = sc.nextInt();
				int numCities = sc.nextInt();
				int src = sc.nextInt();
				ArrayList<Integer> dst = new ArrayList<Integer>();
				for (int j = 1; j < numCities; j++) {
					dst.add(sc.nextInt());
				}
				edges.add(new Edge(src, dst, cost));
			}
			
			int NI = sc.nextInt();
			for (int trips = 0; trips < NI; trips++) {
			
				int numCities = sc.nextInt();
				int start = sc.nextInt();
				ArrayList<Integer> dests = new ArrayList<Integer>();
				dests.add(start);
				for (int i = 1; i < numCities; i++) {
					dests.add(sc.nextInt());
				}
				
				
				PriorityQueue<State> queue = new PriorityQueue<State>();
				queue.offer(new State(start, 1, 0));
				State s = null;
				while (true) {
					
					s = queue.poll();
					//System.out.println(s.cur + " " + s.cost + " " + s.visited + " " + s.path);
					if (s.visited == numCities) break;
					//try each path
					for (int i = 0; i < NT; i++) {
						Edge e = edges.get(i);
						//System.out.println(i + " " + e.src + " " + s.cur);
						if (e.src != s.cur) continue;
						
						int nc = s.cost + e.cost;
						int vis = s.visited;						
						for (int j = 0; j < e.dst.size(); j++) {
							if (vis >= numCities) break;
							if (e.dst.get(j) == dests.get(vis)) vis++;
							int nd = e.dst.get(j);
							State newState = new State(nd, vis, nc);
							newState.path.addAll(s.path);
							newState.path.add(i);
							queue.offer(newState);
						}
						
						
					}
					
				}
				
				System.out.println("Case " + cases + ", Trip " + (trips+1) + ": Cost = " + s.cost);
				System.out.print("  Tickets used:");
				for (Integer i : s.path) {
					System.out.print(" " + (i+1));
				}
				System.out.println();
			}
		}
		
		
		
	}
	
	private static class State implements Comparable<State>{
		
		int cur;
		int cost;
		int visited;
		ArrayList<Integer> path = new ArrayList<Integer>();
		
		public State (int cur, int visited, int cost) {
			this.cur = cur;
			this.visited = visited;
			this.cost = cost;
		}
		
		
		public int compareTo(State s){
			if (cost == s.cost) return s.visited - visited;
			return cost - s.cost;
		}
		
	}
	
	private static class Edge {
		
		int src;
		ArrayList<Integer> dst;
		int cost;
		
		public Edge (int src, ArrayList<Integer> dst, int cost) {
			this.src = src;
			this.dst = dst;
			this.cost = cost;
		}
		
	}
	
	
	
}
