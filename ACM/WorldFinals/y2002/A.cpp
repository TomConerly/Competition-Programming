/* 2002 World Finals
 * Problem A: Ballons In a Box (Passed Online Judge)
 * Type: Simluation
 * Difficulty Coding: 2 
 * Algorithmic Difficulty: 1
 * Solution: We can try all 6! ways of filling the box.
 */                                         
#include <stdio.h>
#include <math.h>
#include <algorithm>

#define min(a,b) ((a)<(b)?(a):(b))

#ifndef M_PI
#define M_PI 3.14159265358979323846264338327950288419
#endif

using namespace std;

double sphere_vol(double r)
{
	double vol = 4.0/3.0 * M_PI;
	vol *= r*r*r;
	return vol;
}

double dist(int a[3], int b[3])
{
	double i=0,t;
	for (int k=0;k<3;k++)
	{
		t = a[k]-b[k];
		t *= t;
		i += t;
	}

	return sqrt(i);
}

int main()
{
	int box[2][3];
	int p[6][3];
	double s[6];
	int perm[6];
	int n,i,j,k;
	int cs = 1;

	while(true)
	{
		scanf("%d", &n);
		if (!n) break;

		for (i=0;i<2;i++)
			scanf("%d %d %d", box[i], box[i]+1, box[i]+2);

		for (i=0;i<n;i++)
		{
			scanf("%d %d %d", p[i], p[i]+1,p[i]+2);
			perm[i]=i;
			
		}
		
		long long ttlvol = 1;
		for (i=0;i<3;i++)
		{
			if (box[1][i] > box[0][i])
			{
				k = box[0][i];
				box[0][i] = box[1][i];
				box[1][i] = k;
			}

			ttlvol *= (box[0][i]-box[1][i]);
		}

		double bstvol = -1;
		do
		{
			double tmpvol = ttlvol;
			memset(s, 0, sizeof(s));
			double big = 0;

			for(i=0;i<n;i++)
			{
				// make sure point is in the box and find boundary by box
				for (k=0;k<3;k++)
				{
					int z = p[perm[i]][k];
					if (z >= box[0][k] || z <= box[1][k]) break;

					double tmp = min(box[0][k] - z, z - box[1][k]);
					if (!k) big = tmp;
					big = min(big, tmp);
				}
				if (k!=3) continue; //outside of box

				// find the biggest based on already chosen points
				for (k=0;k<i;k++)
				{
					double d = dist(p[perm[i]],p[perm[k]]);
					if (d <= s[k]) break; // inside balloon
					if (s[k] > 0.0) big = min(big, d-s[k]); // otberwise we can do no better than this
				}
				if (k != i) continue; //inside a balloon

				s[i] = big;
				tmpvol -= sphere_vol(big);
			}

			tmpvol += 0.5;
			if (bstvol == -1) bstvol = tmpvol;
			bstvol = min(bstvol,tmpvol);

		} while(next_permutation(perm,perm+n));

		printf("Box %d: %d\n\n", cs++, ((int)bstvol));
	}

	return 0;
}
