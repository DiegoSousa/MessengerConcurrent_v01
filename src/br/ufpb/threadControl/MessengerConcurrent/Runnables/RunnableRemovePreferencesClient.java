/**
 * 
 */
package br.ufpb.threadControl.MessengerConcurrent.Runnables;

import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;
import br.ufpb.threadControl.MessengerConcurrent.Managers.ManagerProductPreferences;

/**
 * Runnable Remove Preferences Client.
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class RunnableRemovePreferencesClient implements Runnable {

	private ManagerProductPreferences productPreferredManager;
	private Client client;

	public RunnableRemovePreferencesClient(
			ManagerProductPreferences productPreferredManager, Client client) {
		this.productPreferredManager = productPreferredManager;
		this.client = client;
	}

	@Override
	public void run() {
		this.productPreferredManager.removeAllPreferencesClient(client);
	}
}
