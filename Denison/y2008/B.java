package Denison.y2008;
import java.util.Scanner;

/* 2008 Denison Spring Programming Competition
 * Problem B: Relay Team
 * Type: Brute Force
 */

public class B {
	public static void main(String[] args)
	{
		Scanner s= new Scanner(System.in);
		for(int z = 1;;z++)
		{
			int n = s.nextInt();
			if(n==0) break;
			int[][] swimmers = new int[n][4];
			for(int i = 0; i < n;i++)
			{
				for(int j =0; j < 4;j++){
					swimmers[i][j]=s.nextInt();
				}
			}
			long bestScore = Long.MAX_VALUE;
			int bestTeam = 0;
			for(int i = 0; i < n;i++)
	 		{
				for(int j = 0;j<n;j++)
				{
					if(i==j) continue;
					for(int k = 0;k<n;k++)
					{
						if(i==k || k == j) continue;
						for(int m = 0;m<n;m++)
						{
							if(m==i || m == j || m == k) continue;
							long score = scoreTeam(swimmers,i,j,k,m);
							if(score < bestScore)
							{
								bestScore = score;
								bestTeam =(i<<24)+(j<<16)+(k<<8)+m;
							}
						}
					}
				}
			}
			int a = bestTeam >> 24;
			int b =( bestTeam >> 16)%256;
			int c =( bestTeam >> 8)%256;
			int d =( bestTeam)%256;
			a++;b++;c++;d++;
			int timeSec = (int)(bestScore >> 32);
			int min = timeSec/60;
			int sec = timeSec%60;
			if(sec >= 10)
				System.out.println("Team "+z+"'s best relay team is swimmers "+a+" "+b+" "+c+" "+d+
					" for a total time of "+min+":"+sec+".");
			else
				System.out.println("Team "+z+"'s best relay team is swimmers "+a+" "+b+" "+c+" "+d+
						" for a total time of "+min+":0"+sec+".");
		}
	}
	public static long scoreTeam(int[][] swimmers,int a, int b, int c, int d)
	{
		long time = swimmers[a][0]+swimmers[b][1]+swimmers[c][2]+swimmers[d][3];
		return (time << 32) | (a << 24) | (b << 16) | ( c << 8 ) | d;
	}
}
