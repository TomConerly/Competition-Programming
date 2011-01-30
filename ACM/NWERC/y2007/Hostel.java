package ACM.NWERC.y2007;
import java.util.*;
import static java.lang.Math.*;

/* ACM NWERC 2007
 * Problem D: Hostel
 * Type: DP
 * Solution:Fill in row by row in order. Keep track of the previous row (for each col the lowest placed)
 * where to fill next, and if the entrance has been placed. Enforce that all empty areas are connected
 * and that all beds are connected.
 */

public class Hostel 
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int zz = 0; zz < T;zz++)
		{
			H = sc.nextInt();
			W = sc.nextInt();
			hm = new HashMap<State,Integer>();
			int[] st = new int[W];
			Arrays.fill(st,-2);
			State s = new State(st,0,false);
			String ans = "";
			boolean edge = false;
			System.out.println(score(s));
			while(s.idx != H*W)
			{
				if(score(s.empty(true)) == score(s))
				{
					if(!edge &&( s.idx < W || s.idx %W == 0 || s.idx %W == W-1 || s.idx > W*(H-1)))
					{
						ans = ans + "E";
						edge = true;
					}else{
						ans = ans +".";
					}
					s = s.empty(true);			
				}
				else if(score(s.empty(false)) == score(s))
				{
					s = s.empty(false);
					ans = ans +".";
				}
				else
				{
					s = s.bed();
					ans = ans +"B";
				}
				if(s.idx%W == 0)
					ans = ans +"\n";
			}
			System.out.println(ans);
		}
	}
	public static int score(State s)
	{
		if(s == null)
			return -100000;
		if(s.idx == H*W)
		{
			if(s.valid())
				return 0;
			else
				return -100000;
		}
		s.canon();
		if(hm.containsKey(s))
			return hm.get(s);

		State a = s.empty(true);
		State b = s.empty(false);
		State c = s.bed();
		int ans = max(score(a),max(score(b),score(c)+1));
		hm.put(s,ans);
		return ans;
	}

	static int H,W;
	static HashMap<State,Integer> hm;
	private static class State
	{
		public void canon()
		{
			int next = 1;
			for(int i = 0; i < W;i++)
			{
				if(board[i] >0)
					board[i] += 100;
			}
			for(int i = 0; i < W;i++)
			{
				if(board[i] >0)
					replace(board[i],next++);
			}
		}
		boolean edge;
		int[] board;
		int idx;
		public String toString()
		{
			return idx+" "+Arrays.toString(board)+" "+edge;
		}
		State(int[] b, int i, boolean b1)
		{
			board = b;
			idx = i;
			edge = b1;
		}
		public boolean valid()
		{
			if(!edge)
				return false;
			for(int i = 0; i < W;i++)
			{
				if(board[i] != 0 && board[i] != -2)
					return false;
			}
			return true;
		}

		public boolean equals(Object o)
		{
			State s = (State)o;
			return Arrays.equals(s.board,board) && idx == s.idx && s.edge == edge;
		}
		public int hashCode()
		{
			int ans = idx;
			for(int i = 0; i < board.length;i++)			
				ans = ans*7+(board[i]+2);			
			
			ans= ans*2 + (edge? 1:0);
			return ans;
		}
		State empty(boolean useE)
		{
			State s = new State(Arrays.copyOf(board,W),idx+1,edge);
			int loc = idx%W;
			int v;
			if(useE && !s.edge && (idx < W || idx %W == 0 || idx %W == W-1 || idx > W*(H-1)))
			{
				s.edge = true;
				v = 0;
				if(loc != 0 && s.board[loc-1] >= 0)
					s.board[loc-1] = 0;
				if(loc != 0 && s.board[loc-1] < 0)
					s.board[loc-1] = -2;
			}else{
				if(loc != 0 && s.board[loc-1] >= 0)
				{
					v = s.board[loc-1];				
				}else{
					v = s.maxx()+1;
					if(loc > 0)
						s.board[loc-1] = -2;
				}
			}
			if(idx >= W)
			{
				if(s.board[loc] >=0)
				{
					if(s.board[loc] == 0)
					{						
						s.replace(v,0);
						v = 0;
					}
					else
					{
						s.replace(s.board[loc],v);					
					}
				}
			}			
			s.board[loc] = v;
			return s;
		}		
		State bed()
		{
			State s = new State(Arrays.copyOf(board,W),idx+1,edge);
			int loc = idx%W;	
			int v = -1;
			if(idx >= W)
			{
				if(s.board[loc] == 0)
					v = -2;
				if(s.board[loc] > 0)
				{

					int count = 0;
					for(int i = 0; i < W;i++)
						if(s.board[i] == s.board[loc])
							count++;
					if(count == 1)
						return null;
				}else{
					if(s.board[loc] == -1)
						return null;
				}
			}
			if(loc != 0 && s.board[loc-1] >= 0)
				v = -2;
			s.board[loc] = v;
			return s;
		}
		private void replace(int v, int with) 
		{
			for(int i = 0; i < W;i++)			
				if(board[i] == v)
					board[i] = with;
						
		}
		private int maxx() 
		{
			int m = 0;
			for(int i = 0; i < W;i++)
				m = max(m,board[i]);
			return m;
		}

	}
}
