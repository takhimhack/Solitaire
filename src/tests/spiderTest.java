package tests;

import static org.junit.Assert.assertEquals;  
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.Card;
import code.LittleSpider;

public class spiderTest {

	//Test Tableau starts with  piles
	//tableau test if adding a specific card is legal
	//tableau test if removing top card is legal
	//adding card increases the number of that piles card and the added card is the new top card
	//removing a card decreases a card and the next card in the pile is the new top card\
	//homecell initially holds one card
	//adding a card to homecell is legal or illegal
	//if removing a card from homcell is legal or illegal
	
	@Test
	public void tableauPile() {
		LittleSpider test = new LittleSpider();
		test.x.shuffle();
		test.initializePiles();
		test.initializeTableau();
		assertEquals("number of cards in pile 1", 6, test.piles.get(0).size());
		assertEquals("number of cards in pile 2", 6, test.piles.get(1).size());
		assertEquals("number of cards in pile 3", 6, test.piles.get(2).size());
		assertEquals("number of cards in pile 4", 6, test.piles.get(3).size());
		assertEquals("number of cards in pile 5", 6, test.piles.get(4).size());
		assertEquals("number of cards in pile 6", 6, test.piles.get(5).size());
		assertEquals("number of cards in pile 7", 6, test.piles.get(6).size());
		assertEquals("number of cards in pile 8", 6, test.piles.get(7).size());
	}
	
	@Test
	public void tableauAdd() {
		LittleSpider test = new LittleSpider();
		test.x.shuffle();
		test.initializePiles();
		Card moved1 = new Card(5,"H","5h");
		Card current1 = new Card(6,"C","6c");
		assertTrue(test.tableauAdd(moved1, current1, 0));
		Card moved2 = new Card(7,"H","7h");
		Card current2 = new Card(6,"C","6c");
		assertTrue(test.tableauAdd(moved2, current2, 0));
		Card moved3 = new Card(13,"H","13h");
		Card current3 = new Card(1,"C","1c");
		assertTrue(test.tableauAdd(moved3, current3, 0));
		Card moved4 = new Card(1,"H","1h");
		Card current4 = new Card(13,"C","13c");
		assertTrue(test.tableauAdd(moved4, current4, 0));
	}
	
	@Test
	public void tableauRemove() {
		LittleSpider test = new LittleSpider();
		test.initializePiles();
		test.initializeTableau();
		assertTrue(test.tableauRemove(0));
		test.piles.get(0).remove(0);
		test.piles.get(0).remove(0);
		test.piles.get(0).remove(0);
		test.piles.get(0).remove(0);
		test.piles.get(0).remove(0);
		test.piles.get(0).remove(0);
		assertFalse(test.tableauRemove(0));
	}
	
	
	//Adding card to Little Spider's tableau pile increases its 
	//    number of cards and results in that card being the tableau pile's new top card
	@Test
	public void tableauAddIncrement() {   
		LittleSpider test = new LittleSpider();
		test.initializePiles();
		test.initializeTableau();
		Card y = new Card(3,"D","3d");
		test.tableauAdd(y, test.piles.get(0).get(0), 0);
		assertEquals("number of cards in pile 1", 7 , test.piles.get(0).size());
		assertSame(y, test.piles.get(0).get(0));
	}
	
	//Removing card from Little Spider's tableau pile decreases 
	//   its number of cards  and results in following card being the new top card
	@Test
	public void tableauRemoveDecrement() {
		LittleSpider test = new LittleSpider();
		test.initializePiles();
		test.initializeTableau();
		Card y = test.piles.get(0).get(1);
		test.remove(0, 8);
		assertEquals("number of cards in pile 1", 5 , test.piles.get(0).size());
		assertSame(y, test.piles.get(0).get(0));
	}
	
	//Homecell piles in Little Spider initially hold 1 card 
	@Test
	public void initialCell() {
		LittleSpider test = new LittleSpider();
		test.x.shuffle();
		test.initializePiles();
		assertEquals("number of cards in cell 1", 1, test.piles.get(8).size());
		assertEquals("number of cards in cell 2", 1, test.piles.get(9).size());
		assertEquals("number of cards in cell 3", 1, test.piles.get(10).size());
		assertEquals("number of cards in cell 4", 1, test.piles.get(11).size());
	}
	
	//Little Spider homecell piles correctly determines if adding a specific card is legal or illegal
	@Test
	public void cellAddLeaglity() {
		LittleSpider test = new LittleSpider();
		test.initializePiles();
		Card moved1 = new Card(2,"D","2d");   
		assertTrue("Cell 1 Test", test.cellAdd(moved1, test.piles.get(8).get(0)));
		Card moved2 = new Card(2,"H","2h");
		assertTrue("Cell 2 Test",test.cellAdd(moved2, test.piles.get(9).get(0)));
		Card moved3 = new Card(12,"C","12c");
		assertTrue("Cell 3 Test", test.cellAdd(moved3, test.piles.get(10).get(0)));
		Card moved4 = new Card(12,"S","12s");
		assertTrue("Cell 4 Test", test.cellAdd(moved4, test.piles.get(11).get(0)));
		Card moved5 = new Card(3,"H","3h");
		assertFalse("Cell 5 Test",test.cellAdd(moved5, test.piles.get(8).get(0)));
	}
	/*Little Spider homecell pile correctly returns if 
	   removing top card is legal or illegal (e.g., if the homecell pile has 2 or more cards)*/
	@Test
	public void cellRemoveTop() {
		LittleSpider test = new LittleSpider();
		test.initializePiles();
		assertFalse(test.cellRemove(8));
		Card card = new Card(2, "D","2d");
		test.cellAdd(card, test.piles.get(8).get(0));
		assertTrue(test.cellRemove(8));
	}
	/*Adding card to Little Spider's homecell pile
	    increases its number of cards and results in that card being the homecell pile's new top card */
	
	@Test	
    public void cellAddIncrement() {   
		LittleSpider test = new LittleSpider();
		test.initializePiles();
		Card y = new Card(2,"D","2d");
		test.cellAdd(y, test.piles.get(8).get(0));
		assertEquals("number of cards in cell 1", 2 , test.piles.get(8).size());
		assertSame(y, test.piles.get(8).get(0));
	}

	   /* Removing card from one of Little Spider's homecell pile decreases its 
	        number of cards and results in following card being the new top card*/
    @Test
    public void cellRemoveDecrement() {
			LittleSpider test = new LittleSpider();
			test.initializePiles();
			test.initializeTableau();
			Card y = test.piles.get(0).get(0);
			Card initial = test.piles.get(8).get(0);
			test.remove(0, 8);
			assertEquals("number of cards in cell 1", 2 , test.piles.get(8).size());
			assertSame(y, test.piles.get(8).get(0));
			test.remove(8, 0);
			assertEquals("number of cards in pile 1 after remove", 6 , test.piles.get(0).size());
			assertEquals("number of cards in cell 1 after remove", 1 , test.piles.get(8).size());
			assertSame(initial, test.piles.get(8).get(0));
		}

}

