package TopCoder.Hard;
import static java.lang.Math.*;
import static java.util.Arrays.*;

/* TopCoder SRM 465
 * Hard Problem 900 Points: BouncingDiceGame
 * Type: DP
 * Solution: Once someone is within D of the end all states are isomorphic. They always have a 1/D chance of winning. Compute the probability that they
 * reach within D of the end in k moves using DP (optimize it down). Then try all pairs of time for how long it takes them to reach the end. 
 */

public class BouncingDiceGame {
	public void p(Object... s){System.out.println(deepToString(s));}
	public double winProbability(int N, int D, int X, int Y) {
		double[] cur = new double[N];
		double[] prev = new double[N];
		for(int i = 0; i < N-1;i++)
			if(i+1+D >= N){		
				prev[i] = 1.;
			}
		double[] PX = new double[N+1];
		double[] PY = new double[N+1];
		PX[0] = prev[X-1];
		PY[0] = prev[Y-1];
		for(int moves = 1;moves <= N;moves++){
			double[] P = new double[N];
			for(int at = N-1; at >= 0;at--){
				if(at+1+D >= N && moves > 0)
					cur[at] = 0;
				else
					cur[at] = (at+1 < N?P[at+1]:0)-(at+D+1 < N?P[at+D+1]:0);				
				P[at] = (at +1 < N?P[at+1]:0)+prev[at]/(double)D;
			}
			PX[moves] = cur[X-1];
			PY[moves] = cur[Y-1];
			prev = cur;
			cur = new double[N];
		}
		double prnorm = (1./D)*(1/(1-pow(((D-1)/(double)D),2)));
		double ans = 0;
		double[] L = new double[N*2];
		L[0] = 1;
		for(int i = 1; i < L.length;i++)
			L[i] = (D-1)/(double)D*L[i-1];
		int minj = N;
		int maxj = 0;
		for(int j = 0; j <= N;j++)
			if(PY[j] > 0){
				minj = min(minj,j);
				maxj = max(maxj,j);
			}
		for(int i = 0; i <= N;i++){
			if(PX[i] == 0)
				continue;
			for(int j = minj; j <= maxj;j++){
				if(j-i >= 0){
					double win = 1-L[j-i];
					ans += PX[i]*PY[j]*(win+(1-win)*prnorm);
				}else{
					double lose = 1-L[i-j];
					ans += PX[i]*PY[j]*((1-lose)*prnorm);
				}
			}
		}
		return ans;		
	}


}
