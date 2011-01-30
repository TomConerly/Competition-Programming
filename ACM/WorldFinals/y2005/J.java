package ACM.WorldFinals.y2005;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/* 2005 World Finals
 * Problem J: Zones
 * Type: Brute Force
 * Difficulty Coding: 2
 * Algorithmic Difficulty: 1
 * Solution: Just try all subsets of towers to keep and pick the best.
 */ 

public class J {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File("J.in"));
		for(int CASE = 1; ;CASE++)
		{
			int n = sc.nextInt();
			int k = sc.nextInt();
			if(n == 0 && k == 0) break;
			int[] size = new int[n];
			for(int i = 0; i < n;i++)size[i] = sc.nextInt();
			
			int t = sc.nextInt();
			ArrayList<ArrayList<Integer>> in = new ArrayList<ArrayList<Integer>>();
			int[] sh = new int[t];
			for(int i = 0; i < t;i++)
			{
				in.add(new ArrayList<Integer>());
				int a = sc.nextInt();
				for(int j = 0; j < a;j++)
				{
					in.get(i).add(sc.nextInt()-1);
				}
				sh[i] = sc.nextInt();
			}
			int best = Integer.MIN_VALUE;
			ArrayList<Integer> bestt = null;
			for(int i = 0; i < (1<<n);i++)
			{
				if(Integer.bitCount(i) != k) continue;
				ArrayList<Integer> set = new ArrayList<Integer>();
				int ans = 0;
				for(int j = 0; j < 20;j++)
				{
					if((i & (1<<j) )!= 0){
						ans += size[j];
						set.add(j);
					}
				}
				for(int j = 0; j < t;j++)
				{
					int count = 0;
					for(int e:in.get(j))
						if(set.contains(e)) count++;
					if(count > 1) ans -= sh[j]*(count-1);
				}
				//System.out.println(ans+" "+set);
				if(ans > best || (ans == best && better(set,bestt)))
				{
					best = ans;
					bestt = set;
					
				}
			}
			System.out.println("Case Number  "+CASE);
			System.out.println("Number of Customers: "+best);
			System.out.print("Locations recommended: ");
			for(int i = 0; i < bestt.size();i++)
			{
				System.out.print(bestt.get(i)+1);
				if(i != bestt.size()-1) System.out.print(" ");
			}
			System.out.println("\n");
			
		}
	}

	private static boolean better(ArrayList<Integer> set,
			ArrayList<Integer> bestt) {
		if(bestt == null) return true;
		for(int i = 0; i < set.size();i++)
		{
			if(set.get(i) < bestt.get(i)) return true;
			if(set.get(i) > bestt.get(i)) return false;
		}
		return false;
	}
}
