package ACM.WorldFinals.y2003;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import static java.lang.Math.*;


/* 2003 World Finals
 * Problem I: The Solar System
 * Type: Computational Geometry
 * Difficulty Coding: 3 
 * Algorithmic Difficulty: 4
 * Solution: If you can find a formula for the area under the curve
 * of the ellipse you can binary search on it. I believe our formulas
 * are wrong here.
 */ 

public class I {
	static double a1,b1,t1,a2,b2,t;
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File("I.in"));
		for(int CASE = 1;;CASE++)
		{
			a1 = sc.nextInt();
			b1 = sc.nextInt();
			t1 = sc.nextInt();
			a2 = sc.nextInt();
			b2 = sc.nextInt();
			t = sc.nextInt();
			if(a1 == 0 && b1 == 0 && t1 == 0 && a2 == 0 && b2 == 0 && t == 0) break;

			double t2 = sqrt(t1*t1*a2*a2*a2/((double)a1*a1*a1));
			
			int times = (int)floor(t/t2);
			double mod = t-(t2*times);
			double total = a2*b2*Math.PI; 
			double min = 0;
			double max = Math.PI*2;
			System.out.println(mod+" "+t2+" "+total);
			for(int iter = 0; iter < 1000;iter++)
			{
				if(find((min+max)/2)/total > mod/t2)
					max = (min+max)/2;
				else
					min = (min+max)/2;
			}
			
			double r = r((min+max)/2);
			double x = r*cos((min+max)/2);
			double y = r*sin((min+max)/2);
			System.out.println((min+max)/2+" "+x+" "+y+" "+r+" "+total);
			System.out.format("Solar System %d: %.03f %.03f\n",CASE,x,y);
			
		}
	}
	static double find(double d)
	{
		double ans = 0.0;
		if(d > Math.PI){
			d -= PI;
			ans += a2*b2*PI/2;
		}
		double r = r(d);
		double x = r*cos(d);
		double y = r*sin(d);
		
		ans += f(a2)-f(x);
		double v = abs(x+sqrt(a2*a2-b2*b2))*y/2;
		if(x < -sqrt(a2*a2-b2*b2))
			ans -= v;
		else
			ans += v;
		return ans;
	}
	static double f(double d)
	{
		return -b2*a2*((1/2.0*acos(d/a2)-sin(2*acos(d/a2))/4.0));
	}
	private static double r(double d) {
		
		return a2*b2*sqrt(1/(b2*b2*cos(d)*cos(d)+a2*a2*sin(d)*sin(d)));
	}
}
