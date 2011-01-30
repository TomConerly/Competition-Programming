package GCJ.y2009.Round1B;
import java.util.*;

/* Google Code Jam 2009 Round 1B
 * Problem A: Decision Tree
 * Type: Brute Force
 * Solution: Ugly code to just brute force the tree.
 */

public class A {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		for(int zz = 1; zz <= N;zz++)
		{
			int L = sc.nextInt();
			sc.nextLine();
			String tree = "";
			for(int i = 0; i < L;i++)
			{
				tree = tree+" "+sc.nextLine();
			}
			System.out.println("Case #"+zz+":");
			int A = sc.nextInt();
			for(int i = 0; i < A;i++)
			{
				@SuppressWarnings("unused")
				String s = sc.next();
				int num = sc.nextInt();
				String[] fs = new String[num];
				for(int j = 0; j < num;j++)
				{
					fs[j] = sc.next();
				}
				Scanner t = new Scanner(tree);
				System.out.format("%.09f\n",prob(t,fs));
			}
		}
	}
	public static double prob(Scanner t, String[] fs)
	{
		String s = t.next();
		if(s.equals("("))
			s = t.next();
		if(s.charAt(0) == '(')
			s = s.substring(1);
		boolean end = false;
		if(s.charAt(s.length()-1) == ')')
		{
			end = true;
			s = s.substring(0,s.length()-1);
		}
		double p = Double.valueOf(s);
		if(end)
			return p;

		String f = t.next();
		if(f.charAt(0) == ')')
			return p;
		if(contains(fs,f))
		{
			return p*prob(t,fs);
		}
		else
		{
			return p*prob(skip(t),fs);
		}
	}
	private static Scanner skip(Scanner t) 
	{
		int count = 0;
		boolean start = false;
		while(count != 0 || !start)
		{
			start = true;
			String s = t.next();
			for(int i = 0; i < s.length();i++)
			{
				if(s.charAt(i) == '(')
					count++;
				if(s.charAt(i) == ')')
					count--;
			}
		}
		return t;
	}
	private static boolean contains(String[] fs, String f) {
		for(String s:fs)
			if(f.equals(s))
				return true;
		return false;
	}
}
