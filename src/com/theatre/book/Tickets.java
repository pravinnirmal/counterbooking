package com.theatre.book;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tickets  {
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
		
		Date date = new Date();
		Date time = new Date();
		SimpleDateFormat Formatter = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat Formatter1 = new SimpleDateFormat("HH:mm:ss");
		
		
		System.out.println();	
		System.out.println("--------------------------------------------------");
		System.out.println("               Ticket Information                 ");
		System.out.println("--------------------------------------------------");
		System.out.println(" Booking Date : " + Formatter.format(date));
		System.out.println(" Booking Time : " + Formatter1.format(time));
		System.out.println();
		System.out.println("--------------------------------------------------");
		System.out.println("Show No     Movie          Seats         Timing");
		System.out.println("--------------------------------------------------");
	}
	void displayTicket() {
		SimpleDateFormat Formatter1 = new SimpleDateFormat("HH:mm:ss");
		System.out.format("\n %-8s  %-15s %-9s  %11s \n",snumber,movieRun, tickets, Formatter1.format(screentime));
		System.out.println();
		
		
	}
	
	static void printTicket() {
		
		System.out.println("--------------------------------------------------");
		System.out.println("          Thank You. Enjoy your show.");
		System.out.println("--------------------------------------------------");
	}

}
