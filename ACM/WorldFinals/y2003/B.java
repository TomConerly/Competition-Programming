package ACM.WorldFinals.y2003;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;

/* 2003 World Finals
 * Problem B: Light Bulbs
 * Type: Greedy
 * Difficulty Coding: 3 
 * Algorithmic Difficulty: 2
 * Solution: Try all ways of the first switch, after that the second switch
 * has to go one way, then the 3rd switch has to go one way etc.
 */ 

public class B {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File("B.in"));
		int CASE = 1;
		while(true)
		{
			BigInteger a = sc.nextBigInteger();
			BigInteger b = sc.nextBigInteger();
			if(a.equals(BigInteger.ZERO) && b.equals(BigInteger.ZERO)) break;
			String as = a.toString(2);
			String bs = b.toString(2);
			while(as.length() < bs.length())
			{
				as = "0"+as;
			}
			while(bs.length() < as.length())
			{
				bs = "0"+bs;
			}
			boolean[] aa = new boolean[as.length()];
			boolean[] bb = new boolean[bs.length()];
			for(int i = 0; i < as.length();i++)
			{
				if(as.charAt(i) == '1') aa[i] = true;
				if(bs.charAt(i) == '1') bb[i] = true;
			}
			boolean[] ans = solve(aa,bb);
			System.out.print("Case Number "+CASE+": ");
			if(ans != null)
			{
				String an = "";
				for(int i = 0; i < ans.length;i++)
				{
					if(ans[i]) an = an+"1";
					else an = an+"0";
				}
				BigInteger ansb = new BigInteger(an,2);
				System.out.println(ansb.toString());
			}else{
				System.out.println("impossible");
			}
			System.out.println();
			CASE++;
		}
	}

	private static boolean[] solve(boolean[] a, boolean[] b) {
		int best = Integer.MAX_VALUE;
		boolean[] bestVal = null;
		for(int start = 0; start < 2;start++)
		{
			boolean[] c = new boolean[a.length];
			for(int i = 0; i < c.length;i++)
				c[i] = a[i];
			boolean[] ans = new boolean[a.length];
			int num = 0;
			if(start == 0)
			{

			}else{
				ans[0] = true;
				num++;
				c[0] = !c[0];
				if(c.length > 1)
					c[1] = !c[1];
			}
			for(int i = 1; i < a.length;i++)
			{
				if(c[i-1] != b[i-1])
				{
					ans[i] = true;
					c[i-1] = !c[i-1];
					c[i] = !c[i];
					if(i+1 < a.length)
						c[i+1] = !c[i+1];
					num++;
				}
			}
			if(c[a.length-1] == b[a.length-1])
			{
				
				if(num < best)
				{
					best = num;
					bestVal = ans;
				}
				if(num == best)
				{
					for(int i = 0; i < bestVal.length;i++)
					{
						if(bestVal[i] != ans[i])
						{
							if(bestVal[i])
							{
								best = num;
								bestVal = ans;
							}
							break;
						}
					}
				}
			}


		}
		return bestVal;
	}
}
