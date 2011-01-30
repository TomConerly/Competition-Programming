package GCJ.y2009.Round3;
import java.util.*;

/* Google Code Jam 2009 Qual
 * Problem C: Football Team
 * Type: Graph Theory
 * Solution: The graph is planar so the answer is 1-4. 1 is trivial to check. 2 check for odd cycles. We are left with 3-4.
 * Notice that this graph is made up of a bunch of triangles. Fill in one edge (which is on a triangle) with colors, then
 * you can propogate that to all of the triangles that connect to the first triangle using sides of triangles. If there are two
 * seperate groups of triangles they can only share one vertex so you can seperate them. So simply uncolor everything try propogating
 * one group of triangles, if it works then uncolor and try the next.
 */

public class C {
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int zz = 1; zz <= T;zz++)
		{
			N = sc.nextInt();
			System.out.print("Case #"+zz+": ");
			no = new Node[N];
			adj = new TreeSet[N];
			row = new TreeSet[31];
			for(int i = 0; i < 31;i++)
				row[i] = new TreeSet<Node>();
			for(int i = 0; i < N;i++)
			{
				adj[i] = new TreeSet<Node>();
				no[i] = new Node(sc.nextInt(),sc.nextInt(),i);
				row[no[i].y].add(no[i]);
			}
			for(int i = 0; i < N;i++)
			{
				add(no[i], row[no[i].y].higher(no[i]));
				if(no[i].y-1 >= 0)
					add(no[i], row[no[i].y-1].higher(no[i]));
				if(no[i].y+1 <= 30)
					add(no[i], row[no[i].y+1].higher(no[i]));
			}
			int num = 0;
			for(int i = 0; i < N;i++)
				num += adj[i].size();
			if(num==0)
			{
				System.out.println(1);
				continue;
			}

			color = new int[N];
			Arrays.fill(color,-1);
			boolean legal = true;
			for(int i =0; i < N;i++)
			{
				if(color[i] == -1)
					legal &= color(i,0);
			}
			if(legal)
			{
				System.out.println(2);
				continue;
			}
			
			prop = new boolean[N][N];
			legal = true;
			for(int i = 0; i < N;i++)
			{
				for(Node j :adj[i])
				{
					Arrays.fill(color,-1);
					color[i] = 0;
					color[j.id] = 1;
					legal &= p(no[i],j);					
				}
			}			
			if(legal)
			{
				System.out.println(3);
			}else{
				System.out.println(4);
			}

		}
	}	
	private static boolean find(Node node) 
	{		
		int i = node.id;
		for(int j = 0; j < 3;j++){
			boolean good = true;
			for(Node n:adj[i])			
				if(color[n.id] == j)
				{
					good = false;
					break;
				}
			if(good)
			{
				color[i] = j;
				break;
			}
		}
		return color[i] != -1;
	}
	private static boolean p(Node a, Node b) 
	{
		if(prop[a.id][b.id])
			return true;
		prop[a.id][b.id] = true;
		prop[b.id][a.id] = true; 

		if(color[a.id] == color[b.id])
			return false;
		boolean ans = true;
		for(int i = 0; i < N;i++)
		{
			if(adj[a.id].contains(no[i]) && adj[b.id].contains(no[i]))
			{				
				if(color[i] == -1)
				{
					ans &= find(no[i]);
					if(ans == false)
						return false;					
				}else if(color[i] == color[a.id] || color[i] == color[b.id])
				{
					return false;
				}

				ans &= p(a,no[i]);
				ans &= p(b,no[i]);
			}
		}
		return ans;
	}
	static boolean[][] prop;
	private static boolean color(int at, int c) {
		if(color[at] != -1)
			return color[at] == c;
		boolean ret = true;
		color[at] = c;
		for(Node n:adj[at])
		{
			ret &= color(n.id,1-c);
		}
		return ret;		
	}
	private static void add(Node a, Node b) {
		if(a == null || b == null)
			return;
		adj[a.id].add(b);
		adj[b.id].add(a);		
	}
	static int[] color;
	static TreeSet<Node>[] row;
	static Node[] no;
	static TreeSet<Node>[] adj;
	static int N;

	private static class Node implements Comparable<Node>
	{
		int x,y;
		int id;
		Node(int a, int b, int c)
		{
			x = a;
			y = b;
			id = c;
		}
		public int compareTo(Node n) {			
			return Integer.valueOf(x).compareTo(n.x);
		}
	}
}
