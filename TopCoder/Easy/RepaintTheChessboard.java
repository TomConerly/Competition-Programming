package TopCoder.Easy;
import static java.lang.Math.*;

/* TopCoder TCO 2009 Qual 2
 * Easy Problem 250 Points: RepaintTheChessboard
 * Type: Brute Force
 * Solution: Just try all.
 */

public class RepaintTheChessboard {

	public int minimumChanges(String[] board) 
	{
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < board.length-7;i++)
		{
			for(int j = 0; j < board[0].length()-7;j++)
			{
				int s1=0;
				int s2=0;
				for(int a = 0; a < 8;a++)
				{
					for(int b = 0; b < 8;b++)
					{
						if((board[i+a].charAt(b+j) == 'B') != ((a+b)%2 == 0)) s1++;
						if((board[i+a].charAt(b+j) == 'B') != ((a+b)%2 == 1)) s2++;
					}
				}
				min = min(s1,min);
				min = min(s2,min);
			}
		}
		return min;
	}
}
