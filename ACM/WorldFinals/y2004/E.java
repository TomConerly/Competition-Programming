package ACM.WorldFinals.y2004;

import java.util.*;
import java.io.*;

import static java.lang.Math.*;

/* 2004 World Finals
 * Problem E: Intersecting Dates
 * Type: Simulation
 * Difficulty Coding: 4 
 * Algorithmic Difficulty: 2
 * Solution: Working with dates is the hard part.
 */ 

public class E {
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File("E.in"));
		toStr = new HashMap<Long,String>();
		toDate = new HashMap<String,Long>();
		while(y != 2101)
		{
			next();
		}
		for(int CASE = 1; ;CASE++)
		{
			int n = sc.nextInt();
			int p = sc.nextInt();
			if(n == 0 && p == 0) break;
			
			//System.out.println("DONE");
			sc.nextLine();
			ArrayList<Range> k = new ArrayList<Range>();
			for(int i = 0; i < n;i++)
			{
				String a = sc.nextLine();
				String[] parts = a.split(" ");
			//	System.out.println(a+" "+parts.length);
				Range r = new Range(toDate.get(parts[0]),toDate.get(parts[1]));
				k.add(r);
			}
			ArrayList<Range> q = new ArrayList<Range>();
			for(int i = 0; i < p;i++)
			{
				String a = sc.nextLine();
				String[] parts = a.split(" ");
				//System.out.println(parts[0]+" "+parts[1]);
				//System.out.println(toDate.get(parts[0])+" "+toDate.get(parts[1]));
				Range r = new Range(toDate.get(parts[0]),toDate.get(parts[1]));
				q.add(r);
			}
			for(Range r: k)
			{
				ArrayList<Range> ne = new ArrayList<Range>();
				for(Range b: q)
				{
					ne.addAll(diff(b,r));
				}
				q = ne;
			}
			for(int i = 0; i < q.size();i++)
			{
				for(int j = i+1; j < q.size();j++)
				{
					Range t = merge(q.get(i),q.get(j));
					if(t != null)
					{
						q.remove(j);
						q.set(i,t);
					}
				}
			}
			System.out.println("Case "+CASE+":");
			for(Range r:q)
			{
				if(r.s == r.f)
				{
					System.out.println("\t"+toStr.get(r.s));
				}else{
					System.out.println("\t"+toStr.get(r.s)+" to "+toStr.get(r.f));
				}
			}
			if(q.size() == 0)
				System.out.println("\tNo additional quotes are required.");
			System.out.println();
		}
	}
	private static boolean overlap(Range a, Range b)
	{
		return  max(a.s,b.s) < min(a.f,b.f);
	}
	private static ArrayList<Range> diff(Range a, Range b) {
		ArrayList<Range> ans = new ArrayList<Range>();
		if(overlap(a,b))
		{
			if(a.s < b.s)
			{
				ans.add(new Range(a.s,b.s-1));
			}
			if(a.f > b.f)
			{
				ans.add(new Range(b.f+1,a.f));
			}
		}
		return ans;
	}
	private static Range merge(Range a, Range b)
	{
		if(overlap(a,b))
		{
			return new Range(min(a.s,b.s),max(a.f,b.f));
		}
		if(a.f +1 == b.s || a.s == b.f+1)
		{
			return new Range(min(a.s,b.s),max(a.f,b.f));
		}
		
		return null;
	}
	static HashMap<Long,String> toStr;
	static HashMap<String,Long> toDate;
	static long at = 0;
	static int y=1700,m=0,d=0;
	static int[] month = {31,28,31,30,31,30,31,31,30,31,30,31};
	public static String toStr()
	{
		String yr = y+"";
		while(yr.length() < 4) yr = '0'+yr;
		String da = (d+1)+"";
		while(da.length() < 2) da = '0'+da;
		String mo = (m+1)+"";
		while(mo.length() < 2) mo = '0'+mo;
		return yr+mo+da;
		
	}
	public static int msize()
	{	
		if(m != 1) return month[m];
		if((y%4 == 0 && y%100 != 0) || (y%400 == 0)) return 29;
		return 28;
	}
	static void next()
	{
		toStr.put(at,(m+1)+"/"+(d+1)+"/"+(y));
		toDate.put(toStr(),at);
		at++;
		d++;
		if(d == msize())
		{
		
			d=0;
			m++;
			if(m == 12)
			{
				m =0;
				y++;
			}
		}
	}
	private static class Range
	{
		long s,f;
		public Range(long a,long b)
		{
			s = a;
			f = b;
		}
	}
}
