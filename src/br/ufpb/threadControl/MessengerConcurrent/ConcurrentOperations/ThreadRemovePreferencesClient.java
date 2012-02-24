/**
 * 
 */
package br.ufpb.threadControl.MessengerConcurrent.ConcurrentOperations;

import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;
import br.ufpb.threadControl.MessengerConcurrent.Managers.ProductPreferencesManager;

/**
 * Description of the class
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class ThreadRemovePreferencesClient implements Runnable {

	private ProductPreferencesManager productPreferredManager;
	private Client client;

	public ThreadRemovePreferencesClient(
			ProductPreferencesManager productPreferredManager, Client client) {
		this.productPreferredManager = productPreferredManager;
		this.client = client;
	}

	@Override
	public void run() {
		this.productPreferredManager.removeAllPreferencesClient(client);
	}
}
