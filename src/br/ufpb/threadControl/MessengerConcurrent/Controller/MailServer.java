package br.ufpb.threadControl.MessengerConcurrent.Controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import br.ufpb.threadControl.MessengerConcurrent.Model.Client;


/**
 * Class 
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 0.0.1 Copyright (C) 2011 Diego Sousa de Azevedo
 */

public class MailServer implements Runnable {

	private CheckerBirthday checkerBirthday;
	private Fachada fachada = Fachada.getInstance();
	private SimpleDateFormat dateFormat;
	private Date date;
	private Logger logger = Logger
			.getLogger("br.ufpb.threadControl.birthdayMessage.Controller.MailServer");

	public MailServer() {
		this.checkerBirthday = new CheckerBirthday();
		this.date = new Date(System.currentTimeMillis());
		this.dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	}

	@Override
	public void run() {
		fachada.getExecutor().execute(checkerBirthday); //Dar uma olhada!
			
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
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