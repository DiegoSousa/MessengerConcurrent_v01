/**
 * 
 */
package br.ufpb.threadControl.MessengerConcurrent.Runnables;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Product;
import br.ufpb.threadControl.MessengerConcurrent.Managers.ManagerProductPreferences;

/**
 * Runnable Get List Of Preferred Products Of Customers.
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class RunnableGetListOfPreferredProductsOfCustomers implements Runnable {

	private ManagerProductPreferences productPreferredManager;
	private BlockingQueue<List<Product>> listProductPreferred;
	private Client client;

	public RunnableGetListOfPreferredProductsOfCustomers(
			ManagerProductPreferences productPreferredManager,
			BlockingQueue<List<Product>> listProductPreferred, Client client) {
		this.productPreferredManager = productPreferredManager;
		this.listProductPreferred = listProductPreferred;
		this.client = client;
	}

	@Override
	public void run() {
		try {
			listProductPreferred.put(productPreferredManager
					.getListOfPreferredProductsOfCustomers(this.client));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
