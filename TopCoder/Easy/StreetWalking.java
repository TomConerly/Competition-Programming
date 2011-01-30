package TopCoder.Easy;
import static java.lang.Math.*;

/* TopCoder SRM 395
 * Easy Problem 250 Points: StreetWalking
 * Type: Simulation
 * Solution: There are only 3 ways of walking, all wall, all sneak until it one direction to go then walk,
 * or all sneak.
 */

public class StreetWalking {

	public long minTime(int x, int y, int w, int s) {
		long walkTime = w;
		long sneakTime = s;
		long X = x;
		long Y = y;
		long normal = walkTime*(X+Y);
		long walksneak = walkTime*(max(X,Y)-min(X,Y))+sneakTime*min(X,Y);
		long sneak = sneakTime*min(X,Y)+sneakTime*(((max(X,Y)-min(X,Y))/2)*2)+walkTime*((max(X,Y)-min(X,Y))%2);
		return min(normal,min(sneak,walksneak));
	}

}
