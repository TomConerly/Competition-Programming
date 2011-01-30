package USACO.Chapter2;
/*
ID: theycal2
LANG: JAVA
TASK: zerosum
 */
/* USACO Training
 * Zero Sum
 * Type: Brute Force
 * Solution: Brute force it.
 */
import java.io.*;
import java.util.*;

class zerosum
{
	public static void main (String[] args) throws IOException {
		long start = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("zerosum.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());   
		ans = new ArrayList<String>();

		recur(1,0,1,new StringBuilder("1"),true);
		Collections.sort(ans);
		for(String s: ans)
			out.println(s);
		
		out.close();        
		System.out.println(System.currentTimeMillis()-start);
		System.exit(0);                     
	}
	static int N;
	static ArrayList<String> ans;
	public static void recur(int at, int sum, int left,StringBuilder soFar,boolean add)
	{
		if(at == N)
		{
			if(sum+left == 0)
				ans.add(soFar.substring(0));
			return;
		}
		recur(at+1,sum+left,(at+1),soFar.append("+"+(at+1)),true);
		soFar.delete(soFar.length()-2,soFar.length());
		recur(at+1,sum+left,-(at+1),soFar.append("-"+(at+1)),false);
		soFar.delete(soFar.length()-2,soFar.length());
		recur(at+1,sum,left*10+(add?(at+1):-(at+1)),soFar.append(" "+(at+1)),add);
		soFar.delete(soFar.length()-2,soFar.length());
	}
}
