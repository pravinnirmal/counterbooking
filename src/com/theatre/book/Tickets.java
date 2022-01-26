package com.theatre.book;

import java.sql.Time;

public class Tickets {
	private int snumber;
	private int tickets;
	private String movieRun;
	private Time screentime;
	
	Tickets(int snumber, String movieRun, int tickets,  Time screentime) {
		super();
		this.snumber=snumber;
		this.tickets = tickets;
		this.movieRun = movieRun;
		this.screentime = screentime;
	}
	static void ticketInfo() {
		System.out.println("--------------------------------------------------");
		System.out.println("               Ticket Information                 ");
		System.out.println("--------------------------------------------------");
		System.out.println("Show No     Movie        Seats         Timing");
		System.out.println("--------------------------------------------------");
	}
	void displayTicket() {
		
		System.out.format("\n %-8s  %-15s %-5s \n",snumber,movieRun, tickets, screentime);
		
		
	}
	
	static void printTicket() {
		
		System.out.println("--------------------------------------------------");
	}

}
