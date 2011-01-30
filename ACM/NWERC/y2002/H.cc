#include <stdio.h>
#include <map>
#include <set>
#include <vector>
#include <algorithm>

/* ACM NWERC 2002
 * Problem H: Input
 * Type: Computational Geometry
 * Solution: Coded by Alan
 */

using namespace std;
#define mp make_pair

typedef pair<int,int> point;
vector<pair<point,point> > tiles;
set<int> xvalset;
set<int> yvalset;
vector<int> xvals;
vector<int> yvals;
vector<vector<bool> > taken;

bool overlap;
bool outside;
bool nottaken;

int main()
{
    int ncases;
    scanf("%d", &ncases);

    for(int casenum = 1; casenum <= ncases; casenum++)
    {
        tiles.clear();
        xvalset.clear();
        yvalset.clear();
        xvals.clear();
        yvals.clear();
        taken.clear();
        overlap = false;
        outside = false;
        nottaken = false;

        int w,h;
        scanf("%d %d", &w, &h);

        int n;
        scanf("%d", &n);

        xvalset.insert(0);
        yvalset.insert(0);
        xvalset.insert(w);
        yvalset.insert(h);

        for(int i = 0; i < n; i++)
        {
            int x1,y1,x2,y2;
            scanf("%d %d %d %d", &x1, &y1, &x2, &y2);

            xvalset.insert(x1);
            xvalset.insert(x2);
            yvalset.insert(y1);
            yvalset.insert(y2);

            tiles.push_back(mp(mp(x1,y1),mp(x2,y2)));
        }

        for(set<int>::iterator iter = xvalset.begin(); iter != xvalset.end(); iter++)
        {
            xvals.push_back(*iter);
        }
        for(set<int>::iterator iter = yvalset.begin(); iter != yvalset.end(); iter++)
        {
            yvals.push_back(*iter);
        }

        taken.resize(xvals.size(), vector<bool>(yvals.size()));

        for(int i = 0; i < tiles.size(); i++)
        {
            int xstart = lower_bound(xvals.begin(), xvals.end(), tiles[i].first.first) - xvals.begin();
            int xend = lower_bound(xvals.begin(), xvals.end(), tiles[i].second.first) - xvals.begin();

            int ystart = lower_bound(yvals.begin(), yvals.end(), tiles[i].first.second) - yvals.begin();
            int yend = lower_bound(yvals.begin(), yvals.end(), tiles[i].second.second) - yvals.begin();

            for(int ix = xstart; ix < xend; ix++)
            {
                for(int iy = ystart; iy < yend; iy++)
                {
                    if (taken[ix][iy])
                        overlap = true;
                    taken[ix][iy] = true;
                }
            }

            if (tiles[i].first.first < 0 || tiles[i].first.second < 0 ||
                tiles[i].second.first > w || tiles[i].second.second > h)
            {
                outside = true;
            }
        }

        int xstart = lower_bound(xvals.begin(), xvals.end(), 0) - xvals.begin();
        int xend = lower_bound(xvals.begin(), xvals.end(), w) - xvals.begin();
        int ystart = lower_bound(yvals.begin(), yvals.end(), 0) - yvals.begin();
        int yend = lower_bound(yvals.begin(), yvals.end(), h) - yvals.begin();

        for(int i = xstart; i < xend; i++)
        {
            for(int j = ystart; j < yend; j++)
            {
                if (!taken[i][j])
                {
                    nottaken = true;
                }
            }
        }

        if (overlap)
            printf("NONDISJOINT\n");
        else if (outside)
            printf("NONCONTAINED\n");
        else if (nottaken)
            printf("NONCOVERING\n");
        else
            printf("OK\n");
    }
}

