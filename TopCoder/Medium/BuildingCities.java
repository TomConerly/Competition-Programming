package TopCoder.Medium;
import java.util.*;
import static java.lang.Math.*;

/* TopCoder SRM 460
 * Medium Problem 500 Points: BuildingCities
 * Type: Graph Theory
 * Solution: Create a new graph where each vertex is city at, number extra used, and distance. Minimize extra used first then distance. 
 * This works because we assume that any added cities are directly between two previous cities when we travel from one to the other.
 */

public class BuildingCities {
	public void p(Object s){System.out.println(s);}
	public int findMinimumCities(int D, int maxTravel, int[] X, int[] Y) {
		double[][] dist = new double[X.length][3005];
		PriorityQueue<State> pq = new PriorityQueue<State>();
		pq.add(new State(0,0,0));
		for(double[] d:dist)
			Arrays.fill(d,Integer.MAX_VALUE);
		dist[0][0] = 0.;
		while(pq.size() > 0)
		{
			State s = pq.poll();
			if(dist[s.at][s.numused] < s.dist)
				continue;
			if(s.at == X.length-1 && s.dist <= maxTravel)
				return s.numused;
			for(int i = 0; i < X.length;i++){
				if(i == s.at)
					continue;				
				double dd = sqrt(pow(X[i]-X[s.at],2)+pow(Y[i]-Y[s.at],2));
				int numNeeded = (int)((dd-1e-9) / D);
				if(numNeeded+s.numused > 3000)
					continue;
				boolean legal = true;
				for(int j = 0; j < numNeeded + s.numused;j++)
					if(dist[i][j] < dd+s.dist)
						legal = false;
				if(legal && dist[i][numNeeded+s.numused] > dd+s.dist){
					pq.add(new State(numNeeded+s.numused,i,s.dist+dd));
					dist[i][numNeeded+s.numused] = dd+s.dist;
				}
			}
		}
		return -1;		
	}
	private class State implements Comparable<State>
	{
		int numused,at;
		double dist;
		State(int n,int a, double d)
		{
			numused = n;
			at = a;
			dist = d;
		}
		public int compareTo(State s) {
			int c = Integer.valueOf(numused).compareTo(s.numused);
			if(c != 0)
				return c;
			return Double.valueOf(dist).compareTo(s.dist);
		}
	}
}
