/*
	Danny Rivera
	CSC 311 - 01
	Dr Amlan Chatterjee
	Project 03
	April 6, 2017

	 Citation:
  	Java Programming from the Ground Up
 	 By Ralph Bravaco & Shai Simonson
*/
public class Card
{
	private int suit;	
	private int cardNumber;
	private String name = null;

	public Card()
	{
		suit = 0;
		cardNumber = 0;
	}
	public Card(int suit, int cardNumber)
	{
		this.suit = suit;
		this.cardNumber = cardNumber;
		
	}
	// Setter Method for Suit
	public int setSuit(int n)
	{
		this.suit = 0;
		return this.suit;
	}
	// Setter Method for Card Number
	public int setCardNumber(int n)
	{
		this.cardNumber = 0;
		return this.cardNumber;
	}
	// Getter Method for Suit
	public int getSuit()
	{
		return this.suit;
	}
	// Getter Method for Card Number
	public int getCardNumber()
	{
		return this.cardNumber;
	}
	// Getter Method. Return String of Suit
	public String getSuitName()
	{
		switch(suit)
		{
			case 1:
					name = "Spades of";
					break;
			case 2:
					name = "Hearts of";
					break;
			case 3:
					name = "Diamonds of";
					break;
			case 4:
					name = "Clubs of";
					break;
			default:
					name = " ";
					break;
		}
		return name;
	}
	// Getter Method. Return String of Card Number
	public String getCardName()
	{
		switch(cardNumber)
		{
			case 1:
					name = "Ace";
					break;
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
					name = String.valueOf(cardNumber);
					break;
			case 11:
					name = "Jack";
					break;
			case 12:
					name = "Queen";
					break;
			case 13:
					name = "King";
					break;
			default:
					name = " ";
					break;
		}
		return name;
	}
}