package ACM.EastCentral.y2007;
import java.util.Scanner;

/* ACM ICPC 2007 East Central Regional
 * Problem D: Lemmings
 * Type: Number Theory
 * Solution: We can just try all possible three letter combinations
 * that map to one part because there are only 10^6 combinations.
 * Also we can just loop over the ones which are correct for the first
 * remainder so this is easily brute forcable.
 */
public class G {
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		for(int f = 0; f < N;f++)
		{
			s.nextInt();
			int a = s.nextInt();
			int b = s.nextInt();
			int c = s.nextInt();
			int d = s.nextInt();
			String[] parts = s.nextLine().split(" ");
			String ans = "";
			for(String part: parts)
			{
				
				if(part.length()==0) continue;
				while(part.length() < 8) part = "0"+part;
				int x = Integer.valueOf(part.substring(0,2));
				int y = Integer.valueOf(part.substring(2,4));
				int z = Integer.valueOf(part.substring(4,6));
				int w = Integer.valueOf(part.substring(6,8));
				int num = 0;
				for(int i = x;i < 1000000;i+=a)
				{
					if(i%b == y && i%c==z && i%d == w)
					{
						num = i;
						break;
					}
				}
				String nu = ""+num;
				while(nu.length() < 6) nu = "0"+nu;
				int first = Integer.valueOf(nu.substring(0,2));
				int second = Integer.valueOf(nu.substring(2,4));
				int third = Integer.valueOf(nu.substring(4,6));
				if(first < 27)ans = ans+((char)(first-1+'A'));
				else ans = ans+(" ");
				if(second < 27) ans = ans+((char)(second-1+'A'));
				else ans = ans+(" ");
				if(third < 27) ans = ans+((char)(third-1+'A'));
				else ans = ans+(" ");
			}
			while(ans.length() > 0 && ans.charAt(ans.length()-1) == ' ')
				ans = ans.substring(0,ans.length()-1);
			System.out.println(ans);
		}
	}
}
