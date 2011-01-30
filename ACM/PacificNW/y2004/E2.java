package ACM.PacificNW.y2004;
import java.util.*;

/* ACM Pacific Northwest 2004
 * Problem E: Going Home
 * Type: Assignment Problem
 * Solution: Standard Assignment Problem.
 */

public class E2
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            int N = sc.nextInt();
            int M = sc.nextInt();
            if(N == 0 && M == 0)
                break;
            char[][] map = new char[N][M];
            K = 0;
            for(int i = 0; i < N;i++)
            {
                String s = sc.next();
                for(int j = 0; j < M;j++)
                {
                    map[i][j] = s.charAt(j);
                    if(map[i][j] == 'H')
                        K++;
                }
            }
            int[][] house = new int[K][2];
            int[][] man = new int[K][2];
            int ha = 0,ma= 0;
            for(int i = 0; i < N;i++)
                for(int j = 0; j < M;j++)
                {
                    if(map[i][j] == 'H')
                    {
                        house[ha][0] = i;
                        house[ha][1] = j;
                        ha++;
                    }
                    else if(map[i][j] == 'm')
                    {
                        man[ma][0] = i;
                        man[ma][1] = j;
                        ma++;
                    }
                }
            cost = new int[K][K];
            for(int i = 0; i < K;i++)
            {
                for(int j = 0; j < K;j++)
                {
                    cost[i][j] =
                    Math.abs(house[i][0]-man[j][0])+Math.abs(house[j][1]-man[i][1]);
                }
            }
            assA = new int[K];
            assB = new int[K];
            Arrays.fill(assA,-1);
            Arrays.fill(assB,-1);
            System.out.println(maxflow());
        }
    }
    static int maxflow()
    {
        int co = 0;
        for(int z = 0; z < K;z++)
        {
            shortest();
            int minAt = -1, min = 0;
            for(int j = K; j < 2*K;j++)
            {
                if(assB[j-K] != -1)
                    continue;
                if(minAt == -1 || p[j] < min)
                {
                    minAt = j;
                    min = p[j];
                }
            }
            co += min;
            recur(minAt);
        }
        return co;
    }
    static void recur(int at)
    {
        if(prev[at] == -2 && p[at] == 0)
            return;
        if(at >= K)
        {
            recur(prev[at]);
            assA[prev[at]] = at-K;
            assB[at-K] = prev[at];              
        }
        else
        {        	
        	//don't undo edges because to undo it must be set elsewhere
            recur(prev[at]);
        }
    }
    static int K;
    static int[] assA, assB,p,prev;
    static int[][] cost;
    static void shortest()
    {
        p = new int[2*K];
        prev = new int[2*K];
        Arrays.fill(prev,-1);
        Arrays.fill(p,1000000);
        
        for(int i = 0; i < K;i++)
        {
            if(assA[i] == -1)
            {
                p[i] = 0;
                prev[i] = -2;
            }
        }
        for(int i = 0; i < 2*K;i++)
        {
            for(int j = 0; j < K;j++)
            {
                for(int m = K;m <2*K;m++)
                {
                    if(assA[j] == m-K)
                    {
                    	if(p[m]-cost[j][m-K] < p[j])
                    	{
                    		p[j] = p[m]-cost[j][m-K];
                    		prev[j] = m;
                    	}                       
                    }
                    else
                    {
                    	if(p[j]+cost[j][m-K] < p[m])
                    	{
                    		prev[m] = j;
                    		p[m] = p[j]+cost[j][m-K];
                    	}
                    }
                }
            }
        }
    }
}