package ACM.WorldFinals.y2003;

import java.util.*;
import java.io.*;
import java.awt.*;

/* 2003 World Finals
 * Problem A: Building Bridges
 * Type: MST
 * Difficulty Coding: 4 
 * Algorithmic Difficulty: 2
 * Solution: Generate all edges then make the MST.
 */ 

public class A {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new File("bridges.in"));
		int cities = 1;
		while (true) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			if (r == 0 && c == 0) break;
			sc.nextLine();
			
			char[][] board = new char[r][c];
			for (int i = 0; i < r; i++){
				String line = sc.nextLine();
				for (int j = 0; j < c; j++){
					board[i][j] = line.charAt(j);
				}
			}
			
			int[][] b = new int[r][c];
			for (int i = 0; i < r; i++){
				Arrays.fill(b[i], -1);
			}
			
			int index = 0;
			
			for (int i = 0; i < r; i++){
				for (int j = 0; j < c; j++){
					if (b[i][j] != -1) continue;
					if (board[i][j] == '.') continue;
					fill(b, board, i, j, index);
					index++;
				}
			}
			

			ArrayList<Point>[] points = new ArrayList[index];
			for (int i = 0; i < points.length; i++) points[i] = new ArrayList<Point>();
			
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++){
					if (b[i][j] == -1) continue;
					points[b[i][j]].add(new Point(i, j));
				}
			}
			
			ArrayList<Edge> edges = new ArrayList<Edge>();
			for (int i = 0; i < index; i++){
				for (int j = i+1; j <index; j++){
					int len = edgeBetween(b, points[i], points[j]);
					//System.out.println(i + " " + j + " " + len);
					if (len != -1) edges.add(new Edge(i, j, len));
				}
			}
			
			int[] u = new int[index];
			for (int i = 0; i < u.length; i++){
				u[i] = i;
			}
			
			Comparator<Edge> comp = new Comparator<Edge> () {
				public int compare(Edge e1, Edge e2){
					return e1.length - e2.length;
				}
			};
			
			Collections.sort(edges, comp);
			
			int total = 0;
			int groups = index;
			int bridges = 0;
			for (int i = 0; i < edges.size(); i++){
				int src = edges.get(i).src;
				int dst = edges.get(i).dst;
				
				if (find(u, src) == find(u, dst)) continue;
				union(u, src, dst);
				total += edges.get(i).length;
				groups--;
				bridges++;
			}
			
			System.out.println("City " + cities);
			if (index == 1) System.out.println("No bridges are needed.");
			else {
				if (bridges > 0) System.out.println(bridges + " " + ((bridges == 1) ? "bridge" : "bridges") + " of total length " + total);
				else System.out.println("No bridges are possible.");
				if (groups != 1) System.out.println(groups + " disconnected groups ");
			}
			System.out.println();
			cities++;
		}
		
	}
	
	public static int find(int[] u, int s){
		if (u[s] == s) return s;
		u[s] = find(u, u[s]);
		return u[s];
	}
	
	public static void union(int[] u, int s, int d){
		u[find(u,s)] = find(u, d);
	}
	
	public static int edgeBetween (int[][] b, ArrayList<Point> p1, ArrayList<Point> p2){
		
		int min = 10000000;
		for (int i = 0; i < p1.size(); i++){
			for (int j = 0; j < p2.size(); j++){
				Point s1 = p1.get(i);
				Point s2 = p2.get(j);
				//System.out.println(s1 + " " + s2);
				if (Math.abs(s1.x - s2.x) <= 1){
					int dist = s1.y - s2.y;
					boolean ok = true;
					if (dist > 0) {
						for (int y = s2.y + 1; y < s1.y; y++){
							if (b[s1.x][y] != -1) ok = false;
							if (b[s2.x][y] != -1) ok = false;
						}
						if (ok && dist < min) min = dist - 1;
					}
					else {
						for (int y = s1.y + 1; y < s2.y; y++){
							if (b[s1.x][y] != -1) ok = false;
							if (b[s2.x][y] != -1) ok = false;
						}
						if (ok && (-dist) < min) min = -dist - 1;
					}
				}
				else if (Math.abs(s1.y - s2.y) <= 1){
					int dist = s1.x - s2.x;
					boolean ok = true;
					if (dist > 0) {
						for (int x = s2.x + 1; x < s1.x; x++){
							if (b[x][s1.y] != -1) ok = false;
							if (b[x][s2.y] != -1) ok = false;
						}
						if (ok && dist < min) min = dist - 1;
					}
					else {
						for (int x = s1.x + 1; x < s2.x; x++){
							if (b[x][s1.y] != -1) ok = false;
							if (b[x][s2.y] != -1) ok = false;
						}
						if (ok && (-dist) < min) min = -dist - 1;
					}
		
				}
			}	
		}
		
		if (min == 10000000) return -1; else return min;
	}
	
	
	
	public static void fill(int[][] b, char[][] board, int i, int j, int index){
		if (i < 0 || i >= b.length || j < 0 || j >= b[0].length || b[i][j] == index) return;
		if (board[i][j] == '.') return;
		b[i][j] = index;
		fill(b, board,i+1, j, index);
		fill(b, board, i-1, j, index);
		fill(b, board, i, j-1, index);
		fill(b, board, i, j+1, index);
		fill(b, board, i+1, j+1, index);
		fill(b, board, i+1, j-1, index);
		fill(b, board, i-1, j+1, index);
		fill(b, board, i-1, j-1, index);
	
		
	}
	
	private static class Edge {
		
		int src, dst, length;
		public Edge(int src, int dst, int len){
			this.src = src;
			this.dst = dst;
			length = len;
		}
		
		
	}
	
	
}
