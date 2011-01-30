package TopCoder.Easy;
/* TopCoder 2009 TCO Round 1
 * Easy Problem 250 Points: SequenceSums
 * Type: Binary Search
 * Solution: Try all lengths and use binary search to find if it works.
 * 
 */

public class SequenceSums {

	public int[] sequence(int N, int L) {
		int le=-1;
		int st=-1;
		for(int len = L;len <=100;len++)
		{
			int low = 0;
			int high = 1000000000;
			while(true)
			{
				int mid = (low+high)/2;
				long s =sum(mid+len-1)-sum(mid-1); 
				
				if(s > N)
				{
					high = mid-1;
				}else if(s < N)
				{
					low = mid+1;
				}else{
					le = len;
					st = mid;
					break;
				}
				if( low > high) break;
			}
			if(le != -1) break;

		}
		if(le == -1 || le > 100) return new int[]{};
		int[] ans = new int[le];
		for(int i = 0; i < le;i++)
			ans[i] = st+i;

		return ans;
	}
	long sum(long i)
	{
		if(i < 0) return 0;
		return (i*(i+1))/2;
	}

}
