package ACM.SouthAmerica.y2007;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/* ACM ICPC 2007 South American Regional 
 * Problem J: Justice League
 * Type: Graph Theory
 * Solution: Just brute force it (with some modifications because it is a sparse tree).
 * Adding the ith person will only branch in one case: if all of his friends are in the 
 * justice league and all his enemies are not.
 */

public class J {
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		while(true)
		{
			int h = s.nextInt();
			int rel = s.nextInt();
			if(h==0 && rel==0) break;
			boolean found = false;
			ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
			for(int i = 0; i < h;i++)
			{
				adjList.add(new ArrayList<Integer>());
			}
			for(int i = 0;i<rel;i++)
			{
				int a = s.nextInt()-1;
				int b = s.nextInt()-1;
				adjList.get(a).add(b);
				adjList.get(b).add(a);
			}
			int at = 0;
			LinkedList<Integer> in = new LinkedList<Integer>();
			int[] next = new int[h];			
			
			while(true)
			{				
				if(at == h)
				{
					found = true;	
					break;
				}
				if(at == -1) break;
				if(next[at] < 0)
				{
					if(next[at] == -2) in.removeLast();
					next[at]=0;
					at--;
					continue;
				}
				if(next[at] == 1)
				{
					in.removeLast();
					next[at] = -1;
					at++;
					continue;
				}		
				//are we at the end
				
				//check if we can go left or right
				boolean l = true;
				boolean r = true;
				for(int i:in)
				{
					if(!adjList.get(at).contains(i))
					{
						l = false;
					}
				}
				for(int i: adjList.get(at))
				{
					if(i < at && !in.contains(i))
					{
						r = false;
					}
				}
				if(l && r)
				{
					in.add(at);					
					next[at] = 1;
					at++;
				}else 
				if(l)
				{
					in.add(at);					
					next[at] = -2;
					at++;
				}else
				if(r)
				{					
					next[at] = -1;
					at++;
				}else
				if(!l && !r)
				{
					next[at] = 0;
					at--;
				}				
			}
			System.out.println(found?"Y":"N");
		}
	}
}
