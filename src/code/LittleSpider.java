package code;

import java.util.ArrayList;

public class LittleSpider{
	
	public Deck x = new Deck();
	public ArrayList<ArrayList<Card>> piles = new ArrayList<ArrayList<Card>>();
	
	public void initializePiles() {
		ArrayList<Card> tab1 = new ArrayList<Card>();
		ArrayList<Card> tab2 = new ArrayList<Card>();
		ArrayList<Card> tab3 = new ArrayList<Card>();
		ArrayList<Card> tab4 = new ArrayList<Card>();
		ArrayList<Card> tab5 = new ArrayList<Card>();
		ArrayList<Card> tab6 = new ArrayList<Card>();
		ArrayList<Card> tab7 = new ArrayList<Card>();
		ArrayList<Card> tab8 = new ArrayList<Card>();
		ArrayList<Card> cellD = new ArrayList<Card>();
		ArrayList<Card> cellH = new ArrayList<Card>();
		ArrayList<Card> cellC = new ArrayList<Card>();
		ArrayList<Card> cellS = new ArrayList<Card>();
		piles.add(tab1);
		piles.add(tab2);
		piles.add(tab3);
		piles.add(tab4);
		piles.add(tab5);
		piles.add(tab6);
		piles.add(tab7);
		piles.add(tab8);
		piles.add(cellD);
		piles.add(cellH);
		piles.add(cellC);
		piles.add(cellS);
		piles.get(8).add(x.deck.get(0));
		x.deck.remove(0);
		piles.get(9).add(x.deck.get(12));
		x.deck.remove(12);
		piles.get(10).add(x.deck.get(36));
		x.deck.remove(36);
		piles.get(11).add(x.deck.get(48));
		x.deck.remove(48);
	}
	
	/**
	 * This method sets 8 tableau piles taking the top card of 
	 */
	
	public void initializeTableau() {
		for(int j = 0; j < 8; j++) {
			for(int i = 0; i < 6; i++) {
				piles.get(j).add(x.deck.get(0));
			x.deck.remove(0);
			}
		}
	}
	
	/**
	 * This method checks if the pile that the user wants to add onto
	 * is or greater than 0 and is either 7 or less than 7. If the pile 
	 * they want to add onto is empty than the method ends because it isn't 
	 * possible to add a card onto that pile. Also of the pile that the user
	 * wants to add onto is or greater than 8 and is or less than 11. Piles
	 * 8 - 11 represent the four cells and the card that wants that 
	 * the user wants to add would have to fall under the conditions that
	 * allow a card to be added onto a cell
	 * @param pileRemove
	 * @param pileAdd
	 */
	
	public boolean remove(int pileRemove,int pileAdd) {
		boolean retVal = false;
		if(pileAdd >= 0 && pileAdd <=7) {
			if(!(tableauRemove(pileAdd))) return retVal;
		    else if(tableauAdd(piles.get(pileRemove).get(0), piles.get(pileAdd).get(0), pileAdd)) {
				piles.get(pileRemove).remove(0);
				retVal = true;
			}
		}
		else if(pileAdd >=8 && pileAdd <= 11) {
			if(!(tableauRemove(pileRemove))) return retVal;
		    else if(cellAdd(piles.get(pileRemove).get(0), piles.get(pileAdd).get(0))) {
		    	piles.get(pileRemove).remove(0);
				retVal = true;
			}
		}
		return retVal;
	}
	
	/**
	 * Method checks if the pile the user wants to remove from is empty.
	 * If it isn't the method returns false signifying that the method
	 * can not work but if it isn't the method returns true.
	 * @param pileAdd
	 * @return
	 */
	
	public boolean tableauRemove(int pileAdd) {
		if(piles.get(pileAdd).isEmpty()) return false;
		return true;
	}
	
	/**
	 * 
	 * @param pileRemove
	 * @return
	 */
	
	public boolean cellRemove(int pileRemove) {
		if(piles.get(pileRemove).size() == 1) return false;
		return true;
	}
	/** 
	 * Method that will check if the card being moved onto the chosen tableau pile is either
	 * a rank above or a rank below the pile's top card. If the case is where the top card's 
	 * a King or an Ace (Ace representing 1 and King representing 13). If the top card is
	 * a King then if the card being placed onto the chosen pile is an Ace "1" then it can be 
	 * placed onto the pile. Vice versa for Ace, if the card being placed onto the pile is a King
	 * then it can be placed.
	 * 
	 * @param moved
	 * @param current
	 * @param pileAdd
	 * @return
	 */
	public boolean tableauAdd(Card moved, Card current, int pileAdd) {
		boolean legal = false;
		
		if(moved.getRank() == current.getRank()+1 || moved.getRank() == current.getRank()-1  
				|| (moved.getRank() == 13 &&  current.getRank() == 1 )
				|| (moved.getRank() == 1 &&  current.getRank() == 13 )) {
			legal = true;
			piles.get(pileAdd).add(0,moved);
		}
		return legal;
	}
	
	/**
	 * Method will check if the card being added to the cell is first even able to be placed onto
	 * by checking if the conditions of the cell pile are being met. If the player wanted to add
	 * a card onto the Diamonds suit, the card must first be of the suit "D" representing 
	 * Diamonds. Must also be the rank above the rank of the current top card of the cell pile.
	 * The same conditions go for the Hearts pile, but the difference is the card being added
	 * must have the suit of "H" which stands for Hearts, instead of "D". For the cells of suits
	 * Clubs and Spades ("C" & "S"), the card that the player wants to place onto the cell's pile
	 * must first be of the same rank and also be a rank lower than the top or current card's rank.
	 * 
	 * @param moved
	 * @param current
	 * @return
	 */
	public boolean cellAdd(Card moved, Card current) {
		boolean legal = false;
		
		if(current.getSuit() .equals("D")) {
			if(moved.getRank() == current.getRank()+1 && moved.getSuit() .equals("D")) {
				piles.get(8).add(0,moved);
				legal = true;
			}
		}
		else if(current.getSuit() .equals("H")) {
			if(moved.getRank() == current.getRank()+1 && moved.getSuit() .equals("H")) {
				piles.get(9).add(0,moved);
				legal = true;
			}
		}
        else if(current.getSuit() .equals("C")) {
        	if(moved.getRank() == current.getRank()-1  && moved.getSuit() .equals("C")) {
				piles.get(10).add(0,moved);
				legal = true;
			}
		}
        else if(current.getSuit() .equals("S")) {
        	if(moved.getRank() == current.getRank()-1  && moved.getSuit() .equals("S")) {
				piles.get(11).add(0,moved);
				legal = true;
			}
		} 
		
		return legal;
	}
/*	public static void main(String[] args) {
		LittleSpider.initializePiles();
		
		LittleSpider.remove(0, 8);
		LittleSpider.remove(2, 0);
		
		for(ArrayList<Card> x : piles) {
			for(Card  y: x) {
				System.out.println(y.getRank() + y.getSuit());
			}
		}
		for(ArrayList<Card> x : piles) {
			System.out.println(x);
		}
	}*/
}
