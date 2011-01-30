package ACM.WorldFinals.y2007;

import java.io.*;
import java.util.*;
import static java.lang.Math.*;

/* 2007 World Finals
 * Problem D: Jacquard Circuits
 * Type: Math
 * Difficulty Coding: 3
 * Algorithmic Difficulty: 3
 * Solution: Use Pick's formula to compute it for the base shape,
 * then for every other shape we can compute it on O(1) because
 * the variables in Pick's formula vary directly with the scale.
 */ 

public class D {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File("D.in"));
		for(int z = 1;; z++)
		{
			int N,M;
			N = sc.nextInt();
			M = sc.nextInt();
			if(N==0 && M == 0) break;
			int[][] points = new int[N][2];
			int g = 0;
			for(int i = 0; i < N;i++)
			{
				points[i][0] = sc.nextInt();
				points[i][1] = sc.nextInt();
			}
			for(int i = 0; i < N;i++)
			{
				g = gcd(g,points[i][0]-points[(i+1)%N][0]);
				g = gcd(g,points[i][1]-points[(i+1)%N][1]);
			}
			int dx = (points[0][0]+g)%g;
			int dy = (points[0][1]+g)%g;
			
			for(int i = 0; i < N;i++)
			{
				points[i][0] = (points[i][0]-dx)/g;
				points[i][1] = (points[i][1]-dy)/g;
			}
			int sum = 0;
			for(int i = 0; i < N;i++)
				sum += points[i][0]*points[(i+1)%N][1]-points[i][1]*points[(i+1)%N][0];
			//System.out.println("S:"+sum);
			double area = abs(sum)/2.0;
			
			int edge = 0;
			for(int i = 0; i < N;i++)
			{
				int gg = gcd(points[i][0]-points[(i+1)%N][0],points[i][1]-points[(i+1)%N][1]);
				edge += gg;
			}
			
			long ans = (int)(area+1-edge/2);
			for(int i = 2;i<=M;i++)
			{
				double ai = area*i*i;
			
				double ei = edge*i;
				ans += (int)(ai+1-ei/2);
			}
			System.out.println("Case "+z+": "+ans);
		}
	}

	private static int gcd(int x, int y) {
		x = abs(x);
		y = abs(y);
		if(y==0) return x;
		else return gcd(y,x%y);
	}
}
