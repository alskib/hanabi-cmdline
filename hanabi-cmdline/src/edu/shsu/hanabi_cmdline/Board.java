package edu.shsu.hanabi_cmdline;

public class Board {
	private int clockTokens = 8;
	private int fuseTokens = 3;
	DeckPlayed playedDeckBlue, playedDeckGreen, playedDeckRed, playedDeckWhite, playedDeckYellow;
	DeckDiscard discardDeckBlue, discardDeckGreen, discardDeckRed, discardDeckWhite, discardDeckYellow;
	
	public Board (int playerNum) {
		DeckDraw drawDeck = 		new DeckDraw(50);
		this.playedDeckBlue = 		new DeckPlayed(5, "blue");
		this.playedDeckGreen = 		new DeckPlayed(5, "green");
		this.playedDeckRed = 		new DeckPlayed(5, "red");
		this.playedDeckWhite = 		new DeckPlayed(5, "white");
		this.playedDeckYellow = 	new DeckPlayed(5, "yellow");
		this.discardDeckBlue = 		new DeckDiscard(10, "blue");
		this.discardDeckGreen = 	new DeckDiscard(10, "green");
		this.discardDeckRed =		new DeckDiscard(10, "red");
		this.discardDeckWhite = 	new DeckDiscard(10, "white");
		this.discardDeckYellow = 	new DeckDiscard(10, "yellow");
	}
	
	public void showDiscard() {
		System.out.println("Discarded cards:");
		System.out.print("Blue: "); 	this.discardDeckBlue.iterateDeck(); 	System.out.println("");
		System.out.print("Green: "); 	this.discardDeckGreen.iterateDeck(); 	System.out.println("");
		System.out.print("Red: "); 		this.discardDeckRed.iterateDeck(); 		System.out.println("");
		System.out.print("White: "); 	this.discardDeckWhite.iterateDeck(); 	System.out.println("");
		System.out.print("Yellow: ");	this.discardDeckYellow.iterateDeck(); 	System.out.println("");
	}
	
	public void showPlayed() {
		System.out.println("Played cards:");
		System.out.print("Blue: "); 	this.playedDeckBlue.iterateDeck(); 		System.out.println("");
		System.out.print("Green: "); 	this.playedDeckGreen.iterateDeck(); 	System.out.println("");
		System.out.print("Red: "); 		this.playedDeckRed.iterateDeck(); 		System.out.println("");
		System.out.print("White: "); 	this.playedDeckWhite.iterateDeck(); 	System.out.println("");
		System.out.print("Yellow: ");	this.playedDeckYellow.iterateDeck(); 	System.out.println("");
	}
	
	public int getClockTokens() {
		return this.clockTokens;
	}
	
	public int getFuseTokens() {
		return this.fuseTokens;
	}
	
	public void incClockTokens() {
		if (this.clockTokens <= 8)
			this.clockTokens++;
	}
	
	
}
