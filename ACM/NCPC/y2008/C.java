package ACM.NCPC.y2008;
import java.util.*;

/* ACM NCPC 2008
 * Problem C: Code Theft
 * Type: Brute Force
 * Solution: O(n^3) will time out. Take two files, pick an offset between them. Linearly scan from top to bottom 
 * keeping track of how many match (if next match increment counter, if not zero it) take the best. (Coded by Celestine)
 */

public class C
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextInt())
		{
			int N = sc.nextInt();
			sc.nextLine();
			ArrayList<ArrayList<String>> code = new ArrayList<ArrayList<String>>();
			ArrayList<String> filenames = new ArrayList<String>();
			for (int i = 0; i < N; i++)
			{
				filenames.add(sc.nextLine());
				ArrayList<String> list = new ArrayList<String>();
				while (true)
				{
					String s = sc.nextLine().trim();
					if (s.equals("***END***")) break;
					String t = getLine(s);
					if(t.trim().equals(""))
						continue;
					list.add(t);
				}
				code.add(list);
			}


			ArrayList<String> match = new ArrayList<String>();
			while (true)
			{
				String s = sc.nextLine().trim();
				if (s.equals("***END***")) break;
				String t = getLine(s);
				if(t.trim().equals(""))
					continue;
				match.add(t);
			}

			ArrayList<String> answer = new ArrayList<String>();
			int longest = 0;
			for (int i = 0; i < N; i++)
			{
				int numMatch = Math.max(matchLines(code.get(i),match),matchLines( match,code.get(i)));
				if (numMatch > longest)
				{
					answer.clear();
					answer.add(filenames.get(i));
					longest = numMatch;
				}
				else if (numMatch == longest && numMatch != 0)
				{
					answer.add(filenames.get(i));
				}
			}

			System.out.print(longest);
			for (int i = 0; i < answer.size(); i++)
			{
				System.out.print(" " + answer.get(i));
			}
			System.out.println();
		}

	}

	public static int matchLines (ArrayList<String> list1, ArrayList<String>
	list2)
	{
		int longest = 0;
		for (int i = 0; i < list1.size(); i++)
		{
			int counter = 0;
			for (int j = 0; i+j < list1.size()  && j < list2.size(); j++)
			{
				if (list1.get(i + j).equals(list2.get(j)))
				{
					counter++;
					longest = Math.max(longest,counter);
				}
				else
				{
					counter = 0;
				}
			}
		}
		return longest;

	}

	public static String getLine(String s)
	{
		StringTokenizer st = new StringTokenizer(s);
		String ans = "";
		while (st.hasMoreTokens())
		{
			ans += st.nextToken() + " ";
		}
		return ans;
	}

	public static boolean matchLine(String s1, String s2)
	{
		StringTokenizer st1 = new StringTokenizer(s1);
		StringTokenizer st2 = new StringTokenizer(s2);

		while (st1.hasMoreTokens())
		{
			if (!st2.hasMoreTokens()) return false;
			if (!st1.nextToken().equals(st2.nextToken())) return false;
		}

		if (st2.hasMoreTokens()) return false;
		return true;
	}
}
