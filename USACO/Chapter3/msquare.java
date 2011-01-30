package USACO.Chapter3;
/*
ID: theycal2
LANG: JAVA
TASK: msquare
 */
/* USACO Training
 * Magic Square
 * Type: Graph Search
 * Solution: Search the graph. I had to do some
 * fancy optimizations to make it run in time.
 * Mainly getting the index of a board (0 to 8!)
 * for quick comparisons.
 */
import java.io.*;
import java.util.*;

public class msquare 
{
	public static void main(String[] args) throws IOException 
	{
		long start = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("msquare.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("msquare.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int[] end = new int[8];
		for(int i = 0; i < 8;i++)
			end[i] = Integer.valueOf(st.nextToken());

		Board[] q = new Board[71000];
		q[0] = new Board();
		int l = 0; int h = 1;
		Board goal = new Board(end,0,new char[1]);
		int gi = goal.index();

		Board[] map = new Board[40320];
		
		Board ans = null;
		while(l != h)
		{
			Board b = q[l];
			l++;
			
			int idx = b.index();
			
			if(map[idx] != null && b.compareTo(map[idx]) >= 0)continue;
			map[idx] = b;
						
			if(idx == gi)
			{
				ans = b;
				break;
			}

			Board[] list = b.moves();
			for(int i = 0; i < 3;i++)
			{
				Board a = list[i];
				if(map[a.index()] == null)
				{
					q[h]=a;
					h++;
				}
			}
		}
		out.println(ans.cost);
		for(int i = 0; i < ans.moves.length-1;i++)
		{
			out.print(ans.moves[i]);
		}
		out.println();
		out.close();
		System.out.println("$:"+(System.currentTimeMillis()-start));
		System.exit(0);
	}
	private static class Board implements Comparable<Board>
	{
		int cost;
		int[] state;
		char[] moves;
		public Board()
		{
			cost = 0;
			state = new int[]{1,2,3,4,5,6,7,8};
			moves = new char[1];
		}
		public Board(int[] s,int c,char[] m)
		{
			cost = c;
			state = s;
			moves = new char[m.length+1];
			for(int i = 0; i < m.length;i++)
			{
				moves[i] = m[i];
			}
		}
		public Board[] moves()
		{
			Board[] ans = new Board[3];
			int[] newA = new int[8];
			newA[0] = state[7];
			newA[7] = state[0];
			newA[1] = state[6];
			newA[6] = state[1];
			newA[2] = state[5];
			newA[5] = state[2];
			newA[3] = state[4];
			newA[4] = state[3];
			moves[moves.length-1] = 'A';
			ans[0] = new Board(newA,cost+1,moves);

			int[] newB = new int[8];
			newB[0] = state[3];
			newB[1] = state[0];
			newB[2] = state[1];
			newB[3] = state[2];
			newB[4] = state[5];
			newB[5] = state[6];
			newB[6] = state[7];
			newB[7] = state[4];
			moves[moves.length-1] = 'B';
			ans[1] = new Board(newB,cost+1,moves);


			int[] newC = new int[8];
			newC[0] = state[0];
			newC[7] = state[7];
			newC[3] = state[3];
			newC[4] = state[4];
			newC[1] = state[6];
			newC[2] = state[1];
			newC[5] = state[2];
			newC[6] = state[5];
			moves[moves.length-1] = 'C';
			ans[2] = new Board(newC,cost+1,moves);

			return ans;
		}
		public int compareTo(Board arg0) {
			if(cost < arg0.cost) return -1;
			if(cost > arg0.cost) return 1;
			for(int i = 0; i < moves.length;i++)
			{
				if(moves[i] < arg0.moves[i]) return -1;
				if(moves[i] > arg0.moves[i]) return 1;
			}
			return 0;
		}
		public int index()
		{
			int idx;
			int[] c = new int[8];
			for(int i = 0; i < 8;i++)
			{
				c[i] = state[i];
			}
			idx = 0;
			for(int i = 0; i < 7;i++)
			{				
				idx = idx*(8-i)+(c[i]-1);
				for(int j = i+1;j<8;j++)
				{
					if(c[j] > c[i]) c[j]--;
				}
			}
			return idx;
		}
	}
}

