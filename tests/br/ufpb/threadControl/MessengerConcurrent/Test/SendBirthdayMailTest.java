/**
 * 
 */
package br.ufpb.threadControl.MessengerConcurrent.Test;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ufpb.threadControl.MessengerConcurrent.Controller.Facade;
import br.ufpb.threadControl.MessengerConcurrent.Controller.SendBirthdayMail;
import br.ufpb.threadControl.MessengerConcurrent.Model.Client;

/**
 * Test case class that sends e-mail birthday congratulations to customers
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 0.0.1
 * Copyright (C) 2011 Diego Sousa de Azevedo
 */
public class SendBirthdayMailTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRun() {
Facade fachada = Facade.getInstance();
		
		SendBirthdayMail sendBirthday = new SendBirthdayMail();
		SendBirthdayMail sendBirthday2 = new SendBirthdayMail(23,11);
		SendBirthdayMail sendBirthday3 = new SendBirthdayMail(24,11);
		
		Client client = new Client("Diego2","23432","diego@diego.com",24,11,2011);
		Client client2 = new Client("Diego3","234324","diego@diehgo.com",23,11,2011);
		Client client3 = new Client("Diego4","2d34324","diegoe@diehgo.com",06,11,2011);

//------------------------------------------------------------------------------------------
// Add Three Client		
		fachada.addClient(client);								
		fachada.addClient(client2);								
		fachada.addClient(client3);								
//------------------------------------------------------------------------------------------
				
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		fachada.getExecutor().execute(sendBirthday);		
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
				
		fachada.getExecutor().execute(sendBirthday2);		
		try {			
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			
		fachada.getExecutor().execute(sendBirthday3);				
		try {			
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

//------------------------------------------------------------------------------------------		
		
		fachada.getExecutor().shutdown();
	}

	}


