package ACM.PacificNW.y2004;
import java.util.*;

/* ACM ICPC 2004 Pacific NW Region
 * Problem I: Boundaries on "A New Kind of Science"
 * Type: Brute Force
 * Solution: Brute force, their test cases are really small.
 */

public class I {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		top:
			for(int zz = 1;;zz++)
			{
				String s = sc.nextLine();
				if(s.equals("END OF INPUT"))
					break;
				String[] p = s.split(" ");
			
				S = Integer.parseInt(p[0]);
				ans = new boolean[p[1].length()];
				for(int i = 0; i < p[1].length();i++)
				{
					if(p[1].charAt(i) == 'W')
					{
						ans[i] = false;
					}
					else if(p[1].charAt(i) == 'B')
					{
						ans[i] = true;
					}
					else{
						System.out.println("LINE "+zz+" NONE");
						continue top;
					}
				}
				boolean b = false;
				System.out.print("LINE "+zz+" ");
				for(int i = 0; i < 256;i++)
				{
					boolean[] board = new boolean[ans.length];
					board[ans.length/2] = true;
					int found = -1;
					for(int j = 0; j < S;j++)
					{
						if(Arrays.equals(board,ans))
						{
							found = j+1;
							break;
						}
						board = step(board,i);
					}
					if(found != -1)
					{
						System.out.print("("+i+","+(found)+")");
						b = true;
					}
				}
				if(!b)
					System.out.println("NONE");
				System.out.println();
			}
	}
	private static boolean[] step(boolean[] board, int rule) 
	{
		boolean[] nb = new boolean[board.length];
		for(int i = 1; i < board.length-1;i++)
		{
			int num = (board[i+1]?1:0)+(board[i]?2:0)+(board[i-1]?4:0);
			if((rule & (1<<num)) != 0)
				nb[i] = true;
		}
		return nb;
	}
	static int S;
	static boolean[] ans;
}
