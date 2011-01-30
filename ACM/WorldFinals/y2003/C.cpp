#include <stdio.h>
#include <math.h>
#include <vector>

/* 2003 World Finals
 * Problem C: Riding the Bus
 * Type: Math
 * Difficulty Coding: 4 
 * Algorithmic Difficulty: 3
 * Solution: Find the closest point then walk around. Not sure
 * how it was coded (ask Alan).
 */ 

using namespace std;

typedef long long lld;

lld pow3[100];
lld pow9[100];

double dist(double x1, double y1, double x2, double y2)
{
	double dx = x1 - x2;
	double dy = y1 - y2;
	return sqrt(dx*dx + dy*dy);
}

lld ABS(lld n){
	return max(n, -n);
}

lld numTotal(int ord)
{
	return pow9[ord]; // -1 + 1
}

lld numSoFar(lld x, lld y, int ord)
{
	int lens[3][3] = {{0,5,6},
					{1,4,7},
					{2,3,8}};
					
	bool flip[3][3] = {{false,true,false},
					{true,false,true},
					{false,true,false}};
	if (ord == 1)
	{
		return lens[x][y];
	}
	
	int xi = x / pow3[ord-1];
	int yi = y / pow3[ord-1];
	
	if (!flip[xi][yi])
		return lens[xi][yi] * numTotal(ord-1) + numSoFar(x % pow3[ord-1], y % pow3[ord-1], ord-1);
	else
		return lens[xi][yi] * numTotal(ord-1) + numSoFar(pow3[ord-1] - x % pow3[ord-1] - 1, y % pow3[ord-1], ord-1);
}

int main()
{
	FILE *infile = fopen("C.in", "r");
	pow3[0] = 1;
	pow9[0] = 1;
	for(int i = 1; i < 100; i++)
	{
		pow3[i] = 3 * pow3[i-1];
		pow9[i] = 9 * pow9[i-1];
	}
	
	for(int casenum = 1;; casenum++)
	{
		int ord;
		double x1, y1, x2, y2;
		fscanf(infile, "%d", &ord);
		if (!ord)
			break;
			
		fscanf(infile, "%lf %lf %lf %lf", &x1, &y1, &x2, &y2);
		
		x1 -= 1e-12;
		x2 -= 1e-12;
		y1 -= 1e-12;
		y2 -= 1e-12;
		
		
		
		double oldx1 = x1;
		double oldy1 = y1;
		double oldx2 = x2;
		double oldy2 = y2;
		
		x1 *= pow3[ord]-1;
		y1 *= pow3[ord]-1;
		x2 *= pow3[ord]-1;
		y2 *= pow3[ord]-1;
		
		lld ix1 = (x1 + .5);
		lld ix2 = (x2 + .5);
		lld iy1 = (y1 + .5);
		lld iy2 = (y2 + .5);

		double six1 = double(ix1) / (pow3[ord]-1);
		double siy1 = double(iy1) / (pow3[ord]-1);
		double six2 = double(ix2) / (pow3[ord]-1);
		double siy2 = double(iy2) / (pow3[ord]-1);

		double res = 0.0;
		res += dist(oldx1, oldy1, six1, siy1);
		res += dist(oldx2, oldy2, six2, siy2);
		
		if (ix1 < 0)
			ix1 = 0;
		if (ix1 >= pow3[ord])
			ix1 = pow3[ord]-1;
			
		if (ix2 < 0)
			ix2 = 0;
		if (ix2 >= pow3[ord])
			ix2 = pow3[ord]-1;
			
		if (iy1 < 0)
			iy1 = 0;
		if (iy1 >= pow3[ord])
			iy1 = pow3[ord]-1;
			
		if (iy2 < 0)
			iy2 = 0;
		if (iy2 >= pow3[ord])
			iy2 = pow3[ord]-1;
		
		lld l1 = numSoFar(ix1, iy1, ord);
		lld l2 = numSoFar(ix2, iy2, ord);
		
//		printf("num so far: %lld and %lld\n", l1, l2);
		
		res += ABS(l1-l2) * (1.0 / (pow3[ord]-1));
		
		printf("Case %d. Distance is %.04lf\n\n", casenum, res);
	}
}