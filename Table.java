/*
	Danny Rivera
	CSC 311 - 01
	Dr Amlan Chatterjee
	Project 03
	April 6, 2017
	
	The Table class HAS a Card
	The Table class HAS a Linked List Queue that stores the Card
*/
public class Table
{
	private Card currentCard;
	private LinkedListQueue linkQueue;

	public Table()
	{
		linkQueue = new LinkedListQueue();

	}
	/* Add Card to Link Queue */
	public void add(Card card)
	{
		Object temp = card;
		linkQueue.insert(temp);

	}
	/* Peek Card from Link Queue */
	public Card peekCard()
	{
		currentCard = (Card) linkQueue.peek();
		return currentCard;

	}
	/* Remove Card from Link Queue */
	public Card getCard()
	{
		currentCard = (Card) linkQueue.remove();
		return currentCard;

	}
	/* Getter Method for Get Size of Link Queue */
	public int getCardSize()
	{
		return linkQueue.getSize();
	}
}