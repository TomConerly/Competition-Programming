package ACM.WorldFinals.y2007;

import java.util.*;
import java.io.*;
import static java.lang.Math.*;

/* 2007 World Finals
 * Problem F: Marble Game
 * Type: Brute Force
 * Difficulty Coding: 4
 * Algorithmic Difficulty: 2
 * Solution: We belive that you can simply brute force this. Even in the
 * case where there are a lot of balls there aren't that many states (500 mil)
 * and many will be pruned quickly when a ball falls into a hole that isn't
 * correcty. Also some states won't be reachable.
 */ 

public class F {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File("F.in"));
		for(int z =1;;z++)
		{
			N = sc.nextInt();
			int M = sc.nextInt();
			int W = sc.nextInt();
			if(N==0 && M == 0 && W == 0) break;
			State s = new State();
			s.b = new int[N][N];
			
			for(int i = 0; i < M;i++)
			{
				int x = sc.nextInt();
				int y = sc.nextInt();
				s.b[x][y] = i+1;
			}
			for(int i = 0; i < M;i++)
			{
				int x = sc.nextInt();
				int y = sc.nextInt();
				s.b[x][y] = -(i+1);
			}
			wall = new boolean[4][N][N][N][N];
			for(int i = 0; i < W;i++)
			{
				int i1 = sc.nextInt();
				int j1 = sc.nextInt();
				int i2 = sc.nextInt();
				int j2 = sc.nextInt();
				wall[0][i1][j1][i2][j2] = true;
				wall[0][i2][j2][i1][j1] = true;
				//System.out.println(i1+" "+j1+" "+i2+" "+j2);
			}
			for(int r = 1; r< 4;r++)
			{
				for(int i1 = 0; i1 < N;i1++)
					for(int j1 = 0; j1<N;j1++)
						for(int i2=0; i2<N;i2++)
							for(int j2 = 0; j2 < N;j2++)
								wall[r][i1][j1][i2][j2] = wall[r-1][j1][N-i1-1][j2][N-i2-1];
			}
			/*for(int r = 0; r< 4;r++)
			{
				for(int i1 = 0; i1 < N;i1++)
					for(int j1 = 0; j1<N;j1++)
						for(int i2=0; i2<N;i2++)
							for(int j2 = 0; j2 < N;j2++)
								if(wall[r][i1][j1][i2][j2])
									System.out.println(r+" "+i1+" "+j1+" "+i2+" "+j2);
			}*/
			HashSet<State> hs = new HashSet<State>();
			LinkedList<State> q = new LinkedList<State>();
			q.add(s);
			int ans = -1;
			hs.add(s);
			while(q.size() > 0)
			{
				State at = q.poll();

				if(at.end())
				{
					ans = at.cost;
					break;
				}

				State[] ch = at.getChildren();
				//if(at.cost >= 2) break;
				//System.out.println(at);
				
				for(State ss: ch)
				{			
					if(ss== null) continue;
					if(hs.contains(ss)) continue;
					hs.add(ss);
					q.add(ss);
				}
			

			}
			if(ans == -1) System.out.println("Case "+z+": impossible\n");
			else System.out.println("Case "+z+": "+ans+" moves\n");
		//	System.exit(0);
		}
	}
	static boolean[][][][][] wall;
	static boolean wall(int rot, int i1, int j1, int i2, int j2)
	{
		if(i1 < 0 || i1 >= N || j1 < 0 || j1>=N || i2 < 0 || i2>=N || j2 < 0 || j2>=N) return true;
		return wall[rot][i1][j1][i2][j2];
	}
	static int N;
	private static class State{
		int rot;
		int[][] b;
		int cost;
		State[] getChildren()
		{
			State[] st = new State[4];
			for(int i = 0; i < 4;i++)
			{

				st[i] = copy();

				for(int j = 0; j < i;j++)
				{
					st[i] = st[i].rotate();
				}

				st[i].cost = cost+1;
				st[i] = st[i].down();
			}
			return st;
		}
		public boolean equals(Object o)
		{
			State s = (State)o;
			if(rot != s.rot) return false;
			for(int i = 0; i < N;i++)
				for(int j = 0; j < N;j++)
					if(s.b[i][j] != b[i][j]) return false;
			return true;
		}
		public int hashCode(){
			int hc = 0;
			for(int i = 0; i < N;i++)
				for(int j = 0; j < N;j++)
					hc = hc*100+b[i][j];
			return hc;				
		}
		public boolean end() {
			for(int i = 0; i < N;i++)
				for(int j = 0; j < N;j++)
					if(b[i][j] != 0) return false;
			return true;
		}
		private State down() {
			@SuppressWarnings("unused")
			int[] temp = new int[N+10];
			@SuppressWarnings("unused")
			int[] hole = new int[N+10];
			/*for(int j = 0; j < N;j++)
			{
				int st = 0;
				int at = 0;
				int holeAt = 0;
				for(int i = 0; i < N;i++)
				{
					if(b[i][j] < 0)
					{
						hole[holeAt++]=b[i][j];

					}else if(b[i][j] > 0)
					{
						if(holeAt != 0)
						{
							if(b[i][j] != abs(hole[holeAt-1])) return null;

							for(int m = 0; m < N;m++)
								for(int n = 0; n < N;n++)
									if(b[m][n] == hole[holeAt-1]) b[m][n] = 0;
							holeAt--;
						}else{
							temp[at++] = b[i][j];
						}
						b[i][j] = 0;
					}
					if(wall(rot,i,j,i+1,j))
					{
						int a = 0;
						for(int k = st; a < at;k++)
						{
							b[k][j] = temp[a++];
						}			
						at = 0;
						holeAt = 0;
						st = i+1;
					}
				}
			}*/
			for(int i= 1;i<N;i++)
			{
				for(int j = 0; j < N;j++)
				{
					if(b[i][j] > 0)
					{
						int at = i;
						int ball = b[i][j];
						while(wall(rot,at,j,at-1,j) == false && at > 0 && b[at-1][j] <= 0)
						{
							if(b[at-1][j] == 0) 
							{
								b[at-1][j] = ball;
								b[at][j] = 0;
							}else if(b[at-1][j] < 0)
							{
								if(abs(b[at-1][j]) != ball) return null;
								else {
									b[at-1][j] = 0;
									b[at][j] = 0;
									break;
								}
								
							}
							at--;
						}
					}
				}
			}
			return this;

		}

		private State rotate() {
			State s = new State();
			int[][] n = new int[N][N];
			for(int i = 0; i < N;i++)
				for(int j = 0; j < N;j++)
					n[i][j] = b[j][N-i-1];
			s.rot = (rot+1)%4;
			s.b = n;
			return s;
		}
		private State copy() {
			State s = new State();
			s.b = new int[N][N];
			for(int i = 0; i < N;i++)
				for(int j = 0; j < N;j++)
					s.b[i][j] = b[i][j];
			s.rot = rot;
			return s;
		}
		public String toString()
		{
			String ans = rot+" "+cost+"\n";
			for(int i = 0; i < N;i++)
			{
				for(int j = 0; j < N;j++)
				{
					if(j==0) ans += (b[i][j] >= 0?" ":"")+b[i][j];
					else ans += " "+(b[i][j] >= 0?" ":"")+b[i][j];
				}
				ans += "\n";
			}
			ans += "\n";
			return ans;
		}
	}
}
