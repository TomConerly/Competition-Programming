package GCJ.y2009.Round1B;
import java.util.*;

/* Google Code Jam 2009 Round 1C
 * Problem C: Square Math
 * Type: Dijkstra
 * Solution: Dijkstra starting from any numeric square. Keep track for each board location and sum what is
 * the best value for that square.
 */


public class C {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		hs = new HashMap<State,String>();
		int T = sc.nextInt();
		for(int zz = 1; zz <= T;zz++)
		{
			W = sc.nextInt();
			Q = sc.nextInt();
			hs.clear();
			board = new char[W][W];
			for(int i = 0; i < W;i++)
			{
				String s = sc.next();
				for(int j = 0; j < W;j++)
				{
					board[i][j] = s.charAt(j);
				}
			}
			System.out.println("Case #"+zz+":");
			int[] to = new int[Q];
			for(int i = 0; i < Q;i++)
			{
				to[i] = sc.nextInt();
			}
			String[] ans = solve(to);
			for(String s:ans)
				System.out.println(s);
		}
	}
	private static String[] solve(int[] sums) 
	{
		int max = 0;
		for(int a:sums)
			max = Math.max(a,max);
		String[] ans = new String[sums.length];
		String[] tans = new String[260];
		Arrays.fill(tans,"!");
		for(int a:sums)
			tans[a] = null;

		PriorityQueue<State> q = new PriorityQueue<State>();
		int found = 0;
		int foundLen = -1;
		for(int i = 0; i < W;i++)
			for(int j = 0; j < W;j++)
				if(Character.isDigit(board[i][j]))
					q.add(new State (i,j,board[i][j]-'0',""+board[i][j],board[i][j]));
		
		while(q.size() > 0)
		{			
			State s = q.poll();
			if(foundLen != -1 && s.s.length() > foundLen)
				break;
			if(hs.containsKey(s))
			{
				String old = hs.get(s);
				if(better(s.s,old))
				{
					hs.put(s, s.s);
				}else{
					continue;
				}
			}else{
				hs.put(s,s.s);
				
			}
			if(s.prev != '+' && s.prev != '-')
			{
				if(s.w >= 0 && s.w < 260 && (tans[s.w] == null || !tans[s.w].equals('!')))
				{
					if(tans[s.w] == null || better(s.s,tans[s.w]))
					{
						if(tans[s.w] == null)
						{
							found++;
							if(found == ans.length)
								foundLen = s.s.length();
						}
						tans[s.w] = s.s;
					}
				}
			}
			for(int i = 0; i < 4;i++)
			{
				int nx = s.x+dx[i];
				int ny = s.y+dy[i];
				if(nx < 0 || nx >= W || ny < 0 || ny >= W)
					continue;

				if(s.prev == '+' || s.prev == '-')
				{
					q.add(new State(nx,ny,s.w + (s.prev == '+' ? (board[nx][ny]-'0'):-(board[nx][ny]-'0')),s.s+board[nx][ny],board[nx][ny]));
				}
				else
				{
					q.add(new State(nx,ny,s.w,s.s+board[nx][ny],board[nx][ny]));
				}
			}
		}
		for(int i = 0; i < sums.length;i++)
			ans[i] = tans[sums[i]];
		return ans;
	}
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	private static boolean better(String s, String old) {
		return s.length() < old.length() || (s.length() == old.length() && s.compareTo(old) < 0);
	}
	static HashMap<State,String> hs;
	static int W,Q;
	static char[][] board;

	private static class State implements Comparable<State>
	{
		int x, y,w;
		String s;
		char prev;
		State(int a, int b, int c, String d, char e)
		{
			x = a;y=b;w=c;
			s = d;
			prev = e;
		}		
		public int hashCode()
		{
			return (x)|(y<<5)|(w<<10);
		}
		public boolean equals(Object o)
		{
			State s = (State)o;
			return x == s.x && y == s.y && w == s.w;
		}
		
		public int compareTo(State t) 
		{
			if(s.length() < t.s.length()) return -1;
			if(s.length() > t.s.length()) return 1;
			return s.compareTo(t.s);
		}
	}
}
