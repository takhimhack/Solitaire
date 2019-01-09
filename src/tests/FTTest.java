package tests;

import static org.junit.Assert.assertEquals; 

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.FortyThieves;
import code.Card;

public class FTTest {
	
	@Test
	public void tableauPile() {
		FortyThieves test = new FortyThieves();
		test.createStock();
		test.createTableau();
		assertEquals("number of piles ", 13 , test.tableau.size()); 
		assertEquals("number of cards in pile 1", 3 , test.tableau.get(0).size());
		assertEquals("number of cards in pile 2", 3, test.tableau.get(1).size());
		assertEquals("number of cards in pile 3", 3, test.tableau.get(2).size());
		assertEquals("number of cards in pile 4", 3, test.tableau.get(3).size());
		assertEquals("number of cards in pile 5", 3, test.tableau.get(4).size());
		assertEquals("number of cards in pile 6", 3, test.tableau.get(5).size());
		assertEquals("number of cards in pile 7", 3, test.tableau.get(6).size());
		assertEquals("number of cards in pile 8", 3, test.tableau.get(7).size());
		assertEquals("number of cards in pile 9", 3, test.tableau.get(8).size());
		assertEquals("number of cards in pile 10", 3, test.tableau.get(9).size());
		assertEquals("number of cards in pile 11", 3, test.tableau.get(10).size());
		assertEquals("number of cards in pile 12", 3, test.tableau.get(11).size());
		assertEquals("number of cards in pile 13", 3, test.tableau.get(12).size());		
	}
	
	@Test
	public void tableauAddLegal() {
		FortyThieves test = new FortyThieves();
		test.createStock();
		test.createTableau();
		Card remove1 = new Card(5,"H","5h");
		assertTrue(test.tableauAddLegal(remove1, 6));
		Card remove2 = new Card(7,"H","7h");
		assertFalse(test.tableauAddLegal(remove1, 8));	
	}
	
	
	@Test 
	public void tableauRemoveLegal() {
		FortyThieves test = new FortyThieves();
		test.createStock();
		test.createHomecell();
		test.createTableau();
		assertTrue(test.tableauRemoveLegal(0));
		assertTrue(test.tableauRemoveLegal(1));
		 test.tableau.get(0).remove(0);
		 test.tableau.get(0).remove(0);
		 test.tableau.get(0).remove(0);
	   assertFalse(test.tableauRemoveLegal(0));
	

	}
	
		
	
	@Test 
	public void tableauRemoveDecrement() {
		FortyThieves test = new FortyThieves();
		test.createStock();
		test.createHomecell();
		test.createTableau();
		Card y = test.tableau.get(0).get(1);
		assertEquals("number of cards in pile 1", 3, test.tableau.get(0).size());
        test.tableauRemovetoHome(0, 4);
		assertEquals("number of cards in pile 1", 2 , test.tableau.get(0).size());
		assertSame(y, test.tableau.get(0).get(0));
	}
	
	@Test
	public void tableauAddIncrement() {
		FortyThieves test = new FortyThieves();
		test.createStock();
		test.createTableau();
		Card remove1 = new Card(5,"H","5h");
		test.tableauAdd(remove1, 6);
		assertEquals("number of cards in pile 7", 4 , test.tableau.get(6).size());
		assertSame(remove1, test.tableau.get(6).get(0));
   
	}
	
	
	@Test
	public void HomeCellPile() { // ask about the order of creation of homecell and Tableau
		FortyThieves test = new FortyThieves();
		test.createStock();
		test.createHomecell();
		test.createTableau();
		assertEquals("number of piles ", 8 , test.homeCell.size()); 
		assertEquals("number of cards in cell 1", 1, test.homeCell.get(0).size());
		assertEquals("number of cards in cell 2", 1, test.homeCell.get(1).size());
		assertEquals("number of cards in cell 3", 1, test.homeCell.get(2).size());
		assertEquals("number of cards in cell 4", 1, test.homeCell.get(3).size());
		assertEquals("number of cards in cell 5", 1, test.homeCell.get(4).size());
		assertEquals("number of cards in cell 6", 1, test.homeCell.get(5).size());
		assertEquals("number of cards in cell 7", 1, test.homeCell.get(6).size());
		assertEquals("number of cards in cell 8", 1, test.homeCell.get(7).size());


	}
	
	@Test
	public void homeCellRemoveLegal() {
		FortyThieves test = new FortyThieves();
		test.createStock();
		test.createHomecell();
		test.createTableau();
		assertFalse("It is not legal to remove a card from the Homecell Pile", test.homeCellRemoveLegal()); 

	}
	
	
	@Test
	public void HomecellAddLeaglity() {
		FortyThieves test = new FortyThieves();
		test.createStock();
		test.createHomecell();
		Card a = new Card(2,"D","2d");   
		assertTrue("Cell 1 Test", test.homeCellAddLegal(a, test.homeCell.get(0).get(0)));
		Card b = new Card(2,"H","2h");   
		assertTrue("Cell 2 Test", test.homeCellAddLegal(b, test.homeCell.get(1).get(0)));
		Card c = new Card(2,"C","2c");
	    assertTrue("Cell 3 Test", test.homeCellAddLegal(c, test.homeCell.get(2).get(0)));
		Card d = new Card(2,"S","5s");
		assertTrue("Cell 4 Test", test.homeCellAddLegal(d, test.homeCell.get(3).get(0)));
		Card e = new Card(2,"D","2d");   
		assertTrue("Cell 5 Test", test.homeCellAddLegal(e, test.homeCell.get(4).get(0)));
		Card f = new Card(2,"H","2h");   
		assertTrue("Cell 6 Test", test.homeCellAddLegal(f, test.homeCell.get(5).get(0)));	
		Card g = new Card(2,"C","2c");
		assertTrue("Cell 7 Test", test.homeCellAddLegal(g, test.homeCell.get(6).get(0)));	
		Card h = new Card(2,"S","2s");
		assertTrue("Cell 8 Test", test.homeCellAddLegal(h, test.homeCell.get(7).get(0)));
	}
	
	@Test
	public void homecellAddIncrement() {
		FortyThieves test = new FortyThieves();
		test.createStock();
		test.createHomecell();
		test.createTableau();
		Card remove1 = new Card(2,"S","2s");
		test.homeCellAdd(remove1, 7);
	    assertEquals("number of cards in pile 7", 2 , test.homeCell.get(7).size());
		assertSame(remove1, test.homeCell.get(7).get(0)); 
	}
	
	@Test
	public void StockPile() { // ask how to check number of piles in stock
		FortyThieves test = new FortyThieves();
		test.createStock();
		test.createHomecell();
		test.createTableau();		
		assertEquals("Number of cards in the stockpile", 57, test.stockPile.size());
	}	
	
	@Test
	public void StockPileAddLeaglity() {
		FortyThieves test = new FortyThieves();
		test.createStock();
		assertFalse(test.stockPileAddLegal());
	}

	@Test
	public void StockPileRemoveLegality() {
		FortyThieves test = new FortyThieves();
		test.createStock();
		test.createHomecell();
		test.createTableau();			
		assertTrue(test.stockPileRemoveLegal());
		assertEquals("number of cards in stockPile", 57 , test.stockPile.size());
		test.stockPile.clear();
 		assertFalse(test.stockPileRemoveLegal());

	}

	
	
	@Test
	public void DealingStockPileRemove() {
		FortyThieves test = new FortyThieves();
		test.createStock();
		test.createHomecell();
		test.createTableau();
		Card x = test.stockPile.get(0);
		Card y = test.stockPile.get(1); 
		test.stockPileRemove(); 
		assertEquals("number of cards in stockPile", 56 , test.stockPile.size());
		assertSame(y, test.stockPile.get(0));
		assertSame(x, test.waste.get(0));

	}
	
	@Test
	public void WastePile() {
		FortyThieves test = new FortyThieves();
		assertEquals("Number of cards in the Waste Pile", 0, test.waste.size());
	}	
	@Test
	public void WastePileAddlegality() {
		FortyThieves test = new FortyThieves();
		assertFalse(test.wasteAddLegal());
	}
	
	@Test
	public void WastePileRemovelegality() {
		FortyThieves test = new FortyThieves();
		assertTrue(test.wasteRemoveLegal());
		
	}
	
	@Test
	public void WastePileDecrement() {
		FortyThieves test = new FortyThieves();
		test.createStock();
		test.createHomecell();
		test.createTableau();
		assertEquals("number of cards in wastepile", 0, test.waste.size());
		Card remove1 = new Card(5,"S","5h");
        test.waste.add(remove1);
		test.wasteRemoveToHome(1);
		assertEquals("number of cards in wastepile", 1, test.waste.size());
	    assertSame(remove1, test.waste.get(0));

	}
	

}
