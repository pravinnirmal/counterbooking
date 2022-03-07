package com.theatre.book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class TicketBooking extends Home {
	static int stime;
	static int tickets;
	static ArrayList<Tickets> printTicket = new ArrayList<>();
	static ArrayList <String> seatData = new ArrayList<>();
	static String seats;

	public static void book() throws SQLException {

		System.out.println("Select Show Number :");
		Scanner scan = new Scanner(System.in);
		snumber = scan.nextInt();

		String q1 = "SELECT Seats FROM `seat_status-s1` WHERE `S" + snumber + "` = 'Available'";
		String q2 = "SELECT COUNT(S" + snumber + ") FROM `seat_status-s1` WHERE S" + snumber + " ='Available'";
		String q3 = "SELECT * FROM screen1 WHERE `Show Number` = '" + snumber + "'" ;
		ConnectionPage p = new ConnectionPage();
		p.connect();
		Statement st = p.conn.createStatement();
		ResultSet rs2 = st.executeQuery(q2);
		while(rs2.next()) {
			available = rs2.getInt(1);
		}
		
		
		ResultSet rs = st.executeQuery(q3);
		if (rs.next()) {
			snumber = rs.getInt(1);
			movieRun = rs.getString(2);
			screentime = rs.getTime(3);
	//		available = rs.getInt(4);

			if (!(available == 0)) {
				System.out.println();
				System.out.println("Selected Movie : " + movieRun + " | Show Time : " + screentime);
				System.out.println();
				System.out.println("Available Seats");
				
					ResultSet rs3 = st.executeQuery(q1);
					while(rs3.next()) {
						seats = rs3.getString(1);
						seatData.add(rs3.getString(1));
						System.out.print(seats + " ");
						String seatArray [] = seatData.toArray(new String[seatData.size()]);
						
					}
					
								
				System.out.println();
				System.out.println("Total Available : " + available);
				System.out.println();
				System.out.println("Enter seats to book:");

				String sticket = scan.nextLine();
				sticket+=scan.nextLine();
				
				sticket=sticket.toUpperCase();
				
					
				
				String stb [] = sticket.split(",");
				{
				System.out.println("Selected Seats");
				for (String s: stb) {
					System.out.print(s + ", ");
				}

				available = available - stb.length;
				updateSeats(stb);
				System.out.println();
				System.out.println("Booking Success.. Seats available : " + available);
				}
				

			}

				else {
					System.out.println("Sorry Seats Unavailable. Try other shows.");
					System.out.println("*****************************");
					Home.homePage();
	
				}

			if (!(available == 0)) {
				System.out.println();
				System.out.println("Do you want to book again? Y or N");
				char option = scan.next().charAt(0);

				if (option == 'y' || option == 'Y') {
					book();
				} else {
					Tickets.ticketInfo();

					for (Tickets t : printTicket) {
						t.displayTicket();
					}
					Tickets.printTicket();
					System.exit(0);
				}
			}

			else {
				System.out.println("All Seats are booked. Try Other Shows.");
			}
			scan.close();
		}

		else {
			System.out.println("Error!! Enter Correct Show Number");
			book();
		}
	}

	public static void updateSeats(String[] stb) throws SQLException {
		tickets = stb.length;    //temporary fix until seperate ticket printing fixed
		printTicket.add(new Tickets(snumber, movieRun, tickets, screentime));
//		String q2 = "UPDATE screen SET seats = '" + available + "' WHERE `Show Number` = " + snumber;
		for (String sa: stb) {
		String q2 = "UPDATE `seat_status-s1` SET S" + snumber + "= 'Booked' WHERE Seats = '"+sa+"'";
		
		ConnectionPage s = new ConnectionPage();
		s.connect();
		Statement sst = s.conn.createStatement();

		sst.execute(q2);
		}
	}

}
