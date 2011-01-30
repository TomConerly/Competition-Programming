package ACM.EastCentral.y2007;
import java.util.*;

/* ACM ICPC 2007 East Central Regional
 * Problem H: Target Practice
 * Type: Heuristic Search
 */
public class H {
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		for(int t=1;;t++)
		{
			//read in number of points
			n = s.nextInt();
			if(n==0) break;
			int[][] points = new int[n][3];
			//goal is bits 0 through n-1 all 1
			//which points have been hit is stored in a long
			//so if goal == hit then all points are hit
			goal = 0;
			for(int i = 0; i < n;i++)
			{
				for(int j = 0; j < 3;j++)
				{
					points[i][j] = s.nextInt();
				}
				goal |= 1L << i;
			}
			
			//shotsL keeps track of the shots in long form where
			//there is a 1 in the ith position if the ith target is hit
			//that is for the first 50 bits, the next 12 bits indicate how many targets
			//are hit by this shot
			shotsL = new ArrayList<Long>();
			
			//for any two points make the line and see how many points are on that line
			//if there are more than two points then save it otherwise toss it
			for(int i = 0; i < n;i++)
			{
				for(int j = i+1;j<n;j++)
				{
					int dx = (points[i][0]-points[j][0]);
					int dy = (points[i][1]-points[j][1]);
					int dz = (points[i][2]-points[j][2]);
					
					long shotL = 0;
					long hits = 0;
					//note that this loops over i and j also
					for(int k = 0; k < n;k++)
					{
						int x = (points[i][0]-points[k][0]);
						int y = (points[i][1]-points[k][1]);
						int z = (points[i][2]-points[k][2]);						
						
						//test if the third point lies on the line of the first two
						if(dx*y==x*dy && dy*z==y*dz && dx*z == x*dz)
						{
							hits++;
							shotL |= 1L << k;
						}
					}
					//make sure to not store the same line twice
					if(hits > 2 && !shotsL.contains(shotL| (hits << 50))){
						shotsL.add(shotL | (hits << 50));
					}
				}
			}
			//sort them by most number of targets they hit
			Collections.sort(shotsL,Collections.reverseOrder());
			
			//we know that we can hit two targets with each shot so upper bound our best score
			//this lets us cuts off parts of the search space
			best = (n+1)/2;
			recur(0,0,0);
			System.out.println("Target set "+t+" can be cleared using only "+best+" shots.");
		}
	}
	static int n;
	static ArrayList<Long> shotsL;
	static int best;
	static long goal;
	//at is the index in the list of shots that we are either using or not
	//hit has the ith bit on if the ith target has been hit
	public static void recur(int at,int shotsTaken, long hit)
	{		
		int left = n-Long.bitCount(hit);
		//we can finish from here using lines that hit two points
		//check if that is better than what we have
		best = Math.min(best, shotsTaken+((left+1)/2));
		
		if(at == shotsL.size()) return;
		
		//we can figure out how many targets we can get with out next shot
		//this is the best shot of the shot left so we can end if
		//all the shots are as good as this one and we still can't beat the best
		long maxNext = shotsL.get(at) >> 50;	
		if(shotsTaken +((left+maxNext-1)/maxNext) >= best) return;	
		
		recur(at+1,shotsTaken+1,hit|(shotsL.get(at) & 0x3FFFFFFFFFFFFL));
		recur(at+1,shotsTaken,hit);
		
	}
}
