package edu.shsu.hanabi_cmdline;

import java.util.LinkedHashMap;

import edu.shsu.hanabi_cmdline.database.Database;

public class main {
	
	public static void main(String[] args) {
		
		Database db = new Database("hanabi.db");
		LinkedHashMap<String, String> columns = new LinkedHashMap<String, String>();
		String[] values = {"one", "two", "three"};
		
		columns.put("name", "one");
		columns.put("some", "2");
		
		db.connect();
		db.createTable("One", columns);
		db.insert("whatever", values);
		db.update("update", columns, columns);
		db.select("something", values, columns);
		db.close();
	}
	
	public void createTempTable(Database db, String admin, int gameID){
		
	}
}
