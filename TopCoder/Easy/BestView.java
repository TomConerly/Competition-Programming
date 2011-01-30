package TopCoder.Easy;/* TopCoder SRM 436 * Easy Problem 250 Points: BestView * Type: Simulation * Solution: Just do it. Use Java Line2D for easy intersection. */import static java.lang.Math.*;import java.awt.geom.Line2D;public class BestView {

	public int numberOfBuildings(int[] heights) {
		Line2D.Double[] lines = new Line2D.Double[heights.length];		for(int i = 0; i < heights.length;i++)		{			lines[i] = new Line2D.Double(i,0,i,heights[i]);		}		int max = 0;		for(int i = 0; i < heights.length;i++)		{			int count = 0;			for(int j = 0; j < heights.length;j++)			{				if(i==j) continue;				Line2D.Double check = new Line2D.Double(i,heights[i],j,heights[j]);				boolean good = true;				for(int k = 0; k < heights.length;k++)				{					if(k == i || k == j) continue;					if(check.intersectsLine(lines[k]))					{						good = false;						break;					}				}				if(good) count++;			}			max = max(count,max);		}		return max;
	}

}
