package edu.shsu.hanabi_cmdline.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
	private Connection connect;
	private String name;
	
	public Database(String name){
		setName(name);
	}

	private void setName(String name) {
		this.name = name;
	}
	
	public void connect(){
		this.connect = null;
		try {
			Class.forName("org.sqlite.JDBC");
			this.connect = DriverManager.getConnection("jdbc:sqlite:" + this.getName());
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}
	
	public void close(){
		this.close();
	}

	public String getName() {
		return this.name;
	}
	

}
