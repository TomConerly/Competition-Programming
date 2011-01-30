package SSU;
import java.math.BigInteger;
import java.util.*;

/* Saratov State University Online Judge
 * Problem 269: Rooks
 * Type: DP
 * Solution: Start with the column furthest out either place something in it or don't. Keep track of which column you are at and how many you 
 * have placed. It doesn't matter where you place one in this model because after moving to the left all columns you could have placed it in
 * are isomorphic.
 */

public class p269 {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		B = new int[N];
		S = new int[251];
		for(int i = 0; i < N;i++){
			B[i] = sc.nextInt();
			for(int j = 0; j <= B[i];j++)
				S[j]++;
		}
		DP = new BigInteger[251][251];
		BigInteger ans = recur(250,0);
		System.out.println(ans);
	}
	static int N,K;
	static int[] B;
	static int[] S;
	static BigInteger[][] DP;
	static BigInteger recur(int at, int placed){
		if(DP[at][placed] != null)
			return DP[at][placed];
		if(at == 0)
			return placed == K? BigInteger.ONE:BigInteger.ZERO;
		
		BigInteger ans = recur(at-1,placed);
		if(placed < K)
		ans = ans.add(recur(at-1,placed+1).multiply(BigInteger.valueOf(S[at]-placed)));
		DP[at][placed] = ans;
		return ans;
	}
	
}
