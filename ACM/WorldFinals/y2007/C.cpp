#include <stdio.h>
#include <vector>
#include <set>
#include <map>
#include <math.h>

/* 2007 World Finals
 * Problem C: Grand Prix
 * Type: Computational Geometry
 * Difficulty Coding: 3
 * Algorithmic Difficulty: 2
 * Solution: You simply need to find the range of rotations that make each edge correct.
 * Then do intersection on the edges.
 */ 

using namespace std;

int main()
{
	for(int casenum = 1;; casenum++)
	{
		int n,theta;
		scanf("%d %d", &n, &theta);
		
		if (n == 0 && theta == 0)
			break;
		
		vector<pair<int,int> > pts(1, make_pair(0,0));
		
		for(int i = 0; i < n; i++)
		{
			int a,b;
			scanf("%d %d", &a, &b);
			pts.push_back(make_pair(a,b));
		}

		if (theta == 0)
		{
			printf("Case %d: Acceptable as proposed\n\n", casenum);
			continue;
		}
		
		vector<pair<double,double> > intervals;
		
		for(int i = 0; i < pts.size()-1; i++)
		{
			double ang = atan2(pts[i+1].second - pts[i].second, pts[i+1].first - pts[i].first);
			
			double min = -M_PI / 2.0 - ang;
			double max = M_PI / 2.0 - ang;
			
			if (min < -M_PI)
			{
				min += 2.0*M_PI;
				max += 2.0*M_PI;
			}
		
//			printf("Interval: %lf, %lf\n", min / M_PI * 180, max / M_PI * 180);	
			intervals.push_back(make_pair(min,max));
		}
		
		double start = intervals[0].first;
		double end = intervals[0].second;
		
		
		for(int i = 0; i < intervals.size(); i++)
		{
			if (intervals[i].first < start - M_PI)
			{
				intervals[i].first += 2*M_PI;
				intervals[i].second += 2*M_PI;
			}
			if (intervals[i].first > start + M_PI)
			{
				intervals[i].first -= 2*M_PI;
				intervals[i].second -= 2*M_PI;
			}
			start = max(start, intervals[i].first);
			end = min(end, intervals[i].second);
		}
		if (end < start)
		{
			printf("Case %d: Unacceptable\n\n", casenum);
			continue;
		}
		if ((start < 0 && end > 0) ||
			(start - 2*M_PI < 0 && end - 2*M_PI > 0) ||
			(start + 2*M_PI < 0 && end + 2*M_PI > 0))
		{
			printf("Case %d: Acceptable as Proposed\n\n", casenum);
			continue;
		}
		
		vector<double> checkpts;
		
		checkpts.push_back(start);
		checkpts.push_back(end);
		checkpts.push_back(start + 2*M_PI);
		checkpts.push_back(end + 2*M_PI);
		checkpts.push_back(start - 2*M_PI);
		checkpts.push_back(end - 2*M_PI);
		
		double best = 2*M_PI;
		
		for(int i = 0; i < checkpts.size(); i++)
		{
			if (fabs(checkpts[i]) < fabs(best))
				best = checkpts[i];
		}
		
//		printf("in radians: %lf\n", best);
		
		if (best < 0)
			printf("Case %d: Acceptable after clockwise rotation of %.02lf degrees\n\n", casenum, fabs(best) / M_PI * 180.0);
		else
			printf("Case %d: Acceptable after counterclockwise rotation of %.02lf degrees\n\n", casenum, fabs(best) / M_PI * 180.0);
	}
}

