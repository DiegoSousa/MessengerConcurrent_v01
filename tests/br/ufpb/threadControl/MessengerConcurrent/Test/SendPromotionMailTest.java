package br.ufpb.threadControl.MessengerConcurrent.Test;

import br.ufpb.threadControl.MessengerConcurrent.Controller.Facade;
import br.ufpb.threadControl.MessengerConcurrent.Controller.SendPromotionMail;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Product;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Promotion;

/**
 * Test case class that sends e-mail promotions of interest to the User
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 2.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class SendPromotionMailTest {

	public static void main(String[] args) {

		SendPromotionMail sendPromotionMail = new SendPromotionMail();
		Facade facade = Facade.getInstance();

		Client client1 = new Client("Diego", "111", "diego.so@dce.ufpb.br", 28,
				10, 1988);
		Client client2 = new Client("Diego2", "222", "diego.sou@dce.ufpb.br",
				29, 10, 1988);
		Client client3 = new Client("Diego3", "333", "diego.sousa@dce.ufpb.br",
				30, 10, 1988);
		
		Product product1 = new Product("PenDriver", 123, 15.00, 100);
		Product product2 = new Product("HD", 133, 12.00, 100);
		Product product3 = new Product("Memory", 143, 20.00, 100);
		
		Promotion promotion = new Promotion(product1, 10.00, 567);
		Promotion promotion2 = new Promotion(product2, 8.00, 568);
		Promotion promotion3 = new Promotion(product3, 2.00, 569);
				
		facade.addClient(client1);
		facade.addClient(client2);
		facade.addClient(client3);

		facade.addProduct(product1);
		facade.addProduct(product2);
		facade.addProduct(product3);

		facade.addPromotion(promotion);
		facade.addPromotion(promotion2);
		facade.addPromotion(promotion3);
				
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		
		facade.buyProduct(client1, product1, 5);				
		facade.buyProduct(client2, product2, 10);
		facade.buyProduct(client2, product3, 20);		
		facade.buyProduct(client3, product1, 40);
		facade.buyProduct(client3, product2, 80);
		facade.buyProduct(client3, product3, 100);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}			
		
		facade.getExecutor().execute(sendPromotionMail);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	

		facade.getExecutor().shutdown();
	}

}
