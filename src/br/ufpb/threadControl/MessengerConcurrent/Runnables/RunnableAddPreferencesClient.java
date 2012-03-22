/**
 * 
 */
package br.ufpb.threadControl.MessengerConcurrent.Runnables;

import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Product;
import br.ufpb.threadControl.MessengerConcurrent.Managers.ManagerProductPreferences;

/**
 * Runnable adder Preferences Client
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class RunnableAddPreferencesClient implements Runnable {

	private ManagerProductPreferences productPreferredManager;
	private Client client;
	private Product product;

	public RunnableAddPreferencesClient(
			ManagerProductPreferences productPreferredManager, Client client, Product product) {
		this.productPreferredManager = productPreferredManager;
		this.client = client;
		this.product = product;
	}

	@Override
	public void run() {
		this.productPreferredManager.addPreferencesClient(client, product);
	}
}