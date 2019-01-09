package tests;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.Card;
import code.Golf;

public class GolfTest {

	@Test
	public void tableauPile() {
		Golf test = new Golf();
		test.x.shuffle();
		test.initializeTableau();
		assertEquals("number of piles ", 7 , test.tableau.size()); 
		assertEquals("number of cards in pile 1", 5 , test.tableau.get(0).size());
		assertEquals("number of cards in pile 2", 5, test.tableau.get(1).size());
		assertEquals("number of cards in pile 3", 5, test.tableau.get(2).size());
		assertEquals("number of cards in pile 4", 5, test.tableau.get(3).size());
		assertEquals("number of cards in pile 5", 5, test.tableau.get(4).size());
		assertEquals("number of cards in pile 6", 5, test.tableau.get(5).size());
		assertEquals("number of cards in pile 7", 5, test.tableau.get(6).size());
	}
	
	@Test
	public void tableauAddLegal() {
		Golf test = new Golf();
		test.x.shuffle();
		test.initializeTableau();
		assertFalse("It is not legal to add a card to the Tableau Pile", test.tableauAdd()); 
	}
	
	@Test
	public void tableauRemoveLegal() {   //legality of card removal (its a void method)
		Golf test = new Golf();
		test.x.shuffle();
		test.initializeTableau();
		
		assertTrue("It is not legal to remove a card from the Homecell Pile", test.removeTabTop(0)); 
	}
	
	@Test
	public void tableauRemove() {
		Golf test = new Golf(); 
		test.x.shuffle();
		test.initializeTableau();
		Card x = test.tableau.get(0).get(1);
		test.tableauRemove(0);
		assertEquals("number of cards in pile 1", 4 , test.tableau.get(0).size());
		assertSame(x, test.tableau.get(0).get(0));
	}
	
	@Test
	public void cellInitial() {
		Golf test = new Golf();
		test.x.shuffle();
		test.initializeTableau();
		assertEquals(0, test.cell.size());
	}
	
	@Test
	public void cellLegal() {                     //ask hertz
		Golf test = new Golf();
		test.x.shuffle();
		test.initializeTableau();
		assertTrue(test.homeCell(test.tableau.get(2).get(0)));
		assertTrue(test.homeCell(test.x.deck.get(0)));
	}
	
	@Test
	public void cellRemoveLegal() {
		Golf test = new Golf();
		test.x.shuffle();
		test.initializeTableau();
		
		assertFalse("It is not legal to remove a card from the Homecell Pile", test.homecellRemove()); 
	}
	@Test
	public void cellAdd() {                     //ask hertz
		Golf test = new Golf();
		test.x.shuffle();
		test.initializeTableau();
		Card x = test.tableau.get(0).get(0);
		test.tableauRemove(0);
		assertEquals("number of cards in Homcell is 1", 1 , test.cell.size());
		assertSame(x, test.cell.get(0));
	}
	@Test
	public void stockInitial() {
		Golf test = new Golf();
		test.x.shuffle();
		test.initializeTableau();

		assertEquals(17, test.x.deck.size());
		
	}
	@Test
	public void stockAddLegal() {
		Golf test = new Golf();
		test.x.shuffle();
		test.initializeTableau();
		assertFalse("It is not legal to remove a card from the Homecell Pile", test.stockpileAdd()); 
	}
	@Test
	public void stockRemoveLegal() {
		Golf test = new Golf();
		test.x.shuffle();
		test.initializeTableau();
		assertTrue("It is not legal to remove a card from the Homecell Pile", test.removeStockTop()); 

	}
	@Test
	public void stockRemove() {
		Golf test = new Golf(); 
		test.x.shuffle();
		test.initializeTableau();
		Card x = test.x.deck.get(1);
		test.stockRemove();
		assertEquals("number of cards in stockPile", 16 , test.x.deck.size());
		assertSame(x, test.x.deck.get(0));
	}
	
}
