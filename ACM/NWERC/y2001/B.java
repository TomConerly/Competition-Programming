package ACM.NWERC.y2001;
import java.util.*;

/* ACM NWERC 2001
 * Problem B: Ambiguous Dates
 * Type: Calendar
 * Solution: Use java calendar to make a hash map from a canonical date string to the number from the given date.
 * Then just lots of string parsing.
 */

public class B {
	public static void main(String[] args)
	{
		GregorianCalendar gc = new GregorianCalendar(2001,GregorianCalendar.NOVEMBER,4);
		hm = new HashMap<String,Integer>();
		int i = 0;
		while(gc.get(GregorianCalendar.YEAR) <= 2299)
		{
			add(gc,i);
			i++;
			gc.add(GregorianCalendar.DAY_OF_MONTH, 1);
		}
		gc = new GregorianCalendar(2001,GregorianCalendar.NOVEMBER,4);
		i=0;
		while(gc.get(GregorianCalendar.YEAR) >= 1700)
		{
			i--;
			gc.add(GregorianCalendar.DAY_OF_MONTH, -1);
			add(gc,i);			
		}

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int zz = 1; zz<=T;zz++)
		{
			ans = new TreeSet<Integer>();
			String s = sc.next();
			for(char c: delim)
				s = s.replace(c, ' ');
			String[] parts = s.split(" ");
			if(parts.length == 1)
			{
				recur(parts[0],0,new String[3],0);
			}
			else
			{
				all(parts[0],parts[1],parts[2]);
			}

			System.out.println("Scenario #"+zz+":");
			for(int a:ans)
				System.out.println(a);
			if(ans.size() == 0)
				System.out.println("Illegal date");
			System.out.println();
		}
	}
	private static void add(GregorianCalendar gc, int i) {
		String s = gc.get(GregorianCalendar.DAY_OF_MONTH)+" "+gc.get(GregorianCalendar.MONTH)+" "+gc.get(GregorianCalendar.YEAR);
		hm.put(s, i);		
	}	
	private static void recur(String s, int stridx, String[] strs, int id) {
		if(id == 3 && stridx == s.length())
		{
			all(strs[0],strs[1],strs[2]);
		}
		if(id == 3)
			return;
		if(stridx == s.length())
			return;

		if(id == 0 || id == 1)
		{
			if(stridx+1 <= s.length())
			{
				strs[id] = s.substring(stridx,stridx+1);
				recur(s,stridx+1,strs,id+1);
			}
			if(stridx+2 <= s.length())
			{
				strs[id] = s.substring(stridx,stridx+2);
				recur(s,stridx+2,strs,id+1);
			}
		}else{
			if(stridx+2 <= s.length())
			{
				strs[id] =s.substring(stridx,stridx+2);
				recur(s,stridx+2,strs,id+1);
			}
			if(stridx+4 <= s.length())
			{
				strs[id] =s.substring(stridx,stridx+4);
				recur(s,stridx+4,strs,id+1);
			}
		}

	}
	static void all(String a, String b, String c)
	{
		test(a,b,c);
		test(a,c,b);
		test(b,a,c);
		test(b,c,a);
		test(c,a,b);
		test(c,b,a);
	}
	private static void test(String day, String month, String year) 
	{
		if(day.length() != 1 && day.length() != 2)
			return;
		if(month.length() != 1 && month.length() != 2)
			return;
		if(year.length() != 1 && year.length() != 2 && year.length() !=4)
			return;
		if(year.length() != 4)
		{
			for(int i = 1700; i<= 2200;i+=100)
				test(Integer.valueOf(day),Integer.valueOf(month),i+Integer.valueOf(year));
		}
		else
		{
			test(Integer.valueOf(day),Integer.valueOf(month),Integer.valueOf(year));
		}
	}
	static void test(int day, int month, int year)
	{

		String s = day+" "+(month-1)+" "+year;
		if(hm.containsKey(s))
			ans.add(hm.get(s));
	}
	static TreeSet<Integer> ans;
	static char[] delim = {'/','\\','.',',','-'};
	static HashMap<String,Integer> hm;
}
