package edu.shsu.hanabi_cmdline;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Board {
	private Scanner sc = new Scanner(System.in);
	private DeckDraw drawDeck;
	private DeckPlayed playedDeckBlue, playedDeckGreen, playedDeckRed, playedDeckWhite, playedDeckYellow;
	private DeckPlayed[] deckPlayedArray;
	private DeckDiscard discardDeckBlue, discardDeckGreen, discardDeckRed, discardDeckWhite, discardDeckYellow;
	private DeckDiscard[] deckDiscardArray;
	private Player P1, P2, P3, P4, P5, currentPlayer;
	private Player[] playerArray;
	private Tokens tokens;
	private int currentPlayerTurn;
	private int numPlayers;
	
	public Board (int playerNum) {
		this.numPlayers = playerNum;	//	numPlayers needed by other methods (possibly)
		initializeDecks();
		initializePlayers(this.numPlayers);
		this.tokens = new Tokens();
		
		// Start of first turn.
		this.currentPlayer = this.P1;
		this.currentPlayerTurn = 1;
		do {

			actionMenu(this.currentPlayer);
			nextTurn();

		} while (true);
		
		
		
	}
	
	private void initializePlayers(int playerNum) {
		//	2 or 3 player games have 5 cards per player, 4 or 5 have 4 cards per player.
		int playerHandSize = 4;
		if (playerNum < 4) {
			playerHandSize = 5;
		}
		this.playerArray = new Player[playerNum];
		
		this.P1 = new Player("Amy", playerHandSize);
		this.playerArray[0] = this.P1;
		
		this.P2 = new Player("Bob", playerHandSize);
		this.playerArray[1] = this.P2;
		
		if (playerNum > 4) {
			this.P5 = new Player("Xin", playerHandSize);
			this.playerArray[4] = this.P5;
		}
		if (playerNum > 3) {
			this.P4 = new Player("Johnny", playerHandSize);
			this.playerArray[3] = this.P4;
		}
		if (playerNum > 2) {
			this.P3 = new Player("Franklin", playerHandSize);
			this.playerArray[2] = this.P3;
		}
		
		//	Populate new cards into player hands from drawDeck. 
		for (int j = 0; j < this.playerArray.length; j++) {
			for (int k = 0; k < playerHandSize; k++) {
				this.playerArray[j].insertCard(this.drawDeck.pop());
			}
		}
		
		//	Test to see if players' hands populated correctly.
//		for (int i = 0; i < this.playerArray.length; i++) {
//			this.playerArray[i].iterateDeck();
//		}


		
	}
	
	private void initializeDecks() {
		drawDeck = new DeckDraw(50);
		this.deckPlayedArray = new DeckPlayed[5];
		this.deckPlayedArray[0] = this.playedDeckBlue = 	new DeckPlayed(5, "blue");
		this.deckPlayedArray[1] = this.playedDeckGreen = 	new DeckPlayed(5, "green");
		this.deckPlayedArray[2] = this.playedDeckRed = 		new DeckPlayed(5, "red");
		this.deckPlayedArray[3] = this.playedDeckWhite = 	new DeckPlayed(5, "white");
		this.deckPlayedArray[4] = this.playedDeckYellow = 	new DeckPlayed(5, "yellow");
		this.deckDiscardArray =	new DeckDiscard[5];
		this.deckDiscardArray[0] = this.discardDeckBlue =	new DeckDiscard(10, "blue");
		this.deckDiscardArray[1] = this.discardDeckGreen = 	new DeckDiscard(10, "green");
		this.deckDiscardArray[2] = this.discardDeckRed =	new DeckDiscard(10, "red");
		this.deckDiscardArray[3] = this.discardDeckWhite = 	new DeckDiscard(10, "white");
		this.deckDiscardArray[4] = this.discardDeckYellow =	new DeckDiscard(10, "yellow");
	}
	
	private void showDiscard() {
		System.out.println("Discarded cards:");
		for (int i = 0; i < this.deckDiscardArray.length; i++) {
			this.deckDiscardArray[i].iterateDeck();
		}
//		System.out.print("Blue: "); 	this.discardDeckBlue.iterateDeck(); 	System.out.println("");
//		System.out.print("Green: "); 	this.discardDeckGreen.iterateDeck(); 	System.out.println("");
//		System.out.print("Red: "); 		this.discardDeckRed.iterateDeck(); 		System.out.println("");
//		System.out.print("White: "); 	this.discardDeckWhite.iterateDeck(); 	System.out.println("");
//		System.out.print("Yellow: ");	this.discardDeckYellow.iterateDeck(); 	System.out.println("");
	}
	
	private void showPlayed() {
		System.out.println("Played cards:");
		for (int i = 0; i < this.deckPlayedArray.length; i++) {
			this.deckPlayedArray[i].iterateDeck();
		}
//		System.out.print("Blue: "); 	this.playedDeckBlue.iterateDeck(); 		System.out.println("");
//		System.out.print("Green: "); 	this.playedDeckGreen.iterateDeck(); 	System.out.println("");
//		System.out.print("Red: "); 		this.playedDeckRed.iterateDeck(); 		System.out.println("");
//		System.out.print("White: "); 	this.playedDeckWhite.iterateDeck(); 	System.out.println("");
//		System.out.print("Yellow: ");	this.playedDeckYellow.iterateDeck(); 	System.out.println("");
	}
	
	private void showTokens() {
		System.out.format("Clock tokens: %d | Fuse tokens: %d\n", 
						  this.tokens.getClockTokens(), this.tokens.getFuseTokens());
	}
	
	private void actionMenu(Player p) {
		int answer;
		Card tempCard;
		do {
			System.out.println("==============================");
			for (int i = 0; i < this.playerArray.length; i++) {
				//	Skip loop if i matches current player
//				if (i == this.currentPlayerTurn-1)
//					continue;
				this.playerArray[i].iterateDeck();
			}
			System.out.println("==============================");
			showTokens();
			showPlayed();
			System.out.println("\nPlayer " + p.getName());
			System.out.println("1. Give information to another player");
			System.out.println("2. Discard a card");
			System.out.println("3. Play a card");
			System.out.println("4. Show discarded cards");
			System.out.println("5. Display your cards");	//	Testing only
			System.out.print("What do you want to do? ");
			answer = sc.nextInt();
			if (answer == 4)
				showDiscard();
			if (answer == 5)
				p.iterateDeck();
			if (answer < 1 || answer > 5)
				System.out.println("Please enter 1, 2, 3, 4, or 5.");
		} while (answer < 0 || answer > 3);
		if (answer == 1) {
			//	give info
			//	have player select card(s) to choose, and type of info to choose
			//	put into list, have it shown at beginning of that player's turn
			//	it will be a list per person, with a list on each line
			
		} else if (answer == 2) {
			//	discard card
			tempCard = p.removeCard("discard");
			DeckDiscard tempDeckD = (DeckDiscard)findDeckColored(this.deckDiscardArray, tempCard);
			tempDeckD.push(tempCard);
			this.tokens.incClockTokens();
			p.insertCard(this.drawDeck.pop());
		} else {
			//	play card
			//	if playCard() false, return false for actionMenu()
			//		which will then return for the Board game object,
			//		quitting the game?
			tempCard = p.removeCard("play");
			DeckPlayed tempDeckP = (DeckPlayed)findDeckColored(this.deckPlayedArray, tempCard);
			
			if (tempDeckP.insertIntoPlayed(tempCard)){
				System.out.println("A " + tempCard.getColor() + " " + tempCard.getNumber() +
								   " successfully added to the fireworks!");
				//	Successful 5 card gets a clock token.
				if (tempCard.getNumber() == 5)
					this.tokens.incClockTokens();
			} else {
				System.out.println("Incorrect card played!");
				this.tokens.decFuseTokens();
			}
			p.insertCard(this.drawDeck.pop());
		}
	}
	
	private DeckColored findDeckColored(DeckColored[] array, Card c) {
		for(int i = 0; i < array.length; i++) {
			//	Find deck color that matches card color and push card into deck.
			if (array[i].getDeckColor().equals(c.getColor())){
				return array[i];
			}
		}
		return null;
	}
	
	private void nextTurn() {
		if (this.currentPlayerTurn == 1) {	//	Minimum 2 players, so always change to P2.
			this.currentPlayer = this.P2;	//	Change from P1 to P2
			this.currentPlayerTurn = 2;
			return;
		}
		if (this.currentPlayerTurn == 2) {
			if (this.numPlayers == 2) {		//	If 2 players, go back to P1. Otherwise, P3.
				this.currentPlayer = this.P1;
				this.currentPlayerTurn = 1;
				return;
			}
			this.currentPlayer = this.P3;
			this.currentPlayerTurn = 3;
			return;
		}
		if (this.currentPlayerTurn == 3) { 
			if (this.numPlayers == 3) {		//	If 3 players, go back to P1, Otherwise, P4.
				this.currentPlayer = this.P1;
				this.currentPlayerTurn = 1;
				return;
			}
			this.currentPlayer = this.P4;
			this.currentPlayerTurn = 4;
			return;
		}
		if (this.currentPlayerTurn == 4) {
			if (this.numPlayers == 4) {		//	If 4 players, go back to P1. Otherwise, P5.
				this.currentPlayer = this.P1;
				this.currentPlayerTurn = 1;
				return;
			}
			this.currentPlayer = this.P5;
			this.currentPlayerTurn = 5;
			return;
		}
		if (this.currentPlayerTurn == 5) {	// 	Max 5 player game, have to go back to P1.
			this.currentPlayer = this.P1;
			this.currentPlayerTurn = 1;
		}
	}
}
