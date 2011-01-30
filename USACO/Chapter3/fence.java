package USACO.Chapter3;
/*
ID: theycal2
LANG: JAVA
TASK: fence
 */
/* USACO Training
 * Riding the Fences
 * Type: Graph Theory
 * Solution:Run Eurelian tour.
 */
import java.io.*;
import java.util.*;


public class fence 
{
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException 
	{
		long start = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("fence.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int F = Integer.valueOf(st.nextToken());

		ArrayList<Integer>[] adjList = (ArrayList<Integer>[])new ArrayList[500];
		for(int i = 0; i < 500;i++)
		{
			adjList[i] = new ArrayList<Integer>();
		}
		for(int i = 0; i < F;i++)
		{
			st = new StringTokenizer(f.readLine());
			int s = Integer.valueOf(st.nextToken())-1;
			int e = Integer.valueOf(st.nextToken())-1;

			adjList[s].add(e);
			adjList[e].add(s);
		}
		boolean odd = false;
		for(int i = 0; i < 500;i++)
		{
			Collections.sort(adjList[i]);
			if((adjList[i].size() %2) == 1) odd = true;
		}
		ArrayList<Integer> best = null;
		for(int i = 0; i < 500;i++)
		{
			if(adjList[i].size() == 0) continue;
			if(odd && (adjList[i].size() %2) == 0) continue;
			ArrayList<Integer> c = minTour(i,adjList);
			Collections.reverse(c);
			if(better(best,c))
				best = c;
		}
		for(int i = 0; i < best.size();i++)
		{
			out.println(best.get(i)+1);
		}
		out.close();
		System.out.println("$:"+(System.currentTimeMillis()-start));
		System.exit(0);
	}	
	public static boolean better(ArrayList<Integer> best, ArrayList<Integer> test)
	{
		if(test == null) return false;
		if(best == null) return true;
		for(int i = 0; i < best.size();i++)
		{
			if(test.get(i) < best.get(i)) return true;
			if(test.get(i) > best.get(i)) return false;
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	public static ArrayList<Integer> minTour(int start,ArrayList<Integer>[] a)
	{
		adjList = (ArrayList<Integer>[])new ArrayList[500];
		for(int i = 0; i < 500;i++)
		{
			adjList[i] = new ArrayList<Integer>();
		}
		for(int i = 0; i < 500;i++)
		{
			for(int j = 0; j < a[i].size();j++)
			{
				adjList[i].add(a[i].get(j));
			}
			
		}
		circuit = new ArrayList<Integer>();
		circuit(start);
		return circuit;
	}
	static ArrayList<Integer>[] adjList;
	static ArrayList<Integer> circuit;
	public static void circuit(int at)
	{
		if(adjList[at].size() == 0)
		{
			circuit.add(at);
		}
		else
		{
			while(adjList[at].size() > 0)
			{
				int to = adjList[at].remove(0);
				adjList[to].remove(new Integer(at));
				circuit(to);
			}
			circuit.add(at);			
		}
	}
}

