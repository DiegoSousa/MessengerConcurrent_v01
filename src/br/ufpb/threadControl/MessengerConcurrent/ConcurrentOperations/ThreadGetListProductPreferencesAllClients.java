/**
 * 
 */
package br.ufpb.threadControl.MessengerConcurrent.ConcurrentOperations;

import java.util.List;
import java.util.Map;
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

public class ThreadGetListProductPreferencesAllClients implements Runnable {

	private ProductPreferencesManager productPreferredManager;
	private BlockingQueue<Map<Client, List<Product>>>listProductPreferred;

	public ThreadGetListProductPreferencesAllClients(
			ProductPreferencesManager productPreferredManager,
			BlockingQueue<Map<Client, List<Product>>> listProductPreferred) {
		this.productPreferredManager = productPreferredManager;
		this.listProductPreferred = listProductPreferred;
	}

	@Override
	public void run() {		
		try {
			listProductPreferred.put(productPreferredManager
					.getListPreferenceAllCLient());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}