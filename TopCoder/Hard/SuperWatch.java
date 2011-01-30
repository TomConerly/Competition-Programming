package TopCoder.Hard;
import java.util.*;
import static java.lang.Math.*;

/* TopCoder 2008 TCO Round 3
 * Hard Problem 1000 Points: SuperWatch
 * Type: Simulation
 * Solution: Assume that the day is infinte then we would want to map the ith smallest
 * time to the ith smallest time zone. But the day isn't infinte so there are other possible
 * mappings. It might help to tree the first m times as on the next day, so try all values of m
 * and then map time zones like before, take the best answer.
 * 
 * The other solution here shouldn't work, but the time is so gracious is does. Binary search over the length
 * for a given length try all median times and use max flow to determine if it works or not. This method
 * needs a ton of optimizations and only works because of the large margin of error for timings.
 */

public class SuperWatch {
	public int smallestImprecision(String[] times, int[] zones) {
		int[] t = new int[times.length];
		for(int i = 0; i < t.length;i++)
		{
			String[] p = times[i].split(":");
			t[i] = Integer.parseInt(p[0])*60+Integer.parseInt(p[1]);
		}
		Arrays.sort(zones);
		Arrays.sort(t);
		int best = Integer.MAX_VALUE;
		for(int i = 0; i < t.length;i++)
		{
			int[] check = new int[t.length];
			for(int j = 0; j < t.length;j++)
				check[j] = t[(j+i)%t.length]; 
			
			for(int j = 0; j < t.length;j++)
			{
				check[j]  = (check[j] - zones[j]*60+24*60)%(24*60);
			}
			Arrays.sort(check);
			//p(Arrays.toString(check));
			int score = Integer.MAX_VALUE;
			for(int j = 0; j < t.length;j++)
			{
				if(j != t.length-1)
					score = min(score,24*60-(check[j+1]-check[j]));
				else
				{
					score = min(score,check[j]-check[0]);
				}
			}
			best = min(best,score);
		}

		return best;
	}
	/*public int smallestImprecision(String[] times, int[] zones) {
		int N = 24*60;
		int low = 0,high=N;

		int[] t = new int[times.length];
		for(int i = 0; i < t.length;i++)
		{
			String[] p = times[i].split(":");
			t[i] = Integer.parseInt(p[0])*60+Integer.parseInt(p[1]);
		}
		int[][] graph = new int[t.length*2+2][t.length*2+2];
		int st = t.length*2;
		int en = t.length*2+1;
		int best = Integer.MAX_VALUE;
		while(true)
		{
			int m = (low+high)/2;
			boolean can = false;			

			next:
				for(int time = 0; time < N;time++)
				{				
					int l,h;
					l = (time - m/2+N)%N;
					h = (time + (m+1)/2)%N;
					for(int i = 0; i < graph.length;i++)
						Arrays.fill(graph[i],0);
					boolean[] fo = new boolean[t.length];
					for(int i = 0; i < t.length;i++)
					{				
						boolean found = false;
						for(int j = 0; j < t.length;j++)
						{
							int tt = (t[i]-zones[j]*60 + N)%N;
							int dh = tt + (tt<h?N-h:-h);
							int dl = tt + (tt<l?N-l:-l);
							if(dl < dh){
								graph[i][j+t.length]=1;
								fo[j] = true;
								found = true;
							}
						}
						if(found == false) continue next;
						graph[st][i] = 1;
						graph[i+t.length][en]=1;
					}
					for(boolean b:fo) if(b == false) continue next;
					int f = maxf(st,en,graph);
					if(f == t.length)
					{
						can = true;
						break;
					}
					time += (t.length-f)/2;
				}

			if(!can)
			{				
				low = m+1;
			}else{
				best = m;
				high = m-1;
			}
			if(low > high) break;
		}

		return best-1;
	}
	public static int maxf(int start, int end, int[][] adj){
		int flow = 0;
		while(true){
			int newFlow = dfs(start,end,Integer.MAX_VALUE,adj,new boolean[adj.length]);
			flow += newFlow;
			if(newFlow == 0) break;
		}
		return flow;		
	}

	public static int dfs(int start, int end,int by, int[][] adj,boolean[] used){
		if(start == end) return by;
		used[start] = true;

		for(int i = 0; i < adj.length;i++){

			if(adj[start][i] > 0 && !used[i]){

				int newFlow = dfs(i,end,Math.min(by,adj[start][i]),adj,used);
				if(newFlow > 0){
					adj[start][i] -= newFlow;
					adj[i][start] += newFlow;
					return newFlow;
				}
			}
		}
		return 0;
	}*/
	public void p(Object o){System.out.println(o);}
}
