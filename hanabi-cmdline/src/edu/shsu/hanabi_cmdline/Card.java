package edu.shsu.hanabi_cmdline;

public class Card {
	private String color;
	private int number;
	private String orientation;
	
	public Card (int num, String c) {
		this.number = num;
		this.color = c;
		this.orientation = "up";
	}
	
	public void setColor(String c) {
		this.color = c;
	}
	
	public void setNumber(int n) {
		this.number = n;
	}
	
	public void setOrientation(String o) {
		this.orientation = o;
	}
	
	public String getColor() {
		return this.color;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public String getOrientation() {
		return this.orientation;
	}
}
