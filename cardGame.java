/*
	Danny Rivera
	CSC 311 - 01
	Dr Amlan Chatterjee
	Project 03
	April 6, 2017

	The following program is a Card Game Simulation involving 4 Players
	4 Players play the game for 15 Rounds and the one who gets all 52 Cards or the maximum number of cards wins!
	Player's Card and The Card on the Table is stored in a Link List Queue storage
	The order of the Players is stored in a Single Linked List.
	The Deck class randomize the deck of 52 Cards.

	!! The following project does NOT use any of the Java Collection Framework !!
	Therefore, I compare each players card and each players suits. 

	We want to play a simple card game with 4 players option. The deck of cards contains 52 cards with 13 cards each in the 4 suits:
	clubs, diamonds, hearts and spades. Each player begins with 13 cards and one of the players starts the game by putting their first card on the table.
	In each round, players take turns by putting the top card from their hand on the table. 
	The player who puts the card with the maximum value, gets all the 4 cards for that round. 
	It can be assumed that the values for the cards are the card numbers, and 1 for Ace, 11 for Jack, 12 for Queen and 13 for King. 
	If more than one player has the same value card, then the suit decides the winner of the round; 
	clubs have more value than diamonds, which in turn has greater value than hearts, and hearts have greater value than spades. 
	The winner of each round gets all the 4 cards currently on the table and adds them at the end of the cards currently in his or her hand. 
	Game continues until one player gets all the cards or for 15 rounds, whichever is earlier. 
	If one player doesn’t have all the cards before round 15, the player with the maximum number of cards at the end of 15 rounds is the winner.
	Construct the game using the following guidelines:
1. Create a method to deal the deck of cards so that each player gets 13 random cards
2. Start the game by selecting a random player and move to other players in increasing order (or clockwise direction) 
	and proceed in rounds; in each round all players put their first card on the table
3. Display what cards each player puts on the table
4. Show the cards on the table and in the hand of each player at the end of each round
5. 1 player wins each round; in the next round the winner of the previous round puts the first card on the table
6. Continue the game for 15 rounds or until one player has all the cards
7. Declare the winner (the player with all the cards or the player with maximum cards after 15 rounds), or say its a tie! 
	(when multiple players have equal number of cards after 15 rounds)
8. The cards on the table and in each players hand must be stored using Queues
*/
import java.util.Random;
public class cardGame
{
	public static Player playerOne, playerTwo, playerThree, playerFour;
	public static SingleLinkedList orderedPlayer = new SingleLinkedList();
	public static Table cardTable = new Table();
	public static Deck deck;
	public static int rounds = 1;
	public static Card p1card, p2card, p3card, p4card;
	
	/*  ********* MAIN ********** */
	public static void main(String[] args)
	{
		initializePlayers();
		distributeRandomCards();
		selectRandomPlayer();
		playCards();
	}

	/* Method purpose is to Initialize Players Name */
	public static void initializePlayers()
	{
		playerOne = new Player("Player 1");
		playerTwo = new Player("Player 2");
		playerThree = new Player("Player 3");
		playerFour = new Player("Player 4");
	}

	/* Method purpose is to distribute Random cards to all 4 players */
	public static void distributeRandomCards()
	{
		deck = new Deck();	// Initialize Deck class 
		for(int i=0; i<13; i++)	// Distribute 13 cards to each players
		{
			playerOne.addCard(deck.deal());	// Player 1 gets 13 random cards
			playerTwo.addCard(deck.deal());	// Player 2 gets 13 random cards
			playerThree.addCard(deck.deal());	// Player 3 gets 13 random cards
			playerFour.addCard(deck.deal());	// Player 4 gets 13 random cards
		}
	}

	/* Method purpose is to select random player to go first */
	public static void selectRandomPlayer()
	{
		Random random = new Random();	// Initialize Random
		int value = random.nextInt(4)+1;	// store random number between 1-4
		if(value == 1)
		{
			// Add Single Linked List With Player 1 as first Node */	
			orderedPlayer.add(playerOne);
			orderedPlayer.add(playerTwo);
			orderedPlayer.add(playerThree);
			orderedPlayer.add(playerFour);
			// Add card values to the Table with Player 1 as Front of Queue
			cardTable.add(playerOne.peekCard());
			cardTable.add(playerTwo.peekCard());
			cardTable.add(playerThree.peekCard());
			cardTable.add(playerFour.peekCard());
		}
		else if(value == 2)
		{
			// Add Single Linked List With Player 2 as first Node */			
			orderedPlayer.add(playerTwo);
			orderedPlayer.add(playerThree);
			orderedPlayer.add(playerFour);
			orderedPlayer.add(playerOne);
			// Add card values to the Table with Player 2 as Front of Queue
			cardTable.add(playerTwo.peekCard());
			cardTable.add(playerThree.peekCard());
			cardTable.add(playerFour.peekCard());
			cardTable.add(playerOne.peekCard());
		}
		else if(value == 3)
		{
			// Add Single Linked List With Player 3 as first Node */
			orderedPlayer.add(playerThree);
			orderedPlayer.add(playerFour);
			orderedPlayer.add(playerOne);
			orderedPlayer.add(playerTwo);
			// Add card values to the Table with Player 3 as Front of Queue
			cardTable.add(playerThree.peekCard());
			cardTable.add(playerFour.peekCard());
			cardTable.add(playerOne.peekCard());
			cardTable.add(playerTwo.peekCard());
		}
		else if(value == 4)
		{
			// Add Single Linked List With Player 4 as first Node */
			orderedPlayer.add(playerFour);
			orderedPlayer.add(playerOne);
			orderedPlayer.add(playerTwo);
			orderedPlayer.add(playerThree);
			// Add card values to the Table with Player 4 as Front of Queue
			cardTable.add(playerFour.peekCard());
			cardTable.add(playerOne.peekCard());
			cardTable.add(playerTwo.peekCard());
			cardTable.add(playerThree.peekCard());
		}
	}

	/* Method is used to play Cards for 15 rounds */
	/* Method calls function to check if total cards is not less than 0 */
	/* Method calls function to check if Player Collected all 52 Cards */
	/* Method calls function to Display Cards & Display Table */
	/* Method calls function to remove players card from Single Linked List */
	/* Method calls function to compare cards stored in a Linked Queue */
	public static void playCards()
	{
		while (rounds <= 15)
		{
			System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * ");	// Line Seperator
			System.out.println("\t\tROUND: " + rounds);

			checkTotalCards();	// Check if total cards of player is 0. Probability is low. BUT it CAN Happen.
			playerCollectedAllCards();	// Check if player collected all 52 Cards. Possibility is very very very low. But It CAN Happen.
			displayPlayersCards();		// Display Players Card
			displayTable();				// Display Table's Card
			removeCards();				// Remove Card from Player
			compareCards();				// Compare each Players card

			// Display all players total card for each single round */
			System.out.println(playerOne.getName() + " total cards " + playerOne.getTotalCards());
			System.out.println(playerTwo.getName() + " total cards " + playerTwo.getTotalCards());		
			System.out.println(playerThree.getName() + " total cards " + playerThree.getTotalCards());
			System.out.println(playerFour.getName() + " total cards " + playerFour.getTotalCards());
			rounds++;	// Increment rounds
			System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * *\n\n");	// Line Seperator
		}
		declareWinner();	// Function is used to declare who the Winner is
	}
	// Check if Player's Card is null.  Player data is stored in a Link Queue
	public static void checkTotalCards()
	{
		if(playerOne.peekCard() == null)	// If player 1 Card is null. Set Total Cards to 0
		{
			playerOne.setTotalCards(0);		// Prevents a Null pointer exception
		}
		if(playerTwo.peekCard() == null)	// If player 2 Card is null. Set Total Cards to 0
		{
			playerTwo.setTotalCards(0);
		}
		if(playerThree.peekCard() == null)	// If player 3 Card is null. Set Total Cards to 0
		{
			playerThree.setTotalCards(0);
		}
		if(playerFour.peekCard() == null)	// If player 4 Card is null. Set Total Cards to 0
		{
			playerFour.setTotalCards(0);
		}

	}
	// Check if Player has all 52 Cards
	public static void playerCollectedAllCards()
	{
		if(playerOne.getTotalCards() == 52)	// IF PLAYER 1 GETS ALL 52 CARDS FROM DECK
		{
			System.out.println("***** " + playerOne.getName() + " Wins!!! ***** ");
			System.out.println(playerOne.getName() + " maximum number of cards " + playerOne.getTotalCards());
			System.exit(0);	// EXIT THE PROGRAM
		} 

		if(playerTwo.getTotalCards() == 52)	// IF PLAYER 2 GETS ALL 52 CARDS FROM DECK 	
		{	
			System.out.println("***** " + playerTwo.getName() + " Wins!!! ***** ");
			System.out.println(playerTwo.getName() + " maximum number of cards " + playerTwo.getTotalCards());
			System.exit(0);	// EXIT THE PROGRAM
		}

		if(playerThree.getTotalCards() == 52) // IF PLAYER 2 GETS ALL 52 CARDS FROM DECK
		{
			System.out.println("***** " + playerThree.getName() + " Wins!!! ***** ");
			System.out.println(playerThree.getName() + " maximum number of cards " + playerThree.getTotalCards());
			System.exit(0);	// EXIT THE PROGRAM
		}

		if(playerFour.getTotalCards() == 52) // IF PLAYER 4 GETS ALL 52 CARDS FROM DECK
		{
			System.out.println("***** " + playerFour.getName() + " Wins!!! *****");
			System.out.println(playerFour.getName() + " maximum number of cards " + playerFour.getTotalCards());
			System.exit(0);	// EXIT THE PROGRAM
		} 

	}
	// Outputs Players Card to Screen
	public static void displayPlayersCards()
	{
		Player temp;	// TEMP
		/* Reset Single Linked List */
		while(orderedPlayer.getSize() > 0)
		{
			temp = (Player) orderedPlayer.removeFirst();
			if(temp == playerOne)	// Check Reference
			{
				playerOne.displayCard();	// Call Player class to display Cards
			}
			if(temp == playerTwo)	// Check Reference
			{
				playerTwo.displayCard();	// Call Player class to display Cards
			}
			if(temp == playerThree)	// Check Reference
			{
				playerThree.displayCard();	// Call Player class to display Cards
			}
			if(temp == playerFour)	// Check Reference
			{
				playerFour.displayCard();	// Call Player class to display Cards
			}
		}
	}
	// Outputs Table Cards to Screen
	public static void displayTable()
	{
		System.out.println("\t\tTABLE");
		while ( cardTable.getCardSize() != 0 )
		{ 
			Card card = cardTable.getCard();
			if(card == null)	// If card is null. Go Back to Loop
			{
				continue;
			} 
		 	System.out.println("\t" + card.getSuitName() + " " + card.getCardName());
		}  
	}

	// Remove Card from Player
	public static void removeCards()
	{
		int n = 0;
		p1card = playerOne.getCard();
		if(p1card == null)		// If player 1 has no more cards (null). Set Suit && Card Number to 0
		{
			p1card = p2card;
			p1card.setSuit(n);
			p1card.setCardNumber(n);	
		}
		
		p2card = playerTwo.getCard();	
		if(p2card == null)		// If player 2 has no more cards (null). Set Suit && Card Number to 0
		{
			p2card = p3card;
			p2card.setSuit(n);
			p2card.setCardNumber(n);
		}
		p3card = playerThree.getCard();
		if(p3card == null)		// If player 3 has no more cards (null). Set Suit && Card Number to 0
		{
			p3card = p4card;
			p3card.setSuit(n);
			p3card.setCardNumber(n);
		}
		
		p4card = playerFour.getCard();
		if(p4card == null)		// If player 4 has no more cards (null). Set Suit && Card Number to 0
		{
			p4card = p1card;
			p4card.setSuit(n);
			p4card.setCardNumber(n);
		}
	}

	/* Compare Cards for each 4 Player */
	public static void compareCards()
	{
		Card max;
		/* ASSUME THAT PLAYER 1 WON THE ROUND */
		/* CHECK IF PLAYER 1 HAS THE LARGEST CARD NUMBER */
		if( 			(playerOne != null) &&
			(p1card.getCardNumber() >= p2card.getCardNumber()) && 
			(p1card.getCardNumber() >= p3card.getCardNumber()) &&
			(p1card.getCardNumber() >= p4card.getCardNumber()) )
		{
			max = p1card;	// Store it on a temp Card

			/**  Are the card numbers equal to Player 2 **/
			if(max.getCardNumber() == p2card.getCardNumber()) 
			{
				compareSuits(max, p2card);	// Calls Function to Compare Suits
				return;
			}
			/**  Are the card numbers equal to Player 3 **/
			if(max.getCardNumber() == p3card.getCardNumber())
			{
				compareSuits(max, p3card);	// Calls Function to Compare Suits
				return;
			}
			/**  Are the card numbers equal to Player 4 **/
			if(max.getCardNumber() == p4card.getCardNumber())
			{
				compareSuits(max, p4card);	// Calls Function to Compare Suits
				return;
			}
		
			System.out.println("***** " + playerOne.getName() + " WINS ROUND " + rounds + " *****");
			// Add Cards to Player 1
			playerOne.addCard(p4card);
			playerOne.addCard(p3card);
			playerOne.addCard(p2card);
			playerOne.addCard(p1card);

			// Add Cards to Table for next Round (Data is stored in Link Queue)
			cardTable.add(playerOne.peekCard());
			cardTable.add(playerTwo.peekCard());
			cardTable.add(playerThree.peekCard());
			cardTable.add(playerFour.peekCard());
				
			// Add Players Order for next Round (Data is stored in Single Linked List)
			orderedPlayer.add(playerOne);
			orderedPlayer.add(playerTwo);
			orderedPlayer.add(playerThree);
			orderedPlayer.add(playerFour);		
		}
		/* ASSUME THAT PLAYER 2 WON THE ROUND */
		/* CHECK IF PLAYER 2 HAS THE LARGEST CARD NUMBER */
		if( 			(playerTwo != null) &&
			(p2card.getCardNumber() >= p1card.getCardNumber()) && 
			(p2card.getCardNumber() >= p3card.getCardNumber()) &&
			(p2card.getCardNumber() >= p4card.getCardNumber()) )
		{
			max = p2card;	// Store it on a temp Card

			/**  Are the card numbers equal to Player 1 **/
			if(max.getCardNumber() == p1card.getCardNumber()) 
			{
				compareSuits(max, p1card);	// Calls Function to Compare Suits
				return;
			}
			/**  Are the card numbers equal to Player 3 **/
			if(max.getCardNumber() == p3card.getCardNumber())
			{
				compareSuits(max, p3card);	// Calls Function to Compare Suits
				return;
			}
			/**  Are the card numbers equal to Player 4 **/
			if(max.getCardNumber() == p4card.getCardNumber())
			{
				compareSuits(max, p4card);	// Calls Function to Compare Suits
				return;
			}

			System.out.println("***** " + playerTwo.getName() + " WINS ROUND " + rounds + " *****");
			// Add Cards to Player 2
			playerTwo.addCard(p4card);
			playerTwo.addCard(p3card);
			playerTwo.addCard(p1card);
			playerTwo.addCard(p2card);
			// Add Cards to Table for next Round (Data is stored in Link Queue)
			cardTable.add(playerTwo.peekCard());
			cardTable.add(playerThree.peekCard());
			cardTable.add(playerFour.peekCard());
			cardTable.add(playerOne.peekCard());
			// Add Players Order for next Round (Data is stored in Single Linked List)				
			orderedPlayer.add(playerTwo);
			orderedPlayer.add(playerThree);
			orderedPlayer.add(playerFour);
			orderedPlayer.add(playerOne);
			
		}
		/* ASSUME THAT PLAYER 3 WON THE ROUND */
		/* CHECK IF PLAYER 3 HAS THE LARGEST CARD NUMBER */
		if( 			(playerThree != null) &&
			(p3card.getCardNumber() >= p1card.getCardNumber()) && 
			(p3card.getCardNumber() >= p2card.getCardNumber()) &&
			(p3card.getCardNumber() >= p4card.getCardNumber()) )
		{
			max = p3card;	// Store it on a temp Card

			/**  Are the card numbers equal to Player 1 **/
			if(max.getCardNumber() == p1card.getCardNumber()) 
			{
				compareSuits(max, p1card);	// Calls Function to Compare Suits
				return;
			}
			/**  Are the card numbers equal to Player 2 **/
			if(max.getCardNumber() == p2card.getCardNumber())
			{
				compareSuits(max, p2card);	// Calls Function to Compare Suits
				return;
			}
			/**  Are the card numbers equal to Player 4 **/
			if(max.getCardNumber() == p4card.getCardNumber())
			{
				compareSuits(max, p4card);	// Calls Function to Compare Suits
				return;
			}
			System.out.println("***** " + playerThree.getName() + " WINS ROUND " + rounds + " *****");
			// Add Cards to Player 3
			playerThree.addCard(p4card);
			playerThree.addCard(p2card);
			playerThree.addCard(p1card);
			playerThree.addCard(p3card);
			// Add Cards to Table for next Round (Data is stored in Link Queue)
			cardTable.add(playerThree.peekCard());
			cardTable.add(playerFour.peekCard());
			cardTable.add(playerOne.peekCard());
			cardTable.add(playerTwo.peekCard());
			// Add Players Order for next Round (Data is stored in Single Linked List)	
			orderedPlayer.add(playerThree);
			orderedPlayer.add(playerFour);
			orderedPlayer.add(playerOne);
			orderedPlayer.add(playerTwo);
		}
		/* ASSUME THAT PLAYER 4 WON THE ROUND */
		/* CHECK IF PLAYER 4 HAS THE LARGEST CARD NUMBER */
		if( 			(playerFour != null) &&
			(p4card.getCardNumber() >= p1card.getCardNumber()) && 
			(p4card.getCardNumber() >= p2card.getCardNumber()) &&
			(p4card.getCardNumber() >= p3card.getCardNumber()) )
		{
			
			max = p4card;	// Store it on a temp Card

			/**  Are the card numbers equal to Player 1 **/
			if(max.getCardNumber() == p1card.getCardNumber()) 
			{
				compareSuits(max, p1card);
				return;
			}
			/**  Are the card numbers equal to Player 2 **/
			if(max.getCardNumber() == p2card.getCardNumber())
			{
				compareSuits(max, p2card);
				return;
			}
			/**  Are the card numbers equal to Player 3 **/
			if(max.getCardNumber() == p3card.getCardNumber())
			{
				compareSuits(max, p3card);
				return;
			}
			System.out.println("***** " + playerFour.getName() + " WINS ROUND " + rounds + " *****");
			// Add Cards to Player 4
			playerFour.addCard(p1card);
			playerFour.addCard(p2card);
			playerFour.addCard(p3card);
			playerFour.addCard(p4card);
			// Add Cards to Table for next Round (Data is stored in Link Queue)
			cardTable.add(playerFour.peekCard());
			cardTable.add(playerOne.peekCard());
			cardTable.add(playerTwo.peekCard());
			cardTable.add(playerThree.peekCard());
			// Add Players Order for next Round (Data is stored in Single Linked List)
			orderedPlayer.add(playerFour);
			orderedPlayer.add(playerOne);
			orderedPlayer.add(playerTwo);
			orderedPlayer.add(playerThree);	
		}
	}


	/* Function is used to Compare Suits */
	/* We do not know the order of the Players */
	/* Compare each suit by each Player */
	public static void compareSuits(Card largestNumberOne, Card largestNumberTwo)
	{
		/* CHECK IF PLAYER 1 AND PLAYER 2 IS THE LARGEST NUMBER */
		if( (largestNumberOne.getCardNumber() == p1card.getCardNumber()) && 
			(largestNumberOne.getSuit() == p1card.getSuit()) &&
			(largestNumberTwo.getCardNumber() == p2card.getCardNumber()) &&
			(largestNumberTwo.getSuit() == p2card.getSuit()) )
		{
			/* CHECK IF PLAYER 1 HAS THE LARGEST SUIT */
			if(p1card.getSuit() > p2card.getSuit())
			{
				System.out.println("***** " + playerOne.getName() + " WINS ROUND " + rounds + " *****");
				// STORE ALL PLAYERS CARD TO PLAYER 1. STORED IN A LINKED QUEUE
				playerOne.addCard(p4card);
				playerOne.addCard(p3card);
				playerOne.addCard(p2card);
				playerOne.addCard(p1card);
				// STORE CARDS ON TABLE FOR NEXT ROUND				
				cardTable.add(playerOne.peekCard());
				cardTable.add(playerTwo.peekCard());
				cardTable.add(playerThree.peekCard());
				cardTable.add(playerFour.peekCard());
				// STORE LINKED LIST AS PLAYER 1 BEING THE FIRST NODE. LINKED LIST IS FOR NEXT ROUND
				orderedPlayer.add(playerOne);
				orderedPlayer.add(playerTwo);
				orderedPlayer.add(playerThree);
				orderedPlayer.add(playerFour);				
			}

			/* CHECK IF PLAYER 2 HAS THE LARGEST SUIT */
			else if(p2card.getSuit() > p1card.getSuit())
			{
				System.out.println("***** " + playerTwo.getName() + " WINS ROUND " + rounds + " *****");
				// STORE ALL PLAYERS CARD TO PLAYER 2. STORED IN A LINKED QUEUE
				playerTwo.addCard(p4card);
				playerTwo.addCard(p3card);
				playerTwo.addCard(p1card);
				playerTwo.addCard(p2card);
				// STORE CARDS ON TABLE FOR NEXT ROUND
				cardTable.add(playerTwo.peekCard());
				cardTable.add(playerThree.peekCard());
				cardTable.add(playerFour.peekCard());
				cardTable.add(playerOne.peekCard());
				// STORE LINKED LIST AS PLAYER 2 BEING THE FIRST NODE. LINKED LIST IS FOR NEXT ROUND
				orderedPlayer.add(playerTwo);
				orderedPlayer.add(playerThree);
				orderedPlayer.add(playerFour);
				orderedPlayer.add(playerOne);
			}
		}

		/* CHECK IF PLAYER 1 AND PLAYER 3 IS THE LARGEST NUMBER */
		if( (largestNumberOne.getCardNumber() == p1card.getCardNumber()) && 
			(largestNumberOne.getSuit() == p1card.getSuit()) &&
			(largestNumberTwo.getCardNumber() == p3card.getCardNumber()) &&
			(largestNumberTwo.getSuit() == p3card.getSuit()) )
		{
			/* CHECK IF PLAYER 1 HAS THE LARGEST SUIT */
			if(p1card.getSuit() > p3card.getSuit())
			{
				System.out.println("***** " + playerOne.getName() + " WINS ROUND " + rounds + " *****");
				// STORE ALL PLAYERS CARD TO PLAYER 1. STORED IN A LINKED QUEUE
				playerOne.addCard(p4card);
				playerOne.addCard(p2card);
				playerOne.addCard(p3card);
				playerOne.addCard(p1card);
				// STORE CARDS ON TABLE FOR NEXT ROUND
				cardTable.add(playerOne.peekCard());
				cardTable.add(playerTwo.peekCard());
				cardTable.add(playerThree.peekCard());
				cardTable.add(playerFour.peekCard());
				// STORE LINKED LIST AS PLAYER 1 BEING THE FIRST NODE. LINKED LIST IS FOR NEXT ROUND.
				orderedPlayer.add(playerOne);
				orderedPlayer.add(playerTwo);
				orderedPlayer.add(playerThree);
				orderedPlayer.add(playerFour);
			}

			/* CHECK IF PLAYER 3 HAS THE LARGEST SUIT */
			else if(p3card.getSuit() > p1card.getSuit())
			{
				System.out.println("***** " + playerThree.getName() + " WINS ROUND " + rounds + " *****");
				// STORE ALL PLAYERS CARD TO PLAYER 3. STORED IN A LINKED QUEUE
				playerThree.addCard(p4card);
				playerThree.addCard(p3card);
				playerThree.addCard(p1card);
				playerThree.addCard(p2card);
				// STORE CARDS ON TABLE FOR NEXT ROUND
				cardTable.add(playerThree.peekCard());
				cardTable.add(playerFour.peekCard());
				cardTable.add(playerOne.peekCard());
				cardTable.add(playerTwo.peekCard());
				// STORE LINKED LIST AS PLAYER 3 BEING THE FIRST NODE. LINKED LIST IS FOR NEXT ROUND
				orderedPlayer.add(playerThree);
				orderedPlayer.add(playerFour);
				orderedPlayer.add(playerOne);
				orderedPlayer.add(playerTwo);
			}

		}


		/* CHECK IF PLAYER 1 AND PLAYER 4 IS THE LARGEST NUMBER */
		if( (largestNumberOne.getCardNumber() == p1card.getCardNumber()) && 
			(largestNumberOne.getSuit() == p1card.getSuit()) &&
			(largestNumberTwo.getCardNumber() == p4card.getCardNumber()) &&
			(largestNumberTwo.getSuit() == p4card.getSuit()) )
		{

			/* CHECK IF PLAYER 1 HAS THE LARGEST SUIT */
			if(p1card.getSuit() > p4card.getSuit())
			{
				System.out.println("***** " + playerOne.getName() + " WINS ROUND " + rounds + " *****");
				// STORE ALL PLAYERS CARD TO PLAYER 1. STORED IN A LINKED QUEUE
				playerOne.addCard(p3card);
				playerOne.addCard(p2card);
				playerOne.addCard(p4card);
				playerOne.addCard(p1card);
				// STORE CARDS ON TABLE FOR NEXT ROUND
				cardTable.add(playerOne.peekCard());
				cardTable.add(playerTwo.peekCard());
				cardTable.add(playerThree.peekCard());
				cardTable.add(playerFour.peekCard());
				// STORE LINKED LIST AS PLAYER 1 BEING THE FIRST NODE. LINKED LIST IS FOR NEXT ROUND.
				orderedPlayer.add(playerOne);
				orderedPlayer.add(playerTwo);
				orderedPlayer.add(playerThree);
				orderedPlayer.add(playerFour);
			}

			/* CHECK IF PLAYER 4 HAS THE LARGEST SUIT */
			else if(p4card.getSuit() > p1card.getSuit())
			{
				System.out.println("***** " + playerFour.getName() + " WINS ROUND " + rounds + " *****");
				// STORE ALL PLAYERS CARD TO PLAYER 4. STORED IN A LINKED QUEUE
				playerFour.addCard(p2card);
				playerFour.addCard(p3card);
				playerFour.addCard(p1card);
				playerFour.addCard(p4card);
				// STORE CARDS ON TABLE FOR NEXT ROUND
				cardTable.add(playerFour.peekCard());
				cardTable.add(playerOne.peekCard());
				cardTable.add(playerTwo.peekCard());
				cardTable.add(playerThree.peekCard());
				// STORE LINKED LIST AS PLAYER 4 BEING THE FIRST NODE. LINKED LIST IS FOR NEXT ROUND
				orderedPlayer.add(playerFour);
				orderedPlayer.add(playerOne);
				orderedPlayer.add(playerTwo);
				orderedPlayer.add(playerThree);
			}

		}

		/* CHECK IF PLAYER 2 AND PLAYER 1 IS THE LARGEST NUMBER */
		if( (largestNumberOne.getCardNumber() == p2card.getCardNumber()) && 
			(largestNumberOne.getSuit() == p2card.getSuit()) &&
			(largestNumberTwo.getCardNumber() == p1card.getCardNumber()) &&
			(largestNumberTwo.getSuit() == p1card.getSuit()) )
		{
			/* CHECK IF PLAYER 2 HAS THE LARGEST SUIT */
			if(p2card.getSuit() > p1card.getSuit())
			{
				System.out.println("***** " + playerTwo.getName() + " WINS ROUND " + rounds + " *****");
				// STORE ALL PLAYERS CARD TO PLAYER 2. STORED IN A LINKED QUEUE
				playerTwo.addCard(p4card);
				playerTwo.addCard(p3card);
				playerTwo.addCard(p1card);
				playerTwo.addCard(p2card);
				// STORE CARDS ON TABLE FOR NEXT ROUND
				cardTable.add(playerTwo.peekCard());
				cardTable.add(playerThree.peekCard());
				cardTable.add(playerFour.peekCard());
				cardTable.add(playerOne.peekCard());
				// STORE LINKED LIST AS PLAYER 2 BEING THE FIRST NODE. LINKED LIST IS FOR NEXT ROUND.
				orderedPlayer.add(playerTwo);
				orderedPlayer.add(playerThree);
				orderedPlayer.add(playerFour);
				orderedPlayer.add(playerOne);
			}

			/* CHECK IF PLAYER 1 HAS THE LARGEST SUIT */
			else if(p1card.getSuit() > p2card.getSuit())
			{
				System.out.println("***** " + playerOne.getName() + " WINS ROUND " + rounds + " *****");
				// STORE ALL PLAYERS CARD TO PLAYER 1. STORED IN A LINKED QUEUE
				playerOne.addCard(p4card);
				playerOne.addCard(p3card);
				playerOne.addCard(p2card);
				playerOne.addCard(p1card);
				// STORE CARDS ON TABLE FOR NEXT ROUND	
				cardTable.add(playerOne.peekCard());
				cardTable.add(playerTwo.peekCard());
				cardTable.add(playerThree.peekCard());
				cardTable.add(playerFour.peekCard());
				// STORE LINKED LIST AS PLAYER 3 BEING THE FIRST NODE. LINKED LIST IS FOR NEXT ROUND
				orderedPlayer.add(playerOne);
				orderedPlayer.add(playerTwo);
				orderedPlayer.add(playerThree);
				orderedPlayer.add(playerFour);
			}

		}

		/* CHECK IF PLAYER 2 AND PLAYER 3 IS THE LARGEST NUMBER */
		if( (largestNumberOne.getCardNumber() == p2card.getCardNumber()) && 
			(largestNumberOne.getSuit() == p2card.getSuit()) &&
			(largestNumberTwo.getCardNumber() == p3card.getCardNumber()) &&
			(largestNumberTwo.getSuit() == p3card.getSuit()) )
		{
			/* CHECK IF PLAYER 2 HAS THE LARGEST SUIT */
			if(p2card.getSuit() > p3card.getSuit())
			{
				System.out.println("***** " + playerTwo.getName() + " WINS ROUND " + rounds + " *****");
				// STORE ALL PLAYERS CARD TO PLAYER 2. STORED IN A LINKED QUEUE
				playerTwo.addCard(p4card);
				playerTwo.addCard(p1card);
				playerTwo.addCard(p3card);
				playerTwo.addCard(p2card);
				// STORE CARDS ON TABLE FOR NEXT ROUND
				cardTable.add(playerTwo.peekCard());
				cardTable.add(playerThree.peekCard());
				cardTable.add(playerFour.peekCard());
				cardTable.add(playerOne.peekCard());
				// STORE LINKED LIST AS PLAYER 2 BEING THE FIRST NODE. LINKED LIST IS FOR NEXT ROUND.
				orderedPlayer.add(playerTwo);
				orderedPlayer.add(playerThree);
				orderedPlayer.add(playerFour);
				orderedPlayer.add(playerOne);
			}

			/* CHECK IF PLAYER 3 HAS THE LARGEST SUIT */
			else if(p3card.getSuit() > p2card.getSuit())
			{
				System.out.println("***** " + playerThree.getName() + " WINS ROUND " + rounds + " *****");
				// STORE ALL PLAYERS CARD TO PLAYER 3. STORED IN A LINKED QUEUE
				playerThree.addCard(p4card);
				playerThree.addCard(p1card);
				playerThree.addCard(p2card);
				playerThree.addCard(p3card);
				// STORE CARDS ON TABLE FOR NEXT ROUND
				cardTable.add(playerThree.peekCard());
				cardTable.add(playerFour.peekCard());
				cardTable.add(playerOne.peekCard());
				cardTable.add(playerTwo.peekCard());
				// STORE LINKED LIST AS PLAYER 3 BEING THE FIRST NODE. LINKED LIST IS FOR NEXT ROUND
				orderedPlayer.add(playerThree);
				orderedPlayer.add(playerFour);
				orderedPlayer.add(playerOne);				
				orderedPlayer.add(playerTwo);
			}

		}

		/* CHECK IF PLAYER 2 AND PLAYER 4 IS THE LARGEST NUMBER */
		if( (largestNumberOne.getCardNumber() == p2card.getCardNumber()) && 
			(largestNumberOne.getSuit() == p2card.getSuit()) &&
			(largestNumberTwo.getCardNumber() == p4card.getCardNumber()) &&
			(largestNumberTwo.getSuit() == p4card.getSuit()) )
		{

			/* CHECK IF PLAYER 2 HAS THE LARGEST SUIT */
			if(p2card.getSuit() > p4card.getSuit())
			{
				System.out.println("***** " + playerTwo.getName() + " WINS ROUND " + rounds + " *****");
				// STORE ALL PLAYERS CARD TO PLAYER 2. STORED IN A LINKED QUEUE
				playerTwo.addCard(p1card);
				playerTwo.addCard(p3card);
				playerTwo.addCard(p4card);
				playerTwo.addCard(p2card);
				// STORE CARDS ON TABLE FOR NEXT ROUND
				cardTable.add(playerTwo.peekCard());
				cardTable.add(playerThree.peekCard());
				cardTable.add(playerFour.peekCard());
				cardTable.add(playerOne.peekCard());
				// STORE LINKED LIST AS PLAYER 2 BEING THE FIRST NODE. LINKED LIST IS FOR NEXT ROUND.
				orderedPlayer.add(playerTwo);
				orderedPlayer.add(playerThree);
				orderedPlayer.add(playerFour);
				orderedPlayer.add(playerOne);
			}

			/* CHECK IF PLAYER 4 HAS THE LARGEST SUIT */
			else if(p4card.getSuit() > p2card.getSuit())
			{
				System.out.println("***** " + playerFour.getName() + " WINS ROUND " + rounds + " *****");
				// STORE ALL PLAYERS CARD TO PLAYER 4. STORED IN A LINKED QUEUE
				playerFour.addCard(p1card);
				playerFour.addCard(p3card);
				playerFour.addCard(p2card);
				playerFour.addCard(p4card);
				// STORE CARDS ON TABLE FOR NEXT ROUND
				cardTable.add(playerFour.peekCard());
				cardTable.add(playerOne.peekCard());
				cardTable.add(playerTwo.peekCard());
				cardTable.add(playerThree.peekCard());
				// STORE LINKED LIST AS PLAYER 4 BEING THE FIRST NODE. LINKED LIST IS FOR NEXT ROUND
				orderedPlayer.add(playerFour);
				orderedPlayer.add(playerOne);
				orderedPlayer.add(playerTwo);
				orderedPlayer.add(playerThree);
			}

		}

		/* CHECK IF PLAYER 3 AND PLAYER 1 IS THE LARGEST NUMBER */
		if( (largestNumberOne.getCardNumber() == p3card.getCardNumber()) && 
			(largestNumberOne.getSuit() == p3card.getSuit()) &&
			(largestNumberTwo.getCardNumber() == p1card.getCardNumber()) &&
			(largestNumberTwo.getSuit() == p1card.getSuit()) )
		{
			/* CHECK IF PLAYER 3 HAS THE LARGEST SUIT */
			if(p3card.getSuit() > p1card.getSuit())
			{
				System.out.println("***** " + playerThree.getName() + " WINS ROUND " + rounds + " *****");
				// STORE ALL PLAYERS CARD TO PLAYER 1. STORED IN A LINKED QUEUE
				playerThree.addCard(p4card);
				playerThree.addCard(p2card);
				playerThree.addCard(p1card);
				playerThree.addCard(p3card);
				// STORE CARDS ON TABLE FOR NEXT ROUND
				cardTable.add(playerThree.peekCard());
				cardTable.add(playerFour.peekCard());
				cardTable.add(playerOne.peekCard());
				cardTable.add(playerTwo.peekCard());
				// STORE LINKED LIST AS PLAYER 3 BEING THE FIRST NODE. LINKED LIST IS FOR NEXT ROUND.
				orderedPlayer.add(playerThree);
				orderedPlayer.add(playerFour);
				orderedPlayer.add(playerOne);
				orderedPlayer.add(playerTwo);			
			}

			/* CHECK IF PLAYER 1 HAS THE LARGEST SUIT */
			else if(p1card.getSuit() > p3card.getSuit())
			{
				System.out.println("***** " + playerOne.getName() + " WINS ROUND " + rounds + " *****");
				// STORE ALL PLAYERS CARD TO PLAYER 1. STORED IN A LINKED QUEUE
				playerOne.addCard(p4card);
				playerOne.addCard(p2card);
				playerOne.addCard(p3card);
				playerOne.addCard(p1card);
				// STORE CARDS ON TABLE FOR NEXT ROUND
				cardTable.add(playerOne.peekCard());
				cardTable.add(playerTwo.peekCard());
				cardTable.add(playerThree.peekCard());
				cardTable.add(playerFour.peekCard());
				// STORE LINKED LIST AS PLAYER 1 BEING THE FIRST NODE. LINKED LIST IS FOR NEXT ROUND
				orderedPlayer.add(playerOne);
				orderedPlayer.add(playerTwo);
				orderedPlayer.add(playerThree);
				orderedPlayer.add(playerFour);	
			}

		}

		/* CHECK IF PLAYER 3 AND PLAYER 2 IS THE LARGEST NUMBER */
		if( (largestNumberOne.getCardNumber() == p3card.getCardNumber()) && 
			(largestNumberOne.getSuit() == p3card.getSuit()) &&
			(largestNumberTwo.getCardNumber() == p2card.getCardNumber()) &&
			(largestNumberTwo.getSuit() == p2card.getSuit()) )
		{
			/* CHECK IF PLAYER 3 HAS THE LARGEST SUIT */
			if(p3card.getSuit() > p2card.getSuit())
			{
				System.out.println("***** " + playerThree.getName() + " WINS ROUND " + rounds + " *****");
				// STORE ALL PLAYERS CARD TO PLAYER 3. STORED IN A LINKED QUEUE
				playerThree.addCard(p4card);
				playerThree.addCard(p1card);
				playerThree.addCard(p2card);
				playerThree.addCard(p3card);
				// STORE CARDS ON TABLE FOR NEXT ROUND	
				cardTable.add(playerThree.peekCard());
				cardTable.add(playerFour.peekCard());
				cardTable.add(playerOne.peekCard());
				cardTable.add(playerTwo.peekCard());
				// STORE LINKED LIST AS PLAYER 3 BEING THE FIRST NODE. LINKED LIST IS FOR NEXT ROUND.
				orderedPlayer.add(playerThree);
				orderedPlayer.add(playerFour);
				orderedPlayer.add(playerOne);
				orderedPlayer.add(playerTwo);
			}

			/* CHECK IF PLAYER 2 HAS THE LARGEST SUIT */
			else if(p2card.getSuit() > p3card.getSuit())
			{
				System.out.println("***** " + playerTwo.getName() + " WINS ROUND " + rounds + " *****");
				// STORE ALL PLAYERS CARD TO PLAYER 2. STORED IN A LINKED QUEUE
				playerTwo.addCard(p4card);
				playerTwo.addCard(p1card);
				playerTwo.addCard(p3card);
				playerTwo.addCard(p2card);
				// STORE CARDS ON TABLE FOR NEXT ROUND
				cardTable.add(playerTwo.peekCard());
				cardTable.add(playerThree.peekCard());
				cardTable.add(playerFour.peekCard());
				cardTable.add(playerOne.peekCard());
				// STORE LINKED LIST AS PLAYER 3 BEING THE FIRST NODE. LINKED LIST IS FOR NEXT ROUND.
				orderedPlayer.add(playerTwo);
				orderedPlayer.add(playerThree);
				orderedPlayer.add(playerFour);
				orderedPlayer.add(playerOne);
			}
			
		}

		/* CHECK IF PLAYER 3 AND PLAYER 4 IS THE LARGEST NUMBER */
		if( (largestNumberOne.getCardNumber() == p3card.getCardNumber()) && 
			(largestNumberOne.getSuit() == p3card.getSuit()) &&
			(largestNumberTwo.getCardNumber() == p4card.getCardNumber()) &&
			(largestNumberTwo.getSuit() == p4card.getSuit()) )
		{
			/* CHECK IF PLAYER 3 HAS THE LARGEST SUIT */
			if(p3card.getSuit() > p4card.getSuit())
			{
				System.out.println("***** " + playerThree.getName() + " WINS ROUND " + rounds + " *****");
				// STORE ALL PLAYERS CARD TO PLAYER 3. STORED IN A LINKED QUEUE
				playerThree.addCard(p1card);
				playerThree.addCard(p2card);
				playerThree.addCard(p4card);
				playerThree.addCard(p3card);
				// STORE CARDS ON TABLE FOR NEXT ROUND
				cardTable.add(playerThree.peekCard());
				cardTable.add(playerFour.peekCard());
				cardTable.add(playerOne.peekCard());
				cardTable.add(playerTwo.peekCard());
				// STORE LINKED LIST AS PLAYER 3 BEING THE FIRST NODE. LINKED LIST IS FOR NEXT ROUND.
				orderedPlayer.add(playerThree);
				orderedPlayer.add(playerFour);
				orderedPlayer.add(playerOne);
				orderedPlayer.add(playerTwo);
			}

			/* CHECK IF PLAYER 4 HAS THE LARGEST SUIT */
			else if(p4card.getSuit() > p3card.getSuit())
			{
				System.out.println("***** " + playerFour.getName() + " WINS ROUND " + rounds + " *****");
				// STORE ALL PLAYERS CARD TO PLAYER 4. STORED IN A LINKED QUEUE
				playerFour.addCard(p1card);
				playerFour.addCard(p2card);
				playerFour.addCard(p3card);
				playerFour.addCard(p4card);
				// STORE CARDS ON TABLE FOR NEXT ROUND
				cardTable.add(playerFour.peekCard());
				cardTable.add(playerOne.peekCard());
				cardTable.add(playerTwo.peekCard());
				cardTable.add(playerThree.peekCard());
				// STORE LINKED LIST AS PLAYER 4 BEING THE FIRST NODE. LINKED LIST IS FOR NEXT ROUND
				orderedPlayer.add(playerFour);
				orderedPlayer.add(playerOne);
				orderedPlayer.add(playerTwo);
				orderedPlayer.add(playerThree);
			}
			
		}

		/* CHECK IF PLAYER 4 AND PLAYER 1 IS THE LARGEST NUMBER */
		if( (largestNumberOne.getCardNumber() == p4card.getCardNumber()) && 
			(largestNumberOne.getSuit() == p4card.getSuit()) &&
			(largestNumberTwo.getCardNumber() == p1card.getCardNumber()) &&
			(largestNumberTwo.getSuit() == p1card.getSuit()) )
		{
			/* CHECK IF PLAYER 4 HAS THE LARGEST SUIT */
			if(p4card.getSuit() > p1card.getSuit())
			{
				System.out.println("***** " + playerFour.getName() + " WINS ROUND " + rounds + " *****");
				// STORE ALL PLAYERS CARD TO PLAYER 4. STORED IN A LINKED QUEUE
				playerFour.addCard(p2card);
				playerFour.addCard(p3card);
				playerFour.addCard(p1card);
				playerFour.addCard(p4card);
				// STORE CARDS ON TABLE FOR NEXT ROUND
				cardTable.add(playerFour.peekCard());
				cardTable.add(playerOne.peekCard());
				cardTable.add(playerTwo.peekCard());
				cardTable.add(playerThree.peekCard());
				// STORE LINKED LIST AS PLAYER 4 BEING THE FIRST NODE. LINKED LIST IS FOR NEXT ROUND.
				orderedPlayer.add(playerFour);
				orderedPlayer.add(playerOne);
				orderedPlayer.add(playerTwo);
				orderedPlayer.add(playerThree);
			}

			/* CHECK IF PLAYER 1 HAS THE LARGEST SUIT */
			else if(p1card.getSuit() > p4card.getSuit())
			{
				System.out.println("***** " + playerOne.getName() + " WINS ROUND " + rounds + " *****");
				// STORE ALL PLAYERS CARD TO PLAYER 1. STORED IN A LINKED QUEUE
				playerOne.addCard(p2card);
				playerOne.addCard(p3card);
				playerOne.addCard(p4card);
				playerOne.addCard(p1card);
				// STORE CARDS ON TABLE FOR NEXT ROUND
				cardTable.add(playerOne.peekCard());
				cardTable.add(playerTwo.peekCard());
				cardTable.add(playerThree.peekCard());
				cardTable.add(playerFour.peekCard());
				// STORE LINKED LIST AS PLAYER 1 BEING THE FIRST NODE. LINKED LIST IS FOR NEXT ROUND
				orderedPlayer.add(playerOne);
				orderedPlayer.add(playerTwo);
				orderedPlayer.add(playerThree);
				orderedPlayer.add(playerFour);	
			}	
		}

		/* CHECK IF PLAYER 4 AND PLAYER 2 IS THE LARGEST NUMBER */
		if( (largestNumberOne.getCardNumber() == p4card.getCardNumber()) && 
			(largestNumberOne.getSuit() == p4card.getSuit()) &&
			(largestNumberTwo.getCardNumber() == p2card.getCardNumber()) &&
			(largestNumberTwo.getSuit() == p2card.getSuit()) )
		{
			/* CHECK IF PLAYER 4 HAS THE LARGEST SUIT */
			if(p4card.getSuit() > p2card.getSuit())
			{
				System.out.println("***** " + playerFour.getName() + " WINS ROUND " + rounds + " *****");
				// STORE ALL PLAYERS CARD TO PLAYER 4. STORED IN A LINKED QUEUE
				playerFour.addCard(p1card);
				playerFour.addCard(p3card);
				playerFour.addCard(p2card);
				playerFour.addCard(p4card);
				// STORE CARDS ON TABLE FOR NEXT ROUND
				cardTable.add(playerFour.peekCard());
				cardTable.add(playerOne.peekCard());
				cardTable.add(playerTwo.peekCard());
				cardTable.add(playerThree.peekCard());
				// STORE LINKED LIST AS PLAYER 4 BEING THE FIRST NODE. LINKED LIST IS FOR NEXT ROUND.
				orderedPlayer.add(playerFour);
				orderedPlayer.add(playerOne);
				orderedPlayer.add(playerTwo);
				orderedPlayer.add(playerThree);
			}

			/* CHECK IF PLAYER 2 HAS THE LARGEST SUIT */
			else if(p2card.getSuit() > p4card.getSuit())
			{
				System.out.println("***** " + playerTwo.getName() + " WINS ROUND " + rounds + " *****");
				// STORE ALL PLAYERS CARD TO PLAYER 2. STORED IN A LINKED QUEUE
				playerTwo.addCard(p1card);
				playerTwo.addCard(p3card);
				playerTwo.addCard(p4card);
				playerTwo.addCard(p2card);
				// STORE CARDS ON TABLE FOR NEXT ROUND
				cardTable.add(playerTwo.peekCard());
				cardTable.add(playerThree.peekCard());
				cardTable.add(playerFour.peekCard());
				cardTable.add(playerOne.peekCard());
				// STORE LINKED LIST AS PLAYER 2 BEING THE FIRST NODE. LINKED LIST IS FOR NEXT ROUND
				orderedPlayer.add(playerTwo);
				orderedPlayer.add(playerThree);
				orderedPlayer.add(playerFour);
				orderedPlayer.add(playerOne);
			}	
		}

		/* CHECK IF PLAYER 4 AND PLAYER 3 IS THE LARGEST NUMBER */
		if( (largestNumberOne.getCardNumber() == p4card.getCardNumber()) && 
			(largestNumberOne.getSuit() == p4card.getSuit()) &&
			(largestNumberTwo.getCardNumber() == p3card.getCardNumber()) &&
			(largestNumberTwo.getSuit() == p3card.getSuit()) )
		{
			/* CHECK IF PLAYER 4 HAS THE LARGEST SUIT */
			if(p4card.getSuit() > p3card.getSuit())
			{
				System.out.println("***** " + playerFour.getName() + " WINS ROUND " + rounds + " *****");
				// STORE ALL PLAYERS CARD TO PLAYER 4. STORED IN A LINKED QUEUE
				playerFour.addCard(p1card);
				playerFour.addCard(p2card);
				playerFour.addCard(p3card);
				playerFour.addCard(p4card);
				// STORE CARDS ON TABLE FOR NEXT ROUND
				cardTable.add(playerFour.peekCard());
				cardTable.add(playerOne.peekCard());
				cardTable.add(playerTwo.peekCard());
				cardTable.add(playerThree.peekCard());
				// STORE LINKED LIST AS PLAYER 4 BEING THE FIRST NODE. LINKED LIST IS FOR NEXT ROUND.
				orderedPlayer.add(playerFour);
				orderedPlayer.add(playerOne);
				orderedPlayer.add(playerTwo);
				orderedPlayer.add(playerThree);
			}

			/* CHECK IF PLAYER 3 HAS THE LARGEST SUIT */
			else if(p3card.getSuit() > p4card.getSuit())
			{
				System.out.println("***** " + playerThree.getName() + " WINS ROUND " + rounds + " *****");
				// STORE ALL PLAYERS CARD TO PLAYER 3. STORED IN A LINKED QUEUE
				playerThree.addCard(p1card);
				playerThree.addCard(p2card);
				playerThree.addCard(p4card);
				playerThree.addCard(p3card);
				// STORE CARDS ON TABLE FOR NEXT ROUND
				cardTable.add(playerThree.peekCard());
				cardTable.add(playerFour.peekCard());
				cardTable.add(playerOne.peekCard());
				cardTable.add(playerTwo.peekCard());
				// STORE LINKED LIST AS PLAYER 3 BEING THE FIRST NODE. LINKED LIST IS FOR NEXT ROUND
				orderedPlayer.add(playerThree);
				orderedPlayer.add(playerFour);
				orderedPlayer.add(playerOne);
				orderedPlayer.add(playerTwo);
			}
			
		}
	}
	// Function Outputs the Winner with the total Cards for each Player
	public static void declareWinner()
	{
		int sameNumber = 0;
		// Check If Player 1 is the Winner
		if( 				(playerOne != null) &&
			(playerOne.getTotalCards() >= playerTwo.getTotalCards()) && 
			(playerOne.getTotalCards() >= playerThree.getTotalCards()) &&
			(playerOne.getTotalCards() >= playerFour.getTotalCards()) )
		{
			sameNumber = playerOne.getTotalCards();
			// Check if Player 1 Total Cards == Player 2 Total Cards
			if(sameNumber == playerTwo.getTotalCards()) 
			{
				System.out.println("ITS A TIE ");
				System.out.println(playerOne.getName() + " & " + playerTwo.getName() + " WINS");
				System.out.println(playerOne.getName() + " total cards " + playerOne.getTotalCards());
				System.out.println(playerTwo.getName() + " total cards " + playerTwo.getTotalCards());
				System.out.println(playerThree.getName() + " total cards " + playerThree.getTotalCards());
				System.out.println(playerFour.getName() + " total cards " + playerFour.getTotalCards());
				return;
			}
			// Check if Player 1 Total Cards == Player 3 Total Cards
			if(sameNumber == playerThree.getTotalCards()) 
			{
				System.out.println("ITS A TIE ");
				System.out.println(playerOne.getName() + " & " + playerThree.getName() + " WINS");
				System.out.println(playerOne.getName() + " total cards " + playerOne.getTotalCards());
				System.out.println(playerThree.getName() + " total cards " + playerThree.getTotalCards());
				System.out.println(playerFour.getName() + " total cards " + playerFour.getTotalCards());
				System.out.println(playerTwo.getName() + " total cards " + playerTwo.getTotalCards());
				return;
			}
			// Check if Player 1 Total Cards == Player 4 Total Cards
			if(sameNumber == playerFour.getTotalCards())  
			{
				System.out.println("ITS A TIE ");
				System.out.println(playerOne.getName() + " & " + playerFour.getName() + " WINS");
				System.out.println(playerOne.getName() + " total cards " + playerOne.getTotalCards());	
				System.out.println(playerFour.getName() + " total cards " + playerFour.getTotalCards());
				System.out.println(playerTwo.getName() + " total cards " + playerTwo.getTotalCards());
				System.out.println(playerThree.getName() + " total cards " + playerThree.getTotalCards());
				return;	
			}
			System.out.println(playerOne.getName() + " is the winner ");
			System.out.println(playerOne.getName() + " total cards " + playerOne.getTotalCards());
			System.out.println(playerTwo.getName() + " total cards " + playerTwo.getTotalCards());
			System.out.println(playerThree.getName() + " total cards " + playerThree.getTotalCards());
			System.out.println(playerFour.getName() + " total cards " + playerFour.getTotalCards());
		}

			// Check If Player 2 is the Winner
		if( 				(playerTwo != null) && 						
			(playerTwo.getTotalCards() >= playerOne.getTotalCards()) && 
			(playerTwo.getTotalCards() >= playerThree.getTotalCards()) &&
			(playerTwo.getTotalCards() >= playerFour.getTotalCards()) )
		{
			sameNumber = playerTwo.getTotalCards();
			// Check if Player 2 Total Cards == Player 1 Total Cards
			if(sameNumber == playerOne.getTotalCards()) 
			{
				System.out.println("ITS A TIE ");
				System.out.println(playerTwo.getName() + " & " + playerOne.getName() + " WINS");
				System.out.println(playerTwo.getName() + " total cards " + playerTwo.getTotalCards());
				System.out.println(playerOne.getName() + " total cards " + playerOne.getTotalCards());
				System.out.println(playerThree.getName() + " total cards " + playerThree.getTotalCards());
				System.out.println(playerFour.getName() + " total cards " + playerFour.getTotalCards());
				return;
			}
			// Check if Player 2 Total Cards == Player 3 Total Cards
			if(sameNumber == playerThree.getTotalCards()) 
			{ 
				System.out.println("ITS A TIE ");
				System.out.println(playerTwo.getName() + " & " + playerThree.getName() + " WINS");
				System.out.println(playerTwo.getName() + " total cards " + playerTwo.getTotalCards());
				System.out.println(playerThree.getName() + " total cards " + playerThree.getTotalCards());
				System.out.println(playerFour.getName() + " total cards " + playerFour.getTotalCards());
				System.out.println(playerOne.getName() + " total cards " + playerOne.getTotalCards());
				return;	
			}
			// Check if Player 2 Total Cards == Player 4 Total Cards
			if(sameNumber == playerFour.getTotalCards())  
			{
				System.out.println("ITS A TIE ");
				System.out.println(playerTwo.getName() + " & " + playerFour.getName() + " WINS");
				System.out.println(playerTwo.getName() + " total cards " + playerTwo.getTotalCards());
				System.out.println(playerFour.getName() + " total cards " + playerFour.getTotalCards());
				System.out.println(playerOne.getName() + " total cards " + playerOne.getTotalCards());
				System.out.println(playerThree.getName() + " total cards " + playerThree.getTotalCards());
				return;
			}
			System.out.println(playerTwo.getName() + " is the winner ");
			System.out.println(playerTwo.getName() + " total cards " + playerTwo.getTotalCards());
			System.out.println(playerThree.getName() + " total cards " + playerThree.getTotalCards());
			System.out.println(playerFour.getName() + " total cards " + playerFour.getTotalCards());
			System.out.println(playerOne.getName() + " total cards " + playerOne.getTotalCards());
		}

			// Check If Player 3 is the Winner
		if( 				(playerThree != null) &&
			(playerThree.getTotalCards() >= playerOne.getTotalCards()) && 
			(playerThree.getTotalCards() >= playerTwo.getTotalCards()) &&
			(playerThree.getTotalCards() >= playerFour.getTotalCards()) )
		{
			sameNumber = playerThree.getTotalCards();
			// Check if Player 3 Total Cards == Player 1 Total Cards
			if(sameNumber == playerOne.getTotalCards()) 
			{
				System.out.println("ITS A TIE ");
				System.out.println(playerThree.getName() + " & " + playerOne.getName() + " WINS");
				System.out.println(playerThree.getName() + " total cards " + playerThree.getTotalCards());			
				System.out.println(playerOne.getName() + " total cards " + playerOne.getTotalCards());
				System.out.println(playerTwo.getName() + " total cards " + playerTwo.getTotalCards());	
				System.out.println(playerFour.getName() + " total cards " + playerFour.getTotalCards());
				return;
			}
			// Check if Player 3 Total Cards == Player 2 Total Cards
			if(sameNumber == playerTwo.getTotalCards())
			{ 
				System.out.println("ITS A TIE ");
				System.out.println(playerThree.getName() + " & " + playerTwo.getName() + " WINS");			
				System.out.println(playerThree.getName() + " total cards " + playerThree.getTotalCards());
				System.out.println(playerTwo.getName() + " total cards " + playerTwo.getTotalCards());
				System.out.println(playerFour.getName() + " total cards " + playerFour.getTotalCards());
				System.out.println(playerOne.getName() + " total cards " + playerOne.getTotalCards());
				return;
			}
			// Check if Player 3 Total Cards == Player 4 Total Cards
			if(sameNumber == playerFour.getTotalCards()) 
			{
				System.out.println("ITS A TIE ");
				System.out.println(playerThree.getName() + " & " + playerFour.getName() + " WINS");
				System.out.println(playerThree.getName() + " total cards " + playerThree.getTotalCards());
				System.out.println(playerFour.getName() + " total cards " + playerFour.getTotalCards());
				System.out.println(playerOne.getName() + " total cards " + playerOne.getTotalCards());
				System.out.println(playerTwo.getName() + " total cards " + playerTwo.getTotalCards());
				return;
			}
			System.out.println(playerThree.getName() + " is the winner ");
			System.out.println(playerThree.getName() + " total cards " + playerThree.getTotalCards());
			System.out.println(playerFour.getName() + " total cards " + playerFour.getTotalCards());
			System.out.println(playerOne.getName() + " total cards " + playerOne.getTotalCards());
			System.out.println(playerTwo.getName() + " total cards " + playerTwo.getTotalCards());
		}


			// Check If Player 4 is the Winner
		if( 				(playerFour != null) &&
			(playerFour.getTotalCards() >= playerOne.getTotalCards()) && 
			(playerFour.getTotalCards() >= playerTwo.getTotalCards()) &&
			(playerFour.getTotalCards() >= playerThree.getTotalCards()) )
		{
			sameNumber = playerFour.getTotalCards();
			// Check if Player 4 Total Cards == Player 1 Total Cards
			if(sameNumber == playerOne.getTotalCards()) 
			{
				System.out.println("ITS A TIE ");
				System.out.println(playerFour.getName() + " & " + playerOne.getName() + " WINS");
				System.out.println(playerFour.getName() + " total cards " + playerFour.getTotalCards());
				System.out.println(playerOne.getName() + " total cards " + playerOne.getTotalCards());
				System.out.println(playerTwo.getName() + " total cards " + playerTwo.getTotalCards());
				System.out.println(playerThree.getName() + " total cards " + playerThree.getTotalCards());
				return;
			}
			// Check if Player 4 Total Cards == Player 2 Total Cards
			if(sameNumber == playerTwo.getTotalCards())
			{ 
				System.out.println("ITS A TIE ");
				System.out.println(playerFour.getName() + " & " + playerTwo.getName() + " WINS");
				System.out.println(playerFour.getName() + " total cards " + playerFour.getTotalCards());
				System.out.println(playerTwo.getName() + " total cards " + playerTwo.getTotalCards());
				System.out.println(playerThree.getName() + " total cards " + playerThree.getTotalCards());
				System.out.println(playerOne.getName() + " total cards " + playerOne.getTotalCards());
				return;   	
			}
			// Check if Player 4 Total Cards == Player 3 Total Cards
			if(sameNumber == playerThree.getTotalCards())
			{
				System.out.println("ITS A TIE ");
				System.out.println(playerFour.getName() + " & " + playerThree.getName() + " WINS");
				System.out.println(playerFour.getName() + " total cards " + playerFour.getTotalCards());
				System.out.println(playerThree.getName() + " total cards " + playerThree.getTotalCards());	
				System.out.println(playerOne.getName() + " total cards " + playerOne.getTotalCards());
				System.out.println(playerTwo.getName() + " total cards " + playerTwo.getTotalCards());
				return;
			}
			System.out.println(playerFour.getName() + " is the winner ");	
			System.out.println(playerFour.getName() + " total cards " + playerFour.getTotalCards());
			System.out.println(playerOne.getName() + " total cards " + playerOne.getTotalCards());
			System.out.println(playerTwo.getName() + " total cards " + playerTwo.getTotalCards());	
			System.out.println(playerThree.getName() + " total cards " + playerThree.getTotalCards());	
		}
	}
}