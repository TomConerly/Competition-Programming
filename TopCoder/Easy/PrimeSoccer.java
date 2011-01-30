package TopCoder.Easy;
import java.math.BigInteger;

/* TopCoder SRM 422
 * Easy Problem 250 Points: PrimeSoccer
 * Type: Probability/Simulation
 */
public class PrimeSoccer {

	public double getProbability(int skillOfTeamA, int skillOfTeamB) {
		double[] numGA = new double[100];
		double[] numGB = new double[100];
		double[] nnumGA = new double[100];
		double[] nnumGB = new double[100];
		numGA[0] = 1;
		numGB[0] = 1;
		double SA = skillOfTeamA/100.0;
		double SB = skillOfTeamB/100.0;
		for(int i = 0; i < 18;i++)
		{
			for(int j = 0; j < 100;j++)
			{
				nnumGA[j] = (j!= 0?numGA[j-1]*(SA):0)+numGA[j]*(1-SA);
				nnumGB[j] = (j!= 0?numGB[j-1]*(SB):0)+numGB[j]*(1-SB);
			}
			numGA = nnumGA;
			numGB = nnumGB;
			nnumGA = new double[100];
			nnumGB = new double[100];
		}
		double ansA = 0;
		double ansB = 0;
		for(int i = 0; i < 100;i++)
		{
			
			if(i != 0 && i != 1 &&BigInteger.valueOf(i).isProbablePrime(100))
			{
				ansA += numGA[i];
				ansB += numGB[i];
			}
		}
		return 1-(1-ansA)*(1-ansB);
	}

}
