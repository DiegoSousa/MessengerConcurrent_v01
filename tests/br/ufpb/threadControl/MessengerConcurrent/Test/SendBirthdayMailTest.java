package br.ufpb.threadControl.MessengerConcurrent.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import br.ufpb.threadControl.MessengerConcurrent.Controller.Facade;
import br.ufpb.threadControl.MessengerConcurrent.Controller.SendBirthdayMail;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;

/**
 * Test case class that sends e-mail birthday congratulations to customers
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 2.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class SendBirthdayMailTest {

	public static void main(String[] args) {

		Facade facade = Facade.getInstance();
		Calendar calendar = Calendar.getInstance();

		SendBirthdayMail sendBirthday = new SendBirthdayMail();
		SendBirthdayMail sendBirthday2 = new SendBirthdayMail(23, 11);
		SendBirthdayMail sendBirthday3 = new SendBirthdayMail(24, 11);

		Client client = new Client("Diego2", "23432", "diego@diego.com", 24,
				11, 1988);
		Client client2 = new Client("Diego3", "234324", "diego1@diego.com", 23,
				11, 1988);
		Client client3 = new Client("Diego4", "2d34324", "diego2@diego.com",
				calendar.get(GregorianCalendar.DATE),
				((calendar.get(GregorianCalendar.MONTH)) + 1), 1977);

		facade.addClient(client);
		facade.addClient(client2);
		facade.addClient(client3);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		facade.getExecutor().execute(sendBirthday);
		facade.getExecutor().execute(sendBirthday2);
		facade.getExecutor().execute(sendBirthday3);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// ------------------------------------------------------------------------------------------

		facade.getExecutor().shutdown();
	}

}
