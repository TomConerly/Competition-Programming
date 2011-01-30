package TopCoder.Easy;

import static java.lang.Math.*;
/* TopCoder TCHS09 Finals
 * Easy Problem 250 Points: ThePresents
 * Type: Simulation
 */
public class ThePresents {

	int L,W,H;
	int num;
	public double find(int n, int length, int width, int height) {
		num = n;
		L = length;
		W = width;
		H = height;
		double min = 0;
		double max = 1e50;
		int iter = 0;
		while(iter < 1000)
		{
			iter++;
			if(canFit((max+min)/2))
			{
				min = (max+min)/2;
			}else{
				max = (max+min)/2;
			}
		}
		return (max+min)/2;
	}
	boolean canFit(double a)
	{
		int n = (int)(floor(L/a)*floor(W/a)*floor(H/a));
		if(n >= num) return true;
		return false;
	}

}
