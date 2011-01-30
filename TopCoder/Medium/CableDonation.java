package TopCoder.Medium;
import java.util.*;
import static java.lang.Math.*;

/* TopCoder TCO08 Qual 3
 * Medium Problem 500 Points: CableDonation
 * Type: MST
 * Solution: A basic MST problem.
 */

public class CableDonation {

	public int cable(String[] lengths) {
		int[][] b = new int[lengths.length][lengths.length];
		int ans = 0;
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Double>> cost = new ArrayList<ArrayList<Double>>();
		for(int i = 0; i < b.length;i++)
			for(int j = 0; j < b.length;j++)
			{
				b[i][j] = v(lengths[i].charAt(j));
				ans += b[i][j];
			}
		int[][] co = new int[lengths.length][lengths.length];
		for(int i = 0; i < b.length;i++)
		{
			adj.add(new ArrayList<Integer>());
			cost.add(new ArrayList<Double>());
			for(int j = 0; j < b.length;j++)
			{
				if(j == i) continue;
				if(b[i][j] == 0 && b[j][i] == 0) continue;
				int c = min(b[i][j],b[j][i]);			
				if(c == 0) c = max(b[i][j],b[j][i]);
				co[i][j] = c;
				adj.get(i).add(j);
				cost.get(i).add(0.0+c);
				
			}
		}
		int[][] e = prims(adj,cost);
		if(e == null) return -1;
		for(int i = 0; i < e.length;i++)
		{
			ans -= co[e[i][0]][e[i][1]];
		}
		return ans;
	}
	private int v(char c) {
		if(c == '0') return 0;
		if(Character.isLowerCase(c)) return c - 'a'+1;
		return c-'A'+27;
	}
	public void p(Object o){System.out.println(o);}
	public static int[][] prims(ArrayList<ArrayList<Integer>> adj,ArrayList<ArrayList<Double>> cost){
		int[][] ans = new int[adj.size()-1][2];
		int ansAt = 0;		
		boolean[] used = new boolean[adj.size()];
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		double[] minDist = new double[adj.size()];
		Arrays.fill(minDist,Integer.MAX_VALUE);

		minDist[0]=0;
		pq.add(new Node(-1,0,0));
		while(pq.size() > 0)
		{
			Node n = pq.poll();
			int at = n.b;

			if(used[at]) continue;
			used[at] = true;

			if(n.a != -1){
				ans[ansAt][0] = n.a;
				ans[ansAt][1] = at;
				ansAt++;
			}

			for(int i = 0; i < adj.get(at).size();i++)
			{
				int to = adj.get(at).get(i);
				double c = cost.get(at).get(i);
				if(!used[to] && c < minDist[to])
				{
					pq.add(new Node(at,to,c));
					minDist[to] = c;
				}
			}
		}
		for(int i = 0; i < used.length;i++)
			if(used[i] == false) return null;
		return ans;
	}
	private static class Node implements Comparable<Node>{
		int a,b;
		double c;
		public Node(int i, int j, double k) {
			a = i;
			b = j;
			c = k;
		}		
		public int compareTo(Node n) {
			if(c < n.c) return -1;
			if(c > n.c) return 1;
			return 0;
		}				
	}	
}
