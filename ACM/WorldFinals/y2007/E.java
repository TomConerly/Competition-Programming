package ACM.WorldFinals.y2007;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.io.*;

/* 2007 World Finals
 * Problem E: Collecting Luggage
 * Type: Dijkstra/Computational Geometry
 * Difficulty Coding: 4
 * Algorithmic Difficulty: 4
 * Solution: Because you move faster than your luggage once you 
 * can catch it you will always be able to. So binary search for the minimal time.
 * To see if we can reach it at a certain time first find out where it is then construct
 * a graph which is made up of veritices on the polygon, and edges are between verticies you 
 * can directly travel between, and use Dijkstra to find the shortest path to get there.
 */ 

public class E {

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(new File("E.in"));
		int cases = 0;
		while (true) {
			cases++;
			int N = sc.nextInt();
			if (N == 0) break;
			Polygon POLY = new Polygon();
			ArrayList<Point2D.Double> poly = new ArrayList<Point2D.Double>();
			for (int i = 0; i < N; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				poly.add(new Point2D.Double(x, y));
				POLY.addPoint(x, y);
			}
			
			Point2D.Double passenger = new Point2D.Double(sc.nextInt(), sc.nextInt());
			
			int lspd = sc.nextInt();
			int pspd = sc.nextInt();
			double vp = pspd/60.0;
			double vl = lspd/60.0;
			
			double totaldist = 0;
			for (int i = 0; i < N - 1; i++) {
				totaldist += poly.get(i).distance(poly.get(i+1));
			}
			totaldist += poly.get(0).distance(poly.get(N-1));
			
			double mindist = findMinDist(poly, passenger);
			double maxtime = (int)Math.ceil((totaldist/2 + mindist) / vp);
			double mintime = 0.01;
			
			double best = maxtime + 1;
			double[][] dist = new double[N+2][N+2];
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					Point2D.Double p1 = poly.get(i);
					Point2D.Double p2 = poly.get(j);
					if (intersectsPoly(poly, p1, p2, POLY)) {
						dist[i][j] = -2;
						dist[j][i] = -2;
					}
					else {
						dist[i][j] = p1.distance(p2);
						dist[j][i] = dist[i][j];
					}
				}
			}
			
		/*	for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(dist[i][j] + " ");
				}
				System.out.println();
			}
*/			
			while (maxtime - mintime > 0.01) {
				double mid = (mintime + maxtime) / 2;
				
				if (test(poly, passenger, vp, vl, mid, dist, POLY)) {
					if (mid < best) best = mid;
					maxtime = mid - 0.01;
				}
				else {
					mintime = mid + 0.01;
				}
				
				
			}
			
			int mins = (int)Math.round(best) / 60;
			int secs = (int)Math.round(best) % 60;
			String seconds = (secs < 10 ? "0" + secs : secs + "");
			System.out.println("Case " + cases + ": Time = " + mins + ":" + seconds);
			
		}
		
		
		
	}
	
	public static boolean intersectsPoly(ArrayList<Point2D.Double> poly, Point2D.Double p1, Point2D.Double p2, Polygon POLY) {
		
		for (int i = 0; i < poly.size(); i++) {
			int next = (i == poly.size() -1 ? 0 : i + 1);
			Point2D.Double p = poly.get(i);
			Point2D.Double q = poly.get(next);
			if (liesOnLine(p, q, p1, p2)) continue;
			if (Line2D.linesIntersect(p.getX(), p.getY(), q.getX(), q.getY(), p1.getX(), p1.getY(), p2.getX(), p2.getY())) return true;
		}
		
		Point2D.Double mid = new Point2D.Double(0.5 * p1.getX() + 0.5 * p2.getX(), 0.5*p1.getY() + 0.5*p2.getY());
		//System.out.println(p1 + " " + p2 + " " + mid + " " + POLY.contains(mid));
		if (liesOnPolygon(poly, mid)) return false;
		if (POLY.contains(mid)) return true;
		
		return false;
	}
	
	public static boolean liesOnLine(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3, Point2D.Double p4) {
		
		if (Line2D.ptSegDist(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY()) < 0.001) return true;
		if (Line2D.ptSegDist(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p4.getX(), p4.getY()) < 0.001) return true;
		return false;
	}
	
	public static boolean liesOnPolygon(ArrayList<Point2D.Double> poly, Point2D.Double mid) {
		for (int i = 0; i < poly.size(); i++) {
			int next = (i == poly.size() - 1? 0 : i+1);
			Point2D.Double p = poly.get(i);
			Point2D.Double q = poly.get(next);
			
			if (Line2D.ptSegDist(p.getX(), p.getY(), q.getX(), q.getY(), mid.getX(), mid.getY()) < 0.001) return true;
			
		}
		return false;
	}
	
	
	public static boolean test (ArrayList<Point2D.Double> poly, Point2D.Double pass, double vp, double vl, double time, double[][] dist, Polygon POLY) {
		
		Point2D.Double lug = luggagePos(poly, vl, (double)time); 
		
		double shortestDist = dijkstra (poly, pass, lug, dist, POLY);
		
		double shortestTime = shortestDist / vp;
		
		//System.out.println("Testing " + time + ", lug is at " + lug + " and shortest dist is " + shortestDist + ", " + shortestTime);
		return shortestTime < time;
		
	}
	
	
	public static double dijkstra (ArrayList<Point2D.Double> poly, Point2D.Double src, Point2D.Double dst, double[][] dist, Polygon POLY) {
		if (!intersectsPoly(poly, src, dst, POLY)) return src.distance(dst);
		int n = poly.size();
		for (int i = 0; i < n; i++) {
			if (intersectsPoly(poly, poly.get(i), src, POLY)) {
				dist[n][i] = -2;
				dist[i][n] = -2;
			}
			else {
				dist[n][i] = poly.get(i).distance(src);
				dist[i][n] = dist[n][i];
			}
			
			if (intersectsPoly(poly, poly.get(i), dst, POLY)) {
				dist[n+1][i] = -2;
				dist[i][n+1] = -2;
			}
			else {
				dist[n+1][i] = poly.get(i).distance(dst);
				dist[i][n+1] = dist[n][i];
			}
		}
		
		dist[n][n+1] = -2;
		dist[n+1][n] = -2;
	/*	for (int i = 0; i < n+2; i++) {
			for (int j =0; j < n+2; j++) {
				System.out.print(dist[i][j] + " ");
			}
			System.out.println();
		}
		*/
		boolean[] visited = new boolean[n+2];
		Arrays.fill(visited, false);
		Entry[] e = new Entry[n+2];
		for (int i = 0; i < e.length; i++) {
			e[i] = new Entry(i, 100000000);
		}
		e[n].dist = 0;
		TreeSet<Entry> tset = new TreeSet<Entry>();
		for (int i = 0; i < e.length; i++) {
			tset.add(e[i]);
		}
		visited[n] = true;
		while (true) {
			Entry ent = tset.first();
			tset.remove(ent);
			if (ent.index == n+1) return ent.dist;
			visited[ent.index] = true;
			for (int i = 0; i < n+2; i++) {
				if (visited[i]) continue;
				if (dist[i][ent.index] < -1) continue;
				double total = ent.dist + dist[i][ent.index];
				if (total < e[i].dist) {
					tset.remove(e[i]);
					e[i].dist = total;
					tset.add(e[i]);
				}
			}
		}
		
	}
	
	private static class Entry implements Comparable<Entry> {
		int index;
		double dist;
		
		public Entry (int i, double d) {
			index = i; dist = d;
		}
		public int compareTo(Entry e) {
			if (dist > e.dist) return 1;
			else if (dist < e.dist) return -1;
			else return 0;
		}
	}
	
	public static Point2D.Double luggagePos(ArrayList<Point2D.Double> poly, double vl, double time) {
		
		int index = 0;
		double elapsed = 0;
		Point2D p1 = null;
		Point2D p2 = null;
		int next = 1;
		
		while (time > 0) {
			
			next = index + 1;
			if (next == poly.size()) next = 0;
			p1 = poly.get(index);
			p2 = poly.get(next);
			double dist = p1.distance(p2);
			elapsed = dist/vl;
			if (elapsed > time) break;
			else {
				time -= elapsed;
				index = next;
			}
			
			
			
		}
		double frac = time/elapsed;
		Point2D.Double ans = new Point2D.Double((1-frac) *p1.getX() + frac * p2.getX(), (1 - frac) * p1.getY() + frac * p2.getY());
		return ans;
	}
	
	
	public static double distance (Point2D p1, Point2D p2){
		return p1.distance(p2);
	}
	
	public static double findMinDist(ArrayList<Point2D.Double> poly, Point2D.Double pass) {
		
		double min = 1000000000;
		for (int i = 0; i < poly.size() - 1; i++) {
			double dist = Line2D.ptSegDist(poly.get(i).getX(), poly.get(i).getY(), poly.get(i+1).getX(), poly.get(i+1).getY(), pass.getX(), pass.getY());
			if (dist < min) min = dist;
		}
		Point2D.Double p1 = poly.get(0);
		Point2D.Double p2 = poly.get(poly.size() - 1);
		min = Math.min(min, Line2D.ptSegDist(p1.getX(), p1.getY(), p2.getX(), p2.getY(), pass.getX(), pass.getY()));
		return min;
	}
	
	
}
