package edu.shsu.hanabi_cmdline.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Random;

public class TableGame {

	private int numPlayers, maxPlayers, finalScore, id;
	private boolean pvtGame;
	private String dt, table, password;
	private Database database;
	private ResultSet resultSet;
	private Player admin;
	
	/**
	 * Constructor connects the database
	 * @param dbName Name of database
	 * @param gameInfo LinkedHashMap containing the game info in &ltfield, value&gt pairs
	 */
	public TableGame(Database dbName, LinkedHashMap<String, String> gameInfo){
		setDatabase(dbName);
		setAdmin(admin);
		setTable();
		insertGameInfo(gameInfo);
		setGameInfo(admin.getID());
	}
	
	/**
	 * This function sets the database
	 * @param dbName
	 */
	private void setDatabase(Database dbName){
		this.database = dbName;
	}
	
	/**
	 * This function sets the admin
	 * @param admin
	 */
	private void setAdmin(Player admin){
		this.admin = admin;
	}
	
	/**
	 * This function sets the Game table
	 */
	private void setTable(){
		this.table = "Game";
	}
	
	/**
	 * This function sets the rest of the Game info
	 * @param adminID admin ID from the Game object
	 */
	private void setGameInfo(int adminID){
		String[] fields = {"*"};
		LinkedHashMap<String, String> where = new LinkedHashMap<String, String>();
		where.put("gameAdmin", Integer.toString(adminID));
		where.put("current", "1");
		this.database.select(this.table, fields, where);
		this.resultSet = this.database.getResultSet();		
		try {
			while(this.resultSet.next()){
				this.numPlayers = this.resultSet.getInt("numPlayers");
				this.maxPlayers = this.resultSet.getInt("maxPlayers");
				this.id = this.resultSet.getInt("id");
				this.pvtGame = this.resultSet.getBoolean("pvtGame");
				this.password = this.resultSet.getString("password");
				this.dt = this.resultSet.getString("dtCreated");
			}
		} catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	}
	
	/**
	 * This function is used to insert game info into the database
	 * @param values LinkedHashMap containing game info as &ltfield, value&gt pairs
	 */
	public void insertGameInfo(LinkedHashMap<String, String> values){
		Random idnumber = new Random();
		String[] fields= {"*"};
		boolean test = true;
		while(test){
			int rand = idnumber.nextInt(100000);
			LinkedHashMap<String, String> where = new LinkedHashMap<String, String>();
			where.put("id", Integer.toString(rand));
			this.database.select(this.table, fields, where);
			try {
				if(!this.database.getResultSet().next()){
					test = false;
				}
				values.put("id", Integer.toString(rand));
			} catch (SQLException e) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
		}
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
		return this.admin.getID();
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public String getDateTime(){
		return this.dt;
	}
	
	public boolean isPrivateGame(){
		if(!this.pvtGame)
			return false;
		return true;
	}
}
