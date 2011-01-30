/* 2002 World Finals
 * Problem G: Partitions (Passed Online Judge)
 * Type: Simulation
 * Difficulty Coding: 3 
 * Algorithmic Difficulty: 2
 * Solution: The infimum is the union and the supremum is the intersection of edges in the two.
 * Except the supremum could be left with edges that are not legal (both end points
 * are not on a line) so remove those edges.
 */    
#include <stdio.h>
#include <math.h>
#include <queue>
#include <algorithm>
#include <string>

using namespace std;

int W,H;
vector<string> in1;
vector<string> in2;
vector<string> out1;
vector<string> out2;


bool horiz(int i, int j)
{	
	if(j-1 >= 0 && out2[i][j-1] == ' ') return false;
	if(j+1 < 2*W+1 && out2[i][j+1] == ' ') return false;
	return true;	
}
bool vert(int i, int j)
{
	if(i > 0 && out2[i][j] == ' ') return false;
	if(i+1 < H+1 && out2[i+1][j] == ' ') return false;
	return true;	
}
void infi(void)
{
	out1.clear();
	for(int i = 0; i < H+1;i++)
	{
		string s("");
		for(int j = 0; j < 2*W+1;j++)
		{
			if(in1[i][j] == '|' || in2[i][j] == '|')
				s += "|";
			else if(in1[i][j] == '_' || in2[i][j] == '_')
				s += "_";
			else 
				s += " ";
		}
		out1.push_back(s);
	}
}
void supr(void)
{
	out2.clear();
	for(int i = 0; i < H+1;i++)
	{
		string s("");
		for(int j = 0; j < 2*W+1;j++)
		{
			if(in1[i][j] == '|' && in2[i][j] == '|')
				s += "|";
			else if(in1[i][j] == '_' && in2[i][j] == '_')
				s += "_";
			else 
				s += " ";
		}
		out2.push_back(s);
	}
	bool update = true;
	while(update)
	{
		update = false;
		for(int i = 0; i < H+1;i++)
		{
			for(int j = 0; j < 2*W+1;j++)
			{
				if(out2[i][j] == '|')
				{
					bool top = horiz(i-1,j) || (i-1 >= 0 && out2[i-1][j] == '|');
					bool bot = horiz(i,j) || (i+1 < H+1 && out2[i+1][j] == '|');
					if(!top || !bot)
					{
						out2[i][j] = ' ';
						update = true;
					}
				}
				else if(out2[i][j] == '_')
				{
					bool left = vert(i,j-1) || (j-2 >= 0 && out2[i][j-2] == '_');
					bool right = vert(i,j+1) || (j+2 < 2*W+1 && out2[i][j+2] == '_');
					if(!left || !right)
					{
						out2[i][j] = ' ';
						update = true;
					}
				}
			}
		}
	}
}


int main()
{	
	char buffer[1000];
	for(int CASE=1;;CASE++)
	{
		fgets(buffer,1000,stdin);		
		sscanf(buffer,"%d %d",&W,&H);
		if(W == 0 && H == 0) break;
		
		in1.clear();
		in2.clear();
		for(int i = 0; i < H+1;i++)
		{
			fgets(buffer,1000,stdin);
			string s(buffer);
			in1.push_back(s.substr(0,2*W+1));
			in2.push_back(s.substr(2*W+2,2*W+1));
		}
		infi();
		supr();
		printf("Case %d:\n",CASE);
		for(int i = 0; i < out1.size();i++)
		{
			printf("%s %s\n",out1[i].c_str(),out2[i].c_str());
		}
		printf("\n");
		
		
	}
}