/* 2002 World Finals
 * Problem B: Unreachable Codes (Passed Online Judge)
 * Type: DP (Same problem as South American 2007 problem A)
 * Difficulty Coding:  4
 * Algorithmic Difficulty: 2
 * Solution: The state is simply which word we used last, and 
 * how many characters of it are unmatched at the ends. This leads
 * to only 400 or so states so we can brute force over all of them
 * trivially.
 */         
#include <stdio.h>
#include <vector>
#include <string>
#include <map>
#include <algorithm>
#include <numeric>
#include <queue>
#include <set>

using namespace std;

class Node
{
public:
	int len;
	string soFar;
	int unmatchLen;
	int prev;
	bool legal;
	string left,right;
	bool exLeft;

	Node(int l, string s, int u, int p,bool a,string l1,string r1, bool exl)
	{
		len = l;
		soFar = s;
		unmatchLen = u;
		prev = p;
		legal = a;
		left = l1;
		right = r1;
		exLeft = exl;
	}

};

bool operator<(const Node &a, const Node &b)
{
	return a.len > b.len;
}

int mask(int unmatchLen) {
	return (1<<unmatchLen)-1;
}
int min(int a, int b)
{
	if( a < b) return a;
	return b;
}
int toInt(string s)
{
	int ans = 0;
	for(int i = 0; i < s.size();i++)
	{
		ans = (ans << 1) | (s[i]-'0');
	}
	return ans;
}
int N;
int main()
{
	char buffer[100000];
	int codes[200];
	int len[200];
	string str[200];
	int visited[200][210];
	int zero = 0;
	priority_queue<Node> pq;	
	for(int CASE = 1;; CASE++)
	{
		scanf("%d\n",&N);
		if(N == 0) break;	

		for(int i = 0; i < 200;i++)
			for(int j = 0; j < 210;j++)
				visited[i][j] = -1;

		while(pq.size() > 0) pq.pop();

		if(pq.size() != 0)
			printf("ERROR! %d\n",1/zero);

		for(int i = 0; i < N;i++)
		{					
			scanf("%s\n",&buffer);
			codes[i] = strtol(buffer,NULL,2);
			str[i] = string(buffer);
			len[i] = str[i].size();

			if(toInt(str[i]) != codes[i] || len[i] > 20)
			{
				printf("ERROR %d\n",1/zero);
			}

			pq.push(Node(len[i],str[i],len[i],i,true,str[i],string(""),true));
		}		

		string ans;
		int bestLen = 1000000000;
		int best = bestLen;
		int prev = 0;
		while(pq.size() > 0)
		{
			Node n = pq.top();
			pq.pop();

			if(n.len < prev) 
			{
				printf("ERROR! %d\n",1/zero);
			}

			if(n.unmatchLen == 0)
			{
				best = min(best,n.len);
				if(bestLen == 1000000000 || n.soFar.compare(ans) < 0)
					ans = n.soFar;

				if(n.left.compare(n.right) != 0 || n.left.compare(n.soFar) != 0 || n.right.compare(n.soFar) != 0)
				{
					printf("ERROR! %d\n",1/zero);
				}

				if(n.soFar < ans)
					printf("ERROR! %d\n",1/zero);

				bestLen = n.soFar.size();				
			}
			
			if(n.len > bestLen) break;

			if(n.legal != true)
			{	
				if(visited[n.prev][n.unmatchLen] != -1 && visited[n.prev][n.unmatchLen] < n.len) 
				{
					continue;
				}
				visited[n.prev][n.unmatchLen] = n.len;
			}

			for(int i = 0; i < N;i++)
			{
				if(n.legal == true && n.prev == i) continue;

				if(len[i] >= n.unmatchLen)
				{
					if(str[i].substr(0,n.unmatchLen).compare(str[n.prev].substr(len[n.prev]-n.unmatchLen))==0)				
					{
						string s = str[i].substr(n.unmatchLen);
						Node a(n.len+len[i]-n.unmatchLen,n.soFar+s,len[i]-n.unmatchLen,i,false,n.left+(!n.exLeft?str[i]:string("")),n.right+(n.exLeft?str[i]:string("")),!n.exLeft);
						if(visited[a.prev][a.unmatchLen] != -1 && visited[a.prev][a.unmatchLen] < a.len)
							continue;
						pq.push(a);
					}
				}
				else
				{
					if(str[i].compare(str[n.prev].substr(len[n.prev]-n.unmatchLen,len[i])) == 0)				
					{
						Node a(n.len,n.soFar,n.unmatchLen-len[i],n.prev,false,n.left+(!n.exLeft?str[i]:string("")),n.right+(n.exLeft?str[i]:string("")),n.exLeft);
						if(visited[a.prev][a.unmatchLen] != -1 && visited[a.prev][a.unmatchLen] < a.len)
							continue;
						pq.push(a);
					}
				}
			}
		}
		if(best != ans.size() || best == 1000000000)
			printf("ERROR! %d\n",1/zero);

		if(best != 1000000000)
		{

		printf("Code %d: %d bits\n",CASE,ans.size());

		while(ans.size() > 0)
		{
			printf("%s\n",ans.substr(0,20).c_str());
			ans = ans.substr(min(ans.size(),20));
		}
		printf("\n");
		}else{
			printf("Code %d: null\n",CASE);
			printf("\n");
		}
	}
}

