#include <stdio.h>
#include <vector>
#include <algorithm>
#include <numeric>
#include <string>

/* ACM ICPC 2004 Pacific NW Region
 * Problem J: A Card Trick
 * Type: Simulation
 * Solution: Simulation (Alan coded).
 */

using namespace std;

char *ranks = "A23456789_JQK";
char *suits = "CDHS";


int rank_ch_to_val(char c)
{
    for(int i = 0; i < 13; i++)
        if (ranks[i] == c)
            return i;
}

int suit_ch_to_val(char c)
{
    for(int i = 0; i < 4; i++)
        if (suits[i] == c)
            return i;
}


pair<int,int> str_to_pair(string s)
{
    if (s.length() == 3)
        return make_pair(9, suit_ch_to_val(s[2]));
    return make_pair(rank_ch_to_val(s[0]), suit_ch_to_val(s[1]));
}

string pair_to_str(pair<int,int> p)
{
    string res;

    if (p.first == 9)
        res += "10";
    else
        res.push_back(ranks[p.first]);

    res.push_back(suits[p.second]);

    return res;
}


bool good(vector<pair<int,int> > v)
{
    int val = v[1].first;

    pair<int,int> mval = min(v[2], min(v[3], v[4]));

    int a;
    if (v[2] == mval)
        a = 1;
    else if (v[3] == mval)
        a = 2;
    else if (v[4] == mval)
        a = 3;


    val += a;

    if (a == 1 && v[3] > v[4])
        val += 3;
    else if (a == 2 && v[2] > v[4])
        val += 3;
    else if (a == 3 && v[2] > v[3])
        val += 3;

    return v[0].second == v[1].second &&
        v[0].first == val % 13;
}

int main()
{
    int numcases;
    scanf("%d", &numcases);

    for(int casenum = 1; casenum <= numcases; casenum++)
    {
        char buf[5][100];
        scanf("%s %s %s %s %s", buf[0], buf[1], buf[2], buf[3], buf[4]);

        vector<pair<int,int> > perm;

        for(int i = 0; i < 5; i++)
        {
            perm.push_back(str_to_pair(buf[i]));
        }

        sort(perm.begin(), perm.end());

        do
        {
            if (good(perm))
            {
                printf("Problem %d: ", casenum);
                for(int i = 0; i < 5; i++)
                {
                    printf("%s ", pair_to_str(perm[i]).c_str());
                }
                printf("\n"); 
                break;
            }
        } while(next_permutation(perm.begin(), perm.end()));
    }
}

