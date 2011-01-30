package TopCoder.Medium;
import static java.lang.Math.*;
/* TopCoder SRM 389
 * Medium Problem 500 Points: GuitarChords
 * Type: Brute Force
 * Solution: We can brute force over all assignments of strings to notes
 * then just score each one and pick the best.
 */
public class GuitarChords {

	String[] names = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};
	int best;
	String[] s,c;
	int[] si,ci;
	public int stretch(String[] strings, String[] chord) {
		s = strings;
		c = chord;
		si = new int[s.length];
		ci = new int[c.length];
		for(int i = 0; i < s.length;i++)		
			for(int j = 0; j < names.length;j++)			
				if(s[i].equals(names[j])) si[i] = j;

		for(int i = 0; i < c.length;i++)		
			for(int j = 0; j < names.length;j++)			
				if(c[i].equals(names[j])) ci[i] = j;


		best = Integer.MAX_VALUE;
		recur(0,new int[s.length]);
		return best;
	}

	private void recur(int at, int[] list) {
		if(at == s.length) run(list);
		else{
			for(int i = 0;i<c.length;i++)
			{
				list[at] = i;
				recur(at+1,list);
			}
		}

	}

	private void run(int[] list) {
		boolean[] have = new boolean[c.length];
		for(int i:list)
			have[i] = true;
		for(int i = 0; i < c.length;i++)
		{
			if(!have[i]) return;
		}
		boolean[] h = new boolean[12];
		for(int i = 0; i < s.length;i++)
		{
			
			int m = (ci[list[i]]-si[i]+12)%12;
		//	System.out.println(ci[list[i]]+" "+si[i]+" "+m);
			h[m] = true;
		}
		int last = -1;
		int first = -1;
		int large = 0;
		for(int i = 1;i<12;i++)
		{
			if(h[i])
			{
				if(last == -1) first = i;
				else large = max(large,i-last);
				last = i;
			}
		}
	//	print(list);
	//	print(h);
		//System.out.println(first+" "+last+" "+large);
		if(first != -1 && last != -1)
		{
			large = max(large,first+12-last);
		//	System.out.println("\t"+(13-large));
			best = min(best, 13-large);
		}
		else best = 0;
		
	}
	void print(int[] a)
	{
		for(int b:a)
			System.out.print(b+" ");
		System.out.println();
	}
	void print(boolean[] a)
	{
		for(boolean b:a)
			System.out.print(b+" ");
		System.out.println();
	}

}
