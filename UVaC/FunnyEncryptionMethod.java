package UVaC;
import java.io.FileInputStream;
import java.util.Scanner;

/* UVa Volume C: 10019 - Funny Encryption Method
 * Type: Simulation
 * Solution: Just do it.
 */
public class FunnyEncryptionMethod {
	public static void main(String[] args)throws Exception
	{
		System.setIn(new FileInputStream("main.in"));

		Scanner sc = new Scanner(System.in);
		
		int Y = sc.nextInt();
		for(int z = 0; z < Y;z++)
		{
			int n = sc.nextInt();
			int x2 = Integer.valueOf(n+"",16);
			System.out.println(Integer.bitCount(n)+" "+Integer.bitCount(x2));
		}
	}
}
