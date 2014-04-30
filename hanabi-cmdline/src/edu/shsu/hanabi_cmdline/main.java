package edu.shsu.hanabi_cmdline;

import java.util.LinkedHashMap;
import java.util.Scanner;

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
		Scanner sc = new Scanner(System.in);
		int playerNum;
		do {
			System.out.print("How many players (2-5)? ");
			playerNum = sc.nextInt();
			if (playerNum > 1 && playerNum < 6) {
				Board b = new Board(playerNum);
			} else
				System.out.println("Hanabi only supports 2 to 5 players.");
		} while (playerNum < 1 || playerNum > 5);

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