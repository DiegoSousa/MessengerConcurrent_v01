/**
 * 
 */
package br.ufpb.threadControl.MessengerConcurrent.ConcurrentOperations;

import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Product;
import br.ufpb.threadControl.MessengerConcurrent.Managers.ProductPreferencesManager;

/**
 * Description of the class
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class ThreadAddPreferencesClient implements Runnable {

	private ProductPreferencesManager productPreferredManager;
	private Client client;
	private Product product;

	public ThreadAddPreferencesClient(
			ProductPreferencesManager productPreferredManager, Client client, Product product) {
		this.productPreferredManager = productPreferredManager;
		this.client = client;
		this.product = product;
	}

	@Override
	public void run() {
		this.productPreferredManager.addPreferencesClient(client, product);
	}
}