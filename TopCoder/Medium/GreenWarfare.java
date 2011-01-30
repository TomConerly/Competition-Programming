package TopCoder.Medium;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Arrays.*;

/* TopCoder SRM 465
 * Medium Problem 500 Points: GreenWarfare
 * Type: Max Flow/Min Cut
 * Solution: Create a bipartite graph with bases on the left, and power centers on the right. Connect two things if they are with in range (infinite capacity).
 * Connect things up to the source and sink with the cost of destroying them. Now notice that in any cut you either cut the edge between the source and the base
 * or all of the edges between any power center and the sink. Thus the min cut is exactly what you want.
 */

public class GreenWarfare {
	public void p(Object... s){System.out.println(deepToString(s));}
	@SuppressWarnings("unchecked")
	public int minimumEnergyCost(int[] cX, int[] cY, int[] bX, int[] bY, int[] pX, int[] pY, int esr) {
		HashMap<Integer,Integer>[] adj = new HashMap[2+bX.length+pY.length];
		for(int i = 0; i < adj.length;i++)
			adj[i] = new HashMap<Integer,Integer>();
		int st = adj.length-2;
		int en = adj.length-1;
		for(int i = 0; i < bX.length;i++)
			adj[st].put(i, cost(bX[i],bY[i],cX,cY));
		for(int i = 0; i < bX.length;i++)
			for(int j = 0; j < pX.length;j++)			
				if(sqrt(pow(bX[i]-pX[j],2) + pow(bY[i]-pY[j],2))-1e-6 <= esr)
					adj[i].put(j+bX.length, Integer.MAX_VALUE);
		for(int i = 0; i < pX.length;i++)
			adj[i+bX.length].put(en, cost(pX[i],pY[i],cX,cY));
		
		int ans = maxf(st,en,adj);
		return ans;
	}
	private int cost(int x, int y, int[] cX, int[] cY) {
		int dist = Integer.MAX_VALUE;
		for(int i = 0; i < cX.length;i++)
			dist = min(dist,(cX[i]-x)*(cX[i]-x)+(cY[i]-y)*(cY[i]-y));
		return dist;
	}
	public int maxf(int st,int en, HashMap<Integer,Integer>[] adj){
		int maxf = 0;
		while(true){
			int[] p = dfs(st,en,adj);
			if(p==null) break;
			maxf += update(Integer.MAX_VALUE,st,en,p,adj);
		}
		return maxf;
	}
	public int update(int f, int st,int at,int[] p,HashMap<Integer,Integer>[]adj){
		if(at == st) 
			return f;
		f = update(min(f,adj[p[at]].get(at)),st,p[at],p,adj);
		change(adj[p[at]],at,-f);
		change(adj[at],p[at],f);
		return f;
	}
	public void change(HashMap<Integer,Integer> hm, int to, int delta){
		if(hm.containsKey(to))
			hm.put(to,hm.get(to)+delta);			
		else
			hm.put(to,delta);
	}
	public int[] dfs(int st, int en, HashMap<Integer,Integer>[] adj){
		LinkedList<Integer> paths = new LinkedList<Integer>();
		int[] p = new int[adj.length];
		Arrays.fill(p,-1);
		paths.add(st);
		while(paths.size() > 0){
			int at = paths.removeFirst();
			for(Map.Entry<Integer,Integer> e:adj[at].entrySet()){
				int to = e.getKey();
				if(p[to] == -1 && e.getValue() > 0){
					p[to] = at;
					if(to == en)
						return p;
					//add first -> dfs
					//add last -> bfs
					paths.addFirst(to);
				}
			}
		}
		return null;
	}
}
