package SSU;
import java.util.*;
import java.io.*;

/* Saratov State University Online Judge
 * Problem 462: Electrician
 * Type: Greedy/Graph Theory
 * Solution: If reliability are all distinct that answer is maximum spanning tree based on reliability. You want to break ties with highest price
 * so you always add higher things later. So add order is increasing relibility then price (i think you only need increasing price). To compute max
 * cost you can just run kruskal in reverse order.
 */

public class p462 {
	public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader R = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(R.readLine());
		Edge[] E = new Edge[N];
		for(int i = 0; i < N; i++){
			StringTokenizer st = new StringTokenizer(R.readLine());
			E[i] = new Edge(i+1,Integer.valueOf(st.nextToken()),Integer.valueOf(st.nextToken()),Integer.valueOf(st.nextToken()),Integer.valueOf(st.nextToken()));
		}
		Arrays.sort(E);
		P = new HashMap<Long,Long>();
		long cost = 0;
		for(Edge e:E){
			if(find(e.a) == find(e.b))
				continue;
			union(e.a,e.b);
			cost += e.p;
		}
		StringBuilder ans = new StringBuilder("");
		ans.append(cost+"\n");
		Arrays.sort(E,new Comparator<Edge>(){
			public int compare(Edge e1, Edge e2) {
				int c1 = Long.valueOf(e1.r).compareTo(e2.r);
				if(c1 != 0)
					return c1;
				return Long.valueOf(e1.p).compareTo(e2.p);
			}
		});
		ans.append(E[0].idx);
		for(int i = 1; i < N;i++)
			ans.append(" "+E[i].idx);
		System.out.println(ans);		
	}
	static HashMap<Long,Long> P;
	static void union(long a, long b){
		if(!P.containsKey(a))
			P.put(a,a);
		if(!P.containsKey(b))
			P.put(b,b);
		P.put(find(a), find(b));
	}
	static long find(long a){
		if(!P.containsKey(a))
			P.put(a, a);
		if(P.get(a) != a)
			P.put(a, find(P.get(a)));
		return P.get(a);
	}
	private static class Edge implements Comparable<Edge>{
		long idx,a,b,r,p;
		Edge(int idx,int a, int b, int r, int p){
			this.idx = idx;
			this.a = a;
			this.b = b;
			this.r = r;
			this.p = p;
		}
		@Override
		public int compareTo(Edge e) {
			int c1 = -Long.valueOf(r).compareTo(e.r);
			if(c1 != 0)
				return c1;
			return -Long.valueOf(p).compareTo(e.p);
		}
	}
}