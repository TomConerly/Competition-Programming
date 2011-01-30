package ACM.PacificNW.y2004;
import java.util.*;

/* ACM Pacific Northwest 2004
 * Problem D: Stacking Cylinders
 * Type: Computational Geometry
 * Solution: You can find where the new cyliner is based on the pair it is resting on.
 */

public class D
{

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        while (true)
        {
            int n = sc.nextInt();
            if (n == 0) break;

            Point[] points = new Point[n];
            for (int i = 0; i < n; i++)
            {
                points[i] = new Point(sc.nextDouble(), 1);
            }
            Arrays.sort(points);
            int rem = n;
            while (rem > 1)
            {
                Point[] next = new Point[rem - 1];
                for (int i = 0; i < rem - 1; i++)
                {
                    next[i] = center(points[i], points[i+1]);
                }
                points = next;
                Arrays.sort(points);
                rem--;
            }

            System.out.printf("%.4f %.4f\n", points[0].x, points[0].y);

        }

    }

    public static Point center(Point a, Point b)
    {
        double dist = distance(a, b);
        double theta = Math.acos(dist / 4);
        double phi = Math.atan2(b.y - a.y, b.x - a.x);
        
        double angle = theta + phi;
        double newx = a.x + 2 * Math.cos(angle);
        double newy = a.y + 2 * Math.sin(angle);

        return new Point(newx, newy);


    }

    public static Point centerA(Point a, Point b)
    {
        Point mid = new Point((a.x + b.x)/2, (a.y + b.y)/2);
        double dist = distance(a, b);
        double r2 = 2;
        Point vector = new Point(b.x - a.x, b.y - a.y);
        Point perp = new Point(-vector.y, vector.x);
        double normVal = Math.sqrt(perp.x * perp.x + perp.y * perp.y);
        double normDist = r2 * r2 - dist * dist / 4;
        mid.x = mid.x + perp.x * normDist / normVal;
        mid.y = mid.y + perp.y * normDist / normVal;
        return mid;

    }

    public static double distance (Point a, Point b)
    {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    private static class Point implements Comparable<Point>
    {

        double x, y;
        public Point(double xx, double yy)
        {
            x = xx;
            y = yy;
        }

        public String toString()
        {
            return "[" + x + "," + y + "]";
        }
        public int compareTo(Point p)
        {
            return Double.valueOf(x).compareTo(Double.valueOf(p.x));
        }

    }

}
