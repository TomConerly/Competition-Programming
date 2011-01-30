package TopCoder.Hard;
import java.util.*;

import static java.lang.Math.*;

//PsuedoRandomKingdom2 has a better solution
/* TopCoder SRM 394
 * Hard Problem 900 points: PsuedoRandomKingdom
 * Type: Graph Theory
 * 
 * Solution: Find a node of the form of a. Where it has a link to the rest of the tree
 * and an edge to one or more nodes who have degree one. We can find the probability that 
 * a,x,y will have a path too long, and if not what the probaility distrubution of the longest path from
 * x or y to a is. Using that we can simplify the graph until we have one node and then we are done.
 *  x
 *   \
 *    a --------
 *   /
 *  y
 */

public class PseudoRandomKingdom {

	ArrayList<ArrayList<Integer>> graph;
	ArrayList<ArrayList<Double>> prob;
	int C,S;
	double success;
	public double probabilityOfHappiness(String[] g, int cost, int savings) {
		C = cost;
		S = savings;
		prob = new ArrayList<ArrayList<Double>>();
		graph = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < g.length;i++)
		{
			prob.add(new ArrayList<Double>());
			for(int j = 0; j < S+2;j++)
				prob.get(i).add(j == 0 ? 1.0 : 0.0);
			graph.add(new ArrayList<Integer>());
			String[] sp = g[i].split(" ");
			for(String a:sp)
			{
				graph.get(i).add(Integer.valueOf(a));
			}
			Collections.sort(graph.get(i));
		}
		success = 1.0;
		//System.out.println(graph);
		//System.out.println(prob);
		//System.out.println();
		while(graph.size() > 1)
		{
			update();
			//System.out.println(graph);
			//System.out.println(prob);
			//System.out.println();
		}
		
		return success*(1-prob.get(0).get(S+1));
	}
	private void update() {
		
		for(int i = 0; i < graph.size();i++)
		{
			int count = 0;
			for(int e:graph.get(i))
			{
				if(graph.get(e).size() != 1) count++;
			}
			if(count <= 1 && graph.get(i).size() - count > 0)
			{
				fix(i);
				return;
			}
		}
		
	}
	private void fix(int k) {
		ArrayList<Integer> edges = new ArrayList<Integer>();
		for(int e: graph.get(k))
		{
			if(graph.get(e).size() == 1) edges.add(e);
		}
		Collections.sort(edges);
		
		ArrayList<Double> pr = edge(prob.get(edges.get(0)));
		for(int i = 1; i < edges.size();i++)
		{
			pr = combine(pr,edge(prob.get(edges.get(i))));
		}
		prob.set(k, pr);
		for(int i = edges.size()-1; i >= 0; i--)
		{
			remove(edges.get(i));
			prob.remove((int)edges.get(i));
			
		}

	}
	private void remove(int r) {
		graph.remove(r);
		for(int i = 0; i < graph.size();i++)
		{
			graph.get(i).remove(new Integer(r));
			for(int j = 0; j < graph.get(i).size();j++)
			{
				if(graph.get(i).get(j) > r)
					graph.get(i).set(j,graph.get(i).get(j)-1);
			}
		}
		
	}
	private ArrayList<Double> combine(ArrayList<Double> a, ArrayList<Double> b) {
		ArrayList<Double> pr = new ArrayList<Double>();
		for(int cost = 0; cost <= S+1;cost++)
		{
			pr.add(0.0);
		}
		double fail = 0.0;
		for(int i = 0; i <= S+1;i++)
		{
			for(int j = 0; j <= S+1;j++)
			{
				if(i+j > S)
					fail += a.get(i)*b.get(j);
				else
					pr.set(max(i,j),a.get(i)*b.get(j)+pr.get(max(i,j)));
			}
		}
		success *= (1-fail);
		
		double total = 0.0;
		for(int i = 0; i <= S+1;i++)
		{
			total += pr.get(i);
		}
		for(int i = 0; i <= S+1;i++)
		{
			pr.set(i,pr.get(i)/total);
		}
		
		return pr;
	}
	/*
	 * given a list of probability distributions
	 * find probability that the sum of largest 2 > k
	 * and the probability that the max of all = p for all p
	 * given that the sum of the largest 2 <= k
	 */
	private ArrayList<Double> edge(ArrayList<Double> p) {
		ArrayList<Double> pr = new ArrayList<Double>();
		for(int cost = 0; cost <= S+1;cost++)
		{
			pr.add(0.0);
		}
		for(int at = 0; at <= S+1;at++)
		{
			for(int cost = 0; cost <= C;cost++)
			{
				pr.set(min(at+cost,S+1),p.get(at)*(1/(C+1.0))+pr.get(min(at+cost,S+1)));
			}
		}
		return pr;
	}

}