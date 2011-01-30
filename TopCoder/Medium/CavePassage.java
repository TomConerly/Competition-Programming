package TopCoder.Medium;
import java.util.*;

/* TopCoder SRM 422
 * Medium Problem 500 Points: CavePassage
 * Type: Dijkstra
 * Solution: Just use Dijkstra so search. Make sure to check things before we put them in the PQ.
 * If we put them in then we add a significant amount of cost.
 */

public class CavePassage {
	
	public int minimalTime(int[] travelersWeights, int[] travelersTimes, String[] trustTable, int bridgeStrength) {
		N = travelersWeights.length;
		maxWeight = bridgeStrength;
		weight = new int[1<<N];
		time = new int[1<<N];
		legal = new boolean[1<<N];
		
		for(int i = 1; i < (1<<N);i++)
		{
			ArrayList<Integer> in = new ArrayList<Integer>();
			for(int j = 0; j < N;j++)
			{
				if((i & (1<<j)) != 0){
					in.add(j);
					weight[i] += travelersWeights[j];
					time[i] = Math.max(time[i],travelersTimes[j]);
				}

			}
			boolean[] good = new boolean[in.size()];
			for(int j = 0; j < in.size();j++)
			{
				for(int k = 0; k < in.size();k++)
				{
					if(j== k) continue;
					if(trustTable[in.get(j)].charAt(in.get(k)) == 'Y')
					{
						good[j] = true;
					}
				}
			}
			legal[i] = true;
			for(int j = 0; j < good.length;j++)
			{
				if(good[j] != true) legal[i] = false;
			}
			if(good.length == 1) legal[i] = true;
			
		}
		legal[0] = false;
		PriorityQueue<Long> pq = new PriorityQueue<Long>();
		pq.add(0|((((1L<<N)-1) << 16)|(1L<<31)));

		int[] visited = new int[1<<(N+1)];
		Arrays.fill(visited,Integer.MAX_VALUE);
		
		//HashSet<Integer> v = new HashSet<Integer>();
		
		int maxSize = 0;
		int count = 0;
		while(pq.size() > 0)
		{
			maxSize = Math.max(maxSize,pq.size());
			long p = pq.poll();
			count++;
			int cost = (int)(p>>>32);
			int poll = (int)p;
			int left = (poll >>> 16)&0x7FFF;
			int right = poll & 0xFFFF;			
			boolean onLeft = (poll >>> 31) == 1;
			
			//if(v.contains(poll))continue;
			//v.add(poll);
			if(right == (1<<N)-1){
				System.out.println(count+" "+maxSize);
				return cost;
			}

			if(onLeft)
			{
				for(int i = left; i > 0; i =((i-1)&left))
				{
					if((i | left) != left) continue;
					if(!legal[i]) continue;
					if(weight[i] > maxWeight) continue;
					
					long t = cost + time[i];
					
					if(visited[left-i] <= t) continue;
					visited[left-i] = (int)t;
					
					pq.add((t<<32)|((((left-i)<<16) | (right|i))));
				}
			}else{
				for(int i = right; i > 0; i=((i-1)&right))
				{
					if((i | right) != right) continue;
					if(!legal[i]) continue;
					if(weight[i] > maxWeight) continue;
					
					long t = cost + time[i];
					
					if(visited[(left|i)|(1<<(N))] <= t) continue;
					visited[(left|i)|(1<<N)] = (int)t;
					
					pq.add((t<<32)|(1L<<31)|((((left|i)<<16) | (right-i))));									
				}				
			}
		}
		System.out.println(count+" "+maxSize);
		return -1;
		
	}
	int N;
	int maxWeight;
	int[] weight;
	int[] time;
	boolean[] legal;
	public int numBits(int t)
	{
		int count = 0;
		for(int i = 0; i < 32;i++)
		{
			if((t & (1<<i)) != 0) count++;
		}
		return count;
	}
}
