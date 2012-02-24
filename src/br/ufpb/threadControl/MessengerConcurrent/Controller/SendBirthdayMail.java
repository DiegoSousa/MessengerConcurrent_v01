package br.ufpb.threadControl.MessengerConcurrent.Controller;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;

/**
 * Class that creates a list of birthdays of the day or a specific day.
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 2.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class SendBirthdayMail implements Runnable {

	private Facade facade;
	private Calendar calendar;
	private int day;
	private int month;
	private Logger logger;
	private BlockingQueue<BlockingQueue<Client>> listOfClient;
	private BlockingQueue<Client> listOfClientReturn;

	/*
	 * constructor for manual search of birthdays
	 */

	public SendBirthdayMail(int day, int month) {
		this.listOfClient = new LinkedBlockingQueue<BlockingQueue<Client>>();
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
		this.listOfClient = new LinkedBlockingQueue<BlockingQueue<Client>>();
		this.calendar = Calendar.getInstance();
		this.day = calendar.get(GregorianCalendar.DATE);
		this.month = ((calendar.get(GregorianCalendar.MONTH)) + 1);
		this.facade = Facade.getInstance();
		this.logger = Logger
				.getLogger("br.ufpb.threadControl.birthdayMessage.Controller.MailServer");
	}

	@Override
	public void run() {

		facade.getListOfClient(listOfClient);

		try {
			listOfClientReturn = listOfClient.take();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		if (listOfClientReturn != null) {
			for (Client client : listOfClientReturn) {
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