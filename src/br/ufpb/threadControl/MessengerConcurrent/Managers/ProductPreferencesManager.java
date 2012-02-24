package br.ufpb.threadControl.MessengerConcurrent.Managers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Product;

/**
 * Manager products preferred of Customer.
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 0.0.1 Copyright (C) 2011 Diego Sousa de Azevedo
 */

public class ProductPreferredManager {

	private static ProductPreferredManager productPreferredManager;
	private Map<Client, List<Product>> listOfAllPreferences;
	private Logger logger;

	private ProductPreferredManager() {
		this.listOfAllPreferences = Collections
				.synchronizedMap(new HashMap<Client, List<Product>>());
		this.logger = Logger
				.getLogger("br.ufpb.threadControl.MessageManager.Controller.ProductPreferredManager");
	}

	/*
	 * Singleton
	 */

	public static synchronized ProductPreferredManager getInstance() {
		if (productPreferredManager == null) {
			productPreferredManager = new ProductPreferredManager();
		}
		return productPreferredManager;
	}

	public synchronized List<Product> addPreferencesClient(Client client) {
		if (client != null) {
			listOfAllPreferences.put(client, client.getListPreferredProduct());
			logger.info("Client preferences: " + client.getName()
					+ " added or updated successfully");
			return client.getListPreferredProduct();
		} else {
			return null;
		}

	}

	/*
	 * Remove the complete list of preferences of a customer
	 */

	public synchronized void removePreferencesClient(Client client) {		
			listOfAllPreferences.remove(client);
			logger.info("Client preferences: " + client.getName()
					+ " removed successfully");		
	}

	public synchronized List<Product> getListPreferenceCLient(Client client) {
		return listOfAllPreferences.get(client);

	}

	public synchronized Map<Client, List<Product>> getListPreferenceAllCLient() {
		return listOfAllPreferences;
	}
}