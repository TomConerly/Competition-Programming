package TopCoder.Medium;
import java.util.*;
/* TopCoder TCHS09 Championship
 * Medium Problem 500 Points: TheLongWay
 * Type: Dijkstra
 * 
 */

public class TheLongWay {

	String[] city;
	public int minimalTime(String[] c) {
		city = c;
		PriorityQueue<State> pq = new PriorityQueue<State>(100,new temp());
		State s = new State();
		boolean found = false;
		int c1x=0,c1y=0,c2x=0,c2y=0;
		for(int i = 0; i < city.length;i++)
		{
			for(int j = 0; j < city[i].length();j++)
			{
				if(city[i].charAt(j) == 'S')
				{
					s.x = i;
					s.y = j;
				}
				if(city[i].charAt(j) == 'C')
				{
					if(found)
					{
						c2x = i;
						c2y = j;
					}else{
						found = true;
						c1x = i;
						c1y = j;
					}
				}
			}
		}
		s.f = false;
		s.s = false;
		s.ld = -1;
		s.dist = 0;
		pq.add(s);
		TreeSet<State> visited = new TreeSet<State>();
		while(pq.size() > 0)
		{
			State st = pq.poll();
			if(visited.contains(st)) continue;
			visited.add(st);
		//	System.out.println(st.dist+" "+st.x+" "+st.y+" "+st.s+" "+st.f);
			if(st.s && st.f) return st.dist;			

			for(int dir = 0; dir < 4;dir++)
			{
				//System.out.println(blocked(st.x+x[dir],st.y+y[dir]));
				if(dir == st.ld)continue;
				if(blocked(st.x+x[dir],st.y+y[dir])) continue;

				State next = new State();
				next.x = st.x+x[dir];
				next.y = st.y+y[dir];
				next.s = st.s;
				next.f = st.f;
				if(next.x == c1x && next.y == c1y) next.s = true;
				if(next.x == c2x && next.y == c2y) next.f = true;
				next.ld = dir;
				next.dist = st.dist+1;
				pq.add(next);
			}
		}
		return -1;
	}
	int[] x = {1,-1,0,0};
	int[] y = {0,0,1,-1};
	private boolean blocked(int x, int y)
	{
		if(x < 0 || x >= city.length) return true;
		if(y < 0 || y >= city[0].length()) return true;

		return city[x].charAt(y) == '#';
	}
	private class temp implements Comparator<State>
	{

		public int compare(State a, State b) {
			if(a.dist < b.dist) return -1;
			if(a.dist > b.dist) return 1;
			return a.compareTo(b);
		}

	}
	private class State implements Comparable<State>{
		int x, y;
		int ld;
		int dist;
		boolean f,s;
		public int compareTo(State a) {
			if(x < a.x) return 1;
			if(x > a.x) return -1;
			if(y > a.y) return 1;
			if(y < a.y) return -1;
			if(f && !a.f) return 1;
			if(!f && a.f) return -1;
			if(s && !a.s) return 1;
			if(!s && a.s) return -1;
			if(ld < a.ld) return 1;
			if(ld > a.ld) return -1;
			return 0;
		}
	}
}
