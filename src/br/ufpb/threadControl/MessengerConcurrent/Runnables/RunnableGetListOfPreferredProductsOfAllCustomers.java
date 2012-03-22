/**
 * 
 */
package br.ufpb.threadControl.MessengerConcurrent.Runnables;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Product;
import br.ufpb.threadControl.MessengerConcurrent.Managers.ManagerProductPreferences;

/**
 * Runnable Get List Of Preferred Products Of All Customers.
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class RunnableGetListOfPreferredProductsOfAllCustomers implements Runnable {
	
	private ManagerProductPreferences productPreferredManager;
	private BlockingQueue<Map<Client, List<Product>>>listProductPreferred;

	public RunnableGetListOfPreferredProductsOfAllCustomers(
			ManagerProductPreferences productPreferredManager,
			BlockingQueue<Map<Client, List<Product>>> listProductPreferred) {
		this.productPreferredManager = productPreferredManager;
		this.listProductPreferred = listProductPreferred;
	}

	@Override
	public void run() {		
		try {
			listProductPreferred.put(productPreferredManager
					.getListOfPreferredProductsOfAllCustomers());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}