package TopCoder.Medium;
import java.util.*;
import static java.lang.Math.*;

/* TopCoder SRM 392
 * Medium Problem 500 Points: RoundAboutCircle
 * Type: Graph Theory
 * Solution: All the nodes with zero incoming edges can be removed
 * and the nodes they point to give a counter that there is one node we can
 * use to go to them. After all nodes with zero incoming edges are out the
 * graph just has cycles left so we can find one cycle and not have to visit
 * any one of those nodes again. 
 */

public class RoundAboutCircle {

	int n;
	private class Node
	{
		int at,in,count;
		Node(int a)
		{
			at = a;
		}
	}
	public int maxScore(int N) {
		n = N;
		ArrayList<Node> nodes = new ArrayList<Node>();
		HashMap<Integer,Node> hm = new HashMap<Integer,Node>();
		for(int i = 0; i < N;i++)
		{
			nodes.add(new Node(i+1));
			hm.put(i+1,nodes.get(i));
		}
		for(Node n: nodes)
		{
			hm.get(digit(n.at)).in++;
		}
		Queue<Node> toCheck = new LinkedList<Node>();
		for(int i = 0; i < N;i++)
		{
			if(nodes.get(i).in == 0) toCheck.add(nodes.get(i));
		}
		boolean[] out = new boolean[N];
		int ans = 0;
		while(toCheck.size() > 0)
		{
			Node n = toCheck.poll();
			if(out[n.at-1]) continue;
			Node to = hm.get(digit(n.at));
			to.in--;
			to.count = max(to.count,n.count+1);
			ans = max(ans,to.count+1);
			if(to.in == 0)
			{
				toCheck.add(to);
			}
			out[n.at-1] = true;
		}
		ArrayList<Node> na = new ArrayList<Node>();
		for(int i = 0; i < N;i++)
		{
			if(!out[i]) na.add(nodes.get(i));
		}
		int[] cycle = new int[N];
		Arrays.fill(cycle,-1);
		for(int i = 0; i < na.size();i++)
		{			
			if(cycle[na.get(i).at-1] != -1) continue;
			int at = na.get(i).at;
			int count = 0;
			int start = at;
			int max = 0;
			while(true)
			{
				cycle[at-1] = i;
				max = max(max,hm.get(at).count);
				count++;
				int next = digit(at);
				at = next;
				
				if(next == start) break;
			}
			ans = max(ans,max+count);
		}
		return ans;
	}
	public int digit(int a)
	{
		int ans = 0;
		int t =a;
		while(a != 0)
		{
			ans += a%10;
			a/=10;
		}
		ans += t;
		
		while(ans > n)
			ans -= n;
		return ans;
	}

	void print(int[] a)
	{
		for(int b:a)
			System.out.print(b+" ");
		System.out.println();
	}

}
