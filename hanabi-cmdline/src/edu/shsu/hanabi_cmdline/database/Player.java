package edu.shsu.hanabi_cmdline.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class Player {
	
	int id;
	String firstName, lastName, username, fbusername, table;
	boolean pvtProfile;
	Database database;
	ResultSet resultSet;
	
	public Player(Database database, String username){
		setDatabase(database);
		setTable();
		if(this.playerExist(username))
			setPlayerData(username);
		else{
			LinkedHashMap<String, String> player = new LinkedHashMap<String, String>();
			player.put("username", username);
			this.insert(player);
			setPlayerData(username);
		}
	}

	private void setDatabase(Database database) {
		this.database = database;
	}
	
	private void setTable(){
		this.table = "Player";
	}
	
	private void setPlayerData(String username){
		String[] fields = {"*"};
		LinkedHashMap<String, String> where = new LinkedHashMap<String, String>();
		
		where.put("username", this.username);
		
		this.database.select(table, fields, where);
		this.resultSet = this.database.getResultSet();
		try {
			this.firstName = this.resultSet.getString("firstName");
			this.lastName = this.resultSet.getString("lastName");
			this.id = this.resultSet.getInt("id");
			this.fbusername = this.resultSet.getString("fbusername");
			if(!this.resultSet.getBoolean("pvtProfile"))
				this.pvtProfile = false;
			else
				this.pvtProfile = true;
		} catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	}

	public boolean playerExist(String username){
		String[] fields = {"*"};
		this.database.select(this.table, fields, null);
		try {
			if(!this.database.getResultSet().next())
				return false;
		} catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return true;
	}
	
	public void insert(LinkedHashMap<String, String> player){
		this.database.insert(this.table, player);
	}
	
	public int getID(){
		return this.id;
	}
	
	public String getFirstName(){
		return this.firstName;
	}
	
	public String getLastName(){
		return this.lastName;
	}
	
	public String getFBusername(){
		return this.fbusername;
	}
	
	public boolean getPVTprofile(){
		return this.pvtProfile;
	}
	
}
