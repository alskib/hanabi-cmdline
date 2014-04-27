package edu.shsu.hanabi_cmdline;

import java.util.LinkedHashMap;

import edu.shsu.hanabi_cmdline.database.Database;

public class main {

	public static void main(String[] args) {
	
		Database db = new Database("hanabi.db");
		LinkedHashMap<String, String> columns = new LinkedHashMap<String, String>();
		String[] values = {"one", "two", "3"};
		
		columns.put("name", "one");
		columns.put("some", "2");
		
		db.connect();
		
		Board b = new Board(4);
		//db.createTable("One", columns);
		//db.insert("whatever", columns);
		//db.update("update", columns, columns);
		//db.select("something", values, columns);
		db.dropTable("One");
		db.close();
	}
		
	public static void createTempTable(Database db, String admin, int gameID){
		String name = admin + Integer.toString(gameID);
		LinkedHashMap<String, String> columns = new LinkedHashMap<String, String>();
		
		columns.put("turn", "INTEGER");
		columns.put("type", "VARCHAR(10)");
		columns.put("color", "VARCHAR(10)");
		columns.put("number", "INTEGER");
		columns.put("curPlayerID", "INTEGER");
		columns.put("playerGetInfo", "INTEGER");
		db.createTable(name, columns);
	}
}