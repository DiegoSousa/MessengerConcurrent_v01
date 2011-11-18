package br.ufpb.threadControl.MessengerConcurrent.Controller;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.LinkedBlockingQueue;

import br.ufpb.threadControl.MessengerConcurrent.Model.Client;

/**
 * Class that creates a list of birthdays of the day or a specific day.
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 0.0.1 Copyright (C) 2011 Diego Sousa de Azevedo
 */

public class CheckerBirthday implements Runnable {

	private Facade facade;
	private LinkedBlockingQueue<Client> listClient;
	private LinkedBlockingQueue<Client> listOfBirthdaysOfTheDay;
	private Calendar calendar;
	private int day;
	private int month;
	private boolean terminate;

	/*
	 * constructor for manual search of birthdays 
	 */
		
	public CheckerBirthday(int day, int month) {
		this.listOfBirthdaysOfTheDay = new LinkedBlockingQueue<Client>();		
		this.day = day;
		this.month = month;
		this.calendar = Calendar.getInstance();
		this.terminate= false;
		this.facade = Facade.getInstance();
	}
	
	/*
	 * Constructor Automatic. Gets the list of birthdays of the day
	 */
	
	public CheckerBirthday() {
		this.listOfBirthdaysOfTheDay = new LinkedBlockingQueue<Client>();
		this.day = calendar.get(GregorianCalendar.DATE);
		this.month = ((calendar.get(GregorianCalendar.MONTH)) + 1);
		this.calendar = Calendar.getInstance();
		this.terminate= false;
		this.facade = Facade.getInstance();
	}

	@Override
	public void run() {
		listClient = facade.getListClient();
		for (Client client : listClient) {
			if (client.getBirthday() == day
					&& client.getMonthOfBirth() == month) {
				try {
					listOfBirthdaysOfTheDay.put(client);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}		
		terminate = true;
	}
	
	public boolean getBirthdayCompleteList(){
		return terminate;		
	}
	
	public LinkedBlockingQueue<Client> getListClientOfBirthdays() {
		return listOfBirthdaysOfTheDay;
	}
}
