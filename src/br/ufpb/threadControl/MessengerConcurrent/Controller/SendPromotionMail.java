package br.ufpb.threadControl.MessengerConcurrent.Controller;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;
import br.ufpb.threadControl.MessengerConcurrent.Model.Client;
import br.ufpb.threadControl.MessengerConcurrent.Model.Product;
import br.ufpb.threadControl.MessengerConcurrent.Model.Promotion;

/**
 * Class send e-mail Promotions
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 0.0.1 Copyright (C) 2011 Diego Sousa de Azevedo
 */

public class SendPromotionMail implements Runnable {

	private Facade facade;
	private String creatorPromotionClient;
	private LinkedBlockingQueue<Promotion> listPromotion;
	private LinkedBlockingQueue<Product> listPromotionalProductsAux;
	private HashMap<Client, LinkedBlockingQueue<Product>> listProductPreferred;
	private Logger logger;
	private Set<Client> keysHashMapProductPreferred;

	public SendPromotionMail() {
		this.facade = Facade.getInstance();
		this.creatorPromotionClient = "Promotional Products: \n";
		this.listPromotion = facade.getListPromotion();
		this.listProductPreferred = facade.getListProductPreferred();
		this.logger = Logger
				.getLogger("br.ufpb.threadControl.birthdayMessage.Controller.SendPromotionMail");
		this.listPromotionalProductsAux = new LinkedBlockingQueue<Product>();
		this.keysHashMapProductPreferred = listProductPreferred.keySet();
	}

	public void run() {
		
		/*
		 * Create a list of products that are on sale.
		 */
		
		
		for(Promotion promotion: listPromotion){
			try {
				listPromotionalProductsAux.put(promotion.getProduct());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
				
		/*
		 * Create and send email promotions to the customer, according to their preferences
		 */
				
		for (Client key : keysHashMapProductPreferred) {
			LinkedBlockingQueue<Product> listProductPreferredClient = listProductPreferred
					.get(key);
			
			for (Product product : listProductPreferredClient) {				
				if (listPromotionalProductsAux.contains(product)) {
					creatorPromotionClient += product.toString() + "\n";

				}
			}

			logger.info("Promotion successfully sent to e-mail: "
					+ key.getMail() + "\nBelonging to the client: "
					+ key.getName() + "\nCopy of Promotion: \n"
					+ creatorPromotionClient);

			creatorPromotionClient = "";
		}
	}
}
