package SSU;
import java.util.*;

/* Saratov State University Online Judge
 * Problem 115: Calendar
 * Type: Calendar
 * Solution: Use Java calendar class
 */

public class p115 {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();

		HashMap<String,Integer> hm = new HashMap<String,Integer>();
		GregorianCalendar gc = new GregorianCalendar(2001,0,1);
		while(gc.get(GregorianCalendar.YEAR) == 2001)
		{
			int day = gc.get(GregorianCalendar.DAY_OF_WEEK);
			hm.put(gc.get(GregorianCalendar.DAY_OF_MONTH)+" "+gc.get(GregorianCalendar.MONTH),day);
			
			//System.out.println(gc.get(GregorianCalendar.DAY_OF_MONTH)+" "+gc.get(GregorianCalendar.MONTH)+" "+toNum(day));
			gc.add(GregorianCalendar.DAY_OF_MONTH,1);
		}
		if(hm.containsKey(N+" "+(M-1)))
			System.out.println(toNum(hm.get(N+" "+(M-1))));
		else
			System.out.println("Impossible");
	}
	public static int toNum(int a)
	{
		switch(a)
		{
		case GregorianCalendar.MONDAY:
			return 1;
		case GregorianCalendar.TUESDAY:
			return 2;
		case GregorianCalendar.WEDNESDAY:
			return 3;
		case GregorianCalendar.THURSDAY:
			return 4;
		case GregorianCalendar.FRIDAY:
			return 5;
		case GregorianCalendar.SATURDAY:
			return 6;
		case GregorianCalendar.SUNDAY:
			return 7;
		}
		return -1;
	}
}
