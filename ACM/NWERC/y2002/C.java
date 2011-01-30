package ACM.NWERC.y2002;
import java.util.*;

/* ACM NWERC 2002
 * Problem C: Hansel and Grethel
 * Type: Computational Geometry
 * Solution: Construct the two lines and determine where they cross. (Coded by Celestine)
 */

public class C
{

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int cases = 1; cases <= T; cases++)
        {
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int d1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();
            int d2 = sc.nextInt();

            double vx1 = Math.sin(Math.toRadians(d1 + 180));
            double vy1 = Math.cos(Math.toRadians(d1 + 180));
            double vx2 = Math.sin(Math.toRadians(d2 + 180));
            double vy2 = Math.cos(Math.toRadians(d2 + 180));

            double A1 = vy1;
            double B1 = -vx1;
            double C1 = A1*x1 + B1*y1;
            double A2 = vy2;
            double B2 = -vx2;
            double C2 = A2*x2 + B2 * y2;

            double det = A1 * B2 - A2 * B1;
            double x = (B2 * C1 - B1 * C2) / det;
            double y = (A1 * C2 - A2 * C1) / det;

            System.out.printf("%.4f %.4f\n", x, y);





        }



    }





}
