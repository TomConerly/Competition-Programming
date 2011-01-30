package USACO.Chapter3;
/*
ID: theycal2
LANG: JAVA
TASK: contact
 */
/* USACO Training
 * Contact
 * Type: Counting
 * Solution: Walk through the list and only keep track of the last 32 bits
 * (you could keep track of fewer) then just update one count for an item
 * of each length. The output spec is also difficult.
 */
import java.io.*;
import java.util.*;

public class contact 
{
	public static void main(String[] args) throws IOException 
	{
		long start = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("contact.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("contact.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		int[] bits = new int[200000];
		String s = f.readLine();
		int at = 0;
		int i;
		for(i = 0; i < bits.length;i++)
		{
			if(s.length()==at) {
				if(!f.ready())break;
				s = f.readLine();
				at = 0;
			}
			bits[i] = s.charAt(at)-'0';
			at++;
		}
		int[][] count = new int[13][1<<12];
		int num = i;
		int cur = 0;
		for(int j = 0; j < num;j++)
		{
			cur = (cur << 1)|bits[j];
			for(int k = A;k<=B;k++)
			{
				if(j+1 < k) continue;
				count[k][cur & ((1<<k)-1)]++;
			}
		}
		ArrayList<Long> counts = new ArrayList<Long>();		
		for(int j = 0; j < 13;j++)
		{
			for(int k = 0; k < count[0].length;k++)
			{
				if(count[j][k] > 0)
				{
					counts.add((((long)count[j][k]) << 32)| ((1000-j)<<20)|((1<<20)-k-1));
				}
			}
		}
		Collections.sort(counts);
		int total = 0;
		int prevCount = -1;
		boolean first = true;
		boolean leadingSpace = false;
		int onLine = 0;
		boolean extraLine = true;
		for(int j = counts.size()-1;j>= 0;j--)
		{
			int c = (int)(counts.get(j)>>>32);
			int l = 1000-(int)((counts.get(j)>>>20) & 0xFFF);
			int b = (1<<20)-1-(int)(counts.get(j) & 0xFFFFF);
			String str = Integer.toBinaryString(b);
			while(str.length() < l)
			{
				str = "0"+str;
			}
			
			if(prevCount == c)
			{
				if(leadingSpace) out.print(" ");
				leadingSpace = true;
				first = false;
				out.print(str);
				onLine++;
				if(onLine == 6)
				{
					out.println();
					first = true;
					leadingSpace = false;
					onLine = 0;
				}
			}
			else
			{
				prevCount = c;
				if(!first) out.println();
				first = false;			
				
				if(total == N){
					extraLine = false;
					break;
				}
				total++;
				out.println(c);
				out.print(str);
				onLine = 1;		
				leadingSpace = true;
			}			
		}
		if(extraLine) out.println();

		out.close();
		System.out.println("$:"+(System.currentTimeMillis()-start));
		System.exit(0);
	}
}

