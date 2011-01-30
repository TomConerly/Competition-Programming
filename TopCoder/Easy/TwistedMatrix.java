package TopCoder.Easy;
/* TopCoder Member Pilot 2
 * Easy Problem 250 Points: TwistedMatrix
 * Type: Brute Force
 * Solution: Just do it.
 */
public class TwistedMatrix {
	public void p(Object s){System.out.println(s);}
	public String[] solve(String[] A, String[] B) {
		
		String[] best = new String[0];
		for(int i = 0; i < A.length-1;i++)
		{
			for(int j = 0; j < A[0].length()-1;j++)
			{
				String[] testA = new String[A.length];
				String[] testB = new String[A.length];
				for(int k = 0; k < A.length;k++)
				{
					testA[k] = "";
					testB[k] = "";
				}
				boolean legal = true;
				for(int k = 0; k < A.length;k++)
					for(int m = 0; m < A[0].length();m++)
					{					
						if((k == i || k == i+1)&&( j == m || j+1 == m))
						{
							testA[k] += A[k].charAt(m);
							testB[k] += B[k].charAt(m);
							continue;
						}
						if(A[k].charAt(m) == '?' &&B[k].charAt(m) == '?' )
						{
							testA[k] += "0";
							testB[k] += "0";
						}
						else if(A[k].charAt(m) != '?' && B[k].charAt(m) == '?')
						{
							testA[k] += A[k].charAt(m);
							testB[k] += A[k].charAt(m);
						}
						else if(A[k].charAt(m) == '?' && B[k].charAt(m) != '?')
						{
							testA[k] += B[k].charAt(m);
							testB[k] += B[k].charAt(m);
						}
						else if(A[k].charAt(m) != B[k].charAt(m) )
						{
							legal = false;
							break;
						}else{
							testA[k] += A[k].charAt(m);
							testB[k] += B[k].charAt(m);
						}
						
					}	
				if(!legal)continue;
	
				//clockwise
				legal = true;
				for(int k = 0; k < 4;k++)
				{
					int n = (k+1)%4;
					if(testA[i+x[k]].charAt(j+y[k]) == '?' && testB[i+x[n]].charAt(j+y[n]) == '?')
					{
						set(testA,i+x[k],j+y[k],'0');
						set(testB,i+x[n],j+y[n],'0');
					}else if(testA[i+x[k]].charAt(j+y[k]) == '?' && testB[i+x[n]].charAt(j+y[n]) != '?')
					{
						set(testA,i+x[k],j+y[k],testB[i+x[n]].charAt(j+y[n]));
					}
					else if(testB[i+x[n]].charAt(j+y[n]) == '?' && testA[i+x[k]].charAt(j+y[k]) != '?')
					{
						set(testB,i+x[n],j+y[n],testA[i+x[k]].charAt(j+y[k]));
					}
					else if(testA[i+x[k]].charAt(j+y[k]) != testB[i+x[n]].charAt(j+y[n]))
					{
						legal = false;
						break;
					}
					/*if(testA[i+x[k]].charAt(j+y[k]) == '?' || testB[i+x[n]].charAt(j+y[n]) == '?')
					{
						p("ERROR! "+testA[i+x[k]].charAt(j+y[k])+" "+ testB[i+x[n]].charAt(j+y[n]));
					}*/
				}
				if(legal)
					best = b(best,testB);
				
				
				testA = new String[A.length];
				testB = new String[A.length];
				for(int k = 0; k < A.length;k++)
				{
					testA[k] = "";
					testB[k] = "";
				}
				legal = true;
				for(int k = 0; k < A.length;k++)
					for(int m = 0; m < A[0].length();m++)
					{					
						if((k == i || k == i+1)&&( j == m || j+1 == m))
						{
							testA[k] += A[k].charAt(m);
							testB[k] += B[k].charAt(m);
							continue;
						}
						if(A[k].charAt(m) == '?' &&B[k].charAt(m) == '?' )
						{
							testA[k] += "0";
							testB[k] += "0";
						}
						else if(A[k].charAt(m) != '?' && B[k].charAt(m) == '?')
						{
							testA[k] += A[k].charAt(m);
							testB[k] += A[k].charAt(m);
						}
						else if(A[k].charAt(m) == '?' && B[k].charAt(m) != '?')
						{
							testA[k] += B[k].charAt(m);
							testB[k] += B[k].charAt(m);
						}
						else if(A[k].charAt(m) != B[k].charAt(m) )
						{
							legal = false;
							break;
						}else{
							testA[k] += A[k].charAt(m);
							testB[k] += B[k].charAt(m);
						}
						
					}	
				if(!legal)continue;
				
				legal = true;
				for(int k = 0; k < 4;k++)
				{
					int n = (k-1+4)%4;
					if(testA[i+x[k]].charAt(j+y[k]) == '?' && testB[i+x[n]].charAt(j+y[n]) == '?')
					{
						set(testA,i+x[k],j+y[k],'0');
						set(testB,i+x[n],j+y[n],'0');
					}else if(testA[i+x[k]].charAt(j+y[k]) == '?' && testB[i+x[n]].charAt(j+y[n]) != '?')
					{
						set(testA,i+x[k],j+y[k],testB[i+x[n]].charAt(j+y[n]));
					}
					else if(testB[i+x[n]].charAt(j+y[n]) == '?' && testA[i+x[k]].charAt(j+y[k]) != '?')
					{
						set(testB,i+x[n],j+y[n],testA[i+x[k]].charAt(j+y[k]));
					}
					else if(testA[i+x[k]].charAt(j+y[k]) != testB[i+x[n]].charAt(j+y[n]))
					{
						legal = false;
						break;
					}
					/*if(testA[i+x[k]].charAt(j+y[k]) == '?' || testB[i+x[n]].charAt(j+y[n]) == '?')
					{
						p("ERROR! "+testA[i+x[k]].charAt(j+y[k])+" "+ testB[i+x[n]].charAt(j+y[n]));
					}*/
				}
				if(legal)
					best = b(best,testB);
			}
		}
		/*for(String s:best)
			p(s);*/
		return best;
	}
	public void set(String[] s, int x, int y, char c)
	{
		s[x] = s[x].substring(0,y)+c+s[x].substring(y+1);
		
	}
	int[] x = {0,0,1,1};
	int[] y = {0,1,1,0};
	public String[] b(String[] a, String[] b)
	{
		//for(String s:b)
		//	p(s);
		////p("");
		if(a.length == 0)
			return b;
		for(int i = 0; i < a.length;i++)
		{
			int cmp = a[i].compareTo(b[i]);
			if(cmp < 0)
				return a;
			if(cmp > 0)
				return b;
		}
		return a;
	}
}
