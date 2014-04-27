package edu.shsu.hanabi_cmdline;

import java.util.Scanner;

public class Board {
	private Scanner sc = new Scanner(System.in);
	private DeckDraw drawDeck;
	private DeckPlayed[] deckPlayedArray;
	private DeckDiscard[] deckDiscardArray;
	private Player P1, P2, P3, P4, P5, currentPlayer;
	private Player[] playerArray;
	private String[] infoColorArray;
	private Card[] infoCardsArray;
	private int[] infoNumberArray;
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
		this.deckPlayedArray[0] = new DeckPlayed(5, "blue");
		this.deckPlayedArray[1] = new DeckPlayed(5, "green");
		this.deckPlayedArray[2] = new DeckPlayed(5, "red");
		this.deckPlayedArray[3] = new DeckPlayed(5, "white");
		this.deckPlayedArray[4] = new DeckPlayed(5, "yellow");
		this.deckDiscardArray =	new DeckDiscard[5];
		this.deckDiscardArray[0] = new DeckDiscard(10, "blue");
		this.deckDiscardArray[1] = new DeckDiscard(10, "green");
		this.deckDiscardArray[2] = new DeckDiscard(10, "red");
		this.deckDiscardArray[3] = new DeckDiscard(10, "white");
		this.deckDiscardArray[4] = new DeckDiscard(10, "yellow");
	}
	
	private void showDeckColored(DeckColored[] deck, String type) {
		System.out.println(type + " cards: ");
		for (int i = 0; i < deck.length; i++) {
			deck[i].iterateDeck();
		}
	}
	
	private void showTokens() {
		System.out.format("Clock tokens: %d | Fuse tokens: %d\n", 
						  this.tokens.getClockTokens(), this.tokens.getFuseTokens());
	}
	
	private void actionMenu(Player p) {
		int answer, infoAnswer;
		Card tempCard;
		boolean outOfClockTokens = false;
		boolean continueLoop, infoLoop;
		do {
			continueLoop = false;
			if (this.tokens.getClockTokens() == 0)
				outOfClockTokens = true;
			else
				outOfClockTokens = false;
			
			System.out.println("==============================");
			for (int i = 0; i < this.playerArray.length; i++) {
				//	Skip loop if i matches current player
//				if (i == this.currentPlayerTurn-1)
//					continue;
				this.playerArray[i].iterateDeck();
			}
			System.out.println("==============================");
			showTokens();
			showDeckColored(this.deckPlayedArray, "Played");
			showDeckColored(this.deckDiscardArray, "Discarded");
			
			System.out.println("\nPlayer " + p.getName());
			if (outOfClockTokens)
				System.out.println("-- No clock tokens remain for info giving!");
			else
				System.out.println("1. Give information to another player");
			System.out.println("2. Discard a card");
			System.out.println("3. Play a card");
			System.out.print("What do you want to do? ");
			answer = sc.nextInt();
			if (outOfClockTokens) {
				if (answer < 2 || answer > 3) {
					System.out.println("Please enter 2 or 3.");
					continueLoop = true;
				}
			} else {
				if (answer < 1 || answer > 3) {
					System.out.println("Please enter 1, 2, or 3.");
					continueLoop = true;
				}
			}
		} while (continueLoop);
		if (answer == 1) {
			//	give info
			//	have player select card(s) to choose, and type of info to choose
			//	put into list, have it shown at beginning of that player's turn
			//	it will be a list per person, with a list on each line
			do {
				infoLoop = false;
				
				boolean found = false;
				for (int i = 0; i < this.playerArray.length; i++) {
					String name = this.playerArray[i].getName();
					if (name.equals(p.getName())) {	//	Ignore printout for current player
						found = true;
						continue;
					}
					//	Keep numbering consistently 1 to numPlayers-1
					if (found)
						System.out.println(i + ". " + this.playerArray[i].getName());
					else
						System.out.println(i+1 + ". " + this.playerArray[i].getName());
				}
				System.out.print("To whom do you want to give information? ");
				infoAnswer = sc.nextInt();
				if (infoAnswer < 1 || infoAnswer > this.playerArray.length) {
					System.out.format("Please enter a number from 1 to %d", this.numPlayers-1);
					infoLoop = true;
				}
			} while (infoLoop);
			
			do {
				//	currentPlayerTurn corresponds with the current player's actual
				//		location in the playerArray
				//	since infoAnswer is changed (from previous loop) depending on
				//		current player's location in the playerArray, a comparison
				//		with currentPlayerTurn is necessary to see if the above 
				//		was really modified
				if (infoAnswer < this.currentPlayerTurn) {
					this.playerArray[infoAnswer-1].iterateDeck();
				}
				if (infoAnswer >= this.currentPlayerTurn) {
					this.playerArray[infoAnswer].iterateDeck();
				}
				
				System.out.println("Which card(s) to give info on? ");
				
				
			} while (false);
			
			//	Show cards from person selected
			
			
			//	Select cards from person
			
			
			//	Number or color?
			
			
			//	Error checking
			
			
			this.tokens.decClockTokens();
		} else if (answer == 2) {
			//	discard card
			tempCard = p.removeCard("discard");
			DeckDiscard tempDeckD = (DeckDiscard)findDeckColored(this.deckDiscardArray, tempCard);
			tempDeckD.push(tempCard);
			this.tokens.incClockTokens();
			p.insertCard(this.drawDeck.pop());
		} else if (answer == 3) {
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
