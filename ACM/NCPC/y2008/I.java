package ACM.NCPC.y2008;
import java.util.*;

/* ACM NCPC 2008
 * Problem I: Introspective Caching
 * Type: Greedy
 * Solution: You always remove the one you will use next the furthest time away from now. Store the elements currently
 * in the cache in a tree so in O(log n) you can find which element is going to be used next latest and then remove that element.
 */

public class I
{
	@SuppressWarnings("unchecked")
	public static void main(String[] args) 
	{
		
		Scanner sc = new Scanner(System.in);
		long st = System.currentTimeMillis();
		while(sc.hasNextInt())
		{			

			int C = sc.nextInt();
			int N = sc.nextInt();
			int A = sc.nextInt();
			ArrayList<Integer>[] t = new ArrayList[N];
			int[] acc = new int[A];
			for(int i = 0; i < N;i++)
				t[i] = new ArrayList<Integer>();

			for(int i = 0; i < A;i++)
			{
				int id = sc.nextInt();
				t[id].add(i);
				acc[i] = id;
			}
			boolean[] in = new boolean[N];
			int ans = 0;
			int[] at = new int[N];
			TreeSet<Entry> ts = new TreeSet<Entry>();

			for(int i = 0; i < A;i++)
			{
				int a = acc[i];
				if(in[a])
				{
					ts.remove(new Entry(a,t[a].get(at[a])));
					at[a]++;
					if(at[a] == t[a].size())
						ts.add(new Entry(a,Integer.MAX_VALUE));
					else
						ts.add(new Entry(a,t[a].get(at[a])));
					continue;
				}

				ans++;
				if(ts.size() == C)
				{
					Entry e = ts.pollLast();
					in[e.id] = false;
				}				

				in[a] = true;				
				at[a]++;
				if(at[a] == t[a].size())
					ts.add(new Entry(a,Integer.MAX_VALUE));
				else
					ts.add(new Entry(a,t[a].get(at[a])));

				if(ts.size() > C)
					System.out.println("ERRO!");
			}
			System.out.println(ans);
		}
		long en = System.currentTimeMillis();
		System.out.println(en-st);
	}
	private static class Entry implements Comparable<Entry>
	{
		int id, next;
		Entry(int a, int b)
		{
			id = a;
			next = b;
		}
		public int compareTo(Entry e)
		{
			return Integer.valueOf(next).compareTo(e.next);
		}
	}
}
