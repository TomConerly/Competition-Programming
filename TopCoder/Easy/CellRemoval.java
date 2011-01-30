package TopCoder.Easy;
/* TopCoder SRM 435
 * Easy Problem 250 Points: CellRemoval
 * Type: Trees
 * Solution: Simulate
 */

public class CellRemoval {

	public int cellsLeft(int[] parent, int D) {
		int N = parent.length;
		int ans = 0;
		boolean[] current = new boolean[N];
		for(int i = 0; i < N;i++)
			if(parent[i] == -1 && i != D) current[i] = true;
		
		while(true)
		{
			boolean changed = false;
			for(int i = 0; i < N;i++)
			{
				if(parent[i] >= 0 && current[parent[i]] && !current[i] && i != D)
				{
					current[i] = true;
					changed = true;
				}
			}
			if(!changed) break;
		}
		for(int i = 0; i < N;i++)
		{
			if(parent[i] >=0)current[parent[i]] = false;
		}
		for(int i = 0; i < N;i++)
			if(current[i])ans++;
		return ans;
	}
}
