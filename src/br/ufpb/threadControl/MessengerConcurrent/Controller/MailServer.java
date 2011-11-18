package br.ufpb.threadControl.MessengerConcurrent.Controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import br.ufpb.threadControl.MessengerConcurrent.Model.Client;

/**
 * Class send e-mail customer birthdays
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 0.0.1 Copyright (C) 2011 Diego Sousa de Azevedo
 */

public class MailServer implements Runnable {

	private Facade facade;
	private CheckerBirthday checkerBirthday;		
	private Date date;
	private SimpleDateFormat dateFormat;
	private Logger logger;

	/*
	 * Constructor that Sends emails of congratulations for birthdays of the present day
	 */
	
	
	public MailServer() {
		this.facade = Facade.getInstance();
		this.checkerBirthday = new CheckerBirthday();
		this.date = new Date(System.currentTimeMillis());
		this.dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		this.logger = Logger.getLogger("br.ufpb.threadControl.birthdayMessage.Controller.MailServer");
	}
		
	
	public MailServer(int day, int month) {
		this.facade = Facade.getInstance();
		this.checkerBirthday = new CheckerBirthday(day, month);
		this.date = new Date(System.currentTimeMillis());
		this.dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		this.logger = Logger.getLogger("br.ufpb.threadControl.birthdayMessage.Controller.MailServer");
	}

	@Override
	public void run() {
		facade.getExecutor().execute(checkerBirthday); 
			
		while(checkerBirthday.getBirthdayCompleteList() == false){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}				
		}						
		
		if (checkerBirthday.getListClientOfBirthdays().size() != 0) {
			LinkedBlockingQueue<Client> listAux = checkerBirthday
					.getListClientOfBirthdays();
			for (Client client : listAux) {
				logger.info("Mail of congratulations sent to: "
						+ client.getMail() + ". Client Name: " + client.getName());
				checkerBirthday.getListClientOfBirthdays().remove(client);
			}
			logger.info("All email sent to Birthdays Congratulations were the days: "
					+ dateFormat.format(date));
		} else {
			logger.warning("No birthday in: "
					+ dateFormat.format(date));
		}
	}
}