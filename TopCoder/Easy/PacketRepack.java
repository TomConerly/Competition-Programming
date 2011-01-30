package TopCoder.Easy;
/* TopCoder TCO07 Qual 3
 * Easy Problem 300 Points: PacketRepack
 * Type: Bit Operations
 * Solution: Make a get/set operation then it is trivial, still painful to code.
 */
public class PacketRepack {

	public int[] output(int[] input, int b, int num_fields, int field_size) {
		int[] output = new int[input.length];
		for(int i = 0; i < input.length;i++)
			output[i] = input[i];
		
		for(int i = 0; i < num_fields;i++)
		{
			int v = get(input,i*field_size,field_size,b);
			set(output,(num_fields-i-1)*field_size,field_size,b,v);
		}
		return output;
	}
	public int get(int[] dat,int st, int len,int b)
	{
		int stIdx = st/b;
		int stS = st % b;
		int ans = 0;
		for(int i = 0; i < len;i++)
		{
			if(stS==b){
				stS = 0;
				stIdx++;
			}
			ans |=  ((dat[stIdx] >>> (stS)) &1)<<i;
			stS++;
		}
		return ans;
	}
	public void set(int[] dat,int st, int len,int b,int data)
	{
		int stIdx = st/b;
		int stS = st % b;		
		for(int i = 0; i < len;i++)
		{
			if(stS==b){
				stS = 0;
				stIdx++;
			}
			dat[stIdx] = (dat[stIdx] & (~(1<<(stS)))) | (((data >>> i)&1)<<stS);
			stS++;
		}
	}
	
}
