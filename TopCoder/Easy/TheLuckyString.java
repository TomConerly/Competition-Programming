package TopCoder.Easy;
/* TopCoder SRM 428
 * Easy Problem 250 Points: TheLuckyString
 * Type: Brute Force
 * Solution: Generate all permutations, then check them.
 */
public class TheLuckyString {

	String str;
	int count ;
	
	public int count(String s) {
		str = s;
		count = 0;
		
		int[] c = new int[30];
		for(int i = 0; i < str.length();i++)
		{
			c[str.charAt(i)-'a']++;
		}
		
		perm(0,new int[str.length()],c);
		return count;
		
	}
	public void perm(int at, int[] soFar,int[] used)
	{
		if(at == str.length())
		{			
			count++;
		}else{
			for(int i = 0; i < used.length;i++)
			{
				if(used[i] <= 0) continue;				
				if(at-1 >= 0 && i == soFar[at-1])continue;
				
				soFar[at] = i;
				used[i]--;
				perm(at+1,soFar,used);
				used[i]++;
			}
		}
	}

}
