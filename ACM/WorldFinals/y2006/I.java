package ACM.WorldFinals.y2006;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import static java.lang.Math.*;

/* 2006 World Finals
 * Problem I: Degrees of Separation
 * Type: Floyd–Warshall
 * Difficulty Coding: 2
 * Algorithmic Difficulty:1
 * Solution: Finding the diameter of a graph.
 */ 

public class I {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File("I.in"));
		for(int CASE = 1;;CASE++)
		{
			int P = sc.nextInt();
			int R = sc.nextInt();
			if(P == 0 && R == 0) break;
			int[][] dist = new int[P][P];
			for(int i = 0; i < P;i++)
				Arrays.fill(dist[i],10000);
			sc.nextLine();
			hm = new HashMap<String,Integer>();
			for(int i= 0; i < R;i++)
			{
				String a = sc.next();
				String b = sc.next();
				int an = num(a);
				int bn = num(b);
				dist[an][bn] = 1;
				dist[bn][an] = 1;
			}
			for(int i = 0; i < P;i++)
			{
				for(int j = 0; j < P;j++)
				{
					for(int k = 0; k < P;k++)
					{
						dist[j][k] = min(dist[j][k],dist[j][i]+dist[i][k]);
					}
				}
			}
			int max = 0;
			for(int i = 0; i < P;i++)
			{
				for(int j = 0; j < P;j++)
				{
					max = max(max,dist[i][j]);
				}
			}
			if(max == 10000){
				System.out.format("Network %d: DISCONNECTED\n",CASE);
			}else{
				System.out.format("Network %d: %d\n",CASE,max);
			}
		}
	}
	static HashMap<String,Integer> hm;
	private static int num(String s) {
		if(hm.containsKey(s)) return hm.get(s);
		else hm.put(s,hm.size());
		return hm.get(s);
	}
}
