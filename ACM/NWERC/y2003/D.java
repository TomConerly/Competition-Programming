package ACM.NWERC.y2003;
import java.util.*;

/* ACM NWERC 2003
 * Problem D: Who's the boss?
 * Type: Ad Hoc
 * Solution: Only hard part is to find who is whoms boss quickly. Sort everyone by increasing salary. Keep a second list initially empty
 * sorted by increasing height. Go through the list of increasing salary, while the first person in the second list (shortest) is shorter
 * than you, then they are your parent. When the list is empty or the first person is taller, add yourself to the list.
 */

public class D
{
    @SuppressWarnings("unchecked")
	public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int zz = 1; zz <=T;zz++)
        {
            N = sc.nextInt();
            Q = sc.nextInt();
            id = new int[N];
            height = new int[N];
            salary = new int[N];
            //sorted by increasing salary
            TreeSet<Node> all = new TreeSet<Node>();
            for(int i = 0 ;i < N;i++)
            {
                id[i] = sc.nextInt();
                salary[i] =sc.nextInt();
                height[i] = sc.nextInt();
                all.add(new Node(i));
            }
            //sorted by increasing height
            TreeSet<Node2> wait = new TreeSet<Node2>();
            
            boss = new int[N];
            Arrays.fill(boss,-1);
            //go through in order of increasing salary, add yourself to the "wait list" sorted by increasing
            //height, everyone in the wait list has lower salary than you, so go through and remove anyone
            //who you are taller than
            for(Node n:all)
            {
                while(wait.size() > 0 && height[wait.first().id] <= height[n.id])
                {
                    Node2 no = wait.pollFirst();
                    boss[no.id] = n.id;
                }
                wait.add(new Node2(n.id));
            }
            adj = new ArrayList[N];
            for(int i = 0; i < N;i++)
                adj[i] = new ArrayList<Integer>();
            for(int i = 0; i < N;i++)
            {
                if(boss[i] != -1)
                    adj[boss[i]].add(i);
            }
            size = new int[N];
            Arrays.fill(size,-1);
            for(int i = 0; i < N;i++)
            {
                compute(i);
            }
            for(int i = 0; i < Q;i++)
            {
                int qid = sc.nextInt();
                for(int j = 0; j < N;j++)
                {
                    if(qid == id[j])
                    {
                        int s = size[j];
                        int b = boss[j];
                        System.out.println((b==-1?0:id[b])+" "+s);
                    }
                }
            }
        }
    }
    public static int compute(int at)
    {
        if(size[at] != -1)
            return size[at];
        int ans = 0;
        for(int p:adj[at])
            ans += 1+compute(p);
        size[at] = ans;
        return ans;
    }
    public static class Node2 implements Comparable<Node2>
    {
        int id;
        public Node2(int i)
        {
            id = i;
        }
        public int compareTo(Node2 n)
        {
            return Integer.valueOf(height[id]).compareTo(height[n.id]);
        }
    }
    public static class Node implements Comparable<Node>
    {
        int id;
        public Node(int i)
        {
            id = i;
        }
        public int compareTo(Node n)
        {
            return Integer.valueOf(salary[id]).compareTo(salary[n.id]);
        }
    }
    static int[] size;
    static ArrayList<Integer>[] adj;
    static int N,Q;
    static int[] id,height,salary,boss;
}
