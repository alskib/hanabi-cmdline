package edu.shsu.hanabi_cmdline;

public class main {
	
	public static void main(String[] args) {
		DeckDraw deck = new DeckDraw(50);
		deck.iterateDeck();
		System.out.println(deck.getCardCount());
	}
}
