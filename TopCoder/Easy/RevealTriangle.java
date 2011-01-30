package TopCoder.Easy;
/* TopCoder SRM 404
 * East Problem 250 Points: RevealTriangle
 * Type: Simulation
 * Solution: Just solve it.
 */
public class RevealTriangle {

	public String[] calcTriangle(String[] q) {
		if(q.length == 1) return q;
		for(int r = q.length-2;r >=0;r--)
		{
			int at = -1;
			for(int j = 0; j < q[r].length();j++)
			{
				if(q[r].charAt(j) != '?')
				{
					at = j;
				}
			}
			StringBuilder str = new StringBuilder(q[r]);
			for(int j = at+1;j<q[r].length();j++)
			{
				int n = (q[r+1].charAt(j-1)-str.charAt(j-1)+100)%10;
				str.setCharAt(j, (char)(n+'0'));
			}
			for(int j = at-1;j>=0;j--)
			{
				int n = (q[r+1].charAt(j)-str.charAt(j+1)+100)%10;
				str.setCharAt(j, (char)(n+'0'));
			}
			q[r] = str.toString();
			
		}
		return q;
	}

}
