/**
 * 
 */
package br.ufpb.threadControl.MessengerConcurrent.Runnables;

import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;
import br.ufpb.threadControl.MessengerConcurrent.Managers.ManagerClient;

/**
 * Runnable Remove Client
 * 
 * @author Diego Sousa - www.diegosousa.com
 * @version 1.0 Copyright (C) 2012 Diego Sousa de Azevedo
 */

public class RunnableRemoveClient implements Runnable {
	private ManagerClient clientManager;	
	Client client;

	public RunnableRemoveClient(ManagerClient manager, Client client) {
		this.clientManager = manager;
		this.client = client;
	}

	@Override
	public void run() {
		this.client = clientManager.removeClient(client);
	}
}
