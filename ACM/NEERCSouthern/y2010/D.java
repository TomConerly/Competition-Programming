import java.util.*;

import static java.lang.Math.*;
import java.io.*;

public class D {
	public static void main(String[] args) throws Exception{
		BufferedReader R = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.valueOf(R.readLine());
		X = new int[N];
		Y = new int[N];
		for(int i = 0; i < N;i++){
			StringTokenizer st = new StringTokenizer(R.readLine());
			int x = Integer.valueOf(st.nextToken());
			int y = Integer.valueOf(st.nextToken());
			X[i] = x;
			Y[i] = y;
		}
		int[] d1 = getdist();
		int best = 0;
		for(int d:d1)
			best = max(best, d);
		for(int i = 0; i < N;i++){
			X[i] *= -1;
			Y[i] *= -1;
		}
		int[] d2 = getdist();
		int[] numdist = new int[N+1];
		for(int i = 0; i < N;i++){
			if(d1[i] + d2[i] == best)
				numdist[d1[i]]++;
		}
		ArrayList<Integer> A = new ArrayList<Integer>();
		ArrayList<Integer> B = new ArrayList<Integer>();
		for(int i = 0; i < N;i++){
			if(d1[i] + d2[i] == best){
				A.add(i+1);
				if(numdist[d1[i]] == 1)
					B.add(i+1);
			}
		}
		print(A);
		print(B);
	}
	private static void p(Object...o) {
		System.out.println(Arrays.deepToString(o));
	}
	private static void print(ArrayList<Integer> ans) {
		System.out.print(ans.size());
		for(int a:ans)
			System.out.print(" "+a);
		System.out.println();
	}
	private static int[] getdist() {
		int[] D = new int[N];
		TreeMap<Integer, Integer> tm = new TreeMap<Integer, Integer>();
		Entry[] E = new Entry[N];
		for(int i = 0; i < N;i++)
			E[i] = new Entry(X[i], Y[i], i);
		Arrays.sort(E);
		for(Entry e: E){
			Map.Entry<Integer,Integer> en = tm.lowerEntry(e.y);
			if(en != null)
				D[e.id] = 1 + en.getValue();
			
			en = tm.floorEntry(e.y);
			if(en != null && en.getValue() >= D[e.id])
				continue;

			while(true){
				en = tm.ceilingEntry(e.y);
				if(en == null || en.getValue() > D[e.id])
					break;
				tm.remove(en.getKey());
			}
			tm.put(e.y, D[e.id]);
		}
		return D;
	}
	static int N;
	static int[] X, Y;
	private static class Entry implements Comparable<Entry>{
		int x,y,id;
		public Entry(int x, int y, int id) {
			this.x = x;
			this.y = y;
			this.id = id;
		}
		public int compareTo(Entry e) {
			if(x > e.x)
				return 1;
			if(x < e.x)
				return -1;
			if(y > e.y)
				return -1;
			if(y < e.y)
				return 1;
			return 0;
		}
	}
}
