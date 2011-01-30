package SSU;
import java.util.*;
import java.io.*;

/* Saratov State University Online Judge
 * Problem 199:Beautiful People
 * Type: Data Structures
 * Solution: Given attributes A and B, sort by A and consider each one at a time. Keep a tree of values for B which is a map from B value to max number (max num is strictly 
 * increasing in this tree) when considering A find the best B it can pair with. Then update the tree of Bs.
 */

public class p199 {
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader R = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(R.readLine().trim());
		
		P = new Pair[N];
		for(int i = 0; i < N;i++)
		{
			StringTokenizer st = new StringTokenizer(R.readLine().trim());
			P[i] = new Pair(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),i);
		}
		Arrays.sort(P);
		best = new TreeMap<Integer,Pair>();
		for(int i = 0; i < N;i++)
		{
			Map.Entry<Integer,Pair> p = best.lowerEntry(P[i].b);

			//System.out.println(P[i]+" "+best);
			if(p != null)
			{
				P[i].count = 1 + p.getValue().count;
				P[i].next = p.getValue();
			}else{
				P[i].count = 1;
				P[i].next = null;
			}
			if(p == null || p.getValue().count < P[i].count)
			{
				best.put(P[i].b, P[i]);
			}
			p = best.higherEntry(P[i].b);	
			while(p != null && p.getValue().count < P[i].count)
			{
				best.remove(p.getKey());	
				p = best.higherEntry(P[i].b);	
			}
		}
		Pair a = best.get( best.lastKey());
		int len = a.count;
		ArrayList<Integer> ans = new ArrayList<Integer>();
		while(a != null)
		{
			ans.add(a.id);
			a = a.next;
		}
		StringBuilder out = new StringBuilder("");
		out.append(len+"\n");
		
		for(int e:ans)
			out.append((e+1)+" ");
		out.append("\n");
		System.out.println(out);
	}
	static TreeMap<Integer,Pair> best;
	static int N;
	static Pair[] P;
	
	private static class Pair implements Comparable<Pair>
	{
		int s,b,id,count;
		Pair next;
		Pair(int s,int b,int i)
		{
			this.s = s;
			this.b = b;
			id = i;
			next = null;
			count = 0;
		}
		public int compareTo(Pair p) {
			int c = Integer.valueOf(s).compareTo(p.s);
			if(c != 0)
				return c;
			return -Integer.valueOf(b).compareTo(p.b);
		}
	}
}
