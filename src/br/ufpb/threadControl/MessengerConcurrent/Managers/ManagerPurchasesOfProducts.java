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

public class ProductBuyManager {

	private static ProductBuyManager buyManager;
	private Map<Client, List<Product>> listOfAllBuy;
	private Logger logger;	
	private ProductManager productManager;

	private ProductBuyManager() {		
		productManager = ProductManager.getInstance();
		this.listOfAllBuy = Collections
				.synchronizedMap(new HashMap<Client, List<Product>>());
		this.logger = Logger
				.getLogger("br.ufpb.threadControl.MessageManager.Controller.ProductPreferredManager");
	}

	/*
	 * Singleton
	 */

	public static synchronized ProductBuyManager getInstance() {
		if (buyManager == null) {
			buyManager = new ProductBuyManager();
		}
		return buyManager;
	}

	public synchronized List<Product> buyProduct(Client client,
			Product product, int quantityOfProductsToBuy) {
		
		if (client != null
				&& listOfAllBuy.get(client) != null
				&& product.getQuantity() >= quantityOfProductsToBuy) { /* if there is already a list*/
			
			List<Product> listAux = listOfAllBuy.get(client);
			listAux.add(product);
			listOfAllBuy.put(client, listAux);
			product.setQuantity(product.getQuantity() - quantityOfProductsToBuy);
			productManager.editProduct(product);
			logger.info("successfully performed buy");
			return listOfAllBuy.get(client);
			
		} else if (client != null
				&& listOfAllBuy.get(client) == null) {/* if the list does not exist*/
			List<Product> listAux = new LinkedList<Product>();
			listAux.add(product);
			listOfAllBuy.put(client, listAux);
			product.setQuantity(product.getQuantity() - quantityOfProductsToBuy);
			productManager.editProduct(product);
			return listOfAllBuy.get(client);
		}
		return null;
	}

	public synchronized void removeHistoricBuyClient(Client client) {
		listOfAllBuy.remove(client);
		logger.info("Client Buy: " + client.getName() + " removed successfully");
	}

	public synchronized List<Product> getHistoricBuyClient(Client client) {
		return listOfAllBuy.get(client);
	}

	public synchronized Map<Client, List<Product>> getHistoricBuyAllCLient() {
		return listOfAllBuy;
	}
}