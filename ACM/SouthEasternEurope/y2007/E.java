package ACM.SouthEasternEurope.y2007;
import java.util.*;

/* 2007 South Eastern European Regional
 * Problem E: Show Stopper
 * Type: Number Theory
 * Solution: Note that there is only one answer.
 * Just xor all the possible answers and the odd one
 * will pop up!
 */


public class E {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextLine())
		{
			ArrayList<Long> lx = new ArrayList<Long>();
			ArrayList<Long> ly = new ArrayList<Long>();
			ArrayList<Long> lz = new ArrayList<Long>();
			
			while(sc.hasNextLine())
			{
				String line = sc.nextLine().trim();
				if(line.equals("")) break;
				
				String[] parts = line.split(" ");
				lx.add(Long.valueOf(parts[0]));
				ly.add(Long.valueOf(parts[1]));
				lz.add(Long.valueOf(parts[2]));
			}
			if(lx.size() == 0) continue;;
			long ans = 0;
			for(int i = 0; i < lx.size();i++)
			{
				for(long j = lx.get(i);j <= ly.get(i);j+= lz.get(i))
				{
					ans ^= j;
				}
			}
			if(ans == 0) System.out.println("no corruption");
			else
			{
				int count = 0;
				for(int i = 0; i < lx.size();i++)
				{
					if(ans >= lx.get(i) && ans <= ly.get(i) && (ans-lx.get(i)) %lz.get(i) == 0) count++;
				}
				System.out.println(ans+" "+count);
			}
		}
	}
}
