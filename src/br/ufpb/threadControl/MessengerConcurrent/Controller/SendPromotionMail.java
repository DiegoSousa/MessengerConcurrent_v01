package br.ufpb.threadControl.MessengerConcurrent.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Product;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Promotion;

/**
 * Class send e-mail Promotions
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 2.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class SendPromotionMail implements Runnable {

	private Facade facade;
	private Logger logger;
	private BlockingQueue<BlockingQueue<Promotion>> copyListOfAllPromotion;
	private BlockingQueue<Map<Client, List<Product>>> copyPreferencesOfAllClients;
	private BlockingQueue<Promotion> takerPromotionsList;
	private Map<Client, List<Product>> takerProductPreferredClient;
	private Set<Client> groupClients;
	private List<Promotion> groupPromotions;

	public SendPromotionMail() {
		this.facade = Facade.getInstance();
		this.copyPreferencesOfAllClients = new LinkedBlockingQueue<Map<Client, List<Product>>>();
		this.copyListOfAllPromotion = new LinkedBlockingQueue<BlockingQueue<Promotion>>();
		this.groupPromotions = new ArrayList<Promotion>();
		this.logger = Logger
				.getLogger("br.ufpb.threadControl.birthdayMessage.Controller.SendPromotionMail");
	}

	public void run() {

		/*
		 * Get the list of all promotions
		 */

		facade.getListPromotion(copyListOfAllPromotion);

		try {
			this.takerPromotionsList = copyListOfAllPromotion.take();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		/*
		 * Transfers the list of promotions like BlockingQueue to List.
		 */

		for (Promotion promotion : takerPromotionsList) {
			groupPromotions.add(promotion);
		}

		/*
		 * Get the map with the product preferences of each client
		 */

		facade.getListProductPreferredAllClients(copyPreferencesOfAllClients);

		try {
			takerProductPreferredClient = copyPreferencesOfAllClients.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		/*
		 * Create and send the promotions for Clients
		 */

		this.groupClients = takerProductPreferredClient.keySet();

		for (Client client : groupClients) {

			String creatorPromotionClient = "Promotional Products: \n";
			List<Product> listProductPreferredAux = takerProductPreferredClient
					.get(client);

			for (Product product : listProductPreferredAux) {
				if (groupPromotions.contains(product)) {
					Promotion promotion = groupPromotions.get(groupPromotions
							.indexOf(product));
					creatorPromotionClient += promotion + "\n";
				}
			}

			logger.info("Promotion successfully sent to e-mail: "
					+ client.getMail() + "\nBelonging to the client: "
					+ client.getName() + "\nSent Copies of the Promotion: \n"
					+ creatorPromotionClient);

			creatorPromotionClient = "";
		}
	}
}
