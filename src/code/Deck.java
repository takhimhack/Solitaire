package code;

import java.util.ArrayList;
import java.util.Collections;

public class Deck{

	public Deck() {
		
deck = new ArrayList<Card>();
		
		int counter = 1;
//		for(int i = 0;i <52;i++) {
//			deck.add(i,); 
//		}
		for(int i = 0;i <52;i++) {
			if(i < 13) {
//				deck[i].setRank(counter);
//				deck[i].setSuit("D");
				deck.add(new Card(counter, "D", counter+"d.gif"));
			}
			else if(i > 12 && i < 26) {
//				deck[i].setRank(counter);
//				deck[i].setSuit("H");
				deck.add(new Card(counter, "H", counter+"h.gif"));
			}
			else if(i > 25  && i < 39) {
//				deck[i].setRank(counter);
//				deck[i].setSuit("C");
				deck.add(new Card(counter, "C", counter+"c.gif"));
			}
			else if(i > 38  && i < 52) {
//				deck[i].setRank(counter);
//				deck[i].setSuit("S");
				deck.add(new Card(counter, "S", counter+"s.gif"));
			}
			counter++;
			if(counter == 14) {
				counter = 1;
			}
		}
	}
	public ArrayList<Card> deck;
	
	
	/**
	 * Method used to shuffle the deck's cards
	 */
	public void shuffle() {
		Collections.shuffle(deck);
		
	}
/* Make 52 Cards
 *  Shuffle method to remove cards in use then shuffle
 * 
 * 
 * 
 */
/*public static void main(String[] args) {
	
	//Wanted us to make constructer
	Deck x = new Deck();

	x.shuffle();
	System.out.println(x.deck.get(0).getRank());
	System.out.println(x.deck.get(0).getSuit());
	System.out.println(x.deck);
	
}*/
}
