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
import br.ufpb.threadControl.MessengerConcurrent.Controller.SendPromotionMail;
import br.ufpb.threadControl.MessengerConcurrent.Model.Client;
import br.ufpb.threadControl.MessengerConcurrent.Model.Product;
import br.ufpb.threadControl.MessengerConcurrent.Model.Promotion;

/**
 * Test case class that sends e-mail promotions of interest to the User
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 0.0.1
 * Copyright (C) 2011 Diego Sousa de Azevedo
 */
public class SendPromotionMailTest {
	
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
		SendPromotionMail sendPromotionMail = new SendPromotionMail();
		Facade facade = Facade.getInstance();

		Client client1 = new Client("Diego", "111", "diego.so@dce.ufpb.br",
				28, 10, 1988);
		facade.addClient(client1);
		Client client2 = new Client("Diego2", "222", "diego.sou@dce.ufpb.br",
				29, 10, 1988);
		facade.addClient(client1);
		Client client3 = new Client("Diego3", "333", "diego.sousa@dce.ufpb.br",
				30, 10, 1988);
		
		facade.addClient(client1);
		facade.addClient(client2);
		facade.addClient(client3);

		Product product1 = new Product("PenDriver", 123, 15.00, 100);
		Product product2 = new Product("HD", 133, 12.00, 100);
		Product product3 = new Product("Memory", 143, 20.00, 100);

		facade.addProduct(product1);
		facade.addProduct(product2);
		facade.addProduct(product3);

		facade.locateProduct(client1, product1.getCode());
		facade.buyProduct(client1, product2.getCode(), 8);

		facade.locateProduct(client2, product1.getCode());
		facade.locateProduct(client2, product3.getCode());
		facade.buyProduct(client2, product2.getCode(), 3);

		facade.locateProduct(client3, product3.getCode());

		Promotion promotion = new Promotion(product1, 10.00, 567);
		Promotion promotion2 = new Promotion(product2, 8.00, 568);
		Promotion promotion3 = new Promotion(product3, 2.00, 569);
		facade.addPromotion(promotion);
		facade.addPromotion(promotion2);
		facade.addPromotion(promotion3);

		facade.getExecutor().execute(sendPromotionMail);

		facade.getExecutor().shutdown();
	}

}
