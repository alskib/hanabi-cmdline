package edu.shsu.hanabi_cmdline;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class DeckPlayer implements Deck {
	private Card[] hand;
	private int cardCount;
	private int deckSize;
	
	//	"Deck" of cards for each player - essentially their hand of cards
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
	
	public int getDeckSize() {
		return this.deckSize;
	}
	
	public void insertCard(Card card) {
		hand[hand.length-1] = card;	//	Insert card to end of hand.
	}
	
	public Card removeCard(int pos) {
		Card temp = hand[pos];
		for (int i = pos; i < hand.length-1; i++) {	//	Fill in gap from removed card
			hand[i] = hand[i+1];
		}
		cardCount--;
		return temp;
	}
	
	public void iterateDeck(String name) {
		if (isEmpty())
			return;
		System.out.println(name + "'s hand: ");
		for (int i = 0; i < cardCount; i++) {
			System.out.print("[" + this.hand[i].getColor() + " " + this.hand[i].getNumber() + "] ");
		}
		System.out.println("");
	}
	
	public ArrayList<Integer> searchForElement(int num) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (int i = 0; i < getDeckSize(); i++) {
			if (this.hand[i].getNumber() == num) {
				arr.add(i);
			}
		}
		return arr;
	}
	
	public ArrayList<Integer> searchForElement(String color) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (int i = 0; i < getDeckSize(); i++) {
			if (this.hand[i].getColor().equals(color)) {
				arr.add(i);
			}
		}
		return arr;
	}
	
	public Set<Integer> getNumbersInHand() {
		Set<Integer> numArray = new TreeSet<Integer>();
		for (int i = 0; i < this.hand.length; i++) {
			numArray.add(this.hand[i].getNumber());
		}
		return numArray;
	}
	
	public Set<Integer> getColorsInHand() {
		Set<Integer> colorArray = new TreeSet<Integer>();
		for (int i = 0; i < this.hand.length; i++) {
			switch(this.hand[i].getColor()) {
				case "blue":	colorArray.add(1);
								break;
				case "green":	colorArray.add(2);
								break;
				case "red":		colorArray.add(3);
								break;
				case "white":	colorArray.add(4);
								break;
				case "yellow":	colorArray.add(5);
								break;
			}
		}
		return colorArray;
	}
}