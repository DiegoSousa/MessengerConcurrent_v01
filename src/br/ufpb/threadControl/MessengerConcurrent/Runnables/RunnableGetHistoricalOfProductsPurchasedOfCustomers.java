package br.ufpb.threadControl.MessengerConcurrent.Runnables;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Product;
import br.ufpb.threadControl.MessengerConcurrent.Managers.ManagerPurchasesOfProducts;

/**
 * Runnable Get Historical Of Products Purchased Of Customers.
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class RunnableGetHistoricalOfProductsPurchasedOfCustomers implements Runnable {

	private ManagerPurchasesOfProducts productBuyManager;
	private BlockingQueue<List<Product>> list;
	private Client client;

	public RunnableGetHistoricalOfProductsPurchasedOfCustomers(Client client,
			BlockingQueue<List<Product>> list) {
		this.client = client;
		this.list = list;
		this.productBuyManager = ManagerPurchasesOfProducts.getInstance();
	}

	@Override
	public void run() {
		list.add(productBuyManager.getHistoricalOfProductsPurchasedOfCustomers(client));
	}

}
