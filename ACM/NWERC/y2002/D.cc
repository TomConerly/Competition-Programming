#include <stdio.h>
#include <vector>
#include <set>
#include <map>
#include <algorithm>

/* ACM NWERC 2002
 * Problem D: Floors
 * Type: Greedy
 * Solution: Find a valid cut then recurse on the two seperate boxes, if you can't go any further return the size of the box.
 */

using namespace std;

#define mp make_pair

typedef long long lld;

typedef pair<int,int> point;

lld max_area;

set<lld> xvalset;
set<lld> yvalset;
vector<lld> xvals;
vector<lld> yvals;

vector<set<pair<int,int> > > xranges_raw;
vector<set<pair<int,int> > > yranges_raw;
// x ranges indexed by y, gives x ranges based on y
vector<vector<pair<int,int> > > xranges;
vector<vector<pair<int,int> > > yranges;
vector<pair<point,point> > tiles;

void convert_ranges(set<pair<int,int> > &r1, vector<pair<int,int> > &r2)
{
    /*
    printf("\n\n\nconverting range: ");
    for(set<pair<int,int> >::iterator iter = r1.begin(); iter != r1.end(); iter++)
    {
        printf("(%d %d) ", iter->first, iter->second);
    }
    printf("\n");
    */
    
    vector<pair<int,bool> > events;

    for(set<pair<int,int> >::iterator iter = r1.begin(); iter != r1.end(); iter++)
    {
        events.push_back(make_pair(iter->first, false));
        events.push_back(make_pair(iter->second, true));
    }

    sort(events.begin(), events.end());

    int count = 0;

    int startpt = -1;
    for(int i = 0; i < events.size(); i++)
    {
        if (!events[i].second)
        {
            if (count == 0)
                startpt = events[i].first;
            count++;
        }
        else
        {
            count--;
            if (count == 0)
            {
                r2.push_back(mp(startpt, events[i].first));
            }
        }
    }

    /*
    printf("result\n");

    for(vector<pair<int,int> >::iterator iter = r2.begin(); iter != r2.end(); iter++)
    {
        printf("(%d %d) ", iter->first, iter->second);
    }

    printf("done\n");
    printf("\n\n\n");
    */
}

bool range_lookup(vector<pair<int,int> > &range, int start, int end)
{
    int index = int(lower_bound(range.begin(), range.end(), make_pair(start, 10000000)) - range.begin()) - 1;

    //printf("range lookup, index=%d,  start=%d, end=%d\n", index, start, end);

    //printf("range: %d to %d\n", range[0].first, range[0].second);

    if (index < 0 || index >= (int)range.size())
        return false;

    if (start >= range[index].first &&
        end <= range[index].second)
        return true;

    return false;
}


void recur_x(int x1, int y1, int x2, int y2);
void recur_y(int x1, int y1, int x2, int y2);

void recur_x(int x1, int y1, int x2, int y2)
{ // move left to right
    //printf("recur_x %d %d %d %d\n", x1, y1, x2, y2);
    int last_split = x1;

    for(int i = x1+1; i < x2; i++)
    {
        if (range_lookup(yranges[i], y1, y2))
        {
            //printf("x range found at i=%d\n", i);
            recur_y(last_split, y1, i, y2);
            last_split = i;
        }
    }

    if (last_split != x1)
        recur_y(last_split, y1, x2, y2);
    else
    {
        //printf("xfound %d %d, %d %d\n", x1, y1, x2, y2);
        //printf("vals: %lld %lld, %lld %lld\n", xvals[x1], yvals[y1], xvals[x2], yvals[y2]);
        max_area = max(max_area,
                        (xvals[x2]-xvals[x1])*(yvals[y2]-yvals[y1]));
        //printf("max area: %lld\n", max_area);
    }
}

void recur_y(int x1, int y1, int x2, int y2)
{ // move bottom to top
    //printf("recur_y %d %d %d %d\n", x1, y1, x2, y2);
    int last_split = y1;

    for(int i = y1+1; i < y2; i++)
    {
        if (range_lookup(xranges[i], x1, x2))
        {
            //printf("y range found at i=%d\n", i);
            recur_x(x1, last_split, x2, i);
            last_split = i;
        }
    }

    if (last_split != y1)
        recur_x(x1, last_split, x2, y2);
    else
    {
        //printf("yfound %d %d, %d %d\n", x1, y1, x2, y2);
        //printf("vals: %lld %lld, %lld %lld\n", xvals[x1], yvals[y1], xvals[x2], yvals[y2]);
        max_area = max(max_area,
                        (xvals[x2]-xvals[x1])*(yvals[y2]-yvals[y1]));
    }
}


int main()
{
    int ncases;
    scanf("%d", &ncases);
    
    for(int casenum = 1; casenum <= ncases; casenum++)
    {
        xvalset.clear();
        yvalset.clear();
        xvals.clear();
        yvals.clear();
        xranges.clear();
        yranges.clear();
        xranges_raw.clear();
        yranges_raw.clear();
        tiles.clear();
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

        for(set<lld>::iterator iter = xvalset.begin(); iter != xvalset.end(); iter++)
            xvals.push_back(*iter);
        
        for(set<lld>::iterator iter = yvalset.begin(); iter != yvalset.end(); iter++)
            yvals.push_back(*iter);


        /*
        printf("xvals: ");
        for(int i = 0; i < xvals.size(); i++)
            printf("%lld ", xvals[i]);
        printf("\n");
        printf("yvals: ");
        for(int i = 0; i < yvals.size(); i++)
            printf("%lld ", yvals[i]);
        printf("\n");
        */

        yranges_raw.resize(xvals.size());
        xranges_raw.resize(yvals.size());
        yranges.resize(xvals.size());
        xranges.resize(yvals.size());

        for(int i = 0; i < tiles.size(); i++)
        {
            int xstart = lower_bound(xvals.begin(), xvals.end(), tiles[i].first.first) - xvals.begin();
            int xend = lower_bound(xvals.begin(), xvals.end(), tiles[i].second.first) - xvals.begin();

            int ystart = lower_bound(yvals.begin(), yvals.end(), tiles[i].first.second) - yvals.begin();
            int yend = lower_bound(yvals.begin(), yvals.end(), tiles[i].second.second) - yvals.begin();

            xranges_raw[ystart].insert(make_pair(xstart,xend));
            xranges_raw[yend].insert(make_pair(xstart,xend));

            yranges_raw[xstart].insert(make_pair(ystart,yend));
            yranges_raw[xend].insert(make_pair(ystart,yend));
        }

        for(int i = 0; i < xranges_raw.size(); i++)
            convert_ranges(xranges_raw[i], xranges[i]);
        for(int i = 0; i < yranges_raw.size(); i++)
            convert_ranges(yranges_raw[i], yranges[i]);
/*
        printf("x range at 1:\n");
        for(int i = 0; i < xranges[1].size(); i++)
        {
            printf("(%d %d) ", xranges[1][i].first, xranges[1][i].second);
        }
*/
        max_area = 0;
        recur_x(0, 0, xvals.size()-1, yvals.size()-1);
        lld area1 = max_area;

        //printf("\n\nMIDDLE\n\n\n");

        max_area = 0;
        recur_y(0, 0, xvals.size()-1, yvals.size()-1);
        lld area2 = max_area;

        printf("%lld\n", min(area1,area2));
    }
}

