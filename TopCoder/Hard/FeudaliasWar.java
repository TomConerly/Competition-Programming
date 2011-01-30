package TopCoder.Hard;
import java.util.*;
import static java.lang.Math.*;

/* TopCoder SRM 438
 * Hard Problem 1000 Points: FeudaliasWar
 * Type: MaxFlow
 * Solution: Given a time to test we can construct a bipartite where left has v for each
 * silo and for each take of time, and right has v for each base. Edge between if we can 
 * launch the ith missle at that base. Then we can binary search on the time. Note that there are
 * only 50^3 possible times we care about (the time the jth missle from base i takes to hit silo j).
 */

public class FeudaliasWar {

	@SuppressWarnings("unused")
	private int[] bX,bY,sX,sY;
	double t1,r,s1;
	public double getMinimumTime(int[] sX, int[] sY, int[] bX, int[] bY, int takeT, int reT, int sp) {
		this.bX = bX;
		this.bY = bY;
		this.sX = sX;
		this.sY = sY;

		t1 = takeT;
		r = reT*60;
		s1 = sp*(1.0/60.0);
		
		double[] times = new double[bX.length*sX.length*sX.length];
		int at = 0;
		g = new double[bX.length*sX.length][sX.length];		
		for(int i = 0; i < bX.length;i++)
		{
			for(int j = 0; j < sX.length;j++)
			{
				for(int k = 0; k < sX.length;k++)
				{
					double time = t1*(j+1)+r*j + (sqrt(pow(bX[i]-sX[k],2)+pow(bY[i]-sY[k],2))/s1);
					g[i*sX.length+j][k] = time;
					times[at++] = time;
				}
			}
		}
		Arrays.sort(times);
		graph = new int[2+bX.length*sX.length+sX.length][2+bX.length*sX.length+sX.length];
		int low = 0;
		int high = times.length-1;
		int best = 0;
		while(true)
		{			
			double m = times[(low+high)/2];
			if(can(m))
			{
				best = (low+high)/2;
				high = (low+high)/2-1;
			}else{
				low = (low+high)/2+1;
			}
			if(low > high) break;
		}
		return times[best]/60.0;
	}
	double[][] g;
	int[][] graph;
	private boolean can(double m) {
		for(int i = 0; i < graph.length;i++)
			Arrays.fill(graph[i],0);
		int start = graph.length-2;
		int end = graph.length-1;

		for(int i = 0; i < bX.length;i++)
		{
			for(int j = 0; j < sX.length;j++)
			{
				for(int k = 0; k < sX.length;k++)
				{
					graph[k+bX.length*sX.length][i*sX.length+j] = (g[i*sX.length+j][k] <= m)?1:0;
				}
			}
		}
		for(int i = 0; i < bX.length*sX.length;i++)
			graph[i][end] = 1;
		for(int i = 0; i < sX.length;i++)
		{
			graph[start][i+bX.length*sX.length] = 1;
		}
		return maxf(start,end,graph) == sX.length;
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
	}	
}
