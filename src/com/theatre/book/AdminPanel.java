package com.theatre.book;

import java.util.Scanner;
import java.sql.*;

public class AdminPanel {
	static Scanner scan = new Scanner(System.in);

	static void admin() throws SQLException {
		
		System.out.println("Enter Admin Id");

		String adminid = scan.next();
//		System.out.println("Enter Password");
//		String pwd = scan.next();
		String q = "SELECT * FROM admin WHERE AdminID = '" + adminid + "'";

		ConnectionPage p = new ConnectionPage();
		p.connect();
		Statement stt = p.conn.createStatement();
		ResultSet rs = stt.executeQuery(q);

		if (rs.next()) {
			System.out.println("Welcome Admin");
			adminMenu();

		} else {
			System.out.println("Error. Not an Admin account.");
		}
scan.close();
	}
	
	private static void adminMenu() throws SQLException {
		
		System.out.println("--------------------");
		System.out.println("1. Reset All Shows");
		System.out.println("2. Reset Particular Show");
		System.out.println("3. Change Movie Name");
		System.out.println("4. Change Show Timing");
		System.out.println("5. Cancel Show");
		System.out.println();
		System.out.println("Enter Option:");
		System.out.println();

		switchM();
	}

	private static void switchM() throws SQLException {
		
		int option = scan.nextInt();

		switch (option) {
		case 1:
			resetAll();
			break;
		case 2:
			reset();
			break;
		case 3:
			changeMovie();
			break;
		case 4:
			changeTime();
			break;
		case 5:
			cancelShow();
			break;
		default:
			System.out.println("Enter Proper Option:");
			switchM();
			break;

		}
		scan.close();
	}

	
	private static void reset() throws SQLException {
		
		ConnectionPage p = new ConnectionPage();
		p.connect();
		System.out.println("Enter Show Number To Reset :");
		int sNumber = scan.nextInt();
		String q2 = "UPDATE screen1 SET seats = 50 WHERE `Show Number` = " + sNumber;
		Statement sst = p.conn.createStatement();

		sst.execute(q2);
		System.out.println("Show " + sNumber+ " reset done.");
		System.out.println();
		adminMenu();
		scan.close();
		
	}

	private static void cancelShow() throws SQLException {
		
		ConnectionPage p = new ConnectionPage();
		p.connect();
		
		System.out.println("Enter Show Number");
		int sNumber = scan.nextInt();
		
		String q2 = "UPDATE screen1 SET MovieName = 'Show Cancelled' WHERE `Show Number` = " + sNumber;
		String q3 = "UPDATE screen1 SET seats = 0 WHERE `Show Number` = " + sNumber;
		Statement sst = p.conn.createStatement();

		sst.execute(q2);
		sst.execute(q3);
		System.out.println("Show Cancelled Successfully.");
		System.out.println();
		adminMenu();
		scan.close();
	}

	
	private static void changeTime() throws SQLException {
		System.out.println("WIP");
		adminMenu();

	}

	
	private static void changeMovie() throws SQLException {
				
		ConnectionPage p = new ConnectionPage();
		p.connect();
		
		System.out.println("Enter Show Number:");
		int sNumber = scan.nextInt();
		System.out.println("Enter New Movie Name: ");
		String updatedMovie;
		updatedMovie= scan.nextLine();
		updatedMovie+=scan.nextLine();
		
		String q2 = "UPDATE screen1 SET MovieName = '" + updatedMovie + "' WHERE `Show Number` = " + sNumber;
		Statement sst = p.conn.createStatement();

		sst.execute(q2);
		System.out.println("Movie Change Done.");
		System.out.println();
		adminMenu();
		scan.close();

	}

	
	private static void resetAll() throws SQLException {
		
		ConnectionPage p = new ConnectionPage();
		p.connect();
		String q2 = "UPDATE screen1 SET seats = 50";
		Statement sst = p.conn.createStatement();

		sst.execute(q2);
		System.out.println("Seats reset done.");
		System.out.println();
		adminMenu();
		scan.close();
	}

}
