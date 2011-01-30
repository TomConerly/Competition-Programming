package ACM.EastCentral.y2007;
import java.util.ArrayList;
import java.util.Scanner;

/* ACM ICPC 2007 East Central Regional
 * Problem A: CIVIC DILL MIX
 * Type: Simple
 * Solution: Convert to and from decimal, beware of special cases.
 */

public class A {
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		for(int z=1;;z++)
		{
			int n = s.nextInt();
			if(n==0) break;
			ArrayList<String> input = new ArrayList<String>();
			s.nextLine();
			while(input.size() < n)
			{
				for(String in: s.nextLine().split(" "))
				{
					input.add(in);
				}
			}
			int sum = 0;
			for(String in: input)
			{
				sum += toValue(in);
			}
			System.out.println("Case "+toRom(z)+": "+toRom(sum));
		}
	}
	public static String toRom(int num)
	{
		int[] times = new int[7];
		for(int i = val.length-1;i>=0;i--)
		{
			while(num >= val[i])
			{
				num -= val[i];
				times[i]++;
			}
		}
		String ans = "";
		for(int i = val.length-1; i>=0;i--)
		{
			if(i==0 && times[0] == 4)
			{
				if(times[1] == 0)
				{
					ans = ans +"IV";
				}else{
					ans = ans.substring(0,ans.length()-1) +"IX";
				}
			}else if(i==2 && times[2] == 4){
				if(times[3] == 0)
				{
					ans = ans +"XL";
				}else{
					ans = ans.substring(0,ans.length()-1) +"XC";
				}
			}else if(i==4 && times[4] == 4){
				if(times[5] == 0)
				{
					ans = ans +"CD";
				}else{
					ans = ans.substring(0,ans.length()-1) +"CM";
				}
			}else{
				for(int j = 0; j < times[i];j++)
				{
					ans = ans +name[i];
				}
			}
		}
		return ans;
	}
	static int[] val = {1,5,10,50,100,500,1000};
	static String[] name = {"I","V","X","L","C","D","M"};
	public static int val(char c)
	{
		if(c =='M') return 1000;
		if(c =='D') return 500;
		if(c =='C') return 100;
		if(c =='L') return 50;
		if(c =='X') return 10;
		if(c =='V') return 5;
		if(c =='I') return 1;
		return 0;
	}
	public static int toValue(String rom)
	{
		int largest = 0;
		int total = 0;
		for(int i = rom.length()-1;i>=0;i--)
		{
			int v = val(rom.charAt(i));
			if(v >= largest) total += v;
			else total -= v;
			largest = Math.max(largest,v);
		}
		return total;
	}
}
