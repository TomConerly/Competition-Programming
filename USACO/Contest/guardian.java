package USACO.Contest;
/*
ID: theycal2
LANG: JAVA
TASK: guardian
 */
/* USACO Contest November 2008 Silver
 * Guarding the Farm
 * Type: Graph Theory
 * Solution: Run a DFS starting at each node.
 * If it has a lower neighbor mark that neighbor as not
 * as candidate. If it has a high neighbor mark this
 * node as not a candidate. If it has an equal candidate
 * visit there too (recursion is bad because of possible
 * stack overflow).
 */
import java.io.*;
import java.util.*;

class guardian
{
	public static void main (String[] args) throws IOException {
		long start = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("guardian.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("guardian.out")));
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());   
		M = Integer.parseInt(st.nextToken());   
		
		map = new int[N][M];
		visited = new int[N][M];
		System.out.println(System.currentTimeMillis()-start);
		for(int i = 0; i < N;i++)
		{
			Arrays.fill(visited[i],-1);
			/*st = new StringTokenizer(f.readLine());
			for(int j = 0; j < M;j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
			}*/
			String s = f.readLine();
			int soFar = 0;
			int at = 0;
			for(int j = 0; j < s.length();j++)
			{
				if(s.charAt(j) == ' ')
				{
					map[i][at] = soFar;
					at++;
					soFar = 0;
				}else
				{
					soFar = soFar*10 + s.charAt(j)-'0';
				}
			}
			if(at != M)
			{
				map[i][at] = soFar;
			}
		}		
		System.out.println(System.currentTimeMillis()-start);
		
		int count = 0;
		int run = 0;
		for(int i = 0; i < N;i++)
		{
			for(int j = 0; j < M;j++)
			{
				if(dfs2(i,j,run))
				{
					count++;
				}
				run++;
			}
		}
		out.println(count);	

		out.close();        
		System.out.println(System.currentTimeMillis()-start);
		System.exit(0);                     
	}
	static int[][] d = {{-1,-1},{-1,0},{-1,1},	{0,-1},{0,1},	{1,-1},{1,0},{1,1}};
	static int N,M;
	static int[][] map;
	static int[][] visited;

	private static boolean dfs2(int r, int c, int run)
	{
		if(visited[r][c] != -1) return false;
		boolean returnVal = true;		
		LinkedList<Integer> search = new LinkedList<Integer>();
		search.add(r);
		search.add(c);
		
		while(search.size() > 0)
		{			
			r = search.poll();
			c =  search.poll();

			if(r < 0 || r >=N || c <0 || c >= M) continue;

			visited[r][c] = run;		
			for(int i = 0; i < 8;i++)
			{
				int nr = r + d[i][0];
				int nc = c +d[i][1];

				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

				if(map[nr][nc] > map[r][c])	
					returnVal = false;				
				else if(map[nr][nc] < map[r][c]) 
					visited[nr][nc] = run;
				else if(map[nr][nc] == map[r][c])	
				{
					if(visited[nr][nc] == -1)
					{
						search.add(nr);
						search.add(nc);						
						visited[nr][nc] = run;
					}else if(visited[nr][nc] != run && visited[nr][nc] != -1)
					{
						returnVal = false;
					}
				}								

			}
		}
		return returnVal;
	}
	/*
	private static boolean dfs(int r, int c,int run) {
		if(r < 0 || r >=N || c <0 || c >= M) return true;
		if(visited[r][c] == run) return true;
		if(visited[r][c] != -1) return false;
		visited[r][c] = run;

		for(int i = 0; i < 8;i++)
		{
			int nr = r + d[i][0];
			int nc = c +d[i][1];
			if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

			if(map[nr][nc] > map[r][c]) return false;
			else if(map[nr][nc] < map[r][c]) visited[nr][nc] = run;
			else if(map[nr][nc] == map[r][c])
			{
				if(!dfs(nr,nc,run)) return false;
			}			
		}
		return true;
	}*/

}
