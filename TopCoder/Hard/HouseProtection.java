package TopCoder.Hard;
import java.util.*;
import static java.lang.Math.*;
/* TopCoder SRM 397
 * Hard Problem 1000 Points: HouseProtection
 * Type: MaxFlow/GraphTheory
 * Solution: Given that there are n points we can find which n points by
 * running max flow. Binary search over the max distance. For a distance 
 * make a graph with edges between two that collide, max flow is the # that
 * must be removed to take out collisions. Use that to find out if we have
 * the correct number or not. 
 */
public class HouseProtection {

	int[] pXB;
	int[] pYB;
	int[] pXR;
	int[] pYR;
	int K;
	double[][] dist;
	public double safetyFactor(int[] possibleXForBlue, int[] possibleYForBlue, int[] possibleXForRed, int[] possibleYForRed, int R) {
		K = R;
	
		pXB = possibleXForBlue;
		pYB = possibleYForBlue;
		pXR = possibleXForRed;
		pYR = possibleYForRed;
		dist = new double[pXB.length][pXR.length];
		for(int i = 0; i < dist.length;i++)
		{
			for(int j = 0; j < dist[0].length;j++)
			{
				dist[i][j] = dist(i,j);
			}
		}
		double best = 0;
		for(int i = max(pXB.length,pXR.length); i <= pXB.length+pXR.length;i++)
		{
			double temp = pow(distWithN(i),2)*Math.PI*i;
			best = max(best, temp);
		}
		return best;
	}
	public double distWithN(int n)
	{
		int[][] graph = new int[2+pXB.length+pXR.length][2+pXB.length+pXR.length];
		double a=0.0;
		double b=K;
		while(b-a > 1e-9)
		{
			if(can((b+a)/2,n,graph))
			{
				a = (b+a)/2;
			}else{
				b = (b+a)/2;
			}
		}
		return (b+a)/2;
	}
	private boolean can(double d, int n,int[][] graph) {
		for(int i = 0; i < graph.length;i++)
			Arrays.fill(graph[i],0);
		for(int i = 2; i < 2+pXR.length;i++)
		{
			graph[0][i] = 1;
		}
		for(int i = 2+pXR.length; i < graph.length;i++)
		{
			graph[i][1] = 1;
		}
		for(int i = 0; i < pXB.length;i++)
		{
			for(int j = 0; j < pXR.length;j++)
			{
				if(dist[i][j] <= d)
				{
					graph[j+2][i+2+pXR.length] = 1;
				}
			}
		}
		int maxf = maxf(0,1,graph);
		if((pXB.length+pXR.length - maxf) >= n) return true;
		else return false;
	}
	private double dist(int i, int j) {
		return Math.sqrt(pow(pXR[j]-pXB[i],2)+pow(pYR[j]-pYB[i],2))/2;
	}
	public int maxf(int start, int end, int[][] adj){
		int flow = 0;
		while(true){
			int newFlow = dfs(start,end,Integer.MAX_VALUE,adj,new boolean[adj.length]);
			flow += newFlow;
			if(newFlow == 0) break;
		}
		return flow;		
	}

	public int dfs(int start, int end,int by, int[][] adj,boolean[] used){
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
