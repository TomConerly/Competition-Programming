package GCJ.y2009.Round1B;

import java.util.Arrays;
import java.util.Scanner;

/* Google Code Jam 2009 Round 1B
 * Problem B: The Next Number
 * Type: Greedy
 * Solution: Go through digits starting from least significant. Find the minimum digit
 * to the right larger than the current digit. If there is one switch that digit up
 * then everything to the left is the same, everything to the right is in minimum order
 */


public class B {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int zz = 1; zz <= T;zz++)
		{
			String s = sc.next();
			int[] num = new int[s.length()+1];
			for(int i = 0; i < s.length();i++)
			{
				num[i] = s.charAt(s.length()-i-1)-'0';
			}
			
			int loc;
			
			for(loc = 1;loc < num.length;loc++)
			{
				int min = -1;
				int minAt = -1;
				for(int i = 0; i < loc;i++)
				{
					if(num[i] > num[loc] && (minAt == -1 || num[i] < min))
					{
						min = num[i];
						minAt = i;
					}
				}
				if(num[loc] < min)
				{
					num[minAt] = num[loc];
					num[loc] = min;
					break;
				}	
			}
			int[] temp = new int[loc];
			for(int i = 0; i < temp.length;i++)
				temp[temp.length-i-1] = num[i];
			Arrays.sort(temp);
			for(int i = 0; i < temp.length;i++)
				num[i] = temp[temp.length-i-1];
			String ans = "";
			for(int i = 0; i < num.length;i++)
			{
				ans = num[i]+ans ;
			}
			if(ans.charAt(0) == '0')
				ans = ans.substring(1);
			System.out.println("Case #"+zz+": "+ans);
		}
	}
}
