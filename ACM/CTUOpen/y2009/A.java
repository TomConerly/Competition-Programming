package ACM.CTUOpen.y2009;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

/* CTU Open 2009
 * Problem A: Arable Area
 * Type: Computational Geometry
 * Solution: Walk along each side of the polygon marking boxes as we touch them (but not if we just touch corner).
 * All unmarked boxes are either entirely inside or entirely ouside of the polygon, so just test one point inside of them.
 */

public class A
{

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        while (true)
        {
            int n = sc.nextInt();
            if (n == 0) break;
            Polygon poly = new Polygon();
            for (int i = 0; i < n; i++)
            {
                poly.addPoint(sc.nextInt(), sc.nextInt());
            }

            boolean[][] mark = new boolean[200][200];
            for (int i = 0; i < 200; i++)
            {
                Arrays.fill(mark[i], false);
            }

            for (int i = 0; i < n; i++)
            {
                int next = i == n - 1 ? 0 : i+1;
                markSeg(mark, poly.xpoints[i], poly.ypoints[i], poly.xpoints[next], poly.ypoints[next]);


            }

            int count = 0;
            for (int i = -100; i < 100; i++)
            {
                for (int j = -100; j < 100; j++)
                {
                    if (mark[i+100][j+100]) continue;
 //                   if (!withinPoly(poly, i + 0.99, j + 0.01)) continue;
   //                 if (!withinPoly(poly, i + 0.01, j + 0.99)) continue;
                    if (!withinPoly(poly, i + 0.5, j + 0.5)) continue;
                    count++;
 //                   System.out.println("Found: " + i + "," + j);
                }
            }

            System.out.println(count);


        }



    }

    public static void markSeg(boolean[][] mark, int x1, int y1, int x2, int y2)
    {
        if (x1 == x2 || y1 == y2) return;
        if (x1 > x2)
        {
            int temp = x1;
            x1 = x2;
            x2 = temp;
            temp = y1;
            y1 = y2;
            y2 = temp;
        }

        double ratio = (double)(y2 - y1) / (double)(x2 - x1);
        int g = gcd(Math.abs(x2 - x1), Math.abs(y2 - y1));
        int xdiv = Math.abs(x2 - x1) / g;
        int ydiv = (y2 - y1) / g;
        
    if (ydiv > 0)
    {

        for (int i = 0; i < g; i++)
        {
            int xs = xdiv * i + x1;
            int ys = ydiv * i + y1;
            int prevy = ys;

            for (int j = 0; j < xdiv; j++)
            {
                int xc = xs + j;
                double nexty = (j + 1) * ratio + ys;

                if (j == xdiv - 1) 
                {
                    nexty = ys + ydiv - 1e-6;
                }
                int curry = prevy;

                while (curry < nexty)
                {
                    mark[xc + 100][curry + 100] = true;
                    //System.out.println("marking " + xc + "," + curry);
                    curry++;
                }

                prevy = curry - 1;

            }

        }
    }
    else
    {
        
        for (int i = 0; i < g; i++)
        {
            int xs = xdiv * i + x1;
            int ys = ydiv * i + y1;
            int prevy = ys - 1;

            for (int j = 0; j < xdiv; j++)
            {
                int xc = xs + j;
                double nexty = (j + 1) * ratio + ys;
                if (j == xdiv - 1)
                {
                    nexty = ys + ydiv + 1e-6;
                }
                int curry = prevy;

                while (curry >= (int)Math.floor(nexty))
                {
                    mark[xc + 100][curry + 100] = true;
                    //System.out.println("marking " + xc + "," + curry);
                    curry--;
                }

                prevy = curry+1;

            }
        }

    }

    }

    public static int gcd(int x, int y)
    {
        if (x < y) return gcd(y, x);
        if (y == 0) return x;
        return gcd(y, x % y);
    }

    public static boolean withinPoly(Polygon poly, double x, double y)
    {

        for (int i = 0; i < poly.npoints; i++)
        {
            int next = i == poly.npoints - 1 ? 0 : i+1;
            double result = Line2D.ptSegDistSq(poly.xpoints[i], poly.ypoints[i], poly.xpoints[next], poly.ypoints[next], x, y);
            if (Math.abs(result) < 1e-9) return true;
        }

        return poly.contains(x, y);
    }

    public static boolean crossesBox(int x, int y, int x1, int y1, int x2, int y2)
    {
        if (x1 == x2 || y1 == y2) return false;
        double[] pp1 = intersectLine(x, y, x+1, y, x1, y1, x2, y2);
        double[] pp2 = intersectLine(x+1, y, x+1, y+1, x1, y1, x2, y2);
        double[] pp3 = intersectLine(x, y+1, x+1, y+1, x1, y1, x2, y2);
        double[] pp4 = intersectLine(x, y, x, y+1, x1, y1, x2, y2);
        
        ArrayList<Point2D.Double> list = new ArrayList<Point2D.Double>();

        if (pp1 != null)
        {
            list.add(new Point2D.Double(pp1[0], pp1[1]));
        }
        if (pp2 != null)
        {
            list.add(new Point2D.Double(pp2[0], pp2[1]));
        }
        if (pp3 != null)
        {
            list.add(new Point2D.Double(pp3[0], pp3[1]));
        }
        if (pp4 != null)
        {
            list.add(new Point2D.Double(pp4[0], pp4[1]));
        }

        for (int i = 0; i < list.size(); i++)
        {
            for (int j = i + 1; j < list.size(); j++)
            {
                if (Math.abs(list.get(i).x - list.get(j).x) > 1e-9
                    || Math.abs(list.get(i).y - list.get(j).y) > 1e-9)
                {
                    return true;
                }
            }
        }
        return false;

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

        double x = (B2 * C1 - B1 * C2) / det;
        double y = (A1 * C2 - A2 * C1) / det;

        if (Line2D.ptSegDistSq(x1, y1, x2, y2, x, y) > 1e-9) return null;
        if (Line2D.ptSegDistSq(x3, y3, x4, y4, x, y) > 1e-9) return null;

        return new double[] {x, y};

    }




}
