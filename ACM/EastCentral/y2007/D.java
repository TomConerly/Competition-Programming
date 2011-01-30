package ACM.EastCentral.y2007;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;


/* ACM ICPC 2007 East Central Regional
 * Problem D: Lemmings
 * Type: Simulation
 * Solution: Simulate the game, but do it in a smart way.
 */

public class D {
	static int R,C;
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		for(int z= 1;;z++)
		{
			R = s.nextInt();
			C = s.nextInt();
			if(R==0 && C==0) break;
			String[] order = new String[R*C];
			s.nextLine();
			int at = 0;
			while(at < R*C)
			{
				String[] input = s.nextLine().split(" ");
				for(int i = 0; i < input.length;i++)
				{
					order[at+i] = input[i];
				}
				at += input.length;
			}
			int[][] board = new int[R][C];
			int[] pref = new int[R*C];
			LinkedList<Integer> live = new LinkedList<Integer>();
			int[] loc = new int[R*C];
			for(int i = 0; i < R;i++)
			{
				for(int j = 0; j < C;j++)
				{
					board[i][j] = i*C+j;
					live.add(i*C+j);
					loc[i*C+j] = (i<<16)|j;
				}
			}
			int ans = 0;
			int[] move = new int[R*C];
			int[] old = new int[R*C];
			
			
			while(true)
			{
				int[][] bad = new int[R][C];
				//find moves
				Arrays.fill(move,-1);
				for(int l: live)
				{
					old[l] = loc[l];
					move[l] = next(loc[l]>>>16,loc[l]&0xFFFF,order[l].charAt(pref[l]));
					if(move[l] != -1)
						bad[move[l] >>> 16][move[l]&0xFFFF]++;
				}
				//find collisions
				
				//resolve collisions
				while(true)
				{
					boolean done = true;
					for(int i: live)
					{
						if(move[i] == -1) continue;
						if(move[i] == old[i]) continue;
						if(bad[move[i] >>> 16][move[i]&0xFFFF]>1)
						{
							move[i] = old[i];
							pref[i] = (1+pref[i])%4;
							bad[move[i] >>> 16][move[i]&0xFFFF]=5;
							done = false;
						}
					}
					if(done) break;
				}
				//move
				int alive = 0;
				for(int k = 0; k < live.size();k++)
				{
					int i = live.get(k);
					loc[i] = move[i];
					if(move[i] == -1)
					{
						live.remove(k);
						k--;
						continue;
					}
					
					alive++;
				}
				ans++;
				if(alive == 0) break;
			}
			System.out.println("Case "+z+": "+ans);
		}
	}
	public static int next(int i, int j, char c)
	{
		if(c == 'N') i++;
		if(c == 'E') j++;
		if(c == 'S') i--;
		if(c == 'W') j--;
		if(i < 0 || j < 0 || i >= R || j >= C) return -1;
		else return (i<<16) | j;
	}
}
