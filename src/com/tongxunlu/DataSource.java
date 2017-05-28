package com.tongxunlu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class DataSource {
	public static Connection getConnection() throws SQLException {
		String dbname = "tongxunlu";
		String url = 
				"jdbc:mysql://localhost:3306/" + dbname +"?characterEncoding=utf-8";
		String user = "root";
		String password = "10550205";
		DriverManager.registerDriver(new Driver());
		return DriverManager.getConnection(url, user, password);
	}
}
