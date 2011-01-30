package SSU;
import java.util.*;
import static java.lang.Math.*;

/* Saratov State University Online Judge
 * Problem 114: Telecasting station
 * Type: Greedy
 * Solution: You know the solution must be on a city. If it isn't and there are more cities to one side of the solution it would be better to move
 * towards that side until you hit a city. If the number on each side is equal then you can move left or right to a city w/o changing the answer.
 * If you go from left to right at some point the number of items to your left will pass from more than half to less than half. That is the point
 * where you should test the cities where it changes as they are the only real candidates.
 */

public class p114 {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		long total = 0;
		nodes = new Node[N+2];
		for(int i = 0; i < N;i++)
		{
			nodes[i+1] = new Node(sc.nextInt(),sc.nextInt());
			total += nodes[i+1].p;
		}
		nodes[0] = new Node(0,0);
		nodes[nodes.length-1] = new Node(15000,0);
		Arrays.sort(nodes);
		
		long bestAt = -1;
		
		long soFar = 0;
		for(int i = 0; i < N;i++)
		{
			soFar += nodes[i].p;
			
			if(soFar <= (total+1)/2 && (soFar+nodes[i+1].p) >= total/2)
			{
				long a = score(nodes[i].x);
				long b = score(nodes[i+1].x);
				if(a<b)
					bestAt = nodes[i].x;
				else
					bestAt = nodes[i+1].x;			
			}
		}
		
		System.out.println(bestAt);
	}
	static Node[] nodes;
	private static long score(long at) {
		long score = 0;
		for(int j = 0; j < nodes.length;j++)
			score += abs(at-nodes[j].x)*nodes[j].p;
		return score;
	}
	private static class Node implements Comparable<Node>
	{
		int x,p;
		Node(int a, int b)
		{
			x = a;
			p = b;
		}
		@Override
		public int compareTo(Node n) {
			return Integer.valueOf(x).compareTo(n.x);
		}
	}
}
