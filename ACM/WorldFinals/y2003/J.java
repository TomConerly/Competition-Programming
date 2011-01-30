package ACM.WorldFinals.y2003;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/* 2003 World Finals
 * Problem J: Toll
 * Type: Dijkstra
 * Difficulty Coding: 3 
 * Algorithmic Difficulty: 3
 * Solution: Work from the end forward. Use Dijkstra to compute at each vertex
 * what is the minimum number of items you need there to be able to reach the
 * end with correct amount.
 */ 

public class J {
	static ArrayList<ArrayList<Integer>> graph;
	static int T,S,E;
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File("J.in"));
		for(int CASE = 1; ;CASE++)
		{
			int n = sc.nextInt();
			if(n == -1) break;
			graph = new ArrayList<ArrayList<Integer>>();
			for(int i = 0; i < 52;i++)
			{
				graph.add(new ArrayList<Integer>());
			}
			sc.nextLine();
			for(int i = 0; i < n;i++)
			{
				String s = sc.nextLine();
				int first = v(s.charAt(0));
				int second = v(s.charAt(2));
				graph.get(first).add(second);
				graph.get(second).add(first);
			}
			String s = sc.nextLine();
			String[] p = s.split(" ");
			T = Integer.valueOf(p[0]);
			S = v(p[1].charAt(0));
			E = v(p[2].charAt(0));
			int ans = solve();
			System.out.println("CASE "+CASE+": "+ans);
		}
	}
	private static int solve() {
		PriorityQueue<Long> pq= new PriorityQueue<Long>();
		long[] best = new long[52];
		pq.add((((long)T)<<32)|E);
		Arrays.fill(best,Long.MAX_VALUE);
		best[E] = T;
		while(pq.size() > 0)
		{
			long l = pq.poll();
			int at = (int)(l & 0xFFFFFFFFL);
			int num = (int)(l>>>32);
			if(at == S) return num;
			if(best[at] < num) continue;
			
			for(int e: graph.get(at))
			{
				if(e < 26)
				{
					if(num+1 < best[e])
					{
						pq.add((((long)(num+1))<<32)| e);
						best[e] = num+1;
					}
				}else{
					int n = find(num);
					if(n < best[e])
					{
						pq.add((((long)(n))<<32)|e);
						best[e] = n;
					}
				}
			}
		}
		return -1;
	}
	private static int find(int num) {
		int guess = num + (int)Math.ceil(num/20.0);
		for(int i = guess;;i++)
		{
			if(i-(int)Math.ceil(i/20)>=num) return i;
		}
	}
	private static int v(char c) {
		if(Character.isLowerCase(c))
		{
			return c-'a';
		}else return 26 + c-'A';
	}
}
