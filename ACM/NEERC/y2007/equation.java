package ACM.NEERC.y2007;
/*
ID: !carnegie
LANG: JAVA
TASK: equation
*/
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/* ACM NEERC 2007
 * Problem E: Equation
 * Type: Math
 * Solution: Generate the tree for the formula. If there is no X just evaluate it. Otherwise given a tree
 * and a value you want to match attempt to match it by recusively calling the function one level down.
 * Watch out for special cases with 0.
 */


public class equation {
	public static void main(String[] args) throws IOException 
	{		
		Scanner sc = new Scanner(new FileReader("equation.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("equation.out")));

		Stack<Node> st = new Stack<Node>();
		String[] p = sc.nextLine().split(" ");
		for(String s:p)
		{
			if(s.equals("X"))
				st.push(new Node(null,null,(char)0,true,null));
			else if(Character.isDigit(s.charAt(0)))
				st.push(new Node(null,null,(char)0,false,new Frac(s.charAt(0)-'0',1)));
			else{
				Node r = st.pop();
				Node l = st.pop();
				st.push(new Node(l,r,s.charAt(0),false,null));
			}
		}
		ans = null;
		multiple = false;
		Node root = st.pop();

		if(root.cx == false)
		{
			Frac f = eval(root);
			if(f.p == 0)
				out.println("MULTIPLE");
			else
				out.println("NONE");
		}else{
			ans(root,new Frac(0,1));
			if(multiple)
				out.println("MULTIPLE");
			else if(ans == null)
				out.println("NONE");
			else
				out.println("X = "+ans.p+"/"+ans.q);
		}
		out.close();
		System.exit(0);
	}

	static Frac ans;
	static boolean multiple;
	public static void ans(Node a, Frac match)
	{
		if(a.x)
		{
			ans = match;
			return;
		}else{
			boolean side = a.l.cx;
			Frac f = side ?eval(a.r) :eval(a.l);
			if(a.op == '+')
			{
				if(side)
					ans(a.l,match.sub(f));
				else
					ans(a.r,match.sub(f));
			}
			if(a.op == '-')
			{
				if(side)	
					ans(a.l,match.add(f));						
				else
					ans(a.r,f.sub(match));					
			}
			if(a.op == '*')
			{
				if(f.p == 0)
				{
					if(match.p == 0)
						multiple = true;
				}
				else if(side)
					ans(a.l,match.div(f));
				else
					ans(a.r,match.div(f));
			}
			if(a.op == '/')
			{
				if(side)
					ans(a.l,match.mult(f));
				else{
					if(f.p == 0){
						if(match.p == 0)
							multiple = true;
						return;
					}
					if(match.p == 0)
						return;
					else
						ans(a.r,f.div(match));
				}
			}
		}
	}
	public static Frac eval(Node a)
	{
		if(a.op == 0)
			return a.f;
		else
		{
			if(a.op == '+')
				return eval(a.l).add(eval(a.r));
			if(a.op == '-')
				return eval(a.l).sub(eval(a.r));
			if(a.op == '*')
				return eval(a.l).mult(eval(a.r));
			if(a.op == '/')
				return eval(a.l).div(eval(a.r));
			return null;
		}
	}
	public static long gcd(long a,long b)
	{
		if(b == 0)
			return a;
		else return gcd(b , a%b);
	}

	private static class Frac
	{
		long p,q;
		Frac(long a, long b)
		{
			p = a;
			q = b;
			if(p == 0)
			{
				q = 1;
			}else{
				long g = gcd(p,q);
				p/=g;
				q/=g;
				if(q < 0)
				{
					q*=-1;
					p*=-1;
				}
			}
		}
		Frac mult(Frac a)
		{
			return new Frac(p*a.p,q*a.q);
		}
		Frac div(Frac a)
		{
			return this.mult(new Frac(a.q,a.p));
		}
		Frac add(Frac a)
		{
			return new Frac(p*a.q+a.p*q,q*a.q);
		}
		Frac sub(Frac a)
		{
			return this.add(new Frac(-a.p,a.q));
		}
	}

	private static class Node{
		public Node(Node l, Node  r, char i, boolean b, Frac f) {
			this.l = l;
			this.r = r;
			op = i;
			x = b;
			this.f = f;

			cx = x;
			if(this.l != null)
				cx |= this.l.cx;
			if(this.r != null)
				cx |= this.r.cx;
		}
		Node l,r;
		char op;
		boolean x;
		boolean cx;
		Frac f;
	}
}
