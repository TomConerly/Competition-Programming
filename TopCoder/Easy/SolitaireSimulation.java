package TopCoder.Easy;
import java.util.*;

/* TopCoder SRM 420
 * Easy Problem 250 Points: RedIsGood
 * Type: Simulation
 */
public class SolitaireSimulation {
	
	public int periodLength(int[] heaps) {
		HashMap<State,Integer> m = new HashMap<State,Integer>();
		State s = new State(heaps);
		m.put(s,0);
		int at = 1;
		State t = s.next();
		while(!m.containsKey(t))
		{
			m.put(t,at);
			at++;
			t = t.next();
		}
		return at - m.get(t);
	}
	private class State
	{
		int[] heaps;
		public State(int[] h)
		{
			if(h.length != 50)
			{
				heaps = new int[50];
				for(int i = 0; i < h.length;i++)
				{
					heaps[i]=h[i];
				}
			}else
				heaps = h;
			Arrays.sort(heaps);
		}
		public State next()
		{
			int[] next = new int[50];
			int count = 0;
			int zero = -1;
			for(int i = 0; i < heaps.length;i++)
			{
				if(heaps[i] > 0)
				{
					count++;
					next[i]= (short)(heaps[i]-1);
				}
				if(next[i]==0)
				{
					zero = i;
				}
			}
			next[zero]=(short)count;
			Arrays.sort(next);
			return new State(next);
		}
		public boolean equals(Object o)
		{
			State t = (State)o;
			for(int i = 0; i < 50;i++)
			{
				if(t.heaps[i] != heaps[i]) return false;
			}
			return true;
		}
		public int hashCode()
		{
			int hash = 1;
			for(int s: heaps)
			{
				hash *= (s+1);
			}
			return hash;
		}

	}
}
