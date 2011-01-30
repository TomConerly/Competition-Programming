#include <stdio.h>
#include <vector>
#include <map>

/* 2001 World Finals
 * Problem H: Professor Monotonic’s Networks
 * Type: Sorting Network/Brute Force
 * Difficulty Coding: 3 
 * Algorithmic Difficulty: 4
 * Solution: Recognize that a sorting network works
 * if it correctly sorts when all inputs are either 0/1 for
 * all cases. Only 15 inputs so 2^15 to test which is brute
 * forceable.
 */ 

using namespace std;

vector<pair<int,int> > flips;

int numInputs, numNodes;

void doSort(vector<int> &v)
{
	for(int i = 0; i < flips.size(); i++)
	{
		if (v[flips[i].first] > v[flips[i].second])
			swap(v[flips[i].first], v[flips[i].second]);
	}
}

bool isSorted(vector<int> &v)
{
	for(int i = 0; i < v.size() - 1; i++)
	{
		if (v[i] > v[i+1])
			return false;
	}
	return true;
}

int getDepth()
{
	vector<int> max;
	max.resize(numInputs);
	
	vector<bool> s;
	s.resize(flips.size());
	int t = 0;
	int numS = 0;
	
	vector<pair<int,int> > v;
	
	vector<int> counts;
	counts.resize(numInputs);
	for(int i = 0; i < flips.size(); i++)
	{
		v.push_back(make_pair(flips[i].first, counts[flips[i].first]));
		counts[flips[i].first]++;
		
		v.push_back(make_pair(flips[i].second, counts[flips[i].second]));
		counts[flips[i].second]++;
	}
	int update = 1;
	while(numS != flips.size() && update == 1)
	{
		update = 0;
//		printf("%d\n", numS);
		vector<int> maxCopy = max;
		
		for(int i = 0; i < v.size(); i += 2)
		{
			if (s[i/2])
				continue;
				
		//	printf("%d   %d   %d   %d\n", max[v[i].first], v[i].second,
		//		max[v[i+1].first],  v[i+1].second);
		
			if (max[v[i].first] >= v[i].second &&
				max[v[i+1].first] >= v[i+1].second)
			{
				update = 1;
				printf("%d %d\n", i/2, t);
				numS++;
				s[i/2] = true;
				maxCopy[v[i].first]++;
				maxCopy[v[i+1].first]++;
			}
		}
		
		max = maxCopy;
		t++;
	}
	return t;
}

//#define inFile stdin

int main()
{
	FILE *inFile = fopen("sort.in", "r");
	
//	printf("hello!\n");
	for(int caseNum = 1;; caseNum++)
	{
		fscanf(inFile, "%d %d", &numInputs, &numNodes);
		
		if (numInputs == 0 && numNodes == 0)
			break;
		
		flips.clear();
		
		for(int i = 0; i < numNodes; i++)
		{
			int a,b;
			fscanf(inFile, "%d %d", &a, &b);
			a--;
			b--;
			if (a>b)
				swap(a,b);
			flips.push_back(make_pair(a,b));
		}
		
		bool isGood = true;
		
		for(int i = 0; i < (1 << numInputs); i++)
		{
			vector<int> v;
			for(int j = 0; j < numInputs; j++)
				v.push_back(!!((1 << j) & i));
			doSort(v);
			if (!isSorted(v))
			{
			//	printf("breaks at %d\n", i);
				isGood = false;
				break;
			}
			v.clear();
			for(int j = 0; j < numInputs; j++)
				v.push_back(!((1 << j) & i));
			doSort(v);
			if (!isSorted(v))
			{
			//	printf("breaks 2 at %d\n", i);
				isGood = false;
				break;
			}
		}
		
		if (isGood)
			printf("Case %d is a sorting network and operates in %d time units.\n", caseNum, getDepth());
		else
			printf("Case %d is not a sorting network and operates in %d time units.\n", caseNum, getDepth());
	}
}