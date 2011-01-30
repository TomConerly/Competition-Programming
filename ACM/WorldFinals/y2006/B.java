package ACM.WorldFinals.y2006;

import java.util.*;
import java.io.*;
import static java.lang.Math.*;

/* 2006 World Finals
 * Problem B: Remember the A La Mode!
 * Type: Min Cost Max Flow
 * Difficulty Coding: 3 
 * Algorithmic Difficulty: 4
 * Solution: Setup your graph as follows it is bipartite. The left side is 
 * pies and the right side is ice cream. The flow between the source/sink 
 * and the side is the amount you have, cost is 0. There is an edge between
 * a pie/ice cream of unlimited capacity if we can assign them to each other
 * the cost is the amount of benefit we get per unit we combine. Then find
 * the maximum cost flow.
 */ 

public class B {

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(new File("B.in"));

		for(int CASE = 1;;CASE++)
		{
			int P = sc.nextInt();
			int I = sc.nextInt();
			if(P == 0 && I == 0) break;
			int[] numP = new int[P];
			for(int i = 0; i < P;i++)
				numP[i] = sc.nextInt();
			int[] numI = new int[I];
			for(int i = 0; i < I;i++)
				numI[i] = sc.nextInt();
			double[][] cost = new double[P][I];
			double max = 0;
			for(int i = 0; i < P;i++)
			{
				for(int j = 0; j < I;j++)
				{
					cost[i][j] = sc.nextDouble();
					max = max(cost[i][j],max);
				}
			}
			
			double min = assignmentProblem(cost,numP,numI)[0];

			for(int i = 0; i < P;i++)
			{
				for(int j = 0; j < I;j++)
				{
					if(cost[i][j] != -1) cost[i][j] = max - cost[i][j];
				}
			}
			double[] dat = assignmentProblem(cost,numP,numI);
			double high = -dat[0]+dat[1]*max;
			System.out.format("Problem %d: %.2f to %.2f\n",CASE, min,high);

		}




	}


	static int start;
	static int end;
	static double[][] flow;
	static double[][] matr;
	static int N;

	private static class State {

		double[] cost;
		int[] pred;
		State(double[] a, int[] b)
		{
			cost = a;
			pred = b;
		}

	}

	public static double[] assignmentProblem(double [][] m,int[] flowA,int[] flowB) 
	{		
		N = m.length;
		start = m.length+m[0].length;
		end = start+1;
		flow = new double[end + 1][end+1];
		matr = m;
		for (int i = 0; i < matr.length; i++) {
			for (int j = 0; j < matr[0].length; j++) {
				flow[i][j+matr.length] = 100000;
			}
			flow[start][i] = flowA[i];
			
		}
		for(int j = 0; j < matr[0].length;j++)
		{
			flow[j+matr.length][end] = flowB[j];
		}


		double[] p = new double[flow.length];

		double c = 0;
		int f = 0;
		while(true){
			State s = Dijk(start, p, flow);
			if(s.pred[end] == -1) break;
			c += s.cost[end] - p[start] + p[end]; 
			for (int j = 0; j < p.length; j++) {
				p[j] += s.cost[j];
			}
			f++;
			for (int at = end; at != start; at = s.pred[at]) {
				flow[s.pred[at]][at] -= 1;
				flow[at][s.pred[at]] += 1;				
			}
		}
		return new double[]{c,f};


	}

	public static State Dijk(int start, double[] p, double[][] flow){


		PriorityQueue<Node>pq = new PriorityQueue<Node>();
		boolean[] visited = new boolean[flow.length];
		double[] dist = new double[flow.length];
		int[] pred = new int[flow.length];

		Arrays.fill(dist, Double.MAX_VALUE);
		dist[start] = 0;
		Arrays.fill(pred, -1);

		pq.add(new Node(start,0));
	//	System.out.println("Start");
		while (pq.size() > 0) {
			
			Node n = pq.poll();
			int node = n.at;
			
			double c = n.c;
			if (visited[node]) continue;
			visited[node] = true;
		//	System.out.println(node);
			for (int i = 0; i < flow.length; i++) {
				double cost = 0;
				if(i == start ||i == end || node == start || node == end) cost = 0;
				else if(node < N && i >= N) cost = matr[node][i-N];
				else if(i < N && node >= N) cost = -matr[i][node-N];
				else continue;
				
				if (flow[node][i] <= 0 || cost == -1) continue;
				double newCost = c + cost + p[node] - p[i];
				if (dist[i] > newCost) {
					dist[i] = newCost;
					pq.add(new Node(i,dist[i]));
					pred[i] = node;
				}
			}
		}
		return new State(dist,pred);
	}
	public static class Node implements Comparable<Node>
	{
		int at;
		double c;
		Node(int a, double b)
		{
			at = a;
			c = b;
		}
		public int compareTo(Node n) {
			if(c < n.c) return -1;
			if(c > n.c) return 1;
			return 0;
		}


	}




}





