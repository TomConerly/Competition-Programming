package ACM.SouthCentralUSA.y2006;
import java.util.*;
import java.io.*;

/* ACM ICPC 2006 South Central USA Regional 
 * Problem H: Panic Room
 * Type: Max Flow
 */

public class H {
	public static void main(String[] args) throws Exception{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		int z = Integer.parseInt(r.readLine());
		for(int y = 0; y < z;y++){
			StringTokenizer st = new StringTokenizer(r.readLine());
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			int[][] adj = new int[m+1][m+1];
			for(int i = 0; i < m;i++){
				String s = r.readLine();
				st = new StringTokenizer(s);
				String f = st.nextToken();
				
				if(f.charAt(0)=='I'){
					adj[m][i]=Integer.MAX_VALUE;
				}
				
				int num = Integer.parseInt(st.nextToken());
				
				for(int j = 0;j <num;j++){
					int room = Integer.parseInt(st.nextToken());
					adj[i][room] = Integer.MAX_VALUE;
					if(adj[room][i] != Integer.MAX_VALUE)adj[room][i] += 1;
				}
			}
			int ans = maxf(m,n,adj);
			if(ans == -1) System.out.println("PANIC ROOM BREACH");
			else System.out.println(ans);
		}		
		
	}
	public static int maxf(int start, int end, int[][] adj){
		int[][] flow = new int[adj.length][adj[0].length];
		int[] p = bfs(start,end,flow,adj);
		int maxFlow = 0;
		while(p!=null){
			int f = Integer.MAX_VALUE;
			for(int i = end;i!=start;i=p[i]) f = Math.min(f,adj[p[i]][i]-flow[p[i]][i]);
			if(f == Integer.MAX_VALUE)return -1;				
			maxFlow +=f;
			for(int i = end;i!=start;i=p[i]){
				flow[p[i]][i] +=f;
				flow[i][p[i]] -=f;
			}
			p = bfs(start,end,flow,adj);
		}
		return maxFlow;
	}
	public static int[] bfs(int start, int end, int[][] flow, int[][] adj){
		LinkedList<Integer> qu = new LinkedList<Integer>();
		int[] p = new int[flow.length];		
		for(int i = 0; i < p.length;i++)p[i]=-1;
		qu.offer(start);
		
		while(qu.size()>0){
			int q = qu.pop();
			for(int i = 0; i < p.length;i++){
				if(p[i]==-1 && adj[q][i]-flow[q][i] > 0){
					if(i == end){
						p[i] = q;
						return p;
					}
					p[i] = q;
					qu.offer(i);
				}
			}
		}
		return null;
		
	}
}

