package ACM.SouthAmerica.y2007;
import java.io.File;
import java.util.*;

/* ACM ICPC 2007 South American Regional 
 * Problem A: Ambiguous Codes
 * Type: Dijkstra, DP
 * Solution: All possible states are the leftover letters we are trying to match.
 * Those letters are the last n of any of the words so we can search all
 * possible leftover letters.
 */

public class A
{
	public static void main(String[] args) throws Exception
	{
		Scanner s = new Scanner(new File("B.in"));
		while(true)
		{
			n = s.nextInt();
			if(n==0) break;
			s.nextLine();
			words = new String[n];
			table = new int[n][51];
			for(int i = 0; i < n;i++)
			{
				words[i] = s.nextLine();
			}
			gBest = Integer.MAX_VALUE;
			for(int i = 0; i < n;i++)
			{
				for(int j = 0; j < n;j++)
				{
					if(i==j) continue;
					if(words[i].startsWith(words[j]))
					{
						gBest = Math.min(gBest,dijik(words[j].length(),i,words[i].length()-words[j].length()));
					}
				}
			}
			if(gBest < 1<<14)
				System.out.println(gBest);
			else
				System.out.println("-1");
		}
	}
	public static int dijik(int start, int startWord, int startChars)
	{
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for(int i = 0; i < n;i++)
		{
			for(int j = 0; j < 51;j++)
			{
				if(j > words[i].length()) break;
				if(i==startWord && j == startChars){
					table[i][j] = 0;
					pq.add((start << 16)|(i<<8 )| j);
				}
				else
				{
					table[i][j] = 1 << 14;
					pq.add((table[i][j]<<16)|(i<<8 )| j);
				}
			}
		}
		table[startWord][startChars] = 0;
		boolean[][] visited = new boolean[table.length][table[0].length];
		while(pq.size() > 0)
		{
			int next = pq.poll();
			int dist = next >>> 16;
			if(dist >= gBest) break;
			
			int i = (next & 0xFF00) >>> 8;
			int j = (next & 0xFF);
		
			if(visited[i][j]) continue;
			visited[i][j] = true;	
			int len = words[i].length();
			String toMatch = words[i].substring(len-j,len);
			
			for(int k = 0; k < words.length;k++)
			{
				String s = words[k];
				int toI=-1; 
				int toJ=0; 
				int cost=0;
				if(s.startsWith(toMatch))
				{
					toI = k;
					toJ = s.length()-j;
					cost = j;
				}
				else if(toMatch.startsWith(s))
				{
					toI = i;
					toJ = j - s.length();
					cost = s.length();
				}
				if(toI != -1 && cost + dist < table[toI][toJ])
				{
					table[toI][toJ] = cost + dist;
					pq.add((table[toI][toJ]<<16)|(toI<<8)|toJ);
				}
			}
		}
		int best = 1 << 14;
		for(int i = 0; i < words.length;i++)
		{
			best = Math.min(best,table[i][0]);
		}
		return best;
	}
	static int n;
	static int gBest;
	static String[] words;
	static int[][] table;
}
