package edu.shsu.hanabi_cmdline;

import java.util.Random;

public class DeckDraw implements Deck {
	private Card[] cards;
	private int cardCount;
	private int deckSize;
	
	public DeckDraw(int size) {
		this.cards = new Card[size];
		this.cardCount = 0;
		this.deckSize = size;
		initializeDeck();
		shuffleDeck(this.cards);
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
	
	private void shuffleDeck(Card[] d) {
		int n = deckSize;
		Random rand = new Random();
		rand.nextInt();
		for (int i = 0; i < n; i++) {
			int change = i + rand.nextInt(n-i);
			Card temp = d[i];
			d[i] = d[change];
			d[change] = temp;
		}
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
		Card c = cards[cardCount-1];
		cardCount--;
		return c;
	}
	
	public int getCardCount() {
		return this.cardCount;
	}
	
	public boolean isEmpty() {
		if (this.cardCount == 0)
			return true;
		return false;
	}
	
	public boolean isFull() {
		if (this.cardCount == this.deckSize)
			return true;
		return false;
	}
	
	

}
