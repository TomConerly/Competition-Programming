package USACO.Chapter2;
/*
ID: theycal2
LANG: JAVA
TASK: ttwo
 */
/* USACO Training
 * The Tamworth Two
 * Type: Simulation
 * Solution: Just look for cycles.
 */
import java.io.*;

public class ttwo 
{
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("ttwo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ttwo.out")));
		
		board = new boolean[10][10];
		int fr=0,fc=0,cr=0,cc=0;
		for(int i = 0; i < 10;i++)
		{
			String line = f.readLine();
			for(int j = 0; j < 10;j++)
			{
				if(line.charAt(j) == '*')
				{
					board[i][j] = true;
				}
				else if(line.charAt(j) == 'F')
				{
					fr = i;
					fc = j;
				}
				else if(line.charAt(j) == 'C')
				{
					cr = i;
					cc = j;
				}
			}
		}
		visited = new boolean[10][10][10][10][10][10];
		visited[fr][fc][0][cr][cc][0] = true;
		int fDir=0, cDir=0;
		int time = 0;
		boolean found = false;
		while(true)
		{
			time++;
			if(legal(fr+dir[fDir][0],fc+dir[fDir][1]))
			{
				fr += dir[fDir][0];
				fc += dir[fDir][1];
			}else{
				fDir = (fDir+1)%4;
			}
			if(legal(cr+dir[cDir][0],cc+dir[cDir][1]))
			{
				cr += dir[cDir][0];
				cc += dir[cDir][1];
			}else{
				cDir = (cDir+1)%4;
			}
			
			if(fr == cr && fc == cc)
			{
				found = true;
				break;
			}
			
			if(visited[fr][fc][fDir][cr][cc][cDir]) break;
				visited[fr][fc][fDir][cr][cc][cDir] = true;
		}
		if(found) out.println(time);
		else out.println(0);
		out.close();
		System.exit(0);
	}
	static boolean legal(int r, int c)
	{
		if(r < 0 || r >= 10 || c < 0 || c >= 10) return false;
		if(board[r][c]) return false;
		return true;
	}
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
	static boolean[][][][][][] visited;
	static boolean[][] board;
	
}

