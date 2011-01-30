package TopCoder.Easy;
/* TopCoder SRM 433
 * Easy Problem 250 Points: MagicWords
 * Type: Simulation
 * Solution: Do a somewhat smart test for isMagic and you are fine.
 */

public class MagicWords {
	public int K;
	public int count(String[] S, int kk) {     
		K = kk;     
		ans = 0;
		recur("",0,new boolean[S.length],S);     
		return ans;
	}   
	public int ans = 0;
	public void recur(String a, int at,boolean[] used,String[] str)   
	{
		if(at == used.length)
		{       
			if(isMagic(a)) ans++;     
		}else{
			for(int i = 0; i < used.length;i++)
			{        
				if(used[i]) continue;        
				used[i] = true;         
				recur(a+str[i],at+1,used,str);      
				used[i] = false;    
			}   
		}   
	}   
	 private boolean isMagic(String p)
	    {
	        int delta = (p.substring(1) + p).indexOf(p) + 1;
	        return K * delta == p.length();
	    } 
	

}