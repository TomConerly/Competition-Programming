package ACM.EastCentral.y2000;
import java.util.*;

/* ACM ICPC 2003 East Central Regional
 * Problem E: Checker's Check
 * Type: Simulation
 * Solution: Can you find all the special cases?
 * I think not!
 */

public class E
{
   public static void main(String[] args)
   {
       Scanner sc = new Scanner(System.in);
       while(true)
       {
           int red = sc.nextInt();
           int whi = sc.nextInt();
           if(red == 0 && whi == 0) break;
           board = new int[8][8];
           for(int i = 0; i < red;i++)
           {
               int piece = sc.nextInt();
               int[] loc = tocoord(Math.abs(piece));
               board[loc[0]][loc[1]] = piece < 0 ? 2:1;
           }
           for(int i = 0; i < whi;i++)
           {
               int piece = sc.nextInt();
               int[] loc = tocoord(Math.abs(piece));
               board[loc[0]][loc[1]] = piece < 0 ? -2:-1;
           }
           int num = sc.nextInt();
           String s = sc.nextLine().trim();
           boolean whitesMove = false;
           if(s.equals("W")) whitesMove = true;
           else whitesMove = false;

           ArrayList<ArrayList<Integer>> moves = new ArrayList<ArrayList<Integer>>();
           for(int i = 0; i < num;i++)
           {
               moves.add(new ArrayList<Integer>());
               String[] p = sc.nextLine().split("-");
               for(String temp: p)
               {
                   int[] t = tocoord(Integer.valueOf(temp));
                   moves.get(i).add(t[0]);
                   moves.get(i).add(t[1]);
               }
           }
           boolean legal = true;
           int line = 0;

           for(int n = 1; n <= moves.size();n++)
           {
               for(int i = 0; i < moves.get(n-1).size()-3;i+=2)
               {
                   int atx = moves.get(n-1).get(i);
                   int aty = moves.get(n-1).get(i+1);
                   int tox = moves.get(n-1).get(i+2);
                   int toy = moves.get(n-1).get(i+3);
                   if(!mine(whitesMove,atx,aty)){legal=false;line=n;break;}

                   int[][] pos = posMoves(atx,aty);
                   boolean canMove = false;
                   for(int j = 0; j < pos.length;j++)
                   {
                       if(pos[j][0] == -1) break;
                       if(pos[j][0] == tox && pos[j][1] == toy)
                       {
                           canMove = true;
                       }
                   }
                  // System.out.println("1:"+canMove);
                   if(!canMove){legal=false;line=n;break;}
                   boolean jump = Math.abs(atx-tox) > 1 && Math.abs(aty-toy) > 1;
                   if(! jump)
                   {
                       for(int x = 0; x < 8;x++)
                       {
                           for(int y = 0; y < 8;y++)
                           {
                               if(mine(whitesMove,x,y))
                               {
                                   if(canJump(x,y))
                                   {
                                       legal = false; line = n; break;
                                   }
                               }
                           }
                       }
                   }
                   //System.out.println("2:"+jump);
                   if(!legal) break;
                   board[tox][toy] = board[atx][aty];
                   board[atx][aty] = 0;
                   boolean kingAlready = Math.abs(board[tox][toy]) == 2;
                   if(jump)
                   {
                       board[(atx+tox)/2][(aty+toy)/2] = 0;
                   }
                   boolean kinged = false;
                   if(!kingAlready && whitesMove && toy == 0)
                   {
                	   kinged = true;
                       board[tox][toy] = -2;
                       if(!(i+4 == moves.get(n-1).size()) ){legal=false;line=n;break;}
                   }
                   if(!kingAlready && !whitesMove && toy == 7)
                   {
                	   kinged = true;
                       if(!(i+4 == moves.get(n-1).size())){legal=false;line=n;break;}
                       board[tox][toy] = 2;
                   }
                   if(canJump(tox,toy) && jump&& !kinged && i+4== moves.get(n-1).size())
                   {
                       legal = false; line = n;break;
                   }
                   //System.out.println("end:"+legal);
               }
               if(!legal) break;
               whitesMove = ! whitesMove;
           }
           if(legal) System.out.println("All moves valid");
           else System.out.println("Move "+line+" is invalid");
       }
   }
   public static boolean mine(boolean w, int x, int y)
   {
       if(x < 0 || x > 7 || y < 0 || y > 7) return false;
       if(w && board[x][y] < 0) return true;
       if(!w && board[x][y] > 0) return true;
       return false;
   }

   public static int[] tocoord(int i)
   {
       i--;
       int temp = i %8;
       int x=0,y=0;

       if(temp < 4)
           x = 1+2*temp;
       else
           x = 2*(temp%4);
       y = i /4;
       return new int[] {x,y};
   }
   static int[][] board;
   public static int[][] posMoves(int x, int y)
   {
       boolean king = Math.abs(board[x][y]) == 2;
       boolean white = board[x][y] < 0;
       int[][] pos = new int[1000][2];
       for(int i = 0; i < 1000;i++)
       {
           pos[i][0] = -1;
           pos[i][1] = -1;
       }

       int at = 0;
       for(int m = -1; m <=1; m+=2)
       {
           if((m == -1 && white) || (m==1 && !white) || king)
           {
               if(empty(x+1,y+1*m))
               {
                   pos[at] = new int[] {x+1,y+1*m};at++;
               }
               if(empty(x-1,y+1*m))
               {
                   pos[at] = new int[] {x-1,y+1*m};at++;
               }
               if(opp(white,x+1,y+1*m) && empty(x+2,y+2*m))
               {
                   pos[at] = new int[]{x+2,y+2*m};at++;
               }
               if(opp(white,x-1,y+1*m) && empty(x-2,y+2*m))
               {
                   pos[at] = new int[]{x-2,y+2*m};at++;
               }
           }
       }
       return pos;
   }
   public static boolean canJump(int x, int y)
   {
       int[][] pos = posMoves(x,y);
       for(int i = 0; i < pos.length;i++)
       {
           if(pos[i][0] == -1) break;
           if(Math.abs(pos[i][0]-x) > 1 && Math.abs(pos[i][1]-y) > 1) return true;
       }
       return false;
   }
   public static boolean opp(boolean c, int x, int y)
   {
       if(x < 0 || x > 7 || y < 0 || y > 7) return false;
       if(c)
       {
           return board[x][y] > 0;
       }else return board[x][y] < 0;
   }
   public static boolean empty(int x,  int y)
   {
       if(x < 0 || x > 7 || y < 0 || y > 7) return false;
       return board[x][y] == 0;
   }
}