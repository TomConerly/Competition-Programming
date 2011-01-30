package TopCoder.Medium;
/* TopCoder SRM 442
 * Medium Problem 500 Points: BedroomFloor
 * Type: Medium
 * Solution: Special case the crap out of this.
 */
import static java.lang.Math.*;

public class BedroomFloor {
	public void p(Object s){System.out.println(s);}
	public long numberOfSticks(int x1, int y1, int x2, int y2) {
		int ax1 = ((x1+4)/5)*5;
		int ax2 = ((x2)/5)*5;
		int ay1 = ((y1+4)/5)*5;
		int ay2 = ((y2)/5)*5;

		long ans = 0;
		type = new long[6];
		if(ax1 > ax2 && ay1 > ay2)
		{
			ax1 = x1/5*5;
			ay1 = y1/5*5;
			if((ax1/5+ay1/5+1)%2 == 0)
			{
				type[y2-y1] += x2-x1;
			}
			else
			{
				type[x2-x1] += y2-y1;
			}

		}else if(ax1 > ax2){
			addCorner(x2-x1,ay1-y1,(ax2/5+ay1/5)%2);
			addCorner(x2-x1,y2-ay2,(ax2/5+ay2/5+1)%2);
			addSide(x2-x1,(ay2-ay1)/5,(ax2/5+ay1/5+1)%2);
		}else if(ay1 > ay2){
			int t1 = x1;
			int t2 = x2;
			int t3 = ax1;
			int t4 = ax2;
			
			x1 = y1;
			x2 = y2;
			ax1 = ay1;
			ax2 = ay2;
			
			y1 = t1;
			y2 = t2;
			ay1 = t3;
			ay2 = t4;		
			
			addCorner(x2-x1,ay1-y1,(ax2/5+ay1/5)%2);
			addCorner(x2-x1,y2-ay2,(ax2/5+ay2/5+1)%2);
			addSide(x2-x1,(ay2-ay1)/5,(ax2/5+ay1/5+1)%2);
		}else{
			//System.out.println(ax1+" "+ax2+" "+ay1+" "+ay2);
			ans = (ax2-ax1)*((long)ay2-ay1)/5;
			
			addCorner(ax1-x1,y2-ay2,(ay2/5+ax1/5)%2);//bl
			addCorner(x2-ax2,y2-ay2,(ay2/5+ax2/5+1)%2);//br
			addCorner(x2-ax2,ay1-y1,(ay1/5+ax2/5)%2);//tr
			addCorner(ax1-x1,ay1-y1,(ay1/5+ax1/5+1)%2);//tl

			addSide(ax1-x1,(ay2-ay1)/5,(ay1/5+ax1/5)%2);//left
			addSide(x2-ax2,(ay2-ay1)/5,(ay1/5+ax2/5+1)%2);//right
			addSide(ay1-y1,(ax2-ax1)/5,(ay1/5+ax1/5+1)%2);//top
			addSide(y2-ay2,(ax2-ax1)/5,(ay2/5+ax1/5+1+1)%2);//bottom
		}
		ans += type[5];
		//System.out.println(Arrays.toString(type)+" "+ans);		
		
		long t = min(type[1],type[4]);
		type[1] -= t;
		type[4] -= t;
		ans += t;
		ans += type[4];
		type[4] = 0;

		t = min(type[2],type[3]);
		type[2] -= t;
		type[3] -= t;
		ans += t;

		t = min(type[1]/2,type[3]);
		type[1] -= t*2;
		type[3] -= t;
		ans += t;

		if(type[3] > 0)
			type[1] = 0;
		ans += type[3];
		type[3] = 0;

		t = min(type[1],type[2]/2);
		type[1] -= t;
		type[2] -= t*2;
		ans += t;

		t = min(type[1]/3,type[2]);
		type[1] -= t*3;
		type[2] -= t;
		ans += t;

		if(type[2] > 0)
		{
			ans += (type[2]+1)/2;
			type[2] = 0;
			type[1] = 0;
		}		

		ans += (type[1]+4)/5;
		type[1] = 0;
		return ans;
	}
	private void addSide(int  dx, long num, long startT) {
		long numA = ((num+1)/2);
		long numB = num/2;

		if(startT == 0)
		{
			type[5] += dx*numA;
			type[dx] += numB*5;
		}else{
			type[5] += dx*numB;
			type[dx] += numA*5;
		}		
	}
	//0 vertical, 1 horizontal
	private void addCorner(int dx, int dy, long t) {
		if(t == 0)
		{
			type[dy] += dx;
		}
		else
		{
			type[dx] += dy;
		}
	}
	long[] type;

}
