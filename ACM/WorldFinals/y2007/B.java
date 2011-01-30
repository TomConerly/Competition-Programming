package ACM.WorldFinals.y2007;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/* 2007 World Finals
 * Problem B: Containers
 * Type: Greedy
 * Difficulty Coding: 2
 * Algorithmic Difficulty: 3
 * Solution: You have your piles on shore, it is always
 * better to put the new item on the pile which minimizes
 * the difference between the item on top and the item put on.
 */ 

public class B {
	public static void main(String[] arg) throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File("B.in"));
		for(int z = 1;;z++)
		{
			String s = sc.next();
			if(s.equals("end")) break;
			int[] stacks = new int[s.length()+1000];
			Arrays.fill(stacks,-1);
			int num = 0;
			for(int i = 0; i < s.length();i++)
			{
				int c = s.charAt(i)-'A';
				int bestAt = -1;
				int best = 0;
				for(int j = 0; j  <num;j++)
				{
					if(c <= stacks[j])
					{
						if(bestAt == -1 || stacks[j] < best)
						{
							bestAt = j;
							best = stacks[j];
						}
					}
				}
				//System.out.println(i+" "+bestAt);
				if(bestAt == -1)
				{
					stacks[num++]=c;
				}else{
					stacks[bestAt] = c;
				}
			}
			System.out.println("Case "+z+": "+num);
			
		}
	}
}
