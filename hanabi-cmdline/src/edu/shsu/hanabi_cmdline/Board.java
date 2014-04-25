package edu.shsu.hanabi_cmdline;

import java.util.Scanner;
import java.util.Stack;

public class Board {
	private Scanner sc = new Scanner(System.in);
	private DeckDraw drawDeck;
	private DeckPlayed playedDeckBlue, playedDeckGreen, playedDeckRed, playedDeckWhite, playedDeckYellow;
	private DeckDiscard discardDeckBlue, discardDeckGreen, discardDeckRed, discardDeckWhite, discardDeckYellow;
	private Player P1, P2, P3, P4, P5, currentPlayer;
	private int currentPlayerTurn;
	private int numPlayers;
	
	public Board (int playerNum) {
		this.numPlayers = playerNum;	//	numPlayers needed by other methods
		initializeDecks();
		initializePlayers(playerNum);
		Tokens tokens = new Tokens();
		
		
		
		
	}
	
	private void initializePlayers(int playerNum) {
		//	2 or 3 player games have 5 cards per player, 4 or 5 have 4 cards per player.
		int playerHandSize = 4;
		if (playerNum < 4) {
			playerHandSize = 5;
		}
		Stack<Player> playerStack = new Stack<Player>();
		
		Player P1 = new Player(playerHandSize);
		playerStack.push(P1);
		Player P2 = new Player(playerHandSize);
		playerStack.push(P2);
		if (playerNum > 4) {
			Player P5 = new Player(playerHandSize);
			playerStack.push(P5);	//	Order players are pushed doesn't matter, actually.
		}
		if (playerNum > 3) {
			Player P4 = new Player(playerHandSize);
			playerStack.push(P4);
		}
		if (playerNum > 2) {
			Player P3 = new Player(playerHandSize);
			playerStack.push(P3);
		}
		while (playerStack.isEmpty() == false) {	//	Populate hand with new cards.
			Player tempPlayer = playerStack.pop();
			for (int i = 0; i < playerHandSize; i++) {
				tempPlayer.insertCard(this.drawDeck.pop());
			}
		}
		
	}
	
	private void initializeDecks() {
		drawDeck = 					new DeckDraw(50);
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
	
	public void actionMenu() {
		int answer;
		do {
			System.out.println("1. Give information to another player");
			System.out.println("2. Discard a card");
			System.out.println("3. Play a card");
			System.out.println("4. Show discarded cards");
			System.out.print("What do you want to do? ");
			answer = sc.nextInt();
			if (answer == 4)
				showDiscard();
			if (answer < 1 || answer > 4)
				System.out.println("Please enter 1, 2, 3, or 4.");
		} while (answer < 0 || answer > 3);
		if (answer == 1) {
			// give info
		} else if (answer == 2) {
			// discard card
			this.currentPlayer.discardCard();
				
			
		} else {
			// play card
		}
	}
	
	private void nextTurn() {
		if (this.currentPlayerTurn == 1) {	//	Minimum 2 players, so always change to P2.
			this.currentPlayer = P2;	//	Change from P1 to P2
		}
		if (this.currentPlayerTurn == 2) {
			if (this.numPlayers == 2) {		//	If 2 players, go back to P1. Otherwise, P3.
				this.currentPlayer = P1;
				return;
			}
			this.currentPlayer = P3;
		}
		if (this.currentPlayerTurn == 3) { 
			if (this.numPlayers == 3) {		//	If 3 players, go back to P1, Otherwise, P4.
				this.currentPlayer = P1;
				return;
			}
			this.currentPlayer = P4;
		}
		if (this.currentPlayerTurn == 4) {
			if (this.numPlayers == 4) {		//	If 4 players, go back to P1. Otherwise, P5.
				this.currentPlayer = P1;
				return;
			}
			this.currentPlayer = P5;
		}
		if (this.currentPlayerTurn == 5) {	// 	Max 5 player game, have to go back to P1.
			this.currentPlayer = P1;
		}
	}
}
