package edu.shsu.hanabi_cmdline.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

public class Database {
	
	private Connection connect;
	private String dbname;
	
	public Database(String name){
		setDBName(name);
	}

	private void setDBName(String name) {
		this.dbname = name.substring(0, name.length() - 3);
		System.out.println("Database name = " + this.dbname);
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
		try {
			this.connect.close();
		} catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	}
	
	public void createTable(String table, LinkedHashMap<String, String> columns){
		StringBuffer sb = new StringBuffer();
		String query;
		sb.append("CREATE TABLE " + table + "(");
		for(Map.Entry<String, String> entry : columns.entrySet()){
			String key = entry.getKey();
			String value = entry.getValue();
			sb.append(key + " " + value + ",");			
		}
		query = sb.replace(sb.lastIndexOf(","), sb.lastIndexOf(",") + 1, ")").toString();
		//this.execute(query);
		System.out.println(query);
	}
	
	public void insert(String table, String[] values){
		StringBuffer sb = new StringBuffer();
		String query;
		sb.append("INSERT INTO " + table + " VALUES(");
		for(int i = 0; i < values.length; i++){
			String v = values[i];
			if(!this.isInteger(values[i]))
				sb.append("'" + v + "',");
			else
				sb.append(v + ",");
				
		}
		query = sb.replace(sb.lastIndexOf(","), sb.lastIndexOf(",") + 1, ")").toString();
		//this.execute(query);
		System.out.println(query);
	}
	
	public void update(String table, LinkedHashMap<String, String> setValue, LinkedHashMap<String, String> where){
		StringBuffer sb = new StringBuffer();
		String query;
		sb.append("UPDATE " + table + " SET ");
		for(Map.Entry<String, String> entry : setValue.entrySet()){
			String key = entry.getKey();
			String value = entry.getValue();
			if(!this.isInteger(value))
				sb.append(key + "='" + value + "',");
			else
				sb.append(key + "=" + value + ",");
		}
		sb = sb.replace(sb.lastIndexOf(","), sb.lastIndexOf(",") + 1, " ");
		sb.append("WHERE ");
		for(Map.Entry<String, String> entry : where.entrySet()){
			String key = entry.getKey();
			String value = entry.getValue();
			if(!this.isInteger(value))
				sb.append(key + "='" + value + "',");
			else
				sb.append(key + "=" + value + ",");
		}
		query = sb.replace(sb.lastIndexOf(","), sb.lastIndexOf(",") + 1, "").toString();
		//this.execute(query);
		System.out.println(query);
	}
	
	public void select(String table, String[] fields, LinkedHashMap<String, String> where){
		StringBuffer sb = new StringBuffer();
		String query;
		sb.append("SELECT ");
		for(int i = 0; i < fields.length; i++){
			String v = fields[i];
			sb.append(v + ",");
		}
		sb = sb.replace(sb.lastIndexOf(","), sb.lastIndexOf(",") + 1, " ");
		sb.append("FROM " + table + " WHERE ");
		for(Map.Entry<String, String> entry : where.entrySet()){
			String key = entry.getKey();
			String value = entry.getValue();
			if(!this.isInteger(value))
				sb.append(key + "='" + value + "',");
			else
				sb.append(key + "=" + value + ",");
		}
		query = sb.replace(sb.lastIndexOf(","), sb.lastIndexOf(",") + 1, "").toString();
		System.out.println(query);
	}
	
	private void execute(String query){
		Statement statement;
		try {
			statement = this.connect.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	}
	
	private boolean isInteger(String s){
		try{
			Integer.parseInt(s);
		}
		catch(NumberFormatException e){
			return false;
		}
		return true;
	}

	public String getName() {
		return this.dbname;
	}
	

}
