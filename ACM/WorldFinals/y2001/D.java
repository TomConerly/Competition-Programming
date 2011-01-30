package ACM.WorldFinals.y2001;

import java.io.File;
import java.io.IOException;

import java.util.*;

/* 2001 World Finals
 * Problem D: Can't Cut Down the Forest for Trees
 * Type: Greedy/Computational Geometry
 * Difficulty Coding: 4 
 * Algorithmic Difficulty: 2
 * Solution: It is always better to attempt to cut down a tree if you can.
 * Go through the list of trees attempting to remove each one, if none are 
 * removed you are done, if you removed one, try removing others again.
 */ 

public class D {


	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new File("forest.in"));

		for(int zz = 1;; zz++)
		{
			int xmin = sc.nextInt();
			int ymin = sc.nextInt();
			int xmax = sc.nextInt();
			int ymax = sc.nextInt();
			int N = sc.nextInt();
			if(xmin == 0 && ymin == 0 && xmax == 0 && ymax == 0 && N == 0) break;

			Tree[] trees = new Tree[N];
			for(int i = 0; i < N;i++)
			{
				int x = sc.nextInt();
				int y = sc.nextInt();
				int d = sc.nextInt();
				int h = sc.nextInt();
				trees[i] = new Tree(x,y,d,h);
			}
			boolean update = true;
			int numCut = 0;
			while(update)
			{
				update = false;
				for(int i = 0; i < N;i++)
				{
					if(trees[i] == null) continue;
					if(canCut(i,trees,xmin,ymin,xmax,ymax,false))
					{
						System.out.println("cut:"+i);
						numCut++;
						trees[i] = null;
						update = true;
					}
				}
			}
			
			System.out.println("Forest "+zz);
			System.out.println(numCut+" trees(s) can be cut");
		}
	}



	private static boolean canCut(int i, Tree[] trees,int xmin,int ymin,int xmax, int ymax,boolean p) {
		double[][] ranges = new double[trees.length+4][2];
		int at = 0;
		for(int j = 0; j < trees.length;j++)
		{
			if(trees[j] == null || i == j) continue;
			ranges[at] = angleFrom(trees[i],trees[j]);
			if(ranges[at] != null)
				at++;
		}
		ranges[at] = intersectWallx(trees[i],xmin);
		if(ranges[at] != null)
			at++;
		ranges[at] = intersectWallx(trees[i],xmax);
		if(ranges[at] != null)
			at++;
		ranges[at] = intersectWally(trees[i],ymin);
		if(ranges[at] != null)
			at++;
		ranges[at] = intersectWally(trees[i],ymax);
		if(ranges[at] != null)
			at++;

		for(int j = 0; j < at;j++)
		{
			for(int k = 0; k < 2;k++)
			{
				while(ranges[j][k] < 0) ranges[j][k] += Math.PI*2;
				while(ranges[j][k] >= Math.PI*2) ranges[j][k] -= Math.PI*2;
			}
		}
		boolean result = !r(0.0,ranges,at,p);
		return result;
	}



	private static boolean r(double i,double[][] ranges,int at,boolean p) {
		for(int j = 0; j < at;j++)
		{
			double d1 = 0.0;
			if(ranges[j][1] > ranges[j][0]) d1 = ranges[j][1]-ranges[j][0];
			else d1 = (Math.PI*2-ranges[j][0])+ranges[j][1];

			double d2 = 0.0;
			if(ranges[j][1] > i) d2 = ranges[j][1]-i;
			else d2 = (Math.PI*2-i)+ranges[j][1];
			if(d1 > d2)
			{
				if(ranges[j][1] == i) continue;
				if(ranges[j][1] < i) return true;
				else return r(ranges[j][1],ranges,at,p);
			}
		}
		return false;
	}



	public static double[] intersectWallx(Tree t, int x){
		double[] ans = new double[2];
		if (t.h < Math.abs(x - t.x)) return null;
		if (t.x > x){
			double d = Math.sqrt(t.r*t.r + t.h*t.h);
			@SuppressWarnings("unused")
			double phi = Math.atan(t.r/(double)t.h);
			double theta = Math.acos((t.x-x)/d);
			ans[0] = Math.PI * 3 / 2 - theta;
			ans[1] = Math.PI * 3 / 2 + theta;
		}
		else {
			double d = Math.sqrt(t.r*t.r + t.h*t.h);
			@SuppressWarnings("unused")
			double phi = Math.atan(t.r/(double)t.h);
			double theta = Math.acos((x-t.x)/d);
			ans[0] = Math.PI / 2 - theta;
			ans[1] = Math.PI / 2 + theta;
		}
		return ans;
	}

	public static double[] intersectWally(Tree t, int y){
		double[] ans = new double[2];
		if (t.h < Math.abs(y-t.y)) return null;
		if (t.y > y){
			double d = Math.sqrt(t.r*t.r + t.h*t.h);
			@SuppressWarnings("unused")
			double phi = Math.atan(t.r/(double)t.h);
			double theta = Math.acos((t.y-y)/d);
			ans[0] = Math.PI - theta;
			ans[1] = Math.PI + theta;
		}
		else {
			double d = Math.sqrt(t.r*t.r + t.h*t.h);
			@SuppressWarnings("unused")
			double phi = Math.atan(t.r/(double)t.h);
			double theta = Math.acos((y-t.y)/d);
			ans[0] = -theta;
			ans[1] = theta;
		}
		return ans;
	}

	public static double[] angleFrom(Tree t1, Tree t2){

		double[] ans = new double[2];
		double d = t1.distTo(t2);
		if (t1.h < d - t2.r) return null;
		double maxh = Math.sqrt(d * d - (t1.r+t2.r)*(t1.r+t2.r));
		double phi = globalAngle(t1, t2);
		if (t1.h - maxh >= 0.00001){
			double theta = t1.angleB(t2);
			ans[0] = phi - theta;
			ans[1] = phi + theta;
		}
		else {
			double theta = t1.angleA(t2);
			ans[0] = phi - theta;
			ans[1] = phi + theta;
		}
		return ans;
	}

	public static double globalAngle(Tree t1, Tree t2){
		if (t1.x == t2.x) {
			if (t1.y > t2.y) return Math.PI;
			else return 0;
		}
		int dx = t2.x-t1.x;
		int dy = t2.y-t1.y;
		double angle = Math.atan(dx/(double)dy);
		if (dy < 0) angle += Math.PI;
		if (angle < 0) angle += 2 * Math.PI;
		return angle;
	}

	private static class Tree {
		int x, y, h;
		double r;
		public Tree(int x, int y, int r, int h){
			this.x = x;
			this.y = y;
			this.r = r/2.0;
			this.h = h;
		}

		public double angleA (Tree t){
			double p = Math.sqrt(h * h + r * r);
			double d = distTo(t);
			double theta = Math.acos((p*p + d*d - t.r*t.r)/(2 * p * d));
			double angle = Math.atan(r/(double)h);
			return theta + angle;
		}

		public double angleB(Tree t){
			double d = distTo(t);
			double d2 = t.r /(double)(r + t.r) * d;
			double theta = Math.asin(t.r/d2);
			return theta;
		}

		public double distTo(Tree t){
			int dx = x - t.x;
			int dy = y - t.y;
			return Math.sqrt(dx * dx + dy * dy);
		}
	}


}
