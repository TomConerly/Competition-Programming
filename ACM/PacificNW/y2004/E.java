package ACM.PacificNW.y2004;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


/* ACM ICPC 2004 Pacific NW Region
 * Problem E: Going Home
 * Type: Assignment Problem
 */
public class E {
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		while(true)
		{
			int n = s.nextInt();//rows
			int m = s.nextInt();//cols
			if(n== 0 && m == 0) break;
			s.nextLine();
			ArrayList<Integer> men = new ArrayList<Integer>();
			ArrayList<Integer> houses = new ArrayList<Integer>();
			for(int i = 0;i<n;i++)
			{
				String str = s.nextLine();
				for(int j = 0; j < m;j++)
				{
					
					if(str.charAt(j) == 'H') houses.add((j<<16) | i);
					if(str.charAt(j) == 'm') men.add((j<<16) | i);
				}
			}
			int[][] cost = new int[men.size()][houses.size()];
			for(int i = 0; i < men.size();i++)
			{
				int ma = men.get(i);
				for(int j = 0; j < houses.size();j++)
				{
					int h = houses.get(j);
					cost[i][j] = Math.abs((ma>>>16) - (h>>>16))+Math.abs((ma&0xFFFF) - (h&0xFFFF));
				}				
			}
			
			//ENDIO
			System.out.println(assign(cost));
		}
	}
	public static int assign(int[][] cost)
	{
		{
			int[][] adj = new int[cost.length][cost.length];
			//make each row and column have one zero
			for(int i = 0; i < adj.length;i++)
			{
				int min = Integer.MAX_VALUE;
				for(int j = 0; j < adj.length;j++)
					min = Math.min(min,cost[i][j]);				
				for(int j = 0; j < adj.length;j++)			
					adj[i][j] = cost[i][j]- min;				
			}
			for(int j = 0; j < adj.length;j++)
			{
				int min = Integer.MAX_VALUE;
				for(int i = 0; i < adj.length;i++)				
					min = Math.min(min,adj[i][j]);				
				for(int i = 0; i < adj.length;i++)			
					adj[i][j] -= min;				
			}
			maxfSetup(adj);
			int maxf = 0;
			while(true)
			{
				maxf += maxf();
				if(maxf == adj.length) break;
				
				LinkedList<Integer> checking = new LinkedList<Integer>();
				for(int i = 0; i < adj.length;i++) checking.add(i);
				boolean[] inT = new boolean[adj.length*2];
				
				for(int i = 0; i < adj.length;i++)
				{
					for(int j = 0; j < adj.length;j++)
					{
						if(flow[i][j+adj.length] == 0 && adj[i][j] == 0)
						{
							checking.remove(new Integer(i));
						}
					}
				}
				//bfs
				while(checking.size() > 0)
				{
					int at = checking.removeFirst();
					inT[at] = true;
					//in L
					if( at < adj.length)
					{
						for(int j = 0; j < adj.length;j++)
						{
							if(inT[j+adj.length]) continue;
							if(flow[at][j+adj.length]== 1 && adj[at][j] == 0)
							{
								checking.add(j+adj.length);
							}
						}
					}
					//in R
					else
					{
						for(int i = 0; i < adj.length;i++)
						{
							if(inT[i]) continue;
							if(flow[i][at] == 0 && adj[i][at-adj.length] == 0)
							{
								checking.add(i);
							}
						}
					}
				}
				boolean[] minCover = new boolean[adj.length*2];
				for(int i = 0; i < adj.length;i++)
				{
					minCover[i] = !inT[i];
				}
				for(int i = adj.length; i < 2*adj.length;i++)
				{
					minCover[i] = inT[i];
				}
				int delta = Integer.MAX_VALUE;
				for(int i = 0; i < adj.length;i++)
				{
					for(int j = 0; j <adj.length;j++)
					{
						if(!minCover[i] && !minCover[j+adj.length])
						{
							delta = Math.min(delta,adj[i][j]);
						}
					}
				}
					
				for(int i = 0; i < adj.length;i++)
				{
					for(int j = 0; j <adj.length;j++)
					{
						if(!minCover[i] && !minCover[j+adj.length])
						{
							adj[i][j] -= delta;
							if(adj[i][j] == 0)
								flow[i][j+adj.length]=1;
						}
						if(minCover[i] && minCover[j+adj.length])
						{
							adj[i][j] += delta;
							if(adj[i][j] == delta)
							{
								if(flow[i][j+adj.length] == 0)
								{
									flow[start][i] = 1;
									flow[j+adj.length][end] = 1;
									flow[i][start] = 0;
									flow[end][j+adj.length] = 0;
								}
								flow[i][j+adj.length] = 0;
								flow[j+adj.length][i] = 0;								
							}
						}
					}
				}	
			}
			int c = 0;
			for(int i = 0; i < adj.length;i++)
			{
				for(int j = 0; j < adj.length;j++)
				{
					if(flow[i][j+adj.length] == 0 && adj[i][j] == 0)
					{
						c += cost[i][j];
					}
				}
			}
			return c;
		}
	}
	static int[][] flow;
	static int start;
	static int end;
	public static void maxfSetup(int[][] adj)
	{
		start = adj.length*2;
		end = adj.length*2+1;
		flow = new int[2*adj.length+2][2*adj.length+2];
		for(int i = 0; i < adj.length;i++)	
		{
			for(int j = 0; j < adj.length;j++)
			{
				if(adj[i][j] == 0)
					flow[i][j+adj.length]=1; 
			}
			flow[start][i] = 1;
			flow[i+adj.length][end] = 1;
		}
	}
	public static int maxf()
	{
		int maxf = 0;
		while(true)
		{
			int[] p = bfs();
			if(p == null) break;
			maxf += update(Integer.MAX_VALUE,end,p);
		}
		return maxf;
	}
	public static int update(int f, int at, int[] p)
	{
		if(at == start) return f;
		f = update(Math.min(f,flow[p[at]][at]),p[at],p);
		flow[p[at]][at] -= f;
		flow[at][p[at]] += f;
		return f;
	}
	public static int[] bfs()
	{
		int[] p = new int[flow.length];
		for(int i = 0; i < p.length;i++)p[i]=-1;
		LinkedList<Integer> paths = new LinkedList<Integer>();
		paths.add(start);
		
		while(paths.size() > 0)
		{
			int at= paths.removeFirst();
			for(int i = 0; i < p.length;i++)
			{
				if(p[i] == -1 && flow[at][i] > 0)
				{
					p[i] = at;
					if(i == end) return p;
					paths.addLast(i);
				}
			}
		}
		return null;
	}
}