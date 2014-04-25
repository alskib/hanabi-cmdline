package edu.shsu.hanabi_cmdline;

import java.util.Scanner;

public class Player {
	private int id;
	private String name;
	private String email;
	private String fbName;
	private Card[] hand;
	
	public Player(int size) {
		hand = new Card[size];
	}
	
	public void actionMenu() {
		int answer;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("1. Give information to another player");
			System.out.println("2. Discard a card");
			System.out.println("3. Play a card");
			System.out.print("What do you want to do? ");
			answer = sc.nextInt();
			if (answer != 1 || answer != 2 || answer != 3)
				System.out.println("Please enter 1, 2, or 3.");
		} while (answer < 0 || answer > 3);
		
	}
	
	public void insertCard(Card card) {
		hand[hand.length-1] = card;	//	Insert card to end of hand.
	}
	
	public Card removeCard(int pos) {
		Card temp = hand[pos];
		for (int i = pos; i < hand.length; i++) {	//	Fill in gap from removed card
			hand[i] = hand[i+1];
		}
		return temp;
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
	
	public void gatherInfoToTell() {
		
	}
	
	public int[] tellInfo(int[] pos) {
		
		return null;
	}
	
	public void tellInfo(String color, int[] pos) {
		
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public void setName(String n) {
		this.name = n;
	}
	
	public void setEmail(String e) {
		this.email = e;
	}
	
	public void setFBName(String f) {
		this.fbName = f;
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getFBName() {
		return this.fbName;
	}
}
