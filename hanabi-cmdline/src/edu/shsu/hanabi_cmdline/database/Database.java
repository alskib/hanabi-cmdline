package edu.shsu.hanabi_cmdline.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 * This class contains functions that will communicate with a database
 * @author Johnny Nguyen jtn005@shsu.edu
 * @version 1.0
 * @since 23 Apr 2014
 */
public class Database {
	
	private Connection connect;
	private String dbname;
	private ResultSet resultSet;
	
	/**
	 * Constructor
	 * @param name database name to be connected to
	 */
	public Database(String name){
		setDBName(name);
	}

	/**
	 * This function sets the name of the database
	 * @param name The name of the database
	 */
	private void setDBName(String name) {
		this.dbname = name.substring(0, name.length() - 3);
		System.out.println("Database name = " + this.dbname);
	}
	
	/**
	 * This function connects to this database object
	 */
	public void connect(){
		this.connect = null;
		try {
			Class.forName("org.sqlite.JDBC");
			this.connect = DriverManager.getConnection("jdbc:sqlite:" + this.getName() + ".db");
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}
	
	/**
	 * This function closes the connection to this database object
	 */
	public void close(){
		try {
			this.connect.close();
		} catch (SQLException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Closed database successfully");
	}
	
	/**
	 * This function creates a table in this database
	 * @param table The name of the table to be created
	 * @param columns A Linked Hashmap of fields and datatype [Field, Datatype]
	 */
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
		this.execute(query);
	}
	
	/**
	 * This function creates a SQL Insertion statement and executes. This function is limited to only
	 * one record insertion.
	 * @param table The table to in data into
	 * @param values
	 */
	public void insert(String table, LinkedHashMap<String, String> values){
		StringBuffer sb = new StringBuffer();
		StringBuffer val = new StringBuffer();
		String query;
		sb.append("INSERT INTO " + table + "(");
		val.append(" VALUES(");
		for(Map.Entry<String, String> entry : values.entrySet()){
			String key = entry.getKey();
			String v = entry.getValue();
			sb.append(key + ",");
			val.append(v + ",");
		}
		sb = sb.replace(sb.lastIndexOf(","), sb.lastIndexOf(",") + 1, ")");
		val = val.replace(val.lastIndexOf(","), val.lastIndexOf(",") + 1, ")");
		query = sb.toString() + val.toString();
		System.out.println(query);
		//this.execute(query);
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
		this.execute(query);
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
		sb.append("FROM " + table);
		if(null != where){
			sb.append(" WHERE ");
			for(Map.Entry<String, String> entry : where.entrySet()){
				String key = entry.getKey();
				String value = entry.getValue();
				if(!this.isInteger(value))
					sb.append(key + "='" + value + "',");
				else
					sb.append(key + "=" + value + ",");
			}
			sb = sb.replace(sb.lastIndexOf(","), sb.lastIndexOf(",") + 1, "");
		}
		query = sb.toString();
		this.execute(query);
	}
	
	public ResultSet getResultSet() {
		return this.resultSet;
	}

	public void dropTable(String table){
		StringBuffer sb = new StringBuffer();
		String query;
		sb.append("DROP TABLE " + table);
		query = sb.toString();
		this.execute(query);
	}
	
	private void execute(String query){
		Statement statement;
		try {
			statement = this.connect.createStatement();
			if(query.substring(0, 6).equals("SELECT")){
				ResultSet rs = statement.executeQuery(query);
				this.resultSet = rs;
			}
			else{
				statement.executeUpdate(query);
			}
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
