package TopCoder.Medium;
import static java.lang.Math.*;
import static java.util.Arrays.*;

/* TopCoder SRM 466
 * Medium Problem 500 Points: LotteryPyaterochka
 * Type: Math/DP
 * Solution: Either do probability on all the good ending states (5)(4,1)(3,1,1)(3,2) or do a DP. I do a mix. I keep track of # in each row with > 0 things
 * try adding to each of those with given probability and compute probability of adding to a new row.
 */

public class LotteryPyaterochka {
	public void p(Object... s){System.out.println(deepToString(s));}
	public double chanceToWin(int n) {
		N = n;
		return recur(0,0,new int[10]);
	}
	int N;
	public double recur(int at,int max,int[] B){
		if(at == 5){
			for(int i = 0; i < B.length;i++)
				if(B[i] >= 3)
					return 1.0;
			return 0.0;
		}
		double ans = 0;
		for(int i = 0; i <= max;i++){
			double p = (5-B[i])/(double)(5*N-at);
			if(B[i] == 0)
				p *= (N-i);
			B[i]++;
			ans += p*recur(at+1,max(max,i+1),B);
			B[i]--;
		}
		return ans;
	}
}
