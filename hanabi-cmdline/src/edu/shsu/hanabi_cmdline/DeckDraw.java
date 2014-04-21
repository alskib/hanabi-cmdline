package edu.shsu.hanabi_cmdline;

public class DeckDraw implements Deck {
	private Card[] cards;
	private int cardCount;
	private int deckSize;
	
	public DeckDraw(int size) {
		this.cards = new Card[size];
		this.cardCount = 0;
		this.deckSize = size;
		initializeDeck();
	}
	
	public void initializeDeck() {
		initializeColor("blue");
		initializeColor("green");
		initializeColor("red");
		initializeColor("white");
		initializeColor("yellow");
	}
	
	private void initializeColor(String color) {
		int tempCount = cardCount;
		for (int i = cardCount; i < tempCount+3; i++) {
			this.cards[i] = new Card(1, color);
			this.cardCount++;
		}
		tempCount = cardCount;
		for (int i = cardCount; i < tempCount+2; i++) {
			this.cards[i] = new Card(2, color);
			this.cardCount++;
		}
		tempCount = cardCount;
		for (int i = cardCount; i < tempCount+2; i++) {
			this.cards[i] = new Card(3, color);
			this.cardCount++;
		}
		tempCount = cardCount;
		for (int i = cardCount; i < tempCount+2; i++) {
			this.cards[i] = new Card(4, color);
			this.cardCount++;
		}
		this.cards[cardCount++] = new Card(5, color);
	}
	
	public void iterateDeck() {
		for (int i = 0; i < cardCount; i++) {
			System.out.println(this.cards[i].getNumber() + " " +
							   this.cards[i].getColor());
			
		}
	}
	
	public void push(Card c) {
		cardCount++;
		this.cards[cardCount] = c;
	}
	
	public Card pop() {
		Card c = cards[cardCount];
		cardCount--;
		return c;
	}
	
	public int getCardCount() {
		return this.cardCount;
	}
	
	
	

}
