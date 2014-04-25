package edu.shsu.hanabi_cmdline;

import java.util.Scanner;

public class Player {
	private int id;
	private String name;
	private String email;
	private String fbName;
	private DeckPlayer hand;
	Scanner sc = new Scanner(System.in); 
	
	public Player(int size) {
		hand = new DeckPlayer(size);
	}
	
	public void insertCard(Card c) {
		this.hand.push(c);
	}
	
	
	public void discardCard() {
		System.out.print("Which card would you like to discard (1-5)? ");
		int answer = sc.nextInt();
		this.hand.removeCard(answer-1);	//	removeCard uses 0-based indexing (maybe change this later)
		
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
