/* 2002 World Finals
 * Problem E: Island Hopping
 * Type: MST
 * Difficulty Coding: 2
 * Algorithmic Difficulty: 4
 * Solution: It ends up that using Prims starting at the main island
 * solves the problem. Prims always picks the minimum edge, so we can do a hand
 * wavey proof that at the start all the nodes have minimum max of distances of edges to them
 * and since prims adds minimum edge that will stay true. This is definetly corret I just
 * don't want to bother to formally prove here.
 */    

#include <stdio.h>
#include <math.h>
#include <queue>

using namespace std;

class Edge
{
public:
	int to;
	int from;
	double len;
	Edge(int f, int t, double l)
	{
		from = f;
		to = t;
		len = l;
	}

};
bool operator<(const Edge &a, const Edge &b)
{
	return a.len > b.len;
}
int N;
int x[50];
int y[50];
int p[50];
bool v[50];
double adj[50][50];
vector< vector<int> > g;

double max(double a, double b)
{
	if(a > b) return a;
	else return b;
}

double dist(int a, int b)
{
	double dx = x[a]-x[b];
	double dy = y[a]-y[b];
	return sqrt(dx*dx+dy*dy);
}
double dfs(int at,double len)
{
	if(v[at]) return 0;
	v[at] = true;
	
	double ret = len*p[at];

	for(int i = 0; i < g[at].size();i++)
	{
		ret += dfs(g[at][i], max(len,adj[at][g[at][i]]) );
	}
	return ret;
}
int main()
{
	
	priority_queue<Edge> pq;
	
	for(int CASE = 1;;CASE++)
	{
		scanf("%d",&N);
		if(N == 0) break;

		int totalPeople=0;

		for(int i = 0; i < N;i++)
		{
			v[i] = false;
			scanf("%d %d %d",&x[i],&y[i],&p[i]);
			totalPeople += p[i];
		}
		for(int i = 0; i < N;i++)
		{
			for(int j = i+1; j < N;j++)
			{
				adj[i][j] = dist(i,j);
				adj[j][i] = adj[i][j];
			}
		}
		while(pq.size() > 0) pq.pop();
		for(int i = 1; i < N;i++)
		{
			pq.push(Edge(0,i,adj[0][i]));
		}
		g.clear();
		for(int i = 0; i < N;i++)
			g.push_back(vector <int>());

		v[0] = true;
		int addCount = 0;
		while(addCount != N-1)
		{			
			Edge e = pq.top();
			pq.pop();

			if(v[e.to]) continue;
			v[e.to] = true;

			g[e.to].push_back(e.from);
			g[e.from].push_back(e.to);
			addCount++;

			for(int i = 0; i < N;i++)
			{
				if(v[i]) continue;
				pq.push(Edge(e.to,i,adj[e.to][i]));
			}
		}
		for(int i = 0; i < N;i++)
			v[i] = false;
		
		double cost = dfs(0,0.0);
		printf("Island Group: %d Average %.2f\n\n",CASE,cost/totalPeople);
	}
}

