package ACM.EastCentral.y2007;
import java.util.Scanner;

/* ACM ICPC 2007 East Central Regional
 * Problem C: Give Me an E
 * Type: Simple
 * Solution: There are only 20 combinations of the 1000 (see dig) that we can use.
 * Take input and convert it to base 20 then use the digits. Also have to make sure
 * not to use septillion and sextillion.
 */

public class C {
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		while(true)
		{
			int n = s.nextInt();
			if(n==0) break;
			int[] parts = new int[100];
			int at = 0;
			while(n != 0)
			{
				parts[at] = n%20;
				n /= 20;
				at++;
			}
			String ans = "";
			for(int i =at-1;i>=0;i--)
			{
				
				ans = ans+dig[parts[i]]+",";
				if(i==7) ans = ans+"000,000,";
			}
			while(ans.charAt(0) == '0') ans = ans.substring(1);
			while(ans.charAt(ans.length()-1) == ',') ans = ans.substring(0,ans.length()-1);
			System.out.println(ans);
		}
	}
	static String[] dig = {"000","002","004","006","030","032","034","036","040","042","044","046","050","052","054","056","060","062","064","066"};
}
