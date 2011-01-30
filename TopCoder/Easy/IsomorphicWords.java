package TopCoder.Easy;
import java.util.*;
/* TopCoder SRM 391
 * Easy Problem 250 Points: IsomorphicWords
 * Type: BruteForce
 * Solution: Put all words into a canonical form and then compare them.
 */
public class IsomorphicWords {

	public int countPairs(String[] words) {
		String[] nl = new String[words.length];
		for(int i  = 0; i < words.length;i++)
		{
			nl[i] = s(words[i]);
		}
		int ans = 0;
		for(int i = 0; i < nl.length;i++)
		{
			for(int j = i+1;j<nl.length;j++)
			{
				if(nl[i].equals(nl[j])){
					ans++;
					//System.out.println(words[i]+" "+words[j]+" "+nl[i]+" "+nl[j]);
				}
			}
		}
		return ans;
	}
	
	private String s(String s) {
		HashMap<Character,Character> seen = new HashMap<Character,Character>();
		String ans = "";
		int low = 0;
		for(int i = 0; i < s.length();i++)
		{
			if(seen.containsKey(s.charAt(i))) ans = ans + seen.get(s.charAt(i));
			else{
				seen.put(s.charAt(i), (char)(low+'a'));
				ans = ans+seen.get(s.charAt(i));
				low++;
			}
		}
		return ans;
	}

	void print(int[] a)
	{
		for(int b:a)
			System.out.print(b+" ");
		System.out.println();
	}

}
