package TopCoder.Medium;
import static java.lang.Math.*;
import static java.util.Arrays.*;

/* TopCoder SRM 468
 * Medium Problem 500 Points: RoadOrFlightHard
 * Type: DP
 * Solution: States are DP[at][flights left][prev was flight]. If the previous was a flight you can fly on this one without taking a flight.
 */

public class RoadOrFlightHard {
	public void p(Object... s){System.out.println(deepToString(s));}
	int N;
	public long minTime(int n, int roadFirst, int roadProd, int roadAdd, int roadMod, int flightFirst, int flightProd, int flightAdd, int flightMod, int K) {
		N = n;
		long[] F = gen(flightFirst,flightProd,flightAdd,flightMod);
		long[] R = gen(roadFirst,roadProd,roadAdd,roadMod);
//		p(F,R);
		long[][] DP = new long[K+1][2];
		long[][] nDP = new long[K+1][2];
		for(int i = N-1;i>=0;i--){
			for(int k = 0; k < DP.length;k++){
				for(int on = 0; on < 2;on++){
					nDP[k][on] = HUGE;
					if(on == 1){
						nDP[k][on] = min(nDP[k][on],F[i]+DP[k][1]);
						nDP[k][on] = min(nDP[k][on],R[i]+DP[k][0]);
					}else{
						if(k > 0)
							nDP[k][on] = min(nDP[k][on],F[i]+DP[k-1][1]);
						nDP[k][on] = min(nDP[k][on],R[i]+DP[k][0]);
					}
				}
			}
			long[][] temp = DP;
			DP = nDP;
			nDP = temp;
		}
		return DP[K][0];
	}
	long HUGE = 1000000000000L;
	private long[] gen(int first, int prod, int add, int mod) {
		long[] L = new long[N];
		L[0] = first % mod;
		for(int i = 1; i < N;i++){
			L[i] = (L[i-1]*prod+add)%mod;
		}
		return L;
	}	
}
