package ACM.NEERC.y2007;
import java.io.*;
import java.util.*;

/* ACM NEERC 2007
 * Problem F: fund
 * Type: DP
 * Solution: The DP is standard, but if you don't do something smart you will time out or memory limit.
 * At the very start compute all of the states, and the transitions between all states. That way
 * your innermost loop is only dealing with arrays, no hash map lookups etc.
 */


public class fund {
	@SuppressWarnings("unchecked")
	public static void main (String [] args) throws IOException {
		long ST = System.currentTimeMillis();
		// Use BufferedReader rather than RandomAccessFile; it's much faster
		BufferedReader f = new BufferedReader(new FileReader("fund.in"));
		// input file name goes above
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fund.out")));
		// Use StringTokenizer vs. readLine/split -- lots faster
		StringTokenizer st = new StringTokenizer(f.readLine());
		c = price(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		S = new String[n];
		N = new int[n];
		K = new int[n];
		P = new long[m][n];
		for(int i = 0; i < n;i++)
		{
			st = new StringTokenizer(f.readLine());
			S[i] = st.nextToken();
			N[i] = Integer.parseInt(st.nextToken());
			K[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(f.readLine());
			for(int j = 0; j < m;j++)
			{
				P[j][i] = price(st.nextToken());
			}
		}

		st1 = new HashMap<Long,Integer>();
		trans1 = new ArrayList[12870];
		trans2 = new ArrayList[12870];
		st2 = new long[12870];
		fill(0,0,k);
		fill2(0,0,k);

		DPv = new long[m+1][st1.size()];
		DPm = new int[m+1][st1.size()];
		for(int i = 0; i < DPv.length;i++)
		{
			Arrays.fill(DPv[i],-1);
		}

		System.out.println("time: "+(System.currentTimeMillis()-ST));
		DPv[0][st1.get(0L)] = c;
		for(int i = 0; i < m ;i++)
		{
			for(int j = 0; j < st1.size();j++)
			{
				if(DPv[i][j] < 0)
					continue;
						
				long money = DPv[i][j];
				
				if(DPv[i+1][j] < money)
				{
					DPv[i+1][j] = money;
					DPm[i+1][j] = 0;
				}
				for(int k = 0; k < trans1[j].size();k++)
				{
					int tran = trans1[j].get(k);
					if(tran >= 0)
					{
						int x = tran;
						if(money < P[i][x]*N[x])
							continue;			
						if(DPv[i+1][trans2[j].get(k)] < money-P[i][x]*N[x])
						{
							 DPv[i+1][trans2[j].get(k)] = money-P[i][x]*N[x];
							 DPm[i+1][trans2[j].get(k)] = x+1;
						}
					}else{
						int x = (-tran)-1;	
						if(DPv[i+1][trans2[j].get(k)] < money+P[i][x]*N[x])
						{
							DPv[i+1][trans2[j].get(k)] = money+P[i][x]*N[x];
							DPm[i+1][trans2[j].get(k)] = (n+1)+(x+1);
						}
					}
				}
			}
		}
		System.out.println("time: "+(System.currentTimeMillis()-ST));
		int atm = DPm[m][st1.get(0L)];
		long score = DPv[m][st1.get(0L)];
		ArrayList<String> ans = new ArrayList<String>();
		long have = 0L;
		for(int i = m; i > 0;i--)
		{
			int mo = atm;
			if(mo == 0)
			{
				ans.add(0, "HOLD");
			}else if(mo < n+1)
			{
				ans.add(0,"BUY "+S[mo-1]);
				int a = mo-1;
				have -= (1<<4*a);
			}else{
				ans.add(0,"SELL "+S[(mo-1)-(n+1)]);
				int a = (mo-1)-(n+1);
				have += (1<<4*a);
			}
			atm = DPm[i-1][st1.get(have)];
		}
		long dollar = score/100;
		long cents = score%100;
		String cent = ""+cents;
		while(cent.length() < 2)
			cent = "0"+cent;
		out.println(dollar+"."+cent);
		for(String s:ans)
			out.println(s);

		long EN = System.currentTimeMillis();
		System.out.println("time: "+(EN-ST));

		out.close();                                  // close the output file
		System.exit(0);                               // don't omit this!
	}
	private static void fill(long num, int at,int left) {
		if(at == n)
		{
			st2[st1.size()] = num;
			trans1[st1.size()] = new ArrayList<Integer>();
			long l = num;
			int total = 0;
			for(int x = 0; x < n;x++)
				total += (l>>(4*x)) & 0xf;

			if(total < k)
			{
				for(int x = 0; x < n;x++)
				{
					if(((l>>(4*x)) & 0xf)+1 > K[x])
						continue;
					long nl = l;
					nl += 1<<(4*x);
					trans1[st1.size()].add(x);
				}
			}
			for(int x = 0; x < n;x++)
			{
				if(((l>>(4*x)) & 0xf) <= 0)
					continue;
				long nl = l;
				nl -= 1<<(4*x);
				trans1[st1.size()].add(-(x+1));		
			}	
			st1.put(num, st1.size());
			return;
		}
		for(int i = 0; i <= left;i++)
		{
			fill((num<< 4)| i,at+1,left-i);
		}
	}
	private static void fill2(long num, int at,int left) {
		if(at == n)
		{
			trans2[st1.get(num)] = new ArrayList<Integer>();
			long l = num;
			int total = 0;
			for(int x = 0; x < n;x++)
				total += (l>>(4*x)) & 0xf;

			if(total < k)
			{
				for(int x = 0; x < n;x++)
				{
					if(((l>>(4*x)) & 0xf)+1 > K[x])
						continue;
					long nl = l;
					nl += 1<<(4*x);
					
					trans2[st1.get(num)].add(st1.get(nl));
				}
			}
			for(int x = 0; x < n;x++)
			{
				if(((l>>(4*x)) & 0xf) <= 0)
					continue;
				long nl = l;
				nl -= 1<<(4*x);	
				trans2[st1.get(num)].add(st1.get(nl));
			}	
			return;
		}
		for(int i = 0; i <= left;i++)
		{
			fill2((num<< 4)| i,at+1,left-i);
		}
	}
	private static long price(String s) {
		double d = Double.valueOf(s).doubleValue()+.005;

		return (long)(d*100);
	}
	static long c;
	static int m,n,k;
	static String[] S;
	static int[] N,K;
	static long[][] P;
	static long[][] DPv;
	static int[][] DPm;
	static HashMap<Long,Integer> st1;
	static long[] st2;
	static ArrayList<Integer>[] trans1;
	static ArrayList<Integer>[] trans2;
}