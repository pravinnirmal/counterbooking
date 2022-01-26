package com.theatre.book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class TicketBooking extends Home {
	static int stime;
	static int tickets;

	public static void book() throws SQLException {
		System.out.println("Select Show Number :");
		Scanner scan = new Scanner(System.in);
		snumber = scan.nextInt();

		String q1 = "SELECT * FROM screen1 WHERE ShowNumber = '" + snumber + "'";

		ConnectionPage p = new ConnectionPage();
		p.connect();
		Statement st = p.conn.createStatement();
		ResultSet rs = st.executeQuery(q1);
		while (rs.next()) {
			snumber = rs.getInt(1);
			movieRun = rs.getString(2);
			available = rs.getInt(4);
			screentime = rs.getTime(3);

		}

		if (!(available == 0)) {
			System.out.println();
			System.out.println("Selected Movie : " + movieRun + " | Show Time : " + screentime);
			System.out.println();

			System.out.println("Seats Available : " + available);
			System.out.println("Enter number of seats you want to book:");

			tickets = scan.nextInt();

			available = available - tickets;
			updateSeats();
			ArrayList<Tickets> printTicket = new ArrayList<>();
			printTicket.add(new Tickets(tickets,movieRun,screentime));
			System.out.println("Booking Success.. Seats available : " + available);
			if (!(available == 0)) {
				System.out.println();
				System.out.println("Do you want to book again? Y or N");
				char option = scan.next().charAt(0);

				if (option == 'y' || option == 'Y') {

					book();
				} else {
					System.out.println("Print Ticket");
					System.out.println("----------------------");
					for(Tickets t: printTicket) {
						t.displayTicket();
					}

					System.exit(0);

				}
			}

		} else {
			System.out.println("Sorry. .All seats are booked. Try other shows.");
			System.out.println("*****************************");
			Home.homePage();;
		}
			scan.close();
	}


	public static void updateSeats() throws SQLException {
		String q2 = "UPDATE screen1 SET seats = '" + available + "' WHERE ShowNumber = " + snumber;
		ConnectionPage s = new ConnectionPage();
		s.connect();
		Statement sst = s.conn.createStatement();

		sst.execute(q2);
	}
	
}
