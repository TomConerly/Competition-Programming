/* 2002 World Finals
 * Problem H: Silly Sort (Passed Online Judge)
 * Type: Greedy
 * Difficulty Coding: 3 
 * Algorithmic Difficulty: 4
 * Solution: First decompose the numbers into permutation notation.
 * (ex. 54231 -> (5,1)(4,3,2)) To solve one cycle on its own we must 
 * perform n-1 (n is cycle length) operations, and we must operate on each
 * element at each once. So the minimum cost is to always swap the minimum element
 * with the element who after the swap will be in its correct place. Each element is swapped
 * once and all other cost is on the minimum element so it must be a minimum.
 *
 * It might be good to combine cycles. But the only advantage of combining is having a smaller minimum
 * so we will only combine a cycle with the cycle containing the global minimum element. We can greedily say
 * if a cycle does better by combining with the min then combine it. 
 */    
#include <stdio.h>
#include <math.h>
#include <queue>
#include <algorithm>

using namespace std;

int min(int a, int b)
{
	if(a < b) return a;
	return b;
}

int N;
bool v[1000];
vector<int> dat;
vector<int> sorted;
int n[1000];
int m;
int main()
{
	for(int CASE = 1;;CASE++)
	{
		scanf("%d",&N);
		if(N == 0) break;

		dat.clear();
		sorted.clear();
		m = 1<<30;
		for(int i = 0; i < N;i++)
		{
			int t;
			scanf("%d",&t);
			dat.push_back(t);
			sorted.push_back(t);
			m = min(m,t);
		}
		sort(sorted.begin(),sorted.end());
		
		for(int i = 0; i < N;i++)
		{
			for(int j = 0; j < N;j++)
			{
				if(dat[i] == sorted[j])
				{
					n[i] = j;
				}
			}
		}
		int cost = 0;
		memset(v,0,sizeof(v));
		for(int i = 0; i < N;i++)
		{
			if(v[i]) continue;
			v[i] = true;
			int at = n[i];
			int small = dat[i];
			int sum = dat[i];
			int size = 1;
			while(at != i)
			{
				size++;
				small = min(small,dat[at]);
				sum += dat[at];
				v[at] = true;
				at = n[at];
			}
			int normalCost = sum+(size-2)*small;
			int switchCost = (small+m)+sum+(size)*m;
			cost += min(normalCost,switchCost);
		}
		printf("Case %d: %d\n\n",CASE,cost);

	}

}

