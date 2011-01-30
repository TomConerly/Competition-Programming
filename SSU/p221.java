package SSU;
import java.math.BigInteger;
import java.util.*;

/* Saratov State University Online Judge
 * Problem 221: Big Bishops & Problem 220: Little Bishops
 * Type: DP
 * Solution: You should be able to solve 220 with a simple back tracking search. 
 * 
 * Rotate the board by 45 degrees and draw it as a axis aligned board. You get a series of shapes which look like:
 *        X        X      XX
 *       XXX      XXX    XXXX
 *      XXXXX or  XXX or XXXX
 *       XXX       X      XX
 *        X
 * 
 * All of them can be solved by a simliar DP. Start from the middle and either place a bishop at the end or not.
 * Then mvoe outwards. Keep track of where you are and how many you have placed. When you are working you ignore
 * things that are above and below all of the middle columns become isomorhpic. 
 */

public class p221 {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		BigInteger ans;
		if(N == 1){
			if(K <= 1)
				ans = BigInteger.ONE;
			else
				ans = BigInteger.ZERO;
		}else if(N*2 < K){
			ans = BigInteger.ZERO;
		}else if(N%2 == 0)
			ans = even();
		else
			ans = odd();
		System.out.println(ans);
	}
	static int N,K;

	static BigInteger[][][] DP2,DP3;
	static BigInteger recur2(int n, int k, int need){
		if(n == -1)
			return k == need ? BigInteger.ONE:BigInteger.ZERO;
		if(DP2[n][k][need] != null)
			return DP2[n][k][need];
		BigInteger ans;
		if(N == n){
			ans = recur2(n-2,k,need);
			if(k < need)
				ans = ans.add(recur2(n-2,k+1,need).multiply(bi(2)));
		}else if(n == 1){
			ans = recur2(n-2,k,need);
			if(k < need)
				ans = ans.add(recur2(n-2,k+1,need).multiply(bi((N-n+1-k))));
		}else{
			ans = recur2(n-2,k,need);
			if(k < need)
				ans = ans.add(recur2(n-2,k+1,need).multiply(bi(2*(N-n+1-k))));
			if(k+1 < need)
				ans = ans.add(recur2(n-2,k+2,need).multiply(bi((N-n+1-k)*(N-n-k))));
		}
		DP2[n][k][need] = ans;
		return ans;
	}
	static BigInteger recur3(int n, int k, int need){
		if(n == 0)
			return k == need ? BigInteger.ONE:BigInteger.ZERO;
		if(DP3[n][k][need] != null)
			return DP3[n][k][need];
		BigInteger ans;
		ans = recur3(n-2,k,need);
		if(k < need)
			ans = ans.add(recur3(n-2,k+1,need).multiply(bi(2*(N-n+1-k))));
		if(k+1 < need)
			ans = ans.add(recur3(n-2,k+2,need).multiply(bi((N-n+1-k)*(N-n-k))));

		DP3[n][k][need] = ans;
		return ans;
	}
	private static BigInteger odd() {
		DP2 = new BigInteger[N+1][K+1][K+1];
		DP3 = new BigInteger[N+1][K+1][K+1];
		BigInteger ans = BigInteger.ZERO;
		for(int i = 0; i <= K;i++)
			ans = ans.add(recur2(N,0,i).multiply(recur3(N-1,0,K-i)));
		return ans;
	}

	static BigInteger[][][] DP;
	private static BigInteger even() {
		DP = new BigInteger[N+1][K+1][K+1];
		BigInteger ans = BigInteger.ZERO;
		for(int i = 0; i <= K;i++)
			ans = ans.add(recur(N,0,i).multiply(recur(N,0,K-i)));
		return ans;
	}
	static BigInteger bi(long l){
		return BigInteger.valueOf(l);
	}
	private static BigInteger recur(int n, int k,int need) {
		if(DP[n][k][need] != null)
			return DP[n][k][need];
		if(n == 0)
			return k == need ? BigInteger.ONE:BigInteger.ZERO;
		BigInteger ans;
		if(n == N){
			ans = recur(n-2,k,need);
			if(k < need)
				ans = ans.add(recur(n-2,k+1,need).multiply(bi(2)));
		}else{
			ans = recur(n-2,k,need);
			if(k < need)
				ans = ans.add(recur(n-2,k+1,need).multiply(bi(2*(N-n+1-k))));
			if(k+1 < need)
				ans = ans.add(recur(n-2,k+2,need).multiply(bi((N-n+1-k)*(N-n-k))));
		}
		DP[n][k][need] = ans;
		return ans;
	}
}
