package code;

import java.util.ArrayList;
import java.util.Collections;

public class FortyThieves {

	public  ArrayList<Card> stockPile = new ArrayList<Card>();
	public  ArrayList<Card> waste = new ArrayList<Card>();
	public ArrayList<ArrayList<Card>> tableau = new ArrayList<ArrayList<Card>>();
	public ArrayList<ArrayList<Card>> homeCell = new ArrayList<ArrayList<Card>>();

	
	/**
	 * Method used to create the game's stock pile
	 */
	public  void createStock() {
		Deck x = new Deck();
		Deck y = new Deck();
		for(int i = 0; i < 52; i++) {
			stockPile.add(x.deck.get(i));
		}
		for(int i = 0; i < 52; i++) {
			stockPile.add(y.deck.get(i));
		}
	}
	
	/**
	 * Method that creates the game's home cell
	 */
	public void createHomecell() {
		ArrayList<Card> cell1 = new ArrayList<Card>();
		ArrayList<Card> cell2 = new ArrayList<Card>();
		ArrayList<Card> cell3 = new ArrayList<Card>();
		ArrayList<Card> cell4 = new ArrayList<Card>();
		ArrayList<Card> cell5 = new ArrayList<Card>();
		ArrayList<Card> cell6 = new ArrayList<Card>();
		ArrayList<Card> cell7 = new ArrayList<Card>();
		ArrayList<Card> cell8 = new ArrayList<Card>();
		homeCell.add(cell1);
		homeCell.add(cell2);
		homeCell.add(cell3);
		homeCell.add(cell4);
		homeCell.add(cell5);
		homeCell.add(cell6);
		homeCell.add(cell7);
		homeCell.add(cell8);
		int counter = 0;
		for(int i = 0; i < stockPile.size(); i++) {

			if(stockPile.get(i).getRank() == 1){
				homeCell.get(counter).add(stockPile.get(i));
				stockPile.remove(i);
				counter+=1;
			}	
		}
	}
	
	/**
	 * Method that creates the game's tableau pile
	 */
	public void createTableau() {
		ArrayList<Card> tab1 = new ArrayList<Card>();
		ArrayList<Card> tab2 = new ArrayList<Card>();
		ArrayList<Card> tab3 = new ArrayList<Card>();
		ArrayList<Card> tab4 = new ArrayList<Card>();
		ArrayList<Card> tab5 = new ArrayList<Card>();
		ArrayList<Card> tab6 = new ArrayList<Card>();
		ArrayList<Card> tab7 = new ArrayList<Card>();
		ArrayList<Card> tab8 = new ArrayList<Card>();
		ArrayList<Card> tab9 = new ArrayList<Card>();
		ArrayList<Card> tab10 = new ArrayList<Card>();
		ArrayList<Card> tab11 = new ArrayList<Card>();
		ArrayList<Card> tab12 = new ArrayList<Card>();
		ArrayList<Card> tab13 = new ArrayList<Card>();
		tableau.add(tab1);
		tableau.add(tab2);
		tableau.add(tab3);
		tableau.add(tab4);
		tableau.add(tab5);
		tableau.add(tab6);
		tableau.add(tab7);
		tableau.add(tab8);
		tableau.add(tab9);
		tableau.add(tab10);
		tableau.add(tab11);
		tableau.add(tab12);
		tableau.add(tab13);
		for(int j = 0; j < 13; j++) {
			for(int i = 0; i < 3; i++) {
				tableau.get(j).add(stockPile.get(0));
				stockPile.remove(0);


			}
		}


	}
	
	/**
	 * Method that checks if it's legal to remove a card from it's stockpile
	 * @return
	 */
	public boolean stockPileRemoveLegal() { 
		boolean x = false;
		if(stockPile.size() == 0) {
			x = false;
		}
		else {
			x = true;
		}	
		return x;
	}
	
	/**
	 * Method that checks if it it's legal to remove from the waste pile
	 * @return
	 */
	public boolean wasteRemoveLegal() { 
		return true;
	}
	
	/**
	 * Method that checks if it's legal to add onto the stockpile
	 * @return
	 */
	public boolean stockPileAddLegal() {
		return false; 
	}
	
	/**
	 * Checks if a card can legally be added onto the waste pile
	 * @return
	 */
	public boolean wasteAddLegal() {
		return false; 
	}
	
	/**
	 * Method that checks if the card in the stockpile can legally be removed
	 */
	public void stockPileRemove() {
		boolean x = stockPileRemoveLegal();
		if(x == true) {
			waste.add(0, stockPile.remove(0));
		} 
	}
	
	/**
	 * Method that checks if the card the user wants to move from the waste pile can be moved to the tableau of their chose
	 * @param pileAdd
	 */
	public void wasteRemoveToTab(int pileAdd) {
		
boolean x = tableauAddLegal(waste.get(0), pileAdd);
boolean y = wasteRemoveLegal(); 	
		if(x && y) {
			tableauAdd(waste.get(0), pileAdd);	
			waste.remove(0);
		}	
	}
	
	/**
	 * Method that checks if the card in the waste pile can legally be moved from the waste pile to the homecell
	 * @param pileAdd
	 */
	public void wasteRemoveToHome(int pileAdd) {
		
		boolean x = homeCellAddLegal(waste.get(0), homeCell.get(pileAdd).get(0));
		boolean y = wasteRemoveLegal(); 	
				if(x && y) {
					homeCellAdd(waste.get(0), pileAdd);	
					waste.remove(0);
				}	
			}
	
	/**
	 * Method to check if removing a card from its homecell tableau is legal
	 * @return
	 */
	public boolean homeCellRemoveLegal() {
		return false;
	}
	
	/**
	 * Method that checks if adding a card onto a homecell is legal
	 * @param move
	 * @param topOfCell
	 * @return
	 */
	public boolean homeCellAddLegal(Card move, Card topOfCell) {
		
		boolean x = false;
		 if(move.getRank() == topOfCell.getRank()+1 && move.getSuit() .equals(topOfCell.getSuit())) {
			x = true;
		}
		else {
			x = false;
		}	
		return x;
		
	}
	
	/**
	 * Method used to allow a card to added onto a homecell
	 * @param move
	 * @param pile
	 */
	public void homeCellAdd(Card move, int pile) {
		
		boolean x = homeCellAddLegal(move, homeCell.get(pile).get(0));
	
		if(x == true) {
			
			homeCell.get(pile).add(0, move);
			
		}	
		
	}
	
	/**
	 * Method that checks if a card is legal to remove it from its tableau
	 * @param pile
	 * @return
	 */
	public boolean tableauRemoveLegal(int pile) {
		boolean x = true;
		if(tableau.get(pile).size() == 0) {
			x = false;
		}
		return x;
		
	}
	
	/**
	 * Method that checks if it's legal for a card to be added onto the tableau the user chooses to add that card onto
	 * @param Remove
	 * @param pileAdd
	 * @return
	 */
	public boolean tableauAddLegal(Card Remove, int pileAdd) {
		boolean x = false;
		if(tableau.get(pileAdd).size() == 0) {
			x = true;
		}
		else if((Remove.getRank() == tableau.get(pileAdd).get(0).getRank() - 1) && (Remove.getSuit().equals(tableau.get(pileAdd).get(0).getSuit()))) {
			x = true;
		}
		return x;
		
	}
	
	/**
	 * Method that allows the user to add a card onto the tableau of their choice
	 * @param Remove
	 * @param pileAdd
	 */
	public void tableauAdd(Card Remove, int pileAdd) {
		boolean x = tableauAddLegal(Remove, pileAdd);
		
		if(x == true) {
			
			tableau.get(pileAdd).add(0,Remove);
			
		}	
		
	}
		
	/**
	 * Method that allows a card to be removed from its tableau to a homecell
	 * @param pileTabRemove
	 * @param pileHomeAdd
	 */
	public void tableauRemovetoHome(int pileTabRemove, int pileHomeAdd) {
		boolean x = tableauRemoveLegal(pileTabRemove);

		boolean y = homeCellAddLegal(tableau.get(pileTabRemove).get(0), homeCell.get(pileHomeAdd).get(0));
		
		

			if(x && y) {
				homeCellAdd(tableau.get(pileTabRemove).get(0), pileHomeAdd);
				tableau.get(pileTabRemove).remove(0);
			}
		
	}
	
	/**
	 * Method that allows a card to be removed from one Tableau onto another
	 * @param pileTabRemove
	 * @param pileTabAdd
	 */
	public void tableauRemovetoTab(int pileTabRemove, int pileTabAdd) {
		boolean x = tableauRemoveLegal(pileTabRemove);
		boolean z = tableauAddLegal(tableau.get(pileTabRemove).get(0), pileTabAdd);
	
		if(x && z) {
		tableauAdd(tableau.get(pileTabRemove).get(0), pileTabAdd);	
		tableau.get(pileTabRemove).remove(0);	
		
	}
	}
	
	
//	public static void main(String[] args) {
//
//		FortyThieves x = new FortyThieves();
//		x.createStock();
//		x.createHomecell();
//		Collections.shuffle(x.stockPile);
//		x.createTableau();
//		for(int i = 0; i < 13; i++) {
//			System.out.println(x.tableau.get(i).size());
//		}
//
//	}

}
