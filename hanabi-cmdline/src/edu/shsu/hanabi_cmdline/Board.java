package edu.shsu.hanabi_cmdline;

public class Board {
	private Card[] deck;
	private int clockTokens = 8;
	private int fuseTokens = 3;
	
	public Board (int size) {
		
	}
	
	public void createDeck () {
		
	}
	
	public Card[] getDeck() {
		return this.deck;
	}
	
	public int getClockTokens() {
		return this.clockTokens;
	}
	
	public int getFuseTokens() {
		return this.fuseTokens;
	}
}
