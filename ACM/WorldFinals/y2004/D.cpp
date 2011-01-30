#include <stdio.h>
#include <string>
#include <vector>

using namespace std;

/* 2004 World Finals
 * Problem D: Insecure in Prague
 * Type: Brute Force
 * Difficulty Coding: 3 
 * Algorithmic Difficulty: 2
 * Solution: Try all encrypting values (start and length of hops)
 * run it and see if it matches the result.
 */ 

bool works(string &curstring, vector<bool> oldused, string &s, int oldnleft)
{
/*	if (curstring == "PRAGUE")
	{

	for(int i = 0; i < oldused.size(); i++)
	{
		if (oldused[i])
			printf("true\n");
			else
			printf("false\n");
	}
	printf("\n");

	}
*/

/*
printf("works called with %s\n", curstring.c_str());
	for(int i = 0; i < oldused.size(); i++)
	{
		if (oldused[i])
			printf("true\n");
			else
			printf("false\n");
	}
	printf("nleft: %d\n", oldnleft);
*/

	if (s.empty())
		return true;
	for(int start = 0; start < s.size(); start++)
	{
		if (!oldused[start] && curstring[0] == s[start])
		{
			for(int inc = 0; inc < oldused.size(); inc++)
			{
				int pos = start;
				
				bool good = true;
				
				vector<bool> used = oldused;
				int nleft = oldnleft;
				
				for(int index = 0; index < curstring.size(); index++)
				{
//						if (curstring == "PRAGUE" && inc == 9)
//							printf("index %d is at %d\n", index, pos);
					if (s[pos] != curstring[index])
					{
						good = false;
						break;
					}
					
					if (!used[pos])
					{
						used[pos] = true;
						nleft--;
					}
					
					if (nleft == -1)
					{
						good = false;
						break;
					}
					
					for(int j = 0; j < inc;)
					{
						if (!used[pos])
							j++;
						pos = (pos + 1) % s.size();
						if (nleft == 0)
							break;
					}
					while(used[pos])
					{
						pos = (pos+1) % s.size();
						if (nleft == 0)
							break;
					}
				}
				
				if (good)
					return true;
			}
		}
	}
	
	return false;
}


string findanswer(string s)
{
	int bestsize = 0;
	string beststring = "";
	
	for(int start = 0; start < s.size(); start++)
	{
		for(int inc = 0; inc < s.size(); inc++)
		{
			string curstring;
			vector<bool> used(s.size());
			int nleft = used.size();
			int pos = start;
			for(int i = 0; i <= s.size()/2; i++)
			{
				curstring.push_back(s[pos]);
				used[pos] = true;
				nleft--;
				
				for(int j = 0; j < inc;)
				{
					if (!used[pos])
						j++;
					pos = (pos + 1) % s.size();
					if (nleft == 0)
						break;
				}
				while(used[pos])
				{
					pos = (pos+1) % s.size();
					if (nleft == 0)
						break;
				}
				
				if (i+1 >= bestsize)
				{
					if (works(curstring, used, s, nleft))
					{
//						if (i+1 == 8)
//							printf("good string: %s\n", curstring.c_str());
						if (i+1 == bestsize && curstring != beststring)
							beststring = "";
						else
						{
							bestsize = i+1;
							beststring = curstring;
						}
					}
				}
			}
		}
	}
	
//	printf("best size: %d\n", bestsize);
	
	return beststring;
}

int main()
{
	FILE *in = fopen("D.in", "r");
	for(int casenum = 1;; casenum++)
	{
		char buf[100000];
		fscanf(in, "%s", buf);
		
		if (string(buf) == "X")
			break;
			
		string s = buf;
		
		string res = findanswer(s);
		
		if (res == "")
			printf("Case %d: Codeword not unique\n", casenum);
		else
			printf("Case %d: %s\n", casenum, res.c_str());
	}
}