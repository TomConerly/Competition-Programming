package TopCoder.Hard;
/* TopCoder SRM 391
 * Hard Problem 1000 Points: Transformation
 * Type: Math
 * Solution: We want to factor each number then we only need to keep a factor
 * if it is the largest number of occurances for that prime out of all numbers.
 * If there is a tie for the largest number then leave them in the higher indexed number.
 * We can do these steps with the GCD.
 */
public class Transformation
{
	public String[] transform(String[] origin)
	{
		final int n=origin.length;
		long vector[]=new long[n];
		for(int i=0;i < n;i++)
			vector[i]=Long.parseLong(origin[i]);
		for(int i=n-1;i>=0;i--)
		{
			for(int j=0;j < n;j++)
			{
				if(j==i)continue;
				long g=gcd(vector[i],vector[j]);
				long t=vector[j]/g;
				while((gcd(t,vector[i])) > 1)
					vector[i]/=gcd(t,vector[i]);
			}
			for(int j=i+1;j < n;j++)
				vector[i]/=gcd(vector[i],vector[j]);
		}
		String[] re=new String[n];
		for(int i=0;i < n;i++)re[i]=(new Long(vector[i])).toString();
		return re;
	}
	long gcd(long a,long b)
	{
		if(b == 0) return a;
		return gcd(b,a%b);
	}
}