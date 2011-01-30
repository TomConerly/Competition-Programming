package ACM.CTUOpen.y2009;
import java.util.*;
import java.math.*;

/* CTU Open 2009
 * Problem L: Letter Lies
 * Type: DP
 * Solution: Simple DP to find # of paths through a dag, use BigIntegers.
 */

public class L
{
    @SuppressWarnings("unchecked")
	public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            int N = sc.nextInt();
            L = sc.nextInt();
            int B = sc.nextInt();
            int F = sc.nextInt();
            if(N==0 && L == 0 && B == 0 && F == 0)
                break;
            adj = new ArrayList[N];
            for(int i = 0; i < N;i++)
            {
                adj[i] = new ArrayList<Integer>();
                @SuppressWarnings("unused")
				int a = sc.nextInt();
                int d = sc.nextInt();
                for(int j = 0; j < d;j++)
                {
                    adj[i].add(sc.nextInt()-1);
                }
            }
            isStart = new boolean[N];
            for(int i = 0; i < B;i++)
                isStart[sc.nextInt()-1] = true;;

            isEnd = new boolean[N];
            for(int i = 0; i < F;i++)
                isEnd[sc.nextInt()-1] = true;
            
            BigInteger ans = BigInteger.ZERO;
            dp = new BigInteger[1500][1500];
            for(int i = 0; i < dp.length;i++)
                Arrays.fill(dp[i],BigInteger.valueOf(-1));
            for(int i = 0; i < N;i++)
            {
                if(isStart[i])
                    ans = ans.add(recur(i,1));  
            }
            if(ans.equals(BigInteger.ZERO))
            {
                System.out.println("impossible");
            }
            else{
                System.out.println(ans);
            }
        }
    }
    public static BigInteger recur(int at, int len)
    {
        if(len == L)
        {
            if(isEnd[at])
                return BigInteger.ONE;
            else
                return BigInteger.ZERO;
        }
        if(!dp[at][len].equals(BigInteger.valueOf(-1)))
            return dp[at][len];
        BigInteger res = BigInteger.ZERO;
        for(int i : adj[at])
        {
            res = res.add(recur(i,len+1));
        }
        dp[at][len] = res;
        return res;
    }
    static int L;
    static BigInteger[][] dp;
    static ArrayList<Integer>[] adj;
    static boolean[] isStart,isEnd;
}
