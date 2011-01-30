package CMUInvitional.y2009;
import java.util.*;

/* CMU Spring Invitational 2009
 * Problem H: Dominoes
 * Type: DP
 * Solution: Keep track of the frontier (where it is, and 3^n for where blocks are).
 * Always add a piece to the place furthest left and up so the frontier stays small
 */

public class H {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			W = sc.nextInt();
			H = sc.nextInt();
			if(W == 0 && H == 0) break;
			dp = new HashMap<State,Long>();
			State s = new State(0,new int[H]);
			long ans = recur(s);
			System.out.println(ans);
		}
	}
	public static long recur(State s)
	{
		if(dp.containsKey(s)) return dp.get(s);
		if(s.at == W){
			if(Arrays.equals(new int[H],s.edge)) return 1;
			else return 0;
		}
		if(s.at > W) return 0;
		long ans = 0;
		for(int i = 0; i < H;i++)
		{
			if(s.edge[i] == 0)
			{
				State t = new State(s.at,s.edge);
				t.edge[i] += 2;
				t.push();
				ans += recur(t);
				
				t = new State(s.at,s.edge);
				if(i+1 < H && t.edge[i] == 0 && t.edge[i+1] == 0)
				{
					t.edge[i] = 1;
					t.edge[i+1] = 1;
					t.push();
					ans += recur(t);
				}
				break;
			}
		}
		dp.put(s,ans);
		return ans;
	}
	static HashMap<State,Long> dp;
	static int W,H;
	private static class State 
	{
		int at;
		int[] edge;
		public State(int a, int[] e)
		{
			at = a;
			edge = e.clone();
		}
		public void push() {
			for(int i: edge)
			{
				if(i == 0) return;
			}
			at++;
			for(int i = 0; i < edge.length;i++)
				edge[i]--;
			push();
		}
		public boolean equals(Object o)
		{
			State s = (State)o;
			return at == s.at && Arrays.equals(edge,s.edge);
		}
		public int hashCode()
		{
			int hash = at;
			for(int i: edge)
				hash = hash*3+i;
			return hash;
		}
	}
}
