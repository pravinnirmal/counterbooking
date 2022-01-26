package com.theatre.book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Scanner;

public class Home {
	static int available;
	static String movieRun;
	static Time screentime;
	static int snumber;

	public static void main(String[] args) throws SQLException {
		Scanner scan = new Scanner (System.in);
		System.out.println("Counter Booking (1)  | Admin Access (2)");
		int enter = scan.nextInt();
		
		if (enter ==1) {
			System.out.println("Welcome to cinemas booking");
			homePage();
			}
		else if(enter == 2) {
			AdminPanel.admin();
		}
		else {
			System.out.println("Enter correct option. Try Again..");
		}
	}
	
	public static void homePage() throws SQLException {
		String query = "SELECT * FROM screen1";
		
		System.out.println("---------------------------");
		ConnectionPage s = new ConnectionPage();
		s.connect();
		Statement stt = s.conn.createStatement();
		ResultSet rs = stt.executeQuery(query);

		while (rs.next()) {
			snumber = rs.getInt(1);
			movieRun = rs.getString(2);
			available = rs.getInt(4);
			screentime = rs.getTime(3);
			System.out.println("Show Number     : " + " " + snumber);
			System.out.println("Movie           : " + movieRun);
			System.out.println("Show Timings    : " + screentime);
			System.out.println("Available Seats : " + available);
			System.out.println("-----------------------------");
		}

		TicketBooking.book();
}
}