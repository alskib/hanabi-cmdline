package edu.shsu.hanabi_cmdline;

public class Player {
	private int id;
	private String name;
	private String email;
	private String fbName;
	private Card[] hand;
	
	public Player(int size) {
		hand = new Card[size];
	}
	
	public void insertCard(int pos) {
		
	}
	
	public Card removeCard(int pos) {
		
		return null;
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
