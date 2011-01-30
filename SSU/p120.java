package SSU;
import java.util.*;
import static java.lang.Math.*;

/* Saratov State University Online Judge
 * Problem 120: Archipeligo
 * Type: Computational Geometry
 * Solution: First you want to find the angle that you leave point N1 going along the outside. Make the polygon N1,N1+1,...,N2 you know all of the interior
 * angle except for two. Find those two using the fact that an n sided polygon has a sum of PI(n-2) interior angles. If you take the angle from N1 to N2 and add
 * the theta (the angle you computed) you get the angle you leave.
 * 
 * Now you just need to find the side length. To do that walk around from N1 to N2 keeping track of the distance in terms of the length that you go in the x and y.
 * When you get to N2 you can find length because you know what distance should be.
 * 
 * Once you have the length and the starting angle you can simply walk around the outside.
 */

public class p120 {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		a = sc.nextInt()-1;
		b = sc.nextInt()-1;
		ax = sc.nextDouble();
		ay = sc.nextDouble();
		bx = sc.nextDouble();
		by = sc.nextDouble();

		int k = 1;
		int t = a;
		while(t != b)
		{
			k++;
			t = (t+1)%N;
		}
		
		double alpha = (PI*(N-2))/N;
		double theta = (PI*(k-2) - (k-2)*alpha)/2;
				
		t = a;
		double ang = atan2(by-ay,bx-ax) + theta;
		double dx = 0,dy = 0;
		while(t != b)
		{
			dx += cos(ang);
			dy += sin(ang);			
			ang -= 2*PI/N;	
			t = (t+1)%N;
		}
		
		double dist = sqrt(pow(ax-bx,2) + pow(ay-by,2));
		double len = dist / sqrt(dx*dx+dy*dy);
		
		points = new double[N][2];
		t = a;
		double x = ax,y=ay;
		ang = atan2(by-ay,bx-ax) + theta;
		do
		{
			points[t][0] = x;
			points[t][1] = y;
			x += len*cos(ang);
			y += len*sin(ang);			
			ang -= 2*PI/N;
			t = (t+1)%N;
			
		}while(t != a);
		
		
		for(int i = 0; i < points.length;i++)
		{
			System.out.format("%.06f %.06f\n",points[i][0],points[i][1]);
		}

	}
	static int N,a,b;
	static double ax,ay,bx,by;
	static double[][] points;
}
