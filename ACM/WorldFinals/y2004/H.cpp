#include <stdio.h>
#include <vector>
#include <set>
#include <map>
#include <math.h>
#include <algorithm>

/* 2004 World Finals
 * Problem H: Tree-Lined Streets
 * Type: Greedy/Computational Geometry
 * Difficulty Coding: 3 
 * Algorithmic Difficulty: 2
 * Solution: Split roads into pieces without intersections, then we
 * simply want to greedily fill each piece (which is really just running
 * some math on its length).
 */ 

using namespace std;

typedef pair<double,double> point;

double eps = 0.0000000001;

vector<pair<point,point> > roads;

bool ptOnLine(pair<point,point> r, point p)
{
	double xmin = min(r.first.first, r.second.first) - eps;
	double xmax = max(r.first.first, r.second.first) + eps;
	double ymin = min(r.first.second, r.second.second) - eps;
	double ymax = max(r.first.second, r.second.second) + eps;
	
	return p.first >= xmin && p.first <= xmax && p.second >= ymin && p.second <= ymax;
}

point getIntersection(pair<point,point> r1,
						pair<point,point> r2)
{
	double a1 = (r1.second.second - r1.first.second);
	double b1 = (r1.first.first - r1.second.first);
	double c1 = a1 * r1.first.first + b1 * r1.first.second;

	double a2 = (r2.second.second - r2.first.second);
	double b2 = (r2.first.first - r2.second.first);
	double c2 = a2 * r2.first.first + b2 * r2.first.second;


//	printf("a1: %lf b1: %lf c1: %lf      a2: %lf   b2: %lf   c2:%lf\n", a1, b1, c1, a2, b2, c2);
	
	double det = (b2*a1 - b1*a2);
	
	if (fabs(det) < eps)
	{
		return make_pair(-1.0, -1.0);
	}
	
	double x = (b2*c1 - b1*c2) / det;
	double y = (a1 * c2 - a2 * c1) / det;
	
	return make_pair(x,y);
}

double dist(point p1, point p2)
{
	double dx = p1.first - p2.first;
	double dy = p1.second - p2.second;
	
	return sqrt(dx*dx + dy*dy);
}

int main()
{
	for(int casenum = 1;; casenum++)
	{
		roads.clear();
		int n;
		scanf("%d", &n);
		
		if (n == 0)
			break;
		
		for(int i = 0; i < n ;i++)
		{
			int a,b,c,d;
			scanf("%d %d %d %d", &a, &b, &c, &d);
			roads.push_back(make_pair(make_pair(a,b),make_pair(c,d)));
		}
		
		int res = 0;
		
		for(int i = 0; i < roads.size(); i++)
		{
			point pnt1 = roads[i].first;
			point pnt2 = roads[i].second;
			
			vector<point> intersections;
			
			for(int j = 0; j < roads.size(); j++)
			{
				point inter = getIntersection(roads[i], roads[j]);
				
//				printf("got %lf, %lf\n", inter.first, inter.second);
				if (inter.first < -.5)
				{
//					printf("outside\n");
					continue;
				}
				
				if (!ptOnLine(roads[i], inter) ||
					!ptOnLine(roads[j], inter))
					continue;
				
				
				intersections.push_back(inter);
				
//				printf("ADDED   numintersections: %d\n", intersections.size());
			}
			
			intersections.push_back(pnt1);
			intersections.push_back(pnt2);
			
			sort(intersections.begin(), intersections.end());
			
//			printf("numintersections: %d\n", intersections.size());
			for(int j = 0; j < intersections.size()-1; j++)
			{
				double d = dist(intersections[j], intersections[j+1]);
				
				printf("road %d distance %lf\n", i, d);
				
				if (j != 0)
					d -= 25.0;
				if (j+1 != intersections.size()-1)
					d -= 25.0;
				if (d < 0.0)
					continue;
				res += int(d / 50.0) + 1;
			}
		}
		
		printf("Map %d\nTrees = %d\n", casenum, res);
	}
}