package ACM.PacificNW.y2008;
import java.util.*;

/* ACM Pacific Northwest 2008
 * Problem D: Obstacle Course
 * Type: Dijkstra
 * Solution: Simple graph search.
 */

public class D
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int zz = 1;
        while(true)
        {
            N = sc.nextInt();
            board = new int[N][N];
            for(int i = 0; i < N;i++)
                for(int j = 0; j < N;j++)
                    board[i][j] = sc.nextInt();
            if(N == 0)
                break;
            int ans = s();
            System.out.println("Problem "+zz+": "+ans);
            zz++;
        }
    }
    public  static int s()
    {
        PriorityQueue<State> pq = new PriorityQueue<State>();
        int[][] t = new int[N][N];
        for(int i = 0; i < N;i++)
            Arrays.fill(t[i],Integer.MAX_VALUE);

        pq.add(new State(0,0,board[0][0]));
        while(pq.size() > 0)
        {
            State s = pq.poll();
            if(t[s.x][s.y] < s.c)
                continue;
            if(s.x == N-1 && s.y == N-1)
                return s.c;

            for(int i = 0; i < 4 ;i++)
            {
                int nx = s.x + dx[i];
                int ny = s.y+dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= N)
                    continue;
                if(board[nx][ny] +s.c < t[nx][ny])
                {
                    t[nx][ny] = board[nx][ny]+s.c;
                    pq.add(new State(nx,ny,board[nx][ny]+s.c));
                }
            }
        }
            return -1;
    }
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static class State implements Comparable<State>
    {
        int x,y,c;
        public State(int a, int b, int d)
        {
            x = a;
            y = b;
            c = d;
        }
        public int compareTo(State s)
        {
            return Integer.valueOf(c).compareTo(s.c);
        }

    }
    static int N;
    static int[][] board;
}
