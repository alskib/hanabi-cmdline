package edu.shsu.hanabi_cmdline.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class Game {

	private int numPlayers, maxPlayers, finalScore, gameAdmin, id;
	private boolean pvtGame;
	private String dt, table, password;
	private Database database;
	private ResultSet resultSet;
	
	public Game(Database dbName, int gameAdmin){
		setDatabase(dbName);
		setGameAdmin(gameAdmin);
		setTable();
		setGameData();
	}
	
	private void setDatabase(Database dbName){
		this.database = dbName;
	}
	
	private void setGameAdmin(int gameAdmin){
		this.gameAdmin = gameAdmin;
	}
	
	private void setTable(){
		this.table = "Game";
		String[] fields = {"*"};
		LinkedHashMap<String, String> where = new LinkedHashMap<String, String>();
		where.put("gameAdmin", Integer.toString(this.gameAdmin));
		where.put("current", "1");
		this.database.select(this.table, fields, where);
		this.resultSet = this.database.getResultSet();
	}
	
	private void setGameData(){
		try {
			while(this.resultSet.next()){
				this.numPlayers = this.resultSet.getInt("numPlayers");
				this.maxPlayers = this.resultSet.getInt("maxPlayers");
				this.id = this.resultSet.getInt("id");
				this.pvtGame = this.resultSet.getBoolean("pvtGame");
				this.password = this.resultSet.getString("password");
			}
		} catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	}
	
	public void insertGameData(LinkedHashMap<String, String> values){
		this.database.insert(this.table, values);
	}
	
	public String getTable(){
		return this.table;
	}
	
	public int getNumPlayers(){
		return this.numPlayers;
	}
	
	public int getMaxPlayers(){
		return this.maxPlayers;
	}
	
	public int getID(){
		return this.id;
	}
	
	public int getGameAdmin(){
		return this.gameAdmin;
	}
	
	public String getPassword(){
		return this.password;
	}
}
