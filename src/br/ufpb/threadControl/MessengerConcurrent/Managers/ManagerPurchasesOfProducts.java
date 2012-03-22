package br.ufpb.threadControl.MessengerConcurrent.Managers;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Product;

/**
 * Buy Manager
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 2.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class ManagerPurchasesOfProducts {

	private static ManagerPurchasesOfProducts buyManager;
	private Map<Client, List<Product>> listOfPurchaseOfAllCustomers;
	private Logger logger;	
	private ManagerProduct productManager;

	private ManagerPurchasesOfProducts() {		
		productManager = ManagerProduct.getInstance();
		this.listOfPurchaseOfAllCustomers = Collections
				.synchronizedMap(new HashMap<Client, List<Product>>());
		this.logger = Logger
				.getLogger("br.ufpb.threadControl.MessageManager.Controller.ProductPreferredManager");
	}

	/*
	 * Singleton
	 */

	public static synchronized ManagerPurchasesOfProducts getInstance() {
		if (buyManager == null) {
			buyManager = new ManagerPurchasesOfProducts();
		}
		return buyManager;
	}

	public synchronized List<Product> buyProduct(Client client,
			Product product, int quantityOfProductsToBuy) {
		
		if (client != null
				&& listOfPurchaseOfAllCustomers.get(client) != null
				&& product.getQuantity() >= quantityOfProductsToBuy) { /* if there is already a list*/
			
			List<Product> listAux = listOfPurchaseOfAllCustomers.get(client);
			listAux.add(product);
			listOfPurchaseOfAllCustomers.put(client, listAux);
			product.setQuantity(product.getQuantity() - quantityOfProductsToBuy);
			productManager.editProduct(product);
			logger.info("successfully performed buy");
			return listOfPurchaseOfAllCustomers.get(client);
			
		} else if (client != null
				&& listOfPurchaseOfAllCustomers.get(client) == null) {/* if the list does not exist*/
			List<Product> listAux = new LinkedList<Product>();
			listAux.add(product);
			listOfPurchaseOfAllCustomers.put(client, listAux);
			product.setQuantity(product.getQuantity() - quantityOfProductsToBuy);
			productManager.editProduct(product);
			return listOfPurchaseOfAllCustomers.get(client);
		}
		return null;
	}

	public synchronized void removeHistoricalOfProductsPurchasedOfCustomer(Client client) {
		listOfPurchaseOfAllCustomers.remove(client);
		logger.info("Client Buy: " + client.getName() + " removed successfully");
	}

	public synchronized List<Product> getHistoricalOfProductsPurchasedOfCustomers(Client client) {
		return listOfPurchaseOfAllCustomers.get(client);
	}

	public synchronized Map<Client, List<Product>> getHistoricalOfProductsPurchasedOfAllCustomers() {
		return listOfPurchaseOfAllCustomers;
	}
}