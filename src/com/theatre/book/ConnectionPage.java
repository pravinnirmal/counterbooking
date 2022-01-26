package com.theatre.book;

import java.sql.*;

public class ConnectionPage {
	Connection conn;

	public void connect() throws SQLException {

		String url = "jdbc:mysql://localhost:3306/theatre";
		String username = "root";
		String password = "dossboss";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
