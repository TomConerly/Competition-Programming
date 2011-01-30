package USACO.Chapter3;
/*
ID: theycal2
LANG: JAVA
TASK: heritage
 */
/* USACO Training
 * American Heritage
 * Type: Trees
 * Solution: Find the recurence and then it is trivial.
 * The root is the first thing in the preorder. Use that
 * to split the in order into left subtree and right subtree.
 * Using the sizes of the left and right you can split the in order.
 * Now you have left and right so call print on left and right then print 
 * yourself. Add the base case of size 0 do nothing, and size 1 just print
 * yourself.
 */
import java.io.*;
public class heritage 
{
	public static void main(String[] args) throws IOException 
	{
		long start = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("heritage.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("heritage.out")));

		String inOrder = f.readLine();
		String preOrder = f.readLine();

		print(inOrder,preOrder);
		out.println();
		out.close();
		System.out.println("$:"+(System.currentTimeMillis()-start));
		System.exit(0);
	}
	private static void print(String inOrder, String preOrder) {
//		System.out.println(inOrder+" "+preOrder);
		if(inOrder.length() == 1 && preOrder.length() == 1)
		{
			out.print(inOrder);
			return;
		}else if(inOrder.length() == 0 && preOrder.length() == 0)
			return;
		String root = preOrder.substring(0,1);
		String inOrderL = inOrder.substring(0,inOrder.indexOf(root));
		String inOrderR = inOrder.substring(inOrder.indexOf(root)+1);

		String preOrderL = preOrder.substring(1,inOrderL.length()+1);
		String preOrderR = preOrder.substring(inOrderL.length()+1);

//		System.out.println("root: "+root);
//		System.out.println("in: "+inOrderL+" "+inOrderR);
//		System.out.println("pre: "+preOrderL+" "+preOrderR);


		print(inOrderL,preOrderL);
		print(inOrderR,preOrderR);
		out.print(root);

	}
	static PrintWriter out;

}

