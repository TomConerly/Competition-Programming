package SSU;
import java.util.*;

/* Saratov State University Online Judge
 * Problem 164: Airlines
 * Type: Graph Theory/Greedy
 * Solution: If the number of airlines is large than you can get all of the edges out of one vertex easily. So the number of airlines is at most ~2n.
 * Now try making solutions by taking two vertices, connecting them then connecting every other vertex to one of the two. Because the number of airlines
 * is low there must be "significant" overlap between the two vertices so this works. I have no proof though.
 */


public class p164 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = (M+1)/2;
		A = new int[N][N];
		for(int i = 0; i < N;i++)
			for(int j = 0; j < N;j++)
				A[i][j] = sc.nextInt()-1;

		ArrayList<Integer> ans = null;
		for(int i = 0; i < N;i++){
			boolean[] U = new boolean[M];
			int used = 0;
			for(int j = 0; j < N;j++)
				if(A[i][j] != -1)
					if(U[A[i][j]] == false){
						used++;
						U[A[i][j]] = true;
					}
			if(used <= K){
				ans = new ArrayList<Integer>();
				for(int j = 0; j < U.length;j++)
					if(U[j])
						ans.add(j);
				break;
			}				
		}
		if(ans == null){
			done:
			for(int i = 0; i < N;i++){
				for(int j = i+1; j < N;j++){
					B = new ArrayList[M];
					int[][] in = new int[N][2];
					for(int k = 0; k < M;k++)
						B[k] = new ArrayList<Integer>();
					for(int k = 0; k < N;k++){
						in[k][0] = A[i][k];
						if(A[i][k] != -1)
							B[A[i][k]].add(k);
					}
					for(int k = 0; k < N;k++){
						in[k][1] = A[j][k];
						if(in[k][0] == in[k][1])
							in[k][1] = -2;
						else if(A[j][k] != -1)
							B[A[j][k]].add(k);
					}

					int cover = 0;
					int used = 0;
					size = new int[M];
					boolean[] C = new boolean[N];
					for(int k = 0; k < M;k++)
						size[k] = B[k].size();
					TreeSet<Integer> ts = new TreeSet<Integer>(new Comparator<Integer>(){
						@Override
						public int compare(Integer a, Integer b) {
							int cmp =  -(size[a] - size[b]);
							if(cmp != 0)
								return cmp;
							else 
								return a-b;
						}});
					for(int k = 0; k < N;k++)
						if(k != i && k != j)
							ts.add(k);

					ans = new ArrayList<Integer>();
					while(cover < N && used <= K && ts.size() > 0){
						int k;
						if(used == 0){
							k = A[i][j];
							ts.remove(k);
						}else 
							k = ts.pollFirst();
						cover += size[k];
						for(int a:B[k]){
							if(C[a] == false){
								C[a] = true;
								if(in[a][0] != k && in[a][0] >= 0){
									ts.remove(in[a][0]);
									size[in[a][0]]--;
									ts.add(in[a][0]);
								}
								if(in[a][1] != k && in[a][1] >= 0){
									ts.remove(in[a][1]);
									size[in[a][1]]--;
									ts.add(in[a][1]);
								}
							}
						}
						used++;
						ans.add(k);
					}
					if(cover == N && used <= K)
						break done;
					
				}
			}

		}
		System.out.println(ans.size());
		for(int a:ans)
			System.out.print((a+1)+" ");

	}
	static int[] size;
	static ArrayList<Integer>[] B;
	static int N,M,K;
	static int[][] A;
}
