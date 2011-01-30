package TopCoder.Div2Hard;
import java.util.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

/* TopCoder SRM 461 Div 2
 * Hard Problem 1000 Points: NameInput
 * Type: DP
 * Solution: DP[matched in name][location in prediction] is O(2500^2) size. For each position find the closest
 * letter of each type above and below. W.L.O.G. it is always optimal to move to the closest match above or below.
 * So compute this first then the DP is O(2500^2) and precomputation is O(2500^2).
 */

public class NameInput {
	public void p(Object... s){System.out.println(deepToString(s));}
	public int countUpDownKeyPresses(String[] predictionSequence, String[] name) {
		S = "";
		for(String s:predictionSequence)
			S += s;
		N = "";
		for(String n:name)
			N += n;
		close1 = new int[S.length()][256];
		for(int[] c:close1)
			Arrays.fill(c,-1);
		for(int i = 0; i < S.length();i++)
			for(int j = 0; j < S.length();j++)			
				if(close1[i][S.charAt(j)] == -1 || dist1(i,j) < dist1(i,close1[i][S.charAt(j)]))
					close1[i][S.charAt(j)] = j;

		close2 = new int[S.length()][256];
		for(int[] c:close2)
			Arrays.fill(c,-1);
		for(int i = 0; i < S.length();i++)
			for(int j = 0; j < S.length();j++)			
				if(close2[i][S.charAt(j)] == -1 ||dist2(i,j) < dist2(i,close2[i][S.charAt(j)]))
					close2[i][S.charAt(j)] = j;


		DP = new int[N.length()+1][S.length()];
		for(int[] D:DP)
			Arrays.fill(D,BIG);
		for(int i = 0; i < S.length();i++)
			DP[N.length()][i] = 0;

		for(int i = N.length()-1;i>= 0;i--)
		{
			for(int j = 0; j < S.length();j++)
			{
				if(close1[j][N.charAt(i)] != -1)
					DP[i][j] = min(DP[i][j],DP[i+1][close1[j][N.charAt(i)]]+dist(j,close1[j][N.charAt(i)]));
				if(close2[j][N.charAt(i)] != -1)
					DP[i][j] = min(DP[i][j],DP[i+1][close2[j][N.charAt(i)]]+dist(j,close2[j][N.charAt(i)]));
			}
		}
		return DP[0][0] >= BIG ? -1:DP[0][0];
	}	
	static int BIG = 10000000;
	private int dist(int a, int b) {
		return min(dist1(a,b),dist2(a,b));
	}
	private int dist1(int a, int b) {
		if(a >= b)
			return a-b;
		return a+(S.length()-b);
	}
	private int dist2(int a, int b) {
		if(b >= a)
			return b-a;
		return b+(S.length()-a);
	}
	static int DP[][];
	static int close1[][],close2[][];
	static String S,N;
}
