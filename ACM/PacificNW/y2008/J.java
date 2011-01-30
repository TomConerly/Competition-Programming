package ACM.PacificNW.y2008;
import java.awt.geom.*;
import java.util.*;

/* ACM Pacific Northwest 2008
 * Problem J: PropBot
 * Type: Brute Force
 * Solution: Just try all 2^24 paths. (Coded by Celestine)
 */

public class J
{

    static double bestDist = 0;
    static double[] motionx;
    static double[] motiony;
    static double targetx;
    static double targety;

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        motionx = new double[8];
        motiony = new double[8];
        for (int i = 0; i < 8; i++)
        {
            motionx[i] = 10 * Math.cos(Math.PI * i / 4);
            motiony[i] = 10 * Math.sin(Math.PI * i / 4);
        }


        for (int cases = 0; cases < t; cases++)
        {
            int secs = sc.nextInt();
            targetx = sc.nextDouble();
            targety = sc.nextDouble();
                
            bestDist = (targetx * targetx + targety * targety);
            int dir = 0;
            Point2D.Double at = new Point2D.Double(0, 0);
            Point2D.Double at2 = new Point2D.Double(0, 0);
            move(at, dir, secs);
            turn(at2, dir, secs);

            System.out.printf("%.06f\n", Math.sqrt(bestDist));

        }

    }

    public static void move(Point2D.Double at, int dir, int secs)
    {
        if (secs <= 0) return;
        double x = at.x + motionx[dir];
        double y = at.y + motiony[dir];
        double dist = Math.pow(targetx - x, 2) + Math.pow(targety - y, 2);

        if (dist < bestDist) bestDist = dist;
        
        move(new Point2D.Double(x, y), dir, secs-1);
        turn(new Point2D.Double(x, y), dir, secs-1);
    }

    public static void turn(Point2D.Double at, int dir, int secs)
    {
        if (secs<= 0) return;
        int next = dir - 1;
        if (next == -1) next = 7;
        move(new Point2D.Double(at.x, at.y), next, secs-1);
        turn(new Point2D.Double(at.x, at.y), next, secs-1);
    }

}
