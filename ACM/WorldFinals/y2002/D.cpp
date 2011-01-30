/* 2002 World Finals
 * Problem D: Ferries (Passed Online Judge)
 * Type: Binary Search
 * Difficulty Coding: 3 
 * Algorithmic Difficulty: 2
 * Solution: Find the minimal time to the end going at the max speed.
 * Then binary search over all speeds until you find the minimum one which
 * finishes in that time.
 */        
#include <stdio.h>
#include <vector>
#include <string>
#include <map>
#include <algorithm>
#include <numeric>
#include <math.h>

using namespace std;

typedef long long lld;
typedef vector<lld> Ferry;

vector<pair<bool, vector<lld> > > pathInfo;


lld getPathTime(double speed)
{
		
	 double currentTime = 0;
	 
	 for(lld i = 0; i < pathInfo.size(); i++)
	 {
		if (pathInfo[i].first)
		{
			vector<lld> vec = pathInfo[i].second;
			currentTime +=( vec[0] * 3600 )/ speed;
			currentTime = (lld)ceil(currentTime);
		}
		else
		{
			vector<lld> vec = pathInfo[i].second;
			lld hrStart = ((lld)(currentTime / 3600)) * 3600;
			double min = (currentTime-hrStart)/60;
			lld minutes = (lld)ceil(min);
			lld nextTime = *lower_bound(vec.begin() + 1, vec.end(), minutes);
			currentTime = hrStart + 60*(nextTime + vec[0]);
		}
	 }
	 return (lld)currentTime;
}

int main()
{
	FILE *inFile = stdin;
	int zero = 0;
	for(lld casenum = 1;; casenum++)
	{
		lld numlines;
		
		fscanf(inFile, "%lld", &numlines);
		if (numlines == 0)
			break;
			
		pathInfo.clear();
		
		for(lld i = 0; i < numlines; i++)
		{
			char a[10000], b[10000], c[10000];
			lld d;
			fscanf(inFile, "%s %s %s %lld", a, b, c, &d);
			
			if (string(c) == "road")
			{
				if(pathInfo.size() && pathInfo.back().first)
					pathInfo.back().second[0] += d;
				else
				{
					vector<lld> v;
					v.push_back(d);
					pathInfo.push_back(make_pair(true,v));
				}
			}
			else
			{
				lld n;
				fscanf(inFile, "%lld", &n);
				vector<lld> f;
				f.push_back(d);
				for(lld j = 0; j < n; j++)
				{
					lld e;
					fscanf(inFile, "%lld", &e);
					f.push_back(e);
				}
				f.push_back(f[1] + 60);
				pathInfo.push_back(make_pair(false, f));
			}
		}
		
		lld bestTime = getPathTime(80.00);

		double hi = 80.00;
		double lo = -1;
		while(1)
		{
			double mid = (hi + lo) / 2;
			
			if (getPathTime(mid) == bestTime)
			{ // good
				hi = mid;
			}
			else
			{ // bad
				lo = mid;
			}
			
			if(hi - lo < .0000000000001)
			{
				break;
			}
			if(hi < 0)
			{
				hi = 0;
				break;
			}
		}
		lld h = (lld)((hi+.005)*100);
		printf("Test Case %lld: %.2lld:%.2lld:%.2lld %lld.%.2lld\n\n",casenum, bestTime / 3600, (bestTime / 60) % 60, bestTime % 60, h / 100, h % 100);
	}
}