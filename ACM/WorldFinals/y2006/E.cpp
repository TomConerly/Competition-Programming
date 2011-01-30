#include <stdio.h>
#include <vector>
#include <map>
#include <set>

using namespace std;

/* 2006 World Finals
 * Problem E: Bit Compressor
 * Type: DP
 * Difficulty Coding: 3
 * Algorithmic Difficulty:4
 * Solution: The DP[i][j][k] where i is the number of chars seen so far,
 * j is the number of chars generated, and k is the number of zeroes generated
 * and DP[i][j][k] is the number of ways we can do this using the first i characters.
 */ 

vector<map<pair<int,int>,pair<int,int> > > table;

typedef long long lld;

lld tobin(int len, char *s)
{
	lld res = 0;
	for(int i = 0; i < len; i++)
	{
		res *= 2;
		if (s[i] == '1')
			res++;
	}
	return res;
}

int main()
{
	for(int casenum = 1;; casenum++)
	{
		int L,n;
		scanf("%d %d", &L, &n);
		
		if (L == 0 &&  n == 0)
			break;
			
		table.clear();
		
		char buf[1000];
		
		scanf("%s", buf);
		
		table.push_back(map<pair<int,int>,pair<int,int> >());
		
		table.back()[make_pair(0,0)] = make_pair(1,0);
		
		for(int i = 0; buf[i]; i++)
		{
			table.push_back(map<pair<int,int>,pair<int,int> >());
			
			if (buf[i] == '0')
			{
				for(map<pair<int,int>,pair<int,int> >::iterator iter = table[i].begin(); iter != table[i].end(); iter++)
				{
					//printf("at index %d, adding (%d, %d)  to key (%d %d)\n", i, iter->second.first, iter->second.second, iter->first.first+1, iter->first.second);
					table.back()[make_pair(iter->first.first + 1, iter->first.second)].first += iter->second.first + iter->second.second;
				}
			}
			
			for(int j = 0; j <= i; j++)
			{
				if (buf[j] == '0')
					continue;
				if (j == i-1 && buf[i] == '0')
					continue;
				
				
				//printf("i is %d, j is %d, string is %s\n", i, j, buf + j);
				lld newnum = tobin(i-j+1, buf + j);
				
			//	printf("newnum is %lld\n", newnum);
				
				for(map<pair<int,int>,pair<int,int> >::iterator iter = table[j].begin(); iter != table[j].end(); iter++)
				{
					if(newnum == 3 && iter->first.first + 2 <= L)
					{
						table.back()[make_pair(iter->first.first + 2, iter->first.second + 2)].second += iter->second.first;
					}
					if (iter->first.first + newnum > L)
						continue;
									
					table.back()[make_pair(iter->first.first + newnum, iter->first.second + newnum)].second += iter->second.first;
					
				}
			}
			
			//printf("table size: %d\n", table.back().size());
		}
		
		pair<int,int> res = table.back()[make_pair(L,n)];
		
		//printf("num solutions: %d\n", res.first + res.second);
		
		if (res.first + res.second == 0)
			printf("Case #%d: NO\n", casenum);
		else if (res.first + res.second == 1)
			printf("Case #%d: YES\n", casenum);
		else
			printf("Case #%d: NOT UNIQUE\n", casenum);
	}
}