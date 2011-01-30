package ACM.NWERC.y2003;
import java.util.*;


/* ACM NWERC 2003
 * Problem G: Sightseeing tour
 * Type: Graph Theory
 * Solution: Take all the undirected edges and arbitrarily direct them. Now if there is someone with too many incoming edges,
 * find a path that uses all undirected edges backwards, and ends at a vertex with too many outgoing edges, flip the edges on the path.
 * Do the same thing for someone with too many outgoing edges. If there is no such "augmenting path" then either all in degree = out degree
 * and there exists a euler tour, or if in degree != out degree there is no euler tour.
 */

public class G
{
    @SuppressWarnings("unchecked")
	public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int zz = 1; zz <=T;zz++)
        {
            N = sc.nextInt();
            M = sc.nextInt();
            adj = new ArrayList[N];
            for(int i = 0; i < N;i++)
                adj[i] = new ArrayList<Edge>();
            int id = 0;
            in = new int[N];
            out = new int[N];
            for(int i = 0; i < M;i++)
            {
                int x = sc.nextInt()-1;
                int y = sc.nextInt()-1;
                int s = sc.nextInt();
                if(s == 1)
                {
                    adj[x].add(new Edge(x,y,-1,0));
                    in[y]++;
                    out[x]++;
                }
                else
                {
                    adj[x].add(new Edge(x,y,id,1));
                    adj[y].add(new Edge(y,x,id,0));
                    in[x]++;
                    out[y]++;
                    id++;
                }
            }
            boolean ans = true;
            boolean legal = false;
            for(int i = 0; i < N;i++)
                if((in[i]+out[i]) %2 != 0)
                {
                    ans = false;
                    legal = false;
                }
            if(ans)
            {
            dir = new int[id];
            boolean update = true;
            while(update == true)
            {
                update = false;
                legal = true;
                for(int i = 0; i < N;i++)
                {
                    if(in[i] != out[i])
                        legal = false;
                    if(in[i] >= out[i]+2)
                        if(augment2(i))
                        {
                            update = true;
                            break;
                        }
                    if(out[i] >= in[i]+2)
                        if(augment(i))
                        {
                            update = true;
                            break;
                        }
                }
                if(legal)
                    break;
            }
            }
            if(legal)
                System.out.println("possible");
            else
                System.out.println("impossible");
        }
    }
    static int[] prev;
    static Edge[] prevE;
    public static boolean augment(int at)
    {
        prev = new int[N];
        prevE = new Edge[N];
        Arrays.fill(prev,-1);
        dfs(at);
        for(int i = 0; i < N;i++)
        {
            if(prev[i] != -1 && out[i]+2 <= in[i])
            {
               int cur = i;
               while(cur != at)
               {
                    Edge e = prevE[cur];
                    dir[e.id] = 1-dir[e.id];
                    in[e.st]++;
                    out[e.st]--;
                    in[e.en]--;
                    out[e.en]++;
                    cur = prev[cur];
               }
               return true;
            }
        }
        return false;
    }
    public static void dfs(int at)
    {
        for(Edge e:adj[at])
        {
            if(e.id != -1 && dir[e.id] == e.val)
            {
                if(prev[e.en] == -1)
                {
                    prev[e.en] = at;
                    prevE[e.en] = e;
                    dfs(e.en);
                }
            }
        }
    }
    public static boolean augment2(int at)
    {
        prev = new int[N];
        prevE = new Edge[N];
        Arrays.fill(prev,-1);
        dfs2(at);
        for(int i = 0; i < N;i++)
        {
            if(prev[i] != -1 && in[i] +2<= out[i])
            {
               int cur = i;
               while(cur != at)
               {
                    Edge e = prevE[cur];
                    dir[e.id] = 1-dir[e.id];
                    in[e.st]--;
                    out[e.st]++;
                    in[e.en]++;
                    out[e.en]--;
                    cur = prev[cur];
               }
               return true;
            }
        }
        return false;
    }
    public static void dfs2(int at)
    {
        for(Edge e:adj[at])
        {
            if(e.id != -1 && dir[e.id] != e.val)
            {
                if(prev[e.en] == -1)
                {
                    prev[e.en] = at;
                    prevE[e.en] = e;
                    dfs2(e.en);
                }
            }
        }
    }
    static int N,M;
    static int[] dir,in,out;
    static ArrayList<Edge>[] adj;
    private static class Edge
    {
        int st,en;
        int id,val;
        Edge(int s, int e, int i,int v)
        {
            st = s;
            en = e;
            id = i;
            val = v;
        }
    }
}
