package TopCoder.Easy;

/* TopCoder SRM 425
 * Easy Problem 250 Points: CrazyBot
 * Type: Simulation
 * Solution: There are only 3^14 paths (paths can't go
 * the opposite direction they just went, so brute force.
 */

public class CrazyBot {

	public double getProbability(int n, int east, int west, int south, int north) {
		p = new double[4];
		p[0] = north/100.0;
		p[1] = south/100.0;
		p[2] = east/100.0;
		p[3] = west/100.0;
		return prob(n,15,15,new boolean[35][35]);
	}
	double[] p;
	public double prob(int n, int x, int y,boolean[][] visited)
	{
		
		if(visited[x][y]) return 0.0;
		visited[x][y] = true;
		if(n==0){
			visited[x][y] = false;
			return 1.0;			
		}
		
		double prob = 0.0;
		for(int i = 0; i < dir.length;i++)
		{
			int nx = x+dir[i][0];
			int ny = y+dir[i][1];
			
			prob += p[i]*prob(n-1,nx,ny,visited);			
			
		}
		visited[x][y] = false;
		return prob;
	}
	int[][] dir = {{1,0},{-1,0},{0,-1},{0,1}};
}
