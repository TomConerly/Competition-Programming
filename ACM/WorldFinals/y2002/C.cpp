/* 2002 World Finals
 * Problem C: Crossing The Dessert (Passed Online Judge)
 * Type: Graph Search
 * Difficulty Coding: 2 
 * Algorithmic Difficulty: 4
 * Solution: All we need is a formula which is 
 * given that we need k units of food at a node
 * dist d away how many units of food do we need
 * at this node. The formula is slightly compilicated and calculated
 * in function f(dist,n). Once you have that formula it is Dijkstra
 * starting from the end and working backward. We know we need 0 
 * units of food at the end then work backwards using Dijkstra 
 * to the start. Note because of how we defined f we do not sum
 * path costs the cost of going to node n is the min of the cost 
 * of all edges to n. 
 */        
#include <stdio.h>
#include <vector>
#include <string>
#include <map>
#include <algorithm>
#include <numeric>
#include <queue>
#include <set>
#include <math.h>

using namespace std;

class Node
{
public:
	int at;
	double len;

	Node(int a, double l)
	{
		at = a;
		len = l;
	}
};

bool operator<(const Node &a, const Node &b)
{
	return a.len > b.len;
}
int N,M;
int locx[20];
int locy[20];
double MAX = 1e20;

double ddd(int a, int b)
{
	double dx = locx[a]-locx[b];
	double dy = locy[a]-locy[b];
	return sqrt(dx*dx+dy*dy);
}
double f(double dist, double n){
	double need = n - (M-2*dist);

	if (need <= 0) return dist + n;
	if (M - 3 * dist <= 0) return MAX;

	int trips = (int)ceil( need/(M - 3 * dist));
	return n + ((2*trips) + 1) * dist;
}

int main()
{
	priority_queue<Node> pq;
	double dist[20];
	for(int CASES = 1;; CASES++)
	{
		scanf("%d %d",&N,&M);
		if(N == 0 && M == 0) break;

		for(int i = 0; i < N;i++)
		{
			scanf("%d %d",&locx[i],&locy[i]);
			dist[i] = MAX;
		}

		while(pq.size() > 0) pq.pop();

		pq.push(Node(N-1,0));
		int ans = -1;
		while(pq.size() > 0)
		{
			Node n = pq.top();
			pq.pop();

			if(n.at == 0)
			{
				ans = (int)ceil(n.len);
				break;
			}
			if(n.len > 1000000) break;
			
			for(int i = 0; i < N;i++)
			{
				double di = ddd(n.at,i);
				double len = f(di,n.len);
				if(len < dist[i])
				{
					dist[i] = len;
					pq.push(Node(i,len));
				}
			}
		}
		if(ans == -1 || ans > 1000000)
			printf("Trial %d: Impossible\n\n",CASES);
		else
			printf("Trial %d: %d units of food\n\n",CASES,ans);
	}
}

