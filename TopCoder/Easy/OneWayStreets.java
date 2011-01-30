package TopCoder.Easy;/* TopCoder 2008 TCO Round 2 * Easy Problem 250 Points: OneWayStreets * Type: Graph Theory * Solution: Check if there are already any cycles, and check individually if the verticies connected * by a bidirectional road are already bidirectionally connected. */public class OneWayStreets {

	public String canChange(String[] roads) {
		r = roads;		N = roads.length;		for(int i = 0; i < N;i++)		{			for(int j = 0; j < N;j++)			{				if(roads[i].charAt(j) == 'Y' && roads[j].charAt(i) == 'N')				{					if(connected(j,i)) return "NO";				}			}		}		for(int i = 0; i < N;i++)		{			for(int j = 0; j < N;j++)			{				if(roads[i].charAt(j) == 'Y' && roads[j].charAt(i) == 'Y')				{					if(connected(i,j) && connected(j,i)) return "NO";				}			}		}		return "YES";
	}	String[] r;	int N;	boolean[] v;	private boolean connected(int j, int i) {		v = new boolean[N];		dfs(j);		return v[i];	}	private void dfs(int j) {		if(v[j]) return;		v[j] = true;		for(int i = 0; i < N;i++)		{			if(r[j].charAt(i) == 'Y' && r[i].charAt(j) == 'N')			{				dfs(i);			}		}					}

}
