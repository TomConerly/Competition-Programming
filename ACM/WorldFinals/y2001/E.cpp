#include <stdio.h>
#include <vector>
#include <set>
#include <algorithm>
#include <numeric>
#include <map>

/* 2001 World Finals
 * Problem E: The Geoduck GUI
 * Type: Search/Computational Geometry
 * Difficulty Coding: 3 
 * Algorithmic Difficulty: 2
 * Solution: Try all pairs of geoducks then simulate the game and take
 * the best pair.
 */ 

using namespace std;

vector<pair<int,int> > getUsedPoints(pair<int,int> v, int width, int height)
{
	set<pair<int,int> > used;
	vector<pair<int,int> > result;
	
	pair<int,int> newV = make_pair(abs(v.first), abs(v.second));
	
	bool flip = false;
	if (newV.first < newV.second)
	{
		swap(newV.first, newV.second);
		swap(width,height);
		flip = true;
	}
	
	used.insert(make_pair(0,0));
	result.push_back(make_pair(0,0));
	
	// if true, go up, if false, go right
	bool tieDirection = false;
	
	if (flip)
		tieDirection = !tieDirection;
	
	for(int x = 1;; x++)
	{
		
	
		int newY = ((2*x*newV.second + newV.first - newV.second) / (2*newV.first)) % height;
		int yMod = (2*x*newV.second + newV.first - newV.second) % (2*newV.first);
		
		if (yMod != 0 && result.back().second != newY)
		{
			pair<int,int> newPoint = make_pair((x-1) % width, newY);
			if (used.find(newPoint) != used.end())
				break;
			result.push_back(newPoint);
			used.insert(newPoint);
		}
		
		if (yMod == 0 && result.back().second != newY)
		{
			pair<int,int> newPoint;
			if (tieDirection)
				newPoint = make_pair((x-1) % width, newY);
			else
				newPoint = make_pair(x % width, (newY + height - 1) % height);
			
			if (used.find(newPoint) != used.end())
				break;
			result.push_back(newPoint);
			used.insert(newPoint);
		}
		
		pair<int,int> newPoint = make_pair(x % width, newY);
		if (used.find(newPoint) != used.end())
			break;
		result.push_back(newPoint);
		used.insert(newPoint);
	}
	
	if (flip)
	{
		for(int i = 0; i < result.size(); i++)
			swap(result[i].first, result[i].second);
		swap(width,height);
	}
	
	for(int i = 0; i < result.size(); i++)
	{
		if (v.first < 0)
			result[i].first = width - 1 - result[i].first;
		if (v.second < 0)
			result[i].second = height - 1 - result[i].second;
	}
	
	return result;
}

pair<int,int> getAnswer(int width, int height, pair<int,int> v1, pair<int,int> v2)
{
	vector<pair<int,int> > list1 = getUsedPoints(v1, width, height);
	vector<pair<int,int> > list2 = getUsedPoints(v2, width, height);
	
	set<pair<int,int> > used;
	
	int lastTime = 0;
	int i = 0;
	for(; i < list1.size() || i < list2.size(); i++)
	{
		pair<int,int> pt1 = (i < list1.size() ? list1[i] : list1.back());
		pair<int,int> pt2 = (i < list2.size() ? list2[i] : list2.back());

		if (used.find(pt1) == used.end() ||
			used.find(pt2) == used.end())
			lastTime = i+1;
		used.insert(pt1);
		used.insert(pt2);
		
		if (pt1 == pt2)
			break;
		
		if (i > 0 && i < list1.size() && i < list2.size() && (list1[i] == list2[i-1] && list2[i] == list1[i-1]))
			break;
	}
	
	return make_pair(used.size(), -lastTime);
}

bool sign(int n)
{
	return n < 0;
}

int main()
{
//	FILE *inFile = fopen("geoduck.in", "r");
	
	/*
	printf("list for -4,-3:\n");
	vector<pair<int,int> > thelist = getUsedPoints(make_pair(-4,-3), 6, 5);
	for(int i = 0; i < thelist.size(); i++)
	{
		printf("%d %d\n", thelist[i].first, thelist[i].second);
	}
	*/
	
	for(int casenum = 1;; casenum++)
	{
		int width,height;
		fscanf(stdin, "%d %d", &width, &height);
		
		if (width == 0 && height == 0)
			break;
		
		int numducks;
		fscanf(stdin, "%d", &numducks);
		
		vector<pair<int,int> > ducks;
		for(int i = 0; i < numducks; i++)
		{
			int x,y;
			fscanf(stdin, "%d %d", &x, &y);
			ducks.push_back(make_pair(x,y));
		}
		
		map<pair<int,int>, vector<pair<int,int> > > solutions;
		
		for(int i = 0; i < ducks.size(); i++)
		{
			for(int j = i+1; j < ducks.size(); j++)
			{
				if (!(sign(ducks[i].first) == sign(ducks[j].first) && sign(ducks[i].second) == sign(ducks[j].second)))
				{
					pair<int,int> thepair = make_pair(i+1,j+1);
					pair<int,int> solution = getAnswer(width, height, ducks[i], ducks[j]);
					solutions[solution].push_back(thepair);
				}
			}
		}
		
		map<pair<int,int>, vector<pair<int,int> > >::iterator goodIter = solutions.end();
		goodIter--;
		
		printf("Case %d   Cells Illuminated: %d   Minimum Time: %d\n", casenum, goodIter->first.first, -goodIter->first.second);
		
		vector<pair<int,int> > answers = goodIter->second;
		
		for(int i = 0; i < answers.size(); i++)
			printf("   Geoduck IDs:  %d  %d\n", answers[i].first, answers[i].second);
	}
}