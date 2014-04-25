package edu.shsu.hanabi_cmdline;

public class DeckPlayer implements Deck {
	private Card[] hand;
	private int cardCount;
	private int deckSize;
	
	public DeckPlayer (int size) {
		hand = new Card[size];
		cardCount = size;	//	Each player will start out with the max hand size.
		deckSize = size;
	}
	
	public void push(Card c) {
		hand[cardCount++] = c;
	}
	
	public Card pop() {
		return null;
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
	
	public Card remove(int pos) {
		Card temp = hand[pos];
		for (int i = pos; i < cardCount-1; i++) {
			hand[pos] = hand[pos+1];
		}
		cardCount--;
		return temp;
	}
}
