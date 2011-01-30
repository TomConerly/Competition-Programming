package UVaC;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception
	{		
		//System.setIn(new FileInputStream("main.in"));
		//long start = System.currentTimeMillis();
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		//StringBuilder output = new StringBuilder();
		
		while(true)
		{			
			String s = r.readLine();
			if(s == null) break;
			/*if(! first)
			{
				output.append("\n");
			}
			first = false;*/
			
			String[] parts = s.split(" ");
			move = Integer.valueOf(parts[0]);
			next = new boolean[2][2][2];
			for(int i = 0; i < 8;i++)
			{
				next[i>>2][(i&3)>>1][i&1] = ((move) & (1<<i)) != 0?true:false;
			}

			N = Integer.valueOf(parts[1]);
			
			s = parts[2];
			st = new boolean[N]; 
			for(int i = 0; i < N;i++)
			{
				st[i] = s.charAt(i) == '1'?true:false;
			}

			found = false;
			for(int i = 0; i < 2;i++)
				for(int j = 0; j < 2;j++)
					for(int k = 0; k < 2;k++)
						for(int l = 0; l < 2;l++)
							Arrays.fill(seen[i][j][k][l],false);
			
			recur(0,new int[N]);
			
			if(found)
				System.out.println("REACHABLE");
			//	output.append("REACHABLE");
			else
				System.out.println("GARDEN OF EDGE");
			//	output.append("GARDEN OF EDEN");
			
		}
		//System.out.println((System.currentTimeMillis()-start));
		
		//System.out.println(output);
		
	}
	static boolean[][][] next;
	static boolean found;
	static boolean[] st;
	static int N;
	static int move;
	static boolean[][][][][] seen = new boolean[2][2][2][2][33];;

	static void recur(int at, int[] set) {	
		
		if(at >= 4)
		{
			if(seen[set[0]][set[1]][set[at-2]][set[at-1]][at]) return;
			seen[set[0]][set[1]][set[at-2]][set[at-1]][at] = true;
		}
		
		if(at == N)
		{
			if(next[set[N-1]][set[0]][set[1]] != st[0]) return;
			if(next[set[N-2]][set[N-1]][set[0]] != st[N-1]) return;
			found = true;
			return;
		}
		if(at < 2)
		{
			set[at] = 1;
			recur(at+1,set);
			set[at] = 0;
			recur(at+1,set);
			return;
		}
		if(next[set[at-2]][set[at-1]][1] == st[at-1])
		{
			set[at] = 1;
			recur(at+1,set);
		}
		if(next[set[at-2]][set[at-1]][0] == st[at-1])
		{
			set[at] = 0;
			recur(at+1,set);
		}
	}
}	

