package TopCoder.Medium;
import java.awt.Point;
import java.util.*;
/* TopCoder SRM 396
 * Medium Problem 500 Points: FixImage
 * Type: Simulation
 * Solution: The only thing wrong with a block is either
 * xxx or xx
 * x.x    x.
 *        xx
 * So just check for those and add the points and then repeat.
 */

public class FixImage {

	int[][] v;
	ArrayList<Point> curGroup;
	StringBuilder[] img;
	public String[] originalImage(String[] img1) {
		img = new StringBuilder[img1.length];
		for(int i = 0; i < img1.length;i++)
			img[i] = new StringBuilder(img1[i]);
		v = new int[img.length][img[0].length()];
		curGroup = new ArrayList<Point>();
		int groupNum = 1;
		boolean changed = true;
		again:
		while(changed == true)
		{
			changed = false;
			for(int i = 0; i < img.length;i++)
				Arrays.fill(v[i],0);
			for(int i = 0; i < img.length;i++)
			{
				for(int j = 0; j < img[0].length();j++)
				{
					if(v[i][j] == 0 && img1[i].charAt(j) == '#')
					{
						dfs(i,j,groupNum++);
						if(fix(groupNum,curGroup))
						{
							changed = true;
							curGroup.clear();
							continue again;
						}
						curGroup.clear();
					}
				}
			}
		}
		String[] im = new String[img1.length];
		for(int i = 0; i < im.length;i++)
		{
			im[i] = img[i].toString();
		}
		return im;
	}

	private boolean fix(int groupNum, ArrayList<Point> c) 
	{
		boolean change = false;
		ArrayList<ArrayList<Integer>> row = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> col = new ArrayList<ArrayList<Integer>>();
		
		for(int i = 0; i < img.length;i++)
			row.add(new ArrayList<Integer>());
		for(int i = 0; i < img[0].length();i++)
			col.add(new ArrayList<Integer>());
		
		for(int i = 0; i < c.size();i++)
		{
			row.get(c.get(i).x).add(c.get(i).y);
			col.get(c.get(i).y).add(c.get(i).x);
		}
		for(int i = 0; i < row.size();i++)
		{
			
			ArrayList<Integer> test = row.get(i);
			Collections.sort(test);
			for(int j = 0; j < test.size()-1;j++)
			{
				if(test.get(j) +1 != test.get(j+1))
				{
					for(int k = test.get(j)+1;k<test.get(j+1);k++)
					{
						img[i].setCharAt(k,'#');
						change = true;
					}
				}
			}
		}
		for(int i = 0; i < col.size();i++)
		{
			ArrayList<Integer> test = col.get(i);
			Collections.sort(test);
			for(int j = 0; j < test.size()-1;j++)
			{
				if(test.get(j) +1 != test.get(j+1))
				{
					for(int k = test.get(j)+1;k<test.get(j+1);k++)
					{
						img[k].setCharAt(i,'#');
						change = true;
					}
				}
			}
		}
		return change;
	}

	public void dfs(int x, int y,int g)
	{
		if(v[x][y] != 0) return;
		curGroup.add(new Point(x,y));
		v[x][y] = g;
		for(int d = -1; d < 2; d++)
		{
			if(black(x+d,y))
			{
				dfs(x+d,y,g);
			}
			if(black(x,y+d))
			{
				dfs(x,y+d,g);
			}
		}
	}
	private boolean black(int x, int y) {
		if(x < 0 || x >= img.length) return false;
		if(y < 0 || y >= img[0].length()) return false;
		return img[x].charAt(y) == '#';
	}

}
