package USACO.Chapter2;
/*
ID: theycal2
LANG: JAVA
TASK: preface
 */
/* USACO Training
 * The Castle
 * Type: Simulation
 * Solution: Roman Numeral Convertor
 */
import java.io.*;
import java.util.*;

public class preface 
{
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(new File("preface.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));

		int N = sc.nextInt();
		for(int i = 1; i <= N;i++)
		{
			toRoman(i);
		}
		int last = 6;
		for(int i = 6;i >= 0;i--)
		{
			last = i;
			if(count[i] != 0) 
			{
				break;
			}
		}
		for(int i = 0; i <= last;i++)
		{
			out.println(c[i]+" "+count[i]);
		}
		out.close();
	}
	static int[] count = new int[7];
	static char[] c = {'I','V','X','L','C','D','M'};
	public static void toRoman(int sum)
	{
		String roman= "";		
		String conversion[][] = {
				{"","I","II","III","IV","V","VI","VII","VIII","IX"},
				{"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"},
				{"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"},
				{"","M","MM","MMM","MMMM","MMMMM","vM","vMM","vMMM","Mx"},
				{"","x","xx","xxx","xl","l","lx","lxx","lxxx","xc"},
				{"","c","cc","ccc","cd","d","dc","dcc","dccc","cm"},
				{"","m","mm","mmm","","","","","",""}};

		int i = 0;
		while (sum > 0) {
			int digit = sum%10;
			roman = conversion[i][digit] + roman;
			i++;
			sum = sum/10;
		}
		for(int j = 0; j < roman.length();j++)
		{
			for(int k = 0; k < c.length;k++)
			{
				if(roman.charAt(j) == c[k])
				{
					count[k]++;
				}
			}
		}
	}


}

