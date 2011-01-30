package SSU;
import java.util.*;

/* Saratov State University Online Judge
 * Problem 101: Domino
 * Type: Graph Theory
 * Solution: Find a Euler tour on a graph.
 */

public class p101 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args){
		Scanner sc= new Scanner(System.in);
		int N = sc.nextInt();
		adjList = new ArrayList[7];
		for(int i = 0; i< 7;i++)
			adjList[i] = new ArrayList<Edge>();

		Edge[] edges = new Edge[N];
		for(int i = 0; i < N;i++){
			int a = sc.nextInt();
			int b = sc.nextInt();
			adjList[a].add(new Edge(a,b,i));
			adjList[b].add(new Edge(b,a,i));
			edges[i]= new Edge(a,b,i);
		}
		int num = 0;
		for(int i = 0; i < 7;i++)		
			if(adjList[i].size() %2 == 1)
				num++;			
		
		//check that it is connected		
		v = new boolean[7];
		for(int i = 0; i < 7;i++)
			if(adjList[i].size() > 0){
				bfs(i);
				break;
			}		
		boolean good = true;
		for(int i = 0; i < 7;i++)
			if(!v[i] && adjList[i].size() > 0)			
				good = false;
		
		if(!good || (num != 0 && num != 2)){
			System.out.println("No solution");
		}else {
			circuit = new ArrayList<Edge>();
			int first = 0;
			while(adjList[first].size() == 0 || (num == 2 && adjList[first].size() %2 != 1))			
				first++;
			
			circuit(first);
			Collections.reverse(circuit);
			for(Edge e: circuit)
				if(e.x == edges[e.i].x)
					System.out.println((e.i+1)+" +");
				else
					System.out.println((e.i+1)+" -");
		}

	}
	private static void bfs(int i) {
		if(v[i])
			return;
		v[i] = true;
		for(Edge e: adjList[i])
			bfs(e.y);		
	}
	static boolean[] v;
	static ArrayList<Edge>[] adjList;
	static ArrayList<Edge> circuit;
	
	public static void circuit(int at){
		while(adjList[at].size() > 0){
			Edge e = adjList[at].remove(0);
			adjList[e.y].remove(e);				
			circuit(e.y);
			circuit.add(e);				
		}
	}
	private static class Edge{
		int x,y,i;
		Edge(int a,int b, int c){
			x = a;
			y = b;
			i = c;
		}
	}
}
