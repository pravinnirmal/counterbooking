package com.theatre.book;

import java.sql.Time;

public class Tickets {
	private int tickets;
	private String movieRun;
	private Time screentime;
	
	Tickets(int tickets, String movieRun, Time screentime) {
		super();
		this.tickets = tickets;
		this.movieRun = movieRun;
		this.screentime = screentime;
	}
	
	void displayTicket() {
		System.out.format("\n %10s %-3s %hh:mm \n", "Movie : ",movieRun, "Booked Seats: ", tickets, screentime);
		
		
	}

}
