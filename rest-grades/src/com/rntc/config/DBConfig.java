package com.rntc.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {
	/**
	 * This method gets a connection into the database.
	 * 
	 * @return Nothing.
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * 
	 * @author reis
	 * @version 1.0
	 * @since 5/21/2017
	 */
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		Class.forName("org.postgresql.Driver");
		return DriverManager.getConnection("jdbc:postgresql://localhost:5432/gradesdb", "postgres", "admin" );
	}

}
