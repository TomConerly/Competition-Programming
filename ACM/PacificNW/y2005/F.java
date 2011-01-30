package ACM.PacificNW.y2005;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/* ACM ICPC 2005 Pacific NW Region
 * Problem F: Leaping Lizards
 * Type: Max Flow
 */
public class F {
	static int N = 1000000;
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		int numTest = s.nextInt();
		for(int z = 0; z < numTest;z++)
		{
			int rows = s.nextInt();
			int jump = s.nextInt();
			s.nextLine();
			String[] pillars = new String[rows];
			String[] liz = new String[rows];
			for(int i = 0; i < rows;i++)
			{
				pillars[i] = s.nextLine();
			}
			for(int i = 0; i < rows;i++)
			{
				liz[i] = s.nextLine();
			}
			int cols = pillars[0].length();
			int[][] pillarToNode = new int[rows][cols];
			int[] pillarStr = new int[rows*cols];
			int[] pillarr= new int[rows*cols];
			int[] pillarc= new int[rows*cols];
			int count = 0;
			for(int i = 0; i < rows;i++)
			{
				for(int j = 0; j < cols;j++)
				{
					if(pillars[i].charAt(j) != '0')
					{
						pillarToNode[i][j] = count;
						pillarStr[count] = pillars[i].charAt(j)-'0';
						pillarr[count] = i;
						pillarc[count] = j;
						count++;
					}
				}
			}
			int[][] adj = new int[count*2+2][count*2+2];
			int start = count*2;
			int end = count*2+1;
			int lizCount = 0;
			for(int i = 0; i < rows;i++)
			{
				for(int j = 0; j < cols;j++)
				{
					if(liz[i].charAt(j) == 'L')
					{
						adj[start][2*pillarToNode[i][j]] = 1;
						lizCount++;
					}
				}
			}
			for(int i = 0; i < count;i++)
			{
				adj[2*i][2*i+1] = pillarStr[i];
				int r = pillarr[i];
				int c = pillarc[i];;
				if(r+1 <= jump || rows-r <= jump || c+1 <= jump || cols-c <= jump){
					adj[2*i+1][end] = N;
				}
				for(int j = i+1; j < count;j++)
				{
					int distx = Math.abs(r - pillarr[j]);
					int disty = Math.abs(c - pillarc[j]);
					if(distx*distx + disty*disty <= jump*jump)
					{
						adj[2*i+1][2*j] = N;
						adj[2*j+1][2*i] = N;
					}
				}
			}
			int maxf = maxf(start,end,adj);
			if(lizCount-maxf != 0)
				System.out.println("Case #"+(z+1)+": "+(lizCount-maxf)+ " lizards were left behind.");
			else
				System.out.println("Case #"+(z+1)+": no lizards were left behind.");
		}
	}
	public static int start;
	public static int end;
	public static int[][] adj;
	public static int maxf(int start, int end, int[][] adj)
	{
		F.start = start;
		F.end = end;
		F.adj = adj;
		int maxf = 0;
		while(true)
		{
			
			int[] path = bfs();
			if(path == null) break;
			maxf += update(Integer.MAX_VALUE,end, path);
		}
		return maxf;
	}
	public static int update(int f, int at, int[] path)
	{
		if(at == start) return f;
		f = Math.min(f,adj[path[at]][at]);
		f = update(f,path[at],path);
		adj[path[at]][at] -= f;
		adj[at][path[at]] += f;
		return f;
	}
	public static int[] bfs()
	{
		LinkedList<Integer> q = new LinkedList<Integer>();
		int[] p = new int[adj.length];
		Arrays.fill(p,-1);
		q.add(start);
		
		while(q.size() > 0)
		{
			int at = q.poll();
			for(int i = 0; i < adj.length;i++)
			{
				if(adj[at][i] > 0 && p[i] == -1)
				{
					p[i] = at;
					if(i == end) return p;
					
					q.addLast(i);
				}
			}
		}
		return null;
	}
}
