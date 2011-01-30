package TopCoder.Hard;
import java.util.*;

import static java.lang.Math.*;

/* TopCoder SRM 404
 * Hard Problem 950 Points: SoftwareCompanies
 * Type: Max Flow
 * Solution: We can brute force over which 12 companies we want to use
 * then just run max flow. The runtime is O(2^n*n^5) = 1,000,000,000
 * but in fact its much better than that is if the max flow always ran
 * on 12 companies.
 */

public class SoftwareCompanies {

	public String[] produceData(String[] names, String[] process, int[] cost, int[] amount, String c1, String c2) {
		boolean[][] legal = new boolean[names.length][names.length];
		String[][] proc = new String[names.length][];
		for(int i = 0; i < names.length;i++)
		{
			proc[i] = process[i].split(" ");
			for(int j = 0; j < names.length;j++)
			{
				for(int k = 0; k < proc[i].length;k++)
				{
					if(names[j].equals(proc[i][k]))
					{
						
						legal[i][j] = true;
					}
				}
			}
		}
		int source = 0;
		int sink = 0;
		for(int i = 0; i < names.length;i++)
		{
			if(names[i].equals(c1)) source = i;
			if(names[i].equals(c2)) sink = i;
		}
		int bestFlow = 0;
		int bestCost = 0;
		String[] best = null;
		for(int i = 0;i< 1 << (names.length-2);i++)
		{
			int N = Integer.bitCount(i);
			ArrayList<Integer> used = new ArrayList<Integer>();
			int at = -1;
			int temp = i;
			while(temp != 0)
			{
				at++;
				if(names[at].equals(c1) || names[at].equals(c2)){
					continue;
				}
				if((temp&1) == 1) used.add(at);
				temp >>= 1;
			}
			used.add(source);
			used.add(sink);
			int c = 0;
			int[][] adj = new int[2*(N+2)][2*(N+2)];
			for(int j = 0;j < used.size();j++)
			{
				c += cost[used.get(j)];
				adj[j][j+N+2] = amount[used.get(j)];
				for(int k = 0; k < used.size();k++)
				{
					if(k == j) continue;
					if(legal[used.get(j)][used.get(k)])
					{
						adj[j+N+2][k] = Integer.MAX_VALUE;		
					}
				}				
			}
			String[] list = new String[N+2];
			for(int j = 0; j < used.size();j++)
			{
				list[j] = names[used.get(j)];
			}
			Arrays.sort(list);
			int maxf = maxf(N,adj.length-1,adj);
			
			
			if(maxf > bestFlow || (maxf == bestFlow && c < bestCost) ||(maxf == bestFlow && c == bestCost && better(list,best)))
			{
				
				bestFlow = maxf;
				bestCost = c;
				best = list;
			}		
		}
		if(bestFlow != 0) return best;
		else return new String[]{};
	}
	private boolean better(String[] names, String[] best) {
		if(best == null) return true;
		for(int i = 0; i < min(names.length,best.length);i++)
		{
			if(names[i].compareTo(best[i]) < 0) return true;
			if(names[i].compareTo(best[i]) > 0) return false;
		}
		if(names.length < best.length) return true;
		else return false;
	}
	
	int start;
	int end;
	int[][] adj;
	public int maxf(int start,int end, int[][] adj)
	{
		this.start = start;
		this.end = end;
		this.adj = adj;
		int maxflow = 0;
		while(true)
		{
			int[] p = bfs();
			if(p==null) break;
			maxflow += update(Integer.MAX_VALUE,end,p);
			
		}
		return maxflow;
	}
	public int update(int f, int at,int[] p)
	{
		if(at == start) return f;
		f = Math.min(f,adj[p[at]][at]);
		f = update(f,p[at],p);
		adj[p[at]][at] -= f;
		adj[at][p[at]] += f;
		return f;
	}
	public int[] bfs()
	{
		LinkedList<Integer> paths = new LinkedList<Integer>();
		int[] p = new int[adj.length];
		Arrays.fill(p,-1);
		paths.add(start);

		while(paths.size() > 0)
		{
			int at = paths.removeFirst();
			for(int i = 0; i < p.length;i++)
			{
				if(p[i] == -1 && adj[at][i] > 0)
				{
					p[i] = at;
					if(i == end)
						return p;
					paths.addLast(i);
				}
			}
		}
		return null;
	}

}
