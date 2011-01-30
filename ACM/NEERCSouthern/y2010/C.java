import java.util.*;
import static java.lang.Math.*;

public class C {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		D = new int[N];
		DP = new int[N][N+10];
		for(int[] d:DP)
			Arrays.fill(d, -1);
		G = new ArrayList[N];
		for(int i = 0; i < N;i++)
			G[i] = new ArrayList<Integer>();
		for(int i = 0; i < M;i++){
			int a = sc.nextInt()-1;
			int b = sc.nextInt()-1;
			G[a].add(b);
			G[b].add(a);
		}
		Arrays.fill(D, 100000);
		D[0] = 0;
		LinkedList<Integer> Q = new LinkedList<Integer>();
		Q.addFirst(0);
		while(Q.size() > 0){
			int at = Q.removeLast();
			for(int e:G[at]){
				if(D[e] > D[at]+1){
					D[e] = D[at]+1;
					Q.addFirst(e);
				}
			}
		}
		boolean ans = recur(0, 0);
		System.out.println(ans?"Vladimir":"Nikolay");
	}
	static int[] D;
	static int[][] DP;
	static ArrayList<Integer>[] G;
	//true if play to go wins
	public static boolean recur(int at, int time){
		if(DP[at][time] != -1)
			return DP[at][time] == 1;
		boolean canWin = false;
		for(int e:G[at]){
			if(D[e] <= time)
				continue;
			canWin |= !recur(e, time+1);
		}
		DP[at][time] = canWin ? 1 : 0;
		return canWin;
	}
	private static void p(Object...o) {
		System.out.println(Arrays.deepToString(o));		
	}
}
