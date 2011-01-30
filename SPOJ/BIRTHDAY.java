package SPOJ;

/* SPOJ Problem 972: BSHEEP
 * Type: Ad Hoc
 * Solution: Solve the problem without rotation. Now focus on where the first kid will end. We know that as we rotate where the permutation will end
 * the cost associated with the first kid will increase then decrease. Keep track of when these ranges are and merge them with all other kids. So then
 * we just compute the cost of the first rotation then in O(1) we can compute the cost of rotating it once.
 */

import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class BIRTHDAY {
	public static void main(String[] args) throws Exception, IOException
	{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(r.readLine());
		int[] P = new int[N];
		StringTokenizer st = new StringTokenizer(r.readLine());
		for(int i = 0; i < N;i++)
			P[i] = Integer.parseInt(st.nextToken())-1;
		System.out.println(solve(P,N,false));			

	}
	public static int solve(int[] P, int N, boolean print)
	{
		int ans = Integer.MAX_VALUE;
		int cost = 0;
		for(int i = 0; i < N;i++)
		{
			cost += min(abs(P[i]-i),min(i+1 +N-P[i]-1, N-i+P[i]));
		}

		int[] D = new int[N];
		int delta = 0;
		for(int i = 0; i < N;i++)
		{			
			int at = P[i] >= i ? P[i]-i : N+P[i]-i;
			int op1 = (N/2+at)%N;
			int op2 = ((N+1)/2+at)%N;
			if(print)
				System.out.println(at+" "+op1+" "+op2);
			if((at != 0 && at < op1) || (op1 == 0 && op2 ==0) )
			{
				delta--;
			}else{
				if(op1 +1 == op2 && op1 == 0)
				{}else					
					delta++;
			}
			D[at]+=2;
			D[op1]--;
			D[op2]--;
		}
		if(print)
		{
			System.out.println(cost+" "+delta);
			System.out.println(Arrays.toString(D));
		}
		for(int i = 0; i < N;i++)
		{
			ans = min(ans,cost);
			if(i == N-1)
				break;
			cost += delta;			
			delta += D[i+1];

		}
		return ans;
	}
}
