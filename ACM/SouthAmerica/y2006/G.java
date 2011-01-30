package ACM.SouthAmerica.y2006;
import java.util.*;

/* ACM ICPC 2006 South American Regional 
 * Problem G: Power Generation
 * Type: Graph Theory
 * Solution: Take each leaf and either make it into a seperate company or merge it up. We can do this greedily.
 */

public class G {
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		while(true)
		{
			int N = s.nextInt();
			int C = s.nextInt();
			if(N==0 && C==0) break;
			int[][] nodes = new int[N][2];
			int[] cap = new int[N];
			ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
			for(int i = 0; i < N;i++)
			{
				edges.add(new ArrayList<Integer>());
				nodes[i][0] = s.nextInt();
				nodes[i][1] = s.nextInt();
				cap[i] = s.nextInt();
				int min = -1;
				int dist = 0;
				for(int j = 0; j < i;j++)
				{
					int d = (nodes[i][0]-nodes[j][0])*(nodes[i][0]-nodes[j][0]) + (nodes[i][1]-nodes[j][1])*(nodes[i][1]-nodes[j][1]);
					if(min == -1 || dist > d)
					{
						min = j;
						dist = d;
					}
				}
				if(min == -1) continue;
				edges.get(i).add(min);
				edges.get(min).add(i);
			}
			int ans = 0;
			boolean[] used = new boolean[N];
			for(int i = 0; i < N;i++)
			{
				for(int j = 0; j < N;j++)
				{
					if(used[j]) continue;
					if(edges.get(j).size() == 1)
					{
						used[j] = true;
						if(cap[j] >= C)
						{
							ans++;
							edges.get(edges.get(j).get(0)).remove(new Integer(j));
							edges.get(j).remove(0);
							break;
						}
						else
						{
							int to = edges.get(j).get(0);
							edges.get(to).remove(new Integer(j));
							edges.get(j).remove(0);
							cap[to] += cap[j];
							break;
						}
					}else if(edges.get(j).size() ==0)
					{
						used[j] = true;
						if(cap[j] >= C) ans++;
					}
				}
			}
			System.out.println(ans);
		}
	}
}
