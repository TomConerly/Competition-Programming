import java.util.*;
import static java.lang.Math.*;
import java.io.*;

public class K {
	public static void p(Object...o){System.out.println(Arrays.deepToString(o));}
	public static void main(String[] args) throws Exception{
		BufferedReader R = new BufferedReader(new InputStreamReader(System.in));
		M = Long.valueOf(R.readLine());
		String str = R.readLine();
		char[] C = str.toCharArray();
		LinkedList<Integer> S = new LinkedList<Integer>();
		int cost = 0;
		int N = C.length;
		for(int i  = 0; i < N;i++){
			if(C[i] != 'e' && S.size() > 0 && S.getLast() == 1){
				cost += 2;
				checkCost(i, cost+2);
				S.removeLast();
				S.addLast(2);
			}else if(C[i] != 'e' && S.size() > 0 && S.getLast() == 2){
				cost -= 2;
				checkCost(i, cost);
				S.removeLast();
				S.addLast(1);
			}
			if(C[i] == 'l'){
				cost++;
				S.addLast(0);
				checkCost(i, cost+1);
			}else if(C[i] == 'd'){
				cost++;
				S.addLast(1);
				checkCost(i, cost+1);
			}else if(C[i] == 'e'){
				if(S.size() == 0)
					fail(i);
				if(S.getLast() == 2)
					fail(i);
				cost--;
				S.removeLast();
			}else if(C[i] == 'i'){
				checkCost(i, cost+3);
				i++;
				if(i == N || !Character.isDigit(C[i]))
					fail(i);
				if(C[i]-'0' > 0){
					i++;
					while(i < N && Character.isDigit(C[i])){
						checkCost(i, cost+2);		
						i++;
					}
				}else{
					i++;
				}
				if(i == N || C[i] != 'e')
					fail(i);
			}else if(Character.isDigit(C[i])){
				long num = C[i]-'0';
				checkCost(i, cost+2+num);
				i++;
				if(num > 0){
					while(i < N && Character.isDigit(C[i])){
						num = 10*num+(C[i]-'0');
						checkCost(i, cost+num+2);
						i++;
					}
				}
				if(i == N || C[i] != ':')
					fail(i);
				if(i + num >= N)
					fail(N);
				i += num;
			}else{
				fail(i);
			}
			if(S.size() == 0 && i+1 < N)
				fail(i+1);
		}
		if(S.size() > 0)
			fail(N);
		System.out.println("ok");
	}
	static long M;
	private static void fail(int i){
		System.out.format("Error at position %d!\n", i);
		System.exit(0);
	}
	private static void checkCost(int i, long cost) {
		if(i+cost > M)
			fail(i);		
	}
}