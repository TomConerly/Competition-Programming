package ACM.CTUOpen.y2009;
import java.util.*;

/* CTU Open 2009
 * Problem C: Clock Captcha
 * Type: Brute Force
 * Solution: Generate all solutions from part D and test them (coded by Celestine).
 */

public class C
{

    public static void main(String[] args)
    {

        HashMap<Integer, Wrapper> map = new HashMap<Integer, Wrapper>();

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 24; i++)
        {
            for (int j = 0; j < 60; j++)
            {
                int digit1 = i / 10;
                int digit2 = i % 10;
                int digit3 = j / 10;
                int digit4 = j % 10;

                Wrapper w = new Wrapper();
                w.ans = new char[7][29];
                for (int k = 0; k < 7; k++)
                {
                    Arrays.fill(w.ans[k], ' ');
                }

                drawDigit(w.ans, 0, digit1);
                drawDigit(w.ans, 7, digit2);
                drawDigit(w.ans, 17, digit3);
                drawDigit(w.ans, 24, digit4);
                w.ans[2][14] = 'o';
                w.ans[4][14] = 'o';
                map.put(i * 60 + j, w);

            }
        }

        while (true)
        {
            String[] lines = new String[7];
            lines[0] = sc.nextLine();
            if (lines[0].equals("end"))
            {
                System.out.println("end");
                break;
            }

            char[][] arr = new char[7][29];
            for (int i = 1; i < 7; i++)
            {
                lines[i] = sc.nextLine();
            }
            sc.nextLine();
            sc.nextLine();
            for (int i = 0; i < 7; i++)
            {
                for (int j = 0; j < 29; j++)
                {
                    arr[i][j] = lines[i].charAt(j);
                }
            }

            boolean found = false;
            boolean ambi = false;
            int time = -1;

            for (int i = 0; i < 24 * 60; i++)
            {
                Wrapper wrap  = map.get(i);
                if (matches(wrap.ans, arr)) 
                {
                    if (found)
                    {
                        ambi = true;
                        break;
                    }
                    found = true;
                    time = i;
                }
            }

            if (ambi) 
            {
                System.out.println("ambiguous");
            }
            else
            {
                System.out.format("%02d:%02d\n", time/60, time%60);
            }

        }


    }

    static boolean matches(char[][] arr1, char[][] arr2)
    {
        for (int i = 0; i < 7; i++)
        {
            for (int j = 0; j < 29; j++)
            {
                char q = arr1[i][j];
                char r = arr2[i][j];
                if (r == '.') continue;
                if (q != r) return false;
            }

        }
        return true;
    }



    static class Wrapper
    {
        char[][] ans;

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
