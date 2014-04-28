package edu.shsu.hanabi_cmdline;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class Player {
	private int id;
	private String name;
	private String email;
	private String fbName;
	private DeckPlayer hand;
	Scanner sc = new Scanner(System.in); 
	
	public Player(String name, int size) {
		this.name = name;
		hand = new DeckPlayer(size);
	}
	
	public void insertCard(Card c) {
		this.hand.push(c);
	}
	
	public Card removeCard(String action) {
		System.out.format("Which card would you like to %s (1-%d)? ", action, this.hand.getDeckSize());
		int answer = sc.nextInt();
		return this.hand.removeCard(answer-1);
	}
	
	public void iterateDeck() {
		this.hand.iterateDeck(this.name);
	}
	
	public ArrayList<Integer> searchForElement(int num) {
		return this.hand.searchForElement(num);
	}
	
	public Set<Integer> getNumbersInHand() {
		return this.hand.getNumbersInHand();
	}
	
	public Set<Integer> getColorsInHand() {
		return this.hand.getColorsInHand();
	}
	
	public ArrayList<Integer> searchForElement (String color) {
		return this.hand.searchForElement(color);
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
