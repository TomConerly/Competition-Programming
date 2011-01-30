package TopCoder.Easy;

/* TopCoder TCCC07 ROund 1A 
 * Easy Problem 250 Points: LetterInterchange
 * Type: Greedy
 * Solution: We want to swap the min character into the earliest position that isn't the min
 * if there is no such place look at the next smallest character etc.
 */

public class LetterInterchange {

	public int[] interchangeWhich(String[] s1, String[] s2) {
		String S = "";
		for(String s:s1)
			S += s;
		for(String t:s2)
			S += t;

		for(int j = 0; j < 26;j++)
		{
			int min = 0;
			for(int i = 0; i < S.length();i++)
			{
				if(S.charAt(i)-'a' == j) min = i;
			}
			for(int i = 0; i < min;i++)
			{
				if(S.charAt(i) > S.charAt(min))
				{
					return new int[]{i,min};
				}
			}
		}
		for(int i = 0; i < S.length();i++)
		{
			for(int j = i+1;j<S.length();j++)
			{
				if(S.charAt(i) == S.charAt(j)) return new int[]{i,j};
			}
		}
		
		return new int[]{S.length()-2,S.length()-1};
	}
	public void p(Object o){System.out.println(o);}
}
