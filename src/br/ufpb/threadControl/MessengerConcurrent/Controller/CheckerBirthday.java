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

	private Fachada fachada = Fachada.getInstance();
	private LinkedBlockingQueue<Client> listClient;
	private LinkedBlockingQueue<Client> listOfBirthdaysOfTheDay;
	private Calendar calendar = Calendar.getInstance();
	private int day;
	private int month;

	/*
	 * constructor for manual search of birthdays 
	 */
		
	public CheckerBirthday(int day, int month) {
		this.listOfBirthdaysOfTheDay = new LinkedBlockingQueue<Client>();
		this.day = day;
		this.month = month;
	}
	
	/*
	 * Constructor Automatic. Gets the list of birthdays of the day
	 */
	
	public CheckerBirthday() {
		this.listOfBirthdaysOfTheDay = new LinkedBlockingQueue<Client>();
		this.day = calendar.get(GregorianCalendar.DATE);
		this.month = ((calendar.get(GregorianCalendar.MONTH)) + 1);
	}

	@Override
	public void run() {
		listClient = fachada.getListClient();
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
	}

	public LinkedBlockingQueue<Client> getListClientOfBirthdays() {
		return listOfBirthdaysOfTheDay;
	}
}
