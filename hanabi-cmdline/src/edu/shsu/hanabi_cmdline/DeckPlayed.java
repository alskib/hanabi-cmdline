package edu.shsu.hanabi_cmdline;

public class DeckPlayed implements DeckColored {
	private Card[] cards;
	private String color;
	private int cardCount;
	private int deckSize;
	
	public DeckPlayed(int size, String c) {
		this.cards = new Card[size];
		this.color = c;
		this.cardCount = 0;
		this.deckSize = size;
		
	}
	
	public void iterateDeck() {
		if (isEmpty())
			return;
		System.out.print(this.color + ": ");
		for (int i = 0; i < cardCount; i++) {
			System.out.print(this.cards[i].getNumber() + " ");
		}
		System.out.println("");
	}
	
	public void push(Card c) {
		this.cards[cardCount++] = c;
	}
	
	public Card pop() {
		return null;
	}
	
	public String getDeckColor() {
		return this.color;
	}
	
	public boolean isEmpty() {
		if (cardCount == 0)
			return true;
		return false;
	}
	
	public boolean isFull() {
		if (this.cardCount == this.deckSize)
			return true;
		return false;
	}
	
	public boolean insertIntoPlayed(Card c) {
//	public boolean push(Card c) {
		//	Since the played deck can only be built in a specific order (1 to 5),
		//		if the player is attempting to play a 3, I only have to test if
		//		position cards[1] contains a card of number 2.
		//	Insert 1 - test if empty and card.getNumber() == 1
		//	Insert 2 - test cards[0].getNumber() == 1
		//	Insert 3 - test cards[1].getNumber() == 2
		//	etc.
		int cardNumber = c.getNumber();
		if (isFull())	//	Full deck, so no more cards of this color can be played
			return false;
		if (cardNumber == 1) {	//	If card is 1, empty deck is success. Non-empty is failure.
			if (isEmpty()) {
				push(c);
				return true;
			} else {
				return false;
			}
		}
		
		if (this.cards[cardNumber-2].getNumber() == cardNumber-1) {
			push(c);
			return true;
		}
		return false;
	}
}
