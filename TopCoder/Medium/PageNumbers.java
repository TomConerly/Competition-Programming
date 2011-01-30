package TopCoder.Medium;

/* TopCoder TCO 2009 Qual 2
 * Medium Problem 1000 Points: PageNumbers
 * Type: Brute Force
 * Solution: You could build a lookup table. I counted the number of digits in
 * 0-999 which allowed me to get the answer for i to i+999 quickly so just 
 * take steps of 1000.
 * 
 * There is a better O(log n) recursive solution.
 */

public class PageNumbers {

	public int[] getCounts(int N) {
		int[] ans = new int[10];

		int[] thou1 = brute(1000,false);
		int[] thou2 = brute(1000,true);

		int i = 0;
		while(i <= N)
		{
			if(N-i >= 1000)
			{
				if(i == 0)
				{
					for(int j = 0; j < 10;j++)
						ans[j] += thou1[j];
				}else{
					for(int j = 0; j < 10;j++)
						ans[j] += thou2[j];
					int temp = i;
					temp /= 1000;
					while(temp != 0)
					{
						ans[temp%10] += 1000;
						temp/=10;
					}
				}
				i += 1000;
			}else{
				int temp = i;
				while(temp != 0)
				{
					ans[temp%10]++;
					temp/=10;
				}
				if(i==0)
				{
					ans[0]++;
				}
				i++;
			}
		}
		//System.out.println(Arrays.toString(ans));
		ans[0]--;
		return ans;
	}
	public int[] brute(int N,boolean addZero)
	{
		int[] ans = new int[10];
		for(int i = 0; i < N;i++)
		{
			int count = 0;
			int temp = i;
			while(temp != 0)
			{
				ans[temp%10]++;
				temp/=10;
				count++;
			}
			if(i==0)
			{
				ans[0]++;
				count = 1;
			}
			if(addZero)
				ans[0] += 3-count;
		}
		return ans;
	}
}
