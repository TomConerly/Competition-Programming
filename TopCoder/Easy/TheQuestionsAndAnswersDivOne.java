package TopCoder.Easy;

/* TopCoder SRM 460
 * Easy Problem 250 Points: TheQuestionsAndAnswersDivOne
 * Type: Simulation
 * Solution: Simulate it and throw in an optimization for speed. You should also be able to solve using some simple math.
 */

import java.util.*;

public class TheQuestionsAndAnswersDivOne {
	public void p(Object s){System.out.println(s);}
	public int find(int questions, String[] answers) 
	{
		A = answers;
		Q = questions;
		int[] F = new int[A.length];
		Arrays.fill(F,-1); 
		F[0] = 0;
		F[1] = 0;
		int a1 = recur(2,F);
		Arrays.fill(F,-1);
		F[0] = 0;
		F[1] = 1;
		int a2 = recur(2,F);
		return a1*Q + a2*(Q*(Q-1));
	}
	String[] A;
	int Q;
	public int recur(int at, int[] F)
	{		
		if(at == A.length)
		{			
			for(int i= 0; i < Q;i++)
				if(!contains(F,i))
					return 0;
			return check(F)?1:0;
		}
		int ans = 0;
		for(int i = 0; i < Q;i++)
		{
			F[at] = i;
			ans += recur(at+1,F);
			F[at] = -1;
		}
		return ans;
	}
	private boolean contains(int[] f, int i) {
		for(int a:f)
			if(a == i)
				return true;
		return false;
	}
	private boolean check(int[] f) {
		for(int i = 0; i < A.length;i++){
			for(int j = i+1;j < A.length;j++)
				if(f[i] == f[j] && !A[i].equals(A[j]))
				{
					return false;
				}
		}
		return true;
		
	}
}
