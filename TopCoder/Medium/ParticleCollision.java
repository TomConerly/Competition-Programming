package TopCoder.Medium;
import java.util.*;
import static java.lang.Math.*;

/* TopCoder SRM 401
 * Med Problem 500 Points: ParticleCollision
 * Type: Computational Geometry
 * Solution: Find when the line collides with the unit circle.
 * When it does check if the z value is a time when the spiral will
 * also be there. Lots of weird cases.
 */

public class ParticleCollision {

	public double[] collision(int vx, int vy, int vz, int x0, int y0, int z0) {
		double a = vx*vx+vy*vy;
		double b = 2*vx*x0+2*vy*y0;
		double c = x0*x0+y0*y0-1;
		if(a == 0 && b == 0)
		{
			if(x0*x0+y0*y0 == 1) {
				if(vz == 0)
				{
					double xc = cos(PI*z0);
					double yc = sin(PI*z0);
					if(Math.abs(xc-x0)< 1e-9 && abs(yc-y0) < 1e-9)
						return new double[]{x0,y0,z0};
					else return new double[]{};
				}
				else
					return new double[]{0,0,0};
			}
			else return new double[]{};
		}else{			
			if(a != 0)
			{		
				double t1 = (-(b)+sqrt(b*b-(4*a*c)))/(2*a);
				double t2 = (-(b)-sqrt(b*b-(4*a*c)))/(2*a);
				if(b*b-(4*a*c) >= 0)
				{
					check(t1,vx,vy,vz,x0,y0,z0);
					if(abs(b*b-(4*a*c)) > 1e-9)
						check(t2,vx,vy,vz,x0,y0,z0);
				}
			}else{
				double t1 = (-c)/b;
				check(t1,vx,vy,vz,x0,y0,z0);
			}
			if(ans.size() == 0) return new double[]{};
			if(ans.size() == 1) return new double[]{ans.get(0).get(0),ans.get(0).get(1),ans.get(0).get(2)};
			if(ans.size() > 1) return new double[]{0,0,0};
		}
		
		return null;
	}
	ArrayList<ArrayList<Double>> ans = new ArrayList<ArrayList<Double>>();
	private void check(double t, int vx, int vy, int vz, int x0, int y0, int z0) 
	{
		double x = vx*t+x0;
		double y = vy*t+y0;
		double z = vz*t+z0;
		
		//System.out.println(t+":"+x+" "+y+" "+z);
		
		double tc = z;
		double xc = cos(PI*tc);
		double yc = sin(PI*tc);
		//System.out.println(xc+" "+yc+" "+tc+"\n");
		if(abs(x-xc) < 1e-9 && abs(y-yc) < 1e-9){
			ans.add(new ArrayList<Double>());
			ans.get(ans.size()-1).add(x);
			ans.get(ans.size()-1).add(y);
			ans.get(ans.size()-1).add(z);
		}
	}

}
