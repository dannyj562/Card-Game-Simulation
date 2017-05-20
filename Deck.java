/*
  Danny Rivera
  CSC 311 - 01
  Dr Amlan Chatterjee
  Project 03
  April 6, 2017
  The Deck class has an array of Cards
  
  Citation:
  Java Programming from the Ground Up
  By Ralph Bravaco & Shai Simonson
*/
import java.util.Random;
public class Deck
{
	private Card deck[]; // Array Stores deck of cards
	private int next;

	public Deck()
	{
		deck = new Card[53];

		for(int rank=1; rank<=13; rank++)
		{
			deck[rank] = new Card(1, rank);  // 1 (spades) Rank 1 - 13 
			deck[rank+13] = new Card(2, rank); // 2 (hearts) Rank 1 - 13
			deck[rank+26] = new Card(3, rank); // 3 (diamonds) Rank 1 -13
			deck[rank+39] = new Card(4, rank); // 4 (clubs)  Rank 1 - 13
		}
		next = 1;
		shuffle();  // Calls function to shuffle deck of cards
	}
  // Shuffle the deck of cards
	public void shuffle()
  {
    	Random randomNumber = new Random();  // Initialize Random
        for (int card = 1; card <= 52; card++)  // Loop 52 times
        {
        	// find a random place in the deck
            int rand = randomNumber.nextInt(52) + 1;  // Random Number between 1 - 52
            //swap deck[i] with deck[rand]
            Card temp = deck[card]; // Store each element into a temporary Card
            deck[card] = deck[rand];  // Swap 
            deck[rand] = temp;
        }
        next = 1; // top card of the deck
  }


  public Card deal()
  {
      if (next > 52)  // if deck is depleted
        shuffle();
      Card c = deck[next];
      next++;   // Increment next
      return c;
  }
  
}