package edu.shsu.hanabi_cmdline;

public class Lobby {
	private Game game;
	
	public Lobby() {
		
	}
	
	public void setGame(Game g) {
		this.game = g;
	}
	
	public Game getGame() {
		return this.game;
	}
}
