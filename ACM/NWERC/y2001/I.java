package ACM.NWERC.y2001;
import java.util.*;

/* ACM NWERC 2001
 * Problem I: Signal Box
 * Type: Simulation
 * Solution: This is basically a DFS. If we get to a option we try the specfied one first always
 * then try the other.
 */

public class I
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int zz = 1; zz <= T;zz++)
		{
			hm = new HashMap<String,Integer>();
			hm2 = new HashMap<Integer,String>();
			st = add(sc.next());
			en = add(sc.next());
			N = sc.nextInt();
			adj = new int[N+1][];

			sc.nextLine();
			for(int i = 0; i < N;i++)
			{
				String[] p = sc.nextLine().split(" ");
				if(p[0].charAt(0) == 'W')
				{
					int I = add(p[1]);
					int F = add(p[2]);
					int M = add(p[3]);
					int P = add(p[4]);
					adj[I] = new int[3];
					adj[I][0] = F;
					adj[I][1] = M;
					adj[I][2] = P;
				}else{
					int I = add(p[1]);
					int F = add(p[2]);
					int B = add(p[3]);
					adj[I] = new int[2];
					adj[I][0] = F;
					adj[I][1] = B;
				}
			}
			P = sc.nextInt();
			dir = new int[N+1];
			Arrays.fill(dir,1);

			sc.nextLine();
			for(int i = 0; i < P;i++)
			{
				String[] p = sc.nextLine().split(" ");
				int X = add(p[1]);
				dir[X] = p[3].charAt(0) == '-'?1:2;
			}
			prev = new int[N+1];
			Arrays.fill(prev,-1);
			found = false;
			recur(st,-1);
			
			System.out.println("Scenario #"+zz+":");
			if(!found)
				System.out.println("NOT POSSIBLE\n");
			else{
				int at = en;
				ArrayList<String> signals = new ArrayList<String>();
				ArrayList<String> points = new ArrayList<String>();
				int p = -1;
				while(true)
				{
					if(adj[at].length==2)
					{
						if(adj[at][0] == prev[at] || adj[at][1] == p)
						{
							signals.add(0,hm2.get(at));
						}
					}
					else
					{
						points.add(0,hm2.get(at)+" "+(dir[at]==1?'-':'+'));
					}
					if(at == st)
						break;
					p = at;
					at = prev[at];
				}
				for(String s: signals)
					System.out.println(s);
				for(String s:points)
					System.out.println(s);
				System.out.println();
			}
		}
	}
	static boolean found;
	public static void recur(int at, int p)
	{
		if(found)
			return;
		if(at == hm.get("XXX"))
			return;
		if(at == en && p == adj[at][0])
		{
			found = true;
			return;
		}

		if(adj[at].length == 2)
		{
			for(int i = 0; i < adj[at].length;i++)
			{
				if(adj[at][i] == p || (p==-1 && i == 0))
					continue;
				if(prev[adj[at][i]] == -1)
				{
					prev[adj[at][i]] = at;
					recur(adj[at][i],at);
				}
			}
		}
		else
		{
			if(adj[at][1] == p || adj[at][2] == p)
			{
				if(prev[adj[at][0]] == -1)
				{
					prev[adj[at][0]] = at;
					recur(adj[at][0],at);
				}
				if(adj[at][1] == p)
					dir[at] = 1;
				else
					dir[at] = 2;
			}else{
				int d = dir[at];
				if(prev[adj[at][d]] == -1)
				{
					prev[adj[at][d]] = at;
					recur(adj[at][d],at);
					if(found)
						return;
				}
				
				d = 3-d;
				if(prev[adj[at][d]] == -1)
				{
					prev[adj[at][d]] = at;
					recur(adj[at][d],at);
					if(found)
					{
						dir[at] = d;
						return;
					}
				}
				
			}
		}
	}
	static int[] prev;
	static int[] dir;
	static int N,P;
	static int st,en;
	static int[][] adj;
	public static int add(String s)
	{
		if(hm.containsKey(s))
			return hm.get(s);

		int add = hm.size();
		hm.put(s,add);
		hm2.put(add,s);
		return add;
	}
	static HashMap<String,Integer> hm;
	static HashMap<Integer,String> hm2;
}
