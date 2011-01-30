#include <stdio.h>
#include <vector>
#include <map>
#include <algorithm>
#include <numeric>

/* 2007 World Finals
 * Problem G: Network
 * Type: Greedy
 * Difficulty Coding: 2
 * Algorithmic Difficulty: 2
 * Solution: Try all orderings for which message you will send first
 * then from there it is just greedy. 
 */ 


using namespace std;

int main()
{
	for(int casenum = 1;;casenum++)
	{
		int n,m;
		
		scanf("%d %d", &n, &m);
		
		if (n == 0 && m == 0)
			break;
		
		vector<pair<int,int> > messages;
		vector<pair<int,pair<int,int> > > packets;
		
		for(int i = 0; i < n; i++)
		{
			int a;
			scanf("%d", &a);
			messages.push_back(make_pair(i+1,a));
		}
		
		for(int i = 0; i < m; i++)
		{
			int a,b,c;
			scanf("%d %d %d", &a, &b, &c);
			packets.push_back(make_pair(a,make_pair(b,c)));
		}
		
		int res = -1;
		
		do
		{
//			printf("trying permutation\n");
			int size = 0, maxsize = 0;
			map<pair<int,int>, int> cache;
			
			int index = 0, start = 1;
			
			for(int i = 0; i < packets.size(); i++)
			{
				cache[make_pair(packets[i].first, packets[i].second.first)] = packets[i].second.second;
				size += packets[i].second.second - packets[i].second.first + 1;
				
				while(cache.count(make_pair(messages[index].first, start)))
				{
					map<pair<int,int>, int>::iterator iter = cache.find(make_pair(messages[index].first, start));
					
					start = iter->second + 1;
					size -= iter->second - iter->first.second + 1;
					
					cache.erase(iter);
					
					if (start > messages[index].second)
					{
						index++;
						start = 1;
					}
				}
//				printf("maxsize is %d\n", size);
				maxsize = max(maxsize, size);
			}
			if (res == -1 || maxsize < res)
				res = maxsize;
			
		} while (next_permutation(messages.begin(), messages.end()));
		
		printf("Case %d: %d\n\n", casenum, res);
	}
}