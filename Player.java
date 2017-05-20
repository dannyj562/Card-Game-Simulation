/*
	Danny Rivera
	CSC 311 - 01
	Dr Amlan Chatterjee
	Project 03
	April 6, 2017

	The Player class HAS a Name
	The Player class HAS a Linked List Queue
	The Player class HAS the number of total cards
	The Player class has a Card to withdraw
*/
public class Player
{
	private String name;	// Players Name
	private LinkedListQueue cards; // Link Queue
	private int totalCards;	// Total Cards
	private Card withdraw;	// Withdraw Card


	public Player(String name)
	{
		this.name = name;
		cards = new LinkedListQueue();
		totalCards = 0;
	}
	// Add Card To A Link Queue
	public void addCard(Card card)
	{
		Object temp = card;
		cards.insert(temp);	// Add Object to Link Queue
		totalCards++;	// Increment Total Cards
	}
	// Peek Card from A Link Queue
	public Card peekCard()
	{
		withdraw = (Card) cards.peek();	// Peek Card from Link Queue	
		return withdraw;
	}
	// Remove Card from A Link Queue
	public Card getCard()
	{
		withdraw = (Card) cards.remove();	// Remove Card from Link Queue
		totalCards--;	// Decrement Total Cards
		return withdraw;
	}

	// Getter Method for Name
	public String getName()
	{
		return this.name;
	}
	// Getter Method for Size
	public int getSize()
	{
		return cards.getSize();
	}
	// Getter Method for Total Cards
	public int getTotalCards()
	{
		return this.totalCards;
	}
	// Setter method to set Total Cards
	public int setTotalCards(int n)
	{
		this.totalCards = n;
		return this.totalCards;
	}
	// Display Card Prompt
	public void displayCard()
	{
		if(this.totalCards == 0)	// If Player's Total Cards == 0
		{
			System.out.println("***** " + this.name + " is DISQUALIFIED!!! *****");
			return;
		}
		System.out.println(this.name + " drops a " + withdraw.getSuitName() + " "  + withdraw.getCardName() + " on the table");
	}
}