package edu.shsu.hanabi_cmdline;

public class Game {
	private int id;
	private String name;
	private int numPlayers;
	private boolean prvtGame;
	
	public Game() {
		//	To be implemented with database
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public void setName(String n) {
		this.name = n;
	}
	
	public void setNumPlayers(int p) {
		this.numPlayers = p;
	}
	
	public void setPrvtGame(boolean p) {
		this.prvtGame = p;
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getNumPlayers() {
		return this.numPlayers;
	}
	
	public boolean getPrvtGame() {
		return this.prvtGame;
	}
}
