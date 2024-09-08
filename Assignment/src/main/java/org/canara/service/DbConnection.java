package org.canara.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	private static String URL = "jdbc:mysql://localhost:3306/EMPLOYEE";
	private static String USER_NAME = "root";
	private static String PASSWORD = "Nishal#20";
	public static Connection getDbConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
}
