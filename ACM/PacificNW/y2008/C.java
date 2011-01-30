package ACM.PacificNW.y2008;
import java.util.*;
import static java.lang.Math.*;
import java.awt.geom.*;

/* ACM Pacific Northwest 2008
 * Problem C: Cave Crisis
 * Type: Computational Geometry
 * Solution: We can binary search on the radius. For a given radius for all pairs of polygons see
 * if we can get between them. If there is a path from the bottom of the cave to the top only
 * going between polygons we cannot go between then we are stuck. 
 * 
 * Now we don't actually have to binary search. For all pairs of polygons find the distance. Sort these
 * edges in increasing order. Union edges until the top and the bottom are in the same component, the 
 * edge we added last is the answer.
 * 
 * Note the test data is wrong for test case 17
 */

public class C
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int zz = 1;
		while(true)
        {
			zz++;
            int w = sc.nextInt();
            int N = sc.nextInt();
            if(w == 0 && N == 0)
                break;
            p = new Point2D[N][];
            for(int i = 0; i < N;i++)
            {
                int K = sc.nextInt();
				p[i] = new Point2D[K];
                for(int j = 0; j < K;j++)
                {
                    p[i][j] = new Point2D.Double(sc.nextInt(),sc.nextInt());

				}      
            }
            ArrayList<Edge> edges = new ArrayList<Edge>();
            for(int i = 0; i < N;i++)
            {
                for(int j = i+1;j<N;j++)
                {
                    edges.add(new Edge(i,j,dist(i,j)));
                }
            }
            int st= N;
            int en = N+1;
			for(int i = 0; i < N;i++)
            {
                double min = 1e20;
                double max = -1e20;
                for(int j = 0; j < p[i].length;j++)
                {
                    min = min(min,p[i][j].getY());
                    max = max(max,p[i][j].getY());
                }
                edges.add(new Edge(i,st,abs(min+w/2.0)));
                edges.add(new Edge(i,en,abs(max-w/2.0)));
            }
            edges.add(new Edge(st,en,w));
            Collections.sort(edges);
            double ans = 0.0;
            int at = 0;
            parent = new int[N+2];
            for(int i = 0; i < parent.length;i++)
                parent[i] = i;
        
			while(find(st) != find(en))
            {
				Edge e = edges.get(at);
                at++;
                ans = e.c;
                union(e.x,e.y);
            }
			if(ans < 1e-9)
                {System.out.println("impossible");
            }else
                {System.out.format("%.02f\n",ans/2.0);
        	}
		}
    }
    static int[] parent;
    static int[] rank;
    static int find(int x)
    {
        if(parent[x] == x)
            return x;
        parent[x] = find(parent[x]);
        return parent[x];
    }
    static void union(int x, int y)
    {
        int a = find(x);
        int b = find(y);
        parent[b] = a;
    }
    public static class Edge implements Comparable<Edge>
    {
        int x,y;
        double c;
        Edge(int a, int b, double d)
        {
            x = a;
            y = b;
            c = d;
        }
        public int compareTo(Edge e)
        {
            return Double.valueOf(c).compareTo(e.c);
        }
    }
    static double dist(int a, int b)
    {
        double d = 1e20;
        for(int i = 0; i < p[a].length;i++)
            for(int j = 0; j < p[b].length;j++)
            {
                d = min(d,p[a][i].distance(p[b][j]));
				Line2D l1 = new Line2D.Double(p[a][i],p[a][(i+1)%p[a].length]);
                Line2D l2 = new Line2D.Double(p[b][j],p[b][(j+1)%p[b].length]);
                d = min(d,l1.ptSegDist(p[b][j]));
                d = min(d,l2.ptSegDist(p[a][i]));
                if(l1.intersectsLine(l2))
                {					
				   return 0.0;
                }
            }
        return d;
    }
    static Point2D p[][];
}
