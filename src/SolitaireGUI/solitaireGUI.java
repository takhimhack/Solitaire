package SolitaireGUI;
import java.awt.*;     
import javax.swing.*;
import javax.swing.border.MatteBorder;

import code.Card;
import code.Deck;
import code.FortyThieves;
import code.Golf;
import code.LittleSpider;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class solitaireGUI extends JFrame{

	private HashMap<Card, JLabel> map1  = new HashMap<>(); //card to label
	private HashMap<JLabel, Card> map2  = new HashMap<>(); //label to card
	private JLabel selected = null; //reference to the selected card
	private ArrayList<JLabel> cardLabels; //List of all JLabels with card image
	private ArrayList<String> cardNames;
	private ArrayList<JLabel> eEgg; //Easter Egg labels + cell/stack
	private ArrayList<JLabel> FTcells; //homeCells for FT
	private ArrayList<JLabel> FTemptyT; //empty tableau labels for FT
	private int FTFrom; // -1 _ waste / 0-12 _ tableau Piles
	private JFrame frame; // Main Frame
	private Golf golf;
	private LittleSpider spider;
	private FortyThieves FT;
	private Deck deckClass; 
	private static final javax.swing.border.Border UNSELECTED_BORDER = BorderFactory.createEmptyBorder(5, 5, 5, 5);
	private static final MatteBorder SELECTED_BORDER = BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK);
	/**
	 * Composition of all the needed class.
	 *Sets the GUI layout for the entire program.
	 */

	public solitaireGUI() {
		frame = new JFrame();
		frame.setJMenuBar(mainMenu());
        frame.getContentPane().setBackground(Color.decode( "#16613D" ));
		frame.pack();
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(0, 0);
		frame.setSize(screensize.width, screensize.height);
		deckClass = new Deck();
		cardLabels = new ArrayList<JLabel>();
		cardNames = new ArrayList<String>();
		eEgg = new ArrayList<JLabel>();
		FTcells = new ArrayList<JLabel>();
		FTemptyT = new ArrayList<JLabel>();
		frame.setLayout(null);
		showCards();
		frame.revalidate();
	}

	// Return frame for main method
	public JFrame getFrame() {
		return frame;
	}
	/*This method is creating the menu  which includes the New Game menu with
	sub menu items to start a game of Golf, start a game of Little Spider, and Quit*/


	public JMenuBar mainMenu() {
		JMenuBar newGame = new JMenuBar();
		ActionHandler listener = new ActionHandler();
		JMenu newGameSelect = new JMenu("New Game");
		newGame.add(Box.createHorizontalGlue());
		subMenu(newGameSelect,"New Game of Golf",listener);
		subMenu(newGameSelect,"New Game of Little Spider",listener);
		subMenu(newGameSelect,"New Game of FortyThieves",listener);
		subMenu(newGameSelect,"Quit Game",listener);
		newGame.add(newGameSelect);
		return newGame;		
	}
	// This method is adding a submenu and it's checking if any of the items in menu has been clicked 

	public static void subMenu(JMenu jmenuVar, String str, ActionListener listener) {
		JMenuItem menuItem = new JMenuItem(str);
		menuItem.addActionListener(listener);
		jmenuVar.add(menuItem);
	}
	// set image for JLabel
	public JLabel createDisplayImage(String fileNameRelativeToClassFile) {
		String path = "/S18SemesterProject/src/SolitaireGUI";
		JLabel retVal = new JLabel();
		java.net.URL imgURL = this.getClass().getResource(fileNameRelativeToClassFile);
		if (imgURL == null) {
			throw new IllegalArgumentException("Couldn't find file: " + path);
		}
		ImageIcon cardImage = new ImageIcon(imgURL);    
		retVal.setIcon(cardImage);
		Dimension d = new Dimension(cardImage.getIconWidth() + 10, cardImage.getIconHeight() + 10);
		retVal.setSize(d);
		retVal.setPreferredSize(d);
		retVal.setMaximumSize(d);
		retVal.setMinimumSize(d);
		return retVal;
	}
	 // Highlights the card that was selected with black border

	public static void select(JLabel label) {
		label.setBorder(SELECTED_BORDER);
		label.repaint();
	}
	 // Unhighlights the card which was previously selected 

	public static void unselect(JLabel label) {
		label.setBorder(UNSELECTED_BORDER);
		label.repaint();
	}

    // This method is displaying all the 52 cards at the beginning of the game 

	public void showCards() {
		int X = 0;
		int Y = 0;
		for(int i = 0; i < 52; i++) {
			JLabel cardLabel = createDisplayImage(deckClass.deck.get(i).getImg());
			cardLabel.setLocation(X, Y);
			frame.add(cardLabel);
			//fixes positioning bug from selecting during the game
			select(cardLabel);
			unselect(cardLabel);
			cardLabel.setName(deckClass.deck.get(i).getImg());
			cardLabels.add(cardLabel);
			cardNames.add(deckClass.deck.get(i).getImg());
			//EasterEgg
			if(i == 37) {
				cardLabel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(e.isAltDown()) {
							int x = 0;
							int y = 0;
							for(int i = 0; i < 30; i++) {
								JLabel hertz = createDisplayImage("hertz.png");
					     		frame.add(hertz);
								hertz.setLocation(x,y);
								frame.setComponentZOrder(hertz, 0);
								eEgg.add(hertz);
								x+= 150;
								if(i == 4 || i == 9 || i == 14 || i == 19 || i == 24) {
									y+=150;
									x = 0;
								}
							}
						}
					}
				});
			}
			if(i == 38) {
				cardLabel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(e.isShiftDown()) {
							JLabel hertz2 = createDisplayImage("hertz 2.png");
							frame.add(hertz2);
							eEgg.add(hertz2);
							hertz2.setLocation(0,0);
							frame.setComponentZOrder(hertz2, 0);
							frame.revalidate();
						}
					}
				});
			}
			if(i == 39) {
				cardLabel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(e.isControlDown()) {

							JLabel butcher = createDisplayImage("butcherhertz.jpg");
				
							frame.add(butcher);
							eEgg.add(butcher);
							butcher.setLocation(0,0);
							frame.setComponentZOrder(butcher, 0);
							frame.revalidate();
						}
					}
				});
			}
			
			X+= 100;
			if(i == 7 || i == 15 || i == 23 || i == 31 || i == 39 || i == 47) {
				Y+=100;
				X = 0;
			}
		}
		JLabel z = new JLabel();
		frame.add(z);
	}
	////////////////////////////////////////////////////  FT
	public void FTSetUpLables() {
		for(int i = 0; i < FT.stockPile.size(); i++) {
			JLabel cardLabel = createDisplayImage(FT.stockPile.get(i).getImg());
			cardLabel.setLocation(0, 0);
			frame.add(cardLabel);
			//fixes positioning bug from selecting during the game
			select(cardLabel);
			unselect(cardLabel);
			cardLabel.setName(FT.stockPile.get(i).getImg());
			map1.put(FT.stockPile.get(i), cardLabel);
			map2.put(cardLabel, FT.stockPile.get(i));
			cardLabels.add(cardLabel);
			cardNames.add(FT.stockPile.get(i).getImg());
			addCardListenerFT(cardLabel);
		}
		JLabel z = new JLabel();
		frame.add(z);
	}
	
	public void FTGameSetUp() {
		int X = 50;
		//set up tableau piles
		for(int i = 0;i < 13;i++) {
			int Y = 300;
			for(int j = 0;j < 3; j++) {
				map1.get(FT.tableau.get(i).get(j)).setLocation(X, Y);
				frame.setComponentZOrder(map1.get(FT.tableau.get(i).get(j)), j);
				Y-=25;
			}
			X+=100;
		}
		//set up home cell piles
		X = 300;
		for(int i = 0; i < 8; i++) {
			JLabel cell =  new JLabel();
			cell.setBounds(X, 100, 76, 100);
			select(cell);
			unselect(cell);
			frame.add(cell);
			eEgg.add(cell);
			frame.setComponentZOrder(cell, 0);
			map1.get(FT.homeCell.get(i).get(0)).setLocation(cell.getLocation());
			frame.setComponentZOrder(map1.get(FT.homeCell.get(i).get(0)), 1);
			FTcells.add(cell);
		    addCellListenerFT(cell);
		    X+=100;
		}
		//set up empty labels
		FTemptyTSetUp();
		//set up stock and waste
		JLabel stock = createDisplayImage("back.png");
		stock.setLocation(50, 100);
		JLabel waste =  new JLabel();
		waste.setBounds(155, 105, 76, 100);
		select(waste);
		frame.add(stock);
		frame.add(waste);
		eEgg.add(waste);
		eEgg.add(stock);
		frame.setComponentZOrder(stock, 0);
		frame.setComponentZOrder(waste, 0);
		// add stock listener
		stock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(selected == null) {
					map1.get(FT.stockPile.get(0)).setLocation(waste.getLocation());
					map1.get(FT.stockPile.get(0)).setVisible(true);
					map1.get(FT.stockPile.get(0)).setEnabled(true);
					FT.stockPileRemove();
					//disable stock if empty
					if(!FT.stockPileRemoveLegal()) {
						stock.setVisible(false);
						stock.setEnabled(false);
					}
					//unselect waste if it was empty before
					if(FT.waste.size() > 0) unselect(waste);
					//set up Z order in waste
					for(int i = 0; i < FT.waste.size();i++) {
						frame.setComponentZOrder(map1.get(FT.waste.get(i)), i+1);
					}
					frame.setComponentZOrder(waste, 0);
					JLabel empty = new JLabel();
					empty.setBounds(0, 500, 0, 0);
					frame.add(empty);
					frame.setComponentZOrder(empty, 0);
					frame.revalidate();
					
				}else {
					JOptionPane.showMessageDialog( null, "Can not move a card on to the stack" );
					unselect(selected);
					selected = null;
				}
			}
		});
		
		
		
		
		waste.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(selected == null) {
					if(FT.waste.size() != 0) {
						selected = map1.get(FT.waste.get(0));
						select(selected);
						FTFrom = -1;
					}
				}else if(selected != null) {
					if(selected != map1.get(FT.waste.get(0))){
					JOptionPane.showMessageDialog( null, "Can not add selected card to waste" );
				}
					unselect(selected);
					selected = null;
					
				}
			}
		});
		
		//Hide all the cards from stockpile
		for(int i = 0; i < FT.stockPile.size();i++) {
			map1.get(FT.stockPile.get(i)).setVisible(false);
			map1.get(FT.stockPile.get(i)).setEnabled(false);
		}
		// fixes positioning bug caused by Zorder
		JLabel empty = new JLabel();
		empty.setBounds(0, 500, 0, 0);
		frame.add(empty);
		frame.setComponentZOrder(empty, 0);
		frame.revalidate();
	}
	
	// adds listener to passed JLabel (for FT home cells)
	public void addCellListenerFT(JLabel label) {
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(selected == null) {
					JOptionPane.showMessageDialog( null, "Can not remove a card from home cell" );
				}else if(selected != null) {
					//get cell pile
					int cellPile = getCellPileFT(label);
					//check if legal move
					if(!FT.homeCellAddLegal(map2.get(selected),FT.homeCell.get(cellPile).get(0))) {
						JOptionPane.showMessageDialog( null, "Illegal move" );
						unselect(selected);
						selected = null;
					}else {
						//move card to cell
						selected.setLocation(FTcells.get(cellPile).getLocation());
						frame.setComponentZOrder(selected, 1);
						//check if card is coming from Tableau or waste
						if(FTFrom >= 0 && FTFrom <= 12) {  // from tableau
							FT.tableauRemovetoHome(FTFrom, cellPile);
						}else FT.wasteRemoveToHome(cellPile); //from waste
						//check if tableau where card was moved from is now empty and add label if it is
						//make previous card invisible
						if(FT.homeCell.get(0).size() > 1) map1.get(FT.homeCell.get(0).get(1)).setVisible(false);
						unselect(selected);
						selected = null;
						// fixes positioning bug caused by Zorder
						JLabel empty = new JLabel();
						empty.setBounds(0, 500, 0, 0);
						frame.add(empty);
						frame.setComponentZOrder(empty, 0);
						frame.revalidate();
					}
					frame.setComponentZOrder(FTcells.get(cellPile), 0);
				}
			}
		});
	}
	
	//Listener for cards
	public void addCardListenerFT(JLabel label) {
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int tabPile = FTTabPile(label);
				if(selected == null) {
					if(FTIsTop(label)) {
						selected = label;
						select(selected);
						FTFrom = tabPile;
					}
				}else if(selected != null) {
					if(selected != label){
						//check if legal move
						if(FTIsTop(label)) {
							if(!FT.tableauAddLegal(map2.get(selected),tabPile)) {
								JOptionPane.showMessageDialog( null, "Illegal move" );
							}else {
								//move card
								selected.setLocation(map1.get(FT.tableau.get(tabPile).get(0)).getX(),map1.get(FT.tableau.get(tabPile).get(0)).getY() + 25);
								//check if card is coming from Tableau or waste
								if(FTFrom >= 0 && FTFrom <= 12) {  // from tableau
									FT.tableauRemovetoTab(FTFrom, tabPile);
								}else FT.wasteRemoveToTab(tabPile); //from waste
								// fixes positioning bug caused by Zorder
								JLabel empty = new JLabel();
								empty.setBounds(0, 500, 0, 0);
								frame.add(empty);
								frame.setComponentZOrder(empty, 0);
								frame.revalidate();
								//set Z order
								for(int i = 0; i < FT.tableau.get(tabPile).size();i++) {
									frame.setComponentZOrder(map1.get(FT.tableau.get(tabPile).get(i)), i+1);
								}
							}
						}

					}

					unselect(selected);
					selected = null;
				}
			}
		});
	}
	
	//empty tableau setup
	public void FTemptyTSetUp() {
		int X = 50;
		int Y = 250;
		for(int i = 0; i < 13;i++) {
			JLabel emptyT =  new JLabel();
			emptyT.setBounds(X, Y, 76, 100);
			frame.add(emptyT);
			FTemptyT.add(emptyT);
			frame.setComponentZOrder(emptyT, frame.getComponentZOrder(map1.get(FT.tableau.get(i).get(FT.tableau.get(i).size()-1)))+1);
			emptyT.setName(""+i);
			emptyT.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println(emptyT.getName());
					if(selected != null) {
						selected.setLocation(emptyT.getLocation());
						if(FTFrom != -1) {
							FT.tableauRemovetoTab(FTFrom, Integer.parseInt(emptyT.getName()));
						}else FT.wasteRemoveToTab(Integer.parseInt(emptyT.getName()));
						unselect(selected);
						selected = null;
						unselect(emptyT);
						// fixes positioning bug caused by Zorder
						JLabel empty = new JLabel();
						empty.setBounds(0, 500, 0, 0);
						frame.add(empty);
						frame.setComponentZOrder(empty, 0);
						frame.revalidate();
					}
				}
			});
			X+=100;
		}
	}
	// for CellListener - returns the cell pile number that was clicked on
	public int getCellPileFT(JLabel label) {
		for(int i = 0; i < FTcells.size();i++) {
			if(label == FTcells.get(i) || label .equals(FTcells.get(i))) return i;
		}
		return -1; // not found
	}
	
	// for CardListener - checks if label is the top card in one of the tableau piles
	public boolean FTIsTop(JLabel label) {
		for(int i = 0;i < 13;i++) {
			if(!FT.tableau.get(i).isEmpty()) {
			if(label == map1.get(FT.tableau.get(i).get(0)) || label.equals(map1.get(FT.tableau.get(i).get(0)))) return true;
			}
			}
		return false;
	}
	
	// for CardListner - returns which tableau Pile the card is getting selected
	public int FTTabPile(JLabel label) {
		for(int i = 0;i < 13;i++) {
			
			if(!FT.tableau.get(i).isEmpty()) {
	
			if(label == map1.get(FT.tableau.get(i).get(0)) || label.equals(map1.get(FT.tableau.get(i).get(0)))) return i;
		
			}
			}
		return -99;//not found
	}
	
	///////////////////////////////////////////////////   FT
	
	 // This method is setting up the Little Spider game which starts 8 tableau piles and 4 homecell piles

	public void spiderGameSetUp() {
		int xC = 100;
		//set up tableaus
		for(int i = 0; i < 8; i++) {
			int yC = 400;
			for(int j = 0; j < 6; j++) {
				cardLabels.get(cardNames.indexOf(spider.piles.get(i).get(j).getImg())).setLocation(xC, yC);
				frame.setComponentZOrder(cardLabels.get(cardNames.indexOf(spider.piles.get(i).get(j).getImg())), j);
				addListenerSpider(cardLabels.get(cardNames.indexOf(spider.piles.get(i).get(j).getImg())));
				yC-=25;
			}
			xC+=100;
		}
		//set up HomeCells
		cardLabels.get(cardNames.indexOf(spider.piles.get(8).get(0).getImg())).setLocation(300,100);
		addListenerSpider(cardLabels.get(cardNames.indexOf(spider.piles.get(8).get(0).getImg())));
		frame.setComponentZOrder(cardLabels.get(cardNames.indexOf(spider.piles.get(8).get(0).getImg())), 0);
		cardLabels.get(cardNames.indexOf(spider.piles.get(9).get(0).getImg())).setLocation(400,100);
		addListenerSpider(cardLabels.get(cardNames.indexOf(spider.piles.get(9).get(0).getImg())));
		frame.setComponentZOrder(cardLabels.get(cardNames.indexOf(spider.piles.get(9).get(0).getImg())), 0);
		cardLabels.get(cardNames.indexOf(spider.piles.get(10).get(0).getImg())).setLocation(500,100);
		addListenerSpider(cardLabels.get(cardNames.indexOf(spider.piles.get(10).get(0).getImg())));
		frame.setComponentZOrder(cardLabels.get(cardNames.indexOf(spider.piles.get(10).get(0).getImg())), 0);
		cardLabels.get(cardNames.indexOf(spider.piles.get(11).get(0).getImg())).setLocation(600,100);
		addListenerSpider(cardLabels.get(cardNames.indexOf(spider.piles.get(11).get(0).getImg())));
		frame.setComponentZOrder(cardLabels.get(cardNames.indexOf(spider.piles.get(11).get(0).getImg())), 0);
		// fixes positioning bug caused by Zorder
		JLabel empty = new JLabel();
		empty.setBounds(0, 500, 0, 0);
		frame.add(empty);
		frame.setComponentZOrder(empty, 0);
		frame.revalidate();
	}
	 // This method is setting up the golf game which starts with 7 tableau piles, 1 homecell pile, and 1 stock pile

	public void golfGameSetUp() {
		int xC = 100;
		//set up tableaus
		for(int i = 0; i < 7; i++) {
			int yC = 250;
			for(int j = 0; j < 5; j++) {
				cardLabels.get(cardNames.indexOf(golf.tableau.get(i).get(j).getImg())).setLocation(xC, yC);
				frame.setComponentZOrder(cardLabels.get(cardNames.indexOf(golf.tableau.get(i).get(j).getImg())), j);
				if(j == 0) addListenerGolf(cardLabels.get(cardNames.indexOf(golf.tableau.get(i).get(j).getImg())));
				yC-=25;
			}
			xC+=100;
		}
		for(int i = 0; i < golf.x.deck.size(); i++) {
			int ys = 600;
			int xs = 600;
				cardLabels.get(cardNames.indexOf(golf.x.deck.get(i).getImg())).setLocation(xs, ys);
				frame.setComponentZOrder(cardLabels.get(cardNames.indexOf(golf.x.deck.get(i).getImg())), i);
				
			}
		
		
		JLabel stack = createDisplayImage("back.png");
		stack.setLocation(350, 425);
		JLabel home =  new JLabel();
		home.setBounds(460, 410, 76, 100);
		home.setLocation(455, 430);
		select(home);
		frame.add(stack);
		frame.add(home);
		eEgg.add(home);
		eEgg.add(stack);
		frame.setComponentZOrder(stack, 0);
		frame.setComponentZOrder(home, 0);
		stack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(selected == null) {
					cardLabels.get(cardNames.indexOf(golf.x.deck.get(0).getImg())).setLocation(home.getX(), home.getY()-5);
					cardLabels.get(cardNames.indexOf(golf.x.deck.get(0).getImg())).setVisible(true);
					golf.stockRemove();
					if(golf.x.deck.size() == 0) {
						stack.setVisible(false);
						stack.setEnabled(false);
					}
					if(golf.cell.size() == 1) unselect(home);
					//Make previous Top card on homecell invisible
					if(golf.cell.size() > 1) cardLabels.get(cardNames.indexOf(golf.cell.get(golf.cell.size()-2).getImg())).setVisible(false);
					//if all cards are in HomeCell - win
					if(golf.cell.size() == 52) JOptionPane.showMessageDialog( null, "YOU WIN!\n alt+leftClick Ace of Spades for Easter Egg" );
				}else {
					JOptionPane.showMessageDialog( null, "Can not move a card from tableau to stack" );
					unselect(selected);
					selected = null;
				}
			}
		});
		home.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(selected == null) {
					JOptionPane.showMessageDialog( null, "Can not move a card from HomeCell" );
				}else {
					int pile = getPileNum(selected);
					if(golf.tableauRemove(pile)) {
						selected.setLocation(home.getX(), home.getY()-5);
						
						if(golf.cell.size() == 1) unselect(home);
							
						//Make previous Top card on homecell invisible
						if(golf.cell.size() > 1) cardLabels.get(cardNames.indexOf(golf.cell.get(golf.cell.size()-2).getImg())).setVisible(false);
						//make next top card in tableau available to be selected
						if(golf.tableau.get(pile).size() > 0) {
							int index = getIndex(golf.tableau.get(pile).get(0).getImg());
							if(index != -1) addListenerGolf(cardLabels.get(index));
						}
						//if all cards are in HomeCell - win
						if(golf.cell.size() == 52) JOptionPane.showMessageDialog( null, "YOU WIN!\n alt+leftClick Ace of Spades for Easter Egg" );
					}else JOptionPane.showMessageDialog( null, "Illegal move" );
					unselect(selected);
					selected = null;
				}
			}
		});
		//Hide all the cards from stockpile
		for(int i = 0; i < golf.x.deck.size();i++) {
			cardLabels.get(cardNames.indexOf(golf.x.deck.get(i).getImg())).setVisible(false);
		}
		// fixes positioning bug caused by Zorder
		JLabel empty = new JLabel();
		empty.setBounds(0, 500, 0, 0);
		frame.add(empty);
		frame.setComponentZOrder(empty, 0);
		frame.revalidate();
	}

	/*recreates JLabels for cards to be able to set up a new game
	    and removes easter egg JLabels from the screen */
	public void newGame() {
		selected = null;
		while(!cardLabels.isEmpty()) {
			cardLabels.get(0).setEnabled(false);
			cardLabels.get(0).setVisible(false);
			cardNames.remove(0);
			frame.remove(cardLabels.get(0));
			cardLabels.remove(0);
		}
		while(!eEgg.isEmpty()) {
			eEgg.get(0).setEnabled(false);
			eEgg.get(0).setVisible(false);
			frame.remove(eEgg.get(0));
			eEgg.remove(0);
		}
		while(!FTcells.isEmpty()) {
			FTcells.get(0).setEnabled(false);
			FTcells.get(0).setVisible(false);
			frame.remove(FTcells.get(0));
			FTcells.remove(0);
		}
		while(!FTemptyT.isEmpty()) {
			FTemptyT.get(0).setEnabled(false);
			FTemptyT.get(0).setVisible(false);
			frame.remove(FTemptyT.get(0));
			FTemptyT.remove(0);
		}
	}
	
	// adds listener to the passed JLabel
	public void addListenerGolf(JLabel label) {
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(selected == null) {
					selected = label;
					select(label);
				}else if(selected != null) {
					if(selected != label){
						JOptionPane.showMessageDialog( null, "Can not move a card between Tableaus" );
						}
					unselect(selected);
					selected = null;
					
				}
			}
		});
	}

	public void addListenerSpider(JLabel label) {
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int pile = spiderGetPile(label);
				//tableau cards
				if(pile >=0 && pile <=7) {
					// Checks if the  top card  in a tableau pile can be selected 

					if(selected == null) {
						selected = label;
						if(!spiderTabCheckIfTop(selected)) {
							selected = null;
						}else {
							select(label);
						}
					}else if(selected != null) {
						if(selected != label){
						int pileRemove = spiderGetPile(selected);
						int pileAdd = spiderGetPile(label);

						if(spider.remove(pileRemove, pileAdd)) {
							selected.setLocation(label.getX(),label.getY()+25);
							for(int i = 0; i < spider.piles.get(pileAdd).size();i++) {
								frame.setComponentZOrder(cardLabels.get(cardNames.indexOf(spider.piles.get(pileAdd).get(i).getImg())), i);
							}
						}else {
							JOptionPane.showMessageDialog( null, "Illegal move" );
						}
					}
						unselect(selected);
						selected = null;
					}
					//home cell cards
				}else if(pile >=8 && pile <= 11) {			
				if(selected != label){
				
					if(selected == null) {
						selected = label;
						if(!spider.cellRemove(pile)) {
							selected = null;
							JOptionPane.showMessageDialog( null, "Can not move a card from home cell if size = 0" );
						}else {
							select(label);
						}
					}else if(selected != null) {
						int pileRemove = spiderGetPile(selected);
						int pileAdd = spiderGetPile(label);
						if(spider.remove(pileRemove, pileAdd)) {
							selected.setLocation(label.getX(),label.getY());
							for(int i = 0; i < spider.piles.get(pileAdd).size();i++) {
								frame.setComponentZOrder(cardLabels.get(cardNames.indexOf(spider.piles.get(pileAdd).get(i).getImg())), i);
							}
						}else {
							JOptionPane.showMessageDialog( null, "Illegal move" );
						}
						unselect(selected);
						selected = null;
						JLabel empty = new JLabel();
						empty.setBounds(0, 500, 0, 0);
						frame.add(empty);
						frame.setComponentZOrder(empty, 0);
						frame.revalidate();
					}
				}else {
					unselect(selected);
					selected = null;
				}
				}
				if(spider.piles.get(8).size()+spider.piles.get(9).size()+spider.piles.get(10).size()+spider.piles.get(11).size() == 52) JOptionPane.showMessageDialog( null, "YOU WIN!\n shift+leftClick Ace of Spades for Easter Egg" );
			}
		});
	}

	//return true if card is at the top of tableau else false
	public boolean spiderTabCheckIfTop(JLabel label) {
		boolean retVal = false;
		for(int i = 0; i < 8;i++) {
			if(spider.piles.get(i).isEmpty()) {
				continue;
			}
			else {
				if(label.getName().equals(spider.piles.get(i).get(0).getImg())) {
					retVal = true;
					break;
				}
			}
		}
		return retVal;
	}

	//returns -1 if card not found in homecell
	public int spiderGetPile(JLabel label) {
		int pile = -1;
		for(int i = 0;i < 12;i++) {
			for(int j = 0; j < spider.piles.get(i).size();j++) {
				if(label.getName() .equals(spider.piles.get(i).get(j).getImg()))return i;
			}
		}
		return pile;
	}

	/*returns pile number of the top card in tableau that matches 
		   the selected JLabel. return -1 if not found*/
	public int getPileNum(JLabel label) {
		int retVal = -1;
		for(int i = 0;i < 7;i++) {
			if(!(golf.tableau.get(i).size() == 0)) {
				if(label.getName() .equals(golf.tableau.get(i).get(0).getImg())) {
					retVal = i;
					break;
				}
			}
		}
		return retVal;
	}

	/* takes in card.getImg as parameter and returns the
		   corresponding JLabel index. returns -1 if not found*/
	public int getIndex(String name) {
		for(int i = 0;i < 52; i++) {
			if(cardNames.get(i) .equals(name))return i;
		}
		return -1;
	}
	 /*This is setting up the board for each of the game and checks
	if the user wants to quit the game */
	private class ActionHandler implements ActionListener {	
		public void actionPerformed(ActionEvent event) {
			String str = event.getActionCommand();
			System.out.println(event.getActionCommand());
			if (str.equals("New Game of Golf")) {
				newGame();
				showCards();
				frame.revalidate();
				golf = new Golf();
				golf.x.shuffle();
				golf.initializeTableau();
				golfGameSetUp();
			}
			else if (str.equals("New Game of Little Spider")) {
				newGame();
				showCards();
				frame.revalidate();
				spider = new LittleSpider();
				spider.initializePiles();
				spider.x.shuffle();
				spider.initializeTableau();
				spiderGameSetUp();
			}
			else if(str.equals("New Game of FortyThieves")) {
				newGame();
				FT = new FortyThieves();
				FT.createStock();
				FTSetUpLables();
				FT.createHomecell();
				Collections.shuffle(FT.stockPile);
				FT.createTableau();
				FTGameSetUp();
			}
			else if (str.equals("Quit Game")) {
				System.exit(0);
			}
		}
	}	
}
