package ACM.NWERC.y2001;
import java.util.*;
import static java.lang.Math.*;

/* ACM NWERC 2001
 * Problem C: Cog-Wheels
 * Type: DP
 * Solution: We will build up solutions and show there is a small # of states.
 * 
 * dp[top subset][bottom subset][cruft][number of moves]
 * 
 * Keep track of the subset of the top and bottom's prime factors you have satisifed.
 * You also can have some "cruft" or matched prime factors, but it can only come from one gear.
 * When you attempt to add more to the top or bottom you must keep the invariant that the cruft is only
 * from one gear (you can still generate all solutions).
 * 
 * Rather than explicitly keeping track of the subsets just keep a sparse set and start with A and 
 * divide out the prime factors when you match them.
 * 
 * Keeping the subset of which prime factors has been satisifed should be small (<1000), but it is hard to compute
 * The table is of size O(|subset|^2 *100 *10) (I assume you will never have more than 10 moves from the top than the bottom
 * and vice versa).
 */

public class C
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int zz = 1; zz<=T;zz++)
		{
			N = sc.nextInt();
			num = new int[N];
			int m = Integer.MAX_VALUE;
			for(int i = 0 ;i < N;i++)
			{
				num[i] = sc.nextInt();
				m = min(m,num[i]);
			}
			System.out.println("Scenario #"+zz+":");
			int TT = sc.nextInt();
			for(int k = 0; k < TT;k++)
			{
				A = sc.nextInt();
				B = sc.nextInt();
			
				int g = gcd(A,B);
				ans = false;
				hs = new HashSet<State>();
				recur(new State(A/g,B/g,true,1,0,0));
				recur(new State(A/g,B/g,false,1,0,0));

				System.out.print("Gear ratio "+A+":"+B);
				if(ans)
					System.out.println(" can be realized.");
				else
					System.out.println(" cannot be realized.");
			}
			System.out.println();	
		}
	}
	public static void recur(State s)
	{
		if(ans || abs(s.top-s.bot) > 15)
			return;
		if(s.a == 1 && s.b == 1 && s.c == 1 && s.top == s.bot)
		{
			ans = true;
			return;
		}
		if(hs.contains(s))
			return;
		hs.add(s);

		int ttop = s.top;
		int tbot = s.bot;
		if(s.side)
			ttop++;
		else
			tbot++;

		for(int i = 0; i < N;i++)
		{
			int ta = s.a;
			int tb = s.b;
			int tc = s.c;
			int t = num[i];

			int g = gcd(t,tc);
			t/=g;
			tc/=g;

			if(s.side)
			{
				g = gcd(ta,t);
				ta/=g;
				t/=g;
			}else{
				g = gcd(tb,t);
				tb/=g;
				t/=g;
			}
			if(tc != 1 && t != 1)
				continue;
			if(t != 1)
				recur(new State(ta,tb,!s.side,t,ttop,tbot));
			if(tc != 1)
				recur(new State(ta,tb,s.side,tc,ttop,tbot));
			if(t==1 && tc == 1)
			{
				recur(new State(ta,tb,true,1,ttop,tbot));
				recur(new State(ta,tb,false,1,ttop,tbot));
			}
		}
	}
	static HashSet<State> hs;
	private static class State
	{
		int a,b,c;
		int top,bot;
		boolean side;
		State(int x, int y, boolean d, int w, int ttop, int tbot)
		{
			top = ttop;
			bot = tbot;
			a = x;
			b = y;
			c = w;
			side = d;

		}
		public int hashCode()
		{
			return a ^ (b<<10)^(c<<20);
		}
		public boolean equals(Object o)
		{
			State s = (State)o;
			return s.a == a && s.b == b && s.c == c && side == s.side && (top-bot) == (s.top-s.bot);
		}
	}
	static int gcd(int a, int b)
	{
		while(true)
		{
			if(a == 0)
				return b;
			b%=a;
			if(b==0)
				return a;
			a%=b;
		}
	}
	static boolean ans;
	static int A,B;
	static int N;
	static int[] num;
}
