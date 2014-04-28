package edu.shsu.hanabi_cmdline;

import java.util.LinkedHashMap;

import edu.shsu.hanabi_cmdline.database.Database;
import edu.shsu.hanabi_cmdline.database.TableGame;

public class main {

	public static void main(String[] args) {
	
		Database db = new Database("hanabi.db");
		LinkedHashMap<String, String> columns = new LinkedHashMap<String, String>();
		String[] values = {"one", "two", "3"};
		
		columns.put("name", "one");
		columns.put("some", "2");
		
		db.connect();
		
		Board b = new Board(4);

		// create game by player
		LinkedHashMap<String, String> gameinfo = new LinkedHashMap<String, String>();
		
		TableGame game = new TableGame(db, gameinfo);
		createTempTable(db, game);
		

		//db.createTable("One", columns);
		//db.insert("whatever", columns);
		//db.update("update", columns, columns);
		//db.select("something", values, columns);
		db.dropTable(Integer.toString(game.getID()) + Integer.toString(game.getGameAdmin()));
		db.close();
	}
		
	public static void createTempTable(Database db, TableGame game){
		String name = Integer.toString(game.getID()) + Integer.toString(game.getGameAdmin());
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