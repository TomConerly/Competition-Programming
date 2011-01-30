package ACM.WorldFinals.y2004;

import java.util.*;
import java.io.*;
import java.awt.geom.*;

/* 2004 World Finals
 * Problem G: Navigation
 * Type: Computational Geometry
 * Difficulty Coding: 3 
 * Algorithmic Difficulty: 3
 * Solution: We have a set of circles and we want to find a common intersection point.
 * Find the intersection between a pair and check the others if they also intersect.
 */ 


public class G {

	public static void main(String[] args) throws IOException{
		
		Scanner sc = new Scanner(new File("G.in"));
		
		int trials = 0;
		while (true) {
			trials++;
			int N = sc.nextInt();
			double t = sc.nextDouble();
			double x = sc.nextDouble();
			double y = sc.nextDouble();
			if (N == 0) break;
			
			double[] sx = new double[N];
			double[] sy = new double[N];
			double[] sdir = new double[N];
			double[] stime = new double[N];
			
			for (int i = 0; i < N; i++){
				sx[i] = sc.nextDouble();
				sy[i] = sc.nextDouble();
				sdir[i] = sc.nextDouble();
				stime[i] = sc.nextDouble();
			}
			
			if (N == 1) {
				System.out.println("Trial " + trials + ": Inconclusive");
				continue;
			}
			
			ArrayList<Point2D.Double> points = new ArrayList<Point2D.Double>();
			double[] realsx = new double[N];
			double[] realsy = new double[N];
			double[] rad = new double[N];
			
			for (int i = 0; i < N; i++){
				realsx[i] = sx[i] + 100 * SIN(sdir[i]) * stime[i];
				realsy[i] = sy[i] + 100 * COS(sdir[i]) * stime[i];
				rad[i] = 350 * (t - stime[i]);
			}
			
			ArrayList<Point2D.Double> fp = findIntersect(realsx[0], realsy[0], rad[0], realsx[1], realsy[1], rad[1]);
			if (fp == null){
				System.out.println("Trial " + trials + ": Inconsistent");
			}
			points.addAll(fp);
			
			for (int i = 2; i < N; i++){
				
				if (points.isEmpty()) break;
				for (int j = 0; j < points.size(); j++){
					if (!liesOnRadius(points.get(j), realsx[i], realsy[i], rad[i])) {
						points.remove(j);
						j--;
					}
				}
			}
			
			
			if (points.size() > 1) {
				if (distance(points.get(0).x, points.get(0).y, points.get(1).x, points.get(1).y) < 0.1)
					points.remove(1);	
			}
			
			if (points.isEmpty()) System.out.println("Trial " + trials + ": Inconsistent");
			else if (points.size() > 1){
				System.out.println("Trial " + trials + ": Inconclusive");
			}
			else {
				
				double angle = findAngle(points.get(0).getX(), points.get(0).getY(), x, y);
				if (distance(points.get(0).getX(), points.get(0).getY(), x, y) < 0.1) System.out.println("Trial " + trials + ": Arrived");
				else System.out.println("Trial " + trials + ": " + Math.round(angle) + " degrees");
				
				
			}
		}
		
		
		
		
	}
	
	public static ArrayList<Point2D.Double> findIntersect(double x1, double y1, double rad1, double x2, double y2, double rad2){
		
		ArrayList<Point2D.Double> alist = new ArrayList<Point2D.Double>();
		double d = distance(x1, y1, x2, y2);
		if ((d - rad1 - rad2) > 0.1) return null;
		double theta = Math.acos((rad1*rad1 + d*d - rad2*rad2)/2/rad1/d);
		double pd = rad1 * Math.cos(theta);
		double px1 = x1 + (pd/d)*(x2-x1);
		double py1 = y1 + (pd/d)*(y2-y1);
		double ps = rad1 * Math.sin(theta);
		alist.add(new Point2D.Double(px1 + (ps/d)*(y1-y2), py1+(ps/d)*(x2-x1)));
		alist.add(new Point2D.Double(px1 - (ps/d)*(y1-y2), py1-(ps/d)*(x2-x1)));
		
		
		return alist;
		
		
	}
	
	public static boolean liesOnRadius(Point2D.Double p, double x, double y, double rad){
		double dist = distance(p.x, p.y, x, y);
		if (Math.abs(dist - rad) < 0.1) return true;
		else return false;
	}
	
	public static double findAngle(double x1, double y1, double x2, double y2){
		double a = Math.atan2((y2 - y1),(x2 - x1));
		if (a < 0) a += Math.PI * 2;
		return Math.toDegrees(a);
	}
	
	public static double distance(double x1, double y1, double x2, double y2){
		double xd = x2 - x1;
		double yd = y2 - y1;
		return Math.sqrt(xd * xd + yd * yd);
	}
	
	public static double COS(double angle){
		double a = Math.toRadians(angle);
		return Math.cos(a);
	}
	
	public static double SIN(double angle){
		return Math.sin(Math.toRadians(angle));
	}
	
	
}
