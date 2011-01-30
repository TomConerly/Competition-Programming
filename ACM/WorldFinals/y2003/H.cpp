#include <stdio.h>
#include <vector>
#include <map>

using namespace std;


/* 2003 World Finals
 * Problem H: A Spy in the Metro
 * Type: DP
 * Difficulty Coding: 2
 * Algorithmic Difficulty: 3
 * Solution: DP[i][j] is the spy is in station i at time j.
 */ 

vector<int> transtimes;

vector<vector<bool> > arriveleft;
vector<vector<bool> > arriveright;

int numstations;

map<pair<int,int>, int> dp;

int start;

int waitTime(int station, int time)
{
	if (time == 0 && station == start)
		return 0;
	if (time < 0)
		return -1;
		
//	if (station == 1 && time == 5)
//	{
//		printf("blah\n");
//	}
		
	if (dp.find(make_pair(station, time)) == dp.end())
	{
		int res = -1;
		
		if (station && arriveleft[station][time])
		{
			int lowcase = waitTime(station-1, time - transtimes[station-1]);
			
			if (lowcase != -1)
			{
				if (res == -1)
					res = lowcase;
				else
					res = min(res, lowcase);
			}
		}
		
		if (station < numstations - 1 && arriveright[station][time])
		{
			int lowcase = waitTime(station+1, time - transtimes[station]);
			
			if (lowcase != -1)
			{
				if (res == -1)
					res = lowcase;
				else
					res = min(res, lowcase);
			}
		}
		
		int lowcase = waitTime(station, time-1);
		if (lowcase != -1)
			lowcase++;
		
		if (lowcase != -1)
		{
			if (res == -1)
				res = lowcase;
			else
				res = min(res, lowcase);
		}
		
		dp[make_pair(station,time)] = res;
		
//		printf("Answer at station %d, time %d is %d\n", station, time, res);
	}
	return dp[make_pair(station,time)];
}

int main()
{
	FILE *infile = fopen("H.in", "r");
	for(int casenum = 1;; casenum++)
	{
		dp.clear();
		arriveleft.clear();
		arriveright.clear();
		transtimes.clear();		
		
		int n,t;
		fscanf(infile, "%d %d", &n, &t);
		
		if (!n)
			break;
		
		for(int i = 0; i < n-1; i++)
		{
			int val;
			fscanf(infile, "%d",  &val);
			transtimes.push_back(val);
		}
		
		numstations = n;
		arriveleft.resize(n, vector<bool>(t+1));
		arriveright.resize(n, vector<bool>(t+1));
		
		int m1;
		fscanf(infile, "%d", &m1);
		
		for(int i = 0; i < m1; i++)
		{
			int time;
			fscanf(infile, "%d", &time);
			
			for(int j = 0; j < n; j++)
			{
				if (time > t)
					break;
				
				arriveleft[j][time] = true;
				if (j < n-1)
					time += transtimes[j];
			}
		}
		
		
		
		int m2;
		fscanf(infile, "%d", &m2);
		
		for(int i = 0; i < m2; i++)
		{
			int time;
			fscanf(infile, "%d", &time);
			
			for(int j = n-1; j >= 0; j--)
			{
				if (time > t)
					break;
				
				arriveright[j][time] = true;
				if (j)
					time += transtimes[j-1];
			}
		}
		
		start = 0;
		int res = waitTime(n-1, t);
		
		if (res == -1)
			printf("Case Number %d: impossible\n", casenum);
		else
			printf("Case Number %d: %d\n", casenum, res);
	}
}