#include <stdio.h>
#include <math.h>
#include <algorithm>
#include <queue>
#include <cassert>

/* 2002 World Finals
 * Problem F: Toil for Oil (Passed one Online Judge, failed on another)
 * Type: Line Sweep
 * Difficulty Coding: 5 
 * Algorithmic Difficulty: 4
 * Solution: Line sweep from the bottom up keeping track of the various regions.
 * Calculate oil at the same time, merge them when you need to. Tons of cases
 * to worry about.
 */

using namespace std;

#define O(a) (((a)+1)%N)
#define P(a) (((a)-1+N)%N)
#define OUTSIDE (-1)
#define DRAIN (-2)

double ab(double a)
{
	if( a > 0) return a;
	else return -a;
}
int min(int a, int b)
{
	if(a < b) return a;
	return b;
}
int max(int a, int b)
{
	if(a > b) return a;
	return b;
}

int x[100000];
int y[100000];
int p[100000];
int N;
int LARGE = 1000000000;
vector<int> edge;
vector<int> num;
vector<int> e;

class Node
{
public:
	int idx;

	Node(int i)
	{
		idx = i;
	}

};

bool operator<(const Node &a, const Node &b)
{
	if(y[a.idx] != y[b.idx]) return y[a.idx] > y[b.idx];
	return x[a.idx] > x[b.idx];
}
double getx(int eidx, double yval)
{
	double x1 = y[eidx] < y[O(eidx)] ? x[eidx] : x[O(eidx)];
	double x2 = y[eidx] < y[O(eidx)] ? x[O(eidx)] : x[eidx];
	double dx = x2-x1;
	double dy = abs(y[eidx]-y[O(eidx)]);

	if(dy == 0) return max(x1,x2);
	return(yval-min(y[eidx],y[O(eidx)]))*dx/dy+x1;
}
void convert(int i, int to)
{
	int t = num[i];
	for(unsigned int j = 0; j < num.size();j++)
	{
		if(num[j] == t)
			num[j] = to;
	}
}

int main()
{
	priority_queue<Node> pq;
	FILE* in = stdin;
	for(int CASE=1;;CASE++)
	{		
		fscanf(in, "%d\n",&N);
		if(N == 0) break;

		edge.clear();
		num.clear();
		while(pq.size() > 0)
			pq.pop();

		for(int i = 0; i < N;i++)
		{
			fscanf(in, "%d %d %d\n",&x[i],&y[i],&p[i]);
			pq.push(Node(i));
		}

		int nextNum = 1;
		double total = 0;
		int prev = -LARGE;
		int count = 0;
		while(pq.size() > 0)
		{
			Node a = pq.top();
			pq.pop();
			

		/*	printf("Edges: ");
			for(unsigned int i = 0; i < edge.size();i++)
			{
				printf("%d ",edge[i]);
			}
			printf("\n");
			printf("Nums: ");
			for(unsigned int i = 0; i < num.size();i++)
			{
				printf("%d ",num[i]);
			}
			printf("\n");
			printf("Node: %d %d\n",a.idx,y[a.idx]);
			fflush(stdout);
			
			assert((edge.size() == 0 && num.size() == 0) ||(edge.size() == num.size()-1));
			for(int i = 0; i < edge.size();i++)
				printf("%.2f ",getx(edge[i],y[a.idx]));
			printf("\n");
			printf("Total: %.2f",total);
			printf("\n\n");*/
			for(int i = 0; i+1 < edge.size();i++)
			{
				assert(getx(edge[i],y[a.idx]) <= getx(edge[i+1],y[a.idx])+1e-5);
			}
			/* start at 1 because outside edge is always empty */
			if(prev != -LARGE && prev != y[a.idx])
			{
				for(unsigned int i = 1; i < num.size();i++)
				{
					if(num[i] >= 0)
					{
						double x11 = getx(edge[i],prev);
						double x12 = getx(edge[i-1],prev);

						double x21 = getx(edge[i],y[a.idx]);
						double x22 = getx(edge[i-1],y[a.idx]);

						//assert((x11 <= x12 && x21 <= x22) || (x11 >= x12 && x21 >= x22));
						total += (y[a.idx]-prev)*(ab((double)((x11-x12)))+ab((double)((x21-x22))))/2.0;
						//printf("%d %d %f %f %f %f\n",(int)prev,(int)y[a.idx],x11,x12,x21,x22);
						count++;
					}
				}
			//	printf("%d %f\n",y[a.idx],total);
			}
			
			prev = y[a.idx];

			e.clear();
			for(unsigned int i = 0; i < edge.size();i++)
			{
				if(a.idx == edge[i] || a.idx == O(edge[i]))
				{
					e.push_back((int)i);
				}
			}

			if(e.size() == 2)
			{
				int c = min(e[0],e[1]);
				int d = max(e[0],e[1]);
				//assert(d-c == 1);

				edge.erase(edge.begin()+d);
				edge.erase(edge.begin()+c);
				

				if(p[a.idx] == 0)
				{
					num.erase(num.begin()+c+1);
					int left = min(num[c],num[c+1]);
					int right = max(num[c],num[c+1]);
					for(unsigned int i = 0; i < num.size();i++)
					{
						if(num[i] == right)
							num[i] = left;
					}
				}else{
					if(num[c] >= 0)
						convert(c,DRAIN);
					if(num[c+1] >= 0)
						convert(c+1,DRAIN);
					if(num[c+2] >= 0)
						convert(c+2,DRAIN);
					num.erase(num.begin()+c+1);
				}
				
				num.erase(num.begin()+c);


			}
			else if(e.size() == 1)
			{
				if(a.idx == edge[e[0]])
				{
					edge[e[0]] = P(edge[e[0]]);
				}
				else
				{
					edge[e[0]] = O(edge[e[0]]);
				}

			}
			else
			{
				//assert(e.size() == 0);
				for(unsigned int i = 0; i <= edge.size();i++)
				{
					double xval = (i == edge.size()) ? 1e50 :getx(edge[i],y[a.idx]);
					if(x[a.idx] < xval)
					{
						int left,right;
						double angle1 = atan2((double)y[O(a.idx)]-y[a.idx],(double)x[O(a.idx)]-x[a.idx]);
						double angle2 = atan2((double)y[P(a.idx)]-y[a.idx],(double)x[P(a.idx)]-x[a.idx]);

						if(angle1 > angle2)
						{
							left = a.idx;
							right = P(a.idx);
						}
						else
						{
							left = P(a.idx);
							right = a.idx;
						}
						edge.insert(edge.begin()+i,left);
						edge.insert(edge.begin()+i+1,right);

						if(i >= 0 && i < num.size())
						{
							int split = num[i];
							if(split == OUTSIDE)
							{
								num.insert(num.begin()+i+1,nextNum);
								num.insert(num.begin()+i+2,OUTSIDE);
								nextNum++;
							}
							else
							{
								num.insert(num.begin()+i+1,OUTSIDE);
								num.insert(num.begin()+i+2,split);
							}
						}
						else
						{
							num.insert(num.begin()+(i==0?0:num.size()),OUTSIDE);
							num.insert(num.begin()+(i==0?1:num.size()),nextNum);
							num.insert(num.begin()+(i==0?2:num.size()),OUTSIDE);
							nextNum++;
						}
						break;
					}
				}
			}
			if(p[a.idx] == 1)
			{
				for(unsigned int i = 0; i < edge.size();i++)
				{
					if(y[edge[i]] <= y[O(edge[i])])
					{
						if(a.idx == edge[i])
						{
							if(num[i] >= 0)
								convert(i,DRAIN);
							if(num[i+1] >= 0)
								convert(i+1,DRAIN);
						}
					}
					if(y[edge[i]] >= y[O(edge[i])])
					{
						if(a.idx == O(edge[i]))
						{
							if(num[i] >= 0)
								convert(i,DRAIN);
							if(num[i+1] >= 0)
								convert(i+1,DRAIN);
						}
					}
				}
			}
		}
		//printf("Cave %d: Oil Capacity = %f\n\n",CASE,total);
		//printf("%d\n",count);
		printf("%d\n",(int)(total+0.5));
		//printf("Cave %d: Oil capacity = %d\n\n",CASE,(int)(total+0.5));
		//printf("%d\n",(int)(total+0.5));
	}
}