package code;

import java.util.ArrayList;

import java.util.List;

public class Golf{

	public Deck x = new Deck();
	public ArrayList<ArrayList<Card>> tableau = new ArrayList<ArrayList<Card>>();
	public ArrayList<Card> cell = new ArrayList<Card>();
	
	


	//7 Tableau Piles - 5 cards 
	//1 Homecell
	//1 stock - 17 cards

	
	public void initializeTableau() {
		ArrayList<Card> pile1 = new ArrayList<Card>();
		ArrayList<Card> pile2 = new ArrayList<Card>();
		ArrayList<Card> pile3 = new ArrayList<Card>();
		ArrayList<Card> pile4 = new ArrayList<Card>();
		ArrayList<Card> pile5 = new ArrayList<Card>();
		ArrayList<Card> pile6 = new ArrayList<Card>();
		ArrayList<Card> pile7 = new ArrayList<Card>();
		tableau.add(pile1);
		tableau.add(pile2);
		tableau.add(pile3);
		tableau.add(pile4);
		tableau.add(pile5);
		tableau.add(pile6);
		tableau.add(pile7);
		for(int j = 0; j < 7; j++) {
			for(int i = 0; i < 5; i++) {
			tableau.get(j).add(x.deck.get(0));
			x.deck.remove(0);
			}
		}
	}
	
	/**
	 * Method first checks if the stock is empty. If that isn't the 
	 * case then the homecell gets the top card of the stock and 
	 * removes that top card of the stock.
	 */
	
	public void stockRemove() {
		if(!removeStockTop()) return;
		if(homeCell(x.deck.get(0))) {
			cell.add(x.deck.get(0));
			x.deck.remove(0);
		}
	}
	
	/**
	 * This method takes the card the user wants to use and checks if 
	 * it can be added onto the homecell.
	 * @param choice
	 * @return
	 */
	
	public boolean homeCell(Card choice) {
		boolean legal = false;
		if(cell.isEmpty() || (x.deck.size() > 0 && choice == x.deck.get(0))
				|| choice.getRank() == cell.get(cell.size()-1).getRank()+1
				|| choice.getRank() == cell.get(cell.size()-1).getRank()-1  
				|| (cell.get(cell.size()-1).getRank() == 13 &&  choice.getRank() == 1 )
				|| (cell.get(cell.size()-1).getRank() == 1 &&  choice.getRank() == 13)) {
			legal = true;
			
		}
		return legal;
	}
	
	/**
	 * This method at first checks of the pile that is being chosen isn't empty
	 * by calling on the removeTabTop method. If that isn't the case the method
	 * will get the top card of the pile and remove it.
	 * @param pile
	 */
	
	public boolean tableauRemove(int pile) {
		boolean retVal = false;
		if(!removeTabTop(pile) || pile == -1) return retVal;
		if(homeCell(tableau.get(pile).get(0))) {
			cell.add(tableau.get(pile).get(0));
			tableau.get(pile).remove(0);
			retVal = true;
		}
		return retVal;
	}
	
	
	/**
	 * Method doesn't allow the user to add onto the tableau
	 * @return
	 */
	
	public boolean tableauAdd() {
		return false;
	}
	
	/**
	 * Method doesn't allow the user to remove from the home cell
	 * @return
	 */
	
	public boolean homecellRemove() {
		return false;
	}
	
	/**
	 * Method doesn't allow the user to add onto the stock
	 * @return
	 */
	
	public boolean stockpileAdd() {
		return false;
	}
	
	/**
	 * This method checks to see if the tableau is empty to see if
	 * its top card can be removed from its pile.
	 * @param pile
	 * @return
	 */
	
	public boolean removeTabTop(int pile) {
		
		if(tableau.get(pile).isEmpty()) {
			return false;
		}
		else
			return true;	
	}
	
	/**
	 * This method checks if the stock is empty to see if it can be removed from
	 * @return
	 */
	
public boolean removeStockTop() {
		
		if(x.deck.isEmpty()) {
			return false;
		}
		else
			return true;
		
		
	}
//	public static void main(String[] args) {
//		x.shuffle();
//		Golf.initializeTableau();
//		for(ArrayList<Card> x : tableau) {
//			for(Card  y: x) {
//				System.out.println(y.getRank() + y.getSuit());
//			}
//		}
//		System.out.println("stockpile size - " + x.deck.size());
//		Golf.tableauRemove(2);
//		Golf.tableauRemove(1);
//		Golf.tableauRemove(4);
//		Golf.stockRemove();
//		Golf.tableauRemove(1);
//		Golf.tableauRemove(0);
//		Golf.tableauRemove(3);
//		Golf.stockRemove();
//		Golf.tableauRemove(0);
//		Golf.tableauRemove(3);
//		System.out.println("stockpile size after remove- " + x.deck.size());
//		for(Card x : cell) System.out.println(x.getRank() + "  ");
//		for(ArrayList<Card> x : tableau) {
//			System.out.println(x);
///		}
//	}
}
