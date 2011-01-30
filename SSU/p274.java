package SSU;
import java.util.*;

/* Saratov State University Online Judge
 * Problem 274: Spam-filter
 * Type: Simulation
 * Solution: Yawn.
 */

public class p274 {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.nextLine();
		for(int i = 0; i < N;i++){
			boolean legal;
			String S = sc.nextLine();
			legal = eval(S);
			System.out.println(legal?"YES":"NO");
		}
	}
	private static boolean eval(String S) {
		if(count(S,'@') != 1)
			return false;
		String pre = S.substring(0,S.indexOf('@'));
		String[] pr =split(pre);
		for(String p:pr)
			if(p.length() == 0 || !validchar(p))
				return false;
		
		String post = S.substring(S.indexOf('@')+1);		
		if(count(post,'.') == 0)
			return false;
		String start = post.substring(0,post.lastIndexOf('.'));
		String end = post.substring(post.lastIndexOf('.')+1);
		if(!validletter(end) || (end.length() != 2 && end.length() != 3))
			return false;
		pr = split(start);
		for(String p:pr)
			if(p.length() == 0 || !validchar(p))
				return false;	
		
		return true;
	}
	private static String[] split(String s) {
		ArrayList<Integer> idx = new ArrayList<Integer>();
		int at = 0;
		while(true){
			int next = s.indexOf('.',at);
			if(next == -1)
				break;
			idx.add(next);
			at = next+1;
		}
		String[] ans = new String[idx.size()+1];
		if(idx.size() == 0){
			ans[0] = s;
			return ans;
		}
		ans[0] = s.substring(0,idx.get(0));
		for(int i = 0; i < idx.size();i++){
			if(i+1 < idx.size())
				ans[i+1] = s.substring(idx.get(i)+1,idx.get(i+1));
			else
				ans[i+1] = s.substring(idx.get(i)+1);
		}
		return ans;
	}
	private static boolean letter(char c){
		if('a' <= c && c <= 'z')
			return true;
		if('A' <= c && c <= 'Z')
			return true;
		return false;
	}
	private static boolean digit(char c){
		return '0' <= c && c <= '9';
	}
	private static boolean validletter(String s) {
		for(int i = 0; i < s.length();i++){
			if(letter(s.charAt(i)))
				continue;
			return false;
		}
		return true;
	}
	private static boolean validchar(String s) {
		for(int i = 0; i < s.length();i++){
			if(letter(s.charAt(i)))
				continue;
			if(digit(s.charAt(i)))
				continue;
			if(s.charAt(i) == '_' || s.charAt(i) == '-')
				continue;
			return false;
		}
		return true;
	}
	public static int count(String s, char c){
		int ans = 0;
		for(int i = 0; i < s.length();i++)
			if(s.charAt(i) == c)
				ans++;
		return ans;
	}
}
