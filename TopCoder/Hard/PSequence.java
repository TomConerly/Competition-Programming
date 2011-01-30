package TopCoder.Hard;
/* TopCoder SRM 427
 * Hard Problem 900 Points: PSequence
 * Type: Number Theory/DP
 * Solution: We only care about the number of each type we have
 * where a type is the set of numbers who have the same value mod p.
 * Then DP over this, keep the list sorted so that there are fewer states.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PSequence {

	public int count(int[] s, int p) {
		int[] val = new int[p];
		for(int i = 0; i < s.length;i++)
		{
			int mod = (((s[i])%p)+p)%p; 
			val[mod]++;
		}
		ArrayList<Integer> classes = new ArrayList<Integer>();
		int leftOver = 0;
		for(int i = 0; i < p;i++)
		{
			if(val[i] > 1) classes.add(val[i]);
			else leftOver += val[i];
		}
		int[] c = new int[classes.size()];
		for(int i = 0; i < c.length;i++)
			c[i] = classes.get(i);
		m = new HashMap<Node,Long>();
		return (int)(recur(new Node(c,-1,leftOver))%mmm);

	}
	int mmm = 1234567891;
	private long recur(Node n) {
		boolean done = true;
		for(int i = 0; i < n.left.length;i++)
		{
			if(n.left[i] != 0) done = false;
		}
		if(done && n.extra == 0) return 1;

		if(m.containsKey(n)) return m.get(n);

		long ways = 0;
		for(int i = 0; i < n.left.length;i++)
		{
			if(n.left[i] <= 0 || i == n.prevClass) continue;

			n.left[i]--;
			Node a = new Node(n.left,i,n.extra);

			ways = (ways+recur(a)*(n.left[i]+1))%mmm;

			n.left[i]++;
		}
		if(n.extra > 0)
		{
			Node a = new Node(n.left,-1,n.extra-1);
			ways = (ways + recur(a)*n.extra) % mmm;
		}

		m.put(n, ways);
		return ways;

	}
	public HashMap<Node,Long> m;

	private class Node 
	{
		int[] left;
		int prevClass;
		int extra;
		public Node(int[] l, int p,int e)
		{
			extra = e;
			left = new int[l.length];
			for(int i = 0; i < l.length;i++)
				left[i] = l[i];

			if(p < 0)
			{
				prevClass = -1;
				Arrays.sort(left);
			}
			else {
				int pVal = l[p];

				Arrays.sort(left);
				for(int i = 0; i < left.length;i++)
				{
					if(left[i] == pVal)
					{
						prevClass = i;
						break;
					}
				}
			}
		}
		public int hashCode()
		{
			int hash = 0;
			for(int i = 0; i < left.length;i++)
				hash= hash*100+left[i];
			hash = hash*30+prevClass;
			hash = hash*30+extra;
			return hash;
		}
		public boolean equals(Object o)
		{
			Node a = (Node)o;
			if(extra != a.extra) return false;
			if(prevClass != a.prevClass) return false;
			if(left.length != a.left.length) return false;
			for(int i = 0; i < left.length;i++)
				if(left[i] != a.left[i]) return false;
			return true;
		}
	}

}
