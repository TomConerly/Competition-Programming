package TopCoder.Hard;
import java.util.*;

/* TopCoder SRM 422
 * Hard Problem 1000 Points: WorkersOnPlane
 * Type: Max Flow
 * Solution: Setup a double bipartide( tripartide?) graph where it goes from
 * source to Gold to works to Silver to sink. There is an edge between source
 * and all gold locations, all silver and sink. THen an edge between gold and a worker
 * if they are within distance and between the workers and a silver if they are within distance
 * then max flow! A dfs beats a bfs in this graph considerably so use that.
 */

public class WorkersOnPlane {
	public int howMany(String[] field, int K) {
		f = field;
		int[][] cost = new int[field.length * field[0].length()][field.length
				* field[0].length()];
		
		int len = f[0].length();
		for (int i = 0; i < f.length * f[0].length(); i++) {
			Arrays.fill(cost[i], 1000000);
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
			if (borders(i, i + 1) && inBounds(i + 1))
				pq.add((1 << 16) | (i + 1));
			if (borders(i, i - 1) && inBounds(i - 1))
				pq.add((1 << 16) | (i - 1));
			if (borders(i, i + len) && inBounds(i + len))
				pq.add((1 << 16) | (i + len));
			if (borders(i, i - len) && inBounds(i - len))
				pq.add((1 << 16) | (i - len));

			while (pq.size() > 0) {
				int poll = pq.poll();
				int c = poll >>> 16;
				int at = poll & 0xFFFF;

				// System.out.println(i+" "+at);

				if (cost[i][at] < 1000000)
					continue;
				cost[i][at] = c;

				if (inBounds(at + 1) && borders(at, at + 1) && empty(at)
						&& cost[i][at + 1] > c + 1)
					pq.add(((c + 1) << 16) | (at + 1));
				if (inBounds(at - 1) && borders(at, at - 1) && empty(at)
						&& cost[i][at - 1] > c + 1)
					pq.add(((c + 1) << 16) | (at - 1));
				if (inBounds(at + len) && borders(at, at + len) && empty(at)
						&& cost[i][at + len] > c + 1)
					pq.add(((c + 1) << 16) | (at + len));
				if (inBounds(at - len) && borders(at, at - len) && empty(at)
						&& cost[i][at - len] > c + 1)
					pq.add(((c + 1) << 16) | (at - len));
			}
		}

		ArrayList<Integer> g = new ArrayList<Integer>();
		ArrayList<Integer> w = new ArrayList<Integer>();
		ArrayList<Integer> s = new ArrayList<Integer>();
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[0].length(); j++) {
				if (field[i].charAt(j) == 'G')
					g.add(i * f[0].length() + j);
				if (field[i].charAt(j) == 'W')
					w.add(i * f[0].length() + j);
				if (field[i].charAt(j) == 'S')
					s.add(i * f[0].length() + j);
			}
		}
		int[][] flow = new int[2 * (g.size() + w.size() + s.size()) + 2][2 * (g
				.size()
				+ w.size() + s.size()) + 2];
		int source = flow.length - 2;
		int sink = flow.length - 1;
		for (int i = 0; i < g.size(); i++) {
			flow[source][i] = 1;
			flow[i][i + g.size()] = 1;
			for (int j = 0; j < w.size(); j++) {
				if (cost[g.get(i)][w.get(j)] <= K
						|| borders(g.get(i), w.get(j))) {
					flow[i + g.size()][2 * g.size() + j] = 1;
				}
			}
		}

		int offset = g.size() * 2;
		for (int j = 0; j < w.size(); j++) {
			flow[offset + j][offset + j + w.size()] = 1;
			for (int k = 0; k < s.size(); k++) {
				if (cost[w.get(j)][s.get(k)] <= K
						|| borders(w.get(j), s.get(k))) {
					flow[offset + j + w.size()][offset + 2 * w.size() + k] = 1;
				}
			}
		}
		offset += 2 * w.size();
		for (int k = 0; k < s.size(); k++) {
			flow[offset + k][offset + k + s.size()] = 1;
			flow[offset + k + s.size()][sink] = 1;
		}

		long start = System.currentTimeMillis();
		int ans = maxf(source, sink, flow);

		System.out.println(System.currentTimeMillis() - start);
		return ans;
	}

	String[] f;

	public boolean inBounds(int i) {
		if (i < 0 || i >= f.length * f[0].length())
			return false;
		return true;
	}

	public boolean empty(int i) {
		if (!inBounds(i))
			return false;
		return f[i / f[0].length()].charAt(i % f[0].length()) == '.';
	}

	public boolean borders(int a, int b) {
		if (b + f[0].length() == a || b - f[0].length() == a)
			return true;
		if (b - 1 == a && b % f[0].length() != 0)
			return true;
		if (b + 1 == a && b % f[0].length() != f[0].length() - 1)
			return true;
		return false;
	}

	int start;
	int end;
	int[][] adj;

	public int maxf(int start, int end, int[][] adj) {
		this.start = start;
		this.end = end;
		this.adj = adj;
		int maxflow = 0;
		while (true) {
			int[] p = bfs();
			if (p == null)
				break;
			maxflow += update(Integer.MAX_VALUE, end, p);
		}

		return maxflow;
	}

	public int update(int f, int at, int[] p) {
		if (at == start)
			return f;
		f = Math.min(f, adj[p[at]][at]);
		f = update(f, p[at], p);
		adj[p[at]][at] -= f;
		adj[at][p[at]] += f;
		return f;
	}

	public int[] bfs() {
		LinkedList<Integer> paths = new LinkedList<Integer>();
		int[] p = new int[adj.length];
		Arrays.fill(p, -1);
		paths.add(start);

		while (paths.size() > 0) {
			int at = paths.removeFirst();
			for (int i = 0; i < p.length; i++) {
				if (p[i] == -1 && adj[at][i] > 0) {
					p[i] = at;
					if (i == end)
						return p;
					paths.addFirst(i);
				}
			}
		}
		return null;
	}
}
