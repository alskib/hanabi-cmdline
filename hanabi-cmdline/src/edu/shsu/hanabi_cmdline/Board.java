package edu.shsu.hanabi_cmdline;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Board {
	private Scanner sc = new Scanner(System.in);
	private DeckDraw drawDeck;
	private DeckPlayed[] deckPlayedArray;
	private DeckDiscard[] deckDiscardArray;
	private Player P1, P2, P3, P4, P5, currentPlayer;
	private Player[] playerArray;

	public enum InfoType {
		NUMBER,
		COLOR
	}

	private ArrayList<Info> infoList = new ArrayList<Info>();
	private Tokens tokens;
	private int currentPlayerTurn, numPlayers;
	
	public Board (int playerNum) {
		this.numPlayers = playerNum;	//	numPlayers used by other methods
		initializeDecks();
		initializePlayers(this.numPlayers);
		this.tokens = new Tokens();
		
		// Start of first turn.
		this.currentPlayer = this.P1;
		this.currentPlayerTurn = 1;
		boolean actionMenuLoop = true; 
		do {

			actionMenu(this.currentPlayer);
			nextTurn();
			//	TODO: Implement win/loss conditions

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
		boolean outOfClockTokens;
		boolean continueLoop, infoLoop;
		Info tempInfo;
		
		//	Main menu loop
		do {
			continueLoop = false;
			
			//	No info giving when no clock tokens left
			if (this.tokens.getClockTokens() == 0)
				outOfClockTokens = true;
			else
				outOfClockTokens = false;
			
			System.out.println("==============================");
			for (int i = 0; i < this.playerArray.length; i++) {
				//	Skip loop if i matches current player
				if (i == this.currentPlayerTurn-1)
					continue;
				this.playerArray[i].iterateDeck();
			}
			System.out.println("==============================");
			showTokens();
			showDeckColored(this.deckPlayedArray, "Played");
			showDeckColored(this.deckDiscardArray, "Discarded");
			
			//	Show information if there was info given and player matches
			//	Number as info
			if (!this.infoList.isEmpty()) {
				for (int i = 0; i < infoList.size(); i++) {
					if (infoList.get(i).getPlayerTurn() == this.currentPlayerTurn) {
						tempInfo = infoList.get(i);
						System.out.println("\nYou were given information!");
						System.out.print("Cards in position(s) ");
						tempInfo.iterateData();
						if (tempInfo.getInfoType().equals(InfoType.NUMBER)) {
							System.out.println("have the number " + tempInfo.getInfoAnswer());
						}
						if (tempInfo.getInfoType().equals(InfoType.COLOR)) {
							System.out.println("have the color " + tempInfo.getInfoAnswer());
						}
						//	Removal will be checked after this loop
						tempInfo.setForRemoval();
					}
				}
			}
			
			//	Main menu
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
		
		//	The reason this is set outside the main menu loop is in case the
		//		user makes a mistake in input. I didn't want the info to
		//		disappear (as it's quite important).
		if (!infoList.isEmpty()) {
			for (int i = 0; i < infoList.size(); i++) {
				if (infoList.get(i).removeStatus())
					infoList.remove(i);
			}
		}
		
		//	Give info
		if (answer == 1) {
			//	infoPlayer will store the reference of the player that will be
			//		given the info
			Player infoPlayer;
			
			do {
				infoLoop = false;
				
				boolean found = false;
				System.out.println("");
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
			
			//	currentPlayerTurn corresponds with the current player's actual
			//		location in the playerArray
			//	since infoAnswer is changed (from previous loop) depending on
			//		current player's location in the playerArray, a comparison
			//		with currentPlayerTurn is necessary to see if the above 
			//		was really modified
			if (infoAnswer < this.currentPlayerTurn) {
				infoPlayer = this.playerArray[infoAnswer-1];
				infoPlayer.iterateDeck();
			} else {
				infoPlayer = this.playerArray[infoAnswer];
				infoPlayer.iterateDeck();
			}
			
			int numAnswer, colorAnswer;
			ArrayList<Integer> arr;
			Info info;
			//	Give info loop
			do {
				System.out.println("");
				System.out.println("1. Number");
				System.out.println("2. Color");
				System.out.print("Which info would you like to give? ");
				infoAnswer = sc.nextInt();
				if (infoAnswer < 1 || infoAnswer > 2) {
					System.out.println("Please enter 1 or 2.");
					infoLoop = true;
				} else {
					infoLoop = false;
				}
				
				//	Give number as info
				if (infoAnswer == 1) {
					//	Sets contain only unique entries.
					Set<Integer> numSet = new TreeSet<Integer>();
					numSet = infoPlayer.getNumbersInHand();
					
					//	Convert set to array.
					int[] validNumbers = new int[numSet.size()];
					int index = 0;
					for (int i : numSet) {
						validNumbers[index++] = i;
					}
					
					//	Get number to tell
					boolean pickNumberLoop;
					do {
						pickNumberLoop = true;
						System.out.println("");
						for (int i = 0; i < validNumbers.length; i++) {
							System.out.println(validNumbers[i]);
						}
						System.out.print("Which number? ");
						numAnswer = sc.nextInt();
						
						//	Test if valid input.
						for (int i : validNumbers) {
							if (numAnswer == i) {
								pickNumberLoop = false;
								break;
							}
						}
					} while (pickNumberLoop);
					
					//	Get card positions with that number, then save number and
					//		positions in an array to be shown at beginning of
					//		actionMenu().
					arr = infoPlayer.searchForElement(numAnswer);
					info = new Info(InfoType.NUMBER);
					
					//	posArray will store player turn number as first element,
					//		the number of the card(s) as the info as the second,
					//		and positions of cards with that number after that.
					//		Therefore the array is size()+2.
					int[] posArray = new int[arr.size()];
					//	Find position of infoPlayer in playerArray, and then
					//		+1 to that value to get the proper player turn number.
					for (int i = 0; i < this.playerArray.length; i++) {
						if (playerArray[i] == infoPlayer) {
							info.setPlayerTurn(i+1);
							break;
						}
					}
					info.setInfoAnswer(numAnswer);
					for (int i = 0; i < arr.size(); i++) {
						posArray[i] = arr.get(i)+1;
					}
					info.insertData(posArray);
					//	Save this position data for later
					this.infoList.add(info);
					
				//	Give color as info
				} else if (infoAnswer == 2) {
					//	Sets contain only unique entries (same as implementation
					//		of number)
					Set<Integer> colorSet = new TreeSet<Integer>();
					colorSet = infoPlayer.getColorsInHand();
					
					int[] validColors = new int[colorSet.size()];
					int index = 0;
					for (int i : colorSet) {
						validColors[index++] = i;
					}
					
					boolean pickColorLoop;
					do {
						pickColorLoop = true;
						System.out.println("");
						for (int i = 0; i < validColors.length; i++) {
							switch(validColors[i]) {
								case 1:	System.out.println("1. Blue");
										break;
								case 2:	System.out.println("2. Green");
										break;
								case 3:	System.out.println("3. Red");
										break;
								case 4:	System.out.println("4. White");
										break;
								case 5:	System.out.println("5. Yellow");
										break;
							}
						}
						System.out.print("Which color? ");
						colorAnswer = sc.nextInt();
						for (int i : validColors)
							if (colorAnswer == i) {
								pickColorLoop = false;
								break;
							}
					} while (pickColorLoop);
					
					//	Translate the color integer to actual string color.
					switch (colorAnswer) {
						case 1:	arr = infoPlayer.searchForElement("blue");
								break;
							
						case 2:	arr = infoPlayer.searchForElement("green");
								break;
							
						case 3:	arr = infoPlayer.searchForElement("red");
								break;
							
						case 4:	arr = infoPlayer.searchForElement("white");
								break;

						case 5:	arr = infoPlayer.searchForElement("yellow");
								break;
						default:	arr = null;
									break;
					}
					info = new Info(InfoType.COLOR);
					int[] posArray = new int[arr.size()];
					for (int i = 0; i < this.playerArray.length; i++) {
						if (playerArray[i] == infoPlayer) {
							info.setPlayerTurn(i+1);
							break;
						}
					}
					info.setInfoAnswer(colorAnswer);
					for (int i = 0; i < arr.size(); i++) {
						posArray[i] = arr.get(i)+1;
					}
					
					//	Save this position data for later
					this.infoList.add(info);
				}
			} while (infoLoop);
			
			this.tokens.decClockTokens();
			
		//	Discard card
		} else if (answer == 2) {
			//	Remove card from player hand
			tempCard = p.removeCard("discard");
			//	Transfer card to discard deck
			DeckDiscard tempDeckD = (DeckDiscard)findDeckColored(this.deckDiscardArray, tempCard);
			tempDeckD.push(tempCard);
			this.tokens.incClockTokens();
			//	Draw new card
			p.insertCard(this.drawDeck.pop());
			
		//	Play card
		} else if (answer == 3) {
			//	if playCard() false, return false for actionMenu()
			//		which will then return for the Board game object,
			//		quitting the game?
			
			//	Remove card from player hand
			tempCard = p.removeCard("play");
			//	Get ready to transfer to play deck (pending validity checking)
			DeckPlayed tempDeckP = (DeckPlayed)findDeckColored(this.deckPlayedArray, tempCard);
			
			//	Test if card can really be played
			if (tempDeckP.insertIntoPlayed(tempCard)){
				System.out.println("A " + tempCard.getColor() + " " + tempCard.getNumber() +
								   " successfully added to the fireworks!");
				//	Successful 5 card gets a clock token.
				if (tempCard.getNumber() == 5)
					this.tokens.incClockTokens();
			} else {
				//	Blew a fuse
				System.out.println("Incorrect card played!");
				this.tokens.decFuseTokens();
			}
			//	Draw new card
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
