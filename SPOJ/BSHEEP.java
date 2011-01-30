package SPOJ;
import java.io.*;
import java.util.*;
import static java.lang.Math.*;

/* SPOJ Problem 26: BSHEEP
 * Type: Convex Hull
 * Solution: Use a O(n log n) method and convert to C to get it to work.
 * This thing times out, but equivalent C code passes.
 */

public class BSHEEP {
	public static void p(Object o){System.out.println(o);}
	public static void main(String[] args) throws Exception, IOException
	{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		Integer[][] points = new Integer[100000][3];
		l1 = new Integer[100000][3];
		l2 = new Integer[100000][3];
		int zz = Integer.parseInt(r.readLine());
		
		for(int z = 0; z < zz;z++)
		{
			r.readLine();
			N = Integer.parseInt(r.readLine());			
			for(int i = 0; i < N;i++)		
			{
				StringTokenizer st = new StringTokenizer(r.readLine());
				points[i][0] = Integer.parseInt(st.nextToken());
				points[i][1] = Integer.parseInt(st.nextToken());
				points[i][2] = (i+1);
			}
			convexHull(points);
			double ans = 0;
			for(int i = 0; i < len1-1;i++)
			{
				int j = i+1;
				ans += sqrt(pow(l1[i][0]-l1[j][0],2)+pow(l1[i][1]-l1[j][1],2));
			}
			for(int i = 0; i < len2-1;i++)
			{
				int j = i+1;
				ans += sqrt(pow(l2[i][0]-l2[j][0],2)+pow(l2[i][1]-l2[j][1],2));
			}
			System.out.format("%.2f\n",ans);
			StringBuilder output = new StringBuilder();
			output.append(l1[0][2]);
			for(int i = 1; i < len1;i++ )
			{
				output.append(" "+l1[i][2]);				
			}
			for(int j = 1; j < len2-1;j++)
				output.append(" "+l2[j][2]);
			System.out.println(output+"\n");
			
		}
	}
	static int N;
	static int len1,len2;
	static Integer[][] l1,l2;
	static long sort = 0;
	public static void convexHull(Integer[][] points)
	{		
		len1=0;
		len2=0;
		long t1 = System.currentTimeMillis();
		Arrays.sort(points,0,N,new comp());
		long t2 = System.currentTimeMillis();
		sort += t2-t1;
		for(int i = 0; i <N-1;i++)
		{
			if(points[i][0] == points[i+1][0] && points[i][1] == points[i+1][1])
				points[i+1][2] = points[i][2];
		}
		for(int i = 0; i < N;i++)
		{
			l1[len1] = points[i];
			len1++;
			while(len1 >= 3 && cross(l1[len1-3][0],l1[len1-3][1],l1[len1-2][0],l1[len1-2][1],l1[len1-1][0],l1[len1-1][1])<=0)
			{
				l1[len1-2] = l1[len1-1];
				len1--;
			}
		}
		for(int i = N-1;i>=0;i--)
		{
			l2[len2] = points[i];
			len2++;
			while(len2 >= 3 && cross(l2[len2-3][0],l2[len2-3][1],l2[len2-2][0],l2[len2-2][1],l2[len2-1][0],l2[len2-1][1])<=0)
			{
				l2[len2-2] = l2[len2-1];
				len2--;
			}
		}
	}
	public static int cross(int x1, int y1, int x2, int y2,int x3, int y3)
	{		 
		 return (x2-x1)*(y3-y1) - (y2-y1)*(x3- x1);
	}
	public static class comp implements Comparator<Integer[]>
	{
		public int compare(Integer[] a, Integer[] b) {
			if(a[1] < b[1]) return -1;
			if(a[1] > b[1]) return 1;
			if(a[0] < b[0]) return -1;
			if(a[0] > b[0]) return 1;
			return 0;
		}		
	}
}
