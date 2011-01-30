#include <stdio.h>
#include <vector>
#include <set>
#include <map>
#include <algorithm>
#include <numeric>

/* ACM ICPC 2004 Pacific NW Region
 * Problem G: Jill's Tour Paths
 * Type: Graph Theory
 * Solution: Brute force all paths. (Coded by Alan)
 */

using namespace std;

int sv;
int dv;

vector<map<int,int> > graph;

vector<pair<int,string> > sols;

int curdist;
string sol;

vector<bool> taken;

string tostr(int n)
{
    char buf[100];
    sprintf(buf, "%d", n);
    return buf;
}


void rec(int dist, int v)
{
    if (dist < 0)
        return;

    if (v == dv)
    {
        sols.push_back(make_pair(curdist, sol));
        return;
    }

    for(map<int,int>::iterator iter = graph[v].begin(); iter != graph[v].end();
                                            iter++)
    {
        if (taken[iter->first])
            continue;
        curdist += iter->second;
        dist -= iter->second;
        taken[iter->first] = true;
        string str = tostr(iter->first+1);
        string oldsol = sol;
        sol += " " + str;

        rec(dist, iter->first);

        sol = oldsol;
        taken[iter->first] = false;
        dist += iter->second;
        curdist -= iter->second;
    }
}

int main()
{
    bool firsttry = true;
    for(int casenum = 1;; casenum++)
    {
        int nv;
        scanf("%d", &nv);

        if (nv == -1)
            break;

        int nr;
        scanf("%d", &nr);

        graph.clear();
        graph.resize(nv);

        for(int i = 0; i < nr; i++)
        {
            int a,b,c;
            scanf("%d %d %d", &a, &b, &c);
            a--;
            b--;
            graph[a][b] = c;
            graph[b][a] = c;
        }


        scanf("%d %d", &sv, &dv);
        sv--;
        dv--;

        int maxdist;
        scanf("%d", &maxdist);

        maxdist = min(maxdist, 9999);
        
        sols.clear();
        taken.clear();
        taken.resize(nv);
        taken[sv] = true;
        
        curdist = 0;
        sol.clear();
        sol += tostr(sv+1);

        rec(maxdist, sv);

        sort(sols.begin(), sols.end());
        
        if (!firsttry)
        {
            printf("\n");
        }
        firsttry = false;

        printf("Case %d:\n", casenum);
        for(int i = 0; i < sols.size(); i++)
        {
            printf(" %d: ", sols[i].first);

            printf("%s", sols[i].second.c_str());
            printf("\n");
        }
    }
}

