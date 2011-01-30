package Denison.y2008;
import java.util.Scanner;
import java.util.StringTokenizer;

/* 2008 Denison Spring Programming Competition
 * Problem F: Rubiks Cube
 * Type: Simulation
 */
public class F {
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		for(int z = 1; ;z++)
		{
			int n = s.nextInt();
			if(n==0)break;
			StringTokenizer st  = new StringTokenizer(s.nextLine());
			char[] cube = new char[8];
			String start = st.nextToken();
			for(int i = 0; i < 8;i++)
			{
				cube[i] = start.charAt(i);
			}
			st = new StringTokenizer(s.nextLine());
			for(int i = 0; i < n;i++)
			{
				char move = st.nextToken().charAt(0);
				int times = st.nextToken().charAt(0) == 'c' ? 1:3;
				for(int j = 0; j < times;j++)
				{
					if(move == 'T'){
						char t = cube[0];
						cube[0]=cube[2];
						cube[2]=cube[3];
						cube[3]=cube[1];
						cube[1]=t;
					}else if(move =='B'){
						char t = cube[4];
						cube[4]=cube[5];
						cube[5]=cube[7];
						cube[7]=cube[6];
						cube[6]=t;						
					}else if(move == 'R'){
						char t = cube[3];
						cube[3]=cube[7];
						cube[7]=cube[5];
						cube[5]=cube[1];
						cube[1]=t;
					}else if(move == 'L'){
						char t = cube[0];
						cube[0]=cube[4];
						cube[4]=cube[6];
						cube[6]=cube[2];
						cube[2]=t;
					}else if(move == 'F'){
						char t = cube[2];
						cube[2]=cube[6];
						cube[6]=cube[7];
						cube[7]=cube[3];
						cube[3]=t;
					}else{//K
						char t = cube[0];
						cube[0]=cube[1];
						cube[1]=cube[5];
						cube[5]=cube[4];
						cube[4]=t;
					}
				}
			}
			String out = "";
			for(int i = 0; i < 8;i++)out+=cube[i];
			System.out.println("Cube "+z+" has coloring "+out+".");
		}//T, B, R, L, F and K
	}
}
