package com.theatre.book;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class TicketBooking extends Home {
	static int stime;
	static int tickets;
	static ArrayList<Tickets> printTicket = new ArrayList<>();

	public static void book() throws SQLException {
		
		
		System.out.println("Select Show Number :");
		Scanner scan = new Scanner(System.in);
		snumber = scan.nextInt();

		String q1 = "SELECT * FROM screen1 WHERE `Show Number` = '" + snumber + "'";

		ConnectionPage p = new ConnectionPage();
		p.connect();
		Statement st = p.conn.createStatement();
		ResultSet rs = st.executeQuery(q1);
		if(rs.next()) {
			snumber = rs.getInt(1);
			movieRun = rs.getString(2);
			screentime = rs.getTime(3);
			available = rs.getInt(4);
			
			if (!(available == 0)) {
				System.out.println();
				System.out.println("Selected Movie : " + movieRun + " | Show Time : " + screentime);
				System.out.println();

				System.out.println("Seats Available : " + available);
				System.out.println("Enter number of seats you want to book:");

				tickets = scan.nextInt();

				available = available - tickets;
				updateSeats();
				
				
				
				
				System.out.println("Booking Success.. Seats available : " + available);
				if (!(available == 0)) {
					System.out.println();
					System.out.println("Do you want to book again? Y or N");
					char option = scan.next().charAt(0);

					if (option == 'y' || option == 'Y') {

						book();
					} else {
						Tickets.ticketInfo();
										
						for(Tickets t: printTicket) {
							
							t.displayTicket();
							}
								Tickets.printTicket();		
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


		 
	else {
			System.out.println("Error!! Enter Correct Show Number");
			book();
		}
	}

		

	public static void updateSeats() throws SQLException {
		printTicket.add(new Tickets(snumber,movieRun,tickets,screentime));
		String q2 = "UPDATE screen1 SET seats = '" + available + "' WHERE `Show Number` = " + snumber;
		ConnectionPage s = new ConnectionPage();
		s.connect();
		Statement sst = s.conn.createStatement();

		sst.execute(q2);
	}
	
}
