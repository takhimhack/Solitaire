package code;

public class Card {
	
	private int rank;
	private String suit;
	private String img;

	public Card(int r, String s, String m) {

setRank(r);
setSuit(s);
setImg(m);

	}
	
	
	public String getImg() {
		return img;
	}





	public void setImg(String img) {
		this.img = img;
	}


	/**
	 * Method sets the rank of the card
	 * @param rank
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	/**
	 * Method sets the suit of the card
	 * @param suit
	 */
	
	public void setSuit(String suit) {
		this.suit = suit;
	}
	
	/**
	 * Method gets the card's suit
	 * @return
	 */

	public String getSuit() {

		return suit;

	}
	
	/**
	 * Method gets the card's rank
	 * @return
	 */
	
	public int getRank() {
		return rank;

	}

}
