package edu.shsu.hanabi_cmdline;

public class DeckDiscard implements DeckColored {
	private Card[] cards;
	private String color;
	private int cardCount;
	private int deckSize;
	
	//	Deck of discarded cards
	public DeckDiscard(int size, String c) {
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
		sort(this.cards);
	}
	
	public Card pop() {
		return null;
	}
	
	private void sort(Card[] deck) {
		//	Insertion sort
		for (int i = 1; i < cardCount; i++) {
			Card temp = deck[i];
			int j;
			for (j = i-1; i >= 0 && temp.getNumber() < deck[j].getNumber(); j--) {
				deck[j+1] = deck[j];
				deck[j+1] = temp;
			}
		}
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
