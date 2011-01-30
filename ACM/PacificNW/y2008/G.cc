#include <stdio.h>
#include <string>
#include <set>
#include <map>
#include <vector>
#include <stdlib.h>

/* ACM Pacific Northwest 2008
 * Problem G: Symbolic Logic Mechanization
 * Type: Simulation
 * Solution: Just simulate it. (Coded by Alan).
 */

using namespace std;

char buf[10000];
int ind;

struct value
{
    value(char t, value *v1, value *v2) { type = t; arg1 = v1; arg2 = v2; }
    char type;
    value *arg1;
    value *arg2;
};

value *root;

string error;

set<char> seenvars;

map<char,bool> varmap;

value *parse(void)
{
    if (buf[ind] == 0)
    {
        error = "insufficient operands";
        return NULL;
    }

    value *arg1 = NULL;
    value *arg2 = NULL;
    char c = buf[ind];
    ind++;
    switch(c)
    {
        case 'C':
        case 'K':
        case 'A':
        case 'D':
        case 'E':
        case 'J':
            arg1 = parse();
            if (arg1 == NULL)
                return NULL;
            arg2 = parse();
            if (arg2 == NULL)
                return NULL;
            break;

        case 'N':
            arg1 = parse();
            if (arg1 == NULL)
                return NULL;
            break;

        default:
            if (c >= 'a' && c <= 'z')
            {
                seenvars.insert(c);
            }
            else
            {
                error = "invalid character";
                return NULL;
            }
    }

    return new value(c, arg1, arg2);
}

bool eval(value *v)
{
    bool b1,b2;

    if (!(v->type >= 'a' && v->type <= 'z'))
    {
        b1 = eval(v->arg1);
        if (v->type != 'N')
            b2 = eval(v->arg2);
    }

    switch(v->type)
    {
        case 'C':
            return !b1 || b2;
        case 'N':
            return !b1;
        case 'K':
            return b1 && b2;
        case 'A':
            return b1 || b2;
        case 'D':
            return !(b1 && b2);
        case 'E':
            return b1 == b2;
        case 'J':
            return b1 != b2;

        default:
            //printf("varmap.size(): %d\n", varmap.size());
            return varmap[v->type];
    }
    return false;
}


int main()
{
    while(1)
    {
        fgets(buf, 10000, stdin);
        if (buf[0] == 0)
            break;
        
        if (buf[strlen(buf)-1] == '\n')
            buf[strlen(buf)-1] = '\0';

        if (buf[0] == 0)
            break;

        error.clear();
        ind = 0;
        seenvars.clear();
        root = parse();

        if (error.empty() && buf[ind] != 0)
        {
            error = "extraneous text";
        }

        if (error.size())
        {
            printf("%s is invalid: %s\n", buf, error.c_str());
        }
        else
        {
            vector<char> vars;
            for(set<char>::iterator iter = seenvars.begin(); iter !=
                seenvars.end(); iter++)
            {
                vars.push_back(*iter);
            }

            bool seentrue = false;
            bool seenfalse = false;

            //printf("vars size: %d\n", vars.size());

            for(int i = 0; i < (1<<(vars.size())); i++)
            {
                varmap.clear();

                for(int j = 0; j < vars.size(); j++)
                {
                    if (i & (1<<j))
                    {
                        //printf("setting true\n");
                        varmap[vars[j]] = true;
                    }
                    else
                    {
                        //printf("setting false\n");
                        varmap[vars[j]] = false;
                    }
                }

            //    for(map<char,bool>::iterator iter = varmap.begin(); iter !=
             //       varmap.end(); iter++)
             //       printf("%d: varmap[%c] is %d\n", i, iter->first, iter->second);

                bool r = eval(root);
           //     if (r)
             //       printf("t");
             //   else
             //       printf("f");
                if (r)
                    seentrue = true;
                else
                    seenfalse = true;
            }
            //printf("\n");
            
            string s;
            if (seentrue && seenfalse)
                s = "contingent";
            else if (seentrue)
                s = "tautology";
            else
                s = "contradiction";

            printf("%s is valid: %s\n", buf, s.c_str());
        }
    }
}

