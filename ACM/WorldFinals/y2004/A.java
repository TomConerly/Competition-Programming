package ACM.WorldFinals.y2004;

import java.io.*;
import java.util.*;
import java.awt.*;

/* 2004 World Finals
 * Problem A: Carl the Ant
 * Type: Simulation
 * Difficulty Coding: 4 
 * Algorithmic Difficulty: 2
 * Solution: Simulate the game, be smart about handling
 * collisions.
 */ 

public class A {

	public static void main(String[] args) throws IOException{
		
		Scanner sc = new Scanner(new File("a.in"));
		int tests = sc.nextInt();
		for (int test = 1; test <= tests; test++){
		
			int n = sc.nextInt();
			int m = sc.nextInt();
			d = sc.nextInt();
			int totallen = 0;
			ArrayList<Point> fpoints = new ArrayList<Point>();
			
			for (int i = 0; i < n; i++){
				fpoints.add(new Point(sc.nextInt(), sc.nextInt()));
			}
			
			int[] ant = new int[m];
			Arrays.fill(ant, -1);
			ArrayList<Integer> finished = new ArrayList<Integer>();
			int time = 0;
			ArrayList<Edge> e = new ArrayList<Edge>();
			int[][] board = new int[250][250];
			for (int i = 0; i < 250; i++) Arrays.fill(board[i], -1);
			
			Iterator<Point> pitr = fpoints.iterator();
			Point lastPoint = new Point(0,0);
			finished.add(0);
			
			while (true) {
				if (pitr.hasNext()){
					Point nextPoint = pitr.next();
					totallen += Math.abs(nextPoint.x - lastPoint.x);
					totallen += Math.abs(nextPoint.y - lastPoint.y);
					
					if (nextPoint.x - lastPoint.x == 0 && nextPoint.y > lastPoint.y){
						// going upwards positive
						for (int y = lastPoint.y; y < nextPoint.y; y++){
							e.add(new Edge(new Point(nextPoint.x, y), new Point(nextPoint.x, y+1)));
							ant[0]++;
							board[nextPoint.x+125][y+125] = ant[0];
							simulate(ant, e, board, finished, time);
							time++;
						}
						
						
					}
					else if (nextPoint.x - lastPoint.x == 0 && nextPoint.y < lastPoint.y){
						// going down
						for (int y = lastPoint.y; y > nextPoint.y; y--){
							e.add(new Edge(new Point(nextPoint.x, y), new Point(nextPoint.x, y-1)));
							ant[0]++;
							board[nextPoint.x+125][y+125] = ant[0];
							simulate(ant, e, board, finished, time);
							time++;
						}
					}
					else if (nextPoint.y - lastPoint.y == 0 && nextPoint.x > lastPoint.x) {
						//going right
						for (int x = lastPoint.x; x < nextPoint.x; x++){
							e.add(new Edge(new Point(x, nextPoint.y), new Point(x+1, nextPoint.y)));
							ant[0]++;
							board[x+125][nextPoint.y+125] = ant[0];
							simulate(ant, e, board, finished, time);
							time++;
						}
					}
					else {
						for (int x = lastPoint.x; x > nextPoint.x; x--){
							e.add(new Edge(new Point(x, nextPoint.y), new Point(x-1, nextPoint.y)));
							ant[0]++;
							board[x+125][nextPoint.y+125] = ant[0];
							simulate(ant, e, board, finished, time);
							time++;
						}
					}
					lastPoint = nextPoint;
				}
				else {
					ant[0] = -3;
					simulate(ant, e, board, finished, time);
					time++;
					if (finished.size() == m) break;
					//else System.out.println("Time " + time + ": " + finished);
				} // end if
				if (time > 50) System.exit(0);
			}
			
			System.out.println("Case " + test + ":");
			System.out.println("Carl finished the path at time " + (totallen+1));
			System.out.println("The ants finished in the following order:");
			for (Integer i : finished){
				System.out.print(i + " ");
			}
			System.out.println();
			System.out.println("The last ant finished the path at time " + time);
			System.out.println();
		}
		
	}
	
	public static int d = 0;
	
	private static void simulate(int[] ant, ArrayList<Edge> edges, int[][] board, ArrayList<Integer> finished, int time) {
		
		boolean[] moved = new boolean[ant.length];
		Arrays.fill(moved, false);
		moved[0] = true;
		for (int i = 1; i < ant.length; i++){
			if (moved[i]) continue;
			if (ant[i] == -2 || ant[i] == -3) continue;
			@SuppressWarnings("unused")
			boolean k = false;
			if (ant[i] >= 0) k = move(edges, ant, i, board, moved);
			else if (time >= (d * i) && antOn(0, ant) == -1)
				ant[i] = 0;
			
		}
		
		for (int i = 1; i < ant.length; i++){
			if (ant[i] == -2) {
				ant[i] = -3;
				finished.add(i);
			}
		}
		
		/*
		System.out.println("Time " + time);
		for (int i = 0; i < ant.length; i++){
			if (ant[i] >= 0)
			System.out.println("Ant " + i + " at position " + edges.get(ant[i]).dst.x + "," + edges.get(ant[i]).dst.y);
			else System.out.println("Ant " + i + " finished");
		}
		*/	
	}
	
	private static boolean move(ArrayList<Edge> edges, int[] ant, int cur, int[][] board, boolean[] moved){
		
		if (moved[cur]) return false;
		Point pos = edges.get(ant[cur]).dst;
		int nextpoint = board[pos.x+125][pos.y+125];
		if (nextpoint == -1){
			ant[cur] = -2;
			return true;
		}
		
		int any = antOn(nextpoint, ant);
		if (any == -1 || move(edges, ant, any, board, moved)){
			ant[cur] = nextpoint;
			moved[cur] = true;
			return true;
		}
		else {
			moved[cur] = true;
			return false;
		}
		
	}
	
	private static int antOn(int k, int[] ant){
		for (int i = 0; i < ant.length; i++){
			if (ant[i] == k) return i;
		}
		return -1;
	}
	
	private static class Edge {
		@SuppressWarnings("unused")
		Point src, dst;
		Edge(Point s, Point d){
			src = s; dst = d;
		}
	}
	
}
