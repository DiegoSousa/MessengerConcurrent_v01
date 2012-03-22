/**
 * 
 */
package br.ufpb.threadControl.MessengerConcurrent.Runnables;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Product;
import br.ufpb.threadControl.MessengerConcurrent.Managers.ManagerPurchasesOfProducts;

/**
 * Runnable Get Historical Of Products Purchased Of All Customers.
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class RunnableGetHistoricalOfProductsPurchasedOfAllCustomers implements Runnable {

	private ManagerPurchasesOfProducts managerPurchasesOfProducts;
	private BlockingQueue<Map<Client, List<Product>>> list;	

	public RunnableGetHistoricalOfProductsPurchasedOfAllCustomers(
			BlockingQueue<Map<Client, List<Product>>> list) {
		this.managerPurchasesOfProducts = ManagerPurchasesOfProducts.getInstance();
		this.list = list;
	}

	@Override
	public void run() {
		try {
			list.put(managerPurchasesOfProducts.getHistoricalOfProductsPurchasedOfAllCustomers());
		} catch (InterruptedException e) {
			e.getMessage();
		}
	}
}