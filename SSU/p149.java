package SSU;
import java.util.*;
import static java.lang.Math.*;
import java.io.*;

/* Saratov State University Online Judge
 * Problem 149: Computer Network
 * Type: Graph Theory
 * Solution: First root the tree at 1 and compute the distance from each element to the furthest leaf w/o going up the tree. Now go over the tree again
 * each vertex is passed the maximum distance to the vertex using the edge above it, it can compute its distance as the max of that and the distance to
 * any leaf.
 */

public class p149 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader R = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.valueOf(R.readLine().trim());
		if(N == 0)
			return;
		adj = new ArrayList[N];
		for(int i = 0; i < N;i++)
			adj[i] = new ArrayList<Pair>();
		for(int i = 0; i < N-1;i++)
		{
			StringTokenizer st = new StringTokenizer(R.readLine().trim());
			int from = Integer.valueOf(st.nextToken())-1;
			int length = Integer.valueOf(st.nextToken());
			adj[from].add(new Pair(i+1,length));
		}
		D = new int[N];
		Arrays.fill(D,-1);
		Stack<Integer> st = new Stack<Integer>();
		st.add(0);
		while(st.size() > 0)
		{
			int at = st.peek();
			boolean legal = true;
			for(Pair e:adj[at])
			{
				if(D[e.a] == -1)
				{
					st.add(e.a);
					legal = false;
				}
			}
			if(!legal)
				continue;
			st.pop();
			D[at] = 0;
			for(Pair e:adj[at])
			{
				D[at] = max(D[at],D[e.a]+e.b);
			}
		}
		Stack<Pair> st2 = new Stack<Pair>();
		st2.add(new Pair(0,0));
		A = new int[N];
		Arrays.fill(A,-1);
		while(st2.size() > 0)
		{
			Pair at = st2.pop();
			int max1=0,max2=0;
			for(Pair e:adj[at.a])
			{
				int v = D[e.a]+e.b;
				if(v > max1)
				{
					max2 = max1;
					max1 = v;
				}else if(v > max2)
				{
					max2 = v;
				}
			}
			A[at.a] = max(at.b,max1);
			for(Pair e:adj[at.a])
			{
				int v = D[e.a]+e.b;
				if(v != max1)
				{
					st2.add(new Pair(e.a,max(max1,at.b)+e.b));
				}else{
					st2.add(new Pair(e.a,max(max2,at.b)+e.b));
				}
			}
		}
		for(int i = 0; i < N;i++)
			System.out.println(A[i]);
	}
	static int[] D,A;
	static ArrayList<Pair>[] adj;
	private static class Pair
	{
		int a, b;
		public Pair(int a, int b)
		{
			this.a = a;
			this.b = b;
		}
	}
}
