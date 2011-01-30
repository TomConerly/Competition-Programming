#include <stdio.h>
#include <map>
#include <set>
#include <vector>
#include <algorithm>

/* ACM NWERC 2002
 * Problem E: The Picnic
 * Type: Computational Geometry/DP
 * Solution: Assume the first point is on the convex hull. Do a circular line sweep through the points and fill in a dp table.
 *
 *  dp[second last point used][last point used] = total area we can get for the convex hull using this line going back to the first point.
 *
 * You are always adding a triangle so you know the area. You can also precompute if a triangle contains a point. Special case
 * if the point is on the edge of the triangle or if the line defined by second last point and last point goes through the start.
 *
 * After trying the first point remove it and try again, keep the overall best
 */

using namespace std;


typedef long long lld;
typedef pair<lld,lld> point;
#define mp make_pair

vector<point> pts;

lld dp[200][200];

bool trianglegood[100][100][100];
bool linegood[100][100];

#define printf(...)

int startindex = 0;

lld distsqr(int a, int b)
{
    lld x1 = pts[a].first;
    lld y1 = pts[a].second;
    lld x2 = pts[b].first;
    lld y2 = pts[b].second;
    lld dx = y1-y2;
    lld dy = x1-x2;
    return dx*dx + dy*dy;
}

lld tarea(int a, int b, int c)
{
    lld x1 = pts[a].first;
    lld y1 = pts[a].second;
    lld x2 = pts[b].first;
    lld y2 = pts[b].second;
    lld x3 = pts[c].first;
    lld y3 = pts[c].second;
    return abs(x1*y2 + x2*y3 + x3*y1 - y1*x2 - y2*x3 - y3*x1);
}

bool leftturn(int a, int b, int c)
{
    lld x1 = pts[a].first;
    lld y1 = pts[a].second;
    lld x2 = pts[b].first;
    lld y2 = pts[b].second;
    lld x3 = pts[c].first;
    lld y3 = pts[c].second;
    return (x2-x1)*(y3-y2) - (y2-y1)*(x3-x2) >= 0;
}

int cross(int a, int b, int c)
{
    lld x1 = pts[a].first;
    lld y1 = pts[a].second;
    lld x2 = pts[b].first;
    lld y2 = pts[b].second;
    lld x3 = pts[c].first;
    lld y3 = pts[c].second;
    //lld ans = (x2-x1)*(y3-y2) - (y2-y1)*(x3-x2);
    lld ans = (x2*y3 - x3*y2) - x1*(y3-y2) + y1*(x3-x2);
    if (ans > 0)
        return 1;
    else if (ans < 0)
        return -1;
    return 0;
}

void computegood()
{
    for(int i = 0; i < pts.size(); i++)
    {
        for(int j = 0; j < pts.size(); j++)
        {
            for(int k = 0; k < pts.size(); k++)
            {
                trianglegood[i][j][k] = true;
                for(int l = 0; l < pts.size(); l++)
                {
                    if (l == i || l == j || l == k)
                        continue;
                    int c1 = cross(i,j,l);
                    int c2 = cross(j,k,l);
                    int c3 = cross(k,i,l);
                    if (c1 == c2 && c2 == c3)
                    {
                        trianglegood[i][j][k] = false;
                        break;
                    }
                }
                if (i < j && j < k)
                    printf("trianglegood[%d][%d][%d] is %d\n", i,j,k,trianglegood[i][j][k]);
            }
        }
    }

    for(int i = 0; i < pts.size(); i++)
    {
        for(int j = 0; j < pts.size(); j++)
        {
            linegood[i][j] = true;

            for(int k = 0; k < pts.size(); k++)
            {
                if (k == i || k == j)
                    continue;
                if (cross(i,j,k) == 0)
                    linegood[i][j] = false;
            }
        }
    }
}


lld calc(int secondlast, int last)
{
    printf("calc(%d,%d) called\n", secondlast, last);
    if (last == 0)
        return 0;

    if (dp[secondlast][last] == -1)
    {
        lld res = 0;

        for(int i = 1; i < pts.size(); i++)
        {
            if (i == last || i == secondlast)
                continue;
            printf("calc(%d,%d) i is %d not fully filtered\n", secondlast, last, i);
            if (!trianglegood[0 + startindex][last + startindex][i + startindex])
                continue;
            printf("calc(%d,%d) i is %d not filtered\n", secondlast, last, i);

            if (cross(secondlast, last, i) < 0)
                continue;
            printf("calc(%d,%d) i is %d angle right\n", secondlast, last, i);

            if (cross(0, secondlast, last) == 0)
            { // on start line
                if (cross(0, last, i) > 0 ||
                    (cross(0,last,i) == 0 && distsqr(0, i) > distsqr(0, last)))
                {
                    lld newarea = calc(last, i);
                    res = max(res, newarea + tarea(last, i, 0));
                }
            }
            else
            {
                if (cross(0, last, i) > 0 && linegood[0 + startindex][last + startindex])
                {
                    lld newarea = calc(last, i);
                    res = max(res, newarea + tarea(last, i, 0));
                }
            }

        }

        //res = max(res, tarea(secondlast, last, 0));

        dp[secondlast][last] = res;
    }
    return dp[secondlast][last];
}

lld get2area()
{
    for(int i = 0; i < 200; i++)
    {
        for(int j = 0; j < 200; j++)
            dp[i][j] = -1;
    }

    lld res = 0;
    for(int i = 1; i < pts.size(); i++)
    {
        printf("i is %d, pts.size() is %d\n", i, pts.size());
        res = max(res, calc(0, i));
    }
    return res;
}

int main()
{
    int ncases;
    scanf("%d", &ncases);

    for(int casenum = 0; casenum < ncases; casenum++)
    {
        int npts;
        scanf("%d", &npts);

        for(int i = 0; i < npts; i++)
        {
            int a,b;
            scanf("%d %d", &a, &b);
            pts.push_back(mp(a,b));
        }

        sort(pts.begin(), pts.end());

        computegood();

        printf("trianglegood[0][5][3] is %d\n", trianglegood[0][5][3]);

        lld best2area = 0;

        startindex = 0;

        printf("got here\n");
        fflush(stdout);

        while(pts.size())
        {
            lld newarea = get2area();
            if (newarea == 18)
                printf("with %d points left, 2area is 18\n", pts.size());
            best2area = max(best2area, newarea);

            pts.erase(pts.begin());
            startindex++;
        }
        fprintf(stdout, "%.01lf\n", double(best2area) / 2.0);
    }
}


