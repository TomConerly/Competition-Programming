package ACM.NEERC.y2007;
import java.io.*;
import java.math.*;
import java.util.*;
import static java.lang.Math.*;

/* ACM NEERC 2007
 * Problem G: Game
 * Type: DP
 * Solution: Given a given range find the probability that the new thing is to the left or right then dp on that.
 * To find the probability of going left given you are at one side. Find the prob of going off in one step, and
 * the probability if you move back one of going off the left and back here (using dp table) then compute yourself.
 * 
 * This leads to some floating point error so just use java BigDecimal with fixed precision so it is fast.
 */

public class Game 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		//setup not for stdio but to get test data which is 42 diff files (http://neerc.ifmo.ru/past/2007.html)
		for(int Z = 1; Z <= 42;Z++)
		{			
			Scanner sc = new Scanner(new File(String.format("%02d", Z)));
			Scanner ans = new Scanner(new File(String.format("%02d.a", Z)));
			double an = ans.nextDouble();
			N = sc.nextInt();
			int K = sc.nextInt()-1;
			p = new BigDecimal[N-1];
			for(int i = 0; i < N-1;i++)
				p[i] = sc.nextBigDecimal();
	
			dp_left = new BigDecimal[N][N][2];
			dp_win = new BigDecimal[N][N][2];
			for(int i = 0; i < N;i++)
				for(int j = 0; j < N;j++)
				{
					Arrays.fill(dp_left[i][j],new BigDecimal(-1.0));
					Arrays.fill(dp_win[i][j],new BigDecimal(-1.0));
				}
			double my_ans = recur(K,K,0).doubleValue();
			System.out.println(Z+": "+abs(my_ans-an)+" "+my_ans+" "+an);
		}
	}
	static MathContext mc = new MathContext(128);
	public static BigDecimal recur(int left, int right, int side)
	{
		if(left < 0 || right >= N-1)
			return new BigDecimal(0.0);
		if(left == 0 && right == N-2)
			return new BigDecimal(1.0);
		if(!dp_win[left][right][side].equals(new BigDecimal(-1.0)))
			return dp_win[left][right][side];
				
		BigDecimal pleft = prob_left(left,right,side);
		BigDecimal a = pleft.multiply(recur(left-1,right,0));
		BigDecimal b = ((new BigDecimal(1).subtract(pleft)).multiply(recur(left,right+1,1)));
		BigDecimal ans = a.add(b);
		dp_win[left][right][side] = ans;
		return ans;		
	}
	private static BigDecimal prob_left(int left, int right, int side)
	{
		if(!dp_left[left][right][side].equals(new BigDecimal(-1.0)))
			return dp_left[left][right][side];
		BigDecimal ans;
		if(left == right)
			ans = (BigDecimal.ONE).subtract(p[left]);
		else if(side == 0)
		{	
			BigDecimal a = p[left].multiply(prob_left(left+1,right,0));
			ans = (BigDecimal.ONE.subtract(p[left])).divide(BigDecimal.ONE.subtract(a),mc);
		}
		else
		{
			ans = BigDecimal.ONE.subtract(p[right].divide(BigDecimal.ONE.subtract((BigDecimal.ONE.subtract(p[right])).multiply(BigDecimal.ONE.subtract(prob_left(left,right-1,1)))),mc));		}
		dp_left[left][right][side] = ans;
		return ans;
	}
	static int N;
	static BigDecimal[][][] dp_left;
	static BigDecimal[][][] dp_win;
	static BigDecimal[] p;
}
