package ACM.WorldFinals.y2005;

import java.util.*;
import java.io.*;
import java.awt.geom.*;


/* 2005 World Finals
 * Problem B: Simplified GSM Network
 * Type: Computational Geometery/Dijkstra
 * Difficulty Coding: 3 
 * Algorithmic Difficulty: 3
 * Solution: Make the graph and run Dijkstra. To compute the cost of an edge
 * binary search to find the first point on the edge that changes zones, then go
 * a slight bit further and do it again.
 */ 


public class B {

	public static void main(String [] args) throws IOException {
		
		Scanner sc = new Scanner(new File("B.in"));
		
		int cases = 0;
		while (true) {
			int B = sc.nextInt();
			int C = sc.nextInt();
			int R = sc.nextInt();
			int Q = sc.nextInt();
			if (B + C + R + Q == 0) break;
			cases++;
			
			Point2D.Double[] BTS = new Point2D.Double[B];
			Point2D.Double[] cities = new Point2D.Double[C];
			
			int [][] edges = new int[C][C];
			for (int i = 0; i < C; i++) Arrays.fill(edges[i], -1);
			
			for (int i = 0; i < B; i++) {
				BTS[i] = new Point2D.Double(sc.nextDouble(), sc.nextDouble());
			}
			
			for (int i = 0; i < C; i++) {
				cities[i] = new Point2D.Double(sc.nextDouble(), sc.nextDouble());
			}
			
			for (int i = 0; i < R; i++) {
				int src = sc.nextInt() - 1;
				int dst = sc.nextInt() - 1;
				edges[src][dst] = edgesBetween (BTS, cities, src, dst);
				edges[dst][src] = edges[src][dst];
			}
			/*
			for (int i = 0; i < C; i++) {
				for (int j = 0; j < C; j++) {
					System.out.print(edges[i][j] + " ");
				}
				System.out.println();
			}
			*/
			System.out.println("Case " + cases + ":");
			for (int i = 0; i < Q; i++) {
				int src = sc.nextInt() - 1;
				int dst = sc.nextInt() - 1;
				int ans = dijk (edges, src, dst);
				if (ans == -1) System.out.println("Impossible");
				else System.out.println(ans);
				
			}
			
		}
	}
	
	public static double distanceSq (double x1, double y1, double x2, double y2) {
		
		double xd = x1 - x2;
		double yd = y1 - y2;
		return xd*xd + yd*yd;
	
	}
	
	public static int closest (Point2D.Double[] BTS, Point2D.Double p) {
		
		double[] dist = new double[BTS.length];
		int min = 0;
		for (int i = 0; i < BTS.length; i++) {
			dist[i] = distanceSq (BTS[i].getX(), BTS[i].getY(), p.getX(), p.getY());
			if (dist[i] < dist[min]) min = i;
		}
		//System.out.println(dist[min] + " " + min);
		 
		return min;
		
	}
	
	public static int edgesBetween (Point2D.Double[] BTS, Point2D.Double[] cities, int src, int dst) {
		
		Point2D.Double p = cities[src];
		Point2D.Double p2 = cities[dst];
		
		double pos = 0;
		double min = 0;
		double max = 1;
		
		int closestBTS = closest (BTS, cities[src]);
		int closestDST = closest (BTS, cities[dst]);
		if (closestBTS == closestDST) return 0;
		
		int sum = 0;
		@SuppressWarnings("unused")
		Point2D.Double de = null;
		while (true) {
			max = 1;
			while (true) {
				if (Math.abs(max - min) < 0.0001) break;
				
				pos = (min + max) / 2;
			//	System.out.println(src + "," + dst + "," + min + " " + max + " " + pos + " " + sum);
					
				Point2D.Double mid = pointBetween(p, p2, pos);
				
				if (closest(BTS, mid) != closestBTS) {
					max = pos;
				}
				else min = pos;
				
			}
			sum++;
			
			closestBTS = closest (BTS, de = pointBetween(p, p2, max));
			//System.out.println(cities[src] + " " + cities[dst]);
			//System.out.println(closestBTS + " " + closestDST + " " + pos + " " + sum + " " + src + " " + dst + " " + max);
			//System.out.println(de);
			if (closestBTS == closestDST) return sum;
			
		}
		
	}
	
	public static Point2D.Double pointBetween (Point2D.Double p, Point2D.Double p2, double pos) {
		
		Point2D.Double n = new Point2D.Double (p.getX() * (1-pos) + p2.getX() * (pos), p.getY() * (1-pos) + p2.getY() * (pos));
		
		return n;
	}
	
	public static int dijk (int[][] edges, int src, int dst) {
		
		int[] cost = new int[edges.length];
		Arrays.fill(cost, Integer.MAX_VALUE);
		boolean[] visited = new boolean[edges.length];
		Arrays.fill(visited, false);
		
		cost[src] = 0;
		
		while (true) {
			int next = findMin(cost, visited);
			if (next == -1) return -1;
			if (cost[next] == Integer.MAX_VALUE) return -1;
			if (next == dst) return cost[dst];
			
			visited[next] = true;
			for (int i = 0; i < edges.length; i++) {
				if (edges[next][i] == -1) continue;
				if (visited[i]) continue;
				if (cost[next] + edges[next][i] < cost[i]) cost[i] = cost[next] + edges[next][i];
			}
			
		}
		
	}
	
	public static int findMin(int[] cost, boolean[] visited) {
		int min = -1;
		for (int i = 0; i < cost.length; i++) {
			if (visited[i]) continue;
			if (min == -1 || cost[i] < cost[min]) min = i;
		}
		return min;
	}
}
