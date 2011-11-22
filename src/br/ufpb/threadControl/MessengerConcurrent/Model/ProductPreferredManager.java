package br.ufpb.threadControl.MessengerConcurrent.Model;

import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

/**
 * Manager products preferred of Customer.
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 0.0.1 Copyright (C) 2011 Diego Sousa de Azevedo
 */

public class ProductPreferredManager {

	private HashMap<Client, LinkedBlockingQueue<Product>> listOfAllPreferences;
	private static ProductPreferredManager productPreferredManager;
	private Logger logger;

	
	private ProductPreferredManager() {
		this.listOfAllPreferences = new HashMap<Client, LinkedBlockingQueue<Product>>();
		this.logger = Logger.getLogger("br.ufpb.threadControl.MessageManager.Controller.ProductPreferredManager");		
	}	
	
	/*
	 * Singleton
	 */

	public static ProductPreferredManager getInstance() {
		if (productPreferredManager == null){
			productPreferredManager = new ProductPreferredManager();
			return productPreferredManager;
		} else {
			return productPreferredManager;
		}
	}	

	public void addPreferencesClient(Client client) {
		listOfAllPreferences.put(client, client.getListPreference());
		logger.info("Client preferences: " + client.getName()
				+ " added or updated successfully");
	}

	/*
	 * Remove the complete list of preferences of a customer
	 */
		
	public void removePreferencesClient(Client client) {
		listOfAllPreferences.remove(client);
		logger.info("Client preferences: " + client.getName()
				+ " removed successfully");
	}
	
	public HashMap<Client, LinkedBlockingQueue<Product>> getListPreference() {
		return listOfAllPreferences;
	}
}