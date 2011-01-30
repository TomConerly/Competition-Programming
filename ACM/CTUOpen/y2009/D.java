package ACM.CTUOpen.y2009;
import java.util.*;

/* CTU Open 2009
 * Problem D: Digital Display
 * Type: Simulation
 * Solution: Simply generate all according to the rules.
 */

public class D
{

    public static void main(String[] args)
    {


        Scanner sc = new Scanner(System.in);
        while (true)
        {
            String line = sc.nextLine();
            if (line.equals("end")) 
            {
                System.out.println("end");
                break;
            }

            int digit1 = line.charAt(0) - '0';
            int digit2 = line.charAt(1) - '0';
            int digit3 = line.charAt(3) - '0';
            int digit4 = line.charAt(4) - '0';
            
            char[][] ans = new char[7][29];
            for (int i = 0; i < 7; i++)
            {
                Arrays.fill(ans[i], ' ');
            }

            drawDigit(ans, 0, digit1);
            drawDigit(ans, 7, digit2);
            drawDigit(ans, 17, digit3);
            drawDigit(ans, 24, digit4);
            ans[2][14] = 'o';
            ans[4][14] = 'o';

            for (int i =0; i < 7; i++)
            {
                for (int j = 0; j < 29; j++)
                {
                    System.out.print(ans[i][j]);
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
        }

    }

    static int digits[][] = new int[][]
    {
        {1,1,1,0,1,1,1},
        {0,0,1,0,0,1,0},
        {1,0,1,1,1,0,1},
        {1,0,1,1,0,1,1},
        {0,1,1,1,0,1,0},
        {1,1,0,1,0,1,1},
        {1,1,0,1,1,1,1},
        {1,0,1,0,0,1,0},
        {1,1,1,1,1,1,1},
        {1,1,1,1,0,1,1}
    };

    static void drawDigit(char[][] ans, int x, int digit)
    {
        int[] dig = digits[digit];
        if (dig[0] == 1)
        {
            ans[0][x+1] = '-';
            ans[0][x+2] = '-';
            ans[0][x+3] = '-';
        }
        if (dig[1] == 1)
        {
            ans[1][x] = '|';
            ans[2][x] = '|';
        }
        if (dig[2] == 1)
        {
            ans[1][x+4] = '|';
            ans[2][x+4] = '|';
        }
        if (dig[3] == 1)
        {
            ans[3][x+1] = '-';
            ans[3][x+2] = '-';
            ans[3][x+3] = '-';
        }
        if (dig[4] == 1)
        {
            ans[4][x] = '|';
            ans[5][x] = '|';
        }
        if (dig[5] == 1)
        {
            ans[4][x+4] = '|';
            ans[5][x+4] = '|';
        }
        if (dig[6] == 1)
        {
            ans[6][x+1] = '-';
            ans[6][x+2] = '-';
            ans[6][x+3] = '-';
        }

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                int row = 3 * i;
                int col = x + 4 * j;
                if (checkAdj(ans, row, col))
                {
                    ans[row][col] = '+';
                }
            }
        }


    }

    static boolean checkAdj(char[][] ans, int row, int col)
    {
        if (row > 0)
        {
            if (ans[row - 1][col] != ' ') return true;
        }
        if (row < ans.length -  1)
        {
            if (ans[row + 1][col] != ' ') return true;
        }
        if (col < ans[0].length - 1)
        {
            if (ans[row][col + 1] != ' ') return true;
        }
        if (col > 0)
        {
            if (ans[row][col - 1] != ' ') return true;
        }

        return false;

        
    }



}
