package TopCoder.Hard;
import java.util.*;

/* TopCoder SRM 425
 * Hard Problem 1000 Points: RoadsOfKingdom
 * Type: Graph Theory
 * Solution: This is the O(n^3) solution their is an O(3^n*n+2^n*n^2) solution under RoadsOfKingdom1.java
 * 
 * We want to use the weighted matrix tree theorem. (See: http://en.wikipedia.org/wiki/Kirchhoff%27s_theorem 
 * and the weighted tree theorem from tree.pdf page 63). This sum only gives us the product of the edges
 * in the MST not probability that all the other edges aren't there. The first solution is to replace
 * every edge's probability (which was p) with p/1-p then multiplying the answer from the tree theorem
 * by the sum over all edges of 1-p. The 1-ps cancel for the edges in the tree and not for the others.
 * This works except for we get a divide by zero on the edges of weight 1. Since these edges will 
 * always be in the graph we can combine two nodes if they have a weight 1 edge between them. So first
 * combine all edges of weight 1 into connected components. Make sure there are no cycles. 
 * 
 * For each component we need to find the probablity that any of the non weight 1 edges are in the graph
 * because if any of them are there is a cycle. Then for each pair of components find the probability
 * of zero, one, and 2+ edges between them. Make the weight of the edge between them k/(1-k) where
 * k = (prob one)/(prob zero+prob one). Then we can normalize our answer at the end by the probablity
 * that was never 2+ edges.
 */
public class RoadsOfKingdom {

	public boolean dfs(int at, int num,int prev)
	{
		if(comp[at] != -1) return false;
		comp[at] = num;
		boolean ret = true;
		for(int i = 0; i < N;i++)
		{
			if(i != prev && i != at && roads[at].charAt(i) == '8')
				ret &= dfs(i,num,at);
		}
		return ret;
	}
	int[] comp;
	String[] roads;
	int N;
	public double getProbability(String[] r) {
		roads = r;
		N = roads.length;	

		comp = new int[N];
		Arrays.fill(comp,-1);
		int at = 0;
		boolean legal = true;
		for(int i = 0; i < N;i++)
		{
			if(comp[i] == -1) legal &= dfs(i,at++,-1);
		}
		if(!legal) return 0.0;

		ArrayList<ArrayList<Integer>> groups = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < at;i++)
			groups.add(new ArrayList<Integer>());
		for(int i = 0; i < N;i++)
			groups.get(comp[i]).add(i);		

		double[][] p2 = new double[N][N];
		double[][] p1 = new double[N][N];
		double[][] p0 = new double[N][N];
		double probAllLegal = 1.0;
		for(int i = 0; i < at;i++)
		{
			for(int j = 0; j < at;j++)
			{
				if(i==j) continue;
				double prob0 = 1.0;
				for(int a: groups.get(i))
					for(int b: groups.get(j))
						prob0 *= (1-(roads[a].charAt(b)-'0')/8.0);

				double prob1 = 0.0;
				for(int a: groups.get(i))
					for(int b: groups.get(j))
						if(roads[a].charAt(b) != '0')
							prob1 += ((roads[a].charAt(b)-'0')/8.0)*(1/(1-((roads[a].charAt(b)-'0')/8.0)))*prob0;

				p0[i][j] = prob0;
				p1[i][j] = prob1;
				p2[i][j] = 1-prob1-prob0;
				if(i < j)
				{
					probAllLegal *= 1-p2[i][j];

				}
			}
		}
		for(int i = 0; i < at;i++)
		{
			for(int k = 0; k < groups.get(i).size();k++)
			{
				for(int m = k+1; m< groups.get(i).size();m++)
				{
					if(roads[groups.get(i).get(k)].charAt(groups.get(i).get(m)) != '8')
						probAllLegal *= 1-((roads[groups.get(i).get(k)].charAt(groups.get(i).get(m))-'0')/8.0);
				}
			}
		}
		Matrix m = new Matrix(at,at);
		double res = 1.0;
		for(int i = 0; i < at;i++)
		{
			for(int j = 0; j < at;j++)
			{
				if(i==j) continue;
				double p = p1[i][j]/(p1[i][j]+p0[i][j]);
				m.m[i][j] = -(p)/(1-p);
				m.m[i][i] -= m.m[i][j];				
				if(i < j)
					res *= (1-p);				
			}
		}
		//	System.out.println(m);
		Matrix m2 = new Matrix(at-1,at-1);
		for(int i = 0; i < at-1;i++)
		{
			for(int j = 0; j < at-1;j++)
			{
				m2.m[i][j] = m.m[i][j];
			}
		}
		//System.out.println(probAllLegal+" "+res+" "+m2.determ());
		return probAllLegal*res*m2.determ();			
	}

	private class Matrix {
		int r,c;
		double[][] m;
		public Matrix(int r,int c) {
			this.r = r;
			this.c = c;
			m = new double[r][c];
		}
		public double determ()
		{
			if(r != c) throw new IllegalArgumentException();
			Matrix temp = copy();
			for (int i = 0; i < temp.c; i++) {
				int first = -1;
				for (int j = i; j < temp.r; j++) {
					if(temp.m[i][j] != 0){
						first = j;
						break;
					}
				}
				if(first ==-1) return 0.0; //can't be reduced

				//switch it up
				if(first != i)
				{
					temp.switchRows(i,first);
				}

				for(int j = i+1; j < temp.c;j++)
				{

					temp.subRow(j,i,temp.m[j][i]/temp.m[i][i]);
				}
			}

			double ans = 1.0;
			for(int i = 0; i < temp.c;i++)
			{
				ans *= temp.m[i][i];
			}
			return ans;
		}
		public String toString()
		{
			String out = r+","+c+"\n";
			for(int i = 0; i <r;i++)
			{
				for(int j = 0; j < c;j++)
				{
					out = out + m[i][j]+",";
				}
				out = out +"\n";
			}
			return out;
		}

		private void subRow(int j, int i, double d) {
			for(int k = 0; k < c;k++)
			{
				m[j][k] -= m[i][k]*d;
			}

		}
		@SuppressWarnings("unused")
		public void scaleRow(int r, double f)
		{
			for(int i = 0; i < c;i++)
			{
				m[r][i] *= f;
			}
		}
		public void switchRows(int a, int b) {
			double[] temp = m[a];
			m[a]=m[b];
			m[b] = temp;

		}
		public Matrix copy()
		{
			Matrix copy = new Matrix(r,c);
			for (int i = 0; i < m.length; i++) {
				for (int j = 0; j < m[i].length; j++) {
					copy.m[i][j] = m[i][j];
				}
			}
			return copy;
		}
	}


}
