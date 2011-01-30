package TopCoder.Medium;

/* TopCoder SRM 426
 * Medium Problem 500 Points: CatchTheMice
 * Type: Computational Geometry
 * Solution: My solution is to keep track of the left,bottom,right, and top most points
 * then we only have to look at the times when these change, and the times between when these
 * change. The times between when they change are easy because the width and height of the 
 * bounding box changes linearlly. We can compute all points where the left,bottom,right, and top
 * most points overlap fairly easily. We do have to check if 3 points are all at the bottom
 * to choose the one with most negative velocity in the y direction as the representative point.
 * 
 * You can also a ternary search on the box size needed at any time t. Because the box size is the max
 * of two convex functions (max x dist and max y dist) it is also convex so we can ternary search over it.
 * This solution is much easier to code. More info here: http://forums.topcoder.com/?module=Thread&threadID=628183&start=0
 */
import static java.lang.Math.*;
public class CatchTheMice {

	int[] x;
	int[] y;
	int[] vx;
	int[] vy;
	int N;
	public double largestCage(int[] xp, int[] yp, int[] xv, int[] yv) {
		N = xp.length;
		x = xp;
		y = yp;
		vx = xv;
		vy = yv;

		int l=0,r=0,t=0,b=0;
		for(int i = 0; i < N;i++)
		{
			if(x[l] > x[i]) l = i;
			if(x[l] == x[i] && vx[l] > vx[i]) l = i;
			
			if(x[r] < x[i]) r = i;
			if(x[r] == x[i] && vx[r] < vx[i]) r = i;
			
			if(y[b] > y[i]) b = i;
			if(y[b] == y[i] && vy[b] > vy[i]) b = i;
			
			if(y[t] < y[i]) t = i;
			if(y[t] == y[i] && vy[t] < vy[i]) t = i;
			
		}
		double best = Double.MAX_VALUE;
		double time = 0;
		double prevTime = Double.MAX_VALUE;
		double prevx=Double.MAX_VALUE,prevy= Double.MAX_VALUE;
		//System.out.println(l+" "+r+" "+b+" "+t);
		while(true)
		{
			double xdist = (x[r]+vx[r]*time)-(x[l]+vx[l]*time);
			double ydist = (y[t]+vy[t]*time)-(y[b]+vy[b]*time);
			best = min(best,max(xdist,ydist));
			//System.out.println("time:"+time+" "+max(xdist,ydist));
			if(prevx != Double.MAX_VALUE && prevy != Double.MAX_VALUE)
			{
				if(prevTime != time) 
				{
					double dx = (xdist-prevx)/(time-prevTime);
					double dy = (ydist-prevy)/(time-prevTime);

					//System.out.println(prevTime+" "+prevx+" "+prevy+" "+time+" "+xdist+" "+ydist);
					
					double num = prevy-prevx;
					double dem = dx-dy;
					if(abs(dem) > 1e-9)
					{
						double test = prevTime+num/dem;
						if( prevTime <= test && test <= time)
						{
							best = min(best,max(prevx+dx*(test-prevTime),prevy+dy*(test-prevTime)));
						}
					}
				}
			}
			prevx = xdist;
			prevy = ydist;
			prevTime = time;

			int nl,nr,nt,nb;
			nl = next(time,l,x,vx,false);
			nr = next(time,r,x,vx,true);
			nb = next(time,b,y,vy,false);
			nt = next(time,t,y,vy,true);
			double tl,tr,tt,tb;
			tl = nextT(time,l,x,vx,false);
			tr = nextT(time,r,x,vx,true);
			tb = nextT(time,b,y,vy,false);
			tt = nextT(time,t,y,vy,true);			

			time = min(min(tl,tr),min(tb,tt));

			if(tl == time) l = nl;
			if(tr == time) r = nr;
			if(tb == time) b = nb;
			if(tt == time) t = nt;


			if(time == Double.MAX_VALUE) break;			
		}

		return best;
	}
	double score(double time)
	{
		double mx = 0;
		double my = 0;
		for(int i = 0; i < N;i++)
		{
			for(int j = 0; j < N;j++)
			{
				double xdist = abs((x[i]+vx[i]*time)-(x[j]+vx[j]*time));
				double ydist = abs((y[i]+vy[i]*time)-(y[j]+vy[j]*time));
				mx = max(mx,xdist);
				my = max(my,ydist);
			}
		}
		return max(mx,my);
	}
	int next(double time, int cur,int[] p,int[] v,boolean up)
	{
		int next = -1;
		double ti = Double.MAX_VALUE;
		for(int i = 0; i < N;i++)
		{
			if(i==cur) continue;

			double num = (p[cur]-p[i]);
			double dem = (v[i]-v[cur]);
			if(abs(dem) < 1e-9) continue;

			double t = num/dem;
			if(t <= time) continue;

			if(t < ti)
			{
				ti = t;
				next = i;
			}else if(t == ti && ((up && next != -1 &&  v[next] < v[i]) ||(!up && next != -1 &&  v[next] > v[i])))
			{
				ti = t;
				next = i;
			}
		}
		return next;
	}
	double nextT(double time, int cur,int[] p,int[] v,boolean up)
	{
		int next = -1;
		double ti = Double.MAX_VALUE;
		for(int i = 0; i < N;i++)
		{
			if(i==cur) continue;

			double num = (p[cur]-p[i]);
			double dem = (v[i]-v[cur]);
			if(abs(dem) < 1e-9) continue;

			double t = num/dem;
			if(t <= time) continue;

			if(t < ti)
			{
				ti = t;
				next = i;
			}else if(t == ti && ((up && next != -1 &&  v[next] < v[i]) ||(!up && next != -1 &&  v[next] > v[i])))
			{
				ti = t;
				next = i;
			}
		}
		return ti;
	}
}
