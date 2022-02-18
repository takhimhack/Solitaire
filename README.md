# Solitaire
Created three fully fledged Solitaire games (Golf, Spider, 40 thieves) with extensive junit testing.

**Forty Thieves**

tableauPile()-Tableau piles in Forty Thieves initially hold 3 cards
tableauAddLegal()-Forty Thieves tableau pile method correctly determines if adding a specific card is legal or illegal
tableauRemoveLegal()-Forty Thieves tableau pile method correctly determines if removing a specific card is legal or illegal
tableauAddIncrement()-Adding a card to Forty Thieves tableau pile increases its number of cards and results in that card being the tableau pile's new top card
tableauRemoveDecrement()-Removing a card from Forty Thieves tableau pile decreases its number of cards and results in following card being the new top card
HomeCellPile()-Forty Thieves homecell piles initially hold 1 card 
HomecellAddLeaglity()-Forty Thieves homecell pile correctly determines if adding a specific card is legal or illegal 
homeCellRemoveLegal()-Forty Thieves homecell pile correctly returns if removing top card is legal or illegal (e.g., always illegal)
homecellAddIncrement()-Adding a card to Forty Thieves homecell pile increases its number of cards and results in that card being the homecell pile's new top card
StockPile()-Forty Thieves stock pile initially holds 57 cards
StockPileAddLeaglity()-Forty Thieves stock pile correctly returns if adding a specific card is legal or illegal (e.g., always illegal) 
StockPileRemoveLegality()-Forty Thieves stock pile correctly returns if removing a specific card is legal or illegal (e.g., legal if the stock pile is not empty) 
DealingStockPileRemove()-Dealing cards from Forty Thieves stock pile removes the top card from the pile, results in the next card being the new top card in the stock pile, and makes the removed card the new top card on the waste pile
WastePile()-Forty Thieves waste pile initially holds 0 cards
WastePileAddlegality()-Forty Thieves waste pile correctly returns if adding a specific card is legal or illegal (e.g., always illegal since we use another approach for this) 
WastePileRemovelegality()-Forty Thieves waste pile correctly returns if removing a specific card is legal or illegal (e.g., always legal) 
WastePileDecrement()-Removing a card from Forty Thieves waste pile decreases its number of cards and results in the following card being the new top card


**Golf**

tableauPile() - Tableau piles in Golf initially hold 5 cards
tableauAddLegal() - Golf tableau pile correctly determines if adding a specific card is legal or illegal (e.g., the method should always return false) 
tableauRemoveLegal() - Golf tableau pile correctly returns if removing top card is legal or illegal (e.g., if the tableau pile is NOT empty) 
tableauRemove() - Removing card from Golf tableau pile decreases its number of cards and results in following card being the new top card 
cellInitial() - Homecell pile in Golf initially holds 0 cards
cellLegal() - Golf homecell pile correctly determines if adding a card is legal or illegal
cellRemoveLegal() - Golf homecell pile correctly returns if removing top card is legal or illegal (e.g., always false)
cellAdd() - Adding card to Golf homecell pile increases its number of cards and results in that card being the homecell pile's new top card
stockInitial() - Golf stock initially holds 17 cards
stockAddLegal() - Golf stock pile correctly returns if adding a specific card is legal or illegal (e.g., the method should always return false)
stockRemoveLegal() - Golf stock pile correctly returns if removing top card is legal or illegal (e.g., if the stock pile is NOT empty)
stockRemove() - Removing card from Golf's stock pile decreases its number of cards and results in following card being the new top card
	

**Spider**

tableauPile() - Tableau piles in Little Spider initially hold 6 cards
tableauAdd() - Little Spider tableau pile correctly determines if adding a specific card is legal or illegal
tableauRemove() - Little Spider tableau pile correctly returns if removing top card is legal or illegal (e.g., if the tableau pile is NOT empty)
tableauAddIncrement() - Adding card to Little Spider's tableau pile increases its number of cards and results in that card being the tableau pile's new top card
tableauRemoveDecrement() - Removing card from Little Spider's tableau pile decreases its number of cards and results in following card being the new top card
initialCell() - Homecell piles in Little Spider initially hold 1 card
cellAddLeaglity() - Little Spider homecell piles correctly determines if adding a specific card is legal or illegal
cellRemoveTop() - Little Spider homecell pile correctly returns if removing top card is legal or illegal (e.g., if the homecell pile has 2 or more cards)
cellAddIncrement() - Adding card to Little Spider's homecell pile increases its number of cards and results in that card being the homecell pile's new top card
cellRemoveDecrement() - Removing card from one of Little Spider's homecell pile decreases its number of cards and results in following card being the new top card
