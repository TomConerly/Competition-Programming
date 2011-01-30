package USACO.Chapter3;
/*
ID: theycal2
LANG: JAVA
TASK: rect1
 */
/* USACO Training
 * Shaping Regions
 * Type: Computational Geometry
 * Solution: Note that this solution is just barely fast enough to finish in time.
 * Split the region into only areas with a horizontal/vertical edge. So we end up
 * with a 2000x2000 grid where each grid box has a different width/height. We can now
 * fill into the grid like we would but this is slow because we are filling in up to
 * 4,000,000 array locations per rectangle. Fill them in backwards and keep an array 
 * which says if we are at x,y what is the smallest i such that x,y+i is not filled yet
 * this allows us to not write over things twice.
 */
import java.io.*;
import java.util.*;

public class rect1 
{
	public static void main(String[] args) throws IOException 
	{
		long start = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("rect1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rect1.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int A = Integer.valueOf(st.nextToken());
		int B = Integer.valueOf(st.nextToken());
		int N = Integer.valueOf(st.nextToken());
		int[][] rect = new int[N][5];
		for(int i = 0; i < N;i++)
		{
			st = new StringTokenizer(f.readLine());
			for(int j = 0; j < 5;j++)
			{
				rect[i][j] = Integer.valueOf(st.nextToken());
			}
		}

		board = new short[2002][2002];
		maxX = 1;
		maxY = 1;
		xDim = new ArrayList<Integer>();
		yDim = new ArrayList<Integer>();
		xDim.add(0);
		xDim.add(A);
		yDim.add(0);
		yDim.add(B);

		for(int i = 0; i < N;i++)
		{
			if(!xDim.contains(rect[i][0]))
				xDim.add(rect[i][0]);
			if(!xDim.contains(rect[i][2]))
				xDim.add(rect[i][2]);

			if(!yDim.contains(rect[i][1]))
				yDim.add(rect[i][1]);
			if(!yDim.contains(rect[i][3]))
				yDim.add(rect[i][3]);
		}
		Collections.sort(xDim);
		Collections.sort(yDim);
		maxX = xDim.size()-1;
		maxY = yDim.size()-1;

		short[][] cover = new short[2002][2002];
		for(int i = 0; i < cover.length;i++)
		{
			Arrays.fill(cover[i],(short)-1);
			Arrays.fill(board[i],(short)1);
		}

		int count = 0;
		for(int i = N-1; i >= 0;i--)
		{
			int lx = Collections.binarySearch(xDim, rect[i][0]);
			int hx = Collections.binarySearch(xDim, rect[i][2]);

			int ly = Collections.binarySearch(yDim, rect[i][1]);
			int hy = Collections.binarySearch(yDim, rect[i][3]);

			short v = (short)rect[i][4];
			for(int x = lx; x < hx;x++)
			{
				for(int y = ly; y < hy;y++)
				{
					if(cover[x][y] != -1)
					{
						y = cover[x][y]-1;
					}
					else
					{
						board[x][y] = v;
						cover[x][y] = (short)hy;
						count++;
					}					
				}
			}
		}
		//System.out.println(count+" "+maxX+" "+maxY);
		int[] colors = new int[2510];
		for(int x = 0; x < maxX;x++)
		{
			for(int y = 0; y < maxY;y++)
			{
				colors[board[x][y]] += (yDim.get(y+1)-yDim.get(y))*(xDim.get(x+1)-xDim.get(x));
			}
		}
		for(int i = 0; i < colors.length;i++)
		{
			if(colors[i] > 0)
			{
				out.println(i+" "+colors[i]);
			}
		}
		out.close();
		System.out.println("$:"+(System.currentTimeMillis()-start));
		System.exit(0);
	}
	static int maxX,maxY;
	static short[][] board;
	static ArrayList<Integer> xDim;
	static ArrayList<Integer> yDim;

	public static void print()
	{
		System.out.println("X:"+xDim);
		System.out.println("Y:"+yDim);
		System.out.println(maxX+" "+maxY);

		for(int y = 0; y < maxY;y++)
		{
			for(int x = 0; x < maxX;x++)
			{
				System.out.print(board[x][y]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}


}

