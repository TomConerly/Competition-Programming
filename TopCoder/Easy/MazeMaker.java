package TopCoder.Easy;
import java.util.*;
import static java.lang.Math.*;

/* TopCoder SRM 453.5
 * Easy Problem 250 Points: MazeMaker
 * Type: Graph Theory
 * Solution: BFS.
 */

public class MazeMaker {
	public void p(Object s){System.out.println(s);}
	public int longestPath(String[] maze, int startRow, int startCol, int[] moveRow, int[] moveCol) {
		LinkedList<State> q = new LinkedList<State>();
		q.addLast(new State(startRow,startCol,0));
		boolean[][] R = new boolean[maze.length][maze[0].length()];
		R[startRow][startCol] = true;
		int maxDist = 0;
		while(q.size() > 0)
		{
			State s = q.poll();
			maxDist = max(s.dist,maxDist);
			for(int i = 0; i < moveRow.length;i++)
			{
				int nr = s.r + moveRow[i];
				int nc = s.c + moveCol[i];
				if(nr < 0 || nr >= maze.length || nc < 0 || nc >= maze[0].length())
					continue;
				if(maze[nr].charAt(nc) != '.')
					continue;
				if(R[nr][nc])
					continue;
				R[nr][nc] = true;
				q.addLast(new State(nr,nc,s.dist+1));
			}
		}
		for(int i = 0; i < maze.length;i++)
		{
			for(int j= 0; j < maze[0].length();j++)
			{
				if(R[i][j])
					continue;
				if(maze[i].charAt(j) == '.')
				{
					maxDist = -1;
				}
			}
		}
		return maxDist;
	}
	private static class State
	{
		int r, c, dist;
		State(int a, int b, int d)
		{
			r = a;
			c = b;
			dist = d;
		}
	}
	
}
