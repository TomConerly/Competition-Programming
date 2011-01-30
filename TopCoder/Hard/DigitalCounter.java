package TopCoder.Hard;
import static java.lang.Math.*;

/* TopCoder TCO 2009 Qual 2
 * Hard Problem 1000 Points: DigitalCounter
 * Type: Brute Forceish
 * Solution: Try all sets of n_1 digits with 2 lit, n_2 digits with 3 lit, .... etc
 * skip the ones with the wrong sum. There are only ~1000 with the right sum. For each
 * of these try making the smallest number, or try matching the first i digits of the given
 * string then i+1 digit larger, and i+2 on the smallest you can make. This generates all cases.
 */

public class DigitalCounter {

	int[] lit = {6,2, 5, 5, 4, 5, 6, 3, 7, 5};
	public long nextEvent(String current) {
		cur = current;
		N = current.length();
		S = 0;
		for(int i = 0; i < N;i++)
		{
			S += lit[current.charAt(i)-'0'];
		}
		L = Long.parseLong(current);
		best = Long.MAX_VALUE;
		TOP = (long)Math.pow(10, N);
		//System.out.println(N+" "+S);
		recur(0,0,new int[6],0);
		return best;
	}
	String cur;
	long N,S,L,TOP;
	public void recur(int at,int max,int[] num,int score)
	{
		if(at == N)
		{
			if(S == score) test(num);
			return;
		}		

		for(int i = max+1; i<= 5;i++)
		{
			num[i]++;
			recur(at+1,i,num,score + i+2);
			num[i]--;
		}
		num[max]++;
		recur(at+1,max,num,score + max+2);
		num[max]--;
	}
	long best;
	private void test(int[] num) {
		
		long a = 0;
		boolean[] used = new boolean[10];
		for(int j = 0; j < 10;j++)
		{
			if(used[lit[j]-2]) continue;
			used[lit[j]-2] = true;
			for(int i = 0; i < num[lit[j]-2];i++)
			{				
				a = a*10+(j);
			}
		}

		//System.out.println("here2:"+a);
		best = min(best, TOP-L+a);

		next:
		for(int i = 0; i <= N;i++)
		{
			a = 0;
			int[] temp = new int[6];
			for(int j = 0; j < 6;j++)
				temp[j] = num[j];
			
			//if(print && i == 3) System.out.println("HERE222:");
			for(int j = 0; j < i;j++)
			{
			//	if(print && i == 3) System.out.println("BLAH:"+temp[lit[cur.charAt(j)-'0']-2]);
				if(temp[lit[cur.charAt(j)-'0']-2] > 0)
				{
					temp[lit[cur.charAt(j)-'0']-2]--;
					a = a*10 + (cur.charAt(j)-'0');
				}
				else{
					continue next;
				}
			}
			//if(print)System.out.println(i+" "+a+" "+cur+" "+Arrays.toString(temp)+" "+Arrays.toString(num));
			if(i == N)
			{
				best = min(best,TOP);
				continue;
			}
			
			boolean bad = true;
			for(int j = cur.charAt(i)-'0'+1;j < 10;j++)
			{
				if(temp[lit[j]-2] > 0)
				{
					temp[lit[j]-2]--;
					a = a*10 + j;
					bad = false;
					break;
				}
			}
			if(bad) continue;
			for(int j = 0; j < 10;j++)
			{
				while(temp[lit[j]-2] > 0){
					a = a*10 + j;
					temp[lit[j]-2]--;
				}
			}
			if(a < L) {
				System.out.println("ERROR:"+i+" "+a);
			}
			if(a != L)
				best = min(best,a-L);		
			else
				best = min(best,TOP);
		}

	}
}
