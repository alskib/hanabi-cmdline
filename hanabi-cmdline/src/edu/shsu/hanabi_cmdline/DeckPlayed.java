package edu.shsu.hanabi_cmdline;

public class DeckPlayed implements Deck {
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
		for (int i = 0; i < cardCount; i++) {
			System.out.print(this.cards[i].getNumber() + " ");
		}
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
}
