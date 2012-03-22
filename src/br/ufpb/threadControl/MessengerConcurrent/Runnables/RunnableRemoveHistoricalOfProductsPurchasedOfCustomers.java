package br.ufpb.threadControl.MessengerConcurrent.Runnables;

import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;
import br.ufpb.threadControl.MessengerConcurrent.Managers.ManagerPurchasesOfProducts;
/**
 * Remove Historical Of Products Purchased Of Customer.
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class RunnableRemoveHistoricalOfProductsPurchasedOfCustomers implements Runnable {

	private Client client;
	private ManagerPurchasesOfProducts productBuyManager;

	public RunnableRemoveHistoricalOfProductsPurchasedOfCustomers(Client client) {
		this.client = client;
		this.productBuyManager = ManagerPurchasesOfProducts.getInstance();
	}

	public void run() {
		productBuyManager.removeHistoricalOfProductsPurchasedOfCustomer(client);
	}

}
