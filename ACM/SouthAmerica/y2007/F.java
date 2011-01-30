package ACM.SouthAmerica.y2007;
import java.util.Scanner;

/* ACM ICPC 2007 South American Regional 
 * Problem F: Finding Seats
 * Type: DP
 * Solution: Start with rows i to j on the left then if we don't have enough seats
 * shift the right side of this block over one, if we do then shift the
 * left side over one. Keep track of the best answer. We also need to DP
 * to find out the number of people in each column given the rows i to j.
 */
public class F {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		short[][][] seats = new short[300][300][300];
		while(true)
		{
			int rows = sc.nextInt();
			int cols = sc.nextInt();
			int goal = sc.nextInt();
			if(rows == 0 && cols == 0 && goal == 0) break;
			sc.nextLine();
			for(int r  = 0; r < rows;r++)
			{
				String line = sc.nextLine();
				for(int c = 0; c < cols;c++)
				{
					seats[c][r][r] = (short)(line.charAt(c) =='.' ? 1 : 0);
				}
			}
			
			for(int c = 0; c < cols;c++)
			{
				for(int d = 1; d< rows;d++)
				{
					for(int s = 0; s+d<rows;s++)
					{
						seats[c][s][s+d] = (short)(seats[c][s][s+d-1]+seats[c][s+d][s+d]);
					}
				}
			}
			int best = Integer.MAX_VALUE;
			for(int s = 0; s < rows;s++)
			{
				for(int e = s; e < rows;e++){
					int at = 0; int l = 0; int r = 0;
					while(true)
					{
						if(at < goal)
						{
							if(r==cols) break;
							at += seats[r][s][e];
							r++;
						}
						else
						{
							best = Math.min(best,(r-l)*(e-s+1));
							at -= seats[l][s][e];
							l++;
						}
					}
				}
			}
			System.out.println(best);
		}
	}
}
