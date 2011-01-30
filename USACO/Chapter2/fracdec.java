package USACO.Chapter2;
/*
ID: theycal2
LANG: JAVA
TASK: fracdec
 */
/* USACO Training
 * Fraction to Decimal
 * Type: Number Theory
 * Solution: Use old school long division.
 */
import java.io.*;
import java.util.*;

public class fracdec 
{
	public static void main(String[] args) throws IOException 
	{
		long start = System.currentTimeMillis();
		Scanner sc = new Scanner(new File("fracdec.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fracdec.out")));
		int N = sc.nextInt();
		int D = sc.nextInt();

		String ans = "";
		int carry = 0;
		int tempAns = 0;
		int numTimes = (N+"").length();
		int count = 0;
		while((carry!=0 || N != 0) && count < numTimes)
		{
			int num = (carry*10+((N+"").charAt(0)-'0'))/D;
			tempAns = tempAns*10 + num;
			carry = (carry*10+((N+"").charAt(0)-'0'))%D;
			N = Integer.valueOf("0"+(N+"").substring(1));			
			count++;
		}
		
		
		
		ans = tempAns+".";
		int z = 0;
		int[] seen = new int[D];
		Arrays.fill(seen,-1);
		int rep = -1;
		StringBuilder temp = new StringBuilder();
		
		System.out.println(System.currentTimeMillis()-start);
		while(carry != 0)
		{
			if(seen[carry] != -1)
			{
				rep = seen[carry];
				break;
			}
			seen[carry] = z;		
			
			int num = (carry*10)/D;			
			carry = (carry*10)%D;
			temp.append(num);
			
			z++;
		}
		ans = ans + temp;
		if(rep != -1)			
			ans = ans.substring(0,ans.indexOf('.')+1+rep)+"("+ans.substring(ans.indexOf('.')+1+rep)+")";

		if(ans.indexOf('.')+1 == ans.length())
			ans = ans+"0";
		
		System.out.println(System.currentTimeMillis()-start);
		
		int at = 0;
		while(ans.length()-at > 76)
		{
			out.println(ans.substring(at,at+76));
			at+=76;
		}
		if(at - ans.length() != 0)
			out.println(ans.substring(at));
		out.close();
		System.out.println(System.currentTimeMillis()-start);
		System.exit(0);
	}
}

