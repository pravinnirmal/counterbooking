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
		Scanner scan = new Scanner(System.in);
		System.out.println("Counter Booking (1)  | Admin Access (2)");
		int enter = scan.nextInt();

		if (enter == 1) {
			System.out.println("Welcome to cinemas booking");
			homePage();
		} else if (enter == 2) {
			AdminPanel.admin();
		} else {
			System.out.println("Enter correct option. Try Again..");
			Home.main(args);
			scan.close();
		}
	}

	public static void homePage() throws SQLException {

		System.out.println("---------------------------");
		ConnectionPage s = new ConnectionPage();
		s.connect();

		Statement stt = s.conn.createStatement();

		for (int i = 1; i < 5; i++) {
			String aQuery = "SELECT COUNT(S" + i + ") FROM `seat_status-s1` WHERE S" + i + " ='Available'";
			String query = "SELECT * FROM screen1 WHERE `Show Number` = " + i;
			ResultSet rs2 = stt.executeQuery(aQuery);

			while (rs2.next()) {
				available = rs2.getInt(1);
			}
			ResultSet rs = stt.executeQuery(query);
			while (rs.next()) {
				snumber = rs.getInt(1);
				movieRun = rs.getString(2);
				screentime = rs.getTime(3);

			}
			System.out.println("Show Number     : " + " " + snumber);
			System.out.println("Movie           : " + movieRun);
			System.out.println("Show Timings    : " + screentime);
			System.out.println("Available Seats : " + available);
			System.out.println("-----------------------------");

		}

		TicketBooking.book();

	}

}
