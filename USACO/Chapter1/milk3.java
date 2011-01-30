package USACO.Chapter1;
/*
ID: theycal2
LANG: JAVA
TASK: milk3
 */
/* USACO Training
 * Milk3
 * Type: DFS
 * Solution: Brute force. Just search the entire space.
 */
import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class milk3 
{
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(new File("milk3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
		ans = new ArrayList<Integer>();
		
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		amax = a;
		bmax = b;
		cmax = c;
		seen = new boolean[a+1][b+1][c+1];
		recur(0,0,c);
		Collections.sort(ans);
		boolean first = true;
		for(int an: ans)
		{
			if(!first) out.print(" ");
			first = false;
			out.print(an);
		}
		out.println();
		out.close();
	}
	static int amax;
	static int bmax;
	static int cmax;
	static ArrayList<Integer> ans;
	static boolean[][][] seen;
	
	public static void recur(int a, int b, int c)
	{
		//System.out.println(a+" "+b+" "+c);
		
		if(seen[a][b][c]) return;
		seen[a][b][c] = true;
		
		if(a == 0){
			//System.out.println("new ansser");
			ans.add(c);
		}
		
		//c to a
		recur(a+min(c,amax-a),b,c-min(c,amax-a));
		//a to c
		recur(a-min(cmax-c,a),b,c+min(cmax-c,a));
		
		//b to a
		recur(a+min(b,amax-a),b-min(b,amax-a),c);
		//a to b
		recur(a-min(bmax-b,a),b+min(bmax-b,a),c);
		
		//c to b
		recur(a,b+min(c,bmax-b),c-min(c,bmax-b));
		//b to c
		recur(a,b-min(cmax-c,b),c+min(cmax-c,b));
		
	}
}

