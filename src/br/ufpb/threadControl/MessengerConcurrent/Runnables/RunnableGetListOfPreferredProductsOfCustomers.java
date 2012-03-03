/**
 * 
 */
package br.ufpb.threadControl.MessengerConcurrent.ConcurrentOperations;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Product;
import br.ufpb.threadControl.MessengerConcurrent.Managers.ProductPreferencesManager;

/**
 * Description of the class
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class ThreadGetListProductPreferencesClient implements Runnable {

	private ProductPreferencesManager productPreferredManager;
	private BlockingQueue<List<Product>> listProductPreferred;
	private Client client;

	public ThreadGetListProductPreferencesClient(
			ProductPreferencesManager productPreferredManager,
			BlockingQueue<List<Product>> listProductPreferred, Client client) {
		this.productPreferredManager = productPreferredManager;
		this.listProductPreferred = listProductPreferred;
		this.client = client;
	}

	@Override
	public void run() {
		try {
			listProductPreferred.put(productPreferredManager
					.getListPreferenceCLient(this.client));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
