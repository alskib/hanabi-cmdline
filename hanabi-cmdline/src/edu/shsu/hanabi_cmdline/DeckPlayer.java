package edu.shsu.hanabi_cmdline;

public class DeckPlayer implements Deck {
	private Card[] hand;
	private int cardCount;
	private int deckSize;
	
	public DeckPlayer (int size) {
		hand = new Card[size];
		cardCount = 0;
		deckSize = size;
	}
	
	public void push(Card c) {
		hand[cardCount] = c;
		cardCount++;
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
	
	public void insertCard(Card card) {
		hand[hand.length-1] = card;	//	Insert card to end of hand.
	}
	
	public Card remove(int pos) {
		Card temp = hand[pos];
		for (int i = pos; i < cardCount-1; i++) {
			hand[pos] = hand[pos+1];
		}
		cardCount--;
		return temp;
	}
	
	public Card removeCard(int pos) {
		Card temp = hand[pos];
		for (int i = pos; i < hand.length; i++) {	//	Fill in gap from removed card
			hand[i] = hand[i+1];
		}
		cardCount--;
		return temp;
	}
	
	public void iterateDeck() {
		if (isEmpty())
			return;
		System.out.println("Your hand consists of: ");
		for (int i = 0; i < cardCount; i++) {
			System.out.print(this.hand[i].getNumber() + " ");
		}
	}
	
//	Get number as info
	public void getInfo(int num, int[] pos) {
		for (int i = 0; i < pos.length; i++) {
			System.out.println("Card in position " + pos[i] + "is of number " + num);
		}
	}
	
	// Get color as info
	public void getInfo(String color, int[] pos) {
		for (int i = 0; i < pos.length; i++) {
			System.out.println("Card in position " + pos[i] + "is of color " + color);
		}
	}
}
