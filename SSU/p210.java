package SSU;
import java.util.*;
import java.io.*;

/* Saratov State University Online Judge
 * Problem 210: Beloved Sons
 * Type: Graph Theory
 * Solution: This is mincost max flow or hungarian algorithm. Set it up as bipartite matching but the flow from the sink
 * to a son is the bonus you get from assigning that son.
 * 
 * The time bounds are really tight but you can optimize it a ton.
 */

public class p210 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine().trim());
		VAL = new int[N];
		StringTokenizer st = new StringTokenizer(in.readLine().trim());
		for(int i = 0; i < N;i++)
			VAL[i] = Integer.parseInt(st.nextToken().trim());
		adj = new HashMap[2*N+2];
		for(int i = 0; i < 2*N+2;i++)
			adj[i] = new HashMap<Integer,Pair>();
		int s = adj.length-2;
		int e = adj.length-1;
		for(int i = 0; i < N;i++){
			st = new StringTokenizer(in.readLine().trim());
			int k = Integer.parseInt(st.nextToken().trim());
			adj[s].put(i, new Pair(1,-VAL[i]*VAL[i]));
			for(int j = 0;j < k;j++)
				adj[i].put(N+Integer.valueOf(st.nextToken())-1, new Pair(1,0));

			adj[N+i].put(e, new Pair(1,0));
		}
		maxFlowMinCost(s, e);
		long score = 0;
		for(int i = 0; i < N;i++){
			int pair = 0;
			for(Map.Entry<Integer,Pair> E : adj[i].entrySet()){
				if(N<= E.getKey() && E.getKey() < 2*N && E.getValue().f == 0){
					pair = E.getKey()-N+1;
					score += VAL[i]*VAL[i];
					break;
				}
			}
			System.out.print(pair+" ");
		}
		System.out.println();
	}
	static int[] VAL;
	static HashMap<Integer,Pair>[] adj;
	static double[] dist;
	static long sort=0,start = 0,dfs=0,fill =0;
	public static Pair maxFlowMinCost(int st, int en){
		int N = adj.length;
		Pair ans = new Pair(0,0.0);

		ArrayList<Integer> s = new ArrayList<Integer>();
		for(int i = 0; i < VAL.length;i++)
			s.add(i);
		Collections.sort(s,new Comparator<Integer>(){
			@Override
			public int compare(Integer a, Integer b) {
				return Integer.valueOf(VAL[a]).compareTo(VAL[b]);
			}});
		while(true){
			dist = new double[N];
			int[] pred = new int[N];
			Arrays.fill(dist,Double.MAX_VALUE);
			dist[st] = 0;
			Arrays.fill(pred,-1);

			boolean[] V = new boolean[N];

			LinkedList<Integer> stack = new LinkedList<Integer>();
			for(int i:s){
				if(adj[st].get(i).f == 1){
					pred[i] = st;
					dist[i] = adj[st].get(i).s;
					stack.addLast(i);
					V[i] = true;
				}
			}

			V[st] = true;
			over:
				while(stack.size() > 0){
					int at = stack.pollLast();
					for(Map.Entry<Integer, Pair> E:adj[at].entrySet()){
						int key = E.getKey();
						if(V[key])
							continue;
						if(E.getValue().f > 0){
							V[key] = true;
							pred[key] = at;
							dist[key] = dist[at];
							stack.addLast(key);
							if(key == en)
								break over;
							if(adj[key].get(en) != null && adj[key].get(en).f == 1){
								pred[en] = key;
								dist[en] = dist[key];
								break over;
							}
						}
					}
				}
			if(dist[en] != Double.MAX_VALUE){
				ans.f += 1;
				ans.s += 1*dist[en];

				for(int at = en; at != st; at = pred[at]){
					int prev = pred[at];
					Pair p = adj[prev].get(at);
					p.f -= 1;

					if(!adj[at].containsKey(prev))
						adj[at].put(prev, new Pair(1,-p.s));
					else{
						Pair d = adj[at].get(prev);
						d.f += 1;
					}
				}
			}else
				break;
		}
		return ans;
	}

	public static class Pair {
		int f;
		double s;
		Pair(int a, double b){
			f = a;
			s = b;}
		public String toString() {return "["+f+","+s+"]";}
	}
}