package edu.shsu.hanabi_cmdline;

import edu.shsu.hanabi_cmdline.Board.InfoType;

public class Info {
	private InfoType infoType;
	private int playerTurn;
	private int infoAnswer;
	private int[] infoData;
	boolean remove;
	
	//	Structure for saving info (number or color) given by another player
	public Info (InfoType type) {
		this.infoType = type;
		this.remove = false;
	}
	
	//	Enumerated to InfoType.NUMBER and InfoType.COLOR
	public InfoType getInfoType() {
		return this.infoType;
	}
	
	public int getPlayerTurn() {
		return this.playerTurn;
	}
	
	public void setPlayerTurn(int turn) {
		this.playerTurn = turn;
	}
	
	public int getInfoAnswer() {
		return this.infoAnswer;
	}
	
	public void setInfoAnswer(int a) {
		this.infoAnswer = a;
	}
	
	//	Position data for cards
	public void insertData(int[] data) {
		this.infoData = data;
	}
	
	//	Show data to player
	public void iterateData() {
		for (int i : this.infoData)
				System.out.print(i + " ");
	}
	
	public void setForRemoval() {
		this.remove = true;
	}
	
	//	Check if Info is set for removal (after being shown to designated
	//		player)
	public boolean removeStatus() {
		return this.remove;
	}
}
