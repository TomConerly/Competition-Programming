package TopCoder.Hard;
import static java.lang.Math.*;

/* TopCoder SRM 394
 * Hard Problem 900 points: PsuedoRandomKingdom
 * Type: Graph Theory
 * 
 * Solution: Pick a node. Recursively calculate the failure probablity and the probability distribution of the max path
 * in each subtree given that the tree didn't fail. Combine those and return our answer.
 */

public class PseudoRandomKingdom2 {
	int C,S;
	double success=1.0;
	String[] g;
	public double probabilityOfHappiness(String[] gg, int cost, int savings) {
		C = cost;
		S = savings;
		g = gg;
		dfs(0);
		return success;
	}
	boolean[] v= new boolean[50];
	private double[] dfs(int i) {
		if(v[i]) return null;
		v[i] = true;
		double[] ans = new double[S+2];
		ans[0] = 1;
		
		for(String s: g[i].split(" "))
		{
			int e = Integer.valueOf(s);
			double[] temp = dfs(e);
			if(temp == null) continue;			
			ans = combine(ans,edge(temp));			
		}
		return ans;
	}
	private double[] edge(double[] p) {
		double[] pr = new double[S+2];		
		for(int at = 0; at <= S+1;at++)		
			for(int cost = 0; cost <= C;cost++)
				pr[min(at+cost,S+1)] = p[at]*(1/(C+1.0))+pr[min(at+cost,S+1)];		
		return pr;
	}
	private double[] combine(double[] a, double[] b) {
		double[] pr = new double[S+2];
		
		double total = 0.0;
		double fail = 0.0;
		for(int i = 0; i <= S+1;i++)		
			for(int j = 0; j <= S+1;j++)			
				if(i+j > S)
					fail += a[i]*b[j];
				else
				{
					pr[max(i,j)]=a[i]*b[j]+pr[max(i,j)];
					total += a[i]*b[j];
				}		
		
		success *= (1-fail);		
		for(int i = 0; i <= S+1;i++)	
			pr[i] /= total;
		
		return pr;
	}
}
