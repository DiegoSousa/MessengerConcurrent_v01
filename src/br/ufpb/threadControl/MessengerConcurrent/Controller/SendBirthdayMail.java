package br.ufpb.threadControl.MessengerConcurrent.Controller;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;
import br.ufpb.threadControl.MessengerConcurrent.Model.Client;

/**
 * Class that creates a list of birthdays of the day or a specific day.
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 0.0.1 Copyright (C) 2011 Diego Sousa de Azevedo
 */

public class SendBirthdayMail implements Runnable {

	private Facade facade;
	private LinkedBlockingQueue<Client> listClient;
	private Calendar calendar;
	private int day;
	private int month;
	private Logger logger;

	/*
	 * constructor for manual search of birthdays
	 */

	public SendBirthdayMail(int day, int month) {
		this.day = day;
		this.month = month;
		this.facade = Facade.getInstance();
		this.logger = Logger
				.getLogger("br.ufpb.threadControl.birthdayMessage.Controller.MailServer");
	}

	/*
	 * Constructor Automatic. Gets the list of birthdays of the day
	 */

	public SendBirthdayMail() {
		this.calendar = Calendar.getInstance();
		this.day = calendar.get(GregorianCalendar.DATE);
		this.month = ((calendar.get(GregorianCalendar.MONTH)) + 1);
		this.facade = Facade.getInstance();
		this.logger = Logger
				.getLogger("br.ufpb.threadControl.birthdayMessage.Controller.MailServer");
	}

	@Override
	public void run() {
		listClient = facade.getListClient();

		while(listClient==null){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
				
		if (listClient.isEmpty() == false) {
			for (Client client : listClient) {
				if (client.getBirthday() == day
						&& client.getMonthOfBirth() == month) {
					logger.info("Mail of congratulations sent to: "
							+ client.getMail() + ". Client Name: "
							+ client.getName());
				}
			}
			logger.info("All email sent to Birthdays Congratulations were the days: "
					+ day + "/" + month);
		} else {
			logger.info("No birthday in: " + day + "/" + month);
		}
	}
}
