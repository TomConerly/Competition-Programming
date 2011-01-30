package ACM.SouthAmerica.y2006;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/* ACM ICPC 2006 South American Regional 
 * Problem C: JukeBox
 * Type: Brute Force
 * Solution: Artists will either be removed entirely or there will only be one instance of an artist.
 * If there is only one instance of an artist it will go to the song which has the highest cost. So
 * just brute force the 2^6 possible combinations.
 */

public class C {
	static ArrayList<HashSet<String>> songSubsets = new ArrayList<HashSet<String>>();
	static ArrayList<HashSet<String>> artistSubsets = new ArrayList<HashSet<String>>();
	static int[] artistNum;
	static int n;
	
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		while(true)
		{
			n = s.nextInt();
			if(n==0) break;
			String[] songs = new String[n];
			String[] artists = new String[n];
			s.nextLine();
			for(int i = 0; i < n;i++)
			{
				songs[i] = s.nextLine();
				artists[i] = s.nextLine();
			}
			artistNum = new int[n];
			Arrays.fill(artistNum,-1);
			int at = 0;
			songSubsets.clear();
			artistSubsets.clear();
			
			for(int i = 0; i < artists.length;i++)
			{
				if(artistNum[i] != -1)	continue;
				
				artistNum[i] = at;
				for(int j = i+1; j < artists.length;j++)
				{
					if(artistNum[j] != -1) continue;
					if(artists[i].equals(artists[j]))
					{
						artistNum[j] = at;
					}
				}
				at++;
			}
			for(int i = 0; i < artists.length;i++)
			{
				artistSubsets.add(new HashSet<String>());
				for(int j = 0; j <= artists[i].length();j++)
				{
					for(int k = j+1;k<=artists[i].length();k++)
					{
						artistSubsets.get(i).add(artists[i].substring(j,k));
					}
				}
			}
			for(int i = 0; i < songs.length;i++)
			{
				songSubsets.add(new HashSet<String>());
				for(int j = 0; j <= songs[i].length();j++)
				{
					for(int k = j+1;k<=songs[i].length();k++)
					{
						songSubsets.get(i).add(songs[i].substring(j,k));
					}
				}
			}
			best = Integer.MAX_VALUE;
			
			int[][] scores = new int[1<<at][n];
			for(int k = 0; k < 1<<at;k++)
			{
				for(int i = 0; i < n;i++)
				{
					scores[k][i] = bestUsed(songSubsets.get(i),i,new boolean[n],k);
				}
			}
			int[][] min = new int[1<<at][at];
			for(int k = 0; k < 1<<at;k++)
			{
				for(int i = 0; i < at ;i++)
				{
					int minAt = -1;
					for(int j = 0; j < n;j++)
					{
						if(artistNum[j] != i) continue;
						if(minAt == -1) minAt = j;
						if(scores[k][minAt] < scores[k][j]) minAt = j;
					}
					min[k][i] = minAt;
				}
			}
			recur(0,min,new boolean[n],0);
			System.out.println(best);
		}
	}
	static int count = 0;
	static int best;
	public static void recur(int at, int[][] min, boolean[] used,int aused)
	{
		if(at == n)
		{
			score(used);
			count++;
			return;
		}
		recur(at+1,min,used,aused);
		for(int i = 0; i < min[aused].length;i++)
		{
			int j = min[aused][i];
			if(j == at)
			{
				used[at] = true;
				recur(at+1,min,used,aused|(1<<i));
				used[at] = false;
			}
		}
	}
	public static int bestUsed(HashSet<String> set,int i,boolean[] used,int aused)
	{
		int min = 1000000;
		for(String s: set)
		{
			if(s.length() >= min) continue;
			boolean found = false;
			for(int j = 0; j < used.length;j++)
			{
				if(j==i) continue;
				if(songSubsets.get(j).contains(s)) 
				{
					found = true;
					break;
				}
			}
			for(int j = 0; j < used.length;j++)
			{
				if(found) break;
				if((!used[j]&& ((aused & (1 << j))==0)) || i==j) continue;
				if(artistSubsets.get(j).contains(s)) 
				{
					found = true;
					break;
				}
			}
			if(!found) min = Math.min(min,s.length());
		}
		return min;
	}
	public static void score(boolean[] used)
	{
		int total = 0;
		for(int i = 0; i < used.length;i++)
		{
			int min = bestUsed(songSubsets.get(i),i,used,0);
			if(used[i]) min = Math.min(min,bestUsed(artistSubsets.get(i),i,used,0));
			total += min;
		}
		best = Math.min(best,total);
	}
}
