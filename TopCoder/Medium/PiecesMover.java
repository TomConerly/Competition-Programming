package TopCoder.Medium;
/* TopCoder SRM 425
 * Med Problem 500 Points: PiecesMover
 * Type: Simulation
 * Solution: My method is just to run Dijkstra's
 * which is a pain, but works.
 */

import java.util.*;
import static java.lang.Math.*;

public class PiecesMover {

	@SuppressWarnings("unchecked")
	public int getMinimumMoves(String[] board) {
		PriorityQueue<Long> pq = new PriorityQueue<Long>();
		HashMap<Long,Integer> map = new HashMap<Long,Integer>();

		ArrayList<Integer> pieces= new ArrayList<Integer>();
		for(int i = 0; i < 5;i++)
		{
			for(int j = 0; j < 5;j++)
			{
				if(board[i].charAt(j) == '*')
				{
					pieces.add((i<<3)|j);
				}
			}
		}
		N = pieces.size();

		pq.add(pack(pieces));
		while(pq.size() > 0)
		{
			long a = pq.poll();
			long k = a & 0xFFFFFFFFL;
			int c = (int)(a >>> 32);
			
			if(map.containsKey(k))
				continue;
			map.put(k, c);

			ArrayList<Integer> unpack = unpack(k);
			
			if(connected(unpack)) return c;

			for(int i = 0; i < N;i++)
			{
				int piece = unpack.get(i);
				int x = piece>>>3;
				int y = piece & 0x7;

				for(int j = 0; j < dir.length;j++)
				{
					ArrayList<Integer> copy = (ArrayList<Integer>)unpack.clone();
					copy.remove(i);
					
					
					if(x+dir[j][0] < 0 || x+dir[j][0] >= 5 || y+dir[j][1] < 0 || y+dir[j][1] >= 5) continue;
					int newp = ((x+dir[j][0])<<3)|(y+dir[j][1]);

					if(copy.contains(newp)) continue;
					copy.add(newp);
					
					
				
					long test = pack(copy);

					if(map.get(test)== null || map.get(test) > c+1)
					{
						pq.add((((long)c+1)<<32) | test);
					}
				}
			}

		}
		return -1;
	}
	public void print(ArrayList<Integer> a)
	{
		for(int i = 0; i < a.size();i++)
		{
			int piece = a.get(i);
			int x = piece>>>3;
						int y = piece &0x7;

						System.out.print("("+x+","+y+") ");
		}
		System.out.println();
	}
	int[][] dir = {{1,0},{-1,0},{0,-1},{0,1}};
	public boolean connected(ArrayList<Integer> a)
	{
		visited = new boolean[N];
		dfs(0,a);
		for(int i = 0; i < a.size();i++)
		{

			if(!visited[i]){
				return false;
			}
		}
		return true;
	}
	boolean[] visited;
	public void dfs(int at, ArrayList<Integer> a)
	{
		if(visited[at] == true) return;
		
		visited[at] = true;
		int piece = a.get(at);
		int mx = piece>>>3;
					int my = piece &0x7; 
					for(int i = 0; i < a.size();i++)
					{
						if(visited[i]) continue;
						piece = a.get(i);
						int x = piece>>>3;
						int y = piece &0x7;

						if(abs(mx-x)+abs(my-y) == 1)
						{
							dfs(i,a);
						}
					}
	}

	int N;
	public ArrayList<Integer> unpack(Long a)
	{
		ArrayList<Integer> an = new ArrayList<Integer>();
		for(int i = 0; i < N;i++)
		{
			long t = a >>> (i*6);
			an.add((int)(t &0x3F));
		}
		return an;
	}


	public long pack(ArrayList<Integer> at)
	{
		Collections.sort(at);
		long a = 0;
		for(int i = 0; i < N;i++)
		{
			a |= (at.get(i) << (6*i));
		}
		return a;
	}
}
