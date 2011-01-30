package ACM.NWERC.y2002;
import java.util.*;

/* ACM NWERC 2002
 * Problem B: Markov Trains
 * Type: Brute Force
 * Solution: Try all paths such that if all trains sucseed it is possible to get from the start to the end.
 * There are ~10! paths at most, but we can't have a complete graph, and edges have to be used in a defined
 * order so it is much better than that.
 * 
 * Given a path use DP to find the probability of succsess. The dp is over the last edge used which competly
 * defines the state. 
 */

public class B
{
    @SuppressWarnings("unchecked")
	public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int zz = 1; zz <= T;zz++)
        {
           int N = sc.nextInt();
           edges = new ArrayList[12][12];
           for(int i = 0; i < 12;i++)
               for(int j = 0; j < 12;j++)
                   edges[i][j] = new ArrayList<Integer>();
           s= new int[N];
           e = new int[N];
           ep = new double[N];
           for(int i = 0; i < N;i++)
           {
               int dep = sc.next().charAt(0)-'A';
               int td = readTime(sc);
               int dest = sc.next().charAt(0)-'A';
               int ta = readTime(sc);
               edges[dep][dest].add(i);
               s[i] = td;
               e[i] = ta;
                ep[i] = sc.nextDouble();
           }
           Comparator<Integer> icomp = new Comparator<Integer>()
           {
               public int compare(Integer i1, Integer i2)
               {
                return Integer.valueOf(s[i1]).compareTo(s[i2]);  
               }
           };

           for(int i= 0; i < 12;i++)
               for(int j = 0; j < 12;j++)
               {
                   Collections.sort(edges[i][j],icomp);
               }
           st = sc.next().charAt(0)-'A';
           stt = readTime(sc);
           en = sc.next().charAt(0)-'A';
           ent = readTime(sc);

            best = -1.0;
            bestpath = null;
            int[] path = new int[12];
            path[0] = st;
            recur(path,1,(1<<st),stt-1,st);
            for(int i = 0; i < bestpath.length;i++)
            {
                if(i==0)
                    System.out.print((char)(bestpath[i]+'A'));
                else
                    System.out.print(" "+(char)(bestpath[i]+'A'));
            }
            System.out.println();
            System.out.format("%.04f\n",best);
        }
    }
    static int readTime(Scanner sc)
    {
        String[] p = sc.next().split(":");
        return Integer.valueOf(p[0])*60+Integer.valueOf(p[1]);
    }
    static int st,en;
    static int stt,ent;
    static ArrayList<Integer>[][] edges;
    static int[] s,e;
    static double best;
    static int[] bestpath;
    static double[] ep;
    public static void recur(int[] order, int idx, int used, int time, int at)
    {
        if(time > ent)
            return;
        if(at == en)
        {
            eval(order,idx);
            return;
        }
        for(int i = 0; i < 12;i++)
        {
            if(((1<<i) & used) == 0)
            {
                for(int ed:edges[at][i])
                {
                    if(s[ed] <= time)
                        continue;
                    order[idx] = i;
                    recur(order,idx+1,used|(1<<i),e[ed],i);
                }
            }
        }
    }
    public static void eval(int[] order, int len)
    {
        dp = new double[s.length+1];
        Arrays.fill(dp,-1);
        double p = recur2(order,0,st,s.length,stt-1);
        if(bestpath == null || p > best)
        {
            best = p;
            bestpath = new int[len];
            for(int i = 0; i < len;i++)
                bestpath[i] = order[i];
        }
    }
    public static double recur2(int[] order,int id,int at, int edgeused,int time)
    {
        if(time > ent)
            return 0.0;
        if(at == en)
            return 1.0;
        if(dp[edgeused] != -1)
            return dp[edgeused];
        double ans = 0;
        double p = 1.0;
        for(int ed: edges[at][order[id+1]])
        {
            if(s[ed] <= time)
                continue;
            double suc = p*(1-ep[ed]);
            p *= ep[ed];
            ans += suc*recur2(order,id+1,order[id+1],ed,e[ed]);
        }
        dp[edgeused] = ans;
        return ans;
    }
    static double[] dp;
}
