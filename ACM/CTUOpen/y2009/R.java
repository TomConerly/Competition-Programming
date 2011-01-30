package ACM.CTUOpen.y2009;
import java.util.*;
import static java.lang.Math.*;
import java.awt.geom.*;

/* CTU Open 2009
 * Problem R: Robotic Rails
 * Type: Computional Geometry
 * Solution: Find all intersection points. For each intersection point keep a list of which lines intersect it
 * at which angles, and which intersection point that line takes you too. Then just Dijkstra.
 */

public class R
{
    public static boolean print = false;
    public static boolean p = false;;
    @SuppressWarnings("unchecked")
	public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int T = -0;
        while(true)
        {
            T++;
            //p = T == 32;
            //if(T==33)
             //   break;
            int N = sc.nextInt();
            if(N==0)
                break;
            x1 = new int[N];
            y1 = new int[N];
            x2 = new int[N];
            y2 = new int[N];
            for(int i = 0; i < N;i++)
            {
                x1[i] = sc.nextInt();
                y1[i] = sc.nextInt();
                x2[i] = sc.nextInt();
                y2[i] = sc.nextInt();
               // if(p)
                 //   System.out.println("("+x1[i]+","+y1[i]+")->("+x2[i]+","+y2[i]+")");
            }
            ArrayList<Point2D.Double>[] inter = new ArrayList[N];
            intOn = new ArrayList[N];
            for(int i = 0; i < N;i++)
            {
                intOn[i] = new ArrayList<Integer>();
                inter[i] = new ArrayList<Point2D.Double>();
                for(int j = 0; j < N;j++)
                {
                    if(i==j)
                        continue;
                    print = T==32 && i == 0 && j == 11;
                    double[] p = intersectLine(x1[i],y1[i],x2[i],y2[i],x1[j],y1[j],x2[j],y2[j]);
                    if(p != null)
                    {
                        inter[i].add(new Point2D.Double(p[0],p[1]));
               //         if((i==0 || i == 1) && T==32)
                 //           System.out.println("INTER: "+p[0]+" "+p[1]+" "+i+" "+j);
                    }
                }
            }
            final ArrayList<Point2D.Double> points = new ArrayList<Point2D.Double>();
            for(int i = 0; i < N;i++)
            {
                for(int k = 0; k < inter[i].size();k++)
                {
                    boolean found = false;
                    for(int j = 0; j < points.size();j++)
                    {
                        if(inter[i].get(k).distance(points.get(j)) < 1e-9)
                        {
                            found = true;
                            if(!intOn[i].contains(j))
                                intOn[i].add(j);
                            break;
                        }
                    }
                    if(!found)
                    {
                        points.add(inter[i].get(k));
                        intOn[i].add(points.size()-1);
                    }
                }
            }

            Comparator<Integer> pcomp = new Comparator<Integer>()
            {
                public int compare(Integer a, Integer b)
                {
                    int cmp = Double.valueOf(points.get(a).getX()).compareTo(points.get(b).getX());
                    if(abs(points.get(a).getX() - points.get(b).getX()) < 1e-9)
                        return (Double.valueOf(points.get(a).getY()).compareTo(points.get(b).getY()));
                    else
                        return cmp;
                }
            };
            for(int i = 0; i < N;i++)
            {
                Collections.sort(intOn[i], pcomp);
            }
            adj = new ArrayList[points.size()]; 
            for(int i = 0; i < points.size();i++)
            {
                adj[i] = new ArrayList<Edge>();
            }
            for(int i = 0; i < N;i++)
            {
                double theta = atan2(y2[i]-y1[i],x2[i]-x1[i]);
                    //    if((i==0||i==1) && T==32)
                      //      System.out.println("A: "+i+" "+theta);
                boolean s = false;
                if(x2[i] > x1[i])
                    s = true;
                else if(x2[i] < x1[i])
                    s = false;
                else if(x1[i]== x2[i])
                {
                    if(y2[i] > y1[i])
                    {
                        s = true;
                    }
                    else
                    {
                        s= false;
                    }
                }
                for(int j = 0; j < intOn[i].size();j++)
                {
                    int point = intOn[i].get(j);
                    if(j +1 != intOn[i].size())
                        adj[point].add(new Edge(intOn[i].get(j+1),s?theta:PI+theta));
                    if(j != 0)
                        adj[point].add(new Edge(intOn[i].get(j-1),s?PI+theta:theta));
                }
            }
            int l = N-1;
            if(intOn[0].size() == 0 || intOn[l].size() == 0)
            {
                if(N == 1)
                    System.out.format("%.03f\n",sqrt(pow(x1[0]-x2[0],2)+pow(y1[0]-y2[0],2)));
                else
                System.out.println("unreachable");
                continue;
            }
            PriorityQueue<State> pq = new PriorityQueue<State>();
            double theta1 = atan2(y2[0]-y1[0],x2[0]-x1[0]);
            int stinter;
            if(x2[0] == x1[0])
            {
                if(y1[0] > y2[0])
                {
                    stinter = intOn[0].get(intOn[0].size()-1);
                }
                else
                {
                    stinter = intOn[0].get(0);
                }
            }
            else if(x2[0] > x1[0])
            {
                    stinter = intOn[0].get(0);
                
            }else{
                    stinter = intOn[0].get(intOn[0].size()-1);
            }
            
            double theta2 = atan2(y2[l]-y1[l],x2[l]-x1[l]);
            int eninter;
            if(x2[l] == x1[l])
            {
                if(y1[l] > y2[l])
                {
                    eninter = intOn[l].get(0);
                }
                else
                {
                    eninter = intOn[l].get(intOn[l].size()-1);
                }
            }
            else if(x2[l] > x1[l])
            {
                    eninter = intOn[l].get(intOn[l].size()-1);
                
            }else{
                    eninter = intOn[l].get(0);

            }

            int st = points.size();
            //int en = points.size()+1;
            pq.add(new State(new Edge(stinter,theta1),0.0,stinter,st));
            double[][] cost = new double[points.size()+2][points.size()+2];
            for(int i = 0; i < cost.length;i++)
                Arrays.fill(cost[i],Long.MAX_VALUE);
            cost[stinter][st] = 0.0;
            double ans = 0.0;
            boolean found  = false;
            double e1 = points.get(stinter).distance(new Point2D.Double(x1[0],y1[0]));
            double e2 = points.get(eninter).distance(new Point2D.Double(x2[l],y2[l]));
            while(pq.size() > 0)
            {
                
                State s= pq.poll();
               //if(p)
                    //System.out.format("(%.3f,%.3f) from (%.3f,%.3f), angle=%.3f cost = %.3f\n",points.get(s.at).getX(),points.get(s.at).getY(),(s.prev < points.size() ? points.get(s.prev).getX():x1[0]),(s.prev < points.size() ? points.get(s.prev).getY():y1[0]),s.e.angle,s.cost);
                double xx2 = points.get(s.at).getX();
                double yy2 = points.get(s.at).getY();
                double xx1 = (s.prev < points.size() ? points.get(s.prev).getX():x1[0]);
                double yy1 = (s.prev < points.size() ? points.get(s.prev).getY():y1[0]);
                double theta = atan2(yy2-yy1,xx2-xx1);
                if(abs(norm(theta)-norm(s.e.angle)) > 1e-0)
                {

                    System.out.println("ERROR!");
                    System.exit(0);
                }
                
                if(cost[s.at][s.prev] < s.cost)
                    continue;
                if(s.at == eninter)
                {
                    if(legal(s.e.angle,theta2))
                    {
                        found = true;
                        ans = s.cost;
                        break;
                    }
                }
                for(int i = 0; i < adj[s.at].size();i++)
                {
                    Edge e = adj[s.at].get(i);
                 //   if(p)
                   //     System.out.println("T: "+s.e.angle+" "+e.angle);
                    if(legal(s.e.angle,e.angle))
                    {
                        double c = s.cost + points.get(s.at).distance(points.get(e.at));
                        if(c < cost[e.at][s.at])
                        {
                            cost[e.at][s.at] = c;
                            pq.add(new State(e,c,e.at,s.at));
                        }
                    }
                }
            }
            if(found)
            {
                System.out.format("%.03f\n",ans+e1+e2);
            }else{
                System.out.println("unreachable");
            }
        }
    }
    public static boolean legal(double a1, double a2)
    {
        a1 = norm(a1);
        a2 = norm(a2);
        
        double diff = abs(a2-a1);
        diff = min(diff,abs((a2+2*PI)-a1));
        diff = min(diff,abs(a2-(2*PI+a1)));

        return diff <= PI/2 + 1e-9;
    }
    public static double norm(double a1)
    {
        while(a1 > 2*PI)
            a1 -= 2*PI;
        while(a1 <= 0)
            a1 += 2*PI;
        return a1;
    }
    static ArrayList<Edge>[] adj;
    static int[] x1,y1,x2,y2;
    static ArrayList<Integer>[] intOn;

    private static class State implements Comparable<State>
    {
        Edge e;
        int at, prev;
        double cost;
        State(Edge ee, double c, int a, int p)
        {
            e = ee;
            cost = c;
            at = a;
            prev = p;
        }
        public int compareTo(State s)
        {
            return Double.valueOf(cost).compareTo(s.cost);
        }
    }
    private static class Edge
    {
        int at;
        double angle;
        public Edge(int a, double b)
        {
            at = a;
            angle = b;
        }
    }
public static double[] intersectLine(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4)
    {
        double A1 = y2 - y1;
        double B1 = x1 - x2;
        double A2 = y4 - y3;
        double B2 = x3 - x4;
        double C1 = A1 * x1 + B1 * y1;
        double C2 = A2 * x3 + B2 * y3;
        double det = A1 * B2 - A2 * B1;

        if(abs(det) < 1e-9)
        {
            boolean found = false;;
            int x=0,y=0;
            if(Line2D.ptSegDist(x1,y1,x2,y2,x3,y3) < 1e-9)
                if(!found || x3 < x || (x3 == x && y3 < y))
                {
                    found = true;
                    x =x3;
                    y=y3;
                }

            if(Line2D.ptSegDist(x1,y1,x2,y2,x4,y4) < 1e-9)
                if(!found || x4 < x || (x4 == x && y4 < y))
                {
                    found = true;
                    x =x4;
                    y=y4;
                }
            if(Line2D.ptSegDist(x3,y3,x4,y4,x1,y1) < 1e-9)
                if(!found || x1 < x || (x1 == x && y1 < y))
                {
                    found = true;
                    x =x1;
                    y=y1;
                }
            if(Line2D.ptSegDist(x3,y3,x4,y4,x2,y2) < 1e-9)
                if(!found || x2 < x || (x2 == x && y2 < y))
                {
                    found = true;
                    x =x2;
                    y=y2;
                }
            if(found)
                return new double[] {x,y};
            else
                return null;
        }

        double x = (B2 * C1 - B1 * C2) / det;
        double y = (A1 * C2 - A2 * C1) / det;
        if (Line2D.ptSegDist(x1, y1, x2, y2, x, y) > 1e-3) return null;
        if (Line2D.ptSegDist(x3, y3, x4, y4, x, y) > 1e-3) return null;
        if (Line2D.ptSegDist(x1, y1, x2, y2, x, y) > 1e-4)
        {
            System.out.println("E: "+(B2*C1-B1*C2)+" "+det+" "+Line2D.ptSegDist(x1,y1,x2,y2,x,y));
            System.exit(0);
        }
        return new double[] {x, y};

    }




}
