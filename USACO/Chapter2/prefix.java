package USACO.Chapter2;
/*
ID: theycal2
LANG: JAVA
TASK: prefix
 */
/* USACO Training
 * Longest Prefix
 * Type: DP
 * Solution: It only matters if you can 
 * match the first i characters of the string.
 */
import java.io.*;
import java.util.*;

public class prefix 
{
	public static void main(String[] args) throws IOException 
	{
		
		BufferedReader f = new BufferedReader(new FileReader("prefix.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));

		prim = new ArrayList<String>();

		while(true)
		{
			String line = f.readLine();
			if(line.equals(".")) break;
			StringTokenizer st = new StringTokenizer(line);
			while(st.hasMoreTokens())
			{
				prim.add(st.nextToken());
			}
		}
		match = new StringBuilder(200010);
		while(f.ready()){
			match.append(f.readLine());
		}
		visited = new boolean[match.length()+1];
		pList = prim.toArray(new String[1]);
		recur();

		int max = 0;
		for(int i = 0; i < visited.length;i++)
		{
			if(visited[i]) max = i;
		}
		out.println(max);
		out.close();
	}

	public static void recur() {
		visited[0] = true;

		for(int i = 0; i < match.length();i++)
		{
			if(!visited[i]) continue;
			for(int k = 0; k < pList.length;k++)
			{
				String s = pList[k];				
				if(i+s.length() > match.length() || visited[i+s.length()]) continue;

				boolean good = true;
				for(int j = 0; j < s.length();j++)
				{
					if(s.charAt(j) != match.charAt(i+j)) {
						good = false;
						break;
					}
				}
				if(good)
				{
					
					visited[i+s.length()] = true;
				}
			}
		}

	}
	static String[] pList;
	static boolean[] visited;
	static ArrayList<String> prim;
	static StringBuilder match;

}

