package SSU;
import java.util.*;
import java.io.*;

/* Saratov State University Online Judge
 * Problem 203: Hyperhuffman
 * Type: Simulation
 * Solution: Notice that in the huffman construction whenever you merge two things you pay cost equal to their sum. Then just simulate it
 * and watch for constant factor.
 */

public class p203 {
	public static void main(String[] args) throws IOException{
		BufferedReader R = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.valueOf(R.readLine().trim());
		StringTokenizer st = new StringTokenizer(R.readLine().trim());
		TreeSet<Long> ts = new TreeSet<Long>();
		
		long[] L = new long[N];
		for(int i = 0; i < N;i++)
			L[i] = Long.valueOf(st.nextToken());
		
		int at = N;
		int idx = 0;
		long ans = 0;
		while(at < 2*N-1){
			long a = (ts.size() == 0 ||(idx < N && L[idx] < (ts.first()>>>20)))?L[idx++]:(ts.pollFirst()>>>20);
			long b = (ts.size() == 0 ||(idx < N && L[idx] < (ts.first()>>>20)))?L[idx++]:(ts.pollFirst()>>>20);
			ans += a+b;
			ts.add(((a+b)<<20)|at);
			at++;
		}
		System.out.println(ans);
	}
}
