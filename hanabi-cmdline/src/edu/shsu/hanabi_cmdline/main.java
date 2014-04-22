package edu.shsu.hanabi_cmdline;

import edu.shsu.hanabi_cmdline.database.Database;

public class main {
	
	public static void main(String[] args) {
		
		Database db = new Database("hanabi.db");
		db.connect();
	}
	// what again
}
