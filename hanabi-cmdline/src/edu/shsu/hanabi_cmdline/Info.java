package edu.shsu.hanabi_cmdline;

import edu.shsu.hanabi_cmdline.Board.InfoType;

public class Info {
	private InfoType infoType;
	private int playerTurn;
	private int infoAnswer;
	private int[] infoData;
	boolean remove;
	
	public Info (InfoType type) {
		this.infoType = type;
		this.remove = false;
	}
	
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
	
	public void insertData(int[] data) {
		this.infoData = data;
	}
	
	public void iterateData() {
		for (int i : this.infoData)
			System.out.print(i + " ");
	}
	
	public void setForRemoval() {
		this.remove = true;
	}
	
	public boolean removeStatus() {
		return this.remove;
	}
}
