package TopCoder.Easy;
import java.util.*;

/* TopCoder SRM 426
 * Easy Problem 250 Points: ShufflingMachine
 * Type: Simulation
 * Solution: Find the number of times a given start position is delt to the dishonest player
 * then greadily assign the cards to these positions.
 */
public class ShufflingMachine {

	public double stackDeck(int[] shuffle, int maxShuffles, int[] cardsReceived, int K) {
		int[] cards = new int[shuffle.length];
		for(int i = 0; i < cards.length;i++)
			cards[i] = i;
		
		int[] count = new int[shuffle.length];
		for(int i = 0; i < maxShuffles;i++)
		{
			cards = shuffle(cards,shuffle);
			for(int j = 0; j < cardsReceived.length;j++)
			{
				count[cards[cardsReceived[j]]]++;
			}
		}
		
		Arrays.sort(count);
		int used = K;
		int good = 0;
		for(int i = count.length-1;i>= 0; i--)
		{
			good += count[i];
			used--;
			if(used == 0) break;
		}		
		return good/((double)maxShuffles);
	}	

	private int[] shuffle(int[] c,int[] s) {
		int[] next = new int[c.length];
		for(int i = 0; i < c.length;i++)
		{
			next[s[i]] = c[i];
		}
		return next;		
	}

}
