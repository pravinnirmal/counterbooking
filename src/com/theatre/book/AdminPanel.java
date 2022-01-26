package com.theatre.book;

import java.util.Scanner;
import java.sql.*;

public class AdminPanel {
	static Scanner scan = new Scanner(System.in);

	static void admin() throws SQLException {

		System.out.println("Enter Admin Id");

		String adminid = scan.next();
		System.out.println("Enter Password");
		String pwd = scan.next();
		String q = "SELECT * FROM admin WHERE AdminID = '" + adminid + "'";

		ConnectionPage p = new ConnectionPage();
		p.connect();
		Statement stt = p.conn.createStatement();
		ResultSet rs = stt.executeQuery(q);

		if (rs.next()) {
			System.out.println("Welcome Admin");
			System.out.println("--------------------");
			System.out.println("1. Reset Seats");
			System.out.println("2. Change Movie Name");
			System.out.println("3. Change Show Timing");
			System.out.println("4. Cancel Show");
			System.out.println();
			System.out.println("Enter Option:");
			System.out.println();

			switchM();

		} else {
			System.out.println("Error. Not an Admin account.");
		}

	}

	private static void switchM() throws SQLException {

		int option = scan.nextInt();

		switch (option) {
		case 1:
			reset();
			break;
		case 2:
			changeMovie();
			break;
		case 3:
			changeTime();
			break;
		case 4:
			cancelShow();
			break;
		default:
			System.out.println("Enter Proper Option:");
			switchM();
			break;

		}
	}

	private static void cancelShow() throws SQLException {
		ConnectionPage p = new ConnectionPage();
		p.connect();
		
		System.out.println("Enter Show Number");
		int sNumber = scan.nextInt();
		
		String q2 = "UPDATE screen1 SET MovieName = 'Show Cancelled' WHERE ShowNumber = " + sNumber;
		String q3 = "UPDATE screen1 SET seats = 0 WHERE ShowNumber = " + sNumber;
		Statement sst = p.conn.createStatement();

		sst.execute(q2);
		sst.execute(q3);
		System.out.println("Show Cancelled Successfully.");
		Home.main(null);
	}

	private static void changeTime() throws SQLException {
		System.out.println("WIP");
		Home.main(null);

	}

	private static void changeMovie() throws SQLException {
		ConnectionPage p = new ConnectionPage();
		p.connect();
		
		System.out.println("Enter New Movie Name: ");
		String updatedMovie = scan.next();
		System.out.println("Enter Show Number");
		int sNumber = scan.nextInt();
		
		String q2 = "UPDATE screen1 SET MovieName = '" + updatedMovie + "' WHERE ShowNumber = " + sNumber;
		Statement sst = p.conn.createStatement();

		sst.execute(q2);
		System.out.println("Movie Change Done.");
		Home.main(null);

	}

	private static void reset() throws SQLException {
		ConnectionPage p = new ConnectionPage();
		p.connect();
		String q2 = "UPDATE screen1 SET seats = 50";
		Statement sst = p.conn.createStatement();

		sst.execute(q2);
		System.out.println("Seats reset done.");
		Home.main(null);

	}

}
