#include <stdio.h>

/* ACM Pacific Northwest 2008
 * Problem A: Tetrahedral Stacks of Cannonballs
 * Type: Math
 * Solution: Just compute the formula. (Coded by Alan)
 */

typedef long long lld;

int main()
{
    int ncases;
    scanf("%d", &ncases);
    for(int casenum = 1; casenum <= ncases; casenum++)
    {
        lld n;
        scanf("%lld", &n);

        lld ans = 0;
        for(lld i = 1; i <= n; i++)
        {
            ans += i*(i+1)/2;
        }
        printf("%d: %lld %lld\n", casenum, n, ans);
    }
}

